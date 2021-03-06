/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base16.gedsys.model;

import com.base16.gedsys.entities.Carta;
import com.base16.gedsys.entities.Cartacc;
import com.base16.gedsys.entities.Documento;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.base16.gedsys.entities.Usuario;
import com.base16.gedsys.model.exceptions.NonexistentEntityException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author rober
 */
public class CartaJpaController implements Serializable {

    public CartaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Carta carta) {
        if(carta.getCartaccList() == null){
            carta.setCartaccList(new ArrayList<Cartacc>());
        }
        if(carta.getCartaccCollection() == null){
            carta.setCartaccCollection(new ArrayList<Cartacc>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario remitente = carta.getRemitente();
            if (remitente != null) {
                remitente = em.getReference(remitente.getClass(), remitente.getId());
                carta.setRemitente(remitente);
            }
            Usuario creadoPor = carta.getCreadoPor();
            if (creadoPor != null) {
                creadoPor = em.getReference(creadoPor.getClass(), creadoPor.getId());
                carta.setCreadoPor(creadoPor);
            }
            Usuario modificadoPor = carta.getModificadoPor();
            if (modificadoPor != null) {
                modificadoPor = em.getReference(modificadoPor.getClass(), modificadoPor.getId());
                carta.setModificadoPor(modificadoPor);
            }
            List<Cartacc> attachedCartaccList = new ArrayList<Cartacc>();
            for (Cartacc cartaccListCartaccToAttach : carta.getCartaccList()) {
                cartaccListCartaccToAttach = em.getReference(cartaccListCartaccToAttach.getClass(), cartaccListCartaccToAttach.getId());
                attachedCartaccList.add(cartaccListCartaccToAttach);
            }
            carta.setCartaccList(attachedCartaccList);
            Collection<Cartacc> attachedCartaccCollection = new ArrayList<Cartacc>();
            for (Cartacc cartaccCollectionCartaccToAttach : carta.getCartaccCollection()) {
                cartaccCollectionCartaccToAttach = em.getReference(cartaccCollectionCartaccToAttach.getClass(), cartaccCollectionCartaccToAttach.getId());
                attachedCartaccCollection.add(cartaccCollectionCartaccToAttach);
            }
            carta.setCartaccCollection(attachedCartaccCollection);
            em.persist(carta);
            if (remitente != null) {
                remitente.getCartaList().add(carta);
                remitente = em.merge(remitente);
            }
            if (creadoPor != null) {
                creadoPor.getCartaList().add(carta);
                creadoPor = em.merge(creadoPor);
            }
            if (modificadoPor != null) {
                modificadoPor.getCartaList().add(carta);
                modificadoPor = em.merge(modificadoPor);
            }
            for (Cartacc cartaccListCartacc : carta.getCartaccList()) {
                Carta oldCartaOfCartaccListCartacc = cartaccListCartacc.getCarta();
                cartaccListCartacc.setCarta(carta);
                cartaccListCartacc = em.merge(cartaccListCartacc);
                if (oldCartaOfCartaccListCartacc != null) {
                    oldCartaOfCartaccListCartacc.getCartaccList().remove(cartaccListCartacc);
                    oldCartaOfCartaccListCartacc = em.merge(oldCartaOfCartaccListCartacc);
                }
            }
            for (Cartacc cartaccCollectionCartacc : carta.getCartaccCollection()) {
                Carta oldCartaOfCartaccCollectionCartacc = cartaccCollectionCartacc.getCarta();
                cartaccCollectionCartacc.setCarta(carta);
                cartaccCollectionCartacc = em.merge(cartaccCollectionCartacc);
                if (oldCartaOfCartaccCollectionCartacc != null) {
                    oldCartaOfCartaccCollectionCartacc.getCartaccCollection().remove(cartaccCollectionCartacc);
                    oldCartaOfCartaccCollectionCartacc = em.merge(oldCartaOfCartaccCollectionCartacc);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Carta carta) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Carta persistentCarta = em.find(Carta.class, carta.getId());
            Usuario remitenteOld = persistentCarta.getRemitente();
            Usuario remitenteNew = carta.getRemitente();
            Usuario creadoPorOld = persistentCarta.getCreadoPor();
            Usuario creadoPorNew = carta.getCreadoPor();
            Usuario modificadoPorOld = persistentCarta.getModificadoPor();
            Usuario modificadoPorNew = carta.getModificadoPor();
            if (remitenteNew != null) {
                remitenteNew = em.getReference(remitenteNew.getClass(), remitenteNew.getId());
                carta.setRemitente(remitenteNew);
            }
            if (creadoPorNew != null) {
                creadoPorNew = em.getReference(creadoPorNew.getClass(), creadoPorNew.getId());
                carta.setCreadoPor(creadoPorNew);
            }
            if (modificadoPorNew != null) {
                modificadoPorNew = em.getReference(modificadoPorNew.getClass(), modificadoPorNew.getId());
                carta.setModificadoPor(modificadoPorNew);
            }
            carta = em.merge(carta);
            if (remitenteOld != null && !remitenteOld.equals(remitenteNew)) {
                remitenteOld.getCartaList().remove(carta);
                remitenteOld = em.merge(remitenteOld);
            }
            if (remitenteNew != null && !remitenteNew.equals(remitenteOld)) {
                remitenteNew.getCartaList().add(carta);
                remitenteNew = em.merge(remitenteNew);
            }
            if (creadoPorOld != null && !creadoPorOld.equals(creadoPorNew)) {
                creadoPorOld.getCartaList().remove(carta);
                creadoPorOld = em.merge(creadoPorOld);
            }
            if (creadoPorNew != null && !creadoPorNew.equals(creadoPorOld)) {
                creadoPorNew.getCartaList().add(carta);
                creadoPorNew = em.merge(creadoPorNew);
            }
            if (modificadoPorOld != null && !modificadoPorOld.equals(modificadoPorNew)) {
                modificadoPorOld.getCartaList().remove(carta);
                modificadoPorOld = em.merge(modificadoPorOld);
            }
            if (modificadoPorNew != null && !modificadoPorNew.equals(modificadoPorOld)) {
                modificadoPorNew.getCartaList().add(carta);
                modificadoPorNew = em.merge(modificadoPorNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = carta.getId();
                if (findCarta(id) == null) {
                    throw new NonexistentEntityException("The carta with id " + id + " no longer exists.");
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
            Carta carta;
            try {
                carta = em.getReference(Carta.class, id);
                carta.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The carta with id " + id + " no longer exists.", enfe);
            }
            Usuario remitente = carta.getRemitente();
            if (remitente != null) {
                remitente.getCartaList().remove(carta);
                remitente = em.merge(remitente);
            }
            Usuario creadoPor = carta.getCreadoPor();
            if (creadoPor != null) {
                creadoPor.getCartaList().remove(carta);
                creadoPor = em.merge(creadoPor);
            }
            Usuario modificadoPor = carta.getModificadoPor();
            if (modificadoPor != null) {
                modificadoPor.getCartaList().remove(carta);
                modificadoPor = em.merge(modificadoPor);
            }
            em.remove(carta);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    public List<Carta> findByEstadoYUsuario(String estado, Usuario usuario) {
       EntityManager em = getEntityManager();
       try {
           CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
           cq.select(cq.from(Carta.class));
            Query q = em.createNamedQuery("Carta.findByEstadoYUsuario",Integer.class)
                    .setParameter("estado", estado).setParameter("usuario", usuario);           
            return q.getResultList();
        } finally {
            em.close();
        }
   }

    public List<Carta> findCartaEntities() {
        return findCartaEntities(true, -1, -1);
    }

    public List<Carta> findCartaEntities(int maxResults, int firstResult) {
        return findCartaEntities(false, maxResults, firstResult);
    }

    private List<Carta> findCartaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Carta.class));
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

    public Carta findCarta(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Carta.class, id);
        } finally {
            em.close();
        }
    }

    public int getCartaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Carta> rt = cq.from(Carta.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    public Carta findCartaByDocumentoPadre(Documento documentoPadre) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Carta.class));
            Query q = em.createNamedQuery("Carta.findByDocumentoPadre", Documento.class)
                    .setParameter("documentoPadre", documentoPadre);
            return (Carta) q.getSingleResult();
         
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }
}
