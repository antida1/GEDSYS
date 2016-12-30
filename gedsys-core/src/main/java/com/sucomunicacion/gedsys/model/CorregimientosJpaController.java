/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sucomunicacion.gedsys.model;

import com.sucomunicacion.gedsys.entities.Corregimientos;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.sucomunicacion.gedsys.entities.Municipios;
import com.sucomunicacion.gedsys.model.exceptions.NonexistentEntityException;
import com.sucomunicacion.gedsys.model.exceptions.PreexistingEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Robert Alexis Mejia <rmejia@base16.co>
 */
public class CorregimientosJpaController implements Serializable {

    public CorregimientosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Corregimientos corregimientos) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Municipios municipio = corregimientos.getMunicipio();
            if (municipio != null) {
                municipio = em.getReference(municipio.getClass(), municipio.getId());
                corregimientos.setMunicipio(municipio);
            }
            em.persist(corregimientos);
            if (municipio != null) {
                municipio.getCorregimientosList().add(corregimientos);
                municipio = em.merge(municipio);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findCorregimientos(corregimientos.getId()) != null) {
                throw new PreexistingEntityException("Corregimientos " + corregimientos + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Corregimientos corregimientos) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Corregimientos persistentCorregimientos = em.find(Corregimientos.class, corregimientos.getId());
            Municipios municipioOld = persistentCorregimientos.getMunicipio();
            Municipios municipioNew = corregimientos.getMunicipio();
            if (municipioNew != null) {
                municipioNew = em.getReference(municipioNew.getClass(), municipioNew.getId());
                corregimientos.setMunicipio(municipioNew);
            }
            corregimientos = em.merge(corregimientos);
            if (municipioOld != null && !municipioOld.equals(municipioNew)) {
                municipioOld.getCorregimientosList().remove(corregimientos);
                municipioOld = em.merge(municipioOld);
            }
            if (municipioNew != null && !municipioNew.equals(municipioOld)) {
                municipioNew.getCorregimientosList().add(corregimientos);
                municipioNew = em.merge(municipioNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = corregimientos.getId();
                if (findCorregimientos(id) == null) {
                    throw new NonexistentEntityException("The corregimientos with id " + id + " no longer exists.");
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
            Corregimientos corregimientos;
            try {
                corregimientos = em.getReference(Corregimientos.class, id);
                corregimientos.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The corregimientos with id " + id + " no longer exists.", enfe);
            }
            Municipios municipio = corregimientos.getMunicipio();
            if (municipio != null) {
                municipio.getCorregimientosList().remove(corregimientos);
                municipio = em.merge(municipio);
            }
            em.remove(corregimientos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Corregimientos> findCorregimientosEntities() {
        return findCorregimientosEntities(true, -1, -1);
    }

    public List<Corregimientos> findCorregimientosEntities(int maxResults, int firstResult) {
        return findCorregimientosEntities(false, maxResults, firstResult);
    }

    private List<Corregimientos> findCorregimientosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Corregimientos.class));
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

    public Corregimientos findCorregimientos(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Corregimientos.class, id);
        } finally {
            em.close();
        }
    }

    public int getCorregimientosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Corregimientos> rt = cq.from(Corregimientos.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
