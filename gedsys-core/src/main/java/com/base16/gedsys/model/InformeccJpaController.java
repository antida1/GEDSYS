/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base16.gedsys.model;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.base16.gedsys.entities.Informe;
import com.base16.gedsys.entities.Informecc;
import com.base16.gedsys.entities.Usuario;
import com.base16.gedsys.model.exceptions.NonexistentEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author programacion01
 */
public class InformeccJpaController implements Serializable {

    public InformeccJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Informecc informecc) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Informe informe = informecc.getInforme();
            if (informe != null) {
                informe = em.getReference(informe.getClass(), informe.getId());
                informecc.setInforme(informe);
            }
            Usuario conCopiaA = informecc.getConCopiaA();
            if (conCopiaA != null) {
                conCopiaA = em.getReference(conCopiaA.getClass(), conCopiaA.getId());
                informecc.setConCopiaA(conCopiaA);
            }
            em.persist(informecc);
            if (informe != null) {
                informe.getInformeccList().add(informecc);
                informe = em.merge(informe);
            }
            if (conCopiaA != null) {
                conCopiaA.getInformeccList().add(informecc);
                conCopiaA = em.merge(conCopiaA);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Informecc informecc) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Informecc persistentInformecc = em.find(Informecc.class, informecc.getId());
            Informe informeOld = persistentInformecc.getInforme();
            Informe informeNew = informecc.getInforme();
            Usuario conCopiaAOld = persistentInformecc.getConCopiaA();
            Usuario conCopiaANew = informecc.getConCopiaA();
            if (informeNew != null) {
                informeNew = em.getReference(informeNew.getClass(), informeNew.getId());
                informecc.setInforme(informeNew);
            }
            if (conCopiaANew != null) {
                conCopiaANew = em.getReference(conCopiaANew.getClass(), conCopiaANew.getId());
                informecc.setConCopiaA(conCopiaANew);
            }
            informecc = em.merge(informecc);
            if (informeOld != null && !informeOld.equals(informeNew)) {
                informeOld.getInformeccList().remove(informecc);
                informeOld = em.merge(informeOld);
            }
            if (informeNew != null && !informeNew.equals(informeOld)) {
                informeNew.getInformeccList().add(informecc);
                informeNew = em.merge(informeNew);
            }
            if (conCopiaAOld != null && !conCopiaAOld.equals(conCopiaANew)) {
                conCopiaAOld.getInformeccList().remove(informecc);
                conCopiaAOld = em.merge(conCopiaAOld);
            }
            if (conCopiaANew != null && !conCopiaANew.equals(conCopiaAOld)) {
                conCopiaANew.getInformeccList().add(informecc);
                conCopiaANew = em.merge(conCopiaANew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = informecc.getId();
                if (findInformecc(id) == null) {
                    throw new NonexistentEntityException("The informecc with id " + id + " no longer exists.");
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
            Informecc informecc;
            try {
                informecc = em.getReference(Informecc.class, id);
                informecc.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The informecc with id " + id + " no longer exists.", enfe);
            }
            Informe informe = informecc.getInforme();
            if (informe != null) {
                informe.getInformeccList().remove(informecc);
                informe = em.merge(informe);
            }
            Usuario conCopiaA = informecc.getConCopiaA();
            if (conCopiaA != null) {
                conCopiaA.getInformeccList().remove(informecc);
                conCopiaA = em.merge(conCopiaA);
            }
            em.remove(informecc);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Informecc> findInformeccEntities() {
        return findInformeccEntities(true, -1, -1);
    }

    public List<Informecc> findInformeccEntities(int maxResults, int firstResult) {
        return findInformeccEntities(false, maxResults, firstResult);
    }

    private List<Informecc> findInformeccEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Informecc.class));
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

    public Informecc findInformecc(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Informecc.class, id);
        } finally {
            em.close();
        }
    }

    public int getInformeccCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Informecc> rt = cq.from(Informecc.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
