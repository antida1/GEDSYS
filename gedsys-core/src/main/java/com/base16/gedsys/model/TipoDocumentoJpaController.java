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
import java.util.ArrayList;
import java.util.Collection;
import com.base16.gedsys.entities.PlantillaDocumental;
import com.base16.gedsys.entities.Consecutivo;
import com.base16.gedsys.entities.ProcesoTipoDocumento;
import com.base16.gedsys.entities.TipoDocumento;
import com.base16.gedsys.model.exceptions.NonexistentEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author rober
 */
public class TipoDocumentoJpaController implements Serializable {

    public TipoDocumentoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TipoDocumento tipoDocumento) {
        if (tipoDocumento.getDocumentoCollection() == null) {
            tipoDocumento.setDocumentoCollection(new ArrayList<Documento>());
        }
        if (tipoDocumento.getPlantillaDocumentalCollection() == null) {
            tipoDocumento.setPlantillaDocumentalCollection(new ArrayList<PlantillaDocumental>());
        }
        if (tipoDocumento.getConsecutivoCollection() == null) {
            tipoDocumento.setConsecutivoCollection(new ArrayList<Consecutivo>());
        }
        if (tipoDocumento.getProcesoTipoDocumentoCollection() == null) {
            tipoDocumento.setProcesoTipoDocumentoCollection(new ArrayList<ProcesoTipoDocumento>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario creadoPor = tipoDocumento.getCreadoPor();
            if (creadoPor != null) {
                creadoPor = em.getReference(creadoPor.getClass(), creadoPor.getId());
                tipoDocumento.setCreadoPor(creadoPor);
            }
            Usuario modificadoPor = tipoDocumento.getModificadoPor();
            if (modificadoPor != null) {
                modificadoPor = em.getReference(modificadoPor.getClass(), modificadoPor.getId());
                tipoDocumento.setModificadoPor(modificadoPor);
            }
            Collection<Documento> attachedDocumentoCollection = new ArrayList<Documento>();
            for (Documento documentoCollectionDocumentoToAttach : tipoDocumento.getDocumentoCollection()) {
                documentoCollectionDocumentoToAttach = em.getReference(documentoCollectionDocumentoToAttach.getClass(), documentoCollectionDocumentoToAttach.getId());
                attachedDocumentoCollection.add(documentoCollectionDocumentoToAttach);
            }
            tipoDocumento.setDocumentoCollection(attachedDocumentoCollection);
            Collection<PlantillaDocumental> attachedPlantillaDocumentalCollection = new ArrayList<PlantillaDocumental>();
            for (PlantillaDocumental plantillaDocumentalCollectionPlantillaDocumentalToAttach : tipoDocumento.getPlantillaDocumentalCollection()) {
                plantillaDocumentalCollectionPlantillaDocumentalToAttach = em.getReference(plantillaDocumentalCollectionPlantillaDocumentalToAttach.getClass(), plantillaDocumentalCollectionPlantillaDocumentalToAttach.getId());
                attachedPlantillaDocumentalCollection.add(plantillaDocumentalCollectionPlantillaDocumentalToAttach);
            }
            tipoDocumento.setPlantillaDocumentalCollection(attachedPlantillaDocumentalCollection);
            Collection<Consecutivo> attachedConsecutivoCollection = new ArrayList<Consecutivo>();
            for (Consecutivo consecutivoCollectionConsecutivoToAttach : tipoDocumento.getConsecutivoCollection()) {
                consecutivoCollectionConsecutivoToAttach = em.getReference(consecutivoCollectionConsecutivoToAttach.getClass(), consecutivoCollectionConsecutivoToAttach.getId());
                attachedConsecutivoCollection.add(consecutivoCollectionConsecutivoToAttach);
            }
            tipoDocumento.setConsecutivoCollection(attachedConsecutivoCollection);
            Collection<ProcesoTipoDocumento> attachedProcesoTipoDocumentoCollection = new ArrayList<ProcesoTipoDocumento>();
            for (ProcesoTipoDocumento procesoTipoDocumentoCollectionProcesoTipoDocumentoToAttach : tipoDocumento.getProcesoTipoDocumentoCollection()) {
                procesoTipoDocumentoCollectionProcesoTipoDocumentoToAttach = em.getReference(procesoTipoDocumentoCollectionProcesoTipoDocumentoToAttach.getClass(), procesoTipoDocumentoCollectionProcesoTipoDocumentoToAttach.getId());
                attachedProcesoTipoDocumentoCollection.add(procesoTipoDocumentoCollectionProcesoTipoDocumentoToAttach);
            }
            tipoDocumento.setProcesoTipoDocumentoCollection(attachedProcesoTipoDocumentoCollection);
            em.persist(tipoDocumento);
            if (creadoPor != null) {
                creadoPor.getTipoDocumentoCollection().add(tipoDocumento);
                creadoPor = em.merge(creadoPor);
            }
            if (modificadoPor != null) {
                modificadoPor.getTipoDocumentoCollection().add(tipoDocumento);
                modificadoPor = em.merge(modificadoPor);
            }
            for (Documento documentoCollectionDocumento : tipoDocumento.getDocumentoCollection()) {
                TipoDocumento oldTipoDocumentoOfDocumentoCollectionDocumento = documentoCollectionDocumento.getTipoDocumento();
                documentoCollectionDocumento.setTipoDocumento(tipoDocumento);
                documentoCollectionDocumento = em.merge(documentoCollectionDocumento);
                if (oldTipoDocumentoOfDocumentoCollectionDocumento != null) {
                    oldTipoDocumentoOfDocumentoCollectionDocumento.getDocumentoCollection().remove(documentoCollectionDocumento);
                    oldTipoDocumentoOfDocumentoCollectionDocumento = em.merge(oldTipoDocumentoOfDocumentoCollectionDocumento);
                }
            }
            for (PlantillaDocumental plantillaDocumentalCollectionPlantillaDocumental : tipoDocumento.getPlantillaDocumentalCollection()) {
                TipoDocumento oldTipoDocumentoOfPlantillaDocumentalCollectionPlantillaDocumental = plantillaDocumentalCollectionPlantillaDocumental.getTipoDocumento();
                plantillaDocumentalCollectionPlantillaDocumental.setTipoDocumento(tipoDocumento);
                plantillaDocumentalCollectionPlantillaDocumental = em.merge(plantillaDocumentalCollectionPlantillaDocumental);
                if (oldTipoDocumentoOfPlantillaDocumentalCollectionPlantillaDocumental != null) {
                    oldTipoDocumentoOfPlantillaDocumentalCollectionPlantillaDocumental.getPlantillaDocumentalCollection().remove(plantillaDocumentalCollectionPlantillaDocumental);
                    oldTipoDocumentoOfPlantillaDocumentalCollectionPlantillaDocumental = em.merge(oldTipoDocumentoOfPlantillaDocumentalCollectionPlantillaDocumental);
                }
            }
            for (Consecutivo consecutivoCollectionConsecutivo : tipoDocumento.getConsecutivoCollection()) {
                TipoDocumento oldTipoDocumentoOfConsecutivoCollectionConsecutivo = consecutivoCollectionConsecutivo.getTipoDocumento();
                consecutivoCollectionConsecutivo.setTipoDocumento(tipoDocumento);
                consecutivoCollectionConsecutivo = em.merge(consecutivoCollectionConsecutivo);
                if (oldTipoDocumentoOfConsecutivoCollectionConsecutivo != null) {
                    oldTipoDocumentoOfConsecutivoCollectionConsecutivo.getConsecutivoCollection().remove(consecutivoCollectionConsecutivo);
                    oldTipoDocumentoOfConsecutivoCollectionConsecutivo = em.merge(oldTipoDocumentoOfConsecutivoCollectionConsecutivo);
                }
            }
            for (ProcesoTipoDocumento procesoTipoDocumentoCollectionProcesoTipoDocumento : tipoDocumento.getProcesoTipoDocumentoCollection()) {
                TipoDocumento oldTipoDocumentoOfProcesoTipoDocumentoCollectionProcesoTipoDocumento = procesoTipoDocumentoCollectionProcesoTipoDocumento.getTipoDocumento();
                procesoTipoDocumentoCollectionProcesoTipoDocumento.setTipoDocumento(tipoDocumento);
                procesoTipoDocumentoCollectionProcesoTipoDocumento = em.merge(procesoTipoDocumentoCollectionProcesoTipoDocumento);
                if (oldTipoDocumentoOfProcesoTipoDocumentoCollectionProcesoTipoDocumento != null) {
                    oldTipoDocumentoOfProcesoTipoDocumentoCollectionProcesoTipoDocumento.getProcesoTipoDocumentoCollection().remove(procesoTipoDocumentoCollectionProcesoTipoDocumento);
                    oldTipoDocumentoOfProcesoTipoDocumentoCollectionProcesoTipoDocumento = em.merge(oldTipoDocumentoOfProcesoTipoDocumentoCollectionProcesoTipoDocumento);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TipoDocumento tipoDocumento) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TipoDocumento persistentTipoDocumento = em.find(TipoDocumento.class, tipoDocumento.getId());
            Usuario creadoPorOld = persistentTipoDocumento.getCreadoPor();
            Usuario creadoPorNew = tipoDocumento.getCreadoPor();
            Usuario modificadoPorOld = persistentTipoDocumento.getModificadoPor();
            Usuario modificadoPorNew = tipoDocumento.getModificadoPor();
            Collection<Documento> documentoCollectionOld = persistentTipoDocumento.getDocumentoCollection();
            Collection<Documento> documentoCollectionNew = tipoDocumento.getDocumentoCollection();
            Collection<PlantillaDocumental> plantillaDocumentalCollectionOld = persistentTipoDocumento.getPlantillaDocumentalCollection();
            Collection<PlantillaDocumental> plantillaDocumentalCollectionNew = tipoDocumento.getPlantillaDocumentalCollection();
            Collection<Consecutivo> consecutivoCollectionOld = persistentTipoDocumento.getConsecutivoCollection();
            Collection<Consecutivo> consecutivoCollectionNew = tipoDocumento.getConsecutivoCollection();
            Collection<ProcesoTipoDocumento> procesoTipoDocumentoCollectionOld = persistentTipoDocumento.getProcesoTipoDocumentoCollection();
            Collection<ProcesoTipoDocumento> procesoTipoDocumentoCollectionNew = tipoDocumento.getProcesoTipoDocumentoCollection();
            if (creadoPorNew != null) {
                creadoPorNew = em.getReference(creadoPorNew.getClass(), creadoPorNew.getId());
                tipoDocumento.setCreadoPor(creadoPorNew);
            }
            if (modificadoPorNew != null) {
                modificadoPorNew = em.getReference(modificadoPorNew.getClass(), modificadoPorNew.getId());
                tipoDocumento.setModificadoPor(modificadoPorNew);
            }
            Collection<Documento> attachedDocumentoCollectionNew = new ArrayList<Documento>();
            for (Documento documentoCollectionNewDocumentoToAttach : documentoCollectionNew) {
                documentoCollectionNewDocumentoToAttach = em.getReference(documentoCollectionNewDocumentoToAttach.getClass(), documentoCollectionNewDocumentoToAttach.getId());
                attachedDocumentoCollectionNew.add(documentoCollectionNewDocumentoToAttach);
            }
            documentoCollectionNew = attachedDocumentoCollectionNew;
            tipoDocumento.setDocumentoCollection(documentoCollectionNew);
            Collection<PlantillaDocumental> attachedPlantillaDocumentalCollectionNew = new ArrayList<PlantillaDocumental>();
            for (PlantillaDocumental plantillaDocumentalCollectionNewPlantillaDocumentalToAttach : plantillaDocumentalCollectionNew) {
                plantillaDocumentalCollectionNewPlantillaDocumentalToAttach = em.getReference(plantillaDocumentalCollectionNewPlantillaDocumentalToAttach.getClass(), plantillaDocumentalCollectionNewPlantillaDocumentalToAttach.getId());
                attachedPlantillaDocumentalCollectionNew.add(plantillaDocumentalCollectionNewPlantillaDocumentalToAttach);
            }
            plantillaDocumentalCollectionNew = attachedPlantillaDocumentalCollectionNew;
            tipoDocumento.setPlantillaDocumentalCollection(plantillaDocumentalCollectionNew);
            Collection<Consecutivo> attachedConsecutivoCollectionNew = new ArrayList<Consecutivo>();
            for (Consecutivo consecutivoCollectionNewConsecutivoToAttach : consecutivoCollectionNew) {
                consecutivoCollectionNewConsecutivoToAttach = em.getReference(consecutivoCollectionNewConsecutivoToAttach.getClass(), consecutivoCollectionNewConsecutivoToAttach.getId());
                attachedConsecutivoCollectionNew.add(consecutivoCollectionNewConsecutivoToAttach);
            }
            consecutivoCollectionNew = attachedConsecutivoCollectionNew;
            tipoDocumento.setConsecutivoCollection(consecutivoCollectionNew);
            Collection<ProcesoTipoDocumento> attachedProcesoTipoDocumentoCollectionNew = new ArrayList<ProcesoTipoDocumento>();
            for (ProcesoTipoDocumento procesoTipoDocumentoCollectionNewProcesoTipoDocumentoToAttach : procesoTipoDocumentoCollectionNew) {
                procesoTipoDocumentoCollectionNewProcesoTipoDocumentoToAttach = em.getReference(procesoTipoDocumentoCollectionNewProcesoTipoDocumentoToAttach.getClass(), procesoTipoDocumentoCollectionNewProcesoTipoDocumentoToAttach.getId());
                attachedProcesoTipoDocumentoCollectionNew.add(procesoTipoDocumentoCollectionNewProcesoTipoDocumentoToAttach);
            }
            procesoTipoDocumentoCollectionNew = attachedProcesoTipoDocumentoCollectionNew;
            tipoDocumento.setProcesoTipoDocumentoCollection(procesoTipoDocumentoCollectionNew);
            tipoDocumento = em.merge(tipoDocumento);
            if (creadoPorOld != null && !creadoPorOld.equals(creadoPorNew)) {
                creadoPorOld.getTipoDocumentoCollection().remove(tipoDocumento);
                creadoPorOld = em.merge(creadoPorOld);
            }
            if (creadoPorNew != null && !creadoPorNew.equals(creadoPorOld)) {
                creadoPorNew.getTipoDocumentoCollection().add(tipoDocumento);
                creadoPorNew = em.merge(creadoPorNew);
            }
            if (modificadoPorOld != null && !modificadoPorOld.equals(modificadoPorNew)) {
                modificadoPorOld.getTipoDocumentoCollection().remove(tipoDocumento);
                modificadoPorOld = em.merge(modificadoPorOld);
            }
            if (modificadoPorNew != null && !modificadoPorNew.equals(modificadoPorOld)) {
                modificadoPorNew.getTipoDocumentoCollection().add(tipoDocumento);
                modificadoPorNew = em.merge(modificadoPorNew);
            }
            for (Documento documentoCollectionOldDocumento : documentoCollectionOld) {
                if (!documentoCollectionNew.contains(documentoCollectionOldDocumento)) {
                    documentoCollectionOldDocumento.setTipoDocumento(null);
                    documentoCollectionOldDocumento = em.merge(documentoCollectionOldDocumento);
                }
            }
            for (Documento documentoCollectionNewDocumento : documentoCollectionNew) {
                if (!documentoCollectionOld.contains(documentoCollectionNewDocumento)) {
                    TipoDocumento oldTipoDocumentoOfDocumentoCollectionNewDocumento = documentoCollectionNewDocumento.getTipoDocumento();
                    documentoCollectionNewDocumento.setTipoDocumento(tipoDocumento);
                    documentoCollectionNewDocumento = em.merge(documentoCollectionNewDocumento);
                    if (oldTipoDocumentoOfDocumentoCollectionNewDocumento != null && !oldTipoDocumentoOfDocumentoCollectionNewDocumento.equals(tipoDocumento)) {
                        oldTipoDocumentoOfDocumentoCollectionNewDocumento.getDocumentoCollection().remove(documentoCollectionNewDocumento);
                        oldTipoDocumentoOfDocumentoCollectionNewDocumento = em.merge(oldTipoDocumentoOfDocumentoCollectionNewDocumento);
                    }
                }
            }
            for (PlantillaDocumental plantillaDocumentalCollectionOldPlantillaDocumental : plantillaDocumentalCollectionOld) {
                if (!plantillaDocumentalCollectionNew.contains(plantillaDocumentalCollectionOldPlantillaDocumental)) {
                    plantillaDocumentalCollectionOldPlantillaDocumental.setTipoDocumento(null);
                    plantillaDocumentalCollectionOldPlantillaDocumental = em.merge(plantillaDocumentalCollectionOldPlantillaDocumental);
                }
            }
            for (PlantillaDocumental plantillaDocumentalCollectionNewPlantillaDocumental : plantillaDocumentalCollectionNew) {
                if (!plantillaDocumentalCollectionOld.contains(plantillaDocumentalCollectionNewPlantillaDocumental)) {
                    TipoDocumento oldTipoDocumentoOfPlantillaDocumentalCollectionNewPlantillaDocumental = plantillaDocumentalCollectionNewPlantillaDocumental.getTipoDocumento();
                    plantillaDocumentalCollectionNewPlantillaDocumental.setTipoDocumento(tipoDocumento);
                    plantillaDocumentalCollectionNewPlantillaDocumental = em.merge(plantillaDocumentalCollectionNewPlantillaDocumental);
                    if (oldTipoDocumentoOfPlantillaDocumentalCollectionNewPlantillaDocumental != null && !oldTipoDocumentoOfPlantillaDocumentalCollectionNewPlantillaDocumental.equals(tipoDocumento)) {
                        oldTipoDocumentoOfPlantillaDocumentalCollectionNewPlantillaDocumental.getPlantillaDocumentalCollection().remove(plantillaDocumentalCollectionNewPlantillaDocumental);
                        oldTipoDocumentoOfPlantillaDocumentalCollectionNewPlantillaDocumental = em.merge(oldTipoDocumentoOfPlantillaDocumentalCollectionNewPlantillaDocumental);
                    }
                }
            }
            for (Consecutivo consecutivoCollectionOldConsecutivo : consecutivoCollectionOld) {
                if (!consecutivoCollectionNew.contains(consecutivoCollectionOldConsecutivo)) {
                    consecutivoCollectionOldConsecutivo.setTipoDocumento(null);
                    consecutivoCollectionOldConsecutivo = em.merge(consecutivoCollectionOldConsecutivo);
                }
            }
            for (Consecutivo consecutivoCollectionNewConsecutivo : consecutivoCollectionNew) {
                if (!consecutivoCollectionOld.contains(consecutivoCollectionNewConsecutivo)) {
                    TipoDocumento oldTipoDocumentoOfConsecutivoCollectionNewConsecutivo = consecutivoCollectionNewConsecutivo.getTipoDocumento();
                    consecutivoCollectionNewConsecutivo.setTipoDocumento(tipoDocumento);
                    consecutivoCollectionNewConsecutivo = em.merge(consecutivoCollectionNewConsecutivo);
                    if (oldTipoDocumentoOfConsecutivoCollectionNewConsecutivo != null && !oldTipoDocumentoOfConsecutivoCollectionNewConsecutivo.equals(tipoDocumento)) {
                        oldTipoDocumentoOfConsecutivoCollectionNewConsecutivo.getConsecutivoCollection().remove(consecutivoCollectionNewConsecutivo);
                        oldTipoDocumentoOfConsecutivoCollectionNewConsecutivo = em.merge(oldTipoDocumentoOfConsecutivoCollectionNewConsecutivo);
                    }
                }
            }
            for (ProcesoTipoDocumento procesoTipoDocumentoCollectionOldProcesoTipoDocumento : procesoTipoDocumentoCollectionOld) {
                if (!procesoTipoDocumentoCollectionNew.contains(procesoTipoDocumentoCollectionOldProcesoTipoDocumento)) {
                    procesoTipoDocumentoCollectionOldProcesoTipoDocumento.setTipoDocumento(null);
                    procesoTipoDocumentoCollectionOldProcesoTipoDocumento = em.merge(procesoTipoDocumentoCollectionOldProcesoTipoDocumento);
                }
            }
            for (ProcesoTipoDocumento procesoTipoDocumentoCollectionNewProcesoTipoDocumento : procesoTipoDocumentoCollectionNew) {
                if (!procesoTipoDocumentoCollectionOld.contains(procesoTipoDocumentoCollectionNewProcesoTipoDocumento)) {
                    TipoDocumento oldTipoDocumentoOfProcesoTipoDocumentoCollectionNewProcesoTipoDocumento = procesoTipoDocumentoCollectionNewProcesoTipoDocumento.getTipoDocumento();
                    procesoTipoDocumentoCollectionNewProcesoTipoDocumento.setTipoDocumento(tipoDocumento);
                    procesoTipoDocumentoCollectionNewProcesoTipoDocumento = em.merge(procesoTipoDocumentoCollectionNewProcesoTipoDocumento);
                    if (oldTipoDocumentoOfProcesoTipoDocumentoCollectionNewProcesoTipoDocumento != null && !oldTipoDocumentoOfProcesoTipoDocumentoCollectionNewProcesoTipoDocumento.equals(tipoDocumento)) {
                        oldTipoDocumentoOfProcesoTipoDocumentoCollectionNewProcesoTipoDocumento.getProcesoTipoDocumentoCollection().remove(procesoTipoDocumentoCollectionNewProcesoTipoDocumento);
                        oldTipoDocumentoOfProcesoTipoDocumentoCollectionNewProcesoTipoDocumento = em.merge(oldTipoDocumentoOfProcesoTipoDocumentoCollectionNewProcesoTipoDocumento);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = tipoDocumento.getId();
                if (findTipoDocumento(id) == null) {
                    throw new NonexistentEntityException("The tipoDocumento with id " + id + " no longer exists.");
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
            TipoDocumento tipoDocumento;
            try {
                tipoDocumento = em.getReference(TipoDocumento.class, id);
                tipoDocumento.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tipoDocumento with id " + id + " no longer exists.", enfe);
            }
            Usuario creadoPor = tipoDocumento.getCreadoPor();
            if (creadoPor != null) {
                creadoPor.getTipoDocumentoCollection().remove(tipoDocumento);
                creadoPor = em.merge(creadoPor);
            }
            Usuario modificadoPor = tipoDocumento.getModificadoPor();
            if (modificadoPor != null) {
                modificadoPor.getTipoDocumentoCollection().remove(tipoDocumento);
                modificadoPor = em.merge(modificadoPor);
            }
            Collection<Documento> documentoCollection = tipoDocumento.getDocumentoCollection();
            for (Documento documentoCollectionDocumento : documentoCollection) {
                documentoCollectionDocumento.setTipoDocumento(null);
                documentoCollectionDocumento = em.merge(documentoCollectionDocumento);
            }
            Collection<PlantillaDocumental> plantillaDocumentalCollection = tipoDocumento.getPlantillaDocumentalCollection();
            for (PlantillaDocumental plantillaDocumentalCollectionPlantillaDocumental : plantillaDocumentalCollection) {
                plantillaDocumentalCollectionPlantillaDocumental.setTipoDocumento(null);
                plantillaDocumentalCollectionPlantillaDocumental = em.merge(plantillaDocumentalCollectionPlantillaDocumental);
            }
            Collection<Consecutivo> consecutivoCollection = tipoDocumento.getConsecutivoCollection();
            for (Consecutivo consecutivoCollectionConsecutivo : consecutivoCollection) {
                consecutivoCollectionConsecutivo.setTipoDocumento(null);
                consecutivoCollectionConsecutivo = em.merge(consecutivoCollectionConsecutivo);
            }
            Collection<ProcesoTipoDocumento> procesoTipoDocumentoCollection = tipoDocumento.getProcesoTipoDocumentoCollection();
            for (ProcesoTipoDocumento procesoTipoDocumentoCollectionProcesoTipoDocumento : procesoTipoDocumentoCollection) {
                procesoTipoDocumentoCollectionProcesoTipoDocumento.setTipoDocumento(null);
                procesoTipoDocumentoCollectionProcesoTipoDocumento = em.merge(procesoTipoDocumentoCollectionProcesoTipoDocumento);
            }
            em.remove(tipoDocumento);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TipoDocumento> findTipoDocumentoEntities() {
        return findTipoDocumentoEntities(true, -1, -1);
    }

    public List<TipoDocumento> findTipoDocumentoEntities(int maxResults, int firstResult) {
        return findTipoDocumentoEntities(false, maxResults, firstResult);
    }

    private List<TipoDocumento> findTipoDocumentoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(TipoDocumento.class));
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

    public TipoDocumento findTipoDocumento(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TipoDocumento.class, id);
        } finally {
            em.close();
        }
    }

    public int getTipoDocumentoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<TipoDocumento> rt = cq.from(TipoDocumento.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
