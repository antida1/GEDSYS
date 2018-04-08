/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base16.gedsys.model;

import com.base16.gedsys.entities.Constancia;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.base16.gedsys.entities.Usuario;
import com.base16.gedsys.model.exceptions.NonexistentEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author rober
 */
public class ConstanciaJpaController implements Serializable {

    public ConstanciaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Constancia constancia) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario remitente = constancia.getRemitente();
            if (remitente != null) {
                remitente = em.getReference(remitente.getClass(), remitente.getId());
                constancia.setRemitente(remitente);
            }
            Usuario creadoPor = constancia.getCreadoPor();
            if (creadoPor != null) {
                creadoPor = em.getReference(creadoPor.getClass(), creadoPor.getId());
                constancia.setCreadoPor(creadoPor);
            }
            Usuario modificadoPor = constancia.getModificadoPor();
            if (modificadoPor != null) {
                modificadoPor = em.getReference(modificadoPor.getClass(), modificadoPor.getId());
                constancia.setModificadoPor(modificadoPor);
            }
            em.persist(constancia);
            if (remitente != null) {
                remitente.getConstanciaList().add(constancia);
                remitente = em.merge(remitente);
            }
            if (creadoPor != null) {
                creadoPor.getConstanciaList().add(constancia);
                creadoPor = em.merge(creadoPor);
            }
            if (modificadoPor != null) {
                modificadoPor.getConstanciaList().add(constancia);
                modificadoPor = em.merge(modificadoPor);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Constancia constancia) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Constancia persistentConstancia = em.find(Constancia.class, constancia.getId());
            Usuario remitenteOld = persistentConstancia.getRemitente();
            Usuario remitenteNew = constancia.getRemitente();
            Usuario creadoPorOld = persistentConstancia.getCreadoPor();
            Usuario creadoPorNew = constancia.getCreadoPor();
            Usuario modificadoPorOld = persistentConstancia.getModificadoPor();
            Usuario modificadoPorNew = constancia.getModificadoPor();
            if (remitenteNew != null) {
                remitenteNew = em.getReference(remitenteNew.getClass(), remitenteNew.getId());
                constancia.setRemitente(remitenteNew);
            }
            if (creadoPorNew != null) {
                creadoPorNew = em.getReference(creadoPorNew.getClass(), creadoPorNew.getId());
                constancia.setCreadoPor(creadoPorNew);
            }
            if (modificadoPorNew != null) {
                modificadoPorNew = em.getReference(modificadoPorNew.getClass(), modificadoPorNew.getId());
                constancia.setModificadoPor(modificadoPorNew);
            }
            constancia = em.merge(constancia);
            if (remitenteOld != null && !remitenteOld.equals(remitenteNew)) {
                remitenteOld.getConstanciaList().remove(constancia);
                remitenteOld = em.merge(remitenteOld);
            }
            if (remitenteNew != null && !remitenteNew.equals(remitenteOld)) {
                remitenteNew.getConstanciaList().add(constancia);
                remitenteNew = em.merge(remitenteNew);
            }
            if (creadoPorOld != null && !creadoPorOld.equals(creadoPorNew)) {
                creadoPorOld.getConstanciaList().remove(constancia);
                creadoPorOld = em.merge(creadoPorOld);
            }
            if (creadoPorNew != null && !creadoPorNew.equals(creadoPorOld)) {
                creadoPorNew.getConstanciaList().add(constancia);
                creadoPorNew = em.merge(creadoPorNew);
            }
            if (modificadoPorOld != null && !modificadoPorOld.equals(modificadoPorNew)) {
                modificadoPorOld.getConstanciaList().remove(constancia);
                modificadoPorOld = em.merge(modificadoPorOld);
            }
            if (modificadoPorNew != null && !modificadoPorNew.equals(modificadoPorOld)) {
                modificadoPorNew.getConstanciaList().add(constancia);
                modificadoPorNew = em.merge(modificadoPorNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = constancia.getId();
                if (findConstancia(id) == null) {
                    throw new NonexistentEntityException("The constancia with id " + id + " no longer exists.");
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
            Constancia constancia;
            try {
                constancia = em.getReference(Constancia.class, id);
                constancia.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The constancia with id " + id + " no longer exists.", enfe);
            }
            Usuario remitente = constancia.getRemitente();
            if (remitente != null) {
                remitente.getConstanciaList().remove(constancia);
                remitente = em.merge(remitente);
            }
            Usuario creadoPor = constancia.getCreadoPor();
            if (creadoPor != null) {
                creadoPor.getConstanciaList().remove(constancia);
                creadoPor = em.merge(creadoPor);
            }
            Usuario modificadoPor = constancia.getModificadoPor();
            if (modificadoPor != null) {
                modificadoPor.getConstanciaList().remove(constancia);
                modificadoPor = em.merge(modificadoPor);
            }
            em.remove(constancia);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    
    public List<Constancia> findByEstadoYUsuario(int estado, Usuario usuario) {
       EntityManager em = getEntityManager();
       try {
           CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
           cq.select(cq.from(Constancia.class));
            Query q = em.createNamedQuery("Constancia.findByEstado",Integer.class)
                    .setParameter("estado", estado);           
            return q.getResultList();
        } finally {
            em.close();
        }
   }
    
    public List<Constancia> findConstanciaEntities() {
        return findConstanciaEntities(true, -1, -1);
    }

    public List<Constancia> findConstanciaEntities(int maxResults, int firstResult) {
        return findConstanciaEntities(false, maxResults, firstResult);
    }

    private List<Constancia> findConstanciaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Constancia.class));
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

    public Constancia findConstancia(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Constancia.class, id);
        } finally {
            em.close();
        }
    }

    public int getConstanciaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Constancia> rt = cq.from(Constancia.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
