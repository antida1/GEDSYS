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
import com.base16.gedsys.entities.Usuario;
import com.base16.gedsys.entities.Documento;
import com.base16.gedsys.entities.Mediorecepcion;
import com.base16.gedsys.model.exceptions.NonexistentEntityException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author rober
 */
public class MediorecepcionJpaController implements Serializable {

    public MediorecepcionJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Mediorecepcion mediorecepcion) {
        if (mediorecepcion.getDocumentoList() == null) {
            mediorecepcion.setDocumentoList(new ArrayList<Documento>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario creadoPor = mediorecepcion.getCreadoPor();
            if (creadoPor != null) {
                creadoPor = em.getReference(creadoPor.getClass(), creadoPor.getId());
                mediorecepcion.setCreadoPor(creadoPor);
            }
            Usuario modificadoPor = mediorecepcion.getModificadoPor();
            if (modificadoPor != null) {
                modificadoPor = em.getReference(modificadoPor.getClass(), modificadoPor.getId());
                mediorecepcion.setModificadoPor(modificadoPor);
            }
            List<Documento> attachedDocumentoList = new ArrayList<Documento>();
            for (Documento documentoListDocumentoToAttach : mediorecepcion.getDocumentoList()) {
                documentoListDocumentoToAttach = em.getReference(documentoListDocumentoToAttach.getClass(), documentoListDocumentoToAttach.getId());
                attachedDocumentoList.add(documentoListDocumentoToAttach);
            }
            mediorecepcion.setDocumentoList(attachedDocumentoList);
            em.persist(mediorecepcion);
            if (creadoPor != null) {
                creadoPor.getMediorecepcionList().add(mediorecepcion);
                creadoPor = em.merge(creadoPor);
            }
            if (modificadoPor != null) {
                modificadoPor.getMediorecepcionList().add(mediorecepcion);
                modificadoPor = em.merge(modificadoPor);
            }
            for (Documento documentoListDocumento : mediorecepcion.getDocumentoList()) {
                Mediorecepcion oldMedioEnvioOfDocumentoListDocumento = documentoListDocumento.getMedioEnvio();
                documentoListDocumento.setMedioEnvio(mediorecepcion);
                documentoListDocumento = em.merge(documentoListDocumento);
                if (oldMedioEnvioOfDocumentoListDocumento != null) {
                    oldMedioEnvioOfDocumentoListDocumento.getDocumentoList().remove(documentoListDocumento);
                    oldMedioEnvioOfDocumentoListDocumento = em.merge(oldMedioEnvioOfDocumentoListDocumento);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Mediorecepcion mediorecepcion) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Mediorecepcion persistentMediorecepcion = em.find(Mediorecepcion.class, mediorecepcion.getId());
            Usuario creadoPorOld = persistentMediorecepcion.getCreadoPor();
            Usuario creadoPorNew = mediorecepcion.getCreadoPor();
            Usuario modificadoPorOld = persistentMediorecepcion.getModificadoPor();
            Usuario modificadoPorNew = mediorecepcion.getModificadoPor();
            List<Documento> documentoListOld = persistentMediorecepcion.getDocumentoList();
            List<Documento> documentoListNew = mediorecepcion.getDocumentoList();
            if (creadoPorNew != null) {
                creadoPorNew = em.getReference(creadoPorNew.getClass(), creadoPorNew.getId());
                mediorecepcion.setCreadoPor(creadoPorNew);
            }
            if (modificadoPorNew != null) {
                modificadoPorNew = em.getReference(modificadoPorNew.getClass(), modificadoPorNew.getId());
                mediorecepcion.setModificadoPor(modificadoPorNew);
            }
            List<Documento> attachedDocumentoListNew = new ArrayList<Documento>();
            for (Documento documentoListNewDocumentoToAttach : documentoListNew) {
                documentoListNewDocumentoToAttach = em.getReference(documentoListNewDocumentoToAttach.getClass(), documentoListNewDocumentoToAttach.getId());
                attachedDocumentoListNew.add(documentoListNewDocumentoToAttach);
            }
            documentoListNew = attachedDocumentoListNew;
            mediorecepcion.setDocumentoList(documentoListNew);
            mediorecepcion = em.merge(mediorecepcion);
            if (creadoPorOld != null && !creadoPorOld.equals(creadoPorNew)) {
                creadoPorOld.getMediorecepcionList().remove(mediorecepcion);
                creadoPorOld = em.merge(creadoPorOld);
            }
            if (creadoPorNew != null && !creadoPorNew.equals(creadoPorOld)) {
                creadoPorNew.getMediorecepcionList().add(mediorecepcion);
                creadoPorNew = em.merge(creadoPorNew);
            }
            if (modificadoPorOld != null && !modificadoPorOld.equals(modificadoPorNew)) {
                modificadoPorOld.getMediorecepcionList().remove(mediorecepcion);
                modificadoPorOld = em.merge(modificadoPorOld);
            }
            if (modificadoPorNew != null && !modificadoPorNew.equals(modificadoPorOld)) {
                modificadoPorNew.getMediorecepcionList().add(mediorecepcion);
                modificadoPorNew = em.merge(modificadoPorNew);
            }
            for (Documento documentoListOldDocumento : documentoListOld) {
                if (!documentoListNew.contains(documentoListOldDocumento)) {
                    documentoListOldDocumento.setMedioEnvio(null);
                    documentoListOldDocumento = em.merge(documentoListOldDocumento);
                }
            }
            for (Documento documentoListNewDocumento : documentoListNew) {
                if (!documentoListOld.contains(documentoListNewDocumento)) {
                    Mediorecepcion oldMedioEnvioOfDocumentoListNewDocumento = documentoListNewDocumento.getMedioEnvio();
                    documentoListNewDocumento.setMedioEnvio(mediorecepcion);
                    documentoListNewDocumento = em.merge(documentoListNewDocumento);
                    if (oldMedioEnvioOfDocumentoListNewDocumento != null && !oldMedioEnvioOfDocumentoListNewDocumento.equals(mediorecepcion)) {
                        oldMedioEnvioOfDocumentoListNewDocumento.getDocumentoList().remove(documentoListNewDocumento);
                        oldMedioEnvioOfDocumentoListNewDocumento = em.merge(oldMedioEnvioOfDocumentoListNewDocumento);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = mediorecepcion.getId();
                if (findMediorecepcion(id) == null) {
                    throw new NonexistentEntityException("The mediorecepcion with id " + id + " no longer exists.");
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
            Mediorecepcion mediorecepcion;
            try {
                mediorecepcion = em.getReference(Mediorecepcion.class, id);
                mediorecepcion.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The mediorecepcion with id " + id + " no longer exists.", enfe);
            }
            Usuario creadoPor = mediorecepcion.getCreadoPor();
            if (creadoPor != null) {
                creadoPor.getMediorecepcionList().remove(mediorecepcion);
                creadoPor = em.merge(creadoPor);
            }
            Usuario modificadoPor = mediorecepcion.getModificadoPor();
            if (modificadoPor != null) {
                modificadoPor.getMediorecepcionList().remove(mediorecepcion);
                modificadoPor = em.merge(modificadoPor);
            }
            List<Documento> documentoList = mediorecepcion.getDocumentoList();
            for (Documento documentoListDocumento : documentoList) {
                documentoListDocumento.setMedioEnvio(null);
                documentoListDocumento = em.merge(documentoListDocumento);
            }
            em.remove(mediorecepcion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Mediorecepcion> findMediorecepcionEntities() {
        return findMediorecepcionEntities(true, -1, -1);
    }

    public List<Mediorecepcion> findMediorecepcionEntities(int maxResults, int firstResult) {
        return findMediorecepcionEntities(false, maxResults, firstResult);
    }

    private List<Mediorecepcion> findMediorecepcionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Mediorecepcion.class));
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

    public Mediorecepcion findMediorecepcion(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Mediorecepcion.class, id);
        } finally {
            em.close();
        }
    }

    public int getMediorecepcionCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Mediorecepcion> rt = cq.from(Mediorecepcion.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
