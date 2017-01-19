/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sucomunicacion.gedsys.model;

import com.sucomunicacion.gedsys.entities.Departamentos;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.sucomunicacion.gedsys.entities.Pais;
import com.sucomunicacion.gedsys.entities.Municipios;
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
public class DepartamentosJpaController implements Serializable {

    public DepartamentosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Departamentos departamentos) throws PreexistingEntityException, Exception {
        if (departamentos.getMunicipiosList() == null) {
            departamentos.setMunicipiosList(new ArrayList<Municipios>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Pais pais = departamentos.getPais();
            if (pais != null) {
                pais = em.getReference(pais.getClass(), pais.getId());
                departamentos.setPais(pais);
            }
            List<Municipios> attachedMunicipiosList = new ArrayList<Municipios>();
            for (Municipios municipiosListMunicipiosToAttach : departamentos.getMunicipiosList()) {
                municipiosListMunicipiosToAttach = em.getReference(municipiosListMunicipiosToAttach.getClass(), municipiosListMunicipiosToAttach.getId());
                attachedMunicipiosList.add(municipiosListMunicipiosToAttach);
            }
            departamentos.setMunicipiosList(attachedMunicipiosList);
            em.persist(departamentos);
            if (pais != null) {
                pais.getDepartamentosList().add(departamentos);
                pais = em.merge(pais);
            }
            for (Municipios municipiosListMunicipios : departamentos.getMunicipiosList()) {
                Departamentos oldDepartamentoOfMunicipiosListMunicipios = municipiosListMunicipios.getDepartamento();
                municipiosListMunicipios.setDepartamento(departamentos);
                municipiosListMunicipios = em.merge(municipiosListMunicipios);
                if (oldDepartamentoOfMunicipiosListMunicipios != null) {
                    oldDepartamentoOfMunicipiosListMunicipios.getMunicipiosList().remove(municipiosListMunicipios);
                    oldDepartamentoOfMunicipiosListMunicipios = em.merge(oldDepartamentoOfMunicipiosListMunicipios);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findDepartamentos(departamentos.getId()) != null) {
                throw new PreexistingEntityException("Departamentos " + departamentos + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Departamentos departamentos) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Departamentos persistentDepartamentos = em.find(Departamentos.class, departamentos.getId());
            Pais paisOld = persistentDepartamentos.getPais();
            Pais paisNew = departamentos.getPais();
            List<Municipios> municipiosListOld = persistentDepartamentos.getMunicipiosList();
            List<Municipios> municipiosListNew = departamentos.getMunicipiosList();
            if (paisNew != null) {
                paisNew = em.getReference(paisNew.getClass(), paisNew.getId());
                departamentos.setPais(paisNew);
            }
            List<Municipios> attachedMunicipiosListNew = new ArrayList<Municipios>();
            for (Municipios municipiosListNewMunicipiosToAttach : municipiosListNew) {
                municipiosListNewMunicipiosToAttach = em.getReference(municipiosListNewMunicipiosToAttach.getClass(), municipiosListNewMunicipiosToAttach.getId());
                attachedMunicipiosListNew.add(municipiosListNewMunicipiosToAttach);
            }
            municipiosListNew = attachedMunicipiosListNew;
            departamentos.setMunicipiosList(municipiosListNew);
            departamentos = em.merge(departamentos);
            if (paisOld != null && !paisOld.equals(paisNew)) {
                paisOld.getDepartamentosList().remove(departamentos);
                paisOld = em.merge(paisOld);
            }
            if (paisNew != null && !paisNew.equals(paisOld)) {
                paisNew.getDepartamentosList().add(departamentos);
                paisNew = em.merge(paisNew);
            }
            for (Municipios municipiosListOldMunicipios : municipiosListOld) {
                if (!municipiosListNew.contains(municipiosListOldMunicipios)) {
                    municipiosListOldMunicipios.setDepartamento(null);
                    municipiosListOldMunicipios = em.merge(municipiosListOldMunicipios);
                }
            }
            for (Municipios municipiosListNewMunicipios : municipiosListNew) {
                if (!municipiosListOld.contains(municipiosListNewMunicipios)) {
                    Departamentos oldDepartamentoOfMunicipiosListNewMunicipios = municipiosListNewMunicipios.getDepartamento();
                    municipiosListNewMunicipios.setDepartamento(departamentos);
                    municipiosListNewMunicipios = em.merge(municipiosListNewMunicipios);
                    if (oldDepartamentoOfMunicipiosListNewMunicipios != null && !oldDepartamentoOfMunicipiosListNewMunicipios.equals(departamentos)) {
                        oldDepartamentoOfMunicipiosListNewMunicipios.getMunicipiosList().remove(municipiosListNewMunicipios);
                        oldDepartamentoOfMunicipiosListNewMunicipios = em.merge(oldDepartamentoOfMunicipiosListNewMunicipios);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = departamentos.getId();
                if (findDepartamentos(id) == null) {
                    throw new NonexistentEntityException("The departamentos with id " + id + " no longer exists.");
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
            Departamentos departamentos;
            try {
                departamentos = em.getReference(Departamentos.class, id);
                departamentos.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The departamentos with id " + id + " no longer exists.", enfe);
            }
            Pais pais = departamentos.getPais();
            if (pais != null) {
                pais.getDepartamentosList().remove(departamentos);
                pais = em.merge(pais);
            }
            List<Municipios> municipiosList = departamentos.getMunicipiosList();
            for (Municipios municipiosListMunicipios : municipiosList) {
                municipiosListMunicipios.setDepartamento(null);
                municipiosListMunicipios = em.merge(municipiosListMunicipios);
            }
            em.remove(departamentos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Departamentos> findDepartamentosEntities() {
        return findDepartamentosEntities(true, -1, -1);
    }

    public List<Departamentos> findDepartamentosEntities(int maxResults, int firstResult) {
        return findDepartamentosEntities(false, maxResults, firstResult);
    }

    private List<Departamentos> findDepartamentosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Departamentos.class));
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

    public Departamentos findDepartamentos(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Departamentos.class, id);
        } finally {
            em.close();
        }
    }

    public int getDepartamentosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Departamentos> rt = cq.from(Departamentos.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    public List<Departamentos> findDepartamentosByPais(Pais pais) {
        return findDepartamentosByPais(pais, -1, -1);
    }

    public List<Departamentos> findDepartamentosByPais(Pais pais, int maxResults, int firstResult) {
        return findDepartamentosByPais(pais, true, maxResults, firstResult);
    }
    
    private List<Departamentos> findDepartamentosByPais( Pais pais ,boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Departamentos.class));
            Query q = em.createNamedQuery("Departamentos.findByPais", Departamentos.class)
                    .setParameter("pais", pais);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }
    
}
