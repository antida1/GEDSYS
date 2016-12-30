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
import com.sucomunicacion.gedsys.entities.Seccion;
import com.sucomunicacion.gedsys.entities.Serie;
import com.sucomunicacion.gedsys.entities.SubSeccion;
import com.sucomunicacion.gedsys.entities.TipoDocumental;
import com.sucomunicacion.gedsys.entities.Anexo;
import com.sucomunicacion.gedsys.entities.Documento;
import com.sucomunicacion.gedsys.model.exceptions.NonexistentEntityException;
import com.sucomunicacion.gedsys.model.exceptions.PreexistingEntityException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Robert Alexis Mejia <rmejia@base16.co>
 */
public class DocumentoJpaController implements Serializable {

    public DocumentoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Documento documento) throws PreexistingEntityException, Exception {
        if (documento.getAnexoList() == null) {
            documento.setAnexoList(new ArrayList<Anexo>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Seccion session = documento.getSession();
            if (session != null) {
                session = em.getReference(session.getClass(), session.getId());
                documento.setSession(session);
            }
            Serie serie = documento.getSerie();
            if (serie != null) {
                serie = em.getReference(serie.getClass(), serie.getId());
                documento.setSerie(serie);
            }
            SubSeccion subSeccion = documento.getSubSeccion();
            if (subSeccion != null) {
                subSeccion = em.getReference(subSeccion.getClass(), subSeccion.getId());
                documento.setSubSeccion(subSeccion);
            }
            TipoDocumental tipoDocumental = documento.getTipoDocumental();
            if (tipoDocumental != null) {
                tipoDocumental = em.getReference(tipoDocumental.getClass(), tipoDocumental.getId());
                documento.setTipoDocumental(tipoDocumental);
            }
            List<Anexo> attachedAnexoList = new ArrayList<Anexo>();
            for (Anexo anexoListAnexoToAttach : documento.getAnexoList()) {
                anexoListAnexoToAttach = em.getReference(anexoListAnexoToAttach.getClass(), anexoListAnexoToAttach.getId());
                attachedAnexoList.add(anexoListAnexoToAttach);
            }
            documento.setAnexoList(attachedAnexoList);
            em.persist(documento);
            if (session != null) {
                session.getDocumentoList().add(documento);
                session = em.merge(session);
            }
            if (serie != null) {
                serie.getDocumentoList().add(documento);
                serie = em.merge(serie);
            }
            if (subSeccion != null) {
                subSeccion.getDocumentoList().add(documento);
                subSeccion = em.merge(subSeccion);
            }
            if (tipoDocumental != null) {
                tipoDocumental.getDocumentoList().add(documento);
                tipoDocumental = em.merge(tipoDocumental);
            }
            for (Anexo anexoListAnexo : documento.getAnexoList()) {
                Documento oldDocumentoOfAnexoListAnexo = anexoListAnexo.getDocumento();
                anexoListAnexo.setDocumento(documento);
                anexoListAnexo = em.merge(anexoListAnexo);
                if (oldDocumentoOfAnexoListAnexo != null) {
                    oldDocumentoOfAnexoListAnexo.getAnexoList().remove(anexoListAnexo);
                    oldDocumentoOfAnexoListAnexo = em.merge(oldDocumentoOfAnexoListAnexo);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findDocumento(documento.getId()) != null) {
                throw new PreexistingEntityException("Documento " + documento + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Documento documento) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Documento persistentDocumento = em.find(Documento.class, documento.getId());
            Seccion sessionOld = persistentDocumento.getSession();
            Seccion sessionNew = documento.getSession();
            Serie serieOld = persistentDocumento.getSerie();
            Serie serieNew = documento.getSerie();
            SubSeccion subSeccionOld = persistentDocumento.getSubSeccion();
            SubSeccion subSeccionNew = documento.getSubSeccion();
            TipoDocumental tipoDocumentalOld = persistentDocumento.getTipoDocumental();
            TipoDocumental tipoDocumentalNew = documento.getTipoDocumental();
            List<Anexo> anexoListOld = persistentDocumento.getAnexoList();
            List<Anexo> anexoListNew = documento.getAnexoList();
            if (sessionNew != null) {
                sessionNew = em.getReference(sessionNew.getClass(), sessionNew.getId());
                documento.setSession(sessionNew);
            }
            if (serieNew != null) {
                serieNew = em.getReference(serieNew.getClass(), serieNew.getId());
                documento.setSerie(serieNew);
            }
            if (subSeccionNew != null) {
                subSeccionNew = em.getReference(subSeccionNew.getClass(), subSeccionNew.getId());
                documento.setSubSeccion(subSeccionNew);
            }
            if (tipoDocumentalNew != null) {
                tipoDocumentalNew = em.getReference(tipoDocumentalNew.getClass(), tipoDocumentalNew.getId());
                documento.setTipoDocumental(tipoDocumentalNew);
            }
            List<Anexo> attachedAnexoListNew = new ArrayList<Anexo>();
            for (Anexo anexoListNewAnexoToAttach : anexoListNew) {
                anexoListNewAnexoToAttach = em.getReference(anexoListNewAnexoToAttach.getClass(), anexoListNewAnexoToAttach.getId());
                attachedAnexoListNew.add(anexoListNewAnexoToAttach);
            }
            anexoListNew = attachedAnexoListNew;
            documento.setAnexoList(anexoListNew);
            documento = em.merge(documento);
            if (sessionOld != null && !sessionOld.equals(sessionNew)) {
                sessionOld.getDocumentoList().remove(documento);
                sessionOld = em.merge(sessionOld);
            }
            if (sessionNew != null && !sessionNew.equals(sessionOld)) {
                sessionNew.getDocumentoList().add(documento);
                sessionNew = em.merge(sessionNew);
            }
            if (serieOld != null && !serieOld.equals(serieNew)) {
                serieOld.getDocumentoList().remove(documento);
                serieOld = em.merge(serieOld);
            }
            if (serieNew != null && !serieNew.equals(serieOld)) {
                serieNew.getDocumentoList().add(documento);
                serieNew = em.merge(serieNew);
            }
            if (subSeccionOld != null && !subSeccionOld.equals(subSeccionNew)) {
                subSeccionOld.getDocumentoList().remove(documento);
                subSeccionOld = em.merge(subSeccionOld);
            }
            if (subSeccionNew != null && !subSeccionNew.equals(subSeccionOld)) {
                subSeccionNew.getDocumentoList().add(documento);
                subSeccionNew = em.merge(subSeccionNew);
            }
            if (tipoDocumentalOld != null && !tipoDocumentalOld.equals(tipoDocumentalNew)) {
                tipoDocumentalOld.getDocumentoList().remove(documento);
                tipoDocumentalOld = em.merge(tipoDocumentalOld);
            }
            if (tipoDocumentalNew != null && !tipoDocumentalNew.equals(tipoDocumentalOld)) {
                tipoDocumentalNew.getDocumentoList().add(documento);
                tipoDocumentalNew = em.merge(tipoDocumentalNew);
            }
            for (Anexo anexoListOldAnexo : anexoListOld) {
                if (!anexoListNew.contains(anexoListOldAnexo)) {
                    anexoListOldAnexo.setDocumento(null);
                    anexoListOldAnexo = em.merge(anexoListOldAnexo);
                }
            }
            for (Anexo anexoListNewAnexo : anexoListNew) {
                if (!anexoListOld.contains(anexoListNewAnexo)) {
                    Documento oldDocumentoOfAnexoListNewAnexo = anexoListNewAnexo.getDocumento();
                    anexoListNewAnexo.setDocumento(documento);
                    anexoListNewAnexo = em.merge(anexoListNewAnexo);
                    if (oldDocumentoOfAnexoListNewAnexo != null && !oldDocumentoOfAnexoListNewAnexo.equals(documento)) {
                        oldDocumentoOfAnexoListNewAnexo.getAnexoList().remove(anexoListNewAnexo);
                        oldDocumentoOfAnexoListNewAnexo = em.merge(oldDocumentoOfAnexoListNewAnexo);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = documento.getId();
                if (findDocumento(id) == null) {
                    throw new NonexistentEntityException("The documento with id " + id + " no longer exists.");
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
            Documento documento;
            try {
                documento = em.getReference(Documento.class, id);
                documento.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The documento with id " + id + " no longer exists.", enfe);
            }
            Seccion session = documento.getSession();
            if (session != null) {
                session.getDocumentoList().remove(documento);
                session = em.merge(session);
            }
            Serie serie = documento.getSerie();
            if (serie != null) {
                serie.getDocumentoList().remove(documento);
                serie = em.merge(serie);
            }
            SubSeccion subSeccion = documento.getSubSeccion();
            if (subSeccion != null) {
                subSeccion.getDocumentoList().remove(documento);
                subSeccion = em.merge(subSeccion);
            }
            TipoDocumental tipoDocumental = documento.getTipoDocumental();
            if (tipoDocumental != null) {
                tipoDocumental.getDocumentoList().remove(documento);
                tipoDocumental = em.merge(tipoDocumental);
            }
            List<Anexo> anexoList = documento.getAnexoList();
            for (Anexo anexoListAnexo : anexoList) {
                anexoListAnexo.setDocumento(null);
                anexoListAnexo = em.merge(anexoListAnexo);
            }
            em.remove(documento);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Documento> findDocumentoEntities() {
        return findDocumentoEntities(true, -1, -1);
    }

    public List<Documento> findDocumentoEntities(int maxResults, int firstResult) {
        return findDocumentoEntities(false, maxResults, firstResult);
    }

    private List<Documento> findDocumentoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Documento.class));
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

    public Documento findDocumento(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Documento.class, id);
        } finally {
            em.close();
        }
    }

    public int getDocumentoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Documento> rt = cq.from(Documento.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
