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
import com.sucomunicacion.gedsys.entities.Usuario;
import com.sucomunicacion.gedsys.entities.Departamento;
import com.sucomunicacion.gedsys.entities.Pais;
import com.sucomunicacion.gedsys.model.exceptions.NonexistentEntityException;
import com.sucomunicacion.gedsys.model.exceptions.PreexistingEntityException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author rober
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
        if (pais.getDepartamentoCollection() == null) {
            pais.setDepartamentoCollection(new ArrayList<Departamento>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario creadoPor = pais.getCreadoPor();
            if (creadoPor != null) {
                creadoPor = em.getReference(creadoPor.getClass(), creadoPor.getId());
                pais.setCreadoPor(creadoPor);
            }
            Usuario modificadoPor = pais.getModificadoPor();
            if (modificadoPor != null) {
                modificadoPor = em.getReference(modificadoPor.getClass(), modificadoPor.getId());
                pais.setModificadoPor(modificadoPor);
            }
            Collection<Departamento> attachedDepartamentoCollection = new ArrayList<Departamento>();
            for (Departamento departamentoCollectionDepartamentoToAttach : pais.getDepartamentoCollection()) {
                departamentoCollectionDepartamentoToAttach = em.getReference(departamentoCollectionDepartamentoToAttach.getClass(), departamentoCollectionDepartamentoToAttach.getId());
                attachedDepartamentoCollection.add(departamentoCollectionDepartamentoToAttach);
            }
            pais.setDepartamentoCollection(attachedDepartamentoCollection);
            em.persist(pais);
            if (creadoPor != null) {
                creadoPor.getPaisCollection().add(pais);
                creadoPor = em.merge(creadoPor);
            }
            if (modificadoPor != null) {
                modificadoPor.getPaisCollection().add(pais);
                modificadoPor = em.merge(modificadoPor);
            }
            for (Departamento departamentoCollectionDepartamento : pais.getDepartamentoCollection()) {
                Pais oldPaisOfDepartamentoCollectionDepartamento = departamentoCollectionDepartamento.getPais();
                departamentoCollectionDepartamento.setPais(pais);
                departamentoCollectionDepartamento = em.merge(departamentoCollectionDepartamento);
                if (oldPaisOfDepartamentoCollectionDepartamento != null) {
                    oldPaisOfDepartamentoCollectionDepartamento.getDepartamentoCollection().remove(departamentoCollectionDepartamento);
                    oldPaisOfDepartamentoCollectionDepartamento = em.merge(oldPaisOfDepartamentoCollectionDepartamento);
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
            Usuario creadoPorOld = persistentPais.getCreadoPor();
            Usuario creadoPorNew = pais.getCreadoPor();
            Usuario modificadoPorOld = persistentPais.getModificadoPor();
            Usuario modificadoPorNew = pais.getModificadoPor();
            Collection<Departamento> departamentoCollectionOld = persistentPais.getDepartamentoCollection();
            Collection<Departamento> departamentoCollectionNew = pais.getDepartamentoCollection();
            if (creadoPorNew != null) {
                creadoPorNew = em.getReference(creadoPorNew.getClass(), creadoPorNew.getId());
                pais.setCreadoPor(creadoPorNew);
            }
            if (modificadoPorNew != null) {
                modificadoPorNew = em.getReference(modificadoPorNew.getClass(), modificadoPorNew.getId());
                pais.setModificadoPor(modificadoPorNew);
            }
            Collection<Departamento> attachedDepartamentoCollectionNew = new ArrayList<Departamento>();
            for (Departamento departamentoCollectionNewDepartamentoToAttach : departamentoCollectionNew) {
                departamentoCollectionNewDepartamentoToAttach = em.getReference(departamentoCollectionNewDepartamentoToAttach.getClass(), departamentoCollectionNewDepartamentoToAttach.getId());
                attachedDepartamentoCollectionNew.add(departamentoCollectionNewDepartamentoToAttach);
            }
            departamentoCollectionNew = attachedDepartamentoCollectionNew;
            pais.setDepartamentoCollection(departamentoCollectionNew);
            pais = em.merge(pais);
            if (creadoPorOld != null && !creadoPorOld.equals(creadoPorNew)) {
                creadoPorOld.getPaisCollection().remove(pais);
                creadoPorOld = em.merge(creadoPorOld);
            }
            if (creadoPorNew != null && !creadoPorNew.equals(creadoPorOld)) {
                creadoPorNew.getPaisCollection().add(pais);
                creadoPorNew = em.merge(creadoPorNew);
            }
            if (modificadoPorOld != null && !modificadoPorOld.equals(modificadoPorNew)) {
                modificadoPorOld.getPaisCollection().remove(pais);
                modificadoPorOld = em.merge(modificadoPorOld);
            }
            if (modificadoPorNew != null && !modificadoPorNew.equals(modificadoPorOld)) {
                modificadoPorNew.getPaisCollection().add(pais);
                modificadoPorNew = em.merge(modificadoPorNew);
            }
            for (Departamento departamentoCollectionOldDepartamento : departamentoCollectionOld) {
                if (!departamentoCollectionNew.contains(departamentoCollectionOldDepartamento)) {
                    departamentoCollectionOldDepartamento.setPais(null);
                    departamentoCollectionOldDepartamento = em.merge(departamentoCollectionOldDepartamento);
                }
            }
            for (Departamento departamentoCollectionNewDepartamento : departamentoCollectionNew) {
                if (!departamentoCollectionOld.contains(departamentoCollectionNewDepartamento)) {
                    Pais oldPaisOfDepartamentoCollectionNewDepartamento = departamentoCollectionNewDepartamento.getPais();
                    departamentoCollectionNewDepartamento.setPais(pais);
                    departamentoCollectionNewDepartamento = em.merge(departamentoCollectionNewDepartamento);
                    if (oldPaisOfDepartamentoCollectionNewDepartamento != null && !oldPaisOfDepartamentoCollectionNewDepartamento.equals(pais)) {
                        oldPaisOfDepartamentoCollectionNewDepartamento.getDepartamentoCollection().remove(departamentoCollectionNewDepartamento);
                        oldPaisOfDepartamentoCollectionNewDepartamento = em.merge(oldPaisOfDepartamentoCollectionNewDepartamento);
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
            Usuario creadoPor = pais.getCreadoPor();
            if (creadoPor != null) {
                creadoPor.getPaisCollection().remove(pais);
                creadoPor = em.merge(creadoPor);
            }
            Usuario modificadoPor = pais.getModificadoPor();
            if (modificadoPor != null) {
                modificadoPor.getPaisCollection().remove(pais);
                modificadoPor = em.merge(modificadoPor);
            }
            Collection<Departamento> departamentoCollection = pais.getDepartamentoCollection();
            for (Departamento departamentoCollectionDepartamento : departamentoCollection) {
                departamentoCollectionDepartamento.setPais(null);
                departamentoCollectionDepartamento = em.merge(departamentoCollectionDepartamento);
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
