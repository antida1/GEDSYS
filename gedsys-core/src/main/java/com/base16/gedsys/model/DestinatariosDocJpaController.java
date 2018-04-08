/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base16.gedsys.model;

import com.base16.gedsys.entities.DestinatariosDoc;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.base16.gedsys.entities.Documento;
import com.base16.gedsys.entities.Usuario;
import com.base16.gedsys.model.exceptions.NonexistentEntityException;
import com.base16.gedsys.model.exceptions.PreexistingEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author rober
 */
public class DestinatariosDocJpaController implements Serializable {

    public DestinatariosDocJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(DestinatariosDoc destinatariosDoc) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Documento documentoId = destinatariosDoc.getDocumentoId();
            if (documentoId != null) {
                documentoId = em.getReference(documentoId.getClass(), documentoId.getId());
                destinatariosDoc.setDocumentoId(documentoId);
            }
            Usuario creadoPor = destinatariosDoc.getCreadoPor();
            if (creadoPor != null) {
                creadoPor = em.getReference(creadoPor.getClass(), creadoPor.getId());
                destinatariosDoc.setCreadoPor(creadoPor);
            }
            Usuario modificadoPor = destinatariosDoc.getModificadoPor();
            if (modificadoPor != null) {
                modificadoPor = em.getReference(modificadoPor.getClass(), modificadoPor.getId());
                destinatariosDoc.setModificadoPor(modificadoPor);
            }
            Usuario destinatarioId = destinatariosDoc.getDestinatarioId();
            if (destinatarioId != null) {
                destinatarioId = em.getReference(destinatarioId.getClass(), destinatarioId.getId());
                destinatariosDoc.setDestinatarioId(destinatarioId);
            }
            em.persist(destinatariosDoc);
            if (documentoId != null) {
                documentoId.getDestinatariosDocCollection().add(destinatariosDoc);
                documentoId = em.merge(documentoId);
            }
            if (creadoPor != null) {
                creadoPor.getDestinatariosDocCollection().add(destinatariosDoc);
                creadoPor = em.merge(creadoPor);
            }
            if (modificadoPor != null) {
                modificadoPor.getDestinatariosDocCollection().add(destinatariosDoc);
                modificadoPor = em.merge(modificadoPor);
            }
            if (destinatarioId != null) {
                destinatarioId.getDestinatariosDocCollection().add(destinatariosDoc);
                destinatarioId = em.merge(destinatarioId);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findDestinatariosDoc(destinatariosDoc.getId()) != null) {
                throw new PreexistingEntityException("DestinatariosDoc " + destinatariosDoc + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(DestinatariosDoc destinatariosDoc) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            DestinatariosDoc persistentDestinatariosDoc = em.find(DestinatariosDoc.class, destinatariosDoc.getId());
            Documento documentoIdOld = persistentDestinatariosDoc.getDocumentoId();
            Documento documentoIdNew = destinatariosDoc.getDocumentoId();
            Usuario creadoPorOld = persistentDestinatariosDoc.getCreadoPor();
            Usuario creadoPorNew = destinatariosDoc.getCreadoPor();
            Usuario modificadoPorOld = persistentDestinatariosDoc.getModificadoPor();
            Usuario modificadoPorNew = destinatariosDoc.getModificadoPor();
            Usuario destinatarioIdOld = persistentDestinatariosDoc.getDestinatarioId();
            Usuario destinatarioIdNew = destinatariosDoc.getDestinatarioId();
            if (documentoIdNew != null) {
                documentoIdNew = em.getReference(documentoIdNew.getClass(), documentoIdNew.getId());
                destinatariosDoc.setDocumentoId(documentoIdNew);
            }
            if (creadoPorNew != null) {
                creadoPorNew = em.getReference(creadoPorNew.getClass(), creadoPorNew.getId());
                destinatariosDoc.setCreadoPor(creadoPorNew);
            }
            if (modificadoPorNew != null) {
                modificadoPorNew = em.getReference(modificadoPorNew.getClass(), modificadoPorNew.getId());
                destinatariosDoc.setModificadoPor(modificadoPorNew);
            }
            if (destinatarioIdNew != null) {
                destinatarioIdNew = em.getReference(destinatarioIdNew.getClass(), destinatarioIdNew.getId());
                destinatariosDoc.setDestinatarioId(destinatarioIdNew);
            }
            destinatariosDoc = em.merge(destinatariosDoc);
            if (documentoIdOld != null && !documentoIdOld.equals(documentoIdNew)) {
                documentoIdOld.getDestinatariosDocCollection().remove(destinatariosDoc);
                documentoIdOld = em.merge(documentoIdOld);
            }
            if (documentoIdNew != null && !documentoIdNew.equals(documentoIdOld)) {
                documentoIdNew.getDestinatariosDocCollection().add(destinatariosDoc);
                documentoIdNew = em.merge(documentoIdNew);
            }
            if (creadoPorOld != null && !creadoPorOld.equals(creadoPorNew)) {
                creadoPorOld.getDestinatariosDocCollection().remove(destinatariosDoc);
                creadoPorOld = em.merge(creadoPorOld);
            }
            if (creadoPorNew != null && !creadoPorNew.equals(creadoPorOld)) {
                creadoPorNew.getDestinatariosDocCollection().add(destinatariosDoc);
                creadoPorNew = em.merge(creadoPorNew);
            }
            if (modificadoPorOld != null && !modificadoPorOld.equals(modificadoPorNew)) {
                modificadoPorOld.getDestinatariosDocCollection().remove(destinatariosDoc);
                modificadoPorOld = em.merge(modificadoPorOld);
            }
            if (modificadoPorNew != null && !modificadoPorNew.equals(modificadoPorOld)) {
                modificadoPorNew.getDestinatariosDocCollection().add(destinatariosDoc);
                modificadoPorNew = em.merge(modificadoPorNew);
            }
            if (destinatarioIdOld != null && !destinatarioIdOld.equals(destinatarioIdNew)) {
                destinatarioIdOld.getDestinatariosDocCollection().remove(destinatariosDoc);
                destinatarioIdOld = em.merge(destinatarioIdOld);
            }
            if (destinatarioIdNew != null && !destinatarioIdNew.equals(destinatarioIdOld)) {
                destinatarioIdNew.getDestinatariosDocCollection().add(destinatariosDoc);
                destinatarioIdNew = em.merge(destinatarioIdNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = destinatariosDoc.getId();
                if (findDestinatariosDoc(id) == null) {
                    throw new NonexistentEntityException("The destinatariosDoc with id " + id + " no longer exists.");
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
            DestinatariosDoc destinatariosDoc;
            try {
                destinatariosDoc = em.getReference(DestinatariosDoc.class, id);
                destinatariosDoc.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The destinatariosDoc with id " + id + " no longer exists.", enfe);
            }
            Documento documentoId = destinatariosDoc.getDocumentoId();
            if (documentoId != null) {
                documentoId.getDestinatariosDocCollection().remove(destinatariosDoc);
                documentoId = em.merge(documentoId);
            }
            Usuario creadoPor = destinatariosDoc.getCreadoPor();
            if (creadoPor != null) {
                creadoPor.getDestinatariosDocCollection().remove(destinatariosDoc);
                creadoPor = em.merge(creadoPor);
            }
            Usuario modificadoPor = destinatariosDoc.getModificadoPor();
            if (modificadoPor != null) {
                modificadoPor.getDestinatariosDocCollection().remove(destinatariosDoc);
                modificadoPor = em.merge(modificadoPor);
            }
            Usuario destinatarioId = destinatariosDoc.getDestinatarioId();
            if (destinatarioId != null) {
                destinatarioId.getDestinatariosDocCollection().remove(destinatariosDoc);
                destinatarioId = em.merge(destinatarioId);
            }
            em.remove(destinatariosDoc);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<DestinatariosDoc> findDestinatariosDocEntities() {
        return findDestinatariosDocEntities(true, -1, -1);
    }

    public List<DestinatariosDoc> findDestinatariosDocEntities(int maxResults, int firstResult) {
        return findDestinatariosDocEntities(false, maxResults, firstResult);
    }

    private List<DestinatariosDoc> findDestinatariosDocEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(DestinatariosDoc.class));
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

    public DestinatariosDoc findDestinatariosDoc(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(DestinatariosDoc.class, id);
        } finally {
            em.close();
        }
    }

    public int getDestinatariosDocCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<DestinatariosDoc> rt = cq.from(DestinatariosDoc.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
