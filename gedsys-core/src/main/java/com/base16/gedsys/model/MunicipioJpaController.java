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
import com.base16.gedsys.entities.Departamento;
import com.base16.gedsys.entities.Documento;
import java.util.ArrayList;
import java.util.Collection;
import com.base16.gedsys.entities.Corregimiento;
import com.base16.gedsys.entities.Municipio;
import com.base16.gedsys.model.exceptions.NonexistentEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author rober
 */
public class MunicipioJpaController implements Serializable {

    public MunicipioJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Municipio municipio) {
        if (municipio.getDocumentoCollection() == null) {
            municipio.setDocumentoCollection(new ArrayList<Documento>());
        }
        if (municipio.getCorregimientoCollection() == null) {
            municipio.setCorregimientoCollection(new ArrayList<Corregimiento>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario creadoPor = municipio.getCreadoPor();
            if (creadoPor != null) {
                creadoPor = em.getReference(creadoPor.getClass(), creadoPor.getId());
                municipio.setCreadoPor(creadoPor);
            }
            Departamento departamento = municipio.getDepartamento();
            if (departamento != null) {
                departamento = em.getReference(departamento.getClass(), departamento.getId());
                municipio.setDepartamento(departamento);
            }
            Usuario modificadoPor = municipio.getModificadoPor();
            if (modificadoPor != null) {
                modificadoPor = em.getReference(modificadoPor.getClass(), modificadoPor.getId());
                municipio.setModificadoPor(modificadoPor);
            }
            Collection<Documento> attachedDocumentoCollection = new ArrayList<Documento>();
            for (Documento documentoCollectionDocumentoToAttach : municipio.getDocumentoCollection()) {
                documentoCollectionDocumentoToAttach = em.getReference(documentoCollectionDocumentoToAttach.getClass(), documentoCollectionDocumentoToAttach.getId());
                attachedDocumentoCollection.add(documentoCollectionDocumentoToAttach);
            }
            municipio.setDocumentoCollection(attachedDocumentoCollection);
            Collection<Corregimiento> attachedCorregimientoCollection = new ArrayList<Corregimiento>();
            for (Corregimiento corregimientoCollectionCorregimientoToAttach : municipio.getCorregimientoCollection()) {
                corregimientoCollectionCorregimientoToAttach = em.getReference(corregimientoCollectionCorregimientoToAttach.getClass(), corregimientoCollectionCorregimientoToAttach.getId());
                attachedCorregimientoCollection.add(corregimientoCollectionCorregimientoToAttach);
            }
            municipio.setCorregimientoCollection(attachedCorregimientoCollection);
            em.persist(municipio);
            if (creadoPor != null) {
                creadoPor.getMunicipioCollection().add(municipio);
                creadoPor = em.merge(creadoPor);
            }
            if (departamento != null) {
                departamento.getMunicipioCollection().add(municipio);
                departamento = em.merge(departamento);
            }
            if (modificadoPor != null) {
                modificadoPor.getMunicipioCollection().add(municipio);
                modificadoPor = em.merge(modificadoPor);
            }
            for (Documento documentoCollectionDocumento : municipio.getDocumentoCollection()) {
                Municipio oldMunicipioOfDocumentoCollectionDocumento = documentoCollectionDocumento.getMunicipio();
                documentoCollectionDocumento.setMunicipio(municipio);
                documentoCollectionDocumento = em.merge(documentoCollectionDocumento);
                if (oldMunicipioOfDocumentoCollectionDocumento != null) {
                    oldMunicipioOfDocumentoCollectionDocumento.getDocumentoCollection().remove(documentoCollectionDocumento);
                    oldMunicipioOfDocumentoCollectionDocumento = em.merge(oldMunicipioOfDocumentoCollectionDocumento);
                }
            }
            for (Corregimiento corregimientoCollectionCorregimiento : municipio.getCorregimientoCollection()) {
                Municipio oldMunicipioOfCorregimientoCollectionCorregimiento = corregimientoCollectionCorregimiento.getMunicipio();
                corregimientoCollectionCorregimiento.setMunicipio(municipio);
                corregimientoCollectionCorregimiento = em.merge(corregimientoCollectionCorregimiento);
                if (oldMunicipioOfCorregimientoCollectionCorregimiento != null) {
                    oldMunicipioOfCorregimientoCollectionCorregimiento.getCorregimientoCollection().remove(corregimientoCollectionCorregimiento);
                    oldMunicipioOfCorregimientoCollectionCorregimiento = em.merge(oldMunicipioOfCorregimientoCollectionCorregimiento);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Municipio municipio) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Municipio persistentMunicipio = em.find(Municipio.class, municipio.getId());
            Usuario creadoPorOld = persistentMunicipio.getCreadoPor();
            Usuario creadoPorNew = municipio.getCreadoPor();
            Departamento departamentoOld = persistentMunicipio.getDepartamento();
            Departamento departamentoNew = municipio.getDepartamento();
            Usuario modificadoPorOld = persistentMunicipio.getModificadoPor();
            Usuario modificadoPorNew = municipio.getModificadoPor();
            Collection<Documento> documentoCollectionOld = persistentMunicipio.getDocumentoCollection();
            Collection<Documento> documentoCollectionNew = municipio.getDocumentoCollection();
            Collection<Corregimiento> corregimientoCollectionOld = persistentMunicipio.getCorregimientoCollection();
            Collection<Corregimiento> corregimientoCollectionNew = municipio.getCorregimientoCollection();
            if (creadoPorNew != null) {
                creadoPorNew = em.getReference(creadoPorNew.getClass(), creadoPorNew.getId());
                municipio.setCreadoPor(creadoPorNew);
            }
            if (departamentoNew != null) {
                departamentoNew = em.getReference(departamentoNew.getClass(), departamentoNew.getId());
                municipio.setDepartamento(departamentoNew);
            }
            if (modificadoPorNew != null) {
                modificadoPorNew = em.getReference(modificadoPorNew.getClass(), modificadoPorNew.getId());
                municipio.setModificadoPor(modificadoPorNew);
            }
            Collection<Documento> attachedDocumentoCollectionNew = new ArrayList<Documento>();
            for (Documento documentoCollectionNewDocumentoToAttach : documentoCollectionNew) {
                documentoCollectionNewDocumentoToAttach = em.getReference(documentoCollectionNewDocumentoToAttach.getClass(), documentoCollectionNewDocumentoToAttach.getId());
                attachedDocumentoCollectionNew.add(documentoCollectionNewDocumentoToAttach);
            }
            documentoCollectionNew = attachedDocumentoCollectionNew;
            municipio.setDocumentoCollection(documentoCollectionNew);
            Collection<Corregimiento> attachedCorregimientoCollectionNew = new ArrayList<Corregimiento>();
            for (Corregimiento corregimientoCollectionNewCorregimientoToAttach : corregimientoCollectionNew) {
                corregimientoCollectionNewCorregimientoToAttach = em.getReference(corregimientoCollectionNewCorregimientoToAttach.getClass(), corregimientoCollectionNewCorregimientoToAttach.getId());
                attachedCorregimientoCollectionNew.add(corregimientoCollectionNewCorregimientoToAttach);
            }
            corregimientoCollectionNew = attachedCorregimientoCollectionNew;
            municipio.setCorregimientoCollection(corregimientoCollectionNew);
            municipio = em.merge(municipio);
            if (creadoPorOld != null && !creadoPorOld.equals(creadoPorNew)) {
                creadoPorOld.getMunicipioCollection().remove(municipio);
                creadoPorOld = em.merge(creadoPorOld);
            }
            if (creadoPorNew != null && !creadoPorNew.equals(creadoPorOld)) {
                creadoPorNew.getMunicipioCollection().add(municipio);
                creadoPorNew = em.merge(creadoPorNew);
            }
            if (departamentoOld != null && !departamentoOld.equals(departamentoNew)) {
                departamentoOld.getMunicipioCollection().remove(municipio);
                departamentoOld = em.merge(departamentoOld);
            }
            if (departamentoNew != null && !departamentoNew.equals(departamentoOld)) {
                departamentoNew.getMunicipioCollection().add(municipio);
                departamentoNew = em.merge(departamentoNew);
            }
            if (modificadoPorOld != null && !modificadoPorOld.equals(modificadoPorNew)) {
                modificadoPorOld.getMunicipioCollection().remove(municipio);
                modificadoPorOld = em.merge(modificadoPorOld);
            }
            if (modificadoPorNew != null && !modificadoPorNew.equals(modificadoPorOld)) {
                modificadoPorNew.getMunicipioCollection().add(municipio);
                modificadoPorNew = em.merge(modificadoPorNew);
            }
            for (Documento documentoCollectionOldDocumento : documentoCollectionOld) {
                if (!documentoCollectionNew.contains(documentoCollectionOldDocumento)) {
                    documentoCollectionOldDocumento.setMunicipio(null);
                    documentoCollectionOldDocumento = em.merge(documentoCollectionOldDocumento);
                }
            }
            for (Documento documentoCollectionNewDocumento : documentoCollectionNew) {
                if (!documentoCollectionOld.contains(documentoCollectionNewDocumento)) {
                    Municipio oldMunicipioOfDocumentoCollectionNewDocumento = documentoCollectionNewDocumento.getMunicipio();
                    documentoCollectionNewDocumento.setMunicipio(municipio);
                    documentoCollectionNewDocumento = em.merge(documentoCollectionNewDocumento);
                    if (oldMunicipioOfDocumentoCollectionNewDocumento != null && !oldMunicipioOfDocumentoCollectionNewDocumento.equals(municipio)) {
                        oldMunicipioOfDocumentoCollectionNewDocumento.getDocumentoCollection().remove(documentoCollectionNewDocumento);
                        oldMunicipioOfDocumentoCollectionNewDocumento = em.merge(oldMunicipioOfDocumentoCollectionNewDocumento);
                    }
                }
            }
            for (Corregimiento corregimientoCollectionOldCorregimiento : corregimientoCollectionOld) {
                if (!corregimientoCollectionNew.contains(corregimientoCollectionOldCorregimiento)) {
                    corregimientoCollectionOldCorregimiento.setMunicipio(null);
                    corregimientoCollectionOldCorregimiento = em.merge(corregimientoCollectionOldCorregimiento);
                }
            }
            for (Corregimiento corregimientoCollectionNewCorregimiento : corregimientoCollectionNew) {
                if (!corregimientoCollectionOld.contains(corregimientoCollectionNewCorregimiento)) {
                    Municipio oldMunicipioOfCorregimientoCollectionNewCorregimiento = corregimientoCollectionNewCorregimiento.getMunicipio();
                    corregimientoCollectionNewCorregimiento.setMunicipio(municipio);
                    corregimientoCollectionNewCorregimiento = em.merge(corregimientoCollectionNewCorregimiento);
                    if (oldMunicipioOfCorregimientoCollectionNewCorregimiento != null && !oldMunicipioOfCorregimientoCollectionNewCorregimiento.equals(municipio)) {
                        oldMunicipioOfCorregimientoCollectionNewCorregimiento.getCorregimientoCollection().remove(corregimientoCollectionNewCorregimiento);
                        oldMunicipioOfCorregimientoCollectionNewCorregimiento = em.merge(oldMunicipioOfCorregimientoCollectionNewCorregimiento);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = municipio.getId();
                if (findMunicipio(id) == null) {
                    throw new NonexistentEntityException("The municipio with id " + id + " no longer exists.");
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
            Municipio municipio;
            try {
                municipio = em.getReference(Municipio.class, id);
                municipio.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The municipio with id " + id + " no longer exists.", enfe);
            }
            Usuario creadoPor = municipio.getCreadoPor();
            if (creadoPor != null) {
                creadoPor.getMunicipioCollection().remove(municipio);
                creadoPor = em.merge(creadoPor);
            }
            Departamento departamento = municipio.getDepartamento();
            if (departamento != null) {
                departamento.getMunicipioCollection().remove(municipio);
                departamento = em.merge(departamento);
            }
            Usuario modificadoPor = municipio.getModificadoPor();
            if (modificadoPor != null) {
                modificadoPor.getMunicipioCollection().remove(municipio);
                modificadoPor = em.merge(modificadoPor);
            }
            Collection<Documento> documentoCollection = municipio.getDocumentoCollection();
            for (Documento documentoCollectionDocumento : documentoCollection) {
                documentoCollectionDocumento.setMunicipio(null);
                documentoCollectionDocumento = em.merge(documentoCollectionDocumento);
            }
            Collection<Corregimiento> corregimientoCollection = municipio.getCorregimientoCollection();
            for (Corregimiento corregimientoCollectionCorregimiento : corregimientoCollection) {
                corregimientoCollectionCorregimiento.setMunicipio(null);
                corregimientoCollectionCorregimiento = em.merge(corregimientoCollectionCorregimiento);
            }
            em.remove(municipio);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Municipio> findMunicipioEntities() {
        return findMunicipioEntities(true, -1, -1);
    }

    public List<Municipio> findMunicipioEntities(int maxResults, int firstResult) {
        return findMunicipioEntities(false, maxResults, firstResult);
    }

    private List<Municipio> findMunicipioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Municipio.class));
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

    public Municipio findMunicipio(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Municipio.class, id);
        } finally {
            em.close();
        }
    }

    public int getMunicipioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Municipio> rt = cq.from(Municipio.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public List<Municipio> findMunicipiosByDepartamento(Departamento departamento) {
        return findMunicipiosByDepartamento(departamento, true, -1, -1);
    }

    public List<Municipio> findMunicipiosByDepartamento(Departamento departamento, int maxResults, int firstResult) {
        return findMunicipiosByDepartamento(departamento, false, maxResults, firstResult);
    }

    private List<Municipio> findMunicipiosByDepartamento(Departamento departamento, boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Municipio.class));
            Query q = em.createNamedQuery("Municipios.findByDepartamento", Municipio.class)
                    .setParameter("departamento", departamento);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

}
