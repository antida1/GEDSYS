/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sucomunicacion.gedsys.model;

import com.sucomunicacion.gedsys.entities.Anexo;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.sucomunicacion.gedsys.entities.Documento;
import com.sucomunicacion.gedsys.model.exceptions.NonexistentEntityException;
import com.sucomunicacion.gedsys.model.exceptions.PreexistingEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Robert Alexis Mejia <rmejia@base16.co>
 */
public class AnexoJpaController implements Serializable {

    public AnexoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Anexo anexo) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Documento documento = anexo.getDocumento();
            if (documento != null) {
                documento = em.getReference(documento.getClass(), documento.getId());
                anexo.setDocumento(documento);
            }
            em.persist(anexo);
            if (documento != null) {
                documento.getAnexoList().add(anexo);
                documento = em.merge(documento);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findAnexo(anexo.getId()) != null) {
                throw new PreexistingEntityException("Anexo " + anexo + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Anexo anexo) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Anexo persistentAnexo = em.find(Anexo.class, anexo.getId());
            Documento documentoOld = persistentAnexo.getDocumento();
            Documento documentoNew = anexo.getDocumento();
            if (documentoNew != null) {
                documentoNew = em.getReference(documentoNew.getClass(), documentoNew.getId());
                anexo.setDocumento(documentoNew);
            }
            anexo = em.merge(anexo);
            if (documentoOld != null && !documentoOld.equals(documentoNew)) {
                documentoOld.getAnexoList().remove(anexo);
                documentoOld = em.merge(documentoOld);
            }
            if (documentoNew != null && !documentoNew.equals(documentoOld)) {
                documentoNew.getAnexoList().add(anexo);
                documentoNew = em.merge(documentoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = anexo.getId();
                if (findAnexo(id) == null) {
                    throw new NonexistentEntityException("The anexo with id " + id + " no longer exists.");
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
            Anexo anexo;
            try {
                anexo = em.getReference(Anexo.class, id);
                anexo.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The anexo with id " + id + " no longer exists.", enfe);
            }
            Documento documento = anexo.getDocumento();
            if (documento != null) {
                documento.getAnexoList().remove(anexo);
                documento = em.merge(documento);
            }
            em.remove(anexo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Anexo> findAnexoEntities() {
        return findAnexoEntities(true, -1, -1);
    }

    public List<Anexo> findAnexoEntities(int maxResults, int firstResult) {
        return findAnexoEntities(false, maxResults, firstResult);
    }

    private List<Anexo> findAnexoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Anexo.class));
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

    public Anexo findAnexo(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Anexo.class, id);
        } finally {
            em.close();
        }
    }

    public int getAnexoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Anexo> rt = cq.from(Anexo.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
