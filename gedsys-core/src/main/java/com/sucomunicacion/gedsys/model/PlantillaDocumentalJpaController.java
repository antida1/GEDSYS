/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sucomunicacion.gedsys.model;

import com.sucomunicacion.gedsys.entities.PlantillaDocumental;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.sucomunicacion.gedsys.entities.Usuario;
import com.sucomunicacion.gedsys.entities.TipoDocumento;
import com.sucomunicacion.gedsys.model.exceptions.NonexistentEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author rober
 */
public class PlantillaDocumentalJpaController implements Serializable {

    public PlantillaDocumentalJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(PlantillaDocumental plantillaDocumental) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario creadoPor = plantillaDocumental.getCreadoPor();
            if (creadoPor != null) {
                creadoPor = em.getReference(creadoPor.getClass(), creadoPor.getId());
                plantillaDocumental.setCreadoPor(creadoPor);
            }
            Usuario modificadoPor = plantillaDocumental.getModificadoPor();
            if (modificadoPor != null) {
                modificadoPor = em.getReference(modificadoPor.getClass(), modificadoPor.getId());
                plantillaDocumental.setModificadoPor(modificadoPor);
            }
            TipoDocumento tipoDocumento = plantillaDocumental.getTipoDocumento();
            if (tipoDocumento != null) {
                tipoDocumento = em.getReference(tipoDocumento.getClass(), tipoDocumento.getId());
                plantillaDocumental.setTipoDocumento(tipoDocumento);
            }
            em.persist(plantillaDocumental);
            if (creadoPor != null) {
                creadoPor.getPlantillaDocumentalCollection().add(plantillaDocumental);
                creadoPor = em.merge(creadoPor);
            }
            if (modificadoPor != null) {
                modificadoPor.getPlantillaDocumentalCollection().add(plantillaDocumental);
                modificadoPor = em.merge(modificadoPor);
            }
            if (tipoDocumento != null) {
                tipoDocumento.getPlantillaDocumentalCollection().add(plantillaDocumental);
                tipoDocumento = em.merge(tipoDocumento);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(PlantillaDocumental plantillaDocumental) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            PlantillaDocumental persistentPlantillaDocumental = em.find(PlantillaDocumental.class, plantillaDocumental.getId());
            Usuario creadoPorOld = persistentPlantillaDocumental.getCreadoPor();
            Usuario creadoPorNew = plantillaDocumental.getCreadoPor();
            Usuario modificadoPorOld = persistentPlantillaDocumental.getModificadoPor();
            Usuario modificadoPorNew = plantillaDocumental.getModificadoPor();
            TipoDocumento tipoDocumentoOld = persistentPlantillaDocumental.getTipoDocumento();
            TipoDocumento tipoDocumentoNew = plantillaDocumental.getTipoDocumento();
            if (creadoPorNew != null) {
                creadoPorNew = em.getReference(creadoPorNew.getClass(), creadoPorNew.getId());
                plantillaDocumental.setCreadoPor(creadoPorNew);
            }
            if (modificadoPorNew != null) {
                modificadoPorNew = em.getReference(modificadoPorNew.getClass(), modificadoPorNew.getId());
                plantillaDocumental.setModificadoPor(modificadoPorNew);
            }
            if (tipoDocumentoNew != null) {
                tipoDocumentoNew = em.getReference(tipoDocumentoNew.getClass(), tipoDocumentoNew.getId());
                plantillaDocumental.setTipoDocumento(tipoDocumentoNew);
            }
            plantillaDocumental = em.merge(plantillaDocumental);
            if (creadoPorOld != null && !creadoPorOld.equals(creadoPorNew)) {
                creadoPorOld.getPlantillaDocumentalCollection().remove(plantillaDocumental);
                creadoPorOld = em.merge(creadoPorOld);
            }
            if (creadoPorNew != null && !creadoPorNew.equals(creadoPorOld)) {
                creadoPorNew.getPlantillaDocumentalCollection().add(plantillaDocumental);
                creadoPorNew = em.merge(creadoPorNew);
            }
            if (modificadoPorOld != null && !modificadoPorOld.equals(modificadoPorNew)) {
                modificadoPorOld.getPlantillaDocumentalCollection().remove(plantillaDocumental);
                modificadoPorOld = em.merge(modificadoPorOld);
            }
            if (modificadoPorNew != null && !modificadoPorNew.equals(modificadoPorOld)) {
                modificadoPorNew.getPlantillaDocumentalCollection().add(plantillaDocumental);
                modificadoPorNew = em.merge(modificadoPorNew);
            }
            if (tipoDocumentoOld != null && !tipoDocumentoOld.equals(tipoDocumentoNew)) {
                tipoDocumentoOld.getPlantillaDocumentalCollection().remove(plantillaDocumental);
                tipoDocumentoOld = em.merge(tipoDocumentoOld);
            }
            if (tipoDocumentoNew != null && !tipoDocumentoNew.equals(tipoDocumentoOld)) {
                tipoDocumentoNew.getPlantillaDocumentalCollection().add(plantillaDocumental);
                tipoDocumentoNew = em.merge(tipoDocumentoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = plantillaDocumental.getId();
                if (findPlantillaDocumental(id) == null) {
                    throw new NonexistentEntityException("The plantillaDocumental with id " + id + " no longer exists.");
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
            PlantillaDocumental plantillaDocumental;
            try {
                plantillaDocumental = em.getReference(PlantillaDocumental.class, id);
                plantillaDocumental.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The plantillaDocumental with id " + id + " no longer exists.", enfe);
            }
            Usuario creadoPor = plantillaDocumental.getCreadoPor();
            if (creadoPor != null) {
                creadoPor.getPlantillaDocumentalCollection().remove(plantillaDocumental);
                creadoPor = em.merge(creadoPor);
            }
            Usuario modificadoPor = plantillaDocumental.getModificadoPor();
            if (modificadoPor != null) {
                modificadoPor.getPlantillaDocumentalCollection().remove(plantillaDocumental);
                modificadoPor = em.merge(modificadoPor);
            }
            TipoDocumento tipoDocumento = plantillaDocumental.getTipoDocumento();
            if (tipoDocumento != null) {
                tipoDocumento.getPlantillaDocumentalCollection().remove(plantillaDocumental);
                tipoDocumento = em.merge(tipoDocumento);
            }
            em.remove(plantillaDocumental);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<PlantillaDocumental> findPlantillaDocumentalEntities() {
        return findPlantillaDocumentalEntities(true, -1, -1);
    }

    public List<PlantillaDocumental> findPlantillaDocumentalEntities(int maxResults, int firstResult) {
        return findPlantillaDocumentalEntities(false, maxResults, firstResult);
    }

    private List<PlantillaDocumental> findPlantillaDocumentalEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(PlantillaDocumental.class));
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

    public PlantillaDocumental findPlantillaDocumental(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(PlantillaDocumental.class, id);
        } finally {
            em.close();
        }
    }

    public int getPlantillaDocumentalCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<PlantillaDocumental> rt = cq.from(PlantillaDocumental.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
