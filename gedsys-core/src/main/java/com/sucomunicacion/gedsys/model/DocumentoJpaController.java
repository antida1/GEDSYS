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
import com.sucomunicacion.gedsys.entities.Autor;
import com.sucomunicacion.gedsys.entities.ClaseDocumento;
import com.sucomunicacion.gedsys.entities.Transportador;
import com.sucomunicacion.gedsys.entities.Usuario;
import com.sucomunicacion.gedsys.entities.Corregimiento;
import com.sucomunicacion.gedsys.entities.Documento;
import com.sucomunicacion.gedsys.entities.UnidadDocumental;
import com.sucomunicacion.gedsys.entities.TipoDocumental;
import com.sucomunicacion.gedsys.entities.Entidad;
import com.sucomunicacion.gedsys.entities.Municipio;
import com.sucomunicacion.gedsys.entities.TipoDocumento;
import com.sucomunicacion.gedsys.entities.Seccion;
import com.sucomunicacion.gedsys.entities.Serie;
import com.sucomunicacion.gedsys.entities.SignaturaTopografica;
import com.sucomunicacion.gedsys.entities.SubSeccion;
import com.sucomunicacion.gedsys.entities.SubSerie;
import com.sucomunicacion.gedsys.entities.Anexo;
import java.util.ArrayList;
import java.util.Collection;
import com.sucomunicacion.gedsys.entities.ProcesoDocumental;
import com.sucomunicacion.gedsys.model.exceptions.NonexistentEntityException;
import com.sucomunicacion.gedsys.model.exceptions.PreexistingEntityException;
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

    public void create(Documento documento) throws PreexistingEntityException, Exception {
        if (documento.getAnexoCollection() == null) {
            documento.setAnexoCollection(new ArrayList<Anexo>());
        }
        if (documento.getAnexoCollection1() == null) {
            documento.setAnexoCollection1(new ArrayList<Anexo>());
        }
        if (documento.getProcesoDocumentalCollection() == null) {
            documento.setProcesoDocumentalCollection(new ArrayList<ProcesoDocumental>());
        }
        if (documento.getDocumentoCollection() == null) {
            documento.setDocumentoCollection(new ArrayList<Documento>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
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
            Transportador transportador = documento.getTransportador();
            if (transportador != null) {
                transportador = em.getReference(transportador.getClass(), transportador.getId());
                documento.setTransportador(transportador);
            }
            Usuario creadoPor = documento.getCreadoPor();
            if (creadoPor != null) {
                creadoPor = em.getReference(creadoPor.getClass(), creadoPor.getId());
                documento.setCreadoPor(creadoPor);
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
            UnidadDocumental unidadDocumental = documento.getUnidadDocumental();
            if (unidadDocumental != null) {
                unidadDocumental = em.getReference(unidadDocumental.getClass(), unidadDocumental.getId());
                documento.setUnidadDocumental(unidadDocumental);
            }
            TipoDocumental tipoDocumental = documento.getTipoDocumental();
            if (tipoDocumental != null) {
                tipoDocumental = em.getReference(tipoDocumental.getClass(), tipoDocumental.getId());
                documento.setTipoDocumental(tipoDocumental);
            }
            Entidad entidad = documento.getEntidad();
            if (entidad != null) {
                entidad = em.getReference(entidad.getClass(), entidad.getId());
                documento.setEntidad(entidad);
            }
            Municipio municipio = documento.getMunicipio();
            if (municipio != null) {
                municipio = em.getReference(municipio.getClass(), municipio.getId());
                documento.setMunicipio(municipio);
            }
            TipoDocumento tipoDocumento = documento.getTipoDocumento();
            if (tipoDocumento != null) {
                tipoDocumento = em.getReference(tipoDocumento.getClass(), tipoDocumento.getId());
                documento.setTipoDocumento(tipoDocumento);
            }
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
            SignaturaTopografica signaturaTopografica = documento.getSignaturaTopografica();
            if (signaturaTopografica != null) {
                signaturaTopografica = em.getReference(signaturaTopografica.getClass(), signaturaTopografica.getId());
                documento.setSignaturaTopografica(signaturaTopografica);
            }
            SubSeccion subSeccion = documento.getSubSeccion();
            if (subSeccion != null) {
                subSeccion = em.getReference(subSeccion.getClass(), subSeccion.getId());
                documento.setSubSeccion(subSeccion);
            }
            SubSerie subSerie = documento.getSubSerie();
            if (subSerie != null) {
                subSerie = em.getReference(subSerie.getClass(), subSerie.getId());
                documento.setSubSerie(subSerie);
            }
            Usuario modificadoPor = documento.getModificadoPor();
            if (modificadoPor != null) {
                modificadoPor = em.getReference(modificadoPor.getClass(), modificadoPor.getId());
                documento.setModificadoPor(modificadoPor);
            }
            Collection<Anexo> attachedAnexoCollection = new ArrayList<Anexo>();
            for (Anexo anexoCollectionAnexoToAttach : documento.getAnexoCollection()) {
                anexoCollectionAnexoToAttach = em.getReference(anexoCollectionAnexoToAttach.getClass(), anexoCollectionAnexoToAttach.getId());
                attachedAnexoCollection.add(anexoCollectionAnexoToAttach);
            }
            documento.setAnexoCollection(attachedAnexoCollection);
            Collection<Anexo> attachedAnexoCollection1 = new ArrayList<Anexo>();
            for (Anexo anexoCollection1AnexoToAttach : documento.getAnexoCollection1()) {
                anexoCollection1AnexoToAttach = em.getReference(anexoCollection1AnexoToAttach.getClass(), anexoCollection1AnexoToAttach.getId());
                attachedAnexoCollection1.add(anexoCollection1AnexoToAttach);
            }
            documento.setAnexoCollection1(attachedAnexoCollection1);
            Collection<ProcesoDocumental> attachedProcesoDocumentalCollection = new ArrayList<ProcesoDocumental>();
            for (ProcesoDocumental procesoDocumentalCollectionProcesoDocumentalToAttach : documento.getProcesoDocumentalCollection()) {
                procesoDocumentalCollectionProcesoDocumentalToAttach = em.getReference(procesoDocumentalCollectionProcesoDocumentalToAttach.getClass(), procesoDocumentalCollectionProcesoDocumentalToAttach.getId());
                attachedProcesoDocumentalCollection.add(procesoDocumentalCollectionProcesoDocumentalToAttach);
            }
            documento.setProcesoDocumentalCollection(attachedProcesoDocumentalCollection);
            Collection<Documento> attachedDocumentoCollection = new ArrayList<Documento>();
            for (Documento documentoCollectionDocumentoToAttach : documento.getDocumentoCollection()) {
                documentoCollectionDocumentoToAttach = em.getReference(documentoCollectionDocumentoToAttach.getClass(), documentoCollectionDocumentoToAttach.getId());
                attachedDocumentoCollection.add(documentoCollectionDocumentoToAttach);
            }
            documento.setDocumentoCollection(attachedDocumentoCollection);
            em.persist(documento);
            if (autor != null) {
                autor.getDocumentoCollection().add(documento);
                autor = em.merge(autor);
            }
            if (claseDocumento != null) {
                claseDocumento.getDocumentoCollection().add(documento);
                claseDocumento = em.merge(claseDocumento);
            }
            if (transportador != null) {
                transportador.getDocumentoCollection().add(documento);
                transportador = em.merge(transportador);
            }
            if (creadoPor != null) {
                creadoPor.getDocumentoCollection().add(documento);
                creadoPor = em.merge(creadoPor);
            }
            if (corregimiento != null) {
                corregimiento.getDocumentoCollection().add(documento);
                corregimiento = em.merge(corregimiento);
            }
            if (documentoRelacionado != null) {
                documentoRelacionado.getDocumentoCollection().add(documento);
                documentoRelacionado = em.merge(documentoRelacionado);
            }
            if (unidadDocumental != null) {
                unidadDocumental.getDocumentoCollection().add(documento);
                unidadDocumental = em.merge(unidadDocumental);
            }
            if (tipoDocumental != null) {
                tipoDocumental.getDocumentoCollection().add(documento);
                tipoDocumental = em.merge(tipoDocumental);
            }
            if (entidad != null) {
                entidad.getDocumentoCollection().add(documento);
                entidad = em.merge(entidad);
            }
            if (municipio != null) {
                municipio.getDocumentoCollection().add(documento);
                municipio = em.merge(municipio);
            }
            if (tipoDocumento != null) {
                tipoDocumento.getDocumentoCollection().add(documento);
                tipoDocumento = em.merge(tipoDocumento);
            }
            if (session != null) {
                session.getDocumentoCollection().add(documento);
                session = em.merge(session);
            }
            if (serie != null) {
                serie.getDocumentoCollection().add(documento);
                serie = em.merge(serie);
            }
            if (signaturaTopografica != null) {
                signaturaTopografica.getDocumentoCollection().add(documento);
                signaturaTopografica = em.merge(signaturaTopografica);
            }
            if (subSeccion != null) {
                subSeccion.getDocumentoCollection().add(documento);
                subSeccion = em.merge(subSeccion);
            }
            if (subSerie != null) {
                subSerie.getDocumentoCollection().add(documento);
                subSerie = em.merge(subSerie);
            }
            if (modificadoPor != null) {
                modificadoPor.getDocumentoCollection().add(documento);
                modificadoPor = em.merge(modificadoPor);
            }
            for (Anexo anexoCollectionAnexo : documento.getAnexoCollection()) {
                Documento oldDocumentoOfAnexoCollectionAnexo = anexoCollectionAnexo.getDocumento();
                anexoCollectionAnexo.setDocumento(documento);
                anexoCollectionAnexo = em.merge(anexoCollectionAnexo);
                if (oldDocumentoOfAnexoCollectionAnexo != null) {
                    oldDocumentoOfAnexoCollectionAnexo.getAnexoCollection().remove(anexoCollectionAnexo);
                    oldDocumentoOfAnexoCollectionAnexo = em.merge(oldDocumentoOfAnexoCollectionAnexo);
                }
            }
            for (Anexo anexoCollection1Anexo : documento.getAnexoCollection1()) {
                Documento oldAnexoOfAnexoCollection1Anexo = anexoCollection1Anexo.getAnexo();
                anexoCollection1Anexo.setAnexo(documento);
                anexoCollection1Anexo = em.merge(anexoCollection1Anexo);
                if (oldAnexoOfAnexoCollection1Anexo != null) {
                    oldAnexoOfAnexoCollection1Anexo.getAnexoCollection1().remove(anexoCollection1Anexo);
                    oldAnexoOfAnexoCollection1Anexo = em.merge(oldAnexoOfAnexoCollection1Anexo);
                }
            }
            for (ProcesoDocumental procesoDocumentalCollectionProcesoDocumental : documento.getProcesoDocumentalCollection()) {
                Documento oldDocumentoOfProcesoDocumentalCollectionProcesoDocumental = procesoDocumentalCollectionProcesoDocumental.getDocumento();
                procesoDocumentalCollectionProcesoDocumental.setDocumento(documento);
                procesoDocumentalCollectionProcesoDocumental = em.merge(procesoDocumentalCollectionProcesoDocumental);
                if (oldDocumentoOfProcesoDocumentalCollectionProcesoDocumental != null) {
                    oldDocumentoOfProcesoDocumentalCollectionProcesoDocumental.getProcesoDocumentalCollection().remove(procesoDocumentalCollectionProcesoDocumental);
                    oldDocumentoOfProcesoDocumentalCollectionProcesoDocumental = em.merge(oldDocumentoOfProcesoDocumentalCollectionProcesoDocumental);
                }
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
            Autor autorOld = persistentDocumento.getAutor();
            Autor autorNew = documento.getAutor();
            ClaseDocumento claseDocumentoOld = persistentDocumento.getClaseDocumento();
            ClaseDocumento claseDocumentoNew = documento.getClaseDocumento();
            Transportador transportadorOld = persistentDocumento.getTransportador();
            Transportador transportadorNew = documento.getTransportador();
            Usuario creadoPorOld = persistentDocumento.getCreadoPor();
            Usuario creadoPorNew = documento.getCreadoPor();
            Corregimiento corregimientoOld = persistentDocumento.getCorregimiento();
            Corregimiento corregimientoNew = documento.getCorregimiento();
            Documento documentoRelacionadoOld = persistentDocumento.getDocumentoRelacionado();
            Documento documentoRelacionadoNew = documento.getDocumentoRelacionado();
            UnidadDocumental unidadDocumentalOld = persistentDocumento.getUnidadDocumental();
            UnidadDocumental unidadDocumentalNew = documento.getUnidadDocumental();
            TipoDocumental tipoDocumentalOld = persistentDocumento.getTipoDocumental();
            TipoDocumental tipoDocumentalNew = documento.getTipoDocumental();
            Entidad entidadOld = persistentDocumento.getEntidad();
            Entidad entidadNew = documento.getEntidad();
            Municipio municipioOld = persistentDocumento.getMunicipio();
            Municipio municipioNew = documento.getMunicipio();
            TipoDocumento tipoDocumentoOld = persistentDocumento.getTipoDocumento();
            TipoDocumento tipoDocumentoNew = documento.getTipoDocumento();
            Seccion sessionOld = persistentDocumento.getSession();
            Seccion sessionNew = documento.getSession();
            Serie serieOld = persistentDocumento.getSerie();
            Serie serieNew = documento.getSerie();
            SignaturaTopografica signaturaTopograficaOld = persistentDocumento.getSignaturaTopografica();
            SignaturaTopografica signaturaTopograficaNew = documento.getSignaturaTopografica();
            SubSeccion subSeccionOld = persistentDocumento.getSubSeccion();
            SubSeccion subSeccionNew = documento.getSubSeccion();
            SubSerie subSerieOld = persistentDocumento.getSubSerie();
            SubSerie subSerieNew = documento.getSubSerie();
            Usuario modificadoPorOld = persistentDocumento.getModificadoPor();
            Usuario modificadoPorNew = documento.getModificadoPor();
            Collection<Anexo> anexoCollectionOld = persistentDocumento.getAnexoCollection();
            Collection<Anexo> anexoCollectionNew = documento.getAnexoCollection();
            Collection<Anexo> anexoCollection1Old = persistentDocumento.getAnexoCollection1();
            Collection<Anexo> anexoCollection1New = documento.getAnexoCollection1();
            Collection<ProcesoDocumental> procesoDocumentalCollectionOld = persistentDocumento.getProcesoDocumentalCollection();
            Collection<ProcesoDocumental> procesoDocumentalCollectionNew = documento.getProcesoDocumentalCollection();
            Collection<Documento> documentoCollectionOld = persistentDocumento.getDocumentoCollection();
            Collection<Documento> documentoCollectionNew = documento.getDocumentoCollection();
            if (autorNew != null) {
                autorNew = em.getReference(autorNew.getClass(), autorNew.getId());
                documento.setAutor(autorNew);
            }
            if (claseDocumentoNew != null) {
                claseDocumentoNew = em.getReference(claseDocumentoNew.getClass(), claseDocumentoNew.getId());
                documento.setClaseDocumento(claseDocumentoNew);
            }
            if (transportadorNew != null) {
                transportadorNew = em.getReference(transportadorNew.getClass(), transportadorNew.getId());
                documento.setTransportador(transportadorNew);
            }
            if (creadoPorNew != null) {
                creadoPorNew = em.getReference(creadoPorNew.getClass(), creadoPorNew.getId());
                documento.setCreadoPor(creadoPorNew);
            }
            if (corregimientoNew != null) {
                corregimientoNew = em.getReference(corregimientoNew.getClass(), corregimientoNew.getId());
                documento.setCorregimiento(corregimientoNew);
            }
            if (documentoRelacionadoNew != null) {
                documentoRelacionadoNew = em.getReference(documentoRelacionadoNew.getClass(), documentoRelacionadoNew.getId());
                documento.setDocumentoRelacionado(documentoRelacionadoNew);
            }
            if (unidadDocumentalNew != null) {
                unidadDocumentalNew = em.getReference(unidadDocumentalNew.getClass(), unidadDocumentalNew.getId());
                documento.setUnidadDocumental(unidadDocumentalNew);
            }
            if (tipoDocumentalNew != null) {
                tipoDocumentalNew = em.getReference(tipoDocumentalNew.getClass(), tipoDocumentalNew.getId());
                documento.setTipoDocumental(tipoDocumentalNew);
            }
            if (entidadNew != null) {
                entidadNew = em.getReference(entidadNew.getClass(), entidadNew.getId());
                documento.setEntidad(entidadNew);
            }
            if (municipioNew != null) {
                municipioNew = em.getReference(municipioNew.getClass(), municipioNew.getId());
                documento.setMunicipio(municipioNew);
            }
            if (tipoDocumentoNew != null) {
                tipoDocumentoNew = em.getReference(tipoDocumentoNew.getClass(), tipoDocumentoNew.getId());
                documento.setTipoDocumento(tipoDocumentoNew);
            }
            if (sessionNew != null) {
                sessionNew = em.getReference(sessionNew.getClass(), sessionNew.getId());
                documento.setSession(sessionNew);
            }
            if (serieNew != null) {
                serieNew = em.getReference(serieNew.getClass(), serieNew.getId());
                documento.setSerie(serieNew);
            }
            if (signaturaTopograficaNew != null) {
                signaturaTopograficaNew = em.getReference(signaturaTopograficaNew.getClass(), signaturaTopograficaNew.getId());
                documento.setSignaturaTopografica(signaturaTopograficaNew);
            }
            if (subSeccionNew != null) {
                subSeccionNew = em.getReference(subSeccionNew.getClass(), subSeccionNew.getId());
                documento.setSubSeccion(subSeccionNew);
            }
            if (subSerieNew != null) {
                subSerieNew = em.getReference(subSerieNew.getClass(), subSerieNew.getId());
                documento.setSubSerie(subSerieNew);
            }
            if (modificadoPorNew != null) {
                modificadoPorNew = em.getReference(modificadoPorNew.getClass(), modificadoPorNew.getId());
                documento.setModificadoPor(modificadoPorNew);
            }
            Collection<Anexo> attachedAnexoCollectionNew = new ArrayList<Anexo>();
            for (Anexo anexoCollectionNewAnexoToAttach : anexoCollectionNew) {
                anexoCollectionNewAnexoToAttach = em.getReference(anexoCollectionNewAnexoToAttach.getClass(), anexoCollectionNewAnexoToAttach.getId());
                attachedAnexoCollectionNew.add(anexoCollectionNewAnexoToAttach);
            }
            anexoCollectionNew = attachedAnexoCollectionNew;
            documento.setAnexoCollection(anexoCollectionNew);
            Collection<Anexo> attachedAnexoCollection1New = new ArrayList<Anexo>();
            for (Anexo anexoCollection1NewAnexoToAttach : anexoCollection1New) {
                anexoCollection1NewAnexoToAttach = em.getReference(anexoCollection1NewAnexoToAttach.getClass(), anexoCollection1NewAnexoToAttach.getId());
                attachedAnexoCollection1New.add(anexoCollection1NewAnexoToAttach);
            }
            anexoCollection1New = attachedAnexoCollection1New;
            documento.setAnexoCollection1(anexoCollection1New);
            Collection<ProcesoDocumental> attachedProcesoDocumentalCollectionNew = new ArrayList<ProcesoDocumental>();
            for (ProcesoDocumental procesoDocumentalCollectionNewProcesoDocumentalToAttach : procesoDocumentalCollectionNew) {
                procesoDocumentalCollectionNewProcesoDocumentalToAttach = em.getReference(procesoDocumentalCollectionNewProcesoDocumentalToAttach.getClass(), procesoDocumentalCollectionNewProcesoDocumentalToAttach.getId());
                attachedProcesoDocumentalCollectionNew.add(procesoDocumentalCollectionNewProcesoDocumentalToAttach);
            }
            procesoDocumentalCollectionNew = attachedProcesoDocumentalCollectionNew;
            documento.setProcesoDocumentalCollection(procesoDocumentalCollectionNew);
            Collection<Documento> attachedDocumentoCollectionNew = new ArrayList<Documento>();
            for (Documento documentoCollectionNewDocumentoToAttach : documentoCollectionNew) {
                documentoCollectionNewDocumentoToAttach = em.getReference(documentoCollectionNewDocumentoToAttach.getClass(), documentoCollectionNewDocumentoToAttach.getId());
                attachedDocumentoCollectionNew.add(documentoCollectionNewDocumentoToAttach);
            }
            documentoCollectionNew = attachedDocumentoCollectionNew;
            documento.setDocumentoCollection(documentoCollectionNew);
            documento = em.merge(documento);
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
            if (transportadorOld != null && !transportadorOld.equals(transportadorNew)) {
                transportadorOld.getDocumentoCollection().remove(documento);
                transportadorOld = em.merge(transportadorOld);
            }
            if (transportadorNew != null && !transportadorNew.equals(transportadorOld)) {
                transportadorNew.getDocumentoCollection().add(documento);
                transportadorNew = em.merge(transportadorNew);
            }
            if (creadoPorOld != null && !creadoPorOld.equals(creadoPorNew)) {
                creadoPorOld.getDocumentoCollection().remove(documento);
                creadoPorOld = em.merge(creadoPorOld);
            }
            if (creadoPorNew != null && !creadoPorNew.equals(creadoPorOld)) {
                creadoPorNew.getDocumentoCollection().add(documento);
                creadoPorNew = em.merge(creadoPorNew);
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
            if (unidadDocumentalOld != null && !unidadDocumentalOld.equals(unidadDocumentalNew)) {
                unidadDocumentalOld.getDocumentoCollection().remove(documento);
                unidadDocumentalOld = em.merge(unidadDocumentalOld);
            }
            if (unidadDocumentalNew != null && !unidadDocumentalNew.equals(unidadDocumentalOld)) {
                unidadDocumentalNew.getDocumentoCollection().add(documento);
                unidadDocumentalNew = em.merge(unidadDocumentalNew);
            }
            if (tipoDocumentalOld != null && !tipoDocumentalOld.equals(tipoDocumentalNew)) {
                tipoDocumentalOld.getDocumentoCollection().remove(documento);
                tipoDocumentalOld = em.merge(tipoDocumentalOld);
            }
            if (tipoDocumentalNew != null && !tipoDocumentalNew.equals(tipoDocumentalOld)) {
                tipoDocumentalNew.getDocumentoCollection().add(documento);
                tipoDocumentalNew = em.merge(tipoDocumentalNew);
            }
            if (entidadOld != null && !entidadOld.equals(entidadNew)) {
                entidadOld.getDocumentoCollection().remove(documento);
                entidadOld = em.merge(entidadOld);
            }
            if (entidadNew != null && !entidadNew.equals(entidadOld)) {
                entidadNew.getDocumentoCollection().add(documento);
                entidadNew = em.merge(entidadNew);
            }
            if (municipioOld != null && !municipioOld.equals(municipioNew)) {
                municipioOld.getDocumentoCollection().remove(documento);
                municipioOld = em.merge(municipioOld);
            }
            if (municipioNew != null && !municipioNew.equals(municipioOld)) {
                municipioNew.getDocumentoCollection().add(documento);
                municipioNew = em.merge(municipioNew);
            }
            if (tipoDocumentoOld != null && !tipoDocumentoOld.equals(tipoDocumentoNew)) {
                tipoDocumentoOld.getDocumentoCollection().remove(documento);
                tipoDocumentoOld = em.merge(tipoDocumentoOld);
            }
            if (tipoDocumentoNew != null && !tipoDocumentoNew.equals(tipoDocumentoOld)) {
                tipoDocumentoNew.getDocumentoCollection().add(documento);
                tipoDocumentoNew = em.merge(tipoDocumentoNew);
            }
            if (sessionOld != null && !sessionOld.equals(sessionNew)) {
                sessionOld.getDocumentoCollection().remove(documento);
                sessionOld = em.merge(sessionOld);
            }
            if (sessionNew != null && !sessionNew.equals(sessionOld)) {
                sessionNew.getDocumentoCollection().add(documento);
                sessionNew = em.merge(sessionNew);
            }
            if (serieOld != null && !serieOld.equals(serieNew)) {
                serieOld.getDocumentoCollection().remove(documento);
                serieOld = em.merge(serieOld);
            }
            if (serieNew != null && !serieNew.equals(serieOld)) {
                serieNew.getDocumentoCollection().add(documento);
                serieNew = em.merge(serieNew);
            }
            if (signaturaTopograficaOld != null && !signaturaTopograficaOld.equals(signaturaTopograficaNew)) {
                signaturaTopograficaOld.getDocumentoCollection().remove(documento);
                signaturaTopograficaOld = em.merge(signaturaTopograficaOld);
            }
            if (signaturaTopograficaNew != null && !signaturaTopograficaNew.equals(signaturaTopograficaOld)) {
                signaturaTopograficaNew.getDocumentoCollection().add(documento);
                signaturaTopograficaNew = em.merge(signaturaTopograficaNew);
            }
            if (subSeccionOld != null && !subSeccionOld.equals(subSeccionNew)) {
                subSeccionOld.getDocumentoCollection().remove(documento);
                subSeccionOld = em.merge(subSeccionOld);
            }
            if (subSeccionNew != null && !subSeccionNew.equals(subSeccionOld)) {
                subSeccionNew.getDocumentoCollection().add(documento);
                subSeccionNew = em.merge(subSeccionNew);
            }
            if (subSerieOld != null && !subSerieOld.equals(subSerieNew)) {
                subSerieOld.getDocumentoCollection().remove(documento);
                subSerieOld = em.merge(subSerieOld);
            }
            if (subSerieNew != null && !subSerieNew.equals(subSerieOld)) {
                subSerieNew.getDocumentoCollection().add(documento);
                subSerieNew = em.merge(subSerieNew);
            }
            if (modificadoPorOld != null && !modificadoPorOld.equals(modificadoPorNew)) {
                modificadoPorOld.getDocumentoCollection().remove(documento);
                modificadoPorOld = em.merge(modificadoPorOld);
            }
            if (modificadoPorNew != null && !modificadoPorNew.equals(modificadoPorOld)) {
                modificadoPorNew.getDocumentoCollection().add(documento);
                modificadoPorNew = em.merge(modificadoPorNew);
            }
            for (Anexo anexoCollectionOldAnexo : anexoCollectionOld) {
                if (!anexoCollectionNew.contains(anexoCollectionOldAnexo)) {
                    anexoCollectionOldAnexo.setDocumento(null);
                    anexoCollectionOldAnexo = em.merge(anexoCollectionOldAnexo);
                }
            }
            for (Anexo anexoCollectionNewAnexo : anexoCollectionNew) {
                if (!anexoCollectionOld.contains(anexoCollectionNewAnexo)) {
                    Documento oldDocumentoOfAnexoCollectionNewAnexo = anexoCollectionNewAnexo.getDocumento();
                    anexoCollectionNewAnexo.setDocumento(documento);
                    anexoCollectionNewAnexo = em.merge(anexoCollectionNewAnexo);
                    if (oldDocumentoOfAnexoCollectionNewAnexo != null && !oldDocumentoOfAnexoCollectionNewAnexo.equals(documento)) {
                        oldDocumentoOfAnexoCollectionNewAnexo.getAnexoCollection().remove(anexoCollectionNewAnexo);
                        oldDocumentoOfAnexoCollectionNewAnexo = em.merge(oldDocumentoOfAnexoCollectionNewAnexo);
                    }
                }
            }
            for (Anexo anexoCollection1OldAnexo : anexoCollection1Old) {
                if (!anexoCollection1New.contains(anexoCollection1OldAnexo)) {
                    anexoCollection1OldAnexo.setAnexo(null);
                    anexoCollection1OldAnexo = em.merge(anexoCollection1OldAnexo);
                }
            }
            for (Anexo anexoCollection1NewAnexo : anexoCollection1New) {
                if (!anexoCollection1Old.contains(anexoCollection1NewAnexo)) {
                    Documento oldAnexoOfAnexoCollection1NewAnexo = anexoCollection1NewAnexo.getAnexo();
                    anexoCollection1NewAnexo.setAnexo(documento);
                    anexoCollection1NewAnexo = em.merge(anexoCollection1NewAnexo);
                    if (oldAnexoOfAnexoCollection1NewAnexo != null && !oldAnexoOfAnexoCollection1NewAnexo.equals(documento)) {
                        oldAnexoOfAnexoCollection1NewAnexo.getAnexoCollection1().remove(anexoCollection1NewAnexo);
                        oldAnexoOfAnexoCollection1NewAnexo = em.merge(oldAnexoOfAnexoCollection1NewAnexo);
                    }
                }
            }
            for (ProcesoDocumental procesoDocumentalCollectionOldProcesoDocumental : procesoDocumentalCollectionOld) {
                if (!procesoDocumentalCollectionNew.contains(procesoDocumentalCollectionOldProcesoDocumental)) {
                    procesoDocumentalCollectionOldProcesoDocumental.setDocumento(null);
                    procesoDocumentalCollectionOldProcesoDocumental = em.merge(procesoDocumentalCollectionOldProcesoDocumental);
                }
            }
            for (ProcesoDocumental procesoDocumentalCollectionNewProcesoDocumental : procesoDocumentalCollectionNew) {
                if (!procesoDocumentalCollectionOld.contains(procesoDocumentalCollectionNewProcesoDocumental)) {
                    Documento oldDocumentoOfProcesoDocumentalCollectionNewProcesoDocumental = procesoDocumentalCollectionNewProcesoDocumental.getDocumento();
                    procesoDocumentalCollectionNewProcesoDocumental.setDocumento(documento);
                    procesoDocumentalCollectionNewProcesoDocumental = em.merge(procesoDocumentalCollectionNewProcesoDocumental);
                    if (oldDocumentoOfProcesoDocumentalCollectionNewProcesoDocumental != null && !oldDocumentoOfProcesoDocumentalCollectionNewProcesoDocumental.equals(documento)) {
                        oldDocumentoOfProcesoDocumentalCollectionNewProcesoDocumental.getProcesoDocumentalCollection().remove(procesoDocumentalCollectionNewProcesoDocumental);
                        oldDocumentoOfProcesoDocumentalCollectionNewProcesoDocumental = em.merge(oldDocumentoOfProcesoDocumentalCollectionNewProcesoDocumental);
                    }
                }
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

    public void destroy(Long id) throws NonexistentEntityException {
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
            Transportador transportador = documento.getTransportador();
            if (transportador != null) {
                transportador.getDocumentoCollection().remove(documento);
                transportador = em.merge(transportador);
            }
            Usuario creadoPor = documento.getCreadoPor();
            if (creadoPor != null) {
                creadoPor.getDocumentoCollection().remove(documento);
                creadoPor = em.merge(creadoPor);
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
            UnidadDocumental unidadDocumental = documento.getUnidadDocumental();
            if (unidadDocumental != null) {
                unidadDocumental.getDocumentoCollection().remove(documento);
                unidadDocumental = em.merge(unidadDocumental);
            }
            TipoDocumental tipoDocumental = documento.getTipoDocumental();
            if (tipoDocumental != null) {
                tipoDocumental.getDocumentoCollection().remove(documento);
                tipoDocumental = em.merge(tipoDocumental);
            }
            Entidad entidad = documento.getEntidad();
            if (entidad != null) {
                entidad.getDocumentoCollection().remove(documento);
                entidad = em.merge(entidad);
            }
            Municipio municipio = documento.getMunicipio();
            if (municipio != null) {
                municipio.getDocumentoCollection().remove(documento);
                municipio = em.merge(municipio);
            }
            TipoDocumento tipoDocumento = documento.getTipoDocumento();
            if (tipoDocumento != null) {
                tipoDocumento.getDocumentoCollection().remove(documento);
                tipoDocumento = em.merge(tipoDocumento);
            }
            Seccion session = documento.getSession();
            if (session != null) {
                session.getDocumentoCollection().remove(documento);
                session = em.merge(session);
            }
            Serie serie = documento.getSerie();
            if (serie != null) {
                serie.getDocumentoCollection().remove(documento);
                serie = em.merge(serie);
            }
            SignaturaTopografica signaturaTopografica = documento.getSignaturaTopografica();
            if (signaturaTopografica != null) {
                signaturaTopografica.getDocumentoCollection().remove(documento);
                signaturaTopografica = em.merge(signaturaTopografica);
            }
            SubSeccion subSeccion = documento.getSubSeccion();
            if (subSeccion != null) {
                subSeccion.getDocumentoCollection().remove(documento);
                subSeccion = em.merge(subSeccion);
            }
            SubSerie subSerie = documento.getSubSerie();
            if (subSerie != null) {
                subSerie.getDocumentoCollection().remove(documento);
                subSerie = em.merge(subSerie);
            }
            Usuario modificadoPor = documento.getModificadoPor();
            if (modificadoPor != null) {
                modificadoPor.getDocumentoCollection().remove(documento);
                modificadoPor = em.merge(modificadoPor);
            }
            Collection<Anexo> anexoCollection = documento.getAnexoCollection();
            for (Anexo anexoCollectionAnexo : anexoCollection) {
                anexoCollectionAnexo.setDocumento(null);
                anexoCollectionAnexo = em.merge(anexoCollectionAnexo);
            }
            Collection<Anexo> anexoCollection1 = documento.getAnexoCollection1();
            for (Anexo anexoCollection1Anexo : anexoCollection1) {
                anexoCollection1Anexo.setAnexo(null);
                anexoCollection1Anexo = em.merge(anexoCollection1Anexo);
            }
            Collection<ProcesoDocumental> procesoDocumentalCollection = documento.getProcesoDocumentalCollection();
            for (ProcesoDocumental procesoDocumentalCollectionProcesoDocumental : procesoDocumentalCollection) {
                procesoDocumentalCollectionProcesoDocumental.setDocumento(null);
                procesoDocumentalCollectionProcesoDocumental = em.merge(procesoDocumentalCollectionProcesoDocumental);
            }
            Collection<Documento> documentoCollection = documento.getDocumentoCollection();
            for (Documento documentoCollectionDocumento : documentoCollection) {
                documentoCollectionDocumento.setDocumentoRelacionado(null);
                documentoCollectionDocumento = em.merge(documentoCollectionDocumento);
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
