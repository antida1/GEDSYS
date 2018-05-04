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
import com.base16.gedsys.entities.Autor;
import com.base16.gedsys.entities.ClaseDocumento;
import com.base16.gedsys.entities.Corregimiento;
import com.base16.gedsys.entities.Documento;
import com.base16.gedsys.entities.Entidad;
import com.base16.gedsys.entities.TipoDocumento;
import com.base16.gedsys.entities.Municipio;
import com.base16.gedsys.entities.Transportador;
import com.base16.gedsys.entities.SignaturaTopografica;
import com.base16.gedsys.entities.TipoDocumental;
import com.base16.gedsys.entities.Mediorecepcion;
import java.util.ArrayList;
import java.util.Collection;
import com.base16.gedsys.entities.DestinatariosDoc;
import com.base16.gedsys.entities.ProcesoDocumental;
import com.base16.gedsys.entities.Comentario;
import com.base16.gedsys.entities.Documento;
import com.base16.gedsys.entities.Documento_;
import com.base16.gedsys.entities.Usuario;
import com.base16.gedsys.model.exceptions.IllegalOrphanException;
import com.base16.gedsys.model.exceptions.NonexistentEntityException;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import org.eclipse.persistence.jpa.JpaQuery;

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
        if (documento.getComentarioList() == null) {
            documento.setComentarioList(new ArrayList<Comentario>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario destinatario = documento.getDestinatario();
            if (destinatario != null) {
                destinatario = em.getReference(destinatario.getClass(), destinatario.getId());
                documento.setDestinatario(destinatario);
            }
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
            Mediorecepcion medioEnvio = documento.getMedioEnvio();
            if (medioEnvio != null) {
                medioEnvio = em.getReference(medioEnvio.getClass(), medioEnvio.getId());
                documento.setMedioEnvio(medioEnvio);
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
            List<Comentario> attachedComentarioList = new ArrayList<Comentario>();
            for (Comentario comentarioListComentarioToAttach : documento.getComentarioList()) {
                comentarioListComentarioToAttach = em.getReference(comentarioListComentarioToAttach.getClass(), comentarioListComentarioToAttach.getId());
                attachedComentarioList.add(comentarioListComentarioToAttach);
            }
            documento.setComentarioList(attachedComentarioList);
            em.persist(documento);
            if (destinatario != null) {
                destinatario.getDocumentoCollection().add(documento);
                destinatario = em.merge(destinatario);
            }
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
            if (medioEnvio != null) {
                medioEnvio.getDocumentoList().add(documento);
                medioEnvio = em.merge(medioEnvio);
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
            for (Comentario comentarioListComentario : documento.getComentarioList()) {
                Documento oldDocumentoOfComentarioListComentario = comentarioListComentario.getDocumento();
                comentarioListComentario.setDocumento(documento);
                comentarioListComentario = em.merge(comentarioListComentario);
                if (oldDocumentoOfComentarioListComentario != null) {
                    oldDocumentoOfComentarioListComentario.getComentarioList().remove(comentarioListComentario);
                    oldDocumentoOfComentarioListComentario = em.merge(oldDocumentoOfComentarioListComentario);
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
            Usuario destinatarioOld = persistentDocumento.getDestinatario();
            Usuario destinatarioNew = documento.getDestinatario();
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
            Mediorecepcion medioEnvioOld = persistentDocumento.getMedioEnvio();
            Mediorecepcion medioEnvioNew = documento.getMedioEnvio();
            Collection<Documento> documentoCollectionOld = persistentDocumento.getDocumentoCollection();
            Collection<Documento> documentoCollectionNew = documento.getDocumentoCollection();
            Collection<DestinatariosDoc> destinatariosDocCollectionOld = persistentDocumento.getDestinatariosDocCollection();
            Collection<DestinatariosDoc> destinatariosDocCollectionNew = documento.getDestinatariosDocCollection();
            Collection<ProcesoDocumental> procesodocumentalCollectionOld = persistentDocumento.getProcesodocumentalCollection();
            Collection<ProcesoDocumental> procesodocumentalCollectionNew = documento.getProcesodocumentalCollection();
            List<Comentario> comentarioListOld = persistentDocumento.getComentarioList();
            List<Comentario> comentarioListNew = documento.getComentarioList();
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
            if (destinatarioNew != null) {
                destinatarioNew = em.getReference(destinatarioNew.getClass(), destinatarioNew.getId());
                documento.setDestinatario(destinatarioNew);
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
            if (medioEnvioNew != null) {
                medioEnvioNew = em.getReference(medioEnvioNew.getClass(), medioEnvioNew.getId());
                documento.setMedioEnvio(medioEnvioNew);
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
            List<Comentario> attachedComentarioListNew = new ArrayList<Comentario>();
            for (Comentario comentarioListNewComentarioToAttach : comentarioListNew) {
                comentarioListNewComentarioToAttach = em.getReference(comentarioListNewComentarioToAttach.getClass(), comentarioListNewComentarioToAttach.getId());
                attachedComentarioListNew.add(comentarioListNewComentarioToAttach);
            }
            comentarioListNew = attachedComentarioListNew;
            documento.setComentarioList(comentarioListNew);
            documento = em.merge(documento);
            if (destinatarioOld != null && !destinatarioOld.equals(destinatarioNew)) {
                destinatarioOld.getDocumentoCollection().remove(documento);
                destinatarioOld = em.merge(destinatarioOld);
            }
            if (destinatarioNew != null && !destinatarioNew.equals(destinatarioOld)) {
                destinatarioNew.getDocumentoCollection().add(documento);
                destinatarioNew = em.merge(destinatarioNew);
            }
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
            if (medioEnvioOld != null && !medioEnvioOld.equals(medioEnvioNew)) {
                medioEnvioOld.getDocumentoList().remove(documento);
                medioEnvioOld = em.merge(medioEnvioOld);
            }
            if (medioEnvioNew != null && !medioEnvioNew.equals(medioEnvioOld)) {
                medioEnvioNew.getDocumentoList().add(documento);
                medioEnvioNew = em.merge(medioEnvioNew);
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
            for (Comentario comentarioListOldComentario : comentarioListOld) {
                if (!comentarioListNew.contains(comentarioListOldComentario)) {
                    comentarioListOldComentario.setDocumento(null);
                    comentarioListOldComentario = em.merge(comentarioListOldComentario);
                }
            }
            for (Comentario comentarioListNewComentario : comentarioListNew) {
                if (!comentarioListOld.contains(comentarioListNewComentario)) {
                    Documento oldDocumentoOfComentarioListNewComentario = comentarioListNewComentario.getDocumento();
                    comentarioListNewComentario.setDocumento(documento);
                    comentarioListNewComentario = em.merge(comentarioListNewComentario);
                    if (oldDocumentoOfComentarioListNewComentario != null && !oldDocumentoOfComentarioListNewComentario.equals(documento)) {
                        oldDocumentoOfComentarioListNewComentario.getComentarioList().remove(comentarioListNewComentario);
                        oldDocumentoOfComentarioListNewComentario = em.merge(oldDocumentoOfComentarioListNewComentario);
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
            Usuario destinatario = documento.getDestinatario();
            if (destinatario != null) {
                destinatario.getDocumentoCollection().remove(documento);
                destinatario = em.merge(destinatario);
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
            Mediorecepcion medioEnvio = documento.getMedioEnvio();
            if (medioEnvio != null) {
                medioEnvio.getDocumentoList().remove(documento);
                medioEnvio = em.merge(medioEnvio);
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
            List<Comentario> comentarioList = documento.getComentarioList();
            for (Comentario comentarioListComentario : comentarioList) {
                comentarioListComentario.setDocumento(null);
                comentarioListComentario = em.merge(comentarioListComentario);
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

    public List<Documento> findByCompartidos(Usuario usuario) {
        return findByCompartidos(usuario, true, -1, -1);
    }

    public List<Documento> findByCompartidos(Usuario usuario, int maxResults, int firstResult) {
        return findByCompartidos(usuario, false, maxResults, firstResult);
    }

    private List<Documento> findByCompartidos(Usuario usuario, boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Documento.class));
            Query q = em.createNamedQuery("Documento.findByCompartidos", Usuario.class)
                    .setParameter("destinatario", usuario);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public List<Documento> findEntrantes(Usuario usuario) {
        return findEntrantes(usuario, true, -1, -1);
    }

    public List<Documento> findEntrantes(Usuario usuario, int maxResults, int firstResult) {
        return findEntrantes(usuario, false, maxResults, firstResult);
    }

    private List<Documento> findEntrantes(Usuario usuario, boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Documento.class));
            Query q = em.createNamedQuery("Documento.findEntrantes", Usuario.class)
                    .setParameter("destinatario", usuario);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }
    
    public List<Documento> findEntrantesI(Usuario usuario) {
        return findEntrantesI(usuario, true, -1, -1);
    }

    public List<Documento> findEntrantesI(Usuario usuario, int maxResults, int firstResult) {
        return findEntrantesI(usuario, false, maxResults, firstResult);
    }

    private List<Documento> findEntrantesI(Usuario usuario, boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Documento.class));
            Query q = em.createNamedQuery("Documento.findEntrantesInternos", Usuario.class)
                    .setParameter("destinatarioInterno", usuario);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public List<Documento> findEnviados(Usuario usuario) {
        return findEnviados(usuario, true, -1, -1);
    }

    public List<Documento> findEnviados(Usuario usuario, int maxResults, int firstResult) {
        return findEnviados(usuario, false, maxResults, firstResult);
    }

    private List<Documento> findEnviados(Usuario usuario, boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Documento.class));
            Query q = em.createNamedQuery("Documento.findEnviados", Usuario.class)
                    .setParameter("destinatario", usuario);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }
    public Documento findByConsecutivo(String consecutivo) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Usuario.class));
            Query q = em.createNamedQuery("Documento.findByConsecutivo", Usuario.class)
                    .setParameter("consecutivo", consecutivo);
            return (Documento)q.getSingleResult();
        } finally {
            em.close();
        }
    }

    public List<Documento> findRadicados(Usuario usuario) {
        return findRadicados(usuario, true, -1, -1);
    }

    public List<Documento> findRadicados(Usuario usuario, int maxResults, int firstResult) {
        return findEnviados(usuario, false, maxResults, firstResult);
    }

    private List<Documento> findRadicados(Usuario usuario, boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Documento.class));
            Query q = em.createNamedQuery("Documento.findRadicados", Usuario.class)
                    .setParameter("creadoPor", usuario);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public List<Documento> findEnPrestamo(Usuario usuario) {
        return findEnPrestamo(usuario, true, -1, -1);
    }

    public List<Documento> findEnPrestamo(Usuario usuario, int maxResults, int firstResult) {
        return findEnPrestamo(usuario, false, maxResults, firstResult);
    }

    private List<Documento> findEnPrestamo(Usuario usuario, boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Documento.class));
            Query q = em.createNamedQuery("Documento.findEnPrestamo", Usuario.class)
                    .setParameter("destinatario", usuario);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public List<Documento> findArchivados(Usuario usuario) {
        return findArchivados(usuario, true, -1, -1);
    }

    public List<Documento> findArchivados(Usuario usuario, int maxResults, int firstResult) {
        return findArchivados(usuario, false, maxResults, firstResult);
    }

    private List<Documento> findArchivados(Usuario usuario, boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Documento.class));
            Query q = em.createNamedQuery("Documento.findArchivados", Usuario.class)
                    .setParameter("destinatario", usuario);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public List<Documento> findPorVencer(Usuario usuario) {
        return findPorVencer(usuario, true, -1, -1);
    }

    public List<Documento> findPorVencer(Usuario usuario, int maxResults, int firstResult) {
        return findPorVencer(usuario, false, maxResults, firstResult);
    }

    private List<Documento> findPorVencer(Usuario usuario, boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Documento.class));
            Query q = em.createNamedQuery("Documento.findPorVencer", Usuario.class)
                    .setParameter("destinatario", usuario);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }
            
    public List<Documento> findPorEnviar( ) {
        return findPorEnviar( true, -1, -1);
    }

    public List<Documento> findPorEnviar( int maxResults, int firstResult) {
        return findPorEnviar( false, maxResults, firstResult);
    }

    private List<Documento> findPorEnviar( boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Documento.class));
            Query q = em.createNamedQuery("Documento.findPorEnviar", Documento.class);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }
            
    public List<Documento> findSinArchivar(Usuario usuario) {
        return findSinArchivar(usuario, true, -1, -1);
    }

    public List<Documento> findSinArchivar(Usuario usuario, int maxResults, int firstResult) {
        return findSinArchivar(usuario, false, maxResults, firstResult);
    }

    private List<Documento> findSinArchivar(Usuario usuario, boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Documento.class));
            Query q = em.createNamedQuery("Documento.findSinArchivar", Usuario.class)
                    .setParameter("destinatario", usuario);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public List<Documento> findDocumentos(Usuario remitente, String radicado, String asunto, Date starDate, Date endDate, TipoDocumento tipoDocumento) {
        return findDocumentos(remitente, radicado, asunto, starDate, endDate, tipoDocumento, true, -1, -1);
    }

    public List<Documento> findDocumentos(Usuario remitente, String radicado, String asunto, Date starDate, Date endDate, TipoDocumento tipoDocumento, int maxResults, int firstResult) {
        return findDocumentos(remitente, radicado, asunto, starDate, endDate, tipoDocumento, false, maxResults, firstResult);
    }

    private List<Documento> findDocumentos(Usuario destinatario, String radicado, String asunto, Date starDate, Date endDate, TipoDocumento tipoDocumento, boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        List<Documento> documentos = null;
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery cq = cb.createQuery(Documento.class);
            
            Root doc = cq.from(Documento.class);
            List<Predicate> predicates = new ArrayList<Predicate>();

            if(destinatario != null){
                Expression<Usuario> eRemitente = doc.get("destinatario");
                predicates.add(cb.equal(eRemitente, destinatario));
            }
            
            if (starDate != null && endDate != null) {
                predicates.add(cb.and(cb.between(doc.get(Documento_.fechaDocumento), starDate, endDate)));
            }

            if (tipoDocumento != null) {
                Expression<TipoDocumento> eTipoDoc = doc.get("tipoDocumento");
                predicates.add(cb.and(cb.equal(eTipoDoc, tipoDocumento)));
            }
            
            if (radicado != null && !radicado.isEmpty()) {
                String pRadicado = "%" + radicado + "%";
                Expression<String> eRadicado = doc.get("consecutivo");
                predicates.add(cb.and(cb.like(eRadicado, pRadicado)));
            }

            if (asunto != null && !asunto.isEmpty() ) {
                String pAsunto = "%" + asunto + "%";
                Expression<String> eAsunto = doc.get("asunto");
                predicates.add(cb.and(cb.like(eAsunto, pAsunto)));
            }
            cq.select(doc).where(predicates.toArray(new Predicate[]{}));
        
            TypedQuery<Documento> q = em.createQuery(cq);
            
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            documentos = q.getResultList();

        } catch (Exception e) {
            Logger.getLogger(DocumentoJpaController.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            em.close();
        }
        return documentos;
    }

    //WHERE d.remitente = :remitente and redicado like :radicado and asunto like :asunto and fechaDocumento between (:startDate and :endDate) and tipoDocumento like :tipoDocumento
}
