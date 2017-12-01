/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base16.gedsys.model;

import com.base16.gedsys.entities.Informe;
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
public class InformeJpaController implements Serializable {

    public InformeJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Informe informe) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario remitente = informe.getRemitente();
            if (remitente != null) {
                remitente = em.getReference(remitente.getClass(), remitente.getId());
                informe.setRemitente(remitente);
            }
            Usuario creadoPor = informe.getCreadoPor();
            if (creadoPor != null) {
                creadoPor = em.getReference(creadoPor.getClass(), creadoPor.getId());
                informe.setCreadoPor(creadoPor);
            }
            Usuario modificadoPor = informe.getModificadoPor();
            if (modificadoPor != null) {
                modificadoPor = em.getReference(modificadoPor.getClass(), modificadoPor.getId());
                informe.setModificadoPor(modificadoPor);
            }
            em.persist(informe);
            if (remitente != null) {
                remitente.getInformeList().add(informe);
                remitente = em.merge(remitente);
            }
            if (creadoPor != null) {
                creadoPor.getInformeList().add(informe);
                creadoPor = em.merge(creadoPor);
            }
            if (modificadoPor != null) {
                modificadoPor.getInformeList().add(informe);
                modificadoPor = em.merge(modificadoPor);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Informe informe) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Informe persistentInforme = em.find(Informe.class, informe.getId());
            Usuario remitenteOld = persistentInforme.getRemitente();
            Usuario remitenteNew = informe.getRemitente();
            Usuario creadoPorOld = persistentInforme.getCreadoPor();
            Usuario creadoPorNew = informe.getCreadoPor();
            Usuario modificadoPorOld = persistentInforme.getModificadoPor();
            Usuario modificadoPorNew = informe.getModificadoPor();
            if (remitenteNew != null) {
                remitenteNew = em.getReference(remitenteNew.getClass(), remitenteNew.getId());
                informe.setRemitente(remitenteNew);
            }
            if (creadoPorNew != null) {
                creadoPorNew = em.getReference(creadoPorNew.getClass(), creadoPorNew.getId());
                informe.setCreadoPor(creadoPorNew);
            }
            if (modificadoPorNew != null) {
                modificadoPorNew = em.getReference(modificadoPorNew.getClass(), modificadoPorNew.getId());
                informe.setModificadoPor(modificadoPorNew);
            }
            informe = em.merge(informe);
            if (remitenteOld != null && !remitenteOld.equals(remitenteNew)) {
                remitenteOld.getInformeList().remove(informe);
                remitenteOld = em.merge(remitenteOld);
            }
            if (remitenteNew != null && !remitenteNew.equals(remitenteOld)) {
                remitenteNew.getInformeList().add(informe);
                remitenteNew = em.merge(remitenteNew);
            }
            if (creadoPorOld != null && !creadoPorOld.equals(creadoPorNew)) {
                creadoPorOld.getInformeList().remove(informe);
                creadoPorOld = em.merge(creadoPorOld);
            }
            if (creadoPorNew != null && !creadoPorNew.equals(creadoPorOld)) {
                creadoPorNew.getInformeList().add(informe);
                creadoPorNew = em.merge(creadoPorNew);
            }
            if (modificadoPorOld != null && !modificadoPorOld.equals(modificadoPorNew)) {
                modificadoPorOld.getInformeList().remove(informe);
                modificadoPorOld = em.merge(modificadoPorOld);
            }
            if (modificadoPorNew != null && !modificadoPorNew.equals(modificadoPorOld)) {
                modificadoPorNew.getInformeList().add(informe);
                modificadoPorNew = em.merge(modificadoPorNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = informe.getId();
                if (findInforme(id) == null) {
                    throw new NonexistentEntityException("The informe with id " + id + " no longer exists.");
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
            Informe informe;
            try {
                informe = em.getReference(Informe.class, id);
                informe.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The informe with id " + id + " no longer exists.", enfe);
            }
            Usuario remitente = informe.getRemitente();
            if (remitente != null) {
                remitente.getInformeList().remove(informe);
                remitente = em.merge(remitente);
            }
            Usuario creadoPor = informe.getCreadoPor();
            if (creadoPor != null) {
                creadoPor.getInformeList().remove(informe);
                creadoPor = em.merge(creadoPor);
            }
            Usuario modificadoPor = informe.getModificadoPor();
            if (modificadoPor != null) {
                modificadoPor.getInformeList().remove(informe);
                modificadoPor = em.merge(modificadoPor);
            }
            em.remove(informe);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Informe> findInformeEntities() {
        return findInformeEntities(true, -1, -1);
    }

    public List<Informe> findInformeEntities(int maxResults, int firstResult) {
        return findInformeEntities(false, maxResults, firstResult);
    }

    private List<Informe> findInformeEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Informe.class));
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

    public Informe findInforme(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Informe.class, id);
        } finally {
            em.close();
        }
    }

    public int getInformeCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Informe> rt = cq.from(Informe.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
