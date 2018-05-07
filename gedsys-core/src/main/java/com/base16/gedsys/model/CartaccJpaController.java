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
import com.base16.gedsys.entities.Carta;
import com.base16.gedsys.entities.Cartacc;
import com.base16.gedsys.entities.Usuario;
import com.base16.gedsys.model.exceptions.NonexistentEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author programacion01
 */
public class CartaccJpaController implements Serializable {

    public CartaccJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Cartacc cartacc) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Carta carta = cartacc.getCarta();
            if (carta != null) {
                carta = em.getReference(carta.getClass(), carta.getId());
                cartacc.setCarta(carta);
            }
            Usuario conCopiaA = cartacc.getConCopiaA();
            if (conCopiaA != null) {
                conCopiaA = em.getReference(conCopiaA.getClass(), conCopiaA.getId());
                cartacc.setConCopiaA(conCopiaA);
            }
            em.persist(cartacc);
            if (carta != null) {
                carta.getCartaccCollection().add(cartacc);
                carta = em.merge(carta);
            }
            if (conCopiaA != null) {
                conCopiaA.getCartaccCollection().add(cartacc);
                conCopiaA = em.merge(conCopiaA);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Cartacc cartacc) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cartacc persistentCartacc = em.find(Cartacc.class, cartacc.getId());
            Carta cartaOld = persistentCartacc.getCarta();
            Carta cartaNew = cartacc.getCarta();
            Usuario conCopiaAOld = persistentCartacc.getConCopiaA();
            Usuario conCopiaANew = cartacc.getConCopiaA();
            if (cartaNew != null) {
                cartaNew = em.getReference(cartaNew.getClass(), cartaNew.getId());
                cartacc.setCarta(cartaNew);
            }
            if (conCopiaANew != null) {
                conCopiaANew = em.getReference(conCopiaANew.getClass(), conCopiaANew.getId());
                cartacc.setConCopiaA(conCopiaANew);
            }
            cartacc = em.merge(cartacc);
            if (cartaOld != null && !cartaOld.equals(cartaNew)) {
                cartaOld.getCartaccCollection().remove(cartacc);
                cartaOld = em.merge(cartaOld);
            }
            if (cartaNew != null && !cartaNew.equals(cartaOld)) {
                cartaNew.getCartaccCollection().add(cartacc);
                cartaNew = em.merge(cartaNew);
            }
            if (conCopiaAOld != null && !conCopiaAOld.equals(conCopiaANew)) {
                conCopiaAOld.getCartaccCollection().remove(cartacc);
                conCopiaAOld = em.merge(conCopiaAOld);
            }
            if (conCopiaANew != null && !conCopiaANew.equals(conCopiaAOld)) {
                conCopiaANew.getCartaccCollection().add(cartacc);
                conCopiaANew = em.merge(conCopiaANew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = cartacc.getId();
                if (findCartacc(id) == null) {
                    throw new NonexistentEntityException("The cartacc with id " + id + " no longer exists.");
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
            Cartacc cartacc;
            try {
                cartacc = em.getReference(Cartacc.class, id);
                cartacc.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The cartacc with id " + id + " no longer exists.", enfe);
            }
            Carta carta = cartacc.getCarta();
            if (carta != null) {
                carta.getCartaccCollection().remove(cartacc);
                carta = em.merge(carta);
            }
            Usuario conCopiaA = cartacc.getConCopiaA();
            if (conCopiaA != null) {
                conCopiaA.getCartaccCollection().remove(cartacc);
                conCopiaA = em.merge(conCopiaA);
            }
            em.remove(cartacc);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Cartacc> findCartaccEntities() {
        return findCartaccEntities(true, -1, -1);
    }

    public List<Cartacc> findCartaccEntities(int maxResults, int firstResult) {
        return findCartaccEntities(false, maxResults, firstResult);
    }

    private List<Cartacc> findCartaccEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Cartacc.class));
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

    public Cartacc findCartacc(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Cartacc.class, id);
        } finally {
            em.close();
        }
    }

    public int getCartaccCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Cartacc> rt = cq.from(Cartacc.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
