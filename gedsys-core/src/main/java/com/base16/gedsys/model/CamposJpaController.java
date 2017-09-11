/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base16.gedsys.model;

import com.base16.gedsys.entities.Campos;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.base16.gedsys.entities.Usuario;
import com.base16.gedsys.entities.Modulo;
import com.base16.gedsys.model.exceptions.NonexistentEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author rsi
 */
public class CamposJpaController implements Serializable {

    public CamposJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Campos campos) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario creadoPor = campos.getCreadoPor();
            if (creadoPor != null) {
                creadoPor = em.getReference(creadoPor.getClass(), creadoPor.getId());
                campos.setCreadoPor(creadoPor);
            }
            Usuario modificadoPor = campos.getModificadoPor();
            if (modificadoPor != null) {
                modificadoPor = em.getReference(modificadoPor.getClass(), modificadoPor.getId());
                campos.setModificadoPor(modificadoPor);
            }
            Modulo modulo = campos.getModulo();
            if (modulo != null) {
                modulo = em.getReference(modulo.getClass(), modulo.getId());
                campos.setModulo(modulo);
            }
            em.persist(campos);
            if (creadoPor != null) {
                creadoPor.getCamposCollection().add(campos);
                creadoPor = em.merge(creadoPor);
            }
            if (modificadoPor != null) {
                modificadoPor.getCamposCollection().add(campos);
                modificadoPor = em.merge(modificadoPor);
            }
            if (modulo != null) {
                modulo.getCamposCollection().add(campos);
                modulo = em.merge(modulo);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Campos campos) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Campos persistentCampos = em.find(Campos.class, campos.getId());
            Usuario creadoPorOld = persistentCampos.getCreadoPor();
            Usuario creadoPorNew = campos.getCreadoPor();
            Usuario modificadoPorOld = persistentCampos.getModificadoPor();
            Usuario modificadoPorNew = campos.getModificadoPor();
            Modulo moduloOld = persistentCampos.getModulo();
            Modulo moduloNew = campos.getModulo();
            if (creadoPorNew != null) {
                creadoPorNew = em.getReference(creadoPorNew.getClass(), creadoPorNew.getId());
                campos.setCreadoPor(creadoPorNew);
            }
            if (modificadoPorNew != null) {
                modificadoPorNew = em.getReference(modificadoPorNew.getClass(), modificadoPorNew.getId());
                campos.setModificadoPor(modificadoPorNew);
            }
            if (moduloNew != null) {
                moduloNew = em.getReference(moduloNew.getClass(), moduloNew.getId());
                campos.setModulo(moduloNew);
            }
            campos = em.merge(campos);
            if (creadoPorOld != null && !creadoPorOld.equals(creadoPorNew)) {
                creadoPorOld.getCamposCollection().remove(campos);
                creadoPorOld = em.merge(creadoPorOld);
            }
            if (creadoPorNew != null && !creadoPorNew.equals(creadoPorOld)) {
                creadoPorNew.getCamposCollection().add(campos);
                creadoPorNew = em.merge(creadoPorNew);
            }
            if (modificadoPorOld != null && !modificadoPorOld.equals(modificadoPorNew)) {
                modificadoPorOld.getCamposCollection().remove(campos);
                modificadoPorOld = em.merge(modificadoPorOld);
            }
            if (modificadoPorNew != null && !modificadoPorNew.equals(modificadoPorOld)) {
                modificadoPorNew.getCamposCollection().add(campos);
                modificadoPorNew = em.merge(modificadoPorNew);
            }
            if (moduloOld != null && !moduloOld.equals(moduloNew)) {
                moduloOld.getCamposCollection().remove(campos);
                moduloOld = em.merge(moduloOld);
            }
            if (moduloNew != null && !moduloNew.equals(moduloOld)) {
                moduloNew.getCamposCollection().add(campos);
                moduloNew = em.merge(moduloNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = campos.getId();
                if (findCampos(id) == null) {
                    throw new NonexistentEntityException("The campos with id " + id + " no longer exists.");
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
            Campos campos;
            try {
                campos = em.getReference(Campos.class, id);
                campos.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The campos with id " + id + " no longer exists.", enfe);
            }
            Usuario creadoPor = campos.getCreadoPor();
            if (creadoPor != null) {
                creadoPor.getCamposCollection().remove(campos);
                creadoPor = em.merge(creadoPor);
            }
            Usuario modificadoPor = campos.getModificadoPor();
            if (modificadoPor != null) {
                modificadoPor.getCamposCollection().remove(campos);
                modificadoPor = em.merge(modificadoPor);
            }
            Modulo modulo = campos.getModulo();
            if (modulo != null) {
                modulo.getCamposCollection().remove(campos);
                modulo = em.merge(modulo);
            }
            em.remove(campos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Campos> findCamposEntities() {
        return findCamposEntities(true, -1, -1);
    }

    public List<Campos> findCamposEntities(int maxResults, int firstResult) {
        return findCamposEntities(false, maxResults, firstResult);
    }

    private List<Campos> findCamposEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Campos.class));
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

    public Campos findCampos(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Campos.class, id);
        } finally {
            em.close();
        }
    }

    public int getCamposCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Campos> rt = cq.from(Campos.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
