/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base16.gedsys.model;

import com.base16.gedsys.entities.Consecutivo;
import com.base16.gedsys.entities.ConsecutivosUsuario;
import com.base16.gedsys.entities.Documento;
import com.base16.gedsys.entities.Documento_;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.base16.gedsys.entities.Usuario;
import com.base16.gedsys.entities.TipoDocumento;
import com.base16.gedsys.model.exceptions.NonexistentEntityException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;

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

    public void create(Consecutivo consecutivo) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
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
            TipoDocumento tipoDocumento = consecutivo.getTipoDocumento();
            if (tipoDocumento != null) {
                tipoDocumento = em.getReference(tipoDocumento.getClass(), tipoDocumento.getId());
                consecutivo.setTipoDocumento(tipoDocumento);
            }
            em.persist(consecutivo);
            if (creadoPor != null) {
                creadoPor.getConsecutivoCollection().add(consecutivo);
                creadoPor = em.merge(creadoPor);
            }
            if (modificadoPor != null) {
                modificadoPor.getConsecutivoCollection().add(consecutivo);
                modificadoPor = em.merge(modificadoPor);
            }
            if (tipoDocumento != null) {
                tipoDocumento.getConsecutivoCollection().add(consecutivo);
                tipoDocumento = em.merge(tipoDocumento);
            }
            em.getTransaction().commit();
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
            Usuario creadoPorOld = persistentConsecutivo.getCreadoPor();
            Usuario creadoPorNew = consecutivo.getCreadoPor();
            Usuario modificadoPorOld = persistentConsecutivo.getModificadoPor();
            Usuario modificadoPorNew = consecutivo.getModificadoPor();
            TipoDocumento tipoDocumentoOld = persistentConsecutivo.getTipoDocumento();
            TipoDocumento tipoDocumentoNew = consecutivo.getTipoDocumento();
            if (creadoPorNew != null) {
                creadoPorNew = em.getReference(creadoPorNew.getClass(), creadoPorNew.getId());
                consecutivo.setCreadoPor(creadoPorNew);
            }
            if (modificadoPorNew != null) {
                modificadoPorNew = em.getReference(modificadoPorNew.getClass(), modificadoPorNew.getId());
                consecutivo.setModificadoPor(modificadoPorNew);
            }
            if (tipoDocumentoNew != null) {
                tipoDocumentoNew = em.getReference(tipoDocumentoNew.getClass(), tipoDocumentoNew.getId());
                consecutivo.setTipoDocumento(tipoDocumentoNew);
            }
            consecutivo = em.merge(consecutivo);
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
            if (tipoDocumentoOld != null && !tipoDocumentoOld.equals(tipoDocumentoNew)) {
                tipoDocumentoOld.getConsecutivoCollection().remove(consecutivo);
                tipoDocumentoOld = em.merge(tipoDocumentoOld);
            }
            if (tipoDocumentoNew != null && !tipoDocumentoNew.equals(tipoDocumentoOld)) {
                tipoDocumentoNew.getConsecutivoCollection().add(consecutivo);
                tipoDocumentoNew = em.merge(tipoDocumentoNew);
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
            TipoDocumento tipoDocumento = consecutivo.getTipoDocumento();
            if (tipoDocumento != null) {
                tipoDocumento.getConsecutivoCollection().remove(consecutivo);
                tipoDocumento = em.merge(tipoDocumento);
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
    
     public List<Consecutivo> findConsecutivos(Usuario creadoPor, String consecutivo, Date starDate, Date endDate, String tipo) {
        return findConsecutivos(creadoPor, consecutivo, starDate, endDate, tipo, true, -1, -1);
    }

    public List<Consecutivo> findConsecutivos(Usuario creadoPor, String consecutivo, Date starDate, Date endDate, String tipo, int maxResults, int firstResult) {
        return findConsecutivos(creadoPor, consecutivo, starDate, endDate, tipo, false, maxResults, firstResult);
    }

    private List<Consecutivo> findConsecutivos(Usuario creadoPor, String consecutivo, Date starDate, Date endDate, String tipo, boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        List<Consecutivo> consecutivos = null;
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery cq = cb.createQuery(Consecutivo.class);
            
            Root doc = cq.from(Consecutivo.class);
            List<Predicate> predicates = new ArrayList<Predicate>();

            if(creadoPor != null){
                Expression<Usuario> eCreadoPor = doc.get("creadoPor");
                predicates.add(cb.equal(eCreadoPor, creadoPor));
            }
            
            if (starDate != null && endDate != null) {
                predicates.add(cb.and(cb.between(doc.get(Documento_.fechaDocumento), starDate, endDate)));
            }

            if (tipo != null) {
                Expression<String> eTipoCon = doc.get("tipo");
                predicates.add(cb.and(cb.equal(eTipoCon, tipo)));
            }
            
            if (consecutivo != null && !consecutivo.isEmpty()) {
                String pConsecutivo = "%" + consecutivo + "%";
                Expression<String> eRadicado = doc.get("consecutivo");
                predicates.add(cb.and(cb.like(eRadicado, pConsecutivo)));
            }
            
            cq.select(doc).where(predicates.toArray(new Predicate[]{}));
        
            TypedQuery<Consecutivo> q = em.createQuery(cq);
            
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            consecutivos = q.getResultList();

        } catch (Exception e) {
            Logger.getLogger(DocumentoJpaController.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            em.close();
        }
        return consecutivos;
    }
    
    public Consecutivo findConsecutivoByTipoConsecutivo(String tipoConsecutivo) {
        Consecutivo consec = null;
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Consecutivo> consultaConsecutivo  =
                    em.createNamedQuery("Consecutivo.findByTipoConsecutivo", Consecutivo.class);
            consultaConsecutivo.setParameter("tipoConsecutivo", tipoConsecutivo);
            consec = consultaConsecutivo.getSingleResult();
        } finally{
            em.close();
        }
        return consec;
    }

}
