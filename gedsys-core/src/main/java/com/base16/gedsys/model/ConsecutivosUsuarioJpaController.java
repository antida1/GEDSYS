/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base16.gedsys.model;

import com.base16.gedsys.entities.Consecutivo;
import com.base16.gedsys.entities.ConsecutivosUsuario;
import com.base16.gedsys.entities.Documento_;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.base16.gedsys.entities.Usuario;
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
 * @author robert
 */
public class ConsecutivosUsuarioJpaController implements Serializable {

    public ConsecutivosUsuarioJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ConsecutivosUsuario consecutivosUsuario) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario creadoPor = consecutivosUsuario.getCreadoPor();
            if (creadoPor != null) {
                creadoPor = em.getReference(creadoPor.getClass(), creadoPor.getId());
                consecutivosUsuario.setCreadoPor(creadoPor);
            }
            em.persist(consecutivosUsuario);
            if (creadoPor != null) {
                creadoPor.getConsecutivosUsuarioList().add(consecutivosUsuario);
                creadoPor = em.merge(creadoPor);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ConsecutivosUsuario consecutivosUsuario) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ConsecutivosUsuario persistentConsecutivosUsuario = em.find(ConsecutivosUsuario.class, consecutivosUsuario.getId());
            Usuario creadoPorOld = persistentConsecutivosUsuario.getCreadoPor();
            Usuario creadoPorNew = consecutivosUsuario.getCreadoPor();
            if (creadoPorNew != null) {
                creadoPorNew = em.getReference(creadoPorNew.getClass(), creadoPorNew.getId());
                consecutivosUsuario.setCreadoPor(creadoPorNew);
            }
            consecutivosUsuario = em.merge(consecutivosUsuario);
            if (creadoPorOld != null && !creadoPorOld.equals(creadoPorNew)) {
                creadoPorOld.getConsecutivosUsuarioList().remove(consecutivosUsuario);
                creadoPorOld = em.merge(creadoPorOld);
            }
            if (creadoPorNew != null && !creadoPorNew.equals(creadoPorOld)) {
                creadoPorNew.getConsecutivosUsuarioList().add(consecutivosUsuario);
                creadoPorNew = em.merge(creadoPorNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = consecutivosUsuario.getId();
                if (findConsecutivosUsuario(id) == null) {
                    throw new NonexistentEntityException("The consecutivosUsuario with id " + id + " no longer exists.");
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
            ConsecutivosUsuario consecutivosUsuario;
            try {
                consecutivosUsuario = em.getReference(ConsecutivosUsuario.class, id);
                consecutivosUsuario.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The consecutivosUsuario with id " + id + " no longer exists.", enfe);
            }
            Usuario creadoPor = consecutivosUsuario.getCreadoPor();
            if (creadoPor != null) {
                creadoPor.getConsecutivosUsuarioList().remove(consecutivosUsuario);
                creadoPor = em.merge(creadoPor);
            }
            em.remove(consecutivosUsuario);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ConsecutivosUsuario> findConsecutivosUsuarioEntities() {
        return findConsecutivosUsuarioEntities(true, -1, -1);
    }

    public List<ConsecutivosUsuario> findConsecutivosUsuarioEntities(int maxResults, int firstResult) {
        return findConsecutivosUsuarioEntities(false, maxResults, firstResult);
    }

    private List<ConsecutivosUsuario> findConsecutivosUsuarioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ConsecutivosUsuario.class));
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

    public ConsecutivosUsuario findConsecutivosUsuario(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ConsecutivosUsuario.class, id);
        } finally {
            em.close();
        }
    }
    
     public List<ConsecutivosUsuario> findConsecutivos(Usuario creadoPor, String consecutivo, Date starDate, Date endDate, String tipo) {
        return findConsecutivos(creadoPor, consecutivo, starDate, endDate, tipo, true, -1, -1);
    }

    public List<ConsecutivosUsuario> findConsecutivos(Usuario creadoPor, String consecutivo, Date starDate, Date endDate, String tipo, int maxResults, int firstResult) {
        return findConsecutivos(creadoPor, consecutivo, starDate, endDate, tipo, false, maxResults, firstResult);
    }

    private List<ConsecutivosUsuario> findConsecutivos(Usuario creadoPor, String consecutivo, Date starDate, Date endDate, String tipo, boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        List<ConsecutivosUsuario> consecutivos = null;
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery cq = cb.createQuery(ConsecutivosUsuario.class);
            
            Root doc = cq.from(ConsecutivosUsuario.class);
            List<Predicate> predicates = new ArrayList<Predicate>();

            if(creadoPor != null){
                Expression<Usuario> eCreadoPor = doc.get("creadoPor");
                predicates.add(cb.equal(eCreadoPor, creadoPor));
            }
            
            if (starDate != null && endDate != null) {
                predicates.add(cb.and(cb.between(doc.get("fechaCreacion"), starDate, endDate)));
            }
            
            if (tipo != null && !tipo.isEmpty()) {
                String pTipo = "%" + tipo + "%";
                Expression<String> etipo = doc.get("tipo");
                predicates.add(cb.and(cb.like(etipo, pTipo)));
            }
            
            if (consecutivo != null && !consecutivo.isEmpty()) {
                String pConsecutivo = "%" + consecutivo + "%";
                Expression<String> eRadicado = doc.get("consecutivo");
                predicates.add(cb.and(cb.like(eRadicado, pConsecutivo)));
            }
            
            cq.select(doc).where(predicates.toArray(new Predicate[]{}));
        
            TypedQuery<ConsecutivosUsuario> q = em.createQuery(cq);
            
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

    public int getConsecutivosUsuarioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ConsecutivosUsuario> rt = cq.from(ConsecutivosUsuario.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
