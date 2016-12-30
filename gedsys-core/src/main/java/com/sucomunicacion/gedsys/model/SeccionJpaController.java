/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sucomunicacion.gedsys.model;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.sucomunicacion.gedsys.entities.Documento;
import com.sucomunicacion.gedsys.entities.Seccion;
import java.util.ArrayList;
import java.util.List;
import com.sucomunicacion.gedsys.entities.SubSeccion;
import com.sucomunicacion.gedsys.model.exceptions.NonexistentEntityException;
import com.sucomunicacion.gedsys.model.exceptions.PreexistingEntityException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Robert Alexis Mejia <rmejia@base16.co>
 */
public class SeccionJpaController implements Serializable {

    public SeccionJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Seccion seccion) throws PreexistingEntityException, Exception {
        if (seccion.getDocumentoList() == null) {
            seccion.setDocumentoList(new ArrayList<Documento>());
        }
        if (seccion.getSubSeccionList() == null) {
            seccion.setSubSeccionList(new ArrayList<SubSeccion>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Documento> attachedDocumentoList = new ArrayList<Documento>();
            for (Documento documentoListDocumentoToAttach : seccion.getDocumentoList()) {
                documentoListDocumentoToAttach = em.getReference(documentoListDocumentoToAttach.getClass(), documentoListDocumentoToAttach.getId());
                attachedDocumentoList.add(documentoListDocumentoToAttach);
            }
            seccion.setDocumentoList(attachedDocumentoList);
            List<SubSeccion> attachedSubSeccionList = new ArrayList<SubSeccion>();
            for (SubSeccion subSeccionListSubSeccionToAttach : seccion.getSubSeccionList()) {
                subSeccionListSubSeccionToAttach = em.getReference(subSeccionListSubSeccionToAttach.getClass(), subSeccionListSubSeccionToAttach.getId());
                attachedSubSeccionList.add(subSeccionListSubSeccionToAttach);
            }
            seccion.setSubSeccionList(attachedSubSeccionList);
            em.persist(seccion);
            for (Documento documentoListDocumento : seccion.getDocumentoList()) {
                Seccion oldSessionOfDocumentoListDocumento = documentoListDocumento.getSession();
                documentoListDocumento.setSession(seccion);
                documentoListDocumento = em.merge(documentoListDocumento);
                if (oldSessionOfDocumentoListDocumento != null) {
                    oldSessionOfDocumentoListDocumento.getDocumentoList().remove(documentoListDocumento);
                    oldSessionOfDocumentoListDocumento = em.merge(oldSessionOfDocumentoListDocumento);
                }
            }
            for (SubSeccion subSeccionListSubSeccion : seccion.getSubSeccionList()) {
                Seccion oldSeccionOfSubSeccionListSubSeccion = subSeccionListSubSeccion.getSeccion();
                subSeccionListSubSeccion.setSeccion(seccion);
                subSeccionListSubSeccion = em.merge(subSeccionListSubSeccion);
                if (oldSeccionOfSubSeccionListSubSeccion != null) {
                    oldSeccionOfSubSeccionListSubSeccion.getSubSeccionList().remove(subSeccionListSubSeccion);
                    oldSeccionOfSubSeccionListSubSeccion = em.merge(oldSeccionOfSubSeccionListSubSeccion);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findSeccion(seccion.getId()) != null) {
                throw new PreexistingEntityException("Seccion " + seccion + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Seccion seccion) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Seccion persistentSeccion = em.find(Seccion.class, seccion.getId());
            List<Documento> documentoListOld = persistentSeccion.getDocumentoList();
            List<Documento> documentoListNew = seccion.getDocumentoList();
            List<SubSeccion> subSeccionListOld = persistentSeccion.getSubSeccionList();
            List<SubSeccion> subSeccionListNew = seccion.getSubSeccionList();
            List<Documento> attachedDocumentoListNew = new ArrayList<Documento>();
            for (Documento documentoListNewDocumentoToAttach : documentoListNew) {
                documentoListNewDocumentoToAttach = em.getReference(documentoListNewDocumentoToAttach.getClass(), documentoListNewDocumentoToAttach.getId());
                attachedDocumentoListNew.add(documentoListNewDocumentoToAttach);
            }
            documentoListNew = attachedDocumentoListNew;
            seccion.setDocumentoList(documentoListNew);
            List<SubSeccion> attachedSubSeccionListNew = new ArrayList<SubSeccion>();
            for (SubSeccion subSeccionListNewSubSeccionToAttach : subSeccionListNew) {
                subSeccionListNewSubSeccionToAttach = em.getReference(subSeccionListNewSubSeccionToAttach.getClass(), subSeccionListNewSubSeccionToAttach.getId());
                attachedSubSeccionListNew.add(subSeccionListNewSubSeccionToAttach);
            }
            subSeccionListNew = attachedSubSeccionListNew;
            seccion.setSubSeccionList(subSeccionListNew);
            seccion = em.merge(seccion);
            for (Documento documentoListOldDocumento : documentoListOld) {
                if (!documentoListNew.contains(documentoListOldDocumento)) {
                    documentoListOldDocumento.setSession(null);
                    documentoListOldDocumento = em.merge(documentoListOldDocumento);
                }
            }
            for (Documento documentoListNewDocumento : documentoListNew) {
                if (!documentoListOld.contains(documentoListNewDocumento)) {
                    Seccion oldSessionOfDocumentoListNewDocumento = documentoListNewDocumento.getSession();
                    documentoListNewDocumento.setSession(seccion);
                    documentoListNewDocumento = em.merge(documentoListNewDocumento);
                    if (oldSessionOfDocumentoListNewDocumento != null && !oldSessionOfDocumentoListNewDocumento.equals(seccion)) {
                        oldSessionOfDocumentoListNewDocumento.getDocumentoList().remove(documentoListNewDocumento);
                        oldSessionOfDocumentoListNewDocumento = em.merge(oldSessionOfDocumentoListNewDocumento);
                    }
                }
            }
            for (SubSeccion subSeccionListOldSubSeccion : subSeccionListOld) {
                if (!subSeccionListNew.contains(subSeccionListOldSubSeccion)) {
                    subSeccionListOldSubSeccion.setSeccion(null);
                    subSeccionListOldSubSeccion = em.merge(subSeccionListOldSubSeccion);
                }
            }
            for (SubSeccion subSeccionListNewSubSeccion : subSeccionListNew) {
                if (!subSeccionListOld.contains(subSeccionListNewSubSeccion)) {
                    Seccion oldSeccionOfSubSeccionListNewSubSeccion = subSeccionListNewSubSeccion.getSeccion();
                    subSeccionListNewSubSeccion.setSeccion(seccion);
                    subSeccionListNewSubSeccion = em.merge(subSeccionListNewSubSeccion);
                    if (oldSeccionOfSubSeccionListNewSubSeccion != null && !oldSeccionOfSubSeccionListNewSubSeccion.equals(seccion)) {
                        oldSeccionOfSubSeccionListNewSubSeccion.getSubSeccionList().remove(subSeccionListNewSubSeccion);
                        oldSeccionOfSubSeccionListNewSubSeccion = em.merge(oldSeccionOfSubSeccionListNewSubSeccion);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = seccion.getId();
                if (findSeccion(id) == null) {
                    throw new NonexistentEntityException("The seccion with id " + id + " no longer exists.");
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
            Seccion seccion;
            try {
                seccion = em.getReference(Seccion.class, id);
                seccion.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The seccion with id " + id + " no longer exists.", enfe);
            }
            List<Documento> documentoList = seccion.getDocumentoList();
            for (Documento documentoListDocumento : documentoList) {
                documentoListDocumento.setSession(null);
                documentoListDocumento = em.merge(documentoListDocumento);
            }
            List<SubSeccion> subSeccionList = seccion.getSubSeccionList();
            for (SubSeccion subSeccionListSubSeccion : subSeccionList) {
                subSeccionListSubSeccion.setSeccion(null);
                subSeccionListSubSeccion = em.merge(subSeccionListSubSeccion);
            }
            em.remove(seccion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Seccion> findSeccionEntities() {
        return findSeccionEntities(true, -1, -1);
    }

    public List<Seccion> findSeccionEntities(int maxResults, int firstResult) {
        return findSeccionEntities(false, maxResults, firstResult);
    }

    private List<Seccion> findSeccionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Seccion.class));
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

    public Seccion findSeccion(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Seccion.class, id);
        } finally {
            em.close();
        }
    }

    public int getSeccionCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Seccion> rt = cq.from(Seccion.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
