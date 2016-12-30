/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sucomunicacion.gedsys.model;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.sucomunicacion.gedsys.entities.Departamentos;
import com.sucomunicacion.gedsys.entities.Pais;
import com.sucomunicacion.gedsys.model.exceptions.NonexistentEntityException;
import com.sucomunicacion.gedsys.model.exceptions.PreexistingEntityException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Robert Alexis Mejia <rmejia@base16.co>
 */
public class PaisJpaController implements Serializable {

    public PaisJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Pais pais) throws PreexistingEntityException, Exception {
        if (pais.getDepartamentosList() == null) {
            pais.setDepartamentosList(new ArrayList<Departamentos>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Departamentos> attachedDepartamentosList = new ArrayList<Departamentos>();
            for (Departamentos departamentosListDepartamentosToAttach : pais.getDepartamentosList()) {
                departamentosListDepartamentosToAttach = em.getReference(departamentosListDepartamentosToAttach.getClass(), departamentosListDepartamentosToAttach.getId());
                attachedDepartamentosList.add(departamentosListDepartamentosToAttach);
            }
            pais.setDepartamentosList(attachedDepartamentosList);
            em.persist(pais);
            for (Departamentos departamentosListDepartamentos : pais.getDepartamentosList()) {
                Pais oldPaisOfDepartamentosListDepartamentos = departamentosListDepartamentos.getPais();
                departamentosListDepartamentos.setPais(pais);
                departamentosListDepartamentos = em.merge(departamentosListDepartamentos);
                if (oldPaisOfDepartamentosListDepartamentos != null) {
                    oldPaisOfDepartamentosListDepartamentos.getDepartamentosList().remove(departamentosListDepartamentos);
                    oldPaisOfDepartamentosListDepartamentos = em.merge(oldPaisOfDepartamentosListDepartamentos);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findPais(pais.getId()) != null) {
                throw new PreexistingEntityException("Pais " + pais + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Pais pais) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Pais persistentPais = em.find(Pais.class, pais.getId());
            List<Departamentos> departamentosListOld = persistentPais.getDepartamentosList();
            List<Departamentos> departamentosListNew = pais.getDepartamentosList();
            List<Departamentos> attachedDepartamentosListNew = new ArrayList<Departamentos>();
            for (Departamentos departamentosListNewDepartamentosToAttach : departamentosListNew) {
                departamentosListNewDepartamentosToAttach = em.getReference(departamentosListNewDepartamentosToAttach.getClass(), departamentosListNewDepartamentosToAttach.getId());
                attachedDepartamentosListNew.add(departamentosListNewDepartamentosToAttach);
            }
            departamentosListNew = attachedDepartamentosListNew;
            pais.setDepartamentosList(departamentosListNew);
            pais = em.merge(pais);
            for (Departamentos departamentosListOldDepartamentos : departamentosListOld) {
                if (!departamentosListNew.contains(departamentosListOldDepartamentos)) {
                    departamentosListOldDepartamentos.setPais(null);
                    departamentosListOldDepartamentos = em.merge(departamentosListOldDepartamentos);
                }
            }
            for (Departamentos departamentosListNewDepartamentos : departamentosListNew) {
                if (!departamentosListOld.contains(departamentosListNewDepartamentos)) {
                    Pais oldPaisOfDepartamentosListNewDepartamentos = departamentosListNewDepartamentos.getPais();
                    departamentosListNewDepartamentos.setPais(pais);
                    departamentosListNewDepartamentos = em.merge(departamentosListNewDepartamentos);
                    if (oldPaisOfDepartamentosListNewDepartamentos != null && !oldPaisOfDepartamentosListNewDepartamentos.equals(pais)) {
                        oldPaisOfDepartamentosListNewDepartamentos.getDepartamentosList().remove(departamentosListNewDepartamentos);
                        oldPaisOfDepartamentosListNewDepartamentos = em.merge(oldPaisOfDepartamentosListNewDepartamentos);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = pais.getId();
                if (findPais(id) == null) {
                    throw new NonexistentEntityException("The pais with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Pais pais;
            try {
                pais = em.getReference(Pais.class, id);
                pais.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The pais with id " + id + " no longer exists.", enfe);
            }
            List<Departamentos> departamentosList = pais.getDepartamentosList();
            for (Departamentos departamentosListDepartamentos : departamentosList) {
                departamentosListDepartamentos.setPais(null);
                departamentosListDepartamentos = em.merge(departamentosListDepartamentos);
            }
            em.remove(pais);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Pais> findPaisEntities() {
        return findPaisEntities(true, -1, -1);
    }

    public List<Pais> findPaisEntities(int maxResults, int firstResult) {
        return findPaisEntities(false, maxResults, firstResult);
    }

    private List<Pais> findPaisEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Pais.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Pais findPais(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Pais.class, id);
        } finally {
            em.close();
        }
    }

    public int getPaisCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Pais> rt = cq.from(Pais.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
