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
import com.sucomunicacion.gedsys.entities.Usuario;
import com.sucomunicacion.gedsys.model.exceptions.NonexistentEntityException;
import com.sucomunicacion.gedsys.model.exceptions.PreexistingEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author rober
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
            Documento anexoRel = anexo.getAnexo();
            if (anexoRel != null) {
                anexoRel = em.getReference(anexoRel.getClass(), anexoRel.getId());
                anexo.setAnexo(anexoRel);
            }
            Usuario creadoPor = anexo.getCreadoPor();
            if (creadoPor != null) {
                creadoPor = em.getReference(creadoPor.getClass(), creadoPor.getId());
                anexo.setCreadoPor(creadoPor);
            }
            Usuario modificadorPor = anexo.getModificadorPor();
            if (modificadorPor != null) {
                modificadorPor = em.getReference(modificadorPor.getClass(), modificadorPor.getId());
                anexo.setModificadorPor(modificadorPor);
            }
            em.persist(anexo);
            if (documento != null) {
                documento.getAnexoCollection().add(anexo);
                documento = em.merge(documento);
            }
            if (anexoRel != null) {
                anexoRel.getAnexoCollection().add(anexo);
                anexoRel = em.merge(anexoRel);
            }
            if (creadoPor != null) {
                creadoPor.getAnexoCollection().add(anexo);
                creadoPor = em.merge(creadoPor);
            }
            if (modificadorPor != null) {
                modificadorPor.getAnexoCollection().add(anexo);
                modificadorPor = em.merge(modificadorPor);
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
            Documento anexoRelOld = persistentAnexo.getAnexo();
            Documento anexoRelNew = anexo.getAnexo();
            Usuario creadoPorOld = persistentAnexo.getCreadoPor();
            Usuario creadoPorNew = anexo.getCreadoPor();
            Usuario modificadorPorOld = persistentAnexo.getModificadorPor();
            Usuario modificadorPorNew = anexo.getModificadorPor();
            if (documentoNew != null) {
                documentoNew = em.getReference(documentoNew.getClass(), documentoNew.getId());
                anexo.setDocumento(documentoNew);
            }
            if (anexoRelNew != null) {
                anexoRelNew = em.getReference(anexoRelNew.getClass(), anexoRelNew.getId());
                anexo.setAnexo(anexoRelNew);
            }
            if (creadoPorNew != null) {
                creadoPorNew = em.getReference(creadoPorNew.getClass(), creadoPorNew.getId());
                anexo.setCreadoPor(creadoPorNew);
            }
            if (modificadorPorNew != null) {
                modificadorPorNew = em.getReference(modificadorPorNew.getClass(), modificadorPorNew.getId());
                anexo.setModificadorPor(modificadorPorNew);
            }
            anexo = em.merge(anexo);
            if (documentoOld != null && !documentoOld.equals(documentoNew)) {
                documentoOld.getAnexoCollection().remove(anexo);
                documentoOld = em.merge(documentoOld);
            }
            if (documentoNew != null && !documentoNew.equals(documentoOld)) {
                documentoNew.getAnexoCollection().add(anexo);
                documentoNew = em.merge(documentoNew);
            }
            if (anexoRelOld != null && !anexoRelOld.equals(anexoRelNew)) {
                anexoRelOld.getAnexoCollection().remove(anexo);
                anexoRelOld = em.merge(anexoRelOld);
            }
            if (anexoRelNew != null && !anexoRelNew.equals(anexoRelOld)) {
                anexoRelNew.getAnexoCollection().add(anexo);
                anexoRelNew = em.merge(anexoRelNew);
            }
            if (creadoPorOld != null && !creadoPorOld.equals(creadoPorNew)) {
                creadoPorOld.getAnexoCollection().remove(anexo);
                creadoPorOld = em.merge(creadoPorOld);
            }
            if (creadoPorNew != null && !creadoPorNew.equals(creadoPorOld)) {
                creadoPorNew.getAnexoCollection().add(anexo);
                creadoPorNew = em.merge(creadoPorNew);
            }
            if (modificadorPorOld != null && !modificadorPorOld.equals(modificadorPorNew)) {
                modificadorPorOld.getAnexoCollection().remove(anexo);
                modificadorPorOld = em.merge(modificadorPorOld);
            }
            if (modificadorPorNew != null && !modificadorPorNew.equals(modificadorPorOld)) {
                modificadorPorNew.getAnexoCollection().add(anexo);
                modificadorPorNew = em.merge(modificadorPorNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = anexo.getId();
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

    public void destroy(Long id) throws NonexistentEntityException {
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
                documento.getAnexoCollection().remove(anexo);
                documento = em.merge(documento);
            }
            Documento anexoRel = anexo.getAnexo();
            if (anexoRel != null) {
                anexoRel.getAnexoCollection().remove(anexo);
                anexoRel = em.merge(anexoRel);
            }
            Usuario creadoPor = anexo.getCreadoPor();
            if (creadoPor != null) {
                creadoPor.getAnexoCollection().remove(anexo);
                creadoPor = em.merge(creadoPor);
            }
            Usuario modificadorPor = anexo.getModificadorPor();
            if (modificadorPor != null) {
                modificadorPor.getAnexoCollection().remove(anexo);
                modificadorPor = em.merge(modificadorPor);
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

    public Anexo findAnexo(Long id) {
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
