/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sucomunicacion.gedsys.model;

import com.sucomunicacion.gedsys.entities.Consecutivo;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.sucomunicacion.gedsys.entities.TipoDocumento;
import com.sucomunicacion.gedsys.entities.Usuario;
import com.sucomunicacion.gedsys.model.exceptions.NonexistentEntityException;
import com.sucomunicacion.gedsys.model.exceptions.PreexistingEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

/**
 *
 * @author rober
 */
public class ConsecutivoJpaController implements Serializable {

    public ConsecutivoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Consecutivo consecutivo) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TipoDocumento tipoDocumento = consecutivo.getTipoDocumento();
            if (tipoDocumento != null) {
                tipoDocumento = em.getReference(tipoDocumento.getClass(), tipoDocumento.getId());
                consecutivo.setTipoDocumento(tipoDocumento);
            }
            Usuario creadoPor = consecutivo.getCreadoPor();
            if (creadoPor != null) {
                creadoPor = em.getReference(creadoPor.getClass(), creadoPor.getId());
                consecutivo.setCreadoPor(creadoPor);
            }
            Usuario modificadoPor = consecutivo.getModificadoPor();
            if (modificadoPor != null) {
                modificadoPor = em.getReference(modificadoPor.getClass(), modificadoPor.getId());
                consecutivo.setModificadoPor(modificadoPor);
            }
            em.persist(consecutivo);
            if (tipoDocumento != null) {
                tipoDocumento.getConsecutivoCollection().add(consecutivo);
                tipoDocumento = em.merge(tipoDocumento);
            }
            if (creadoPor != null) {
                creadoPor.getConsecutivoCollection().add(consecutivo);
                creadoPor = em.merge(creadoPor);
            }
            if (modificadoPor != null) {
                modificadoPor.getConsecutivoCollection().add(consecutivo);
                modificadoPor = em.merge(modificadoPor);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findConsecutivo(consecutivo.getId()) != null) {
                throw new PreexistingEntityException("Consecutivo " + consecutivo + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Consecutivo consecutivo) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Consecutivo persistentConsecutivo = em.find(Consecutivo.class, consecutivo.getId());
            TipoDocumento tipoDocumentoOld = persistentConsecutivo.getTipoDocumento();
            TipoDocumento tipoDocumentoNew = consecutivo.getTipoDocumento();
            Usuario creadoPorOld = persistentConsecutivo.getCreadoPor();
            Usuario creadoPorNew = consecutivo.getCreadoPor();
            Usuario modificadoPorOld = persistentConsecutivo.getModificadoPor();
            Usuario modificadoPorNew = consecutivo.getModificadoPor();
            if (tipoDocumentoNew != null) {
                tipoDocumentoNew = em.getReference(tipoDocumentoNew.getClass(), tipoDocumentoNew.getId());
                consecutivo.setTipoDocumento(tipoDocumentoNew);
            }
            if (creadoPorNew != null) {
                creadoPorNew = em.getReference(creadoPorNew.getClass(), creadoPorNew.getId());
                consecutivo.setCreadoPor(creadoPorNew);
            }
            if (modificadoPorNew != null) {
                modificadoPorNew = em.getReference(modificadoPorNew.getClass(), modificadoPorNew.getId());
                consecutivo.setModificadoPor(modificadoPorNew);
            }
            consecutivo = em.merge(consecutivo);
            if (tipoDocumentoOld != null && !tipoDocumentoOld.equals(tipoDocumentoNew)) {
                tipoDocumentoOld.getConsecutivoCollection().remove(consecutivo);
                tipoDocumentoOld = em.merge(tipoDocumentoOld);
            }
            if (tipoDocumentoNew != null && !tipoDocumentoNew.equals(tipoDocumentoOld)) {
                tipoDocumentoNew.getConsecutivoCollection().add(consecutivo);
                tipoDocumentoNew = em.merge(tipoDocumentoNew);
            }
            if (creadoPorOld != null && !creadoPorOld.equals(creadoPorNew)) {
                creadoPorOld.getConsecutivoCollection().remove(consecutivo);
                creadoPorOld = em.merge(creadoPorOld);
            }
            if (creadoPorNew != null && !creadoPorNew.equals(creadoPorOld)) {
                creadoPorNew.getConsecutivoCollection().add(consecutivo);
                creadoPorNew = em.merge(creadoPorNew);
            }
            if (modificadoPorOld != null && !modificadoPorOld.equals(modificadoPorNew)) {
                modificadoPorOld.getConsecutivoCollection().remove(consecutivo);
                modificadoPorOld = em.merge(modificadoPorOld);
            }
            if (modificadoPorNew != null && !modificadoPorNew.equals(modificadoPorOld)) {
                modificadoPorNew.getConsecutivoCollection().add(consecutivo);
                modificadoPorNew = em.merge(modificadoPorNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = consecutivo.getId();
                if (findConsecutivo(id) == null) {
                    throw new NonexistentEntityException("The consecutivo with id " + id + " no longer exists.");
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
            Consecutivo consecutivo;
            try {
                consecutivo = em.getReference(Consecutivo.class, id);
                consecutivo.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The consecutivo with id " + id + " no longer exists.", enfe);
            }
            TipoDocumento tipoDocumento = consecutivo.getTipoDocumento();
            if (tipoDocumento != null) {
                tipoDocumento.getConsecutivoCollection().remove(consecutivo);
                tipoDocumento = em.merge(tipoDocumento);
            }
            Usuario creadoPor = consecutivo.getCreadoPor();
            if (creadoPor != null) {
                creadoPor.getConsecutivoCollection().remove(consecutivo);
                creadoPor = em.merge(creadoPor);
            }
            Usuario modificadoPor = consecutivo.getModificadoPor();
            if (modificadoPor != null) {
                modificadoPor.getConsecutivoCollection().remove(consecutivo);
                modificadoPor = em.merge(modificadoPor);
            }
            em.remove(consecutivo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Consecutivo> findConsecutivoEntities() {
        return findConsecutivoEntities(true, -1, -1);
    }

    public List<Consecutivo> findConsecutivoEntities(int maxResults, int firstResult) {
        return findConsecutivoEntities(false, maxResults, firstResult);
    }

    private List<Consecutivo> findConsecutivoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Consecutivo.class));
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

    public Consecutivo findConsecutivo(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Consecutivo.class, id);
        } finally {
            em.close();
        }
    }

    public int getConsecutivoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Consecutivo> rt = cq.from(Consecutivo.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    public Consecutivo findConsecutivoByTipoConsecutivo(String tipoConsecutivo) {
        Consecutivo consec = null;
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Consecutivo> consultaConsecutivo  = 
                    em.createNamedQuery("Consecutivo.findByTipoConsecutivo", Consecutivo.class);
            consultaConsecutivo.setParameter("tipoConsecutivo", tipoConsecutivo);
            consec = consultaConsecutivo.getSingleResult();
        } catch (Exception e) {
            throw e;
        }
        return consec;
    }
    
}
