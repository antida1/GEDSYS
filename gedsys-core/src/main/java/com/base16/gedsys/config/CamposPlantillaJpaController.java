/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base16.gedsys.config;

import com.base16.gedsys.config.exceptions.NonexistentEntityException;
import com.base16.gedsys.config.exceptions.PreexistingEntityException;
import com.base16.gedsys.entities.CamposPlantilla;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.base16.gedsys.entities.Modulo;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author rober
 */
public class CamposPlantillaJpaController implements Serializable {

    public CamposPlantillaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(CamposPlantilla camposPlantilla) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Modulo modulo = camposPlantilla.getModulo();
            if (modulo != null) {
                modulo = em.getReference(modulo.getClass(), modulo.getId());
                camposPlantilla.setModulo(modulo);
            }
            em.persist(camposPlantilla);
            if (modulo != null) {
                modulo.getCamposPlantillaCollection().add(camposPlantilla);
                modulo = em.merge(modulo);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findCamposPlantilla(camposPlantilla.getId()) != null) {
                throw new PreexistingEntityException("CamposPlantilla " + camposPlantilla + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(CamposPlantilla camposPlantilla) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CamposPlantilla persistentCamposPlantilla = em.find(CamposPlantilla.class, camposPlantilla.getId());
            Modulo moduloOld = persistentCamposPlantilla.getModulo();
            Modulo moduloNew = camposPlantilla.getModulo();
            if (moduloNew != null) {
                moduloNew = em.getReference(moduloNew.getClass(), moduloNew.getId());
                camposPlantilla.setModulo(moduloNew);
            }
            camposPlantilla = em.merge(camposPlantilla);
            if (moduloOld != null && !moduloOld.equals(moduloNew)) {
                moduloOld.getCamposPlantillaCollection().remove(camposPlantilla);
                moduloOld = em.merge(moduloOld);
            }
            if (moduloNew != null && !moduloNew.equals(moduloOld)) {
                moduloNew.getCamposPlantillaCollection().add(camposPlantilla);
                moduloNew = em.merge(moduloNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = camposPlantilla.getId();
                if (findCamposPlantilla(id) == null) {
                    throw new NonexistentEntityException("The camposPlantilla with id " + id + " no longer exists.");
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
            CamposPlantilla camposPlantilla;
            try {
                camposPlantilla = em.getReference(CamposPlantilla.class, id);
                camposPlantilla.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The camposPlantilla with id " + id + " no longer exists.", enfe);
            }
            Modulo modulo = camposPlantilla.getModulo();
            if (modulo != null) {
                modulo.getCamposPlantillaCollection().remove(camposPlantilla);
                modulo = em.merge(modulo);
            }
            em.remove(camposPlantilla);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<CamposPlantilla> findCamposPlantillaEntities() {
        return findCamposPlantillaEntities(true, -1, -1);
    }

    public List<CamposPlantilla> findCamposPlantillaEntities(int maxResults, int firstResult) {
        return findCamposPlantillaEntities(false, maxResults, firstResult);
    }

    private List<CamposPlantilla> findCamposPlantillaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(CamposPlantilla.class));
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

    public CamposPlantilla findCamposPlantilla(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(CamposPlantilla.class, id);
        } finally {
            em.close();
        }
    }

    public int getCamposPlantillaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<CamposPlantilla> rt = cq.from(CamposPlantilla.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
