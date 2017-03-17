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
import com.sucomunicacion.gedsys.entities.ProcesoNegocio;
import com.sucomunicacion.gedsys.entities.Usuario;
import com.sucomunicacion.gedsys.entities.ProcesoTipoDocumento;
import java.util.ArrayList;
import java.util.Collection;
import com.sucomunicacion.gedsys.entities.ProcesoDocumental;
import com.sucomunicacion.gedsys.entities.MonitoresProceso;
import com.sucomunicacion.gedsys.model.exceptions.NonexistentEntityException;
import com.sucomunicacion.gedsys.model.exceptions.PreexistingEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author rober
 */
public class ProcesoNegocioJpaController implements Serializable {

    public ProcesoNegocioJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ProcesoNegocio procesoNegocio) throws PreexistingEntityException, Exception {
        if (procesoNegocio.getProcesoTipoDocumentoCollection() == null) {
            procesoNegocio.setProcesoTipoDocumentoCollection(new ArrayList<ProcesoTipoDocumento>());
        }
        if (procesoNegocio.getProcesoNegocioCollection() == null) {
            procesoNegocio.setProcesoNegocioCollection(new ArrayList<ProcesoNegocio>());
        }
        if (procesoNegocio.getProcesoDocumentalCollection() == null) {
            procesoNegocio.setProcesoDocumentalCollection(new ArrayList<ProcesoDocumental>());
        }
        if (procesoNegocio.getMonitoresProcesoCollection() == null) {
            procesoNegocio.setMonitoresProcesoCollection(new ArrayList<MonitoresProceso>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ProcesoNegocio siguienteProceso = procesoNegocio.getSiguienteProceso();
            if (siguienteProceso != null) {
                siguienteProceso = em.getReference(siguienteProceso.getClass(), siguienteProceso.getId());
                procesoNegocio.setSiguienteProceso(siguienteProceso);
            }
            Usuario creadoPor = procesoNegocio.getCreadoPor();
            if (creadoPor != null) {
                creadoPor = em.getReference(creadoPor.getClass(), creadoPor.getId());
                procesoNegocio.setCreadoPor(creadoPor);
            }
            Usuario modificadoPor = procesoNegocio.getModificadoPor();
            if (modificadoPor != null) {
                modificadoPor = em.getReference(modificadoPor.getClass(), modificadoPor.getId());
                procesoNegocio.setModificadoPor(modificadoPor);
            }
            Collection<ProcesoTipoDocumento> attachedProcesoTipoDocumentoCollection = new ArrayList<ProcesoTipoDocumento>();
            for (ProcesoTipoDocumento procesoTipoDocumentoCollectionProcesoTipoDocumentoToAttach : procesoNegocio.getProcesoTipoDocumentoCollection()) {
                procesoTipoDocumentoCollectionProcesoTipoDocumentoToAttach = em.getReference(procesoTipoDocumentoCollectionProcesoTipoDocumentoToAttach.getClass(), procesoTipoDocumentoCollectionProcesoTipoDocumentoToAttach.getId());
                attachedProcesoTipoDocumentoCollection.add(procesoTipoDocumentoCollectionProcesoTipoDocumentoToAttach);
            }
            procesoNegocio.setProcesoTipoDocumentoCollection(attachedProcesoTipoDocumentoCollection);
            Collection<ProcesoNegocio> attachedProcesoNegocioCollection = new ArrayList<ProcesoNegocio>();
            for (ProcesoNegocio procesoNegocioCollectionProcesoNegocioToAttach : procesoNegocio.getProcesoNegocioCollection()) {
                procesoNegocioCollectionProcesoNegocioToAttach = em.getReference(procesoNegocioCollectionProcesoNegocioToAttach.getClass(), procesoNegocioCollectionProcesoNegocioToAttach.getId());
                attachedProcesoNegocioCollection.add(procesoNegocioCollectionProcesoNegocioToAttach);
            }
            procesoNegocio.setProcesoNegocioCollection(attachedProcesoNegocioCollection);
            Collection<ProcesoDocumental> attachedProcesoDocumentalCollection = new ArrayList<ProcesoDocumental>();
            for (ProcesoDocumental procesoDocumentalCollectionProcesoDocumentalToAttach : procesoNegocio.getProcesoDocumentalCollection()) {
                procesoDocumentalCollectionProcesoDocumentalToAttach = em.getReference(procesoDocumentalCollectionProcesoDocumentalToAttach.getClass(), procesoDocumentalCollectionProcesoDocumentalToAttach.getId());
                attachedProcesoDocumentalCollection.add(procesoDocumentalCollectionProcesoDocumentalToAttach);
            }
            procesoNegocio.setProcesoDocumentalCollection(attachedProcesoDocumentalCollection);
            Collection<MonitoresProceso> attachedMonitoresProcesoCollection = new ArrayList<MonitoresProceso>();
            for (MonitoresProceso monitoresProcesoCollectionMonitoresProcesoToAttach : procesoNegocio.getMonitoresProcesoCollection()) {
                monitoresProcesoCollectionMonitoresProcesoToAttach = em.getReference(monitoresProcesoCollectionMonitoresProcesoToAttach.getClass(), monitoresProcesoCollectionMonitoresProcesoToAttach.getId());
                attachedMonitoresProcesoCollection.add(monitoresProcesoCollectionMonitoresProcesoToAttach);
            }
            procesoNegocio.setMonitoresProcesoCollection(attachedMonitoresProcesoCollection);
            em.persist(procesoNegocio);
            if (siguienteProceso != null) {
                siguienteProceso.getProcesoNegocioCollection().add(procesoNegocio);
                siguienteProceso = em.merge(siguienteProceso);
            }
            if (creadoPor != null) {
                creadoPor.getProcesoNegocioCollection().add(procesoNegocio);
                creadoPor = em.merge(creadoPor);
            }
            if (modificadoPor != null) {
                modificadoPor.getProcesoNegocioCollection().add(procesoNegocio);
                modificadoPor = em.merge(modificadoPor);
            }
            for (ProcesoTipoDocumento procesoTipoDocumentoCollectionProcesoTipoDocumento : procesoNegocio.getProcesoTipoDocumentoCollection()) {
                ProcesoNegocio oldProcesoOfProcesoTipoDocumentoCollectionProcesoTipoDocumento = procesoTipoDocumentoCollectionProcesoTipoDocumento.getProceso();
                procesoTipoDocumentoCollectionProcesoTipoDocumento.setProceso(procesoNegocio);
                procesoTipoDocumentoCollectionProcesoTipoDocumento = em.merge(procesoTipoDocumentoCollectionProcesoTipoDocumento);
                if (oldProcesoOfProcesoTipoDocumentoCollectionProcesoTipoDocumento != null) {
                    oldProcesoOfProcesoTipoDocumentoCollectionProcesoTipoDocumento.getProcesoTipoDocumentoCollection().remove(procesoTipoDocumentoCollectionProcesoTipoDocumento);
                    oldProcesoOfProcesoTipoDocumentoCollectionProcesoTipoDocumento = em.merge(oldProcesoOfProcesoTipoDocumentoCollectionProcesoTipoDocumento);
                }
            }
            for (ProcesoNegocio procesoNegocioCollectionProcesoNegocio : procesoNegocio.getProcesoNegocioCollection()) {
                ProcesoNegocio oldSiguienteProcesoOfProcesoNegocioCollectionProcesoNegocio = procesoNegocioCollectionProcesoNegocio.getSiguienteProceso();
                procesoNegocioCollectionProcesoNegocio.setSiguienteProceso(procesoNegocio);
                procesoNegocioCollectionProcesoNegocio = em.merge(procesoNegocioCollectionProcesoNegocio);
                if (oldSiguienteProcesoOfProcesoNegocioCollectionProcesoNegocio != null) {
                    oldSiguienteProcesoOfProcesoNegocioCollectionProcesoNegocio.getProcesoNegocioCollection().remove(procesoNegocioCollectionProcesoNegocio);
                    oldSiguienteProcesoOfProcesoNegocioCollectionProcesoNegocio = em.merge(oldSiguienteProcesoOfProcesoNegocioCollectionProcesoNegocio);
                }
            }
            for (ProcesoDocumental procesoDocumentalCollectionProcesoDocumental : procesoNegocio.getProcesoDocumentalCollection()) {
                ProcesoNegocio oldProcesoOfProcesoDocumentalCollectionProcesoDocumental = procesoDocumentalCollectionProcesoDocumental.getProceso();
                procesoDocumentalCollectionProcesoDocumental.setProceso(procesoNegocio);
                procesoDocumentalCollectionProcesoDocumental = em.merge(procesoDocumentalCollectionProcesoDocumental);
                if (oldProcesoOfProcesoDocumentalCollectionProcesoDocumental != null) {
                    oldProcesoOfProcesoDocumentalCollectionProcesoDocumental.getProcesoDocumentalCollection().remove(procesoDocumentalCollectionProcesoDocumental);
                    oldProcesoOfProcesoDocumentalCollectionProcesoDocumental = em.merge(oldProcesoOfProcesoDocumentalCollectionProcesoDocumental);
                }
            }
            for (MonitoresProceso monitoresProcesoCollectionMonitoresProceso : procesoNegocio.getMonitoresProcesoCollection()) {
                ProcesoNegocio oldProcesoOfMonitoresProcesoCollectionMonitoresProceso = monitoresProcesoCollectionMonitoresProceso.getProceso();
                monitoresProcesoCollectionMonitoresProceso.setProceso(procesoNegocio);
                monitoresProcesoCollectionMonitoresProceso = em.merge(monitoresProcesoCollectionMonitoresProceso);
                if (oldProcesoOfMonitoresProcesoCollectionMonitoresProceso != null) {
                    oldProcesoOfMonitoresProcesoCollectionMonitoresProceso.getMonitoresProcesoCollection().remove(monitoresProcesoCollectionMonitoresProceso);
                    oldProcesoOfMonitoresProcesoCollectionMonitoresProceso = em.merge(oldProcesoOfMonitoresProcesoCollectionMonitoresProceso);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findProcesoNegocio(procesoNegocio.getId()) != null) {
                throw new PreexistingEntityException("ProcesoNegocio " + procesoNegocio + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ProcesoNegocio procesoNegocio) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ProcesoNegocio persistentProcesoNegocio = em.find(ProcesoNegocio.class, procesoNegocio.getId());
            ProcesoNegocio siguienteProcesoOld = persistentProcesoNegocio.getSiguienteProceso();
            ProcesoNegocio siguienteProcesoNew = procesoNegocio.getSiguienteProceso();
            Usuario creadoPorOld = persistentProcesoNegocio.getCreadoPor();
            Usuario creadoPorNew = procesoNegocio.getCreadoPor();
            Usuario modificadoPorOld = persistentProcesoNegocio.getModificadoPor();
            Usuario modificadoPorNew = procesoNegocio.getModificadoPor();
            Collection<ProcesoTipoDocumento> procesoTipoDocumentoCollectionOld = persistentProcesoNegocio.getProcesoTipoDocumentoCollection();
            Collection<ProcesoTipoDocumento> procesoTipoDocumentoCollectionNew = procesoNegocio.getProcesoTipoDocumentoCollection();
            Collection<ProcesoNegocio> procesoNegocioCollectionOld = persistentProcesoNegocio.getProcesoNegocioCollection();
            Collection<ProcesoNegocio> procesoNegocioCollectionNew = procesoNegocio.getProcesoNegocioCollection();
            Collection<ProcesoDocumental> procesoDocumentalCollectionOld = persistentProcesoNegocio.getProcesoDocumentalCollection();
            Collection<ProcesoDocumental> procesoDocumentalCollectionNew = procesoNegocio.getProcesoDocumentalCollection();
            Collection<MonitoresProceso> monitoresProcesoCollectionOld = persistentProcesoNegocio.getMonitoresProcesoCollection();
            Collection<MonitoresProceso> monitoresProcesoCollectionNew = procesoNegocio.getMonitoresProcesoCollection();
            if (siguienteProcesoNew != null) {
                siguienteProcesoNew = em.getReference(siguienteProcesoNew.getClass(), siguienteProcesoNew.getId());
                procesoNegocio.setSiguienteProceso(siguienteProcesoNew);
            }
            if (creadoPorNew != null) {
                creadoPorNew = em.getReference(creadoPorNew.getClass(), creadoPorNew.getId());
                procesoNegocio.setCreadoPor(creadoPorNew);
            }
            if (modificadoPorNew != null) {
                modificadoPorNew = em.getReference(modificadoPorNew.getClass(), modificadoPorNew.getId());
                procesoNegocio.setModificadoPor(modificadoPorNew);
            }
            Collection<ProcesoTipoDocumento> attachedProcesoTipoDocumentoCollectionNew = new ArrayList<ProcesoTipoDocumento>();
            for (ProcesoTipoDocumento procesoTipoDocumentoCollectionNewProcesoTipoDocumentoToAttach : procesoTipoDocumentoCollectionNew) {
                procesoTipoDocumentoCollectionNewProcesoTipoDocumentoToAttach = em.getReference(procesoTipoDocumentoCollectionNewProcesoTipoDocumentoToAttach.getClass(), procesoTipoDocumentoCollectionNewProcesoTipoDocumentoToAttach.getId());
                attachedProcesoTipoDocumentoCollectionNew.add(procesoTipoDocumentoCollectionNewProcesoTipoDocumentoToAttach);
            }
            procesoTipoDocumentoCollectionNew = attachedProcesoTipoDocumentoCollectionNew;
            procesoNegocio.setProcesoTipoDocumentoCollection(procesoTipoDocumentoCollectionNew);
            Collection<ProcesoNegocio> attachedProcesoNegocioCollectionNew = new ArrayList<ProcesoNegocio>();
            for (ProcesoNegocio procesoNegocioCollectionNewProcesoNegocioToAttach : procesoNegocioCollectionNew) {
                procesoNegocioCollectionNewProcesoNegocioToAttach = em.getReference(procesoNegocioCollectionNewProcesoNegocioToAttach.getClass(), procesoNegocioCollectionNewProcesoNegocioToAttach.getId());
                attachedProcesoNegocioCollectionNew.add(procesoNegocioCollectionNewProcesoNegocioToAttach);
            }
            procesoNegocioCollectionNew = attachedProcesoNegocioCollectionNew;
            procesoNegocio.setProcesoNegocioCollection(procesoNegocioCollectionNew);
            Collection<ProcesoDocumental> attachedProcesoDocumentalCollectionNew = new ArrayList<ProcesoDocumental>();
            for (ProcesoDocumental procesoDocumentalCollectionNewProcesoDocumentalToAttach : procesoDocumentalCollectionNew) {
                procesoDocumentalCollectionNewProcesoDocumentalToAttach = em.getReference(procesoDocumentalCollectionNewProcesoDocumentalToAttach.getClass(), procesoDocumentalCollectionNewProcesoDocumentalToAttach.getId());
                attachedProcesoDocumentalCollectionNew.add(procesoDocumentalCollectionNewProcesoDocumentalToAttach);
            }
            procesoDocumentalCollectionNew = attachedProcesoDocumentalCollectionNew;
            procesoNegocio.setProcesoDocumentalCollection(procesoDocumentalCollectionNew);
            Collection<MonitoresProceso> attachedMonitoresProcesoCollectionNew = new ArrayList<MonitoresProceso>();
            for (MonitoresProceso monitoresProcesoCollectionNewMonitoresProcesoToAttach : monitoresProcesoCollectionNew) {
                monitoresProcesoCollectionNewMonitoresProcesoToAttach = em.getReference(monitoresProcesoCollectionNewMonitoresProcesoToAttach.getClass(), monitoresProcesoCollectionNewMonitoresProcesoToAttach.getId());
                attachedMonitoresProcesoCollectionNew.add(monitoresProcesoCollectionNewMonitoresProcesoToAttach);
            }
            monitoresProcesoCollectionNew = attachedMonitoresProcesoCollectionNew;
            procesoNegocio.setMonitoresProcesoCollection(monitoresProcesoCollectionNew);
            procesoNegocio = em.merge(procesoNegocio);
            if (siguienteProcesoOld != null && !siguienteProcesoOld.equals(siguienteProcesoNew)) {
                siguienteProcesoOld.getProcesoNegocioCollection().remove(procesoNegocio);
                siguienteProcesoOld = em.merge(siguienteProcesoOld);
            }
            if (siguienteProcesoNew != null && !siguienteProcesoNew.equals(siguienteProcesoOld)) {
                siguienteProcesoNew.getProcesoNegocioCollection().add(procesoNegocio);
                siguienteProcesoNew = em.merge(siguienteProcesoNew);
            }
            if (creadoPorOld != null && !creadoPorOld.equals(creadoPorNew)) {
                creadoPorOld.getProcesoNegocioCollection().remove(procesoNegocio);
                creadoPorOld = em.merge(creadoPorOld);
            }
            if (creadoPorNew != null && !creadoPorNew.equals(creadoPorOld)) {
                creadoPorNew.getProcesoNegocioCollection().add(procesoNegocio);
                creadoPorNew = em.merge(creadoPorNew);
            }
            if (modificadoPorOld != null && !modificadoPorOld.equals(modificadoPorNew)) {
                modificadoPorOld.getProcesoNegocioCollection().remove(procesoNegocio);
                modificadoPorOld = em.merge(modificadoPorOld);
            }
            if (modificadoPorNew != null && !modificadoPorNew.equals(modificadoPorOld)) {
                modificadoPorNew.getProcesoNegocioCollection().add(procesoNegocio);
                modificadoPorNew = em.merge(modificadoPorNew);
            }
            for (ProcesoTipoDocumento procesoTipoDocumentoCollectionOldProcesoTipoDocumento : procesoTipoDocumentoCollectionOld) {
                if (!procesoTipoDocumentoCollectionNew.contains(procesoTipoDocumentoCollectionOldProcesoTipoDocumento)) {
                    procesoTipoDocumentoCollectionOldProcesoTipoDocumento.setProceso(null);
                    procesoTipoDocumentoCollectionOldProcesoTipoDocumento = em.merge(procesoTipoDocumentoCollectionOldProcesoTipoDocumento);
                }
            }
            for (ProcesoTipoDocumento procesoTipoDocumentoCollectionNewProcesoTipoDocumento : procesoTipoDocumentoCollectionNew) {
                if (!procesoTipoDocumentoCollectionOld.contains(procesoTipoDocumentoCollectionNewProcesoTipoDocumento)) {
                    ProcesoNegocio oldProcesoOfProcesoTipoDocumentoCollectionNewProcesoTipoDocumento = procesoTipoDocumentoCollectionNewProcesoTipoDocumento.getProceso();
                    procesoTipoDocumentoCollectionNewProcesoTipoDocumento.setProceso(procesoNegocio);
                    procesoTipoDocumentoCollectionNewProcesoTipoDocumento = em.merge(procesoTipoDocumentoCollectionNewProcesoTipoDocumento);
                    if (oldProcesoOfProcesoTipoDocumentoCollectionNewProcesoTipoDocumento != null && !oldProcesoOfProcesoTipoDocumentoCollectionNewProcesoTipoDocumento.equals(procesoNegocio)) {
                        oldProcesoOfProcesoTipoDocumentoCollectionNewProcesoTipoDocumento.getProcesoTipoDocumentoCollection().remove(procesoTipoDocumentoCollectionNewProcesoTipoDocumento);
                        oldProcesoOfProcesoTipoDocumentoCollectionNewProcesoTipoDocumento = em.merge(oldProcesoOfProcesoTipoDocumentoCollectionNewProcesoTipoDocumento);
                    }
                }
            }
            for (ProcesoNegocio procesoNegocioCollectionOldProcesoNegocio : procesoNegocioCollectionOld) {
                if (!procesoNegocioCollectionNew.contains(procesoNegocioCollectionOldProcesoNegocio)) {
                    procesoNegocioCollectionOldProcesoNegocio.setSiguienteProceso(null);
                    procesoNegocioCollectionOldProcesoNegocio = em.merge(procesoNegocioCollectionOldProcesoNegocio);
                }
            }
            for (ProcesoNegocio procesoNegocioCollectionNewProcesoNegocio : procesoNegocioCollectionNew) {
                if (!procesoNegocioCollectionOld.contains(procesoNegocioCollectionNewProcesoNegocio)) {
                    ProcesoNegocio oldSiguienteProcesoOfProcesoNegocioCollectionNewProcesoNegocio = procesoNegocioCollectionNewProcesoNegocio.getSiguienteProceso();
                    procesoNegocioCollectionNewProcesoNegocio.setSiguienteProceso(procesoNegocio);
                    procesoNegocioCollectionNewProcesoNegocio = em.merge(procesoNegocioCollectionNewProcesoNegocio);
                    if (oldSiguienteProcesoOfProcesoNegocioCollectionNewProcesoNegocio != null && !oldSiguienteProcesoOfProcesoNegocioCollectionNewProcesoNegocio.equals(procesoNegocio)) {
                        oldSiguienteProcesoOfProcesoNegocioCollectionNewProcesoNegocio.getProcesoNegocioCollection().remove(procesoNegocioCollectionNewProcesoNegocio);
                        oldSiguienteProcesoOfProcesoNegocioCollectionNewProcesoNegocio = em.merge(oldSiguienteProcesoOfProcesoNegocioCollectionNewProcesoNegocio);
                    }
                }
            }
            for (ProcesoDocumental procesoDocumentalCollectionOldProcesoDocumental : procesoDocumentalCollectionOld) {
                if (!procesoDocumentalCollectionNew.contains(procesoDocumentalCollectionOldProcesoDocumental)) {
                    procesoDocumentalCollectionOldProcesoDocumental.setProceso(null);
                    procesoDocumentalCollectionOldProcesoDocumental = em.merge(procesoDocumentalCollectionOldProcesoDocumental);
                }
            }
            for (ProcesoDocumental procesoDocumentalCollectionNewProcesoDocumental : procesoDocumentalCollectionNew) {
                if (!procesoDocumentalCollectionOld.contains(procesoDocumentalCollectionNewProcesoDocumental)) {
                    ProcesoNegocio oldProcesoOfProcesoDocumentalCollectionNewProcesoDocumental = procesoDocumentalCollectionNewProcesoDocumental.getProceso();
                    procesoDocumentalCollectionNewProcesoDocumental.setProceso(procesoNegocio);
                    procesoDocumentalCollectionNewProcesoDocumental = em.merge(procesoDocumentalCollectionNewProcesoDocumental);
                    if (oldProcesoOfProcesoDocumentalCollectionNewProcesoDocumental != null && !oldProcesoOfProcesoDocumentalCollectionNewProcesoDocumental.equals(procesoNegocio)) {
                        oldProcesoOfProcesoDocumentalCollectionNewProcesoDocumental.getProcesoDocumentalCollection().remove(procesoDocumentalCollectionNewProcesoDocumental);
                        oldProcesoOfProcesoDocumentalCollectionNewProcesoDocumental = em.merge(oldProcesoOfProcesoDocumentalCollectionNewProcesoDocumental);
                    }
                }
            }
            for (MonitoresProceso monitoresProcesoCollectionOldMonitoresProceso : monitoresProcesoCollectionOld) {
                if (!monitoresProcesoCollectionNew.contains(monitoresProcesoCollectionOldMonitoresProceso)) {
                    monitoresProcesoCollectionOldMonitoresProceso.setProceso(null);
                    monitoresProcesoCollectionOldMonitoresProceso = em.merge(monitoresProcesoCollectionOldMonitoresProceso);
                }
            }
            for (MonitoresProceso monitoresProcesoCollectionNewMonitoresProceso : monitoresProcesoCollectionNew) {
                if (!monitoresProcesoCollectionOld.contains(monitoresProcesoCollectionNewMonitoresProceso)) {
                    ProcesoNegocio oldProcesoOfMonitoresProcesoCollectionNewMonitoresProceso = monitoresProcesoCollectionNewMonitoresProceso.getProceso();
                    monitoresProcesoCollectionNewMonitoresProceso.setProceso(procesoNegocio);
                    monitoresProcesoCollectionNewMonitoresProceso = em.merge(monitoresProcesoCollectionNewMonitoresProceso);
                    if (oldProcesoOfMonitoresProcesoCollectionNewMonitoresProceso != null && !oldProcesoOfMonitoresProcesoCollectionNewMonitoresProceso.equals(procesoNegocio)) {
                        oldProcesoOfMonitoresProcesoCollectionNewMonitoresProceso.getMonitoresProcesoCollection().remove(monitoresProcesoCollectionNewMonitoresProceso);
                        oldProcesoOfMonitoresProcesoCollectionNewMonitoresProceso = em.merge(oldProcesoOfMonitoresProcesoCollectionNewMonitoresProceso);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = procesoNegocio.getId();
                if (findProcesoNegocio(id) == null) {
                    throw new NonexistentEntityException("The procesoNegocio with id " + id + " no longer exists.");
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
            ProcesoNegocio procesoNegocio;
            try {
                procesoNegocio = em.getReference(ProcesoNegocio.class, id);
                procesoNegocio.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The procesoNegocio with id " + id + " no longer exists.", enfe);
            }
            ProcesoNegocio siguienteProceso = procesoNegocio.getSiguienteProceso();
            if (siguienteProceso != null) {
                siguienteProceso.getProcesoNegocioCollection().remove(procesoNegocio);
                siguienteProceso = em.merge(siguienteProceso);
            }
            Usuario creadoPor = procesoNegocio.getCreadoPor();
            if (creadoPor != null) {
                creadoPor.getProcesoNegocioCollection().remove(procesoNegocio);
                creadoPor = em.merge(creadoPor);
            }
            Usuario modificadoPor = procesoNegocio.getModificadoPor();
            if (modificadoPor != null) {
                modificadoPor.getProcesoNegocioCollection().remove(procesoNegocio);
                modificadoPor = em.merge(modificadoPor);
            }
            Collection<ProcesoTipoDocumento> procesoTipoDocumentoCollection = procesoNegocio.getProcesoTipoDocumentoCollection();
            for (ProcesoTipoDocumento procesoTipoDocumentoCollectionProcesoTipoDocumento : procesoTipoDocumentoCollection) {
                procesoTipoDocumentoCollectionProcesoTipoDocumento.setProceso(null);
                procesoTipoDocumentoCollectionProcesoTipoDocumento = em.merge(procesoTipoDocumentoCollectionProcesoTipoDocumento);
            }
            Collection<ProcesoNegocio> procesoNegocioCollection = procesoNegocio.getProcesoNegocioCollection();
            for (ProcesoNegocio procesoNegocioCollectionProcesoNegocio : procesoNegocioCollection) {
                procesoNegocioCollectionProcesoNegocio.setSiguienteProceso(null);
                procesoNegocioCollectionProcesoNegocio = em.merge(procesoNegocioCollectionProcesoNegocio);
            }
            Collection<ProcesoDocumental> procesoDocumentalCollection = procesoNegocio.getProcesoDocumentalCollection();
            for (ProcesoDocumental procesoDocumentalCollectionProcesoDocumental : procesoDocumentalCollection) {
                procesoDocumentalCollectionProcesoDocumental.setProceso(null);
                procesoDocumentalCollectionProcesoDocumental = em.merge(procesoDocumentalCollectionProcesoDocumental);
            }
            Collection<MonitoresProceso> monitoresProcesoCollection = procesoNegocio.getMonitoresProcesoCollection();
            for (MonitoresProceso monitoresProcesoCollectionMonitoresProceso : monitoresProcesoCollection) {
                monitoresProcesoCollectionMonitoresProceso.setProceso(null);
                monitoresProcesoCollectionMonitoresProceso = em.merge(monitoresProcesoCollectionMonitoresProceso);
            }
            em.remove(procesoNegocio);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ProcesoNegocio> findProcesoNegocioEntities() {
        return findProcesoNegocioEntities(true, -1, -1);
    }

    public List<ProcesoNegocio> findProcesoNegocioEntities(int maxResults, int firstResult) {
        return findProcesoNegocioEntities(false, maxResults, firstResult);
    }

    private List<ProcesoNegocio> findProcesoNegocioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ProcesoNegocio.class));
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

    public ProcesoNegocio findProcesoNegocio(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ProcesoNegocio.class, id);
        } finally {
            em.close();
        }
    }

    public int getProcesoNegocioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ProcesoNegocio> rt = cq.from(ProcesoNegocio.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
