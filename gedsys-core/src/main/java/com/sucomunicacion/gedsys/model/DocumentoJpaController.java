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
import com.sucomunicacion.gedsys.entities.Usuario;
import com.sucomunicacion.gedsys.entities.Autor;
import com.sucomunicacion.gedsys.entities.ClaseDocumento;
import com.sucomunicacion.gedsys.entities.Corregimiento;
import com.sucomunicacion.gedsys.entities.Documento;
import com.sucomunicacion.gedsys.entities.Entidad;
import com.sucomunicacion.gedsys.entities.TipoDocumento;
import com.sucomunicacion.gedsys.entities.Municipio;
import com.sucomunicacion.gedsys.entities.Transportador;
import com.sucomunicacion.gedsys.entities.SignaturaTopografica;
import com.sucomunicacion.gedsys.entities.TipoDocumental;
import java.util.ArrayList;
import java.util.Collection;
import com.sucomunicacion.gedsys.entities.DestinatariosDoc;
import com.sucomunicacion.gedsys.entities.ProcesoDocumental;
import com.sucomunicacion.gedsys.model.exceptions.IllegalOrphanException;
import com.sucomunicacion.gedsys.model.exceptions.NonexistentEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author rober
 */
public class DocumentoJpaController implements Serializable {

    public DocumentoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Documento documento) {
        if (documento.getDocumentoCollection() == null) {
            documento.setDocumentoCollection(new ArrayList<Documento>());
        }
        if (documento.getDestinatariosDocCollection() == null) {
            documento.setDestinatariosDocCollection(new ArrayList<DestinatariosDoc>());
        }
        if (documento.getProcesodocumentalCollection() == null) {
            documento.setProcesodocumentalCollection(new ArrayList<ProcesoDocumental>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario creadoPor = documento.getCreadoPor();
            if (creadoPor != null) {
                creadoPor = em.getReference(creadoPor.getClass(), creadoPor.getId());
                documento.setCreadoPor(creadoPor);
            }
            Autor autor = documento.getAutor();
            if (autor != null) {
                autor = em.getReference(autor.getClass(), autor.getId());
                documento.setAutor(autor);
            }
            ClaseDocumento claseDocumento = documento.getClaseDocumento();
            if (claseDocumento != null) {
                claseDocumento = em.getReference(claseDocumento.getClass(), claseDocumento.getId());
                documento.setClaseDocumento(claseDocumento);
            }
            Corregimiento corregimiento = documento.getCorregimiento();
            if (corregimiento != null) {
                corregimiento = em.getReference(corregimiento.getClass(), corregimiento.getId());
                documento.setCorregimiento(corregimiento);
            }
            Documento documentoRelacionado = documento.getDocumentoRelacionado();
            if (documentoRelacionado != null) {
                documentoRelacionado = em.getReference(documentoRelacionado.getClass(), documentoRelacionado.getId());
                documento.setDocumentoRelacionado(documentoRelacionado);
            }
            Entidad entidad = documento.getEntidad();
            if (entidad != null) {
                entidad = em.getReference(entidad.getClass(), entidad.getId());
                documento.setEntidad(entidad);
            }
            Usuario modificadoPor = documento.getModificadoPor();
            if (modificadoPor != null) {
                modificadoPor = em.getReference(modificadoPor.getClass(), modificadoPor.getId());
                documento.setModificadoPor(modificadoPor);
            }
            TipoDocumento tipoDocumento = documento.getTipoDocumento();
            if (tipoDocumento != null) {
                tipoDocumento = em.getReference(tipoDocumento.getClass(), tipoDocumento.getId());
                documento.setTipoDocumento(tipoDocumento);
            }
            Municipio municipio = documento.getMunicipio();
            if (municipio != null) {
                municipio = em.getReference(municipio.getClass(), municipio.getId());
                documento.setMunicipio(municipio);
            }
            Transportador transportador = documento.getTransportador();
            if (transportador != null) {
                transportador = em.getReference(transportador.getClass(), transportador.getId());
                documento.setTransportador(transportador);
            }
            SignaturaTopografica signaturaTopografica = documento.getSignaturaTopografica();
            if (signaturaTopografica != null) {
                signaturaTopografica = em.getReference(signaturaTopografica.getClass(), signaturaTopografica.getId());
                documento.setSignaturaTopografica(signaturaTopografica);
            }
            TipoDocumental tipoDocumental = documento.getTipoDocumental();
            if (tipoDocumental != null) {
                tipoDocumental = em.getReference(tipoDocumental.getClass(), tipoDocumental.getId());
                documento.setTipoDocumental(tipoDocumental);
            }
            Collection<Documento> attachedDocumentoCollection = new ArrayList<Documento>();
            for (Documento documentoCollectionDocumentoToAttach : documento.getDocumentoCollection()) {
                documentoCollectionDocumentoToAttach = em.getReference(documentoCollectionDocumentoToAttach.getClass(), documentoCollectionDocumentoToAttach.getId());
                attachedDocumentoCollection.add(documentoCollectionDocumentoToAttach);
            }
            documento.setDocumentoCollection(attachedDocumentoCollection);
            Collection<DestinatariosDoc> attachedDestinatariosDocCollection = new ArrayList<DestinatariosDoc>();
            for (DestinatariosDoc destinatariosDocCollectionDestinatariosDocToAttach : documento.getDestinatariosDocCollection()) {
                destinatariosDocCollectionDestinatariosDocToAttach = em.getReference(destinatariosDocCollectionDestinatariosDocToAttach.getClass(), destinatariosDocCollectionDestinatariosDocToAttach.getId());
                attachedDestinatariosDocCollection.add(destinatariosDocCollectionDestinatariosDocToAttach);
            }
            documento.setDestinatariosDocCollection(attachedDestinatariosDocCollection);
            Collection<ProcesoDocumental> attachedProcesodocumentalCollection = new ArrayList<ProcesoDocumental>();
            for (ProcesoDocumental procesodocumentalCollectionProcesoDocumentalToAttach : documento.getProcesodocumentalCollection()) {
                procesodocumentalCollectionProcesoDocumentalToAttach = em.getReference(procesodocumentalCollectionProcesoDocumentalToAttach.getClass(), procesodocumentalCollectionProcesoDocumentalToAttach.getId());
                attachedProcesodocumentalCollection.add(procesodocumentalCollectionProcesoDocumentalToAttach);
            }
            documento.setProcesodocumentalCollection(attachedProcesodocumentalCollection);
            em.persist(documento);
            if (creadoPor != null) {
                creadoPor.getDocumentoCollection().add(documento);
                creadoPor = em.merge(creadoPor);
            }
            if (autor != null) {
                autor.getDocumentoCollection().add(documento);
                autor = em.merge(autor);
            }
            if (claseDocumento != null) {
                claseDocumento.getDocumentoCollection().add(documento);
                claseDocumento = em.merge(claseDocumento);
            }
            if (corregimiento != null) {
                corregimiento.getDocumentoCollection().add(documento);
                corregimiento = em.merge(corregimiento);
            }
            if (documentoRelacionado != null) {
                documentoRelacionado.getDocumentoCollection().add(documento);
                documentoRelacionado = em.merge(documentoRelacionado);
            }
            if (entidad != null) {
                entidad.getDocumentoCollection().add(documento);
                entidad = em.merge(entidad);
            }
            if (modificadoPor != null) {
                modificadoPor.getDocumentoCollection().add(documento);
                modificadoPor = em.merge(modificadoPor);
            }
            if (tipoDocumento != null) {
                tipoDocumento.getDocumentoCollection().add(documento);
                tipoDocumento = em.merge(tipoDocumento);
            }
            if (municipio != null) {
                municipio.getDocumentoCollection().add(documento);
                municipio = em.merge(municipio);
            }
            if (transportador != null) {
                transportador.getDocumentoCollection().add(documento);
                transportador = em.merge(transportador);
            }
            if (signaturaTopografica != null) {
                signaturaTopografica.getDocumentoCollection().add(documento);
                signaturaTopografica = em.merge(signaturaTopografica);
            }
            if (tipoDocumental != null) {
                tipoDocumental.getDocumentoCollection().add(documento);
                tipoDocumental = em.merge(tipoDocumental);
            }
            for (Documento documentoCollectionDocumento : documento.getDocumentoCollection()) {
                Documento oldDocumentoRelacionadoOfDocumentoCollectionDocumento = documentoCollectionDocumento.getDocumentoRelacionado();
                documentoCollectionDocumento.setDocumentoRelacionado(documento);
                documentoCollectionDocumento = em.merge(documentoCollectionDocumento);
                if (oldDocumentoRelacionadoOfDocumentoCollectionDocumento != null) {
                    oldDocumentoRelacionadoOfDocumentoCollectionDocumento.getDocumentoCollection().remove(documentoCollectionDocumento);
                    oldDocumentoRelacionadoOfDocumentoCollectionDocumento = em.merge(oldDocumentoRelacionadoOfDocumentoCollectionDocumento);
                }
            }
            for (DestinatariosDoc destinatariosDocCollectionDestinatariosDoc : documento.getDestinatariosDocCollection()) {
                Documento oldDocumentoIdOfDestinatariosDocCollectionDestinatariosDoc = destinatariosDocCollectionDestinatariosDoc.getDocumentoId();
                destinatariosDocCollectionDestinatariosDoc.setDocumentoId(documento);
                destinatariosDocCollectionDestinatariosDoc = em.merge(destinatariosDocCollectionDestinatariosDoc);
                if (oldDocumentoIdOfDestinatariosDocCollectionDestinatariosDoc != null) {
                    oldDocumentoIdOfDestinatariosDocCollectionDestinatariosDoc.getDestinatariosDocCollection().remove(destinatariosDocCollectionDestinatariosDoc);
                    oldDocumentoIdOfDestinatariosDocCollectionDestinatariosDoc = em.merge(oldDocumentoIdOfDestinatariosDocCollectionDestinatariosDoc);
                }
            }
            for (ProcesoDocumental procesodocumentalCollectionProcesoDocumental : documento.getProcesodocumentalCollection()) {
                Documento oldDocumentoOfProcesodocumentalCollectionProcesoDocumental = procesodocumentalCollectionProcesoDocumental.getDocumento();
                procesodocumentalCollectionProcesoDocumental.setDocumento(documento);
                procesodocumentalCollectionProcesoDocumental = em.merge(procesodocumentalCollectionProcesoDocumental);
                if (oldDocumentoOfProcesodocumentalCollectionProcesoDocumental != null) {
                    oldDocumentoOfProcesodocumentalCollectionProcesoDocumental.getProcesodocumentalCollection().remove(procesodocumentalCollectionProcesoDocumental);
                    oldDocumentoOfProcesodocumentalCollectionProcesoDocumental = em.merge(oldDocumentoOfProcesodocumentalCollectionProcesoDocumental);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Documento documento) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Documento persistentDocumento = em.find(Documento.class, documento.getId());
            Usuario creadoPorOld = persistentDocumento.getCreadoPor();
            Usuario creadoPorNew = documento.getCreadoPor();
            Autor autorOld = persistentDocumento.getAutor();
            Autor autorNew = documento.getAutor();
            ClaseDocumento claseDocumentoOld = persistentDocumento.getClaseDocumento();
            ClaseDocumento claseDocumentoNew = documento.getClaseDocumento();
            Corregimiento corregimientoOld = persistentDocumento.getCorregimiento();
            Corregimiento corregimientoNew = documento.getCorregimiento();
            Documento documentoRelacionadoOld = persistentDocumento.getDocumentoRelacionado();
            Documento documentoRelacionadoNew = documento.getDocumentoRelacionado();
            Entidad entidadOld = persistentDocumento.getEntidad();
            Entidad entidadNew = documento.getEntidad();
            Usuario modificadoPorOld = persistentDocumento.getModificadoPor();
            Usuario modificadoPorNew = documento.getModificadoPor();
            TipoDocumento tipoDocumentoOld = persistentDocumento.getTipoDocumento();
            TipoDocumento tipoDocumentoNew = documento.getTipoDocumento();
            Municipio municipioOld = persistentDocumento.getMunicipio();
            Municipio municipioNew = documento.getMunicipio();
            Transportador transportadorOld = persistentDocumento.getTransportador();
            Transportador transportadorNew = documento.getTransportador();
            SignaturaTopografica signaturaTopograficaOld = persistentDocumento.getSignaturaTopografica();
            SignaturaTopografica signaturaTopograficaNew = documento.getSignaturaTopografica();
            TipoDocumental tipoDocumentalOld = persistentDocumento.getTipoDocumental();
            TipoDocumental tipoDocumentalNew = documento.getTipoDocumental();
            Collection<Documento> documentoCollectionOld = persistentDocumento.getDocumentoCollection();
            Collection<Documento> documentoCollectionNew = documento.getDocumentoCollection();
            Collection<DestinatariosDoc> destinatariosDocCollectionOld = persistentDocumento.getDestinatariosDocCollection();
            Collection<DestinatariosDoc> destinatariosDocCollectionNew = documento.getDestinatariosDocCollection();
            Collection<ProcesoDocumental> procesodocumentalCollectionOld = persistentDocumento.getProcesodocumentalCollection();
            Collection<ProcesoDocumental> procesodocumentalCollectionNew = documento.getProcesodocumentalCollection();
            List<String> illegalOrphanMessages = null;
            for (DestinatariosDoc destinatariosDocCollectionOldDestinatariosDoc : destinatariosDocCollectionOld) {
                if (!destinatariosDocCollectionNew.contains(destinatariosDocCollectionOldDestinatariosDoc)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain DestinatariosDoc " + destinatariosDocCollectionOldDestinatariosDoc + " since its documentoId field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (creadoPorNew != null) {
                creadoPorNew = em.getReference(creadoPorNew.getClass(), creadoPorNew.getId());
                documento.setCreadoPor(creadoPorNew);
            }
            if (autorNew != null) {
                autorNew = em.getReference(autorNew.getClass(), autorNew.getId());
                documento.setAutor(autorNew);
            }
            if (claseDocumentoNew != null) {
                claseDocumentoNew = em.getReference(claseDocumentoNew.getClass(), claseDocumentoNew.getId());
                documento.setClaseDocumento(claseDocumentoNew);
            }
            if (corregimientoNew != null) {
                corregimientoNew = em.getReference(corregimientoNew.getClass(), corregimientoNew.getId());
                documento.setCorregimiento(corregimientoNew);
            }
            if (documentoRelacionadoNew != null) {
                documentoRelacionadoNew = em.getReference(documentoRelacionadoNew.getClass(), documentoRelacionadoNew.getId());
                documento.setDocumentoRelacionado(documentoRelacionadoNew);
            }
            if (entidadNew != null) {
                entidadNew = em.getReference(entidadNew.getClass(), entidadNew.getId());
                documento.setEntidad(entidadNew);
            }
            if (modificadoPorNew != null) {
                modificadoPorNew = em.getReference(modificadoPorNew.getClass(), modificadoPorNew.getId());
                documento.setModificadoPor(modificadoPorNew);
            }
            if (tipoDocumentoNew != null) {
                tipoDocumentoNew = em.getReference(tipoDocumentoNew.getClass(), tipoDocumentoNew.getId());
                documento.setTipoDocumento(tipoDocumentoNew);
            }
            if (municipioNew != null) {
                municipioNew = em.getReference(municipioNew.getClass(), municipioNew.getId());
                documento.setMunicipio(municipioNew);
            }
            if (transportadorNew != null) {
                transportadorNew = em.getReference(transportadorNew.getClass(), transportadorNew.getId());
                documento.setTransportador(transportadorNew);
            }
            if (signaturaTopograficaNew != null) {
                signaturaTopograficaNew = em.getReference(signaturaTopograficaNew.getClass(), signaturaTopograficaNew.getId());
                documento.setSignaturaTopografica(signaturaTopograficaNew);
            }
            if (tipoDocumentalNew != null) {
                tipoDocumentalNew = em.getReference(tipoDocumentalNew.getClass(), tipoDocumentalNew.getId());
                documento.setTipoDocumental(tipoDocumentalNew);
            }
            Collection<Documento> attachedDocumentoCollectionNew = new ArrayList<Documento>();
            for (Documento documentoCollectionNewDocumentoToAttach : documentoCollectionNew) {
                documentoCollectionNewDocumentoToAttach = em.getReference(documentoCollectionNewDocumentoToAttach.getClass(), documentoCollectionNewDocumentoToAttach.getId());
                attachedDocumentoCollectionNew.add(documentoCollectionNewDocumentoToAttach);
            }
            documentoCollectionNew = attachedDocumentoCollectionNew;
            documento.setDocumentoCollection(documentoCollectionNew);
            Collection<DestinatariosDoc> attachedDestinatariosDocCollectionNew = new ArrayList<DestinatariosDoc>();
            for (DestinatariosDoc destinatariosDocCollectionNewDestinatariosDocToAttach : destinatariosDocCollectionNew) {
                destinatariosDocCollectionNewDestinatariosDocToAttach = em.getReference(destinatariosDocCollectionNewDestinatariosDocToAttach.getClass(), destinatariosDocCollectionNewDestinatariosDocToAttach.getId());
                attachedDestinatariosDocCollectionNew.add(destinatariosDocCollectionNewDestinatariosDocToAttach);
            }
            destinatariosDocCollectionNew = attachedDestinatariosDocCollectionNew;
            documento.setDestinatariosDocCollection(destinatariosDocCollectionNew);
            Collection<ProcesoDocumental> attachedProcesodocumentalCollectionNew = new ArrayList<ProcesoDocumental>();
            for (ProcesoDocumental procesodocumentalCollectionNewProcesoDocumentalToAttach : procesodocumentalCollectionNew) {
                procesodocumentalCollectionNewProcesoDocumentalToAttach = em.getReference(procesodocumentalCollectionNewProcesoDocumentalToAttach.getClass(), procesodocumentalCollectionNewProcesoDocumentalToAttach.getId());
                attachedProcesodocumentalCollectionNew.add(procesodocumentalCollectionNewProcesoDocumentalToAttach);
            }
            procesodocumentalCollectionNew = attachedProcesodocumentalCollectionNew;
            documento.setProcesodocumentalCollection(procesodocumentalCollectionNew);
            documento = em.merge(documento);
            if (creadoPorOld != null && !creadoPorOld.equals(creadoPorNew)) {
                creadoPorOld.getDocumentoCollection().remove(documento);
                creadoPorOld = em.merge(creadoPorOld);
            }
            if (creadoPorNew != null && !creadoPorNew.equals(creadoPorOld)) {
                creadoPorNew.getDocumentoCollection().add(documento);
                creadoPorNew = em.merge(creadoPorNew);
            }
            if (autorOld != null && !autorOld.equals(autorNew)) {
                autorOld.getDocumentoCollection().remove(documento);
                autorOld = em.merge(autorOld);
            }
            if (autorNew != null && !autorNew.equals(autorOld)) {
                autorNew.getDocumentoCollection().add(documento);
                autorNew = em.merge(autorNew);
            }
            if (claseDocumentoOld != null && !claseDocumentoOld.equals(claseDocumentoNew)) {
                claseDocumentoOld.getDocumentoCollection().remove(documento);
                claseDocumentoOld = em.merge(claseDocumentoOld);
            }
            if (claseDocumentoNew != null && !claseDocumentoNew.equals(claseDocumentoOld)) {
                claseDocumentoNew.getDocumentoCollection().add(documento);
                claseDocumentoNew = em.merge(claseDocumentoNew);
            }
            if (corregimientoOld != null && !corregimientoOld.equals(corregimientoNew)) {
                corregimientoOld.getDocumentoCollection().remove(documento);
                corregimientoOld = em.merge(corregimientoOld);
            }
            if (corregimientoNew != null && !corregimientoNew.equals(corregimientoOld)) {
                corregimientoNew.getDocumentoCollection().add(documento);
                corregimientoNew = em.merge(corregimientoNew);
            }
            if (documentoRelacionadoOld != null && !documentoRelacionadoOld.equals(documentoRelacionadoNew)) {
                documentoRelacionadoOld.getDocumentoCollection().remove(documento);
                documentoRelacionadoOld = em.merge(documentoRelacionadoOld);
            }
            if (documentoRelacionadoNew != null && !documentoRelacionadoNew.equals(documentoRelacionadoOld)) {
                documentoRelacionadoNew.getDocumentoCollection().add(documento);
                documentoRelacionadoNew = em.merge(documentoRelacionadoNew);
            }
            if (entidadOld != null && !entidadOld.equals(entidadNew)) {
                entidadOld.getDocumentoCollection().remove(documento);
                entidadOld = em.merge(entidadOld);
            }
            if (entidadNew != null && !entidadNew.equals(entidadOld)) {
                entidadNew.getDocumentoCollection().add(documento);
                entidadNew = em.merge(entidadNew);
            }
            if (modificadoPorOld != null && !modificadoPorOld.equals(modificadoPorNew)) {
                modificadoPorOld.getDocumentoCollection().remove(documento);
                modificadoPorOld = em.merge(modificadoPorOld);
            }
            if (modificadoPorNew != null && !modificadoPorNew.equals(modificadoPorOld)) {
                modificadoPorNew.getDocumentoCollection().add(documento);
                modificadoPorNew = em.merge(modificadoPorNew);
            }
            if (tipoDocumentoOld != null && !tipoDocumentoOld.equals(tipoDocumentoNew)) {
                tipoDocumentoOld.getDocumentoCollection().remove(documento);
                tipoDocumentoOld = em.merge(tipoDocumentoOld);
            }
            if (tipoDocumentoNew != null && !tipoDocumentoNew.equals(tipoDocumentoOld)) {
                tipoDocumentoNew.getDocumentoCollection().add(documento);
                tipoDocumentoNew = em.merge(tipoDocumentoNew);
            }
            if (municipioOld != null && !municipioOld.equals(municipioNew)) {
                municipioOld.getDocumentoCollection().remove(documento);
                municipioOld = em.merge(municipioOld);
            }
            if (municipioNew != null && !municipioNew.equals(municipioOld)) {
                municipioNew.getDocumentoCollection().add(documento);
                municipioNew = em.merge(municipioNew);
            }
            if (transportadorOld != null && !transportadorOld.equals(transportadorNew)) {
                transportadorOld.getDocumentoCollection().remove(documento);
                transportadorOld = em.merge(transportadorOld);
            }
            if (transportadorNew != null && !transportadorNew.equals(transportadorOld)) {
                transportadorNew.getDocumentoCollection().add(documento);
                transportadorNew = em.merge(transportadorNew);
            }
            if (signaturaTopograficaOld != null && !signaturaTopograficaOld.equals(signaturaTopograficaNew)) {
                signaturaTopograficaOld.getDocumentoCollection().remove(documento);
                signaturaTopograficaOld = em.merge(signaturaTopograficaOld);
            }
            if (signaturaTopograficaNew != null && !signaturaTopograficaNew.equals(signaturaTopograficaOld)) {
                signaturaTopograficaNew.getDocumentoCollection().add(documento);
                signaturaTopograficaNew = em.merge(signaturaTopograficaNew);
            }
            if (tipoDocumentalOld != null && !tipoDocumentalOld.equals(tipoDocumentalNew)) {
                tipoDocumentalOld.getDocumentoCollection().remove(documento);
                tipoDocumentalOld = em.merge(tipoDocumentalOld);
            }
            if (tipoDocumentalNew != null && !tipoDocumentalNew.equals(tipoDocumentalOld)) {
                tipoDocumentalNew.getDocumentoCollection().add(documento);
                tipoDocumentalNew = em.merge(tipoDocumentalNew);
            }
            for (Documento documentoCollectionOldDocumento : documentoCollectionOld) {
                if (!documentoCollectionNew.contains(documentoCollectionOldDocumento)) {
                    documentoCollectionOldDocumento.setDocumentoRelacionado(null);
                    documentoCollectionOldDocumento = em.merge(documentoCollectionOldDocumento);
                }
            }
            for (Documento documentoCollectionNewDocumento : documentoCollectionNew) {
                if (!documentoCollectionOld.contains(documentoCollectionNewDocumento)) {
                    Documento oldDocumentoRelacionadoOfDocumentoCollectionNewDocumento = documentoCollectionNewDocumento.getDocumentoRelacionado();
                    documentoCollectionNewDocumento.setDocumentoRelacionado(documento);
                    documentoCollectionNewDocumento = em.merge(documentoCollectionNewDocumento);
                    if (oldDocumentoRelacionadoOfDocumentoCollectionNewDocumento != null && !oldDocumentoRelacionadoOfDocumentoCollectionNewDocumento.equals(documento)) {
                        oldDocumentoRelacionadoOfDocumentoCollectionNewDocumento.getDocumentoCollection().remove(documentoCollectionNewDocumento);
                        oldDocumentoRelacionadoOfDocumentoCollectionNewDocumento = em.merge(oldDocumentoRelacionadoOfDocumentoCollectionNewDocumento);
                    }
                }
            }
            for (DestinatariosDoc destinatariosDocCollectionNewDestinatariosDoc : destinatariosDocCollectionNew) {
                if (!destinatariosDocCollectionOld.contains(destinatariosDocCollectionNewDestinatariosDoc)) {
                    Documento oldDocumentoIdOfDestinatariosDocCollectionNewDestinatariosDoc = destinatariosDocCollectionNewDestinatariosDoc.getDocumentoId();
                    destinatariosDocCollectionNewDestinatariosDoc.setDocumentoId(documento);
                    destinatariosDocCollectionNewDestinatariosDoc = em.merge(destinatariosDocCollectionNewDestinatariosDoc);
                    if (oldDocumentoIdOfDestinatariosDocCollectionNewDestinatariosDoc != null && !oldDocumentoIdOfDestinatariosDocCollectionNewDestinatariosDoc.equals(documento)) {
                        oldDocumentoIdOfDestinatariosDocCollectionNewDestinatariosDoc.getDestinatariosDocCollection().remove(destinatariosDocCollectionNewDestinatariosDoc);
                        oldDocumentoIdOfDestinatariosDocCollectionNewDestinatariosDoc = em.merge(oldDocumentoIdOfDestinatariosDocCollectionNewDestinatariosDoc);
                    }
                }
            }
            for (ProcesoDocumental procesodocumentalCollectionOldProcesoDocumental : procesodocumentalCollectionOld) {
                if (!procesodocumentalCollectionNew.contains(procesodocumentalCollectionOldProcesoDocumental)) {
                    procesodocumentalCollectionOldProcesoDocumental.setDocumento(null);
                    procesodocumentalCollectionOldProcesoDocumental = em.merge(procesodocumentalCollectionOldProcesoDocumental);
                }
            }
            for (ProcesoDocumental procesodocumentalCollectionNewProcesoDocumental : procesodocumentalCollectionNew) {
                if (!procesodocumentalCollectionOld.contains(procesodocumentalCollectionNewProcesoDocumental)) {
                    Documento oldDocumentoOfProcesodocumentalCollectionNewProcesoDocumental = procesodocumentalCollectionNewProcesoDocumental.getDocumento();
                    procesodocumentalCollectionNewProcesoDocumental.setDocumento(documento);
                    procesodocumentalCollectionNewProcesoDocumental = em.merge(procesodocumentalCollectionNewProcesoDocumental);
                    if (oldDocumentoOfProcesodocumentalCollectionNewProcesoDocumental != null && !oldDocumentoOfProcesodocumentalCollectionNewProcesoDocumental.equals(documento)) {
                        oldDocumentoOfProcesodocumentalCollectionNewProcesoDocumental.getProcesodocumentalCollection().remove(procesodocumentalCollectionNewProcesoDocumental);
                        oldDocumentoOfProcesodocumentalCollectionNewProcesoDocumental = em.merge(oldDocumentoOfProcesodocumentalCollectionNewProcesoDocumental);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = documento.getId();
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

    public void destroy(Long id) throws IllegalOrphanException, NonexistentEntityException {
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
            List<String> illegalOrphanMessages = null;
            Collection<DestinatariosDoc> destinatariosDocCollectionOrphanCheck = documento.getDestinatariosDocCollection();
            for (DestinatariosDoc destinatariosDocCollectionOrphanCheckDestinatariosDoc : destinatariosDocCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Documento (" + documento + ") cannot be destroyed since the DestinatariosDoc " + destinatariosDocCollectionOrphanCheckDestinatariosDoc + " in its destinatariosDocCollection field has a non-nullable documentoId field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Usuario creadoPor = documento.getCreadoPor();
            if (creadoPor != null) {
                creadoPor.getDocumentoCollection().remove(documento);
                creadoPor = em.merge(creadoPor);
            }
            Autor autor = documento.getAutor();
            if (autor != null) {
                autor.getDocumentoCollection().remove(documento);
                autor = em.merge(autor);
            }
            ClaseDocumento claseDocumento = documento.getClaseDocumento();
            if (claseDocumento != null) {
                claseDocumento.getDocumentoCollection().remove(documento);
                claseDocumento = em.merge(claseDocumento);
            }
            Corregimiento corregimiento = documento.getCorregimiento();
            if (corregimiento != null) {
                corregimiento.getDocumentoCollection().remove(documento);
                corregimiento = em.merge(corregimiento);
            }
            Documento documentoRelacionado = documento.getDocumentoRelacionado();
            if (documentoRelacionado != null) {
                documentoRelacionado.getDocumentoCollection().remove(documento);
                documentoRelacionado = em.merge(documentoRelacionado);
            }
            Entidad entidad = documento.getEntidad();
            if (entidad != null) {
                entidad.getDocumentoCollection().remove(documento);
                entidad = em.merge(entidad);
            }
            Usuario modificadoPor = documento.getModificadoPor();
            if (modificadoPor != null) {
                modificadoPor.getDocumentoCollection().remove(documento);
                modificadoPor = em.merge(modificadoPor);
            }
            TipoDocumento tipoDocumento = documento.getTipoDocumento();
            if (tipoDocumento != null) {
                tipoDocumento.getDocumentoCollection().remove(documento);
                tipoDocumento = em.merge(tipoDocumento);
            }
            Municipio municipio = documento.getMunicipio();
            if (municipio != null) {
                municipio.getDocumentoCollection().remove(documento);
                municipio = em.merge(municipio);
            }
            Transportador transportador = documento.getTransportador();
            if (transportador != null) {
                transportador.getDocumentoCollection().remove(documento);
                transportador = em.merge(transportador);
            }
            SignaturaTopografica signaturaTopografica = documento.getSignaturaTopografica();
            if (signaturaTopografica != null) {
                signaturaTopografica.getDocumentoCollection().remove(documento);
                signaturaTopografica = em.merge(signaturaTopografica);
            }
            TipoDocumental tipoDocumental = documento.getTipoDocumental();
            if (tipoDocumental != null) {
                tipoDocumental.getDocumentoCollection().remove(documento);
                tipoDocumental = em.merge(tipoDocumental);
            }
            Collection<Documento> documentoCollection = documento.getDocumentoCollection();
            for (Documento documentoCollectionDocumento : documentoCollection) {
                documentoCollectionDocumento.setDocumentoRelacionado(null);
                documentoCollectionDocumento = em.merge(documentoCollectionDocumento);
            }
            Collection<ProcesoDocumental> procesodocumentalCollection = documento.getProcesodocumentalCollection();
            for (ProcesoDocumental procesodocumentalCollectionProcesoDocumental : procesodocumentalCollection) {
                procesodocumentalCollectionProcesoDocumental.setDocumento(null);
                procesodocumentalCollectionProcesoDocumental = em.merge(procesodocumentalCollectionProcesoDocumental);
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

    public Documento findDocumento(Long id) {
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
