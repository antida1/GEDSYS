/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base16.gedsys.config;

import com.base16.gedsys.config.exceptions.IllegalOrphanException;
import com.base16.gedsys.config.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.base16.gedsys.entities.Cargo;
import com.base16.gedsys.entities.ClaseDocumento;
import java.util.ArrayList;
import java.util.Collection;
import com.base16.gedsys.entities.Grupo;
import com.base16.gedsys.entities.Documento;
import com.base16.gedsys.entities.Acl;
import com.base16.gedsys.entities.SeccionSubSeccion;
import com.base16.gedsys.entities.Preferencias;
import com.base16.gedsys.entities.PlantillaDocumental;
import com.base16.gedsys.entities.TipoDocumento;
import com.base16.gedsys.entities.SubSerie;
import com.base16.gedsys.entities.Municipio;
import com.base16.gedsys.entities.ProcesoNegocio;
import com.base16.gedsys.entities.Corregimiento;
import com.base16.gedsys.entities.GrupoUsuario;
import com.base16.gedsys.entities.DestinatariosDoc;
import com.base16.gedsys.entities.Autor;
import com.base16.gedsys.entities.Notificacion;
import com.base16.gedsys.entities.Pais;
import com.base16.gedsys.entities.Consecutivo;
import com.base16.gedsys.entities.SignaturaTopografica;
import com.base16.gedsys.entities.TipoDocumental;
import com.base16.gedsys.entities.Transportador;
import com.base16.gedsys.entities.ProcesoDocumental;
import com.base16.gedsys.entities.ProcesoTipoDocumento;
import com.base16.gedsys.entities.Entidad;
import com.base16.gedsys.entities.UnidadDocumental;
import com.base16.gedsys.entities.Serie;
import com.base16.gedsys.entities.Departamento;
import com.base16.gedsys.entities.MonitoresProceso;
import com.base16.gedsys.entities.Modulo;
import com.base16.gedsys.entities.Campos;
import com.base16.gedsys.entities.Usuario;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author rober
 */
public class UsuarioJpaController implements Serializable {

    public UsuarioJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Usuario usuario) {
        if (usuario.getClaseDocumentoCollection() == null) {
            usuario.setClaseDocumentoCollection(new ArrayList<ClaseDocumento>());
        }
        if (usuario.getClaseDocumentoCollection1() == null) {
            usuario.setClaseDocumentoCollection1(new ArrayList<ClaseDocumento>());
        }
        if (usuario.getGrupoCollection() == null) {
            usuario.setGrupoCollection(new ArrayList<Grupo>());
        }
        if (usuario.getDocumentoCollection() == null) {
            usuario.setDocumentoCollection(new ArrayList<Documento>());
        }
        if (usuario.getDocumentoCollection1() == null) {
            usuario.setDocumentoCollection1(new ArrayList<Documento>());
        }
        if (usuario.getAclCollection() == null) {
            usuario.setAclCollection(new ArrayList<Acl>());
        }
        if (usuario.getAclCollection1() == null) {
            usuario.setAclCollection1(new ArrayList<Acl>());
        }
        if (usuario.getSeccionSubSeccionCollection() == null) {
            usuario.setSeccionSubSeccionCollection(new ArrayList<SeccionSubSeccion>());
        }
        if (usuario.getSeccionSubSeccionCollection1() == null) {
            usuario.setSeccionSubSeccionCollection1(new ArrayList<SeccionSubSeccion>());
        }
        if (usuario.getPreferenciasCollection() == null) {
            usuario.setPreferenciasCollection(new ArrayList<Preferencias>());
        }
        if (usuario.getPlantillaDocumentalCollection() == null) {
            usuario.setPlantillaDocumentalCollection(new ArrayList<PlantillaDocumental>());
        }
        if (usuario.getPlantillaDocumentalCollection1() == null) {
            usuario.setPlantillaDocumentalCollection1(new ArrayList<PlantillaDocumental>());
        }
        if (usuario.getCargoCollection() == null) {
            usuario.setCargoCollection(new ArrayList<Cargo>());
        }
        if (usuario.getCargoCollection1() == null) {
            usuario.setCargoCollection1(new ArrayList<Cargo>());
        }
        if (usuario.getTipoDocumentoCollection() == null) {
            usuario.setTipoDocumentoCollection(new ArrayList<TipoDocumento>());
        }
        if (usuario.getTipoDocumentoCollection1() == null) {
            usuario.setTipoDocumentoCollection1(new ArrayList<TipoDocumento>());
        }
        if (usuario.getSubSerieCollection() == null) {
            usuario.setSubSerieCollection(new ArrayList<SubSerie>());
        }
        if (usuario.getSubSerieCollection1() == null) {
            usuario.setSubSerieCollection1(new ArrayList<SubSerie>());
        }
        if (usuario.getMunicipioCollection() == null) {
            usuario.setMunicipioCollection(new ArrayList<Municipio>());
        }
        if (usuario.getMunicipioCollection1() == null) {
            usuario.setMunicipioCollection1(new ArrayList<Municipio>());
        }
        if (usuario.getProcesoNegocioCollection() == null) {
            usuario.setProcesoNegocioCollection(new ArrayList<ProcesoNegocio>());
        }
        if (usuario.getProcesoNegocioCollection1() == null) {
            usuario.setProcesoNegocioCollection1(new ArrayList<ProcesoNegocio>());
        }
        if (usuario.getCorregimientoCollection() == null) {
            usuario.setCorregimientoCollection(new ArrayList<Corregimiento>());
        }
        if (usuario.getCorregimientoCollection1() == null) {
            usuario.setCorregimientoCollection1(new ArrayList<Corregimiento>());
        }
        if (usuario.getGrupoUsuarioCollection() == null) {
            usuario.setGrupoUsuarioCollection(new ArrayList<GrupoUsuario>());
        }
        if (usuario.getGrupoUsuarioCollection1() == null) {
            usuario.setGrupoUsuarioCollection1(new ArrayList<GrupoUsuario>());
        }
        if (usuario.getGrupoUsuarioCollection2() == null) {
            usuario.setGrupoUsuarioCollection2(new ArrayList<GrupoUsuario>());
        }
        if (usuario.getDestinatariosDocCollection() == null) {
            usuario.setDestinatariosDocCollection(new ArrayList<DestinatariosDoc>());
        }
        if (usuario.getDestinatariosDocCollection1() == null) {
            usuario.setDestinatariosDocCollection1(new ArrayList<DestinatariosDoc>());
        }
        if (usuario.getDestinatariosDocCollection2() == null) {
            usuario.setDestinatariosDocCollection2(new ArrayList<DestinatariosDoc>());
        }
        if (usuario.getAutorCollection() == null) {
            usuario.setAutorCollection(new ArrayList<Autor>());
        }
        if (usuario.getAutorCollection1() == null) {
            usuario.setAutorCollection1(new ArrayList<Autor>());
        }
        if (usuario.getNotificacionCollection() == null) {
            usuario.setNotificacionCollection(new ArrayList<Notificacion>());
        }
        if (usuario.getPaisCollection() == null) {
            usuario.setPaisCollection(new ArrayList<Pais>());
        }
        if (usuario.getPaisCollection1() == null) {
            usuario.setPaisCollection1(new ArrayList<Pais>());
        }
        if (usuario.getConsecutivoCollection() == null) {
            usuario.setConsecutivoCollection(new ArrayList<Consecutivo>());
        }
        if (usuario.getConsecutivoCollection1() == null) {
            usuario.setConsecutivoCollection1(new ArrayList<Consecutivo>());
        }
        if (usuario.getSignaturaTopograficaCollection() == null) {
            usuario.setSignaturaTopograficaCollection(new ArrayList<SignaturaTopografica>());
        }
        if (usuario.getSignaturaTopograficaCollection1() == null) {
            usuario.setSignaturaTopograficaCollection1(new ArrayList<SignaturaTopografica>());
        }
        if (usuario.getTipoDocumentalCollection() == null) {
            usuario.setTipoDocumentalCollection(new ArrayList<TipoDocumental>());
        }
        if (usuario.getTipoDocumentalCollection1() == null) {
            usuario.setTipoDocumentalCollection1(new ArrayList<TipoDocumental>());
        }
        if (usuario.getTransportadorCollection() == null) {
            usuario.setTransportadorCollection(new ArrayList<Transportador>());
        }
        if (usuario.getTransportadorCollection1() == null) {
            usuario.setTransportadorCollection1(new ArrayList<Transportador>());
        }
        if (usuario.getProcesodocumentalCollection() == null) {
            usuario.setProcesodocumentalCollection(new ArrayList<ProcesoDocumental>());
        }
        if (usuario.getProcesodocumentalCollection1() == null) {
            usuario.setProcesodocumentalCollection1(new ArrayList<ProcesoDocumental>());
        }
        if (usuario.getProcesoTipoDocumentoCollection() == null) {
            usuario.setProcesoTipoDocumentoCollection(new ArrayList<ProcesoTipoDocumento>());
        }
        if (usuario.getProcesoTipoDocumentoCollection1() == null) {
            usuario.setProcesoTipoDocumentoCollection1(new ArrayList<ProcesoTipoDocumento>());
        }
        if (usuario.getEntidadCollection() == null) {
            usuario.setEntidadCollection(new ArrayList<Entidad>());
        }
        if (usuario.getEntidadCollection1() == null) {
            usuario.setEntidadCollection1(new ArrayList<Entidad>());
        }
        if (usuario.getUnidadDocumentalCollection() == null) {
            usuario.setUnidadDocumentalCollection(new ArrayList<UnidadDocumental>());
        }
        if (usuario.getUnidadDocumentalCollection1() == null) {
            usuario.setUnidadDocumentalCollection1(new ArrayList<UnidadDocumental>());
        }
        if (usuario.getSerieCollection() == null) {
            usuario.setSerieCollection(new ArrayList<Serie>());
        }
        if (usuario.getSerieCollection1() == null) {
            usuario.setSerieCollection1(new ArrayList<Serie>());
        }
        if (usuario.getDepartamentoCollection() == null) {
            usuario.setDepartamentoCollection(new ArrayList<Departamento>());
        }
        if (usuario.getDepartamentoCollection1() == null) {
            usuario.setDepartamentoCollection1(new ArrayList<Departamento>());
        }
        if (usuario.getMonitoresProcesoCollection() == null) {
            usuario.setMonitoresProcesoCollection(new ArrayList<MonitoresProceso>());
        }
        if (usuario.getMonitoresProcesoCollection1() == null) {
            usuario.setMonitoresProcesoCollection1(new ArrayList<MonitoresProceso>());
        }
        if (usuario.getModuloCollection() == null) {
            usuario.setModuloCollection(new ArrayList<Modulo>());
        }
        if (usuario.getModuloCollection1() == null) {
            usuario.setModuloCollection1(new ArrayList<Modulo>());
        }
        if (usuario.getCamposCollection() == null) {
            usuario.setCamposCollection(new ArrayList<Campos>());
        }
        if (usuario.getCamposCollection1() == null) {
            usuario.setCamposCollection1(new ArrayList<Campos>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cargo cargo = usuario.getCargo();
            if (cargo != null) {
                cargo = em.getReference(cargo.getClass(), cargo.getId());
                usuario.setCargo(cargo);
            }
            Collection<ClaseDocumento> attachedClaseDocumentoCollection = new ArrayList<ClaseDocumento>();
            for (ClaseDocumento claseDocumentoCollectionClaseDocumentoToAttach : usuario.getClaseDocumentoCollection()) {
                claseDocumentoCollectionClaseDocumentoToAttach = em.getReference(claseDocumentoCollectionClaseDocumentoToAttach.getClass(), claseDocumentoCollectionClaseDocumentoToAttach.getId());
                attachedClaseDocumentoCollection.add(claseDocumentoCollectionClaseDocumentoToAttach);
            }
            usuario.setClaseDocumentoCollection(attachedClaseDocumentoCollection);
            Collection<ClaseDocumento> attachedClaseDocumentoCollection1 = new ArrayList<ClaseDocumento>();
            for (ClaseDocumento claseDocumentoCollection1ClaseDocumentoToAttach : usuario.getClaseDocumentoCollection1()) {
                claseDocumentoCollection1ClaseDocumentoToAttach = em.getReference(claseDocumentoCollection1ClaseDocumentoToAttach.getClass(), claseDocumentoCollection1ClaseDocumentoToAttach.getId());
                attachedClaseDocumentoCollection1.add(claseDocumentoCollection1ClaseDocumentoToAttach);
            }
            usuario.setClaseDocumentoCollection1(attachedClaseDocumentoCollection1);
            Collection<Grupo> attachedGrupoCollection = new ArrayList<Grupo>();
            for (Grupo grupoCollectionGrupoToAttach : usuario.getGrupoCollection()) {
                grupoCollectionGrupoToAttach = em.getReference(grupoCollectionGrupoToAttach.getClass(), grupoCollectionGrupoToAttach.getId());
                attachedGrupoCollection.add(grupoCollectionGrupoToAttach);
            }
            usuario.setGrupoCollection(attachedGrupoCollection);
            Collection<Documento> attachedDocumentoCollection = new ArrayList<Documento>();
            for (Documento documentoCollectionDocumentoToAttach : usuario.getDocumentoCollection()) {
                documentoCollectionDocumentoToAttach = em.getReference(documentoCollectionDocumentoToAttach.getClass(), documentoCollectionDocumentoToAttach.getId());
                attachedDocumentoCollection.add(documentoCollectionDocumentoToAttach);
            }
            usuario.setDocumentoCollection(attachedDocumentoCollection);
            Collection<Documento> attachedDocumentoCollection1 = new ArrayList<Documento>();
            for (Documento documentoCollection1DocumentoToAttach : usuario.getDocumentoCollection1()) {
                documentoCollection1DocumentoToAttach = em.getReference(documentoCollection1DocumentoToAttach.getClass(), documentoCollection1DocumentoToAttach.getId());
                attachedDocumentoCollection1.add(documentoCollection1DocumentoToAttach);
            }
            usuario.setDocumentoCollection1(attachedDocumentoCollection1);
            Collection<Acl> attachedAclCollection = new ArrayList<Acl>();
            for (Acl aclCollectionAclToAttach : usuario.getAclCollection()) {
                aclCollectionAclToAttach = em.getReference(aclCollectionAclToAttach.getClass(), aclCollectionAclToAttach.getId());
                attachedAclCollection.add(aclCollectionAclToAttach);
            }
            usuario.setAclCollection(attachedAclCollection);
            Collection<Acl> attachedAclCollection1 = new ArrayList<Acl>();
            for (Acl aclCollection1AclToAttach : usuario.getAclCollection1()) {
                aclCollection1AclToAttach = em.getReference(aclCollection1AclToAttach.getClass(), aclCollection1AclToAttach.getId());
                attachedAclCollection1.add(aclCollection1AclToAttach);
            }
            usuario.setAclCollection1(attachedAclCollection1);
            Collection<SeccionSubSeccion> attachedSeccionSubSeccionCollection = new ArrayList<SeccionSubSeccion>();
            for (SeccionSubSeccion seccionSubSeccionCollectionSeccionSubSeccionToAttach : usuario.getSeccionSubSeccionCollection()) {
                seccionSubSeccionCollectionSeccionSubSeccionToAttach = em.getReference(seccionSubSeccionCollectionSeccionSubSeccionToAttach.getClass(), seccionSubSeccionCollectionSeccionSubSeccionToAttach.getId());
                attachedSeccionSubSeccionCollection.add(seccionSubSeccionCollectionSeccionSubSeccionToAttach);
            }
            usuario.setSeccionSubSeccionCollection(attachedSeccionSubSeccionCollection);
            Collection<SeccionSubSeccion> attachedSeccionSubSeccionCollection1 = new ArrayList<SeccionSubSeccion>();
            for (SeccionSubSeccion seccionSubSeccionCollection1SeccionSubSeccionToAttach : usuario.getSeccionSubSeccionCollection1()) {
                seccionSubSeccionCollection1SeccionSubSeccionToAttach = em.getReference(seccionSubSeccionCollection1SeccionSubSeccionToAttach.getClass(), seccionSubSeccionCollection1SeccionSubSeccionToAttach.getId());
                attachedSeccionSubSeccionCollection1.add(seccionSubSeccionCollection1SeccionSubSeccionToAttach);
            }
            usuario.setSeccionSubSeccionCollection1(attachedSeccionSubSeccionCollection1);
            Collection<Preferencias> attachedPreferenciasCollection = new ArrayList<Preferencias>();
            for (Preferencias preferenciasCollectionPreferenciasToAttach : usuario.getPreferenciasCollection()) {
                preferenciasCollectionPreferenciasToAttach = em.getReference(preferenciasCollectionPreferenciasToAttach.getClass(), preferenciasCollectionPreferenciasToAttach.getId());
                attachedPreferenciasCollection.add(preferenciasCollectionPreferenciasToAttach);
            }
            usuario.setPreferenciasCollection(attachedPreferenciasCollection);
            Collection<PlantillaDocumental> attachedPlantillaDocumentalCollection = new ArrayList<PlantillaDocumental>();
            for (PlantillaDocumental plantillaDocumentalCollectionPlantillaDocumentalToAttach : usuario.getPlantillaDocumentalCollection()) {
                plantillaDocumentalCollectionPlantillaDocumentalToAttach = em.getReference(plantillaDocumentalCollectionPlantillaDocumentalToAttach.getClass(), plantillaDocumentalCollectionPlantillaDocumentalToAttach.getId());
                attachedPlantillaDocumentalCollection.add(plantillaDocumentalCollectionPlantillaDocumentalToAttach);
            }
            usuario.setPlantillaDocumentalCollection(attachedPlantillaDocumentalCollection);
            Collection<PlantillaDocumental> attachedPlantillaDocumentalCollection1 = new ArrayList<PlantillaDocumental>();
            for (PlantillaDocumental plantillaDocumentalCollection1PlantillaDocumentalToAttach : usuario.getPlantillaDocumentalCollection1()) {
                plantillaDocumentalCollection1PlantillaDocumentalToAttach = em.getReference(plantillaDocumentalCollection1PlantillaDocumentalToAttach.getClass(), plantillaDocumentalCollection1PlantillaDocumentalToAttach.getId());
                attachedPlantillaDocumentalCollection1.add(plantillaDocumentalCollection1PlantillaDocumentalToAttach);
            }
            usuario.setPlantillaDocumentalCollection1(attachedPlantillaDocumentalCollection1);
            Collection<Cargo> attachedCargoCollection = new ArrayList<Cargo>();
            for (Cargo cargoCollectionCargoToAttach : usuario.getCargoCollection()) {
                cargoCollectionCargoToAttach = em.getReference(cargoCollectionCargoToAttach.getClass(), cargoCollectionCargoToAttach.getId());
                attachedCargoCollection.add(cargoCollectionCargoToAttach);
            }
            usuario.setCargoCollection(attachedCargoCollection);
            Collection<Cargo> attachedCargoCollection1 = new ArrayList<Cargo>();
            for (Cargo cargoCollection1CargoToAttach : usuario.getCargoCollection1()) {
                cargoCollection1CargoToAttach = em.getReference(cargoCollection1CargoToAttach.getClass(), cargoCollection1CargoToAttach.getId());
                attachedCargoCollection1.add(cargoCollection1CargoToAttach);
            }
            usuario.setCargoCollection1(attachedCargoCollection1);
            Collection<TipoDocumento> attachedTipoDocumentoCollection = new ArrayList<TipoDocumento>();
            for (TipoDocumento tipoDocumentoCollectionTipoDocumentoToAttach : usuario.getTipoDocumentoCollection()) {
                tipoDocumentoCollectionTipoDocumentoToAttach = em.getReference(tipoDocumentoCollectionTipoDocumentoToAttach.getClass(), tipoDocumentoCollectionTipoDocumentoToAttach.getId());
                attachedTipoDocumentoCollection.add(tipoDocumentoCollectionTipoDocumentoToAttach);
            }
            usuario.setTipoDocumentoCollection(attachedTipoDocumentoCollection);
            Collection<TipoDocumento> attachedTipoDocumentoCollection1 = new ArrayList<TipoDocumento>();
            for (TipoDocumento tipoDocumentoCollection1TipoDocumentoToAttach : usuario.getTipoDocumentoCollection1()) {
                tipoDocumentoCollection1TipoDocumentoToAttach = em.getReference(tipoDocumentoCollection1TipoDocumentoToAttach.getClass(), tipoDocumentoCollection1TipoDocumentoToAttach.getId());
                attachedTipoDocumentoCollection1.add(tipoDocumentoCollection1TipoDocumentoToAttach);
            }
            usuario.setTipoDocumentoCollection1(attachedTipoDocumentoCollection1);
            Collection<SubSerie> attachedSubSerieCollection = new ArrayList<SubSerie>();
            for (SubSerie subSerieCollectionSubSerieToAttach : usuario.getSubSerieCollection()) {
                subSerieCollectionSubSerieToAttach = em.getReference(subSerieCollectionSubSerieToAttach.getClass(), subSerieCollectionSubSerieToAttach.getId());
                attachedSubSerieCollection.add(subSerieCollectionSubSerieToAttach);
            }
            usuario.setSubSerieCollection(attachedSubSerieCollection);
            Collection<SubSerie> attachedSubSerieCollection1 = new ArrayList<SubSerie>();
            for (SubSerie subSerieCollection1SubSerieToAttach : usuario.getSubSerieCollection1()) {
                subSerieCollection1SubSerieToAttach = em.getReference(subSerieCollection1SubSerieToAttach.getClass(), subSerieCollection1SubSerieToAttach.getId());
                attachedSubSerieCollection1.add(subSerieCollection1SubSerieToAttach);
            }
            usuario.setSubSerieCollection1(attachedSubSerieCollection1);
            Collection<Municipio> attachedMunicipioCollection = new ArrayList<Municipio>();
            for (Municipio municipioCollectionMunicipioToAttach : usuario.getMunicipioCollection()) {
                municipioCollectionMunicipioToAttach = em.getReference(municipioCollectionMunicipioToAttach.getClass(), municipioCollectionMunicipioToAttach.getId());
                attachedMunicipioCollection.add(municipioCollectionMunicipioToAttach);
            }
            usuario.setMunicipioCollection(attachedMunicipioCollection);
            Collection<Municipio> attachedMunicipioCollection1 = new ArrayList<Municipio>();
            for (Municipio municipioCollection1MunicipioToAttach : usuario.getMunicipioCollection1()) {
                municipioCollection1MunicipioToAttach = em.getReference(municipioCollection1MunicipioToAttach.getClass(), municipioCollection1MunicipioToAttach.getId());
                attachedMunicipioCollection1.add(municipioCollection1MunicipioToAttach);
            }
            usuario.setMunicipioCollection1(attachedMunicipioCollection1);
            Collection<ProcesoNegocio> attachedProcesoNegocioCollection = new ArrayList<ProcesoNegocio>();
            for (ProcesoNegocio procesoNegocioCollectionProcesoNegocioToAttach : usuario.getProcesoNegocioCollection()) {
                procesoNegocioCollectionProcesoNegocioToAttach = em.getReference(procesoNegocioCollectionProcesoNegocioToAttach.getClass(), procesoNegocioCollectionProcesoNegocioToAttach.getId());
                attachedProcesoNegocioCollection.add(procesoNegocioCollectionProcesoNegocioToAttach);
            }
            usuario.setProcesoNegocioCollection(attachedProcesoNegocioCollection);
            Collection<ProcesoNegocio> attachedProcesoNegocioCollection1 = new ArrayList<ProcesoNegocio>();
            for (ProcesoNegocio procesoNegocioCollection1ProcesoNegocioToAttach : usuario.getProcesoNegocioCollection1()) {
                procesoNegocioCollection1ProcesoNegocioToAttach = em.getReference(procesoNegocioCollection1ProcesoNegocioToAttach.getClass(), procesoNegocioCollection1ProcesoNegocioToAttach.getId());
                attachedProcesoNegocioCollection1.add(procesoNegocioCollection1ProcesoNegocioToAttach);
            }
            usuario.setProcesoNegocioCollection1(attachedProcesoNegocioCollection1);
            Collection<Corregimiento> attachedCorregimientoCollection = new ArrayList<Corregimiento>();
            for (Corregimiento corregimientoCollectionCorregimientoToAttach : usuario.getCorregimientoCollection()) {
                corregimientoCollectionCorregimientoToAttach = em.getReference(corregimientoCollectionCorregimientoToAttach.getClass(), corregimientoCollectionCorregimientoToAttach.getId());
                attachedCorregimientoCollection.add(corregimientoCollectionCorregimientoToAttach);
            }
            usuario.setCorregimientoCollection(attachedCorregimientoCollection);
            Collection<Corregimiento> attachedCorregimientoCollection1 = new ArrayList<Corregimiento>();
            for (Corregimiento corregimientoCollection1CorregimientoToAttach : usuario.getCorregimientoCollection1()) {
                corregimientoCollection1CorregimientoToAttach = em.getReference(corregimientoCollection1CorregimientoToAttach.getClass(), corregimientoCollection1CorregimientoToAttach.getId());
                attachedCorregimientoCollection1.add(corregimientoCollection1CorregimientoToAttach);
            }
            usuario.setCorregimientoCollection1(attachedCorregimientoCollection1);
            Collection<GrupoUsuario> attachedGrupoUsuarioCollection = new ArrayList<GrupoUsuario>();
            for (GrupoUsuario grupoUsuarioCollectionGrupoUsuarioToAttach : usuario.getGrupoUsuarioCollection()) {
                grupoUsuarioCollectionGrupoUsuarioToAttach = em.getReference(grupoUsuarioCollectionGrupoUsuarioToAttach.getClass(), grupoUsuarioCollectionGrupoUsuarioToAttach.getId());
                attachedGrupoUsuarioCollection.add(grupoUsuarioCollectionGrupoUsuarioToAttach);
            }
            usuario.setGrupoUsuarioCollection(attachedGrupoUsuarioCollection);
            Collection<GrupoUsuario> attachedGrupoUsuarioCollection1 = new ArrayList<GrupoUsuario>();
            for (GrupoUsuario grupoUsuarioCollection1GrupoUsuarioToAttach : usuario.getGrupoUsuarioCollection1()) {
                grupoUsuarioCollection1GrupoUsuarioToAttach = em.getReference(grupoUsuarioCollection1GrupoUsuarioToAttach.getClass(), grupoUsuarioCollection1GrupoUsuarioToAttach.getId());
                attachedGrupoUsuarioCollection1.add(grupoUsuarioCollection1GrupoUsuarioToAttach);
            }
            usuario.setGrupoUsuarioCollection1(attachedGrupoUsuarioCollection1);
            Collection<GrupoUsuario> attachedGrupoUsuarioCollection2 = new ArrayList<GrupoUsuario>();
            for (GrupoUsuario grupoUsuarioCollection2GrupoUsuarioToAttach : usuario.getGrupoUsuarioCollection2()) {
                grupoUsuarioCollection2GrupoUsuarioToAttach = em.getReference(grupoUsuarioCollection2GrupoUsuarioToAttach.getClass(), grupoUsuarioCollection2GrupoUsuarioToAttach.getId());
                attachedGrupoUsuarioCollection2.add(grupoUsuarioCollection2GrupoUsuarioToAttach);
            }
            usuario.setGrupoUsuarioCollection2(attachedGrupoUsuarioCollection2);
            Collection<DestinatariosDoc> attachedDestinatariosDocCollection = new ArrayList<DestinatariosDoc>();
            for (DestinatariosDoc destinatariosDocCollectionDestinatariosDocToAttach : usuario.getDestinatariosDocCollection()) {
                destinatariosDocCollectionDestinatariosDocToAttach = em.getReference(destinatariosDocCollectionDestinatariosDocToAttach.getClass(), destinatariosDocCollectionDestinatariosDocToAttach.getId());
                attachedDestinatariosDocCollection.add(destinatariosDocCollectionDestinatariosDocToAttach);
            }
            usuario.setDestinatariosDocCollection(attachedDestinatariosDocCollection);
            Collection<DestinatariosDoc> attachedDestinatariosDocCollection1 = new ArrayList<DestinatariosDoc>();
            for (DestinatariosDoc destinatariosDocCollection1DestinatariosDocToAttach : usuario.getDestinatariosDocCollection1()) {
                destinatariosDocCollection1DestinatariosDocToAttach = em.getReference(destinatariosDocCollection1DestinatariosDocToAttach.getClass(), destinatariosDocCollection1DestinatariosDocToAttach.getId());
                attachedDestinatariosDocCollection1.add(destinatariosDocCollection1DestinatariosDocToAttach);
            }
            usuario.setDestinatariosDocCollection1(attachedDestinatariosDocCollection1);
            Collection<DestinatariosDoc> attachedDestinatariosDocCollection2 = new ArrayList<DestinatariosDoc>();
            for (DestinatariosDoc destinatariosDocCollection2DestinatariosDocToAttach : usuario.getDestinatariosDocCollection2()) {
                destinatariosDocCollection2DestinatariosDocToAttach = em.getReference(destinatariosDocCollection2DestinatariosDocToAttach.getClass(), destinatariosDocCollection2DestinatariosDocToAttach.getId());
                attachedDestinatariosDocCollection2.add(destinatariosDocCollection2DestinatariosDocToAttach);
            }
            usuario.setDestinatariosDocCollection2(attachedDestinatariosDocCollection2);
            Collection<Autor> attachedAutorCollection = new ArrayList<Autor>();
            for (Autor autorCollectionAutorToAttach : usuario.getAutorCollection()) {
                autorCollectionAutorToAttach = em.getReference(autorCollectionAutorToAttach.getClass(), autorCollectionAutorToAttach.getId());
                attachedAutorCollection.add(autorCollectionAutorToAttach);
            }
            usuario.setAutorCollection(attachedAutorCollection);
            Collection<Autor> attachedAutorCollection1 = new ArrayList<Autor>();
            for (Autor autorCollection1AutorToAttach : usuario.getAutorCollection1()) {
                autorCollection1AutorToAttach = em.getReference(autorCollection1AutorToAttach.getClass(), autorCollection1AutorToAttach.getId());
                attachedAutorCollection1.add(autorCollection1AutorToAttach);
            }
            usuario.setAutorCollection1(attachedAutorCollection1);
            Collection<Notificacion> attachedNotificacionCollection = new ArrayList<Notificacion>();
            for (Notificacion notificacionCollectionNotificacionToAttach : usuario.getNotificacionCollection()) {
                notificacionCollectionNotificacionToAttach = em.getReference(notificacionCollectionNotificacionToAttach.getClass(), notificacionCollectionNotificacionToAttach.getId());
                attachedNotificacionCollection.add(notificacionCollectionNotificacionToAttach);
            }
            usuario.setNotificacionCollection(attachedNotificacionCollection);
            Collection<Pais> attachedPaisCollection = new ArrayList<Pais>();
            for (Pais paisCollectionPaisToAttach : usuario.getPaisCollection()) {
                paisCollectionPaisToAttach = em.getReference(paisCollectionPaisToAttach.getClass(), paisCollectionPaisToAttach.getId());
                attachedPaisCollection.add(paisCollectionPaisToAttach);
            }
            usuario.setPaisCollection(attachedPaisCollection);
            Collection<Pais> attachedPaisCollection1 = new ArrayList<Pais>();
            for (Pais paisCollection1PaisToAttach : usuario.getPaisCollection1()) {
                paisCollection1PaisToAttach = em.getReference(paisCollection1PaisToAttach.getClass(), paisCollection1PaisToAttach.getId());
                attachedPaisCollection1.add(paisCollection1PaisToAttach);
            }
            usuario.setPaisCollection1(attachedPaisCollection1);
            Collection<Consecutivo> attachedConsecutivoCollection = new ArrayList<Consecutivo>();
            for (Consecutivo consecutivoCollectionConsecutivoToAttach : usuario.getConsecutivoCollection()) {
                consecutivoCollectionConsecutivoToAttach = em.getReference(consecutivoCollectionConsecutivoToAttach.getClass(), consecutivoCollectionConsecutivoToAttach.getId());
                attachedConsecutivoCollection.add(consecutivoCollectionConsecutivoToAttach);
            }
            usuario.setConsecutivoCollection(attachedConsecutivoCollection);
            Collection<Consecutivo> attachedConsecutivoCollection1 = new ArrayList<Consecutivo>();
            for (Consecutivo consecutivoCollection1ConsecutivoToAttach : usuario.getConsecutivoCollection1()) {
                consecutivoCollection1ConsecutivoToAttach = em.getReference(consecutivoCollection1ConsecutivoToAttach.getClass(), consecutivoCollection1ConsecutivoToAttach.getId());
                attachedConsecutivoCollection1.add(consecutivoCollection1ConsecutivoToAttach);
            }
            usuario.setConsecutivoCollection1(attachedConsecutivoCollection1);
            Collection<SignaturaTopografica> attachedSignaturaTopograficaCollection = new ArrayList<SignaturaTopografica>();
            for (SignaturaTopografica signaturaTopograficaCollectionSignaturaTopograficaToAttach : usuario.getSignaturaTopograficaCollection()) {
                signaturaTopograficaCollectionSignaturaTopograficaToAttach = em.getReference(signaturaTopograficaCollectionSignaturaTopograficaToAttach.getClass(), signaturaTopograficaCollectionSignaturaTopograficaToAttach.getId());
                attachedSignaturaTopograficaCollection.add(signaturaTopograficaCollectionSignaturaTopograficaToAttach);
            }
            usuario.setSignaturaTopograficaCollection(attachedSignaturaTopograficaCollection);
            Collection<SignaturaTopografica> attachedSignaturaTopograficaCollection1 = new ArrayList<SignaturaTopografica>();
            for (SignaturaTopografica signaturaTopograficaCollection1SignaturaTopograficaToAttach : usuario.getSignaturaTopograficaCollection1()) {
                signaturaTopograficaCollection1SignaturaTopograficaToAttach = em.getReference(signaturaTopograficaCollection1SignaturaTopograficaToAttach.getClass(), signaturaTopograficaCollection1SignaturaTopograficaToAttach.getId());
                attachedSignaturaTopograficaCollection1.add(signaturaTopograficaCollection1SignaturaTopograficaToAttach);
            }
            usuario.setSignaturaTopograficaCollection1(attachedSignaturaTopograficaCollection1);
            Collection<TipoDocumental> attachedTipoDocumentalCollection = new ArrayList<TipoDocumental>();
            for (TipoDocumental tipoDocumentalCollectionTipoDocumentalToAttach : usuario.getTipoDocumentalCollection()) {
                tipoDocumentalCollectionTipoDocumentalToAttach = em.getReference(tipoDocumentalCollectionTipoDocumentalToAttach.getClass(), tipoDocumentalCollectionTipoDocumentalToAttach.getId());
                attachedTipoDocumentalCollection.add(tipoDocumentalCollectionTipoDocumentalToAttach);
            }
            usuario.setTipoDocumentalCollection(attachedTipoDocumentalCollection);
            Collection<TipoDocumental> attachedTipoDocumentalCollection1 = new ArrayList<TipoDocumental>();
            for (TipoDocumental tipoDocumentalCollection1TipoDocumentalToAttach : usuario.getTipoDocumentalCollection1()) {
                tipoDocumentalCollection1TipoDocumentalToAttach = em.getReference(tipoDocumentalCollection1TipoDocumentalToAttach.getClass(), tipoDocumentalCollection1TipoDocumentalToAttach.getId());
                attachedTipoDocumentalCollection1.add(tipoDocumentalCollection1TipoDocumentalToAttach);
            }
            usuario.setTipoDocumentalCollection1(attachedTipoDocumentalCollection1);
            Collection<Transportador> attachedTransportadorCollection = new ArrayList<Transportador>();
            for (Transportador transportadorCollectionTransportadorToAttach : usuario.getTransportadorCollection()) {
                transportadorCollectionTransportadorToAttach = em.getReference(transportadorCollectionTransportadorToAttach.getClass(), transportadorCollectionTransportadorToAttach.getId());
                attachedTransportadorCollection.add(transportadorCollectionTransportadorToAttach);
            }
            usuario.setTransportadorCollection(attachedTransportadorCollection);
            Collection<Transportador> attachedTransportadorCollection1 = new ArrayList<Transportador>();
            for (Transportador transportadorCollection1TransportadorToAttach : usuario.getTransportadorCollection1()) {
                transportadorCollection1TransportadorToAttach = em.getReference(transportadorCollection1TransportadorToAttach.getClass(), transportadorCollection1TransportadorToAttach.getId());
                attachedTransportadorCollection1.add(transportadorCollection1TransportadorToAttach);
            }
            usuario.setTransportadorCollection1(attachedTransportadorCollection1);
            Collection<ProcesoDocumental> attachedProcesodocumentalCollection = new ArrayList<ProcesoDocumental>();
            for (ProcesoDocumental procesodocumentalCollectionProcesoDocumentalToAttach : usuario.getProcesodocumentalCollection()) {
                procesodocumentalCollectionProcesoDocumentalToAttach = em.getReference(procesodocumentalCollectionProcesoDocumentalToAttach.getClass(), procesodocumentalCollectionProcesoDocumentalToAttach.getId());
                attachedProcesodocumentalCollection.add(procesodocumentalCollectionProcesoDocumentalToAttach);
            }
            usuario.setProcesodocumentalCollection(attachedProcesodocumentalCollection);
            Collection<ProcesoDocumental> attachedProcesodocumentalCollection1 = new ArrayList<ProcesoDocumental>();
            for (ProcesoDocumental procesodocumentalCollection1ProcesoDocumentalToAttach : usuario.getProcesodocumentalCollection1()) {
                procesodocumentalCollection1ProcesoDocumentalToAttach = em.getReference(procesodocumentalCollection1ProcesoDocumentalToAttach.getClass(), procesodocumentalCollection1ProcesoDocumentalToAttach.getId());
                attachedProcesodocumentalCollection1.add(procesodocumentalCollection1ProcesoDocumentalToAttach);
            }
            usuario.setProcesodocumentalCollection1(attachedProcesodocumentalCollection1);
            Collection<ProcesoTipoDocumento> attachedProcesoTipoDocumentoCollection = new ArrayList<ProcesoTipoDocumento>();
            for (ProcesoTipoDocumento procesoTipoDocumentoCollectionProcesoTipoDocumentoToAttach : usuario.getProcesoTipoDocumentoCollection()) {
                procesoTipoDocumentoCollectionProcesoTipoDocumentoToAttach = em.getReference(procesoTipoDocumentoCollectionProcesoTipoDocumentoToAttach.getClass(), procesoTipoDocumentoCollectionProcesoTipoDocumentoToAttach.getId());
                attachedProcesoTipoDocumentoCollection.add(procesoTipoDocumentoCollectionProcesoTipoDocumentoToAttach);
            }
            usuario.setProcesoTipoDocumentoCollection(attachedProcesoTipoDocumentoCollection);
            Collection<ProcesoTipoDocumento> attachedProcesoTipoDocumentoCollection1 = new ArrayList<ProcesoTipoDocumento>();
            for (ProcesoTipoDocumento procesoTipoDocumentoCollection1ProcesoTipoDocumentoToAttach : usuario.getProcesoTipoDocumentoCollection1()) {
                procesoTipoDocumentoCollection1ProcesoTipoDocumentoToAttach = em.getReference(procesoTipoDocumentoCollection1ProcesoTipoDocumentoToAttach.getClass(), procesoTipoDocumentoCollection1ProcesoTipoDocumentoToAttach.getId());
                attachedProcesoTipoDocumentoCollection1.add(procesoTipoDocumentoCollection1ProcesoTipoDocumentoToAttach);
            }
            usuario.setProcesoTipoDocumentoCollection1(attachedProcesoTipoDocumentoCollection1);
            Collection<Entidad> attachedEntidadCollection = new ArrayList<Entidad>();
            for (Entidad entidadCollectionEntidadToAttach : usuario.getEntidadCollection()) {
                entidadCollectionEntidadToAttach = em.getReference(entidadCollectionEntidadToAttach.getClass(), entidadCollectionEntidadToAttach.getId());
                attachedEntidadCollection.add(entidadCollectionEntidadToAttach);
            }
            usuario.setEntidadCollection(attachedEntidadCollection);
            Collection<Entidad> attachedEntidadCollection1 = new ArrayList<Entidad>();
            for (Entidad entidadCollection1EntidadToAttach : usuario.getEntidadCollection1()) {
                entidadCollection1EntidadToAttach = em.getReference(entidadCollection1EntidadToAttach.getClass(), entidadCollection1EntidadToAttach.getId());
                attachedEntidadCollection1.add(entidadCollection1EntidadToAttach);
            }
            usuario.setEntidadCollection1(attachedEntidadCollection1);
            Collection<UnidadDocumental> attachedUnidadDocumentalCollection = new ArrayList<UnidadDocumental>();
            for (UnidadDocumental unidadDocumentalCollectionUnidadDocumentalToAttach : usuario.getUnidadDocumentalCollection()) {
                unidadDocumentalCollectionUnidadDocumentalToAttach = em.getReference(unidadDocumentalCollectionUnidadDocumentalToAttach.getClass(), unidadDocumentalCollectionUnidadDocumentalToAttach.getId());
                attachedUnidadDocumentalCollection.add(unidadDocumentalCollectionUnidadDocumentalToAttach);
            }
            usuario.setUnidadDocumentalCollection(attachedUnidadDocumentalCollection);
            Collection<UnidadDocumental> attachedUnidadDocumentalCollection1 = new ArrayList<UnidadDocumental>();
            for (UnidadDocumental unidadDocumentalCollection1UnidadDocumentalToAttach : usuario.getUnidadDocumentalCollection1()) {
                unidadDocumentalCollection1UnidadDocumentalToAttach = em.getReference(unidadDocumentalCollection1UnidadDocumentalToAttach.getClass(), unidadDocumentalCollection1UnidadDocumentalToAttach.getId());
                attachedUnidadDocumentalCollection1.add(unidadDocumentalCollection1UnidadDocumentalToAttach);
            }
            usuario.setUnidadDocumentalCollection1(attachedUnidadDocumentalCollection1);
            Collection<Serie> attachedSerieCollection = new ArrayList<Serie>();
            for (Serie serieCollectionSerieToAttach : usuario.getSerieCollection()) {
                serieCollectionSerieToAttach = em.getReference(serieCollectionSerieToAttach.getClass(), serieCollectionSerieToAttach.getId());
                attachedSerieCollection.add(serieCollectionSerieToAttach);
            }
            usuario.setSerieCollection(attachedSerieCollection);
            Collection<Serie> attachedSerieCollection1 = new ArrayList<Serie>();
            for (Serie serieCollection1SerieToAttach : usuario.getSerieCollection1()) {
                serieCollection1SerieToAttach = em.getReference(serieCollection1SerieToAttach.getClass(), serieCollection1SerieToAttach.getId());
                attachedSerieCollection1.add(serieCollection1SerieToAttach);
            }
            usuario.setSerieCollection1(attachedSerieCollection1);
            Collection<Departamento> attachedDepartamentoCollection = new ArrayList<Departamento>();
            for (Departamento departamentoCollectionDepartamentoToAttach : usuario.getDepartamentoCollection()) {
                departamentoCollectionDepartamentoToAttach = em.getReference(departamentoCollectionDepartamentoToAttach.getClass(), departamentoCollectionDepartamentoToAttach.getId());
                attachedDepartamentoCollection.add(departamentoCollectionDepartamentoToAttach);
            }
            usuario.setDepartamentoCollection(attachedDepartamentoCollection);
            Collection<Departamento> attachedDepartamentoCollection1 = new ArrayList<Departamento>();
            for (Departamento departamentoCollection1DepartamentoToAttach : usuario.getDepartamentoCollection1()) {
                departamentoCollection1DepartamentoToAttach = em.getReference(departamentoCollection1DepartamentoToAttach.getClass(), departamentoCollection1DepartamentoToAttach.getId());
                attachedDepartamentoCollection1.add(departamentoCollection1DepartamentoToAttach);
            }
            usuario.setDepartamentoCollection1(attachedDepartamentoCollection1);
            Collection<MonitoresProceso> attachedMonitoresProcesoCollection = new ArrayList<MonitoresProceso>();
            for (MonitoresProceso monitoresProcesoCollectionMonitoresProcesoToAttach : usuario.getMonitoresProcesoCollection()) {
                monitoresProcesoCollectionMonitoresProcesoToAttach = em.getReference(monitoresProcesoCollectionMonitoresProcesoToAttach.getClass(), monitoresProcesoCollectionMonitoresProcesoToAttach.getId());
                attachedMonitoresProcesoCollection.add(monitoresProcesoCollectionMonitoresProcesoToAttach);
            }
            usuario.setMonitoresProcesoCollection(attachedMonitoresProcesoCollection);
            Collection<MonitoresProceso> attachedMonitoresProcesoCollection1 = new ArrayList<MonitoresProceso>();
            for (MonitoresProceso monitoresProcesoCollection1MonitoresProcesoToAttach : usuario.getMonitoresProcesoCollection1()) {
                monitoresProcesoCollection1MonitoresProcesoToAttach = em.getReference(monitoresProcesoCollection1MonitoresProcesoToAttach.getClass(), monitoresProcesoCollection1MonitoresProcesoToAttach.getId());
                attachedMonitoresProcesoCollection1.add(monitoresProcesoCollection1MonitoresProcesoToAttach);
            }
            usuario.setMonitoresProcesoCollection1(attachedMonitoresProcesoCollection1);
            Collection<Modulo> attachedModuloCollection = new ArrayList<Modulo>();
            for (Modulo moduloCollectionModuloToAttach : usuario.getModuloCollection()) {
                moduloCollectionModuloToAttach = em.getReference(moduloCollectionModuloToAttach.getClass(), moduloCollectionModuloToAttach.getId());
                attachedModuloCollection.add(moduloCollectionModuloToAttach);
            }
            usuario.setModuloCollection(attachedModuloCollection);
            Collection<Modulo> attachedModuloCollection1 = new ArrayList<Modulo>();
            for (Modulo moduloCollection1ModuloToAttach : usuario.getModuloCollection1()) {
                moduloCollection1ModuloToAttach = em.getReference(moduloCollection1ModuloToAttach.getClass(), moduloCollection1ModuloToAttach.getId());
                attachedModuloCollection1.add(moduloCollection1ModuloToAttach);
            }
            usuario.setModuloCollection1(attachedModuloCollection1);
            Collection<Campos> attachedCamposCollection = new ArrayList<Campos>();
            for (Campos camposCollectionCamposToAttach : usuario.getCamposCollection()) {
                camposCollectionCamposToAttach = em.getReference(camposCollectionCamposToAttach.getClass(), camposCollectionCamposToAttach.getId());
                attachedCamposCollection.add(camposCollectionCamposToAttach);
            }
            usuario.setCamposCollection(attachedCamposCollection);
            Collection<Campos> attachedCamposCollection1 = new ArrayList<Campos>();
            for (Campos camposCollection1CamposToAttach : usuario.getCamposCollection1()) {
                camposCollection1CamposToAttach = em.getReference(camposCollection1CamposToAttach.getClass(), camposCollection1CamposToAttach.getId());
                attachedCamposCollection1.add(camposCollection1CamposToAttach);
            }
            usuario.setCamposCollection1(attachedCamposCollection1);
            em.persist(usuario);
            if (cargo != null) {
                Usuario oldCreadoPorOfCargo = cargo.getCreadoPor();
                if (oldCreadoPorOfCargo != null) {
                    oldCreadoPorOfCargo.setCargo(null);
                    oldCreadoPorOfCargo = em.merge(oldCreadoPorOfCargo);
                }
                cargo.setCreadoPor(usuario);
                cargo = em.merge(cargo);
            }
            for (ClaseDocumento claseDocumentoCollectionClaseDocumento : usuario.getClaseDocumentoCollection()) {
                Usuario oldCreadoPorOfClaseDocumentoCollectionClaseDocumento = claseDocumentoCollectionClaseDocumento.getCreadoPor();
                claseDocumentoCollectionClaseDocumento.setCreadoPor(usuario);
                claseDocumentoCollectionClaseDocumento = em.merge(claseDocumentoCollectionClaseDocumento);
                if (oldCreadoPorOfClaseDocumentoCollectionClaseDocumento != null) {
                    oldCreadoPorOfClaseDocumentoCollectionClaseDocumento.getClaseDocumentoCollection().remove(claseDocumentoCollectionClaseDocumento);
                    oldCreadoPorOfClaseDocumentoCollectionClaseDocumento = em.merge(oldCreadoPorOfClaseDocumentoCollectionClaseDocumento);
                }
            }
            for (ClaseDocumento claseDocumentoCollection1ClaseDocumento : usuario.getClaseDocumentoCollection1()) {
                Usuario oldModificadoPorOfClaseDocumentoCollection1ClaseDocumento = claseDocumentoCollection1ClaseDocumento.getModificadoPor();
                claseDocumentoCollection1ClaseDocumento.setModificadoPor(usuario);
                claseDocumentoCollection1ClaseDocumento = em.merge(claseDocumentoCollection1ClaseDocumento);
                if (oldModificadoPorOfClaseDocumentoCollection1ClaseDocumento != null) {
                    oldModificadoPorOfClaseDocumentoCollection1ClaseDocumento.getClaseDocumentoCollection1().remove(claseDocumentoCollection1ClaseDocumento);
                    oldModificadoPorOfClaseDocumentoCollection1ClaseDocumento = em.merge(oldModificadoPorOfClaseDocumentoCollection1ClaseDocumento);
                }
            }
            for (Grupo grupoCollectionGrupo : usuario.getGrupoCollection()) {
                Usuario oldModificadoPorOfGrupoCollectionGrupo = grupoCollectionGrupo.getModificadoPor();
                grupoCollectionGrupo.setModificadoPor(usuario);
                grupoCollectionGrupo = em.merge(grupoCollectionGrupo);
                if (oldModificadoPorOfGrupoCollectionGrupo != null) {
                    oldModificadoPorOfGrupoCollectionGrupo.getGrupoCollection().remove(grupoCollectionGrupo);
                    oldModificadoPorOfGrupoCollectionGrupo = em.merge(oldModificadoPorOfGrupoCollectionGrupo);
                }
            }
            for (Documento documentoCollectionDocumento : usuario.getDocumentoCollection()) {
                Usuario oldCreadoPorOfDocumentoCollectionDocumento = documentoCollectionDocumento.getCreadoPor();
                documentoCollectionDocumento.setCreadoPor(usuario);
                documentoCollectionDocumento = em.merge(documentoCollectionDocumento);
                if (oldCreadoPorOfDocumentoCollectionDocumento != null) {
                    oldCreadoPorOfDocumentoCollectionDocumento.getDocumentoCollection().remove(documentoCollectionDocumento);
                    oldCreadoPorOfDocumentoCollectionDocumento = em.merge(oldCreadoPorOfDocumentoCollectionDocumento);
                }
            }
            for (Documento documentoCollection1Documento : usuario.getDocumentoCollection1()) {
                Usuario oldModificadoPorOfDocumentoCollection1Documento = documentoCollection1Documento.getModificadoPor();
                documentoCollection1Documento.setModificadoPor(usuario);
                documentoCollection1Documento = em.merge(documentoCollection1Documento);
                if (oldModificadoPorOfDocumentoCollection1Documento != null) {
                    oldModificadoPorOfDocumentoCollection1Documento.getDocumentoCollection1().remove(documentoCollection1Documento);
                    oldModificadoPorOfDocumentoCollection1Documento = em.merge(oldModificadoPorOfDocumentoCollection1Documento);
                }
            }
            for (Acl aclCollectionAcl : usuario.getAclCollection()) {
                Usuario oldCreadoPorOfAclCollectionAcl = aclCollectionAcl.getCreadoPor();
                aclCollectionAcl.setCreadoPor(usuario);
                aclCollectionAcl = em.merge(aclCollectionAcl);
                if (oldCreadoPorOfAclCollectionAcl != null) {
                    oldCreadoPorOfAclCollectionAcl.getAclCollection().remove(aclCollectionAcl);
                    oldCreadoPorOfAclCollectionAcl = em.merge(oldCreadoPorOfAclCollectionAcl);
                }
            }
            for (Acl aclCollection1Acl : usuario.getAclCollection1()) {
                Usuario oldModificadoPorOfAclCollection1Acl = aclCollection1Acl.getModificadoPor();
                aclCollection1Acl.setModificadoPor(usuario);
                aclCollection1Acl = em.merge(aclCollection1Acl);
                if (oldModificadoPorOfAclCollection1Acl != null) {
                    oldModificadoPorOfAclCollection1Acl.getAclCollection1().remove(aclCollection1Acl);
                    oldModificadoPorOfAclCollection1Acl = em.merge(oldModificadoPorOfAclCollection1Acl);
                }
            }
            for (SeccionSubSeccion seccionSubSeccionCollectionSeccionSubSeccion : usuario.getSeccionSubSeccionCollection()) {
                Usuario oldCreadoPorOfSeccionSubSeccionCollectionSeccionSubSeccion = seccionSubSeccionCollectionSeccionSubSeccion.getCreadoPor();
                seccionSubSeccionCollectionSeccionSubSeccion.setCreadoPor(usuario);
                seccionSubSeccionCollectionSeccionSubSeccion = em.merge(seccionSubSeccionCollectionSeccionSubSeccion);
                if (oldCreadoPorOfSeccionSubSeccionCollectionSeccionSubSeccion != null) {
                    oldCreadoPorOfSeccionSubSeccionCollectionSeccionSubSeccion.getSeccionSubSeccionCollection().remove(seccionSubSeccionCollectionSeccionSubSeccion);
                    oldCreadoPorOfSeccionSubSeccionCollectionSeccionSubSeccion = em.merge(oldCreadoPorOfSeccionSubSeccionCollectionSeccionSubSeccion);
                }
            }
            for (SeccionSubSeccion seccionSubSeccionCollection1SeccionSubSeccion : usuario.getSeccionSubSeccionCollection1()) {
                Usuario oldModificadoPorOfSeccionSubSeccionCollection1SeccionSubSeccion = seccionSubSeccionCollection1SeccionSubSeccion.getModificadoPor();
                seccionSubSeccionCollection1SeccionSubSeccion.setModificadoPor(usuario);
                seccionSubSeccionCollection1SeccionSubSeccion = em.merge(seccionSubSeccionCollection1SeccionSubSeccion);
                if (oldModificadoPorOfSeccionSubSeccionCollection1SeccionSubSeccion != null) {
                    oldModificadoPorOfSeccionSubSeccionCollection1SeccionSubSeccion.getSeccionSubSeccionCollection1().remove(seccionSubSeccionCollection1SeccionSubSeccion);
                    oldModificadoPorOfSeccionSubSeccionCollection1SeccionSubSeccion = em.merge(oldModificadoPorOfSeccionSubSeccionCollection1SeccionSubSeccion);
                }
            }
            for (Preferencias preferenciasCollectionPreferencias : usuario.getPreferenciasCollection()) {
                Usuario oldUsuarioOfPreferenciasCollectionPreferencias = preferenciasCollectionPreferencias.getUsuario();
                preferenciasCollectionPreferencias.setUsuario(usuario);
                preferenciasCollectionPreferencias = em.merge(preferenciasCollectionPreferencias);
                if (oldUsuarioOfPreferenciasCollectionPreferencias != null) {
                    oldUsuarioOfPreferenciasCollectionPreferencias.getPreferenciasCollection().remove(preferenciasCollectionPreferencias);
                    oldUsuarioOfPreferenciasCollectionPreferencias = em.merge(oldUsuarioOfPreferenciasCollectionPreferencias);
                }
            }
            for (PlantillaDocumental plantillaDocumentalCollectionPlantillaDocumental : usuario.getPlantillaDocumentalCollection()) {
                Usuario oldCreadoPorOfPlantillaDocumentalCollectionPlantillaDocumental = plantillaDocumentalCollectionPlantillaDocumental.getCreadoPor();
                plantillaDocumentalCollectionPlantillaDocumental.setCreadoPor(usuario);
                plantillaDocumentalCollectionPlantillaDocumental = em.merge(plantillaDocumentalCollectionPlantillaDocumental);
                if (oldCreadoPorOfPlantillaDocumentalCollectionPlantillaDocumental != null) {
                    oldCreadoPorOfPlantillaDocumentalCollectionPlantillaDocumental.getPlantillaDocumentalCollection().remove(plantillaDocumentalCollectionPlantillaDocumental);
                    oldCreadoPorOfPlantillaDocumentalCollectionPlantillaDocumental = em.merge(oldCreadoPorOfPlantillaDocumentalCollectionPlantillaDocumental);
                }
            }
            for (PlantillaDocumental plantillaDocumentalCollection1PlantillaDocumental : usuario.getPlantillaDocumentalCollection1()) {
                Usuario oldModificadoPorOfPlantillaDocumentalCollection1PlantillaDocumental = plantillaDocumentalCollection1PlantillaDocumental.getModificadoPor();
                plantillaDocumentalCollection1PlantillaDocumental.setModificadoPor(usuario);
                plantillaDocumentalCollection1PlantillaDocumental = em.merge(plantillaDocumentalCollection1PlantillaDocumental);
                if (oldModificadoPorOfPlantillaDocumentalCollection1PlantillaDocumental != null) {
                    oldModificadoPorOfPlantillaDocumentalCollection1PlantillaDocumental.getPlantillaDocumentalCollection1().remove(plantillaDocumentalCollection1PlantillaDocumental);
                    oldModificadoPorOfPlantillaDocumentalCollection1PlantillaDocumental = em.merge(oldModificadoPorOfPlantillaDocumentalCollection1PlantillaDocumental);
                }
            }
            for (Cargo cargoCollectionCargo : usuario.getCargoCollection()) {
                Usuario oldCreadoPorOfCargoCollectionCargo = cargoCollectionCargo.getCreadoPor();
                cargoCollectionCargo.setCreadoPor(usuario);
                cargoCollectionCargo = em.merge(cargoCollectionCargo);
                if (oldCreadoPorOfCargoCollectionCargo != null) {
                    oldCreadoPorOfCargoCollectionCargo.getCargoCollection().remove(cargoCollectionCargo);
                    oldCreadoPorOfCargoCollectionCargo = em.merge(oldCreadoPorOfCargoCollectionCargo);
                }
            }
            for (Cargo cargoCollection1Cargo : usuario.getCargoCollection1()) {
                Usuario oldModificadoPorOfCargoCollection1Cargo = cargoCollection1Cargo.getModificadoPor();
                cargoCollection1Cargo.setModificadoPor(usuario);
                cargoCollection1Cargo = em.merge(cargoCollection1Cargo);
                if (oldModificadoPorOfCargoCollection1Cargo != null) {
                    oldModificadoPorOfCargoCollection1Cargo.getCargoCollection1().remove(cargoCollection1Cargo);
                    oldModificadoPorOfCargoCollection1Cargo = em.merge(oldModificadoPorOfCargoCollection1Cargo);
                }
            }
            for (TipoDocumento tipoDocumentoCollectionTipoDocumento : usuario.getTipoDocumentoCollection()) {
                Usuario oldCreadoPorOfTipoDocumentoCollectionTipoDocumento = tipoDocumentoCollectionTipoDocumento.getCreadoPor();
                tipoDocumentoCollectionTipoDocumento.setCreadoPor(usuario);
                tipoDocumentoCollectionTipoDocumento = em.merge(tipoDocumentoCollectionTipoDocumento);
                if (oldCreadoPorOfTipoDocumentoCollectionTipoDocumento != null) {
                    oldCreadoPorOfTipoDocumentoCollectionTipoDocumento.getTipoDocumentoCollection().remove(tipoDocumentoCollectionTipoDocumento);
                    oldCreadoPorOfTipoDocumentoCollectionTipoDocumento = em.merge(oldCreadoPorOfTipoDocumentoCollectionTipoDocumento);
                }
            }
            for (TipoDocumento tipoDocumentoCollection1TipoDocumento : usuario.getTipoDocumentoCollection1()) {
                Usuario oldModificadoPorOfTipoDocumentoCollection1TipoDocumento = tipoDocumentoCollection1TipoDocumento.getModificadoPor();
                tipoDocumentoCollection1TipoDocumento.setModificadoPor(usuario);
                tipoDocumentoCollection1TipoDocumento = em.merge(tipoDocumentoCollection1TipoDocumento);
                if (oldModificadoPorOfTipoDocumentoCollection1TipoDocumento != null) {
                    oldModificadoPorOfTipoDocumentoCollection1TipoDocumento.getTipoDocumentoCollection1().remove(tipoDocumentoCollection1TipoDocumento);
                    oldModificadoPorOfTipoDocumentoCollection1TipoDocumento = em.merge(oldModificadoPorOfTipoDocumentoCollection1TipoDocumento);
                }
            }
            for (SubSerie subSerieCollectionSubSerie : usuario.getSubSerieCollection()) {
                Usuario oldCreadoPorOfSubSerieCollectionSubSerie = subSerieCollectionSubSerie.getCreadoPor();
                subSerieCollectionSubSerie.setCreadoPor(usuario);
                subSerieCollectionSubSerie = em.merge(subSerieCollectionSubSerie);
                if (oldCreadoPorOfSubSerieCollectionSubSerie != null) {
                    oldCreadoPorOfSubSerieCollectionSubSerie.getSubSerieCollection().remove(subSerieCollectionSubSerie);
                    oldCreadoPorOfSubSerieCollectionSubSerie = em.merge(oldCreadoPorOfSubSerieCollectionSubSerie);
                }
            }
            for (SubSerie subSerieCollection1SubSerie : usuario.getSubSerieCollection1()) {
                Usuario oldModificadoPorOfSubSerieCollection1SubSerie = subSerieCollection1SubSerie.getModificadoPor();
                subSerieCollection1SubSerie.setModificadoPor(usuario);
                subSerieCollection1SubSerie = em.merge(subSerieCollection1SubSerie);
                if (oldModificadoPorOfSubSerieCollection1SubSerie != null) {
                    oldModificadoPorOfSubSerieCollection1SubSerie.getSubSerieCollection1().remove(subSerieCollection1SubSerie);
                    oldModificadoPorOfSubSerieCollection1SubSerie = em.merge(oldModificadoPorOfSubSerieCollection1SubSerie);
                }
            }
            for (Municipio municipioCollectionMunicipio : usuario.getMunicipioCollection()) {
                Usuario oldCreadoPorOfMunicipioCollectionMunicipio = municipioCollectionMunicipio.getCreadoPor();
                municipioCollectionMunicipio.setCreadoPor(usuario);
                municipioCollectionMunicipio = em.merge(municipioCollectionMunicipio);
                if (oldCreadoPorOfMunicipioCollectionMunicipio != null) {
                    oldCreadoPorOfMunicipioCollectionMunicipio.getMunicipioCollection().remove(municipioCollectionMunicipio);
                    oldCreadoPorOfMunicipioCollectionMunicipio = em.merge(oldCreadoPorOfMunicipioCollectionMunicipio);
                }
            }
            for (Municipio municipioCollection1Municipio : usuario.getMunicipioCollection1()) {
                Usuario oldModificadoPorOfMunicipioCollection1Municipio = municipioCollection1Municipio.getModificadoPor();
                municipioCollection1Municipio.setModificadoPor(usuario);
                municipioCollection1Municipio = em.merge(municipioCollection1Municipio);
                if (oldModificadoPorOfMunicipioCollection1Municipio != null) {
                    oldModificadoPorOfMunicipioCollection1Municipio.getMunicipioCollection1().remove(municipioCollection1Municipio);
                    oldModificadoPorOfMunicipioCollection1Municipio = em.merge(oldModificadoPorOfMunicipioCollection1Municipio);
                }
            }
            for (ProcesoNegocio procesoNegocioCollectionProcesoNegocio : usuario.getProcesoNegocioCollection()) {
                Usuario oldCreadoPorOfProcesoNegocioCollectionProcesoNegocio = procesoNegocioCollectionProcesoNegocio.getCreadoPor();
                procesoNegocioCollectionProcesoNegocio.setCreadoPor(usuario);
                procesoNegocioCollectionProcesoNegocio = em.merge(procesoNegocioCollectionProcesoNegocio);
                if (oldCreadoPorOfProcesoNegocioCollectionProcesoNegocio != null) {
                    oldCreadoPorOfProcesoNegocioCollectionProcesoNegocio.getProcesoNegocioCollection().remove(procesoNegocioCollectionProcesoNegocio);
                    oldCreadoPorOfProcesoNegocioCollectionProcesoNegocio = em.merge(oldCreadoPorOfProcesoNegocioCollectionProcesoNegocio);
                }
            }
            for (ProcesoNegocio procesoNegocioCollection1ProcesoNegocio : usuario.getProcesoNegocioCollection1()) {
                Usuario oldModificadoPorOfProcesoNegocioCollection1ProcesoNegocio = procesoNegocioCollection1ProcesoNegocio.getModificadoPor();
                procesoNegocioCollection1ProcesoNegocio.setModificadoPor(usuario);
                procesoNegocioCollection1ProcesoNegocio = em.merge(procesoNegocioCollection1ProcesoNegocio);
                if (oldModificadoPorOfProcesoNegocioCollection1ProcesoNegocio != null) {
                    oldModificadoPorOfProcesoNegocioCollection1ProcesoNegocio.getProcesoNegocioCollection1().remove(procesoNegocioCollection1ProcesoNegocio);
                    oldModificadoPorOfProcesoNegocioCollection1ProcesoNegocio = em.merge(oldModificadoPorOfProcesoNegocioCollection1ProcesoNegocio);
                }
            }
            for (Corregimiento corregimientoCollectionCorregimiento : usuario.getCorregimientoCollection()) {
                Usuario oldCreadoPorOfCorregimientoCollectionCorregimiento = corregimientoCollectionCorregimiento.getCreadoPor();
                corregimientoCollectionCorregimiento.setCreadoPor(usuario);
                corregimientoCollectionCorregimiento = em.merge(corregimientoCollectionCorregimiento);
                if (oldCreadoPorOfCorregimientoCollectionCorregimiento != null) {
                    oldCreadoPorOfCorregimientoCollectionCorregimiento.getCorregimientoCollection().remove(corregimientoCollectionCorregimiento);
                    oldCreadoPorOfCorregimientoCollectionCorregimiento = em.merge(oldCreadoPorOfCorregimientoCollectionCorregimiento);
                }
            }
            for (Corregimiento corregimientoCollection1Corregimiento : usuario.getCorregimientoCollection1()) {
                Usuario oldModificadoPorOfCorregimientoCollection1Corregimiento = corregimientoCollection1Corregimiento.getModificadoPor();
                corregimientoCollection1Corregimiento.setModificadoPor(usuario);
                corregimientoCollection1Corregimiento = em.merge(corregimientoCollection1Corregimiento);
                if (oldModificadoPorOfCorregimientoCollection1Corregimiento != null) {
                    oldModificadoPorOfCorregimientoCollection1Corregimiento.getCorregimientoCollection1().remove(corregimientoCollection1Corregimiento);
                    oldModificadoPorOfCorregimientoCollection1Corregimiento = em.merge(oldModificadoPorOfCorregimientoCollection1Corregimiento);
                }
            }
            for (GrupoUsuario grupoUsuarioCollectionGrupoUsuario : usuario.getGrupoUsuarioCollection()) {
                Usuario oldCreadoPorOfGrupoUsuarioCollectionGrupoUsuario = grupoUsuarioCollectionGrupoUsuario.getCreadoPor();
                grupoUsuarioCollectionGrupoUsuario.setCreadoPor(usuario);
                grupoUsuarioCollectionGrupoUsuario = em.merge(grupoUsuarioCollectionGrupoUsuario);
                if (oldCreadoPorOfGrupoUsuarioCollectionGrupoUsuario != null) {
                    oldCreadoPorOfGrupoUsuarioCollectionGrupoUsuario.getGrupoUsuarioCollection().remove(grupoUsuarioCollectionGrupoUsuario);
                    oldCreadoPorOfGrupoUsuarioCollectionGrupoUsuario = em.merge(oldCreadoPorOfGrupoUsuarioCollectionGrupoUsuario);
                }
            }
            for (GrupoUsuario grupoUsuarioCollection1GrupoUsuario : usuario.getGrupoUsuarioCollection1()) {
                Usuario oldModificadoPorOfGrupoUsuarioCollection1GrupoUsuario = grupoUsuarioCollection1GrupoUsuario.getModificadoPor();
                grupoUsuarioCollection1GrupoUsuario.setModificadoPor(usuario);
                grupoUsuarioCollection1GrupoUsuario = em.merge(grupoUsuarioCollection1GrupoUsuario);
                if (oldModificadoPorOfGrupoUsuarioCollection1GrupoUsuario != null) {
                    oldModificadoPorOfGrupoUsuarioCollection1GrupoUsuario.getGrupoUsuarioCollection1().remove(grupoUsuarioCollection1GrupoUsuario);
                    oldModificadoPorOfGrupoUsuarioCollection1GrupoUsuario = em.merge(oldModificadoPorOfGrupoUsuarioCollection1GrupoUsuario);
                }
            }
            for (GrupoUsuario grupoUsuarioCollection2GrupoUsuario : usuario.getGrupoUsuarioCollection2()) {
                Usuario oldUsuarioOfGrupoUsuarioCollection2GrupoUsuario = grupoUsuarioCollection2GrupoUsuario.getUsuario();
                grupoUsuarioCollection2GrupoUsuario.setUsuario(usuario);
                grupoUsuarioCollection2GrupoUsuario = em.merge(grupoUsuarioCollection2GrupoUsuario);
                if (oldUsuarioOfGrupoUsuarioCollection2GrupoUsuario != null) {
                    oldUsuarioOfGrupoUsuarioCollection2GrupoUsuario.getGrupoUsuarioCollection2().remove(grupoUsuarioCollection2GrupoUsuario);
                    oldUsuarioOfGrupoUsuarioCollection2GrupoUsuario = em.merge(oldUsuarioOfGrupoUsuarioCollection2GrupoUsuario);
                }
            }
            for (DestinatariosDoc destinatariosDocCollectionDestinatariosDoc : usuario.getDestinatariosDocCollection()) {
                Usuario oldCreadoPorOfDestinatariosDocCollectionDestinatariosDoc = destinatariosDocCollectionDestinatariosDoc.getCreadoPor();
                destinatariosDocCollectionDestinatariosDoc.setCreadoPor(usuario);
                destinatariosDocCollectionDestinatariosDoc = em.merge(destinatariosDocCollectionDestinatariosDoc);
                if (oldCreadoPorOfDestinatariosDocCollectionDestinatariosDoc != null) {
                    oldCreadoPorOfDestinatariosDocCollectionDestinatariosDoc.getDestinatariosDocCollection().remove(destinatariosDocCollectionDestinatariosDoc);
                    oldCreadoPorOfDestinatariosDocCollectionDestinatariosDoc = em.merge(oldCreadoPorOfDestinatariosDocCollectionDestinatariosDoc);
                }
            }
            for (DestinatariosDoc destinatariosDocCollection1DestinatariosDoc : usuario.getDestinatariosDocCollection1()) {
                Usuario oldModificadoPorOfDestinatariosDocCollection1DestinatariosDoc = destinatariosDocCollection1DestinatariosDoc.getModificadoPor();
                destinatariosDocCollection1DestinatariosDoc.setModificadoPor(usuario);
                destinatariosDocCollection1DestinatariosDoc = em.merge(destinatariosDocCollection1DestinatariosDoc);
                if (oldModificadoPorOfDestinatariosDocCollection1DestinatariosDoc != null) {
                    oldModificadoPorOfDestinatariosDocCollection1DestinatariosDoc.getDestinatariosDocCollection1().remove(destinatariosDocCollection1DestinatariosDoc);
                    oldModificadoPorOfDestinatariosDocCollection1DestinatariosDoc = em.merge(oldModificadoPorOfDestinatariosDocCollection1DestinatariosDoc);
                }
            }
            for (DestinatariosDoc destinatariosDocCollection2DestinatariosDoc : usuario.getDestinatariosDocCollection2()) {
                Usuario oldDestinatarioIdOfDestinatariosDocCollection2DestinatariosDoc = destinatariosDocCollection2DestinatariosDoc.getDestinatarioId();
                destinatariosDocCollection2DestinatariosDoc.setDestinatarioId(usuario);
                destinatariosDocCollection2DestinatariosDoc = em.merge(destinatariosDocCollection2DestinatariosDoc);
                if (oldDestinatarioIdOfDestinatariosDocCollection2DestinatariosDoc != null) {
                    oldDestinatarioIdOfDestinatariosDocCollection2DestinatariosDoc.getDestinatariosDocCollection2().remove(destinatariosDocCollection2DestinatariosDoc);
                    oldDestinatarioIdOfDestinatariosDocCollection2DestinatariosDoc = em.merge(oldDestinatarioIdOfDestinatariosDocCollection2DestinatariosDoc);
                }
            }
            for (Autor autorCollectionAutor : usuario.getAutorCollection()) {
                Usuario oldCreadoPorOfAutorCollectionAutor = autorCollectionAutor.getCreadoPor();
                autorCollectionAutor.setCreadoPor(usuario);
                autorCollectionAutor = em.merge(autorCollectionAutor);
                if (oldCreadoPorOfAutorCollectionAutor != null) {
                    oldCreadoPorOfAutorCollectionAutor.getAutorCollection().remove(autorCollectionAutor);
                    oldCreadoPorOfAutorCollectionAutor = em.merge(oldCreadoPorOfAutorCollectionAutor);
                }
            }
            for (Autor autorCollection1Autor : usuario.getAutorCollection1()) {
                Usuario oldModificadoPorOfAutorCollection1Autor = autorCollection1Autor.getModificadoPor();
                autorCollection1Autor.setModificadoPor(usuario);
                autorCollection1Autor = em.merge(autorCollection1Autor);
                if (oldModificadoPorOfAutorCollection1Autor != null) {
                    oldModificadoPorOfAutorCollection1Autor.getAutorCollection1().remove(autorCollection1Autor);
                    oldModificadoPorOfAutorCollection1Autor = em.merge(oldModificadoPorOfAutorCollection1Autor);
                }
            }
            for (Notificacion notificacionCollectionNotificacion : usuario.getNotificacionCollection()) {
                Usuario oldResponsableOfNotificacionCollectionNotificacion = notificacionCollectionNotificacion.getResponsable();
                notificacionCollectionNotificacion.setResponsable(usuario);
                notificacionCollectionNotificacion = em.merge(notificacionCollectionNotificacion);
                if (oldResponsableOfNotificacionCollectionNotificacion != null) {
                    oldResponsableOfNotificacionCollectionNotificacion.getNotificacionCollection().remove(notificacionCollectionNotificacion);
                    oldResponsableOfNotificacionCollectionNotificacion = em.merge(oldResponsableOfNotificacionCollectionNotificacion);
                }
            }
            for (Pais paisCollectionPais : usuario.getPaisCollection()) {
                Usuario oldCreadoPorOfPaisCollectionPais = paisCollectionPais.getCreadoPor();
                paisCollectionPais.setCreadoPor(usuario);
                paisCollectionPais = em.merge(paisCollectionPais);
                if (oldCreadoPorOfPaisCollectionPais != null) {
                    oldCreadoPorOfPaisCollectionPais.getPaisCollection().remove(paisCollectionPais);
                    oldCreadoPorOfPaisCollectionPais = em.merge(oldCreadoPorOfPaisCollectionPais);
                }
            }
            for (Pais paisCollection1Pais : usuario.getPaisCollection1()) {
                Usuario oldModificadoPorOfPaisCollection1Pais = paisCollection1Pais.getModificadoPor();
                paisCollection1Pais.setModificadoPor(usuario);
                paisCollection1Pais = em.merge(paisCollection1Pais);
                if (oldModificadoPorOfPaisCollection1Pais != null) {
                    oldModificadoPorOfPaisCollection1Pais.getPaisCollection1().remove(paisCollection1Pais);
                    oldModificadoPorOfPaisCollection1Pais = em.merge(oldModificadoPorOfPaisCollection1Pais);
                }
            }
            for (Consecutivo consecutivoCollectionConsecutivo : usuario.getConsecutivoCollection()) {
                Usuario oldCreadoPorOfConsecutivoCollectionConsecutivo = consecutivoCollectionConsecutivo.getCreadoPor();
                consecutivoCollectionConsecutivo.setCreadoPor(usuario);
                consecutivoCollectionConsecutivo = em.merge(consecutivoCollectionConsecutivo);
                if (oldCreadoPorOfConsecutivoCollectionConsecutivo != null) {
                    oldCreadoPorOfConsecutivoCollectionConsecutivo.getConsecutivoCollection().remove(consecutivoCollectionConsecutivo);
                    oldCreadoPorOfConsecutivoCollectionConsecutivo = em.merge(oldCreadoPorOfConsecutivoCollectionConsecutivo);
                }
            }
            for (Consecutivo consecutivoCollection1Consecutivo : usuario.getConsecutivoCollection1()) {
                Usuario oldModificadoPorOfConsecutivoCollection1Consecutivo = consecutivoCollection1Consecutivo.getModificadoPor();
                consecutivoCollection1Consecutivo.setModificadoPor(usuario);
                consecutivoCollection1Consecutivo = em.merge(consecutivoCollection1Consecutivo);
                if (oldModificadoPorOfConsecutivoCollection1Consecutivo != null) {
                    oldModificadoPorOfConsecutivoCollection1Consecutivo.getConsecutivoCollection1().remove(consecutivoCollection1Consecutivo);
                    oldModificadoPorOfConsecutivoCollection1Consecutivo = em.merge(oldModificadoPorOfConsecutivoCollection1Consecutivo);
                }
            }
            for (SignaturaTopografica signaturaTopograficaCollectionSignaturaTopografica : usuario.getSignaturaTopograficaCollection()) {
                Usuario oldCreadoPorOfSignaturaTopograficaCollectionSignaturaTopografica = signaturaTopograficaCollectionSignaturaTopografica.getCreadoPor();
                signaturaTopograficaCollectionSignaturaTopografica.setCreadoPor(usuario);
                signaturaTopograficaCollectionSignaturaTopografica = em.merge(signaturaTopograficaCollectionSignaturaTopografica);
                if (oldCreadoPorOfSignaturaTopograficaCollectionSignaturaTopografica != null) {
                    oldCreadoPorOfSignaturaTopograficaCollectionSignaturaTopografica.getSignaturaTopograficaCollection().remove(signaturaTopograficaCollectionSignaturaTopografica);
                    oldCreadoPorOfSignaturaTopograficaCollectionSignaturaTopografica = em.merge(oldCreadoPorOfSignaturaTopograficaCollectionSignaturaTopografica);
                }
            }
            for (SignaturaTopografica signaturaTopograficaCollection1SignaturaTopografica : usuario.getSignaturaTopograficaCollection1()) {
                Usuario oldModificadoPorOfSignaturaTopograficaCollection1SignaturaTopografica = signaturaTopograficaCollection1SignaturaTopografica.getModificadoPor();
                signaturaTopograficaCollection1SignaturaTopografica.setModificadoPor(usuario);
                signaturaTopograficaCollection1SignaturaTopografica = em.merge(signaturaTopograficaCollection1SignaturaTopografica);
                if (oldModificadoPorOfSignaturaTopograficaCollection1SignaturaTopografica != null) {
                    oldModificadoPorOfSignaturaTopograficaCollection1SignaturaTopografica.getSignaturaTopograficaCollection1().remove(signaturaTopograficaCollection1SignaturaTopografica);
                    oldModificadoPorOfSignaturaTopograficaCollection1SignaturaTopografica = em.merge(oldModificadoPorOfSignaturaTopograficaCollection1SignaturaTopografica);
                }
            }
            for (TipoDocumental tipoDocumentalCollectionTipoDocumental : usuario.getTipoDocumentalCollection()) {
                Usuario oldCreadoPorOfTipoDocumentalCollectionTipoDocumental = tipoDocumentalCollectionTipoDocumental.getCreadoPor();
                tipoDocumentalCollectionTipoDocumental.setCreadoPor(usuario);
                tipoDocumentalCollectionTipoDocumental = em.merge(tipoDocumentalCollectionTipoDocumental);
                if (oldCreadoPorOfTipoDocumentalCollectionTipoDocumental != null) {
                    oldCreadoPorOfTipoDocumentalCollectionTipoDocumental.getTipoDocumentalCollection().remove(tipoDocumentalCollectionTipoDocumental);
                    oldCreadoPorOfTipoDocumentalCollectionTipoDocumental = em.merge(oldCreadoPorOfTipoDocumentalCollectionTipoDocumental);
                }
            }
            for (TipoDocumental tipoDocumentalCollection1TipoDocumental : usuario.getTipoDocumentalCollection1()) {
                Usuario oldModificadoPorOfTipoDocumentalCollection1TipoDocumental = tipoDocumentalCollection1TipoDocumental.getModificadoPor();
                tipoDocumentalCollection1TipoDocumental.setModificadoPor(usuario);
                tipoDocumentalCollection1TipoDocumental = em.merge(tipoDocumentalCollection1TipoDocumental);
                if (oldModificadoPorOfTipoDocumentalCollection1TipoDocumental != null) {
                    oldModificadoPorOfTipoDocumentalCollection1TipoDocumental.getTipoDocumentalCollection1().remove(tipoDocumentalCollection1TipoDocumental);
                    oldModificadoPorOfTipoDocumentalCollection1TipoDocumental = em.merge(oldModificadoPorOfTipoDocumentalCollection1TipoDocumental);
                }
            }
            for (Transportador transportadorCollectionTransportador : usuario.getTransportadorCollection()) {
                Usuario oldCreadoPorOfTransportadorCollectionTransportador = transportadorCollectionTransportador.getCreadoPor();
                transportadorCollectionTransportador.setCreadoPor(usuario);
                transportadorCollectionTransportador = em.merge(transportadorCollectionTransportador);
                if (oldCreadoPorOfTransportadorCollectionTransportador != null) {
                    oldCreadoPorOfTransportadorCollectionTransportador.getTransportadorCollection().remove(transportadorCollectionTransportador);
                    oldCreadoPorOfTransportadorCollectionTransportador = em.merge(oldCreadoPorOfTransportadorCollectionTransportador);
                }
            }
            for (Transportador transportadorCollection1Transportador : usuario.getTransportadorCollection1()) {
                Usuario oldModificadoPorOfTransportadorCollection1Transportador = transportadorCollection1Transportador.getModificadoPor();
                transportadorCollection1Transportador.setModificadoPor(usuario);
                transportadorCollection1Transportador = em.merge(transportadorCollection1Transportador);
                if (oldModificadoPorOfTransportadorCollection1Transportador != null) {
                    oldModificadoPorOfTransportadorCollection1Transportador.getTransportadorCollection1().remove(transportadorCollection1Transportador);
                    oldModificadoPorOfTransportadorCollection1Transportador = em.merge(oldModificadoPorOfTransportadorCollection1Transportador);
                }
            }
            for (ProcesoDocumental procesodocumentalCollectionProcesoDocumental : usuario.getProcesodocumentalCollection()) {
                Usuario oldCreadoPorOfProcesodocumentalCollectionProcesoDocumental = procesodocumentalCollectionProcesoDocumental.getCreadoPor();
                procesodocumentalCollectionProcesoDocumental.setCreadoPor(usuario);
                procesodocumentalCollectionProcesoDocumental = em.merge(procesodocumentalCollectionProcesoDocumental);
                if (oldCreadoPorOfProcesodocumentalCollectionProcesoDocumental != null) {
                    oldCreadoPorOfProcesodocumentalCollectionProcesoDocumental.getProcesodocumentalCollection().remove(procesodocumentalCollectionProcesoDocumental);
                    oldCreadoPorOfProcesodocumentalCollectionProcesoDocumental = em.merge(oldCreadoPorOfProcesodocumentalCollectionProcesoDocumental);
                }
            }
            for (ProcesoDocumental procesodocumentalCollection1ProcesoDocumental : usuario.getProcesodocumentalCollection1()) {
                Usuario oldModificadoPorOfProcesodocumentalCollection1ProcesoDocumental = procesodocumentalCollection1ProcesoDocumental.getModificadoPor();
                procesodocumentalCollection1ProcesoDocumental.setModificadoPor(usuario);
                procesodocumentalCollection1ProcesoDocumental = em.merge(procesodocumentalCollection1ProcesoDocumental);
                if (oldModificadoPorOfProcesodocumentalCollection1ProcesoDocumental != null) {
                    oldModificadoPorOfProcesodocumentalCollection1ProcesoDocumental.getProcesodocumentalCollection1().remove(procesodocumentalCollection1ProcesoDocumental);
                    oldModificadoPorOfProcesodocumentalCollection1ProcesoDocumental = em.merge(oldModificadoPorOfProcesodocumentalCollection1ProcesoDocumental);
                }
            }
            for (ProcesoTipoDocumento procesoTipoDocumentoCollectionProcesoTipoDocumento : usuario.getProcesoTipoDocumentoCollection()) {
                Usuario oldCreadoPorOfProcesoTipoDocumentoCollectionProcesoTipoDocumento = procesoTipoDocumentoCollectionProcesoTipoDocumento.getCreadoPor();
                procesoTipoDocumentoCollectionProcesoTipoDocumento.setCreadoPor(usuario);
                procesoTipoDocumentoCollectionProcesoTipoDocumento = em.merge(procesoTipoDocumentoCollectionProcesoTipoDocumento);
                if (oldCreadoPorOfProcesoTipoDocumentoCollectionProcesoTipoDocumento != null) {
                    oldCreadoPorOfProcesoTipoDocumentoCollectionProcesoTipoDocumento.getProcesoTipoDocumentoCollection().remove(procesoTipoDocumentoCollectionProcesoTipoDocumento);
                    oldCreadoPorOfProcesoTipoDocumentoCollectionProcesoTipoDocumento = em.merge(oldCreadoPorOfProcesoTipoDocumentoCollectionProcesoTipoDocumento);
                }
            }
            for (ProcesoTipoDocumento procesoTipoDocumentoCollection1ProcesoTipoDocumento : usuario.getProcesoTipoDocumentoCollection1()) {
                Usuario oldModificadoPorOfProcesoTipoDocumentoCollection1ProcesoTipoDocumento = procesoTipoDocumentoCollection1ProcesoTipoDocumento.getModificadoPor();
                procesoTipoDocumentoCollection1ProcesoTipoDocumento.setModificadoPor(usuario);
                procesoTipoDocumentoCollection1ProcesoTipoDocumento = em.merge(procesoTipoDocumentoCollection1ProcesoTipoDocumento);
                if (oldModificadoPorOfProcesoTipoDocumentoCollection1ProcesoTipoDocumento != null) {
                    oldModificadoPorOfProcesoTipoDocumentoCollection1ProcesoTipoDocumento.getProcesoTipoDocumentoCollection1().remove(procesoTipoDocumentoCollection1ProcesoTipoDocumento);
                    oldModificadoPorOfProcesoTipoDocumentoCollection1ProcesoTipoDocumento = em.merge(oldModificadoPorOfProcesoTipoDocumentoCollection1ProcesoTipoDocumento);
                }
            }
            for (Entidad entidadCollectionEntidad : usuario.getEntidadCollection()) {
                Usuario oldCreadoPorOfEntidadCollectionEntidad = entidadCollectionEntidad.getCreadoPor();
                entidadCollectionEntidad.setCreadoPor(usuario);
                entidadCollectionEntidad = em.merge(entidadCollectionEntidad);
                if (oldCreadoPorOfEntidadCollectionEntidad != null) {
                    oldCreadoPorOfEntidadCollectionEntidad.getEntidadCollection().remove(entidadCollectionEntidad);
                    oldCreadoPorOfEntidadCollectionEntidad = em.merge(oldCreadoPorOfEntidadCollectionEntidad);
                }
            }
            for (Entidad entidadCollection1Entidad : usuario.getEntidadCollection1()) {
                Usuario oldModificadoPorOfEntidadCollection1Entidad = entidadCollection1Entidad.getModificadoPor();
                entidadCollection1Entidad.setModificadoPor(usuario);
                entidadCollection1Entidad = em.merge(entidadCollection1Entidad);
                if (oldModificadoPorOfEntidadCollection1Entidad != null) {
                    oldModificadoPorOfEntidadCollection1Entidad.getEntidadCollection1().remove(entidadCollection1Entidad);
                    oldModificadoPorOfEntidadCollection1Entidad = em.merge(oldModificadoPorOfEntidadCollection1Entidad);
                }
            }
            for (UnidadDocumental unidadDocumentalCollectionUnidadDocumental : usuario.getUnidadDocumentalCollection()) {
                Usuario oldCreadoPorOfUnidadDocumentalCollectionUnidadDocumental = unidadDocumentalCollectionUnidadDocumental.getCreadoPor();
                unidadDocumentalCollectionUnidadDocumental.setCreadoPor(usuario);
                unidadDocumentalCollectionUnidadDocumental = em.merge(unidadDocumentalCollectionUnidadDocumental);
                if (oldCreadoPorOfUnidadDocumentalCollectionUnidadDocumental != null) {
                    oldCreadoPorOfUnidadDocumentalCollectionUnidadDocumental.getUnidadDocumentalCollection().remove(unidadDocumentalCollectionUnidadDocumental);
                    oldCreadoPorOfUnidadDocumentalCollectionUnidadDocumental = em.merge(oldCreadoPorOfUnidadDocumentalCollectionUnidadDocumental);
                }
            }
            for (UnidadDocumental unidadDocumentalCollection1UnidadDocumental : usuario.getUnidadDocumentalCollection1()) {
                Usuario oldModificadoPorOfUnidadDocumentalCollection1UnidadDocumental = unidadDocumentalCollection1UnidadDocumental.getModificadoPor();
                unidadDocumentalCollection1UnidadDocumental.setModificadoPor(usuario);
                unidadDocumentalCollection1UnidadDocumental = em.merge(unidadDocumentalCollection1UnidadDocumental);
                if (oldModificadoPorOfUnidadDocumentalCollection1UnidadDocumental != null) {
                    oldModificadoPorOfUnidadDocumentalCollection1UnidadDocumental.getUnidadDocumentalCollection1().remove(unidadDocumentalCollection1UnidadDocumental);
                    oldModificadoPorOfUnidadDocumentalCollection1UnidadDocumental = em.merge(oldModificadoPorOfUnidadDocumentalCollection1UnidadDocumental);
                }
            }
            for (Serie serieCollectionSerie : usuario.getSerieCollection()) {
                Usuario oldCreadoPorOfSerieCollectionSerie = serieCollectionSerie.getCreadoPor();
                serieCollectionSerie.setCreadoPor(usuario);
                serieCollectionSerie = em.merge(serieCollectionSerie);
                if (oldCreadoPorOfSerieCollectionSerie != null) {
                    oldCreadoPorOfSerieCollectionSerie.getSerieCollection().remove(serieCollectionSerie);
                    oldCreadoPorOfSerieCollectionSerie = em.merge(oldCreadoPorOfSerieCollectionSerie);
                }
            }
            for (Serie serieCollection1Serie : usuario.getSerieCollection1()) {
                Usuario oldModificadoPorOfSerieCollection1Serie = serieCollection1Serie.getModificadoPor();
                serieCollection1Serie.setModificadoPor(usuario);
                serieCollection1Serie = em.merge(serieCollection1Serie);
                if (oldModificadoPorOfSerieCollection1Serie != null) {
                    oldModificadoPorOfSerieCollection1Serie.getSerieCollection1().remove(serieCollection1Serie);
                    oldModificadoPorOfSerieCollection1Serie = em.merge(oldModificadoPorOfSerieCollection1Serie);
                }
            }
            for (Departamento departamentoCollectionDepartamento : usuario.getDepartamentoCollection()) {
                Usuario oldCreadoPorOfDepartamentoCollectionDepartamento = departamentoCollectionDepartamento.getCreadoPor();
                departamentoCollectionDepartamento.setCreadoPor(usuario);
                departamentoCollectionDepartamento = em.merge(departamentoCollectionDepartamento);
                if (oldCreadoPorOfDepartamentoCollectionDepartamento != null) {
                    oldCreadoPorOfDepartamentoCollectionDepartamento.getDepartamentoCollection().remove(departamentoCollectionDepartamento);
                    oldCreadoPorOfDepartamentoCollectionDepartamento = em.merge(oldCreadoPorOfDepartamentoCollectionDepartamento);
                }
            }
            for (Departamento departamentoCollection1Departamento : usuario.getDepartamentoCollection1()) {
                Usuario oldModificadoPorOfDepartamentoCollection1Departamento = departamentoCollection1Departamento.getModificadoPor();
                departamentoCollection1Departamento.setModificadoPor(usuario);
                departamentoCollection1Departamento = em.merge(departamentoCollection1Departamento);
                if (oldModificadoPorOfDepartamentoCollection1Departamento != null) {
                    oldModificadoPorOfDepartamentoCollection1Departamento.getDepartamentoCollection1().remove(departamentoCollection1Departamento);
                    oldModificadoPorOfDepartamentoCollection1Departamento = em.merge(oldModificadoPorOfDepartamentoCollection1Departamento);
                }
            }
            for (MonitoresProceso monitoresProcesoCollectionMonitoresProceso : usuario.getMonitoresProcesoCollection()) {
                Usuario oldCreadoPorOfMonitoresProcesoCollectionMonitoresProceso = monitoresProcesoCollectionMonitoresProceso.getCreadoPor();
                monitoresProcesoCollectionMonitoresProceso.setCreadoPor(usuario);
                monitoresProcesoCollectionMonitoresProceso = em.merge(monitoresProcesoCollectionMonitoresProceso);
                if (oldCreadoPorOfMonitoresProcesoCollectionMonitoresProceso != null) {
                    oldCreadoPorOfMonitoresProcesoCollectionMonitoresProceso.getMonitoresProcesoCollection().remove(monitoresProcesoCollectionMonitoresProceso);
                    oldCreadoPorOfMonitoresProcesoCollectionMonitoresProceso = em.merge(oldCreadoPorOfMonitoresProcesoCollectionMonitoresProceso);
                }
            }
            for (MonitoresProceso monitoresProcesoCollection1MonitoresProceso : usuario.getMonitoresProcesoCollection1()) {
                Usuario oldModificadoPorOfMonitoresProcesoCollection1MonitoresProceso = monitoresProcesoCollection1MonitoresProceso.getModificadoPor();
                monitoresProcesoCollection1MonitoresProceso.setModificadoPor(usuario);
                monitoresProcesoCollection1MonitoresProceso = em.merge(monitoresProcesoCollection1MonitoresProceso);
                if (oldModificadoPorOfMonitoresProcesoCollection1MonitoresProceso != null) {
                    oldModificadoPorOfMonitoresProcesoCollection1MonitoresProceso.getMonitoresProcesoCollection1().remove(monitoresProcesoCollection1MonitoresProceso);
                    oldModificadoPorOfMonitoresProcesoCollection1MonitoresProceso = em.merge(oldModificadoPorOfMonitoresProcesoCollection1MonitoresProceso);
                }
            }
            for (Modulo moduloCollectionModulo : usuario.getModuloCollection()) {
                Usuario oldCreadoPorOfModuloCollectionModulo = moduloCollectionModulo.getCreadoPor();
                moduloCollectionModulo.setCreadoPor(usuario);
                moduloCollectionModulo = em.merge(moduloCollectionModulo);
                if (oldCreadoPorOfModuloCollectionModulo != null) {
                    oldCreadoPorOfModuloCollectionModulo.getModuloCollection().remove(moduloCollectionModulo);
                    oldCreadoPorOfModuloCollectionModulo = em.merge(oldCreadoPorOfModuloCollectionModulo);
                }
            }
            for (Modulo moduloCollection1Modulo : usuario.getModuloCollection1()) {
                Usuario oldModificadoPorOfModuloCollection1Modulo = moduloCollection1Modulo.getModificadoPor();
                moduloCollection1Modulo.setModificadoPor(usuario);
                moduloCollection1Modulo = em.merge(moduloCollection1Modulo);
                if (oldModificadoPorOfModuloCollection1Modulo != null) {
                    oldModificadoPorOfModuloCollection1Modulo.getModuloCollection1().remove(moduloCollection1Modulo);
                    oldModificadoPorOfModuloCollection1Modulo = em.merge(oldModificadoPorOfModuloCollection1Modulo);
                }
            }
            for (Campos camposCollectionCampos : usuario.getCamposCollection()) {
                Usuario oldCreadoPorOfCamposCollectionCampos = camposCollectionCampos.getCreadoPor();
                camposCollectionCampos.setCreadoPor(usuario);
                camposCollectionCampos = em.merge(camposCollectionCampos);
                if (oldCreadoPorOfCamposCollectionCampos != null) {
                    oldCreadoPorOfCamposCollectionCampos.getCamposCollection().remove(camposCollectionCampos);
                    oldCreadoPorOfCamposCollectionCampos = em.merge(oldCreadoPorOfCamposCollectionCampos);
                }
            }
            for (Campos camposCollection1Campos : usuario.getCamposCollection1()) {
                Usuario oldModificadoPorOfCamposCollection1Campos = camposCollection1Campos.getModificadoPor();
                camposCollection1Campos.setModificadoPor(usuario);
                camposCollection1Campos = em.merge(camposCollection1Campos);
                if (oldModificadoPorOfCamposCollection1Campos != null) {
                    oldModificadoPorOfCamposCollection1Campos.getCamposCollection1().remove(camposCollection1Campos);
                    oldModificadoPorOfCamposCollection1Campos = em.merge(oldModificadoPorOfCamposCollection1Campos);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Usuario usuario) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario persistentUsuario = em.find(Usuario.class, usuario.getId());
            Cargo cargoOld = persistentUsuario.getCargo();
            Cargo cargoNew = usuario.getCargo();
            Collection<ClaseDocumento> claseDocumentoCollectionOld = persistentUsuario.getClaseDocumentoCollection();
            Collection<ClaseDocumento> claseDocumentoCollectionNew = usuario.getClaseDocumentoCollection();
            Collection<ClaseDocumento> claseDocumentoCollection1Old = persistentUsuario.getClaseDocumentoCollection1();
            Collection<ClaseDocumento> claseDocumentoCollection1New = usuario.getClaseDocumentoCollection1();
            Collection<Grupo> grupoCollectionOld = persistentUsuario.getGrupoCollection();
            Collection<Grupo> grupoCollectionNew = usuario.getGrupoCollection();
            Collection<Documento> documentoCollectionOld = persistentUsuario.getDocumentoCollection();
            Collection<Documento> documentoCollectionNew = usuario.getDocumentoCollection();
            Collection<Documento> documentoCollection1Old = persistentUsuario.getDocumentoCollection1();
            Collection<Documento> documentoCollection1New = usuario.getDocumentoCollection1();
            Collection<Acl> aclCollectionOld = persistentUsuario.getAclCollection();
            Collection<Acl> aclCollectionNew = usuario.getAclCollection();
            Collection<Acl> aclCollection1Old = persistentUsuario.getAclCollection1();
            Collection<Acl> aclCollection1New = usuario.getAclCollection1();
            Collection<SeccionSubSeccion> seccionSubSeccionCollectionOld = persistentUsuario.getSeccionSubSeccionCollection();
            Collection<SeccionSubSeccion> seccionSubSeccionCollectionNew = usuario.getSeccionSubSeccionCollection();
            Collection<SeccionSubSeccion> seccionSubSeccionCollection1Old = persistentUsuario.getSeccionSubSeccionCollection1();
            Collection<SeccionSubSeccion> seccionSubSeccionCollection1New = usuario.getSeccionSubSeccionCollection1();
            Collection<Preferencias> preferenciasCollectionOld = persistentUsuario.getPreferenciasCollection();
            Collection<Preferencias> preferenciasCollectionNew = usuario.getPreferenciasCollection();
            Collection<PlantillaDocumental> plantillaDocumentalCollectionOld = persistentUsuario.getPlantillaDocumentalCollection();
            Collection<PlantillaDocumental> plantillaDocumentalCollectionNew = usuario.getPlantillaDocumentalCollection();
            Collection<PlantillaDocumental> plantillaDocumentalCollection1Old = persistentUsuario.getPlantillaDocumentalCollection1();
            Collection<PlantillaDocumental> plantillaDocumentalCollection1New = usuario.getPlantillaDocumentalCollection1();
            Collection<Cargo> cargoCollectionOld = persistentUsuario.getCargoCollection();
            Collection<Cargo> cargoCollectionNew = usuario.getCargoCollection();
            Collection<Cargo> cargoCollection1Old = persistentUsuario.getCargoCollection1();
            Collection<Cargo> cargoCollection1New = usuario.getCargoCollection1();
            Collection<TipoDocumento> tipoDocumentoCollectionOld = persistentUsuario.getTipoDocumentoCollection();
            Collection<TipoDocumento> tipoDocumentoCollectionNew = usuario.getTipoDocumentoCollection();
            Collection<TipoDocumento> tipoDocumentoCollection1Old = persistentUsuario.getTipoDocumentoCollection1();
            Collection<TipoDocumento> tipoDocumentoCollection1New = usuario.getTipoDocumentoCollection1();
            Collection<SubSerie> subSerieCollectionOld = persistentUsuario.getSubSerieCollection();
            Collection<SubSerie> subSerieCollectionNew = usuario.getSubSerieCollection();
            Collection<SubSerie> subSerieCollection1Old = persistentUsuario.getSubSerieCollection1();
            Collection<SubSerie> subSerieCollection1New = usuario.getSubSerieCollection1();
            Collection<Municipio> municipioCollectionOld = persistentUsuario.getMunicipioCollection();
            Collection<Municipio> municipioCollectionNew = usuario.getMunicipioCollection();
            Collection<Municipio> municipioCollection1Old = persistentUsuario.getMunicipioCollection1();
            Collection<Municipio> municipioCollection1New = usuario.getMunicipioCollection1();
            Collection<ProcesoNegocio> procesoNegocioCollectionOld = persistentUsuario.getProcesoNegocioCollection();
            Collection<ProcesoNegocio> procesoNegocioCollectionNew = usuario.getProcesoNegocioCollection();
            Collection<ProcesoNegocio> procesoNegocioCollection1Old = persistentUsuario.getProcesoNegocioCollection1();
            Collection<ProcesoNegocio> procesoNegocioCollection1New = usuario.getProcesoNegocioCollection1();
            Collection<Corregimiento> corregimientoCollectionOld = persistentUsuario.getCorregimientoCollection();
            Collection<Corregimiento> corregimientoCollectionNew = usuario.getCorregimientoCollection();
            Collection<Corregimiento> corregimientoCollection1Old = persistentUsuario.getCorregimientoCollection1();
            Collection<Corregimiento> corregimientoCollection1New = usuario.getCorregimientoCollection1();
            Collection<GrupoUsuario> grupoUsuarioCollectionOld = persistentUsuario.getGrupoUsuarioCollection();
            Collection<GrupoUsuario> grupoUsuarioCollectionNew = usuario.getGrupoUsuarioCollection();
            Collection<GrupoUsuario> grupoUsuarioCollection1Old = persistentUsuario.getGrupoUsuarioCollection1();
            Collection<GrupoUsuario> grupoUsuarioCollection1New = usuario.getGrupoUsuarioCollection1();
            Collection<GrupoUsuario> grupoUsuarioCollection2Old = persistentUsuario.getGrupoUsuarioCollection2();
            Collection<GrupoUsuario> grupoUsuarioCollection2New = usuario.getGrupoUsuarioCollection2();
            Collection<DestinatariosDoc> destinatariosDocCollectionOld = persistentUsuario.getDestinatariosDocCollection();
            Collection<DestinatariosDoc> destinatariosDocCollectionNew = usuario.getDestinatariosDocCollection();
            Collection<DestinatariosDoc> destinatariosDocCollection1Old = persistentUsuario.getDestinatariosDocCollection1();
            Collection<DestinatariosDoc> destinatariosDocCollection1New = usuario.getDestinatariosDocCollection1();
            Collection<DestinatariosDoc> destinatariosDocCollection2Old = persistentUsuario.getDestinatariosDocCollection2();
            Collection<DestinatariosDoc> destinatariosDocCollection2New = usuario.getDestinatariosDocCollection2();
            Collection<Autor> autorCollectionOld = persistentUsuario.getAutorCollection();
            Collection<Autor> autorCollectionNew = usuario.getAutorCollection();
            Collection<Autor> autorCollection1Old = persistentUsuario.getAutorCollection1();
            Collection<Autor> autorCollection1New = usuario.getAutorCollection1();
            Collection<Notificacion> notificacionCollectionOld = persistentUsuario.getNotificacionCollection();
            Collection<Notificacion> notificacionCollectionNew = usuario.getNotificacionCollection();
            Collection<Pais> paisCollectionOld = persistentUsuario.getPaisCollection();
            Collection<Pais> paisCollectionNew = usuario.getPaisCollection();
            Collection<Pais> paisCollection1Old = persistentUsuario.getPaisCollection1();
            Collection<Pais> paisCollection1New = usuario.getPaisCollection1();
            Collection<Consecutivo> consecutivoCollectionOld = persistentUsuario.getConsecutivoCollection();
            Collection<Consecutivo> consecutivoCollectionNew = usuario.getConsecutivoCollection();
            Collection<Consecutivo> consecutivoCollection1Old = persistentUsuario.getConsecutivoCollection1();
            Collection<Consecutivo> consecutivoCollection1New = usuario.getConsecutivoCollection1();
            Collection<SignaturaTopografica> signaturaTopograficaCollectionOld = persistentUsuario.getSignaturaTopograficaCollection();
            Collection<SignaturaTopografica> signaturaTopograficaCollectionNew = usuario.getSignaturaTopograficaCollection();
            Collection<SignaturaTopografica> signaturaTopograficaCollection1Old = persistentUsuario.getSignaturaTopograficaCollection1();
            Collection<SignaturaTopografica> signaturaTopograficaCollection1New = usuario.getSignaturaTopograficaCollection1();
            Collection<TipoDocumental> tipoDocumentalCollectionOld = persistentUsuario.getTipoDocumentalCollection();
            Collection<TipoDocumental> tipoDocumentalCollectionNew = usuario.getTipoDocumentalCollection();
            Collection<TipoDocumental> tipoDocumentalCollection1Old = persistentUsuario.getTipoDocumentalCollection1();
            Collection<TipoDocumental> tipoDocumentalCollection1New = usuario.getTipoDocumentalCollection1();
            Collection<Transportador> transportadorCollectionOld = persistentUsuario.getTransportadorCollection();
            Collection<Transportador> transportadorCollectionNew = usuario.getTransportadorCollection();
            Collection<Transportador> transportadorCollection1Old = persistentUsuario.getTransportadorCollection1();
            Collection<Transportador> transportadorCollection1New = usuario.getTransportadorCollection1();
            Collection<ProcesoDocumental> procesodocumentalCollectionOld = persistentUsuario.getProcesodocumentalCollection();
            Collection<ProcesoDocumental> procesodocumentalCollectionNew = usuario.getProcesodocumentalCollection();
            Collection<ProcesoDocumental> procesodocumentalCollection1Old = persistentUsuario.getProcesodocumentalCollection1();
            Collection<ProcesoDocumental> procesodocumentalCollection1New = usuario.getProcesodocumentalCollection1();
            Collection<ProcesoTipoDocumento> procesoTipoDocumentoCollectionOld = persistentUsuario.getProcesoTipoDocumentoCollection();
            Collection<ProcesoTipoDocumento> procesoTipoDocumentoCollectionNew = usuario.getProcesoTipoDocumentoCollection();
            Collection<ProcesoTipoDocumento> procesoTipoDocumentoCollection1Old = persistentUsuario.getProcesoTipoDocumentoCollection1();
            Collection<ProcesoTipoDocumento> procesoTipoDocumentoCollection1New = usuario.getProcesoTipoDocumentoCollection1();
            Collection<Entidad> entidadCollectionOld = persistentUsuario.getEntidadCollection();
            Collection<Entidad> entidadCollectionNew = usuario.getEntidadCollection();
            Collection<Entidad> entidadCollection1Old = persistentUsuario.getEntidadCollection1();
            Collection<Entidad> entidadCollection1New = usuario.getEntidadCollection1();
            Collection<UnidadDocumental> unidadDocumentalCollectionOld = persistentUsuario.getUnidadDocumentalCollection();
            Collection<UnidadDocumental> unidadDocumentalCollectionNew = usuario.getUnidadDocumentalCollection();
            Collection<UnidadDocumental> unidadDocumentalCollection1Old = persistentUsuario.getUnidadDocumentalCollection1();
            Collection<UnidadDocumental> unidadDocumentalCollection1New = usuario.getUnidadDocumentalCollection1();
            Collection<Serie> serieCollectionOld = persistentUsuario.getSerieCollection();
            Collection<Serie> serieCollectionNew = usuario.getSerieCollection();
            Collection<Serie> serieCollection1Old = persistentUsuario.getSerieCollection1();
            Collection<Serie> serieCollection1New = usuario.getSerieCollection1();
            Collection<Departamento> departamentoCollectionOld = persistentUsuario.getDepartamentoCollection();
            Collection<Departamento> departamentoCollectionNew = usuario.getDepartamentoCollection();
            Collection<Departamento> departamentoCollection1Old = persistentUsuario.getDepartamentoCollection1();
            Collection<Departamento> departamentoCollection1New = usuario.getDepartamentoCollection1();
            Collection<MonitoresProceso> monitoresProcesoCollectionOld = persistentUsuario.getMonitoresProcesoCollection();
            Collection<MonitoresProceso> monitoresProcesoCollectionNew = usuario.getMonitoresProcesoCollection();
            Collection<MonitoresProceso> monitoresProcesoCollection1Old = persistentUsuario.getMonitoresProcesoCollection1();
            Collection<MonitoresProceso> monitoresProcesoCollection1New = usuario.getMonitoresProcesoCollection1();
            Collection<Modulo> moduloCollectionOld = persistentUsuario.getModuloCollection();
            Collection<Modulo> moduloCollectionNew = usuario.getModuloCollection();
            Collection<Modulo> moduloCollection1Old = persistentUsuario.getModuloCollection1();
            Collection<Modulo> moduloCollection1New = usuario.getModuloCollection1();
            Collection<Campos> camposCollectionOld = persistentUsuario.getCamposCollection();
            Collection<Campos> camposCollectionNew = usuario.getCamposCollection();
            Collection<Campos> camposCollection1Old = persistentUsuario.getCamposCollection1();
            Collection<Campos> camposCollection1New = usuario.getCamposCollection1();
            List<String> illegalOrphanMessages = null;
            for (DestinatariosDoc destinatariosDocCollectionOldDestinatariosDoc : destinatariosDocCollectionOld) {
                if (!destinatariosDocCollectionNew.contains(destinatariosDocCollectionOldDestinatariosDoc)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain DestinatariosDoc " + destinatariosDocCollectionOldDestinatariosDoc + " since its creadoPor field is not nullable.");
                }
            }
            for (DestinatariosDoc destinatariosDocCollection1OldDestinatariosDoc : destinatariosDocCollection1Old) {
                if (!destinatariosDocCollection1New.contains(destinatariosDocCollection1OldDestinatariosDoc)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain DestinatariosDoc " + destinatariosDocCollection1OldDestinatariosDoc + " since its modificadoPor field is not nullable.");
                }
            }
            for (DestinatariosDoc destinatariosDocCollection2OldDestinatariosDoc : destinatariosDocCollection2Old) {
                if (!destinatariosDocCollection2New.contains(destinatariosDocCollection2OldDestinatariosDoc)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain DestinatariosDoc " + destinatariosDocCollection2OldDestinatariosDoc + " since its destinatarioId field is not nullable.");
                }
            }
            for (Campos camposCollectionOldCampos : camposCollectionOld) {
                if (!camposCollectionNew.contains(camposCollectionOldCampos)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Campos " + camposCollectionOldCampos + " since its creadoPor field is not nullable.");
                }
            }
            for (Campos camposCollection1OldCampos : camposCollection1Old) {
                if (!camposCollection1New.contains(camposCollection1OldCampos)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Campos " + camposCollection1OldCampos + " since its modificadoPor field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (cargoNew != null) {
                cargoNew = em.getReference(cargoNew.getClass(), cargoNew.getId());
                usuario.setCargo(cargoNew);
            }
            Collection<ClaseDocumento> attachedClaseDocumentoCollectionNew = new ArrayList<ClaseDocumento>();
            for (ClaseDocumento claseDocumentoCollectionNewClaseDocumentoToAttach : claseDocumentoCollectionNew) {
                claseDocumentoCollectionNewClaseDocumentoToAttach = em.getReference(claseDocumentoCollectionNewClaseDocumentoToAttach.getClass(), claseDocumentoCollectionNewClaseDocumentoToAttach.getId());
                attachedClaseDocumentoCollectionNew.add(claseDocumentoCollectionNewClaseDocumentoToAttach);
            }
            claseDocumentoCollectionNew = attachedClaseDocumentoCollectionNew;
            usuario.setClaseDocumentoCollection(claseDocumentoCollectionNew);
            Collection<ClaseDocumento> attachedClaseDocumentoCollection1New = new ArrayList<ClaseDocumento>();
            for (ClaseDocumento claseDocumentoCollection1NewClaseDocumentoToAttach : claseDocumentoCollection1New) {
                claseDocumentoCollection1NewClaseDocumentoToAttach = em.getReference(claseDocumentoCollection1NewClaseDocumentoToAttach.getClass(), claseDocumentoCollection1NewClaseDocumentoToAttach.getId());
                attachedClaseDocumentoCollection1New.add(claseDocumentoCollection1NewClaseDocumentoToAttach);
            }
            claseDocumentoCollection1New = attachedClaseDocumentoCollection1New;
            usuario.setClaseDocumentoCollection1(claseDocumentoCollection1New);
            Collection<Grupo> attachedGrupoCollectionNew = new ArrayList<Grupo>();
            for (Grupo grupoCollectionNewGrupoToAttach : grupoCollectionNew) {
                grupoCollectionNewGrupoToAttach = em.getReference(grupoCollectionNewGrupoToAttach.getClass(), grupoCollectionNewGrupoToAttach.getId());
                attachedGrupoCollectionNew.add(grupoCollectionNewGrupoToAttach);
            }
            grupoCollectionNew = attachedGrupoCollectionNew;
            usuario.setGrupoCollection(grupoCollectionNew);
            Collection<Documento> attachedDocumentoCollectionNew = new ArrayList<Documento>();
            for (Documento documentoCollectionNewDocumentoToAttach : documentoCollectionNew) {
                documentoCollectionNewDocumentoToAttach = em.getReference(documentoCollectionNewDocumentoToAttach.getClass(), documentoCollectionNewDocumentoToAttach.getId());
                attachedDocumentoCollectionNew.add(documentoCollectionNewDocumentoToAttach);
            }
            documentoCollectionNew = attachedDocumentoCollectionNew;
            usuario.setDocumentoCollection(documentoCollectionNew);
            Collection<Documento> attachedDocumentoCollection1New = new ArrayList<Documento>();
            for (Documento documentoCollection1NewDocumentoToAttach : documentoCollection1New) {
                documentoCollection1NewDocumentoToAttach = em.getReference(documentoCollection1NewDocumentoToAttach.getClass(), documentoCollection1NewDocumentoToAttach.getId());
                attachedDocumentoCollection1New.add(documentoCollection1NewDocumentoToAttach);
            }
            documentoCollection1New = attachedDocumentoCollection1New;
            usuario.setDocumentoCollection1(documentoCollection1New);
            Collection<Acl> attachedAclCollectionNew = new ArrayList<Acl>();
            for (Acl aclCollectionNewAclToAttach : aclCollectionNew) {
                aclCollectionNewAclToAttach = em.getReference(aclCollectionNewAclToAttach.getClass(), aclCollectionNewAclToAttach.getId());
                attachedAclCollectionNew.add(aclCollectionNewAclToAttach);
            }
            aclCollectionNew = attachedAclCollectionNew;
            usuario.setAclCollection(aclCollectionNew);
            Collection<Acl> attachedAclCollection1New = new ArrayList<Acl>();
            for (Acl aclCollection1NewAclToAttach : aclCollection1New) {
                aclCollection1NewAclToAttach = em.getReference(aclCollection1NewAclToAttach.getClass(), aclCollection1NewAclToAttach.getId());
                attachedAclCollection1New.add(aclCollection1NewAclToAttach);
            }
            aclCollection1New = attachedAclCollection1New;
            usuario.setAclCollection1(aclCollection1New);
            Collection<SeccionSubSeccion> attachedSeccionSubSeccionCollectionNew = new ArrayList<SeccionSubSeccion>();
            for (SeccionSubSeccion seccionSubSeccionCollectionNewSeccionSubSeccionToAttach : seccionSubSeccionCollectionNew) {
                seccionSubSeccionCollectionNewSeccionSubSeccionToAttach = em.getReference(seccionSubSeccionCollectionNewSeccionSubSeccionToAttach.getClass(), seccionSubSeccionCollectionNewSeccionSubSeccionToAttach.getId());
                attachedSeccionSubSeccionCollectionNew.add(seccionSubSeccionCollectionNewSeccionSubSeccionToAttach);
            }
            seccionSubSeccionCollectionNew = attachedSeccionSubSeccionCollectionNew;
            usuario.setSeccionSubSeccionCollection(seccionSubSeccionCollectionNew);
            Collection<SeccionSubSeccion> attachedSeccionSubSeccionCollection1New = new ArrayList<SeccionSubSeccion>();
            for (SeccionSubSeccion seccionSubSeccionCollection1NewSeccionSubSeccionToAttach : seccionSubSeccionCollection1New) {
                seccionSubSeccionCollection1NewSeccionSubSeccionToAttach = em.getReference(seccionSubSeccionCollection1NewSeccionSubSeccionToAttach.getClass(), seccionSubSeccionCollection1NewSeccionSubSeccionToAttach.getId());
                attachedSeccionSubSeccionCollection1New.add(seccionSubSeccionCollection1NewSeccionSubSeccionToAttach);
            }
            seccionSubSeccionCollection1New = attachedSeccionSubSeccionCollection1New;
            usuario.setSeccionSubSeccionCollection1(seccionSubSeccionCollection1New);
            Collection<Preferencias> attachedPreferenciasCollectionNew = new ArrayList<Preferencias>();
            for (Preferencias preferenciasCollectionNewPreferenciasToAttach : preferenciasCollectionNew) {
                preferenciasCollectionNewPreferenciasToAttach = em.getReference(preferenciasCollectionNewPreferenciasToAttach.getClass(), preferenciasCollectionNewPreferenciasToAttach.getId());
                attachedPreferenciasCollectionNew.add(preferenciasCollectionNewPreferenciasToAttach);
            }
            preferenciasCollectionNew = attachedPreferenciasCollectionNew;
            usuario.setPreferenciasCollection(preferenciasCollectionNew);
            Collection<PlantillaDocumental> attachedPlantillaDocumentalCollectionNew = new ArrayList<PlantillaDocumental>();
            for (PlantillaDocumental plantillaDocumentalCollectionNewPlantillaDocumentalToAttach : plantillaDocumentalCollectionNew) {
                plantillaDocumentalCollectionNewPlantillaDocumentalToAttach = em.getReference(plantillaDocumentalCollectionNewPlantillaDocumentalToAttach.getClass(), plantillaDocumentalCollectionNewPlantillaDocumentalToAttach.getId());
                attachedPlantillaDocumentalCollectionNew.add(plantillaDocumentalCollectionNewPlantillaDocumentalToAttach);
            }
            plantillaDocumentalCollectionNew = attachedPlantillaDocumentalCollectionNew;
            usuario.setPlantillaDocumentalCollection(plantillaDocumentalCollectionNew);
            Collection<PlantillaDocumental> attachedPlantillaDocumentalCollection1New = new ArrayList<PlantillaDocumental>();
            for (PlantillaDocumental plantillaDocumentalCollection1NewPlantillaDocumentalToAttach : plantillaDocumentalCollection1New) {
                plantillaDocumentalCollection1NewPlantillaDocumentalToAttach = em.getReference(plantillaDocumentalCollection1NewPlantillaDocumentalToAttach.getClass(), plantillaDocumentalCollection1NewPlantillaDocumentalToAttach.getId());
                attachedPlantillaDocumentalCollection1New.add(plantillaDocumentalCollection1NewPlantillaDocumentalToAttach);
            }
            plantillaDocumentalCollection1New = attachedPlantillaDocumentalCollection1New;
            usuario.setPlantillaDocumentalCollection1(plantillaDocumentalCollection1New);
            Collection<Cargo> attachedCargoCollectionNew = new ArrayList<Cargo>();
            for (Cargo cargoCollectionNewCargoToAttach : cargoCollectionNew) {
                cargoCollectionNewCargoToAttach = em.getReference(cargoCollectionNewCargoToAttach.getClass(), cargoCollectionNewCargoToAttach.getId());
                attachedCargoCollectionNew.add(cargoCollectionNewCargoToAttach);
            }
            cargoCollectionNew = attachedCargoCollectionNew;
            usuario.setCargoCollection(cargoCollectionNew);
            Collection<Cargo> attachedCargoCollection1New = new ArrayList<Cargo>();
            for (Cargo cargoCollection1NewCargoToAttach : cargoCollection1New) {
                cargoCollection1NewCargoToAttach = em.getReference(cargoCollection1NewCargoToAttach.getClass(), cargoCollection1NewCargoToAttach.getId());
                attachedCargoCollection1New.add(cargoCollection1NewCargoToAttach);
            }
            cargoCollection1New = attachedCargoCollection1New;
            usuario.setCargoCollection1(cargoCollection1New);
            Collection<TipoDocumento> attachedTipoDocumentoCollectionNew = new ArrayList<TipoDocumento>();
            for (TipoDocumento tipoDocumentoCollectionNewTipoDocumentoToAttach : tipoDocumentoCollectionNew) {
                tipoDocumentoCollectionNewTipoDocumentoToAttach = em.getReference(tipoDocumentoCollectionNewTipoDocumentoToAttach.getClass(), tipoDocumentoCollectionNewTipoDocumentoToAttach.getId());
                attachedTipoDocumentoCollectionNew.add(tipoDocumentoCollectionNewTipoDocumentoToAttach);
            }
            tipoDocumentoCollectionNew = attachedTipoDocumentoCollectionNew;
            usuario.setTipoDocumentoCollection(tipoDocumentoCollectionNew);
            Collection<TipoDocumento> attachedTipoDocumentoCollection1New = new ArrayList<TipoDocumento>();
            for (TipoDocumento tipoDocumentoCollection1NewTipoDocumentoToAttach : tipoDocumentoCollection1New) {
                tipoDocumentoCollection1NewTipoDocumentoToAttach = em.getReference(tipoDocumentoCollection1NewTipoDocumentoToAttach.getClass(), tipoDocumentoCollection1NewTipoDocumentoToAttach.getId());
                attachedTipoDocumentoCollection1New.add(tipoDocumentoCollection1NewTipoDocumentoToAttach);
            }
            tipoDocumentoCollection1New = attachedTipoDocumentoCollection1New;
            usuario.setTipoDocumentoCollection1(tipoDocumentoCollection1New);
            Collection<SubSerie> attachedSubSerieCollectionNew = new ArrayList<SubSerie>();
            for (SubSerie subSerieCollectionNewSubSerieToAttach : subSerieCollectionNew) {
                subSerieCollectionNewSubSerieToAttach = em.getReference(subSerieCollectionNewSubSerieToAttach.getClass(), subSerieCollectionNewSubSerieToAttach.getId());
                attachedSubSerieCollectionNew.add(subSerieCollectionNewSubSerieToAttach);
            }
            subSerieCollectionNew = attachedSubSerieCollectionNew;
            usuario.setSubSerieCollection(subSerieCollectionNew);
            Collection<SubSerie> attachedSubSerieCollection1New = new ArrayList<SubSerie>();
            for (SubSerie subSerieCollection1NewSubSerieToAttach : subSerieCollection1New) {
                subSerieCollection1NewSubSerieToAttach = em.getReference(subSerieCollection1NewSubSerieToAttach.getClass(), subSerieCollection1NewSubSerieToAttach.getId());
                attachedSubSerieCollection1New.add(subSerieCollection1NewSubSerieToAttach);
            }
            subSerieCollection1New = attachedSubSerieCollection1New;
            usuario.setSubSerieCollection1(subSerieCollection1New);
            Collection<Municipio> attachedMunicipioCollectionNew = new ArrayList<Municipio>();
            for (Municipio municipioCollectionNewMunicipioToAttach : municipioCollectionNew) {
                municipioCollectionNewMunicipioToAttach = em.getReference(municipioCollectionNewMunicipioToAttach.getClass(), municipioCollectionNewMunicipioToAttach.getId());
                attachedMunicipioCollectionNew.add(municipioCollectionNewMunicipioToAttach);
            }
            municipioCollectionNew = attachedMunicipioCollectionNew;
            usuario.setMunicipioCollection(municipioCollectionNew);
            Collection<Municipio> attachedMunicipioCollection1New = new ArrayList<Municipio>();
            for (Municipio municipioCollection1NewMunicipioToAttach : municipioCollection1New) {
                municipioCollection1NewMunicipioToAttach = em.getReference(municipioCollection1NewMunicipioToAttach.getClass(), municipioCollection1NewMunicipioToAttach.getId());
                attachedMunicipioCollection1New.add(municipioCollection1NewMunicipioToAttach);
            }
            municipioCollection1New = attachedMunicipioCollection1New;
            usuario.setMunicipioCollection1(municipioCollection1New);
            Collection<ProcesoNegocio> attachedProcesoNegocioCollectionNew = new ArrayList<ProcesoNegocio>();
            for (ProcesoNegocio procesoNegocioCollectionNewProcesoNegocioToAttach : procesoNegocioCollectionNew) {
                procesoNegocioCollectionNewProcesoNegocioToAttach = em.getReference(procesoNegocioCollectionNewProcesoNegocioToAttach.getClass(), procesoNegocioCollectionNewProcesoNegocioToAttach.getId());
                attachedProcesoNegocioCollectionNew.add(procesoNegocioCollectionNewProcesoNegocioToAttach);
            }
            procesoNegocioCollectionNew = attachedProcesoNegocioCollectionNew;
            usuario.setProcesoNegocioCollection(procesoNegocioCollectionNew);
            Collection<ProcesoNegocio> attachedProcesoNegocioCollection1New = new ArrayList<ProcesoNegocio>();
            for (ProcesoNegocio procesoNegocioCollection1NewProcesoNegocioToAttach : procesoNegocioCollection1New) {
                procesoNegocioCollection1NewProcesoNegocioToAttach = em.getReference(procesoNegocioCollection1NewProcesoNegocioToAttach.getClass(), procesoNegocioCollection1NewProcesoNegocioToAttach.getId());
                attachedProcesoNegocioCollection1New.add(procesoNegocioCollection1NewProcesoNegocioToAttach);
            }
            procesoNegocioCollection1New = attachedProcesoNegocioCollection1New;
            usuario.setProcesoNegocioCollection1(procesoNegocioCollection1New);
            Collection<Corregimiento> attachedCorregimientoCollectionNew = new ArrayList<Corregimiento>();
            for (Corregimiento corregimientoCollectionNewCorregimientoToAttach : corregimientoCollectionNew) {
                corregimientoCollectionNewCorregimientoToAttach = em.getReference(corregimientoCollectionNewCorregimientoToAttach.getClass(), corregimientoCollectionNewCorregimientoToAttach.getId());
                attachedCorregimientoCollectionNew.add(corregimientoCollectionNewCorregimientoToAttach);
            }
            corregimientoCollectionNew = attachedCorregimientoCollectionNew;
            usuario.setCorregimientoCollection(corregimientoCollectionNew);
            Collection<Corregimiento> attachedCorregimientoCollection1New = new ArrayList<Corregimiento>();
            for (Corregimiento corregimientoCollection1NewCorregimientoToAttach : corregimientoCollection1New) {
                corregimientoCollection1NewCorregimientoToAttach = em.getReference(corregimientoCollection1NewCorregimientoToAttach.getClass(), corregimientoCollection1NewCorregimientoToAttach.getId());
                attachedCorregimientoCollection1New.add(corregimientoCollection1NewCorregimientoToAttach);
            }
            corregimientoCollection1New = attachedCorregimientoCollection1New;
            usuario.setCorregimientoCollection1(corregimientoCollection1New);
            Collection<GrupoUsuario> attachedGrupoUsuarioCollectionNew = new ArrayList<GrupoUsuario>();
            for (GrupoUsuario grupoUsuarioCollectionNewGrupoUsuarioToAttach : grupoUsuarioCollectionNew) {
                grupoUsuarioCollectionNewGrupoUsuarioToAttach = em.getReference(grupoUsuarioCollectionNewGrupoUsuarioToAttach.getClass(), grupoUsuarioCollectionNewGrupoUsuarioToAttach.getId());
                attachedGrupoUsuarioCollectionNew.add(grupoUsuarioCollectionNewGrupoUsuarioToAttach);
            }
            grupoUsuarioCollectionNew = attachedGrupoUsuarioCollectionNew;
            usuario.setGrupoUsuarioCollection(grupoUsuarioCollectionNew);
            Collection<GrupoUsuario> attachedGrupoUsuarioCollection1New = new ArrayList<GrupoUsuario>();
            for (GrupoUsuario grupoUsuarioCollection1NewGrupoUsuarioToAttach : grupoUsuarioCollection1New) {
                grupoUsuarioCollection1NewGrupoUsuarioToAttach = em.getReference(grupoUsuarioCollection1NewGrupoUsuarioToAttach.getClass(), grupoUsuarioCollection1NewGrupoUsuarioToAttach.getId());
                attachedGrupoUsuarioCollection1New.add(grupoUsuarioCollection1NewGrupoUsuarioToAttach);
            }
            grupoUsuarioCollection1New = attachedGrupoUsuarioCollection1New;
            usuario.setGrupoUsuarioCollection1(grupoUsuarioCollection1New);
            Collection<GrupoUsuario> attachedGrupoUsuarioCollection2New = new ArrayList<GrupoUsuario>();
            for (GrupoUsuario grupoUsuarioCollection2NewGrupoUsuarioToAttach : grupoUsuarioCollection2New) {
                grupoUsuarioCollection2NewGrupoUsuarioToAttach = em.getReference(grupoUsuarioCollection2NewGrupoUsuarioToAttach.getClass(), grupoUsuarioCollection2NewGrupoUsuarioToAttach.getId());
                attachedGrupoUsuarioCollection2New.add(grupoUsuarioCollection2NewGrupoUsuarioToAttach);
            }
            grupoUsuarioCollection2New = attachedGrupoUsuarioCollection2New;
            usuario.setGrupoUsuarioCollection2(grupoUsuarioCollection2New);
            Collection<DestinatariosDoc> attachedDestinatariosDocCollectionNew = new ArrayList<DestinatariosDoc>();
            for (DestinatariosDoc destinatariosDocCollectionNewDestinatariosDocToAttach : destinatariosDocCollectionNew) {
                destinatariosDocCollectionNewDestinatariosDocToAttach = em.getReference(destinatariosDocCollectionNewDestinatariosDocToAttach.getClass(), destinatariosDocCollectionNewDestinatariosDocToAttach.getId());
                attachedDestinatariosDocCollectionNew.add(destinatariosDocCollectionNewDestinatariosDocToAttach);
            }
            destinatariosDocCollectionNew = attachedDestinatariosDocCollectionNew;
            usuario.setDestinatariosDocCollection(destinatariosDocCollectionNew);
            Collection<DestinatariosDoc> attachedDestinatariosDocCollection1New = new ArrayList<DestinatariosDoc>();
            for (DestinatariosDoc destinatariosDocCollection1NewDestinatariosDocToAttach : destinatariosDocCollection1New) {
                destinatariosDocCollection1NewDestinatariosDocToAttach = em.getReference(destinatariosDocCollection1NewDestinatariosDocToAttach.getClass(), destinatariosDocCollection1NewDestinatariosDocToAttach.getId());
                attachedDestinatariosDocCollection1New.add(destinatariosDocCollection1NewDestinatariosDocToAttach);
            }
            destinatariosDocCollection1New = attachedDestinatariosDocCollection1New;
            usuario.setDestinatariosDocCollection1(destinatariosDocCollection1New);
            Collection<DestinatariosDoc> attachedDestinatariosDocCollection2New = new ArrayList<DestinatariosDoc>();
            for (DestinatariosDoc destinatariosDocCollection2NewDestinatariosDocToAttach : destinatariosDocCollection2New) {
                destinatariosDocCollection2NewDestinatariosDocToAttach = em.getReference(destinatariosDocCollection2NewDestinatariosDocToAttach.getClass(), destinatariosDocCollection2NewDestinatariosDocToAttach.getId());
                attachedDestinatariosDocCollection2New.add(destinatariosDocCollection2NewDestinatariosDocToAttach);
            }
            destinatariosDocCollection2New = attachedDestinatariosDocCollection2New;
            usuario.setDestinatariosDocCollection2(destinatariosDocCollection2New);
            Collection<Autor> attachedAutorCollectionNew = new ArrayList<Autor>();
            for (Autor autorCollectionNewAutorToAttach : autorCollectionNew) {
                autorCollectionNewAutorToAttach = em.getReference(autorCollectionNewAutorToAttach.getClass(), autorCollectionNewAutorToAttach.getId());
                attachedAutorCollectionNew.add(autorCollectionNewAutorToAttach);
            }
            autorCollectionNew = attachedAutorCollectionNew;
            usuario.setAutorCollection(autorCollectionNew);
            Collection<Autor> attachedAutorCollection1New = new ArrayList<Autor>();
            for (Autor autorCollection1NewAutorToAttach : autorCollection1New) {
                autorCollection1NewAutorToAttach = em.getReference(autorCollection1NewAutorToAttach.getClass(), autorCollection1NewAutorToAttach.getId());
                attachedAutorCollection1New.add(autorCollection1NewAutorToAttach);
            }
            autorCollection1New = attachedAutorCollection1New;
            usuario.setAutorCollection1(autorCollection1New);
            Collection<Notificacion> attachedNotificacionCollectionNew = new ArrayList<Notificacion>();
            for (Notificacion notificacionCollectionNewNotificacionToAttach : notificacionCollectionNew) {
                notificacionCollectionNewNotificacionToAttach = em.getReference(notificacionCollectionNewNotificacionToAttach.getClass(), notificacionCollectionNewNotificacionToAttach.getId());
                attachedNotificacionCollectionNew.add(notificacionCollectionNewNotificacionToAttach);
            }
            notificacionCollectionNew = attachedNotificacionCollectionNew;
            usuario.setNotificacionCollection(notificacionCollectionNew);
            Collection<Pais> attachedPaisCollectionNew = new ArrayList<Pais>();
            for (Pais paisCollectionNewPaisToAttach : paisCollectionNew) {
                paisCollectionNewPaisToAttach = em.getReference(paisCollectionNewPaisToAttach.getClass(), paisCollectionNewPaisToAttach.getId());
                attachedPaisCollectionNew.add(paisCollectionNewPaisToAttach);
            }
            paisCollectionNew = attachedPaisCollectionNew;
            usuario.setPaisCollection(paisCollectionNew);
            Collection<Pais> attachedPaisCollection1New = new ArrayList<Pais>();
            for (Pais paisCollection1NewPaisToAttach : paisCollection1New) {
                paisCollection1NewPaisToAttach = em.getReference(paisCollection1NewPaisToAttach.getClass(), paisCollection1NewPaisToAttach.getId());
                attachedPaisCollection1New.add(paisCollection1NewPaisToAttach);
            }
            paisCollection1New = attachedPaisCollection1New;
            usuario.setPaisCollection1(paisCollection1New);
            Collection<Consecutivo> attachedConsecutivoCollectionNew = new ArrayList<Consecutivo>();
            for (Consecutivo consecutivoCollectionNewConsecutivoToAttach : consecutivoCollectionNew) {
                consecutivoCollectionNewConsecutivoToAttach = em.getReference(consecutivoCollectionNewConsecutivoToAttach.getClass(), consecutivoCollectionNewConsecutivoToAttach.getId());
                attachedConsecutivoCollectionNew.add(consecutivoCollectionNewConsecutivoToAttach);
            }
            consecutivoCollectionNew = attachedConsecutivoCollectionNew;
            usuario.setConsecutivoCollection(consecutivoCollectionNew);
            Collection<Consecutivo> attachedConsecutivoCollection1New = new ArrayList<Consecutivo>();
            for (Consecutivo consecutivoCollection1NewConsecutivoToAttach : consecutivoCollection1New) {
                consecutivoCollection1NewConsecutivoToAttach = em.getReference(consecutivoCollection1NewConsecutivoToAttach.getClass(), consecutivoCollection1NewConsecutivoToAttach.getId());
                attachedConsecutivoCollection1New.add(consecutivoCollection1NewConsecutivoToAttach);
            }
            consecutivoCollection1New = attachedConsecutivoCollection1New;
            usuario.setConsecutivoCollection1(consecutivoCollection1New);
            Collection<SignaturaTopografica> attachedSignaturaTopograficaCollectionNew = new ArrayList<SignaturaTopografica>();
            for (SignaturaTopografica signaturaTopograficaCollectionNewSignaturaTopograficaToAttach : signaturaTopograficaCollectionNew) {
                signaturaTopograficaCollectionNewSignaturaTopograficaToAttach = em.getReference(signaturaTopograficaCollectionNewSignaturaTopograficaToAttach.getClass(), signaturaTopograficaCollectionNewSignaturaTopograficaToAttach.getId());
                attachedSignaturaTopograficaCollectionNew.add(signaturaTopograficaCollectionNewSignaturaTopograficaToAttach);
            }
            signaturaTopograficaCollectionNew = attachedSignaturaTopograficaCollectionNew;
            usuario.setSignaturaTopograficaCollection(signaturaTopograficaCollectionNew);
            Collection<SignaturaTopografica> attachedSignaturaTopograficaCollection1New = new ArrayList<SignaturaTopografica>();
            for (SignaturaTopografica signaturaTopograficaCollection1NewSignaturaTopograficaToAttach : signaturaTopograficaCollection1New) {
                signaturaTopograficaCollection1NewSignaturaTopograficaToAttach = em.getReference(signaturaTopograficaCollection1NewSignaturaTopograficaToAttach.getClass(), signaturaTopograficaCollection1NewSignaturaTopograficaToAttach.getId());
                attachedSignaturaTopograficaCollection1New.add(signaturaTopograficaCollection1NewSignaturaTopograficaToAttach);
            }
            signaturaTopograficaCollection1New = attachedSignaturaTopograficaCollection1New;
            usuario.setSignaturaTopograficaCollection1(signaturaTopograficaCollection1New);
            Collection<TipoDocumental> attachedTipoDocumentalCollectionNew = new ArrayList<TipoDocumental>();
            for (TipoDocumental tipoDocumentalCollectionNewTipoDocumentalToAttach : tipoDocumentalCollectionNew) {
                tipoDocumentalCollectionNewTipoDocumentalToAttach = em.getReference(tipoDocumentalCollectionNewTipoDocumentalToAttach.getClass(), tipoDocumentalCollectionNewTipoDocumentalToAttach.getId());
                attachedTipoDocumentalCollectionNew.add(tipoDocumentalCollectionNewTipoDocumentalToAttach);
            }
            tipoDocumentalCollectionNew = attachedTipoDocumentalCollectionNew;
            usuario.setTipoDocumentalCollection(tipoDocumentalCollectionNew);
            Collection<TipoDocumental> attachedTipoDocumentalCollection1New = new ArrayList<TipoDocumental>();
            for (TipoDocumental tipoDocumentalCollection1NewTipoDocumentalToAttach : tipoDocumentalCollection1New) {
                tipoDocumentalCollection1NewTipoDocumentalToAttach = em.getReference(tipoDocumentalCollection1NewTipoDocumentalToAttach.getClass(), tipoDocumentalCollection1NewTipoDocumentalToAttach.getId());
                attachedTipoDocumentalCollection1New.add(tipoDocumentalCollection1NewTipoDocumentalToAttach);
            }
            tipoDocumentalCollection1New = attachedTipoDocumentalCollection1New;
            usuario.setTipoDocumentalCollection1(tipoDocumentalCollection1New);
            Collection<Transportador> attachedTransportadorCollectionNew = new ArrayList<Transportador>();
            for (Transportador transportadorCollectionNewTransportadorToAttach : transportadorCollectionNew) {
                transportadorCollectionNewTransportadorToAttach = em.getReference(transportadorCollectionNewTransportadorToAttach.getClass(), transportadorCollectionNewTransportadorToAttach.getId());
                attachedTransportadorCollectionNew.add(transportadorCollectionNewTransportadorToAttach);
            }
            transportadorCollectionNew = attachedTransportadorCollectionNew;
            usuario.setTransportadorCollection(transportadorCollectionNew);
            Collection<Transportador> attachedTransportadorCollection1New = new ArrayList<Transportador>();
            for (Transportador transportadorCollection1NewTransportadorToAttach : transportadorCollection1New) {
                transportadorCollection1NewTransportadorToAttach = em.getReference(transportadorCollection1NewTransportadorToAttach.getClass(), transportadorCollection1NewTransportadorToAttach.getId());
                attachedTransportadorCollection1New.add(transportadorCollection1NewTransportadorToAttach);
            }
            transportadorCollection1New = attachedTransportadorCollection1New;
            usuario.setTransportadorCollection1(transportadorCollection1New);
            Collection<ProcesoDocumental> attachedProcesodocumentalCollectionNew = new ArrayList<ProcesoDocumental>();
            for (ProcesoDocumental procesodocumentalCollectionNewProcesoDocumentalToAttach : procesodocumentalCollectionNew) {
                procesodocumentalCollectionNewProcesoDocumentalToAttach = em.getReference(procesodocumentalCollectionNewProcesoDocumentalToAttach.getClass(), procesodocumentalCollectionNewProcesoDocumentalToAttach.getId());
                attachedProcesodocumentalCollectionNew.add(procesodocumentalCollectionNewProcesoDocumentalToAttach);
            }
            procesodocumentalCollectionNew = attachedProcesodocumentalCollectionNew;
            usuario.setProcesodocumentalCollection(procesodocumentalCollectionNew);
            Collection<ProcesoDocumental> attachedProcesodocumentalCollection1New = new ArrayList<ProcesoDocumental>();
            for (ProcesoDocumental procesodocumentalCollection1NewProcesoDocumentalToAttach : procesodocumentalCollection1New) {
                procesodocumentalCollection1NewProcesoDocumentalToAttach = em.getReference(procesodocumentalCollection1NewProcesoDocumentalToAttach.getClass(), procesodocumentalCollection1NewProcesoDocumentalToAttach.getId());
                attachedProcesodocumentalCollection1New.add(procesodocumentalCollection1NewProcesoDocumentalToAttach);
            }
            procesodocumentalCollection1New = attachedProcesodocumentalCollection1New;
            usuario.setProcesodocumentalCollection1(procesodocumentalCollection1New);
            Collection<ProcesoTipoDocumento> attachedProcesoTipoDocumentoCollectionNew = new ArrayList<ProcesoTipoDocumento>();
            for (ProcesoTipoDocumento procesoTipoDocumentoCollectionNewProcesoTipoDocumentoToAttach : procesoTipoDocumentoCollectionNew) {
                procesoTipoDocumentoCollectionNewProcesoTipoDocumentoToAttach = em.getReference(procesoTipoDocumentoCollectionNewProcesoTipoDocumentoToAttach.getClass(), procesoTipoDocumentoCollectionNewProcesoTipoDocumentoToAttach.getId());
                attachedProcesoTipoDocumentoCollectionNew.add(procesoTipoDocumentoCollectionNewProcesoTipoDocumentoToAttach);
            }
            procesoTipoDocumentoCollectionNew = attachedProcesoTipoDocumentoCollectionNew;
            usuario.setProcesoTipoDocumentoCollection(procesoTipoDocumentoCollectionNew);
            Collection<ProcesoTipoDocumento> attachedProcesoTipoDocumentoCollection1New = new ArrayList<ProcesoTipoDocumento>();
            for (ProcesoTipoDocumento procesoTipoDocumentoCollection1NewProcesoTipoDocumentoToAttach : procesoTipoDocumentoCollection1New) {
                procesoTipoDocumentoCollection1NewProcesoTipoDocumentoToAttach = em.getReference(procesoTipoDocumentoCollection1NewProcesoTipoDocumentoToAttach.getClass(), procesoTipoDocumentoCollection1NewProcesoTipoDocumentoToAttach.getId());
                attachedProcesoTipoDocumentoCollection1New.add(procesoTipoDocumentoCollection1NewProcesoTipoDocumentoToAttach);
            }
            procesoTipoDocumentoCollection1New = attachedProcesoTipoDocumentoCollection1New;
            usuario.setProcesoTipoDocumentoCollection1(procesoTipoDocumentoCollection1New);
            Collection<Entidad> attachedEntidadCollectionNew = new ArrayList<Entidad>();
            for (Entidad entidadCollectionNewEntidadToAttach : entidadCollectionNew) {
                entidadCollectionNewEntidadToAttach = em.getReference(entidadCollectionNewEntidadToAttach.getClass(), entidadCollectionNewEntidadToAttach.getId());
                attachedEntidadCollectionNew.add(entidadCollectionNewEntidadToAttach);
            }
            entidadCollectionNew = attachedEntidadCollectionNew;
            usuario.setEntidadCollection(entidadCollectionNew);
            Collection<Entidad> attachedEntidadCollection1New = new ArrayList<Entidad>();
            for (Entidad entidadCollection1NewEntidadToAttach : entidadCollection1New) {
                entidadCollection1NewEntidadToAttach = em.getReference(entidadCollection1NewEntidadToAttach.getClass(), entidadCollection1NewEntidadToAttach.getId());
                attachedEntidadCollection1New.add(entidadCollection1NewEntidadToAttach);
            }
            entidadCollection1New = attachedEntidadCollection1New;
            usuario.setEntidadCollection1(entidadCollection1New);
            Collection<UnidadDocumental> attachedUnidadDocumentalCollectionNew = new ArrayList<UnidadDocumental>();
            for (UnidadDocumental unidadDocumentalCollectionNewUnidadDocumentalToAttach : unidadDocumentalCollectionNew) {
                unidadDocumentalCollectionNewUnidadDocumentalToAttach = em.getReference(unidadDocumentalCollectionNewUnidadDocumentalToAttach.getClass(), unidadDocumentalCollectionNewUnidadDocumentalToAttach.getId());
                attachedUnidadDocumentalCollectionNew.add(unidadDocumentalCollectionNewUnidadDocumentalToAttach);
            }
            unidadDocumentalCollectionNew = attachedUnidadDocumentalCollectionNew;
            usuario.setUnidadDocumentalCollection(unidadDocumentalCollectionNew);
            Collection<UnidadDocumental> attachedUnidadDocumentalCollection1New = new ArrayList<UnidadDocumental>();
            for (UnidadDocumental unidadDocumentalCollection1NewUnidadDocumentalToAttach : unidadDocumentalCollection1New) {
                unidadDocumentalCollection1NewUnidadDocumentalToAttach = em.getReference(unidadDocumentalCollection1NewUnidadDocumentalToAttach.getClass(), unidadDocumentalCollection1NewUnidadDocumentalToAttach.getId());
                attachedUnidadDocumentalCollection1New.add(unidadDocumentalCollection1NewUnidadDocumentalToAttach);
            }
            unidadDocumentalCollection1New = attachedUnidadDocumentalCollection1New;
            usuario.setUnidadDocumentalCollection1(unidadDocumentalCollection1New);
            Collection<Serie> attachedSerieCollectionNew = new ArrayList<Serie>();
            for (Serie serieCollectionNewSerieToAttach : serieCollectionNew) {
                serieCollectionNewSerieToAttach = em.getReference(serieCollectionNewSerieToAttach.getClass(), serieCollectionNewSerieToAttach.getId());
                attachedSerieCollectionNew.add(serieCollectionNewSerieToAttach);
            }
            serieCollectionNew = attachedSerieCollectionNew;
            usuario.setSerieCollection(serieCollectionNew);
            Collection<Serie> attachedSerieCollection1New = new ArrayList<Serie>();
            for (Serie serieCollection1NewSerieToAttach : serieCollection1New) {
                serieCollection1NewSerieToAttach = em.getReference(serieCollection1NewSerieToAttach.getClass(), serieCollection1NewSerieToAttach.getId());
                attachedSerieCollection1New.add(serieCollection1NewSerieToAttach);
            }
            serieCollection1New = attachedSerieCollection1New;
            usuario.setSerieCollection1(serieCollection1New);
            Collection<Departamento> attachedDepartamentoCollectionNew = new ArrayList<Departamento>();
            for (Departamento departamentoCollectionNewDepartamentoToAttach : departamentoCollectionNew) {
                departamentoCollectionNewDepartamentoToAttach = em.getReference(departamentoCollectionNewDepartamentoToAttach.getClass(), departamentoCollectionNewDepartamentoToAttach.getId());
                attachedDepartamentoCollectionNew.add(departamentoCollectionNewDepartamentoToAttach);
            }
            departamentoCollectionNew = attachedDepartamentoCollectionNew;
            usuario.setDepartamentoCollection(departamentoCollectionNew);
            Collection<Departamento> attachedDepartamentoCollection1New = new ArrayList<Departamento>();
            for (Departamento departamentoCollection1NewDepartamentoToAttach : departamentoCollection1New) {
                departamentoCollection1NewDepartamentoToAttach = em.getReference(departamentoCollection1NewDepartamentoToAttach.getClass(), departamentoCollection1NewDepartamentoToAttach.getId());
                attachedDepartamentoCollection1New.add(departamentoCollection1NewDepartamentoToAttach);
            }
            departamentoCollection1New = attachedDepartamentoCollection1New;
            usuario.setDepartamentoCollection1(departamentoCollection1New);
            Collection<MonitoresProceso> attachedMonitoresProcesoCollectionNew = new ArrayList<MonitoresProceso>();
            for (MonitoresProceso monitoresProcesoCollectionNewMonitoresProcesoToAttach : monitoresProcesoCollectionNew) {
                monitoresProcesoCollectionNewMonitoresProcesoToAttach = em.getReference(monitoresProcesoCollectionNewMonitoresProcesoToAttach.getClass(), monitoresProcesoCollectionNewMonitoresProcesoToAttach.getId());
                attachedMonitoresProcesoCollectionNew.add(monitoresProcesoCollectionNewMonitoresProcesoToAttach);
            }
            monitoresProcesoCollectionNew = attachedMonitoresProcesoCollectionNew;
            usuario.setMonitoresProcesoCollection(monitoresProcesoCollectionNew);
            Collection<MonitoresProceso> attachedMonitoresProcesoCollection1New = new ArrayList<MonitoresProceso>();
            for (MonitoresProceso monitoresProcesoCollection1NewMonitoresProcesoToAttach : monitoresProcesoCollection1New) {
                monitoresProcesoCollection1NewMonitoresProcesoToAttach = em.getReference(monitoresProcesoCollection1NewMonitoresProcesoToAttach.getClass(), monitoresProcesoCollection1NewMonitoresProcesoToAttach.getId());
                attachedMonitoresProcesoCollection1New.add(monitoresProcesoCollection1NewMonitoresProcesoToAttach);
            }
            monitoresProcesoCollection1New = attachedMonitoresProcesoCollection1New;
            usuario.setMonitoresProcesoCollection1(monitoresProcesoCollection1New);
            Collection<Modulo> attachedModuloCollectionNew = new ArrayList<Modulo>();
            for (Modulo moduloCollectionNewModuloToAttach : moduloCollectionNew) {
                moduloCollectionNewModuloToAttach = em.getReference(moduloCollectionNewModuloToAttach.getClass(), moduloCollectionNewModuloToAttach.getId());
                attachedModuloCollectionNew.add(moduloCollectionNewModuloToAttach);
            }
            moduloCollectionNew = attachedModuloCollectionNew;
            usuario.setModuloCollection(moduloCollectionNew);
            Collection<Modulo> attachedModuloCollection1New = new ArrayList<Modulo>();
            for (Modulo moduloCollection1NewModuloToAttach : moduloCollection1New) {
                moduloCollection1NewModuloToAttach = em.getReference(moduloCollection1NewModuloToAttach.getClass(), moduloCollection1NewModuloToAttach.getId());
                attachedModuloCollection1New.add(moduloCollection1NewModuloToAttach);
            }
            moduloCollection1New = attachedModuloCollection1New;
            usuario.setModuloCollection1(moduloCollection1New);
            Collection<Campos> attachedCamposCollectionNew = new ArrayList<Campos>();
            for (Campos camposCollectionNewCamposToAttach : camposCollectionNew) {
                camposCollectionNewCamposToAttach = em.getReference(camposCollectionNewCamposToAttach.getClass(), camposCollectionNewCamposToAttach.getId());
                attachedCamposCollectionNew.add(camposCollectionNewCamposToAttach);
            }
            camposCollectionNew = attachedCamposCollectionNew;
            usuario.setCamposCollection(camposCollectionNew);
            Collection<Campos> attachedCamposCollection1New = new ArrayList<Campos>();
            for (Campos camposCollection1NewCamposToAttach : camposCollection1New) {
                camposCollection1NewCamposToAttach = em.getReference(camposCollection1NewCamposToAttach.getClass(), camposCollection1NewCamposToAttach.getId());
                attachedCamposCollection1New.add(camposCollection1NewCamposToAttach);
            }
            camposCollection1New = attachedCamposCollection1New;
            usuario.setCamposCollection1(camposCollection1New);
            usuario = em.merge(usuario);
            if (cargoOld != null && !cargoOld.equals(cargoNew)) {
                cargoOld.setCreadoPor(null);
                cargoOld = em.merge(cargoOld);
            }
            if (cargoNew != null && !cargoNew.equals(cargoOld)) {
                Usuario oldCreadoPorOfCargo = cargoNew.getCreadoPor();
                if (oldCreadoPorOfCargo != null) {
                    oldCreadoPorOfCargo.setCargo(null);
                    oldCreadoPorOfCargo = em.merge(oldCreadoPorOfCargo);
                }
                cargoNew.setCreadoPor(usuario);
                cargoNew = em.merge(cargoNew);
            }
            for (ClaseDocumento claseDocumentoCollectionOldClaseDocumento : claseDocumentoCollectionOld) {
                if (!claseDocumentoCollectionNew.contains(claseDocumentoCollectionOldClaseDocumento)) {
                    claseDocumentoCollectionOldClaseDocumento.setCreadoPor(null);
                    claseDocumentoCollectionOldClaseDocumento = em.merge(claseDocumentoCollectionOldClaseDocumento);
                }
            }
            for (ClaseDocumento claseDocumentoCollectionNewClaseDocumento : claseDocumentoCollectionNew) {
                if (!claseDocumentoCollectionOld.contains(claseDocumentoCollectionNewClaseDocumento)) {
                    Usuario oldCreadoPorOfClaseDocumentoCollectionNewClaseDocumento = claseDocumentoCollectionNewClaseDocumento.getCreadoPor();
                    claseDocumentoCollectionNewClaseDocumento.setCreadoPor(usuario);
                    claseDocumentoCollectionNewClaseDocumento = em.merge(claseDocumentoCollectionNewClaseDocumento);
                    if (oldCreadoPorOfClaseDocumentoCollectionNewClaseDocumento != null && !oldCreadoPorOfClaseDocumentoCollectionNewClaseDocumento.equals(usuario)) {
                        oldCreadoPorOfClaseDocumentoCollectionNewClaseDocumento.getClaseDocumentoCollection().remove(claseDocumentoCollectionNewClaseDocumento);
                        oldCreadoPorOfClaseDocumentoCollectionNewClaseDocumento = em.merge(oldCreadoPorOfClaseDocumentoCollectionNewClaseDocumento);
                    }
                }
            }
            for (ClaseDocumento claseDocumentoCollection1OldClaseDocumento : claseDocumentoCollection1Old) {
                if (!claseDocumentoCollection1New.contains(claseDocumentoCollection1OldClaseDocumento)) {
                    claseDocumentoCollection1OldClaseDocumento.setModificadoPor(null);
                    claseDocumentoCollection1OldClaseDocumento = em.merge(claseDocumentoCollection1OldClaseDocumento);
                }
            }
            for (ClaseDocumento claseDocumentoCollection1NewClaseDocumento : claseDocumentoCollection1New) {
                if (!claseDocumentoCollection1Old.contains(claseDocumentoCollection1NewClaseDocumento)) {
                    Usuario oldModificadoPorOfClaseDocumentoCollection1NewClaseDocumento = claseDocumentoCollection1NewClaseDocumento.getModificadoPor();
                    claseDocumentoCollection1NewClaseDocumento.setModificadoPor(usuario);
                    claseDocumentoCollection1NewClaseDocumento = em.merge(claseDocumentoCollection1NewClaseDocumento);
                    if (oldModificadoPorOfClaseDocumentoCollection1NewClaseDocumento != null && !oldModificadoPorOfClaseDocumentoCollection1NewClaseDocumento.equals(usuario)) {
                        oldModificadoPorOfClaseDocumentoCollection1NewClaseDocumento.getClaseDocumentoCollection1().remove(claseDocumentoCollection1NewClaseDocumento);
                        oldModificadoPorOfClaseDocumentoCollection1NewClaseDocumento = em.merge(oldModificadoPorOfClaseDocumentoCollection1NewClaseDocumento);
                    }
                }
            }
            for (Grupo grupoCollectionOldGrupo : grupoCollectionOld) {
                if (!grupoCollectionNew.contains(grupoCollectionOldGrupo)) {
                    grupoCollectionOldGrupo.setModificadoPor(null);
                    grupoCollectionOldGrupo = em.merge(grupoCollectionOldGrupo);
                }
            }
            for (Grupo grupoCollectionNewGrupo : grupoCollectionNew) {
                if (!grupoCollectionOld.contains(grupoCollectionNewGrupo)) {
                    Usuario oldModificadoPorOfGrupoCollectionNewGrupo = grupoCollectionNewGrupo.getModificadoPor();
                    grupoCollectionNewGrupo.setModificadoPor(usuario);
                    grupoCollectionNewGrupo = em.merge(grupoCollectionNewGrupo);
                    if (oldModificadoPorOfGrupoCollectionNewGrupo != null && !oldModificadoPorOfGrupoCollectionNewGrupo.equals(usuario)) {
                        oldModificadoPorOfGrupoCollectionNewGrupo.getGrupoCollection().remove(grupoCollectionNewGrupo);
                        oldModificadoPorOfGrupoCollectionNewGrupo = em.merge(oldModificadoPorOfGrupoCollectionNewGrupo);
                    }
                }
            }
            for (Documento documentoCollectionOldDocumento : documentoCollectionOld) {
                if (!documentoCollectionNew.contains(documentoCollectionOldDocumento)) {
                    documentoCollectionOldDocumento.setCreadoPor(null);
                    documentoCollectionOldDocumento = em.merge(documentoCollectionOldDocumento);
                }
            }
            for (Documento documentoCollectionNewDocumento : documentoCollectionNew) {
                if (!documentoCollectionOld.contains(documentoCollectionNewDocumento)) {
                    Usuario oldCreadoPorOfDocumentoCollectionNewDocumento = documentoCollectionNewDocumento.getCreadoPor();
                    documentoCollectionNewDocumento.setCreadoPor(usuario);
                    documentoCollectionNewDocumento = em.merge(documentoCollectionNewDocumento);
                    if (oldCreadoPorOfDocumentoCollectionNewDocumento != null && !oldCreadoPorOfDocumentoCollectionNewDocumento.equals(usuario)) {
                        oldCreadoPorOfDocumentoCollectionNewDocumento.getDocumentoCollection().remove(documentoCollectionNewDocumento);
                        oldCreadoPorOfDocumentoCollectionNewDocumento = em.merge(oldCreadoPorOfDocumentoCollectionNewDocumento);
                    }
                }
            }
            for (Documento documentoCollection1OldDocumento : documentoCollection1Old) {
                if (!documentoCollection1New.contains(documentoCollection1OldDocumento)) {
                    documentoCollection1OldDocumento.setModificadoPor(null);
                    documentoCollection1OldDocumento = em.merge(documentoCollection1OldDocumento);
                }
            }
            for (Documento documentoCollection1NewDocumento : documentoCollection1New) {
                if (!documentoCollection1Old.contains(documentoCollection1NewDocumento)) {
                    Usuario oldModificadoPorOfDocumentoCollection1NewDocumento = documentoCollection1NewDocumento.getModificadoPor();
                    documentoCollection1NewDocumento.setModificadoPor(usuario);
                    documentoCollection1NewDocumento = em.merge(documentoCollection1NewDocumento);
                    if (oldModificadoPorOfDocumentoCollection1NewDocumento != null && !oldModificadoPorOfDocumentoCollection1NewDocumento.equals(usuario)) {
                        oldModificadoPorOfDocumentoCollection1NewDocumento.getDocumentoCollection1().remove(documentoCollection1NewDocumento);
                        oldModificadoPorOfDocumentoCollection1NewDocumento = em.merge(oldModificadoPorOfDocumentoCollection1NewDocumento);
                    }
                }
            }
            for (Acl aclCollectionOldAcl : aclCollectionOld) {
                if (!aclCollectionNew.contains(aclCollectionOldAcl)) {
                    aclCollectionOldAcl.setCreadoPor(null);
                    aclCollectionOldAcl = em.merge(aclCollectionOldAcl);
                }
            }
            for (Acl aclCollectionNewAcl : aclCollectionNew) {
                if (!aclCollectionOld.contains(aclCollectionNewAcl)) {
                    Usuario oldCreadoPorOfAclCollectionNewAcl = aclCollectionNewAcl.getCreadoPor();
                    aclCollectionNewAcl.setCreadoPor(usuario);
                    aclCollectionNewAcl = em.merge(aclCollectionNewAcl);
                    if (oldCreadoPorOfAclCollectionNewAcl != null && !oldCreadoPorOfAclCollectionNewAcl.equals(usuario)) {
                        oldCreadoPorOfAclCollectionNewAcl.getAclCollection().remove(aclCollectionNewAcl);
                        oldCreadoPorOfAclCollectionNewAcl = em.merge(oldCreadoPorOfAclCollectionNewAcl);
                    }
                }
            }
            for (Acl aclCollection1OldAcl : aclCollection1Old) {
                if (!aclCollection1New.contains(aclCollection1OldAcl)) {
                    aclCollection1OldAcl.setModificadoPor(null);
                    aclCollection1OldAcl = em.merge(aclCollection1OldAcl);
                }
            }
            for (Acl aclCollection1NewAcl : aclCollection1New) {
                if (!aclCollection1Old.contains(aclCollection1NewAcl)) {
                    Usuario oldModificadoPorOfAclCollection1NewAcl = aclCollection1NewAcl.getModificadoPor();
                    aclCollection1NewAcl.setModificadoPor(usuario);
                    aclCollection1NewAcl = em.merge(aclCollection1NewAcl);
                    if (oldModificadoPorOfAclCollection1NewAcl != null && !oldModificadoPorOfAclCollection1NewAcl.equals(usuario)) {
                        oldModificadoPorOfAclCollection1NewAcl.getAclCollection1().remove(aclCollection1NewAcl);
                        oldModificadoPorOfAclCollection1NewAcl = em.merge(oldModificadoPorOfAclCollection1NewAcl);
                    }
                }
            }
            for (SeccionSubSeccion seccionSubSeccionCollectionOldSeccionSubSeccion : seccionSubSeccionCollectionOld) {
                if (!seccionSubSeccionCollectionNew.contains(seccionSubSeccionCollectionOldSeccionSubSeccion)) {
                    seccionSubSeccionCollectionOldSeccionSubSeccion.setCreadoPor(null);
                    seccionSubSeccionCollectionOldSeccionSubSeccion = em.merge(seccionSubSeccionCollectionOldSeccionSubSeccion);
                }
            }
            for (SeccionSubSeccion seccionSubSeccionCollectionNewSeccionSubSeccion : seccionSubSeccionCollectionNew) {
                if (!seccionSubSeccionCollectionOld.contains(seccionSubSeccionCollectionNewSeccionSubSeccion)) {
                    Usuario oldCreadoPorOfSeccionSubSeccionCollectionNewSeccionSubSeccion = seccionSubSeccionCollectionNewSeccionSubSeccion.getCreadoPor();
                    seccionSubSeccionCollectionNewSeccionSubSeccion.setCreadoPor(usuario);
                    seccionSubSeccionCollectionNewSeccionSubSeccion = em.merge(seccionSubSeccionCollectionNewSeccionSubSeccion);
                    if (oldCreadoPorOfSeccionSubSeccionCollectionNewSeccionSubSeccion != null && !oldCreadoPorOfSeccionSubSeccionCollectionNewSeccionSubSeccion.equals(usuario)) {
                        oldCreadoPorOfSeccionSubSeccionCollectionNewSeccionSubSeccion.getSeccionSubSeccionCollection().remove(seccionSubSeccionCollectionNewSeccionSubSeccion);
                        oldCreadoPorOfSeccionSubSeccionCollectionNewSeccionSubSeccion = em.merge(oldCreadoPorOfSeccionSubSeccionCollectionNewSeccionSubSeccion);
                    }
                }
            }
            for (SeccionSubSeccion seccionSubSeccionCollection1OldSeccionSubSeccion : seccionSubSeccionCollection1Old) {
                if (!seccionSubSeccionCollection1New.contains(seccionSubSeccionCollection1OldSeccionSubSeccion)) {
                    seccionSubSeccionCollection1OldSeccionSubSeccion.setModificadoPor(null);
                    seccionSubSeccionCollection1OldSeccionSubSeccion = em.merge(seccionSubSeccionCollection1OldSeccionSubSeccion);
                }
            }
            for (SeccionSubSeccion seccionSubSeccionCollection1NewSeccionSubSeccion : seccionSubSeccionCollection1New) {
                if (!seccionSubSeccionCollection1Old.contains(seccionSubSeccionCollection1NewSeccionSubSeccion)) {
                    Usuario oldModificadoPorOfSeccionSubSeccionCollection1NewSeccionSubSeccion = seccionSubSeccionCollection1NewSeccionSubSeccion.getModificadoPor();
                    seccionSubSeccionCollection1NewSeccionSubSeccion.setModificadoPor(usuario);
                    seccionSubSeccionCollection1NewSeccionSubSeccion = em.merge(seccionSubSeccionCollection1NewSeccionSubSeccion);
                    if (oldModificadoPorOfSeccionSubSeccionCollection1NewSeccionSubSeccion != null && !oldModificadoPorOfSeccionSubSeccionCollection1NewSeccionSubSeccion.equals(usuario)) {
                        oldModificadoPorOfSeccionSubSeccionCollection1NewSeccionSubSeccion.getSeccionSubSeccionCollection1().remove(seccionSubSeccionCollection1NewSeccionSubSeccion);
                        oldModificadoPorOfSeccionSubSeccionCollection1NewSeccionSubSeccion = em.merge(oldModificadoPorOfSeccionSubSeccionCollection1NewSeccionSubSeccion);
                    }
                }
            }
            for (Preferencias preferenciasCollectionOldPreferencias : preferenciasCollectionOld) {
                if (!preferenciasCollectionNew.contains(preferenciasCollectionOldPreferencias)) {
                    preferenciasCollectionOldPreferencias.setUsuario(null);
                    preferenciasCollectionOldPreferencias = em.merge(preferenciasCollectionOldPreferencias);
                }
            }
            for (Preferencias preferenciasCollectionNewPreferencias : preferenciasCollectionNew) {
                if (!preferenciasCollectionOld.contains(preferenciasCollectionNewPreferencias)) {
                    Usuario oldUsuarioOfPreferenciasCollectionNewPreferencias = preferenciasCollectionNewPreferencias.getUsuario();
                    preferenciasCollectionNewPreferencias.setUsuario(usuario);
                    preferenciasCollectionNewPreferencias = em.merge(preferenciasCollectionNewPreferencias);
                    if (oldUsuarioOfPreferenciasCollectionNewPreferencias != null && !oldUsuarioOfPreferenciasCollectionNewPreferencias.equals(usuario)) {
                        oldUsuarioOfPreferenciasCollectionNewPreferencias.getPreferenciasCollection().remove(preferenciasCollectionNewPreferencias);
                        oldUsuarioOfPreferenciasCollectionNewPreferencias = em.merge(oldUsuarioOfPreferenciasCollectionNewPreferencias);
                    }
                }
            }
            for (PlantillaDocumental plantillaDocumentalCollectionOldPlantillaDocumental : plantillaDocumentalCollectionOld) {
                if (!plantillaDocumentalCollectionNew.contains(plantillaDocumentalCollectionOldPlantillaDocumental)) {
                    plantillaDocumentalCollectionOldPlantillaDocumental.setCreadoPor(null);
                    plantillaDocumentalCollectionOldPlantillaDocumental = em.merge(plantillaDocumentalCollectionOldPlantillaDocumental);
                }
            }
            for (PlantillaDocumental plantillaDocumentalCollectionNewPlantillaDocumental : plantillaDocumentalCollectionNew) {
                if (!plantillaDocumentalCollectionOld.contains(plantillaDocumentalCollectionNewPlantillaDocumental)) {
                    Usuario oldCreadoPorOfPlantillaDocumentalCollectionNewPlantillaDocumental = plantillaDocumentalCollectionNewPlantillaDocumental.getCreadoPor();
                    plantillaDocumentalCollectionNewPlantillaDocumental.setCreadoPor(usuario);
                    plantillaDocumentalCollectionNewPlantillaDocumental = em.merge(plantillaDocumentalCollectionNewPlantillaDocumental);
                    if (oldCreadoPorOfPlantillaDocumentalCollectionNewPlantillaDocumental != null && !oldCreadoPorOfPlantillaDocumentalCollectionNewPlantillaDocumental.equals(usuario)) {
                        oldCreadoPorOfPlantillaDocumentalCollectionNewPlantillaDocumental.getPlantillaDocumentalCollection().remove(plantillaDocumentalCollectionNewPlantillaDocumental);
                        oldCreadoPorOfPlantillaDocumentalCollectionNewPlantillaDocumental = em.merge(oldCreadoPorOfPlantillaDocumentalCollectionNewPlantillaDocumental);
                    }
                }
            }
            for (PlantillaDocumental plantillaDocumentalCollection1OldPlantillaDocumental : plantillaDocumentalCollection1Old) {
                if (!plantillaDocumentalCollection1New.contains(plantillaDocumentalCollection1OldPlantillaDocumental)) {
                    plantillaDocumentalCollection1OldPlantillaDocumental.setModificadoPor(null);
                    plantillaDocumentalCollection1OldPlantillaDocumental = em.merge(plantillaDocumentalCollection1OldPlantillaDocumental);
                }
            }
            for (PlantillaDocumental plantillaDocumentalCollection1NewPlantillaDocumental : plantillaDocumentalCollection1New) {
                if (!plantillaDocumentalCollection1Old.contains(plantillaDocumentalCollection1NewPlantillaDocumental)) {
                    Usuario oldModificadoPorOfPlantillaDocumentalCollection1NewPlantillaDocumental = plantillaDocumentalCollection1NewPlantillaDocumental.getModificadoPor();
                    plantillaDocumentalCollection1NewPlantillaDocumental.setModificadoPor(usuario);
                    plantillaDocumentalCollection1NewPlantillaDocumental = em.merge(plantillaDocumentalCollection1NewPlantillaDocumental);
                    if (oldModificadoPorOfPlantillaDocumentalCollection1NewPlantillaDocumental != null && !oldModificadoPorOfPlantillaDocumentalCollection1NewPlantillaDocumental.equals(usuario)) {
                        oldModificadoPorOfPlantillaDocumentalCollection1NewPlantillaDocumental.getPlantillaDocumentalCollection1().remove(plantillaDocumentalCollection1NewPlantillaDocumental);
                        oldModificadoPorOfPlantillaDocumentalCollection1NewPlantillaDocumental = em.merge(oldModificadoPorOfPlantillaDocumentalCollection1NewPlantillaDocumental);
                    }
                }
            }
            for (Cargo cargoCollectionOldCargo : cargoCollectionOld) {
                if (!cargoCollectionNew.contains(cargoCollectionOldCargo)) {
                    cargoCollectionOldCargo.setCreadoPor(null);
                    cargoCollectionOldCargo = em.merge(cargoCollectionOldCargo);
                }
            }
            for (Cargo cargoCollectionNewCargo : cargoCollectionNew) {
                if (!cargoCollectionOld.contains(cargoCollectionNewCargo)) {
                    Usuario oldCreadoPorOfCargoCollectionNewCargo = cargoCollectionNewCargo.getCreadoPor();
                    cargoCollectionNewCargo.setCreadoPor(usuario);
                    cargoCollectionNewCargo = em.merge(cargoCollectionNewCargo);
                    if (oldCreadoPorOfCargoCollectionNewCargo != null && !oldCreadoPorOfCargoCollectionNewCargo.equals(usuario)) {
                        oldCreadoPorOfCargoCollectionNewCargo.getCargoCollection().remove(cargoCollectionNewCargo);
                        oldCreadoPorOfCargoCollectionNewCargo = em.merge(oldCreadoPorOfCargoCollectionNewCargo);
                    }
                }
            }
            for (Cargo cargoCollection1OldCargo : cargoCollection1Old) {
                if (!cargoCollection1New.contains(cargoCollection1OldCargo)) {
                    cargoCollection1OldCargo.setModificadoPor(null);
                    cargoCollection1OldCargo = em.merge(cargoCollection1OldCargo);
                }
            }
            for (Cargo cargoCollection1NewCargo : cargoCollection1New) {
                if (!cargoCollection1Old.contains(cargoCollection1NewCargo)) {
                    Usuario oldModificadoPorOfCargoCollection1NewCargo = cargoCollection1NewCargo.getModificadoPor();
                    cargoCollection1NewCargo.setModificadoPor(usuario);
                    cargoCollection1NewCargo = em.merge(cargoCollection1NewCargo);
                    if (oldModificadoPorOfCargoCollection1NewCargo != null && !oldModificadoPorOfCargoCollection1NewCargo.equals(usuario)) {
                        oldModificadoPorOfCargoCollection1NewCargo.getCargoCollection1().remove(cargoCollection1NewCargo);
                        oldModificadoPorOfCargoCollection1NewCargo = em.merge(oldModificadoPorOfCargoCollection1NewCargo);
                    }
                }
            }
            for (TipoDocumento tipoDocumentoCollectionOldTipoDocumento : tipoDocumentoCollectionOld) {
                if (!tipoDocumentoCollectionNew.contains(tipoDocumentoCollectionOldTipoDocumento)) {
                    tipoDocumentoCollectionOldTipoDocumento.setCreadoPor(null);
                    tipoDocumentoCollectionOldTipoDocumento = em.merge(tipoDocumentoCollectionOldTipoDocumento);
                }
            }
            for (TipoDocumento tipoDocumentoCollectionNewTipoDocumento : tipoDocumentoCollectionNew) {
                if (!tipoDocumentoCollectionOld.contains(tipoDocumentoCollectionNewTipoDocumento)) {
                    Usuario oldCreadoPorOfTipoDocumentoCollectionNewTipoDocumento = tipoDocumentoCollectionNewTipoDocumento.getCreadoPor();
                    tipoDocumentoCollectionNewTipoDocumento.setCreadoPor(usuario);
                    tipoDocumentoCollectionNewTipoDocumento = em.merge(tipoDocumentoCollectionNewTipoDocumento);
                    if (oldCreadoPorOfTipoDocumentoCollectionNewTipoDocumento != null && !oldCreadoPorOfTipoDocumentoCollectionNewTipoDocumento.equals(usuario)) {
                        oldCreadoPorOfTipoDocumentoCollectionNewTipoDocumento.getTipoDocumentoCollection().remove(tipoDocumentoCollectionNewTipoDocumento);
                        oldCreadoPorOfTipoDocumentoCollectionNewTipoDocumento = em.merge(oldCreadoPorOfTipoDocumentoCollectionNewTipoDocumento);
                    }
                }
            }
            for (TipoDocumento tipoDocumentoCollection1OldTipoDocumento : tipoDocumentoCollection1Old) {
                if (!tipoDocumentoCollection1New.contains(tipoDocumentoCollection1OldTipoDocumento)) {
                    tipoDocumentoCollection1OldTipoDocumento.setModificadoPor(null);
                    tipoDocumentoCollection1OldTipoDocumento = em.merge(tipoDocumentoCollection1OldTipoDocumento);
                }
            }
            for (TipoDocumento tipoDocumentoCollection1NewTipoDocumento : tipoDocumentoCollection1New) {
                if (!tipoDocumentoCollection1Old.contains(tipoDocumentoCollection1NewTipoDocumento)) {
                    Usuario oldModificadoPorOfTipoDocumentoCollection1NewTipoDocumento = tipoDocumentoCollection1NewTipoDocumento.getModificadoPor();
                    tipoDocumentoCollection1NewTipoDocumento.setModificadoPor(usuario);
                    tipoDocumentoCollection1NewTipoDocumento = em.merge(tipoDocumentoCollection1NewTipoDocumento);
                    if (oldModificadoPorOfTipoDocumentoCollection1NewTipoDocumento != null && !oldModificadoPorOfTipoDocumentoCollection1NewTipoDocumento.equals(usuario)) {
                        oldModificadoPorOfTipoDocumentoCollection1NewTipoDocumento.getTipoDocumentoCollection1().remove(tipoDocumentoCollection1NewTipoDocumento);
                        oldModificadoPorOfTipoDocumentoCollection1NewTipoDocumento = em.merge(oldModificadoPorOfTipoDocumentoCollection1NewTipoDocumento);
                    }
                }
            }
            for (SubSerie subSerieCollectionOldSubSerie : subSerieCollectionOld) {
                if (!subSerieCollectionNew.contains(subSerieCollectionOldSubSerie)) {
                    subSerieCollectionOldSubSerie.setCreadoPor(null);
                    subSerieCollectionOldSubSerie = em.merge(subSerieCollectionOldSubSerie);
                }
            }
            for (SubSerie subSerieCollectionNewSubSerie : subSerieCollectionNew) {
                if (!subSerieCollectionOld.contains(subSerieCollectionNewSubSerie)) {
                    Usuario oldCreadoPorOfSubSerieCollectionNewSubSerie = subSerieCollectionNewSubSerie.getCreadoPor();
                    subSerieCollectionNewSubSerie.setCreadoPor(usuario);
                    subSerieCollectionNewSubSerie = em.merge(subSerieCollectionNewSubSerie);
                    if (oldCreadoPorOfSubSerieCollectionNewSubSerie != null && !oldCreadoPorOfSubSerieCollectionNewSubSerie.equals(usuario)) {
                        oldCreadoPorOfSubSerieCollectionNewSubSerie.getSubSerieCollection().remove(subSerieCollectionNewSubSerie);
                        oldCreadoPorOfSubSerieCollectionNewSubSerie = em.merge(oldCreadoPorOfSubSerieCollectionNewSubSerie);
                    }
                }
            }
            for (SubSerie subSerieCollection1OldSubSerie : subSerieCollection1Old) {
                if (!subSerieCollection1New.contains(subSerieCollection1OldSubSerie)) {
                    subSerieCollection1OldSubSerie.setModificadoPor(null);
                    subSerieCollection1OldSubSerie = em.merge(subSerieCollection1OldSubSerie);
                }
            }
            for (SubSerie subSerieCollection1NewSubSerie : subSerieCollection1New) {
                if (!subSerieCollection1Old.contains(subSerieCollection1NewSubSerie)) {
                    Usuario oldModificadoPorOfSubSerieCollection1NewSubSerie = subSerieCollection1NewSubSerie.getModificadoPor();
                    subSerieCollection1NewSubSerie.setModificadoPor(usuario);
                    subSerieCollection1NewSubSerie = em.merge(subSerieCollection1NewSubSerie);
                    if (oldModificadoPorOfSubSerieCollection1NewSubSerie != null && !oldModificadoPorOfSubSerieCollection1NewSubSerie.equals(usuario)) {
                        oldModificadoPorOfSubSerieCollection1NewSubSerie.getSubSerieCollection1().remove(subSerieCollection1NewSubSerie);
                        oldModificadoPorOfSubSerieCollection1NewSubSerie = em.merge(oldModificadoPorOfSubSerieCollection1NewSubSerie);
                    }
                }
            }
            for (Municipio municipioCollectionOldMunicipio : municipioCollectionOld) {
                if (!municipioCollectionNew.contains(municipioCollectionOldMunicipio)) {
                    municipioCollectionOldMunicipio.setCreadoPor(null);
                    municipioCollectionOldMunicipio = em.merge(municipioCollectionOldMunicipio);
                }
            }
            for (Municipio municipioCollectionNewMunicipio : municipioCollectionNew) {
                if (!municipioCollectionOld.contains(municipioCollectionNewMunicipio)) {
                    Usuario oldCreadoPorOfMunicipioCollectionNewMunicipio = municipioCollectionNewMunicipio.getCreadoPor();
                    municipioCollectionNewMunicipio.setCreadoPor(usuario);
                    municipioCollectionNewMunicipio = em.merge(municipioCollectionNewMunicipio);
                    if (oldCreadoPorOfMunicipioCollectionNewMunicipio != null && !oldCreadoPorOfMunicipioCollectionNewMunicipio.equals(usuario)) {
                        oldCreadoPorOfMunicipioCollectionNewMunicipio.getMunicipioCollection().remove(municipioCollectionNewMunicipio);
                        oldCreadoPorOfMunicipioCollectionNewMunicipio = em.merge(oldCreadoPorOfMunicipioCollectionNewMunicipio);
                    }
                }
            }
            for (Municipio municipioCollection1OldMunicipio : municipioCollection1Old) {
                if (!municipioCollection1New.contains(municipioCollection1OldMunicipio)) {
                    municipioCollection1OldMunicipio.setModificadoPor(null);
                    municipioCollection1OldMunicipio = em.merge(municipioCollection1OldMunicipio);
                }
            }
            for (Municipio municipioCollection1NewMunicipio : municipioCollection1New) {
                if (!municipioCollection1Old.contains(municipioCollection1NewMunicipio)) {
                    Usuario oldModificadoPorOfMunicipioCollection1NewMunicipio = municipioCollection1NewMunicipio.getModificadoPor();
                    municipioCollection1NewMunicipio.setModificadoPor(usuario);
                    municipioCollection1NewMunicipio = em.merge(municipioCollection1NewMunicipio);
                    if (oldModificadoPorOfMunicipioCollection1NewMunicipio != null && !oldModificadoPorOfMunicipioCollection1NewMunicipio.equals(usuario)) {
                        oldModificadoPorOfMunicipioCollection1NewMunicipio.getMunicipioCollection1().remove(municipioCollection1NewMunicipio);
                        oldModificadoPorOfMunicipioCollection1NewMunicipio = em.merge(oldModificadoPorOfMunicipioCollection1NewMunicipio);
                    }
                }
            }
            for (ProcesoNegocio procesoNegocioCollectionOldProcesoNegocio : procesoNegocioCollectionOld) {
                if (!procesoNegocioCollectionNew.contains(procesoNegocioCollectionOldProcesoNegocio)) {
                    procesoNegocioCollectionOldProcesoNegocio.setCreadoPor(null);
                    procesoNegocioCollectionOldProcesoNegocio = em.merge(procesoNegocioCollectionOldProcesoNegocio);
                }
            }
            for (ProcesoNegocio procesoNegocioCollectionNewProcesoNegocio : procesoNegocioCollectionNew) {
                if (!procesoNegocioCollectionOld.contains(procesoNegocioCollectionNewProcesoNegocio)) {
                    Usuario oldCreadoPorOfProcesoNegocioCollectionNewProcesoNegocio = procesoNegocioCollectionNewProcesoNegocio.getCreadoPor();
                    procesoNegocioCollectionNewProcesoNegocio.setCreadoPor(usuario);
                    procesoNegocioCollectionNewProcesoNegocio = em.merge(procesoNegocioCollectionNewProcesoNegocio);
                    if (oldCreadoPorOfProcesoNegocioCollectionNewProcesoNegocio != null && !oldCreadoPorOfProcesoNegocioCollectionNewProcesoNegocio.equals(usuario)) {
                        oldCreadoPorOfProcesoNegocioCollectionNewProcesoNegocio.getProcesoNegocioCollection().remove(procesoNegocioCollectionNewProcesoNegocio);
                        oldCreadoPorOfProcesoNegocioCollectionNewProcesoNegocio = em.merge(oldCreadoPorOfProcesoNegocioCollectionNewProcesoNegocio);
                    }
                }
            }
            for (ProcesoNegocio procesoNegocioCollection1OldProcesoNegocio : procesoNegocioCollection1Old) {
                if (!procesoNegocioCollection1New.contains(procesoNegocioCollection1OldProcesoNegocio)) {
                    procesoNegocioCollection1OldProcesoNegocio.setModificadoPor(null);
                    procesoNegocioCollection1OldProcesoNegocio = em.merge(procesoNegocioCollection1OldProcesoNegocio);
                }
            }
            for (ProcesoNegocio procesoNegocioCollection1NewProcesoNegocio : procesoNegocioCollection1New) {
                if (!procesoNegocioCollection1Old.contains(procesoNegocioCollection1NewProcesoNegocio)) {
                    Usuario oldModificadoPorOfProcesoNegocioCollection1NewProcesoNegocio = procesoNegocioCollection1NewProcesoNegocio.getModificadoPor();
                    procesoNegocioCollection1NewProcesoNegocio.setModificadoPor(usuario);
                    procesoNegocioCollection1NewProcesoNegocio = em.merge(procesoNegocioCollection1NewProcesoNegocio);
                    if (oldModificadoPorOfProcesoNegocioCollection1NewProcesoNegocio != null && !oldModificadoPorOfProcesoNegocioCollection1NewProcesoNegocio.equals(usuario)) {
                        oldModificadoPorOfProcesoNegocioCollection1NewProcesoNegocio.getProcesoNegocioCollection1().remove(procesoNegocioCollection1NewProcesoNegocio);
                        oldModificadoPorOfProcesoNegocioCollection1NewProcesoNegocio = em.merge(oldModificadoPorOfProcesoNegocioCollection1NewProcesoNegocio);
                    }
                }
            }
            for (Corregimiento corregimientoCollectionOldCorregimiento : corregimientoCollectionOld) {
                if (!corregimientoCollectionNew.contains(corregimientoCollectionOldCorregimiento)) {
                    corregimientoCollectionOldCorregimiento.setCreadoPor(null);
                    corregimientoCollectionOldCorregimiento = em.merge(corregimientoCollectionOldCorregimiento);
                }
            }
            for (Corregimiento corregimientoCollectionNewCorregimiento : corregimientoCollectionNew) {
                if (!corregimientoCollectionOld.contains(corregimientoCollectionNewCorregimiento)) {
                    Usuario oldCreadoPorOfCorregimientoCollectionNewCorregimiento = corregimientoCollectionNewCorregimiento.getCreadoPor();
                    corregimientoCollectionNewCorregimiento.setCreadoPor(usuario);
                    corregimientoCollectionNewCorregimiento = em.merge(corregimientoCollectionNewCorregimiento);
                    if (oldCreadoPorOfCorregimientoCollectionNewCorregimiento != null && !oldCreadoPorOfCorregimientoCollectionNewCorregimiento.equals(usuario)) {
                        oldCreadoPorOfCorregimientoCollectionNewCorregimiento.getCorregimientoCollection().remove(corregimientoCollectionNewCorregimiento);
                        oldCreadoPorOfCorregimientoCollectionNewCorregimiento = em.merge(oldCreadoPorOfCorregimientoCollectionNewCorregimiento);
                    }
                }
            }
            for (Corregimiento corregimientoCollection1OldCorregimiento : corregimientoCollection1Old) {
                if (!corregimientoCollection1New.contains(corregimientoCollection1OldCorregimiento)) {
                    corregimientoCollection1OldCorregimiento.setModificadoPor(null);
                    corregimientoCollection1OldCorregimiento = em.merge(corregimientoCollection1OldCorregimiento);
                }
            }
            for (Corregimiento corregimientoCollection1NewCorregimiento : corregimientoCollection1New) {
                if (!corregimientoCollection1Old.contains(corregimientoCollection1NewCorregimiento)) {
                    Usuario oldModificadoPorOfCorregimientoCollection1NewCorregimiento = corregimientoCollection1NewCorregimiento.getModificadoPor();
                    corregimientoCollection1NewCorregimiento.setModificadoPor(usuario);
                    corregimientoCollection1NewCorregimiento = em.merge(corregimientoCollection1NewCorregimiento);
                    if (oldModificadoPorOfCorregimientoCollection1NewCorregimiento != null && !oldModificadoPorOfCorregimientoCollection1NewCorregimiento.equals(usuario)) {
                        oldModificadoPorOfCorregimientoCollection1NewCorregimiento.getCorregimientoCollection1().remove(corregimientoCollection1NewCorregimiento);
                        oldModificadoPorOfCorregimientoCollection1NewCorregimiento = em.merge(oldModificadoPorOfCorregimientoCollection1NewCorregimiento);
                    }
                }
            }
            for (GrupoUsuario grupoUsuarioCollectionOldGrupoUsuario : grupoUsuarioCollectionOld) {
                if (!grupoUsuarioCollectionNew.contains(grupoUsuarioCollectionOldGrupoUsuario)) {
                    grupoUsuarioCollectionOldGrupoUsuario.setCreadoPor(null);
                    grupoUsuarioCollectionOldGrupoUsuario = em.merge(grupoUsuarioCollectionOldGrupoUsuario);
                }
            }
            for (GrupoUsuario grupoUsuarioCollectionNewGrupoUsuario : grupoUsuarioCollectionNew) {
                if (!grupoUsuarioCollectionOld.contains(grupoUsuarioCollectionNewGrupoUsuario)) {
                    Usuario oldCreadoPorOfGrupoUsuarioCollectionNewGrupoUsuario = grupoUsuarioCollectionNewGrupoUsuario.getCreadoPor();
                    grupoUsuarioCollectionNewGrupoUsuario.setCreadoPor(usuario);
                    grupoUsuarioCollectionNewGrupoUsuario = em.merge(grupoUsuarioCollectionNewGrupoUsuario);
                    if (oldCreadoPorOfGrupoUsuarioCollectionNewGrupoUsuario != null && !oldCreadoPorOfGrupoUsuarioCollectionNewGrupoUsuario.equals(usuario)) {
                        oldCreadoPorOfGrupoUsuarioCollectionNewGrupoUsuario.getGrupoUsuarioCollection().remove(grupoUsuarioCollectionNewGrupoUsuario);
                        oldCreadoPorOfGrupoUsuarioCollectionNewGrupoUsuario = em.merge(oldCreadoPorOfGrupoUsuarioCollectionNewGrupoUsuario);
                    }
                }
            }
            for (GrupoUsuario grupoUsuarioCollection1OldGrupoUsuario : grupoUsuarioCollection1Old) {
                if (!grupoUsuarioCollection1New.contains(grupoUsuarioCollection1OldGrupoUsuario)) {
                    grupoUsuarioCollection1OldGrupoUsuario.setModificadoPor(null);
                    grupoUsuarioCollection1OldGrupoUsuario = em.merge(grupoUsuarioCollection1OldGrupoUsuario);
                }
            }
            for (GrupoUsuario grupoUsuarioCollection1NewGrupoUsuario : grupoUsuarioCollection1New) {
                if (!grupoUsuarioCollection1Old.contains(grupoUsuarioCollection1NewGrupoUsuario)) {
                    Usuario oldModificadoPorOfGrupoUsuarioCollection1NewGrupoUsuario = grupoUsuarioCollection1NewGrupoUsuario.getModificadoPor();
                    grupoUsuarioCollection1NewGrupoUsuario.setModificadoPor(usuario);
                    grupoUsuarioCollection1NewGrupoUsuario = em.merge(grupoUsuarioCollection1NewGrupoUsuario);
                    if (oldModificadoPorOfGrupoUsuarioCollection1NewGrupoUsuario != null && !oldModificadoPorOfGrupoUsuarioCollection1NewGrupoUsuario.equals(usuario)) {
                        oldModificadoPorOfGrupoUsuarioCollection1NewGrupoUsuario.getGrupoUsuarioCollection1().remove(grupoUsuarioCollection1NewGrupoUsuario);
                        oldModificadoPorOfGrupoUsuarioCollection1NewGrupoUsuario = em.merge(oldModificadoPorOfGrupoUsuarioCollection1NewGrupoUsuario);
                    }
                }
            }
            for (GrupoUsuario grupoUsuarioCollection2OldGrupoUsuario : grupoUsuarioCollection2Old) {
                if (!grupoUsuarioCollection2New.contains(grupoUsuarioCollection2OldGrupoUsuario)) {
                    grupoUsuarioCollection2OldGrupoUsuario.setUsuario(null);
                    grupoUsuarioCollection2OldGrupoUsuario = em.merge(grupoUsuarioCollection2OldGrupoUsuario);
                }
            }
            for (GrupoUsuario grupoUsuarioCollection2NewGrupoUsuario : grupoUsuarioCollection2New) {
                if (!grupoUsuarioCollection2Old.contains(grupoUsuarioCollection2NewGrupoUsuario)) {
                    Usuario oldUsuarioOfGrupoUsuarioCollection2NewGrupoUsuario = grupoUsuarioCollection2NewGrupoUsuario.getUsuario();
                    grupoUsuarioCollection2NewGrupoUsuario.setUsuario(usuario);
                    grupoUsuarioCollection2NewGrupoUsuario = em.merge(grupoUsuarioCollection2NewGrupoUsuario);
                    if (oldUsuarioOfGrupoUsuarioCollection2NewGrupoUsuario != null && !oldUsuarioOfGrupoUsuarioCollection2NewGrupoUsuario.equals(usuario)) {
                        oldUsuarioOfGrupoUsuarioCollection2NewGrupoUsuario.getGrupoUsuarioCollection2().remove(grupoUsuarioCollection2NewGrupoUsuario);
                        oldUsuarioOfGrupoUsuarioCollection2NewGrupoUsuario = em.merge(oldUsuarioOfGrupoUsuarioCollection2NewGrupoUsuario);
                    }
                }
            }
            for (DestinatariosDoc destinatariosDocCollectionNewDestinatariosDoc : destinatariosDocCollectionNew) {
                if (!destinatariosDocCollectionOld.contains(destinatariosDocCollectionNewDestinatariosDoc)) {
                    Usuario oldCreadoPorOfDestinatariosDocCollectionNewDestinatariosDoc = destinatariosDocCollectionNewDestinatariosDoc.getCreadoPor();
                    destinatariosDocCollectionNewDestinatariosDoc.setCreadoPor(usuario);
                    destinatariosDocCollectionNewDestinatariosDoc = em.merge(destinatariosDocCollectionNewDestinatariosDoc);
                    if (oldCreadoPorOfDestinatariosDocCollectionNewDestinatariosDoc != null && !oldCreadoPorOfDestinatariosDocCollectionNewDestinatariosDoc.equals(usuario)) {
                        oldCreadoPorOfDestinatariosDocCollectionNewDestinatariosDoc.getDestinatariosDocCollection().remove(destinatariosDocCollectionNewDestinatariosDoc);
                        oldCreadoPorOfDestinatariosDocCollectionNewDestinatariosDoc = em.merge(oldCreadoPorOfDestinatariosDocCollectionNewDestinatariosDoc);
                    }
                }
            }
            for (DestinatariosDoc destinatariosDocCollection1NewDestinatariosDoc : destinatariosDocCollection1New) {
                if (!destinatariosDocCollection1Old.contains(destinatariosDocCollection1NewDestinatariosDoc)) {
                    Usuario oldModificadoPorOfDestinatariosDocCollection1NewDestinatariosDoc = destinatariosDocCollection1NewDestinatariosDoc.getModificadoPor();
                    destinatariosDocCollection1NewDestinatariosDoc.setModificadoPor(usuario);
                    destinatariosDocCollection1NewDestinatariosDoc = em.merge(destinatariosDocCollection1NewDestinatariosDoc);
                    if (oldModificadoPorOfDestinatariosDocCollection1NewDestinatariosDoc != null && !oldModificadoPorOfDestinatariosDocCollection1NewDestinatariosDoc.equals(usuario)) {
                        oldModificadoPorOfDestinatariosDocCollection1NewDestinatariosDoc.getDestinatariosDocCollection1().remove(destinatariosDocCollection1NewDestinatariosDoc);
                        oldModificadoPorOfDestinatariosDocCollection1NewDestinatariosDoc = em.merge(oldModificadoPorOfDestinatariosDocCollection1NewDestinatariosDoc);
                    }
                }
            }
            for (DestinatariosDoc destinatariosDocCollection2NewDestinatariosDoc : destinatariosDocCollection2New) {
                if (!destinatariosDocCollection2Old.contains(destinatariosDocCollection2NewDestinatariosDoc)) {
                    Usuario oldDestinatarioIdOfDestinatariosDocCollection2NewDestinatariosDoc = destinatariosDocCollection2NewDestinatariosDoc.getDestinatarioId();
                    destinatariosDocCollection2NewDestinatariosDoc.setDestinatarioId(usuario);
                    destinatariosDocCollection2NewDestinatariosDoc = em.merge(destinatariosDocCollection2NewDestinatariosDoc);
                    if (oldDestinatarioIdOfDestinatariosDocCollection2NewDestinatariosDoc != null && !oldDestinatarioIdOfDestinatariosDocCollection2NewDestinatariosDoc.equals(usuario)) {
                        oldDestinatarioIdOfDestinatariosDocCollection2NewDestinatariosDoc.getDestinatariosDocCollection2().remove(destinatariosDocCollection2NewDestinatariosDoc);
                        oldDestinatarioIdOfDestinatariosDocCollection2NewDestinatariosDoc = em.merge(oldDestinatarioIdOfDestinatariosDocCollection2NewDestinatariosDoc);
                    }
                }
            }
            for (Autor autorCollectionOldAutor : autorCollectionOld) {
                if (!autorCollectionNew.contains(autorCollectionOldAutor)) {
                    autorCollectionOldAutor.setCreadoPor(null);
                    autorCollectionOldAutor = em.merge(autorCollectionOldAutor);
                }
            }
            for (Autor autorCollectionNewAutor : autorCollectionNew) {
                if (!autorCollectionOld.contains(autorCollectionNewAutor)) {
                    Usuario oldCreadoPorOfAutorCollectionNewAutor = autorCollectionNewAutor.getCreadoPor();
                    autorCollectionNewAutor.setCreadoPor(usuario);
                    autorCollectionNewAutor = em.merge(autorCollectionNewAutor);
                    if (oldCreadoPorOfAutorCollectionNewAutor != null && !oldCreadoPorOfAutorCollectionNewAutor.equals(usuario)) {
                        oldCreadoPorOfAutorCollectionNewAutor.getAutorCollection().remove(autorCollectionNewAutor);
                        oldCreadoPorOfAutorCollectionNewAutor = em.merge(oldCreadoPorOfAutorCollectionNewAutor);
                    }
                }
            }
            for (Autor autorCollection1OldAutor : autorCollection1Old) {
                if (!autorCollection1New.contains(autorCollection1OldAutor)) {
                    autorCollection1OldAutor.setModificadoPor(null);
                    autorCollection1OldAutor = em.merge(autorCollection1OldAutor);
                }
            }
            for (Autor autorCollection1NewAutor : autorCollection1New) {
                if (!autorCollection1Old.contains(autorCollection1NewAutor)) {
                    Usuario oldModificadoPorOfAutorCollection1NewAutor = autorCollection1NewAutor.getModificadoPor();
                    autorCollection1NewAutor.setModificadoPor(usuario);
                    autorCollection1NewAutor = em.merge(autorCollection1NewAutor);
                    if (oldModificadoPorOfAutorCollection1NewAutor != null && !oldModificadoPorOfAutorCollection1NewAutor.equals(usuario)) {
                        oldModificadoPorOfAutorCollection1NewAutor.getAutorCollection1().remove(autorCollection1NewAutor);
                        oldModificadoPorOfAutorCollection1NewAutor = em.merge(oldModificadoPorOfAutorCollection1NewAutor);
                    }
                }
            }
            for (Notificacion notificacionCollectionOldNotificacion : notificacionCollectionOld) {
                if (!notificacionCollectionNew.contains(notificacionCollectionOldNotificacion)) {
                    notificacionCollectionOldNotificacion.setResponsable(null);
                    notificacionCollectionOldNotificacion = em.merge(notificacionCollectionOldNotificacion);
                }
            }
            for (Notificacion notificacionCollectionNewNotificacion : notificacionCollectionNew) {
                if (!notificacionCollectionOld.contains(notificacionCollectionNewNotificacion)) {
                    Usuario oldResponsableOfNotificacionCollectionNewNotificacion = notificacionCollectionNewNotificacion.getResponsable();
                    notificacionCollectionNewNotificacion.setResponsable(usuario);
                    notificacionCollectionNewNotificacion = em.merge(notificacionCollectionNewNotificacion);
                    if (oldResponsableOfNotificacionCollectionNewNotificacion != null && !oldResponsableOfNotificacionCollectionNewNotificacion.equals(usuario)) {
                        oldResponsableOfNotificacionCollectionNewNotificacion.getNotificacionCollection().remove(notificacionCollectionNewNotificacion);
                        oldResponsableOfNotificacionCollectionNewNotificacion = em.merge(oldResponsableOfNotificacionCollectionNewNotificacion);
                    }
                }
            }
            for (Pais paisCollectionOldPais : paisCollectionOld) {
                if (!paisCollectionNew.contains(paisCollectionOldPais)) {
                    paisCollectionOldPais.setCreadoPor(null);
                    paisCollectionOldPais = em.merge(paisCollectionOldPais);
                }
            }
            for (Pais paisCollectionNewPais : paisCollectionNew) {
                if (!paisCollectionOld.contains(paisCollectionNewPais)) {
                    Usuario oldCreadoPorOfPaisCollectionNewPais = paisCollectionNewPais.getCreadoPor();
                    paisCollectionNewPais.setCreadoPor(usuario);
                    paisCollectionNewPais = em.merge(paisCollectionNewPais);
                    if (oldCreadoPorOfPaisCollectionNewPais != null && !oldCreadoPorOfPaisCollectionNewPais.equals(usuario)) {
                        oldCreadoPorOfPaisCollectionNewPais.getPaisCollection().remove(paisCollectionNewPais);
                        oldCreadoPorOfPaisCollectionNewPais = em.merge(oldCreadoPorOfPaisCollectionNewPais);
                    }
                }
            }
            for (Pais paisCollection1OldPais : paisCollection1Old) {
                if (!paisCollection1New.contains(paisCollection1OldPais)) {
                    paisCollection1OldPais.setModificadoPor(null);
                    paisCollection1OldPais = em.merge(paisCollection1OldPais);
                }
            }
            for (Pais paisCollection1NewPais : paisCollection1New) {
                if (!paisCollection1Old.contains(paisCollection1NewPais)) {
                    Usuario oldModificadoPorOfPaisCollection1NewPais = paisCollection1NewPais.getModificadoPor();
                    paisCollection1NewPais.setModificadoPor(usuario);
                    paisCollection1NewPais = em.merge(paisCollection1NewPais);
                    if (oldModificadoPorOfPaisCollection1NewPais != null && !oldModificadoPorOfPaisCollection1NewPais.equals(usuario)) {
                        oldModificadoPorOfPaisCollection1NewPais.getPaisCollection1().remove(paisCollection1NewPais);
                        oldModificadoPorOfPaisCollection1NewPais = em.merge(oldModificadoPorOfPaisCollection1NewPais);
                    }
                }
            }
            for (Consecutivo consecutivoCollectionOldConsecutivo : consecutivoCollectionOld) {
                if (!consecutivoCollectionNew.contains(consecutivoCollectionOldConsecutivo)) {
                    consecutivoCollectionOldConsecutivo.setCreadoPor(null);
                    consecutivoCollectionOldConsecutivo = em.merge(consecutivoCollectionOldConsecutivo);
                }
            }
            for (Consecutivo consecutivoCollectionNewConsecutivo : consecutivoCollectionNew) {
                if (!consecutivoCollectionOld.contains(consecutivoCollectionNewConsecutivo)) {
                    Usuario oldCreadoPorOfConsecutivoCollectionNewConsecutivo = consecutivoCollectionNewConsecutivo.getCreadoPor();
                    consecutivoCollectionNewConsecutivo.setCreadoPor(usuario);
                    consecutivoCollectionNewConsecutivo = em.merge(consecutivoCollectionNewConsecutivo);
                    if (oldCreadoPorOfConsecutivoCollectionNewConsecutivo != null && !oldCreadoPorOfConsecutivoCollectionNewConsecutivo.equals(usuario)) {
                        oldCreadoPorOfConsecutivoCollectionNewConsecutivo.getConsecutivoCollection().remove(consecutivoCollectionNewConsecutivo);
                        oldCreadoPorOfConsecutivoCollectionNewConsecutivo = em.merge(oldCreadoPorOfConsecutivoCollectionNewConsecutivo);
                    }
                }
            }
            for (Consecutivo consecutivoCollection1OldConsecutivo : consecutivoCollection1Old) {
                if (!consecutivoCollection1New.contains(consecutivoCollection1OldConsecutivo)) {
                    consecutivoCollection1OldConsecutivo.setModificadoPor(null);
                    consecutivoCollection1OldConsecutivo = em.merge(consecutivoCollection1OldConsecutivo);
                }
            }
            for (Consecutivo consecutivoCollection1NewConsecutivo : consecutivoCollection1New) {
                if (!consecutivoCollection1Old.contains(consecutivoCollection1NewConsecutivo)) {
                    Usuario oldModificadoPorOfConsecutivoCollection1NewConsecutivo = consecutivoCollection1NewConsecutivo.getModificadoPor();
                    consecutivoCollection1NewConsecutivo.setModificadoPor(usuario);
                    consecutivoCollection1NewConsecutivo = em.merge(consecutivoCollection1NewConsecutivo);
                    if (oldModificadoPorOfConsecutivoCollection1NewConsecutivo != null && !oldModificadoPorOfConsecutivoCollection1NewConsecutivo.equals(usuario)) {
                        oldModificadoPorOfConsecutivoCollection1NewConsecutivo.getConsecutivoCollection1().remove(consecutivoCollection1NewConsecutivo);
                        oldModificadoPorOfConsecutivoCollection1NewConsecutivo = em.merge(oldModificadoPorOfConsecutivoCollection1NewConsecutivo);
                    }
                }
            }
            for (SignaturaTopografica signaturaTopograficaCollectionOldSignaturaTopografica : signaturaTopograficaCollectionOld) {
                if (!signaturaTopograficaCollectionNew.contains(signaturaTopograficaCollectionOldSignaturaTopografica)) {
                    signaturaTopograficaCollectionOldSignaturaTopografica.setCreadoPor(null);
                    signaturaTopograficaCollectionOldSignaturaTopografica = em.merge(signaturaTopograficaCollectionOldSignaturaTopografica);
                }
            }
            for (SignaturaTopografica signaturaTopograficaCollectionNewSignaturaTopografica : signaturaTopograficaCollectionNew) {
                if (!signaturaTopograficaCollectionOld.contains(signaturaTopograficaCollectionNewSignaturaTopografica)) {
                    Usuario oldCreadoPorOfSignaturaTopograficaCollectionNewSignaturaTopografica = signaturaTopograficaCollectionNewSignaturaTopografica.getCreadoPor();
                    signaturaTopograficaCollectionNewSignaturaTopografica.setCreadoPor(usuario);
                    signaturaTopograficaCollectionNewSignaturaTopografica = em.merge(signaturaTopograficaCollectionNewSignaturaTopografica);
                    if (oldCreadoPorOfSignaturaTopograficaCollectionNewSignaturaTopografica != null && !oldCreadoPorOfSignaturaTopograficaCollectionNewSignaturaTopografica.equals(usuario)) {
                        oldCreadoPorOfSignaturaTopograficaCollectionNewSignaturaTopografica.getSignaturaTopograficaCollection().remove(signaturaTopograficaCollectionNewSignaturaTopografica);
                        oldCreadoPorOfSignaturaTopograficaCollectionNewSignaturaTopografica = em.merge(oldCreadoPorOfSignaturaTopograficaCollectionNewSignaturaTopografica);
                    }
                }
            }
            for (SignaturaTopografica signaturaTopograficaCollection1OldSignaturaTopografica : signaturaTopograficaCollection1Old) {
                if (!signaturaTopograficaCollection1New.contains(signaturaTopograficaCollection1OldSignaturaTopografica)) {
                    signaturaTopograficaCollection1OldSignaturaTopografica.setModificadoPor(null);
                    signaturaTopograficaCollection1OldSignaturaTopografica = em.merge(signaturaTopograficaCollection1OldSignaturaTopografica);
                }
            }
            for (SignaturaTopografica signaturaTopograficaCollection1NewSignaturaTopografica : signaturaTopograficaCollection1New) {
                if (!signaturaTopograficaCollection1Old.contains(signaturaTopograficaCollection1NewSignaturaTopografica)) {
                    Usuario oldModificadoPorOfSignaturaTopograficaCollection1NewSignaturaTopografica = signaturaTopograficaCollection1NewSignaturaTopografica.getModificadoPor();
                    signaturaTopograficaCollection1NewSignaturaTopografica.setModificadoPor(usuario);
                    signaturaTopograficaCollection1NewSignaturaTopografica = em.merge(signaturaTopograficaCollection1NewSignaturaTopografica);
                    if (oldModificadoPorOfSignaturaTopograficaCollection1NewSignaturaTopografica != null && !oldModificadoPorOfSignaturaTopograficaCollection1NewSignaturaTopografica.equals(usuario)) {
                        oldModificadoPorOfSignaturaTopograficaCollection1NewSignaturaTopografica.getSignaturaTopograficaCollection1().remove(signaturaTopograficaCollection1NewSignaturaTopografica);
                        oldModificadoPorOfSignaturaTopograficaCollection1NewSignaturaTopografica = em.merge(oldModificadoPorOfSignaturaTopograficaCollection1NewSignaturaTopografica);
                    }
                }
            }
            for (TipoDocumental tipoDocumentalCollectionOldTipoDocumental : tipoDocumentalCollectionOld) {
                if (!tipoDocumentalCollectionNew.contains(tipoDocumentalCollectionOldTipoDocumental)) {
                    tipoDocumentalCollectionOldTipoDocumental.setCreadoPor(null);
                    tipoDocumentalCollectionOldTipoDocumental = em.merge(tipoDocumentalCollectionOldTipoDocumental);
                }
            }
            for (TipoDocumental tipoDocumentalCollectionNewTipoDocumental : tipoDocumentalCollectionNew) {
                if (!tipoDocumentalCollectionOld.contains(tipoDocumentalCollectionNewTipoDocumental)) {
                    Usuario oldCreadoPorOfTipoDocumentalCollectionNewTipoDocumental = tipoDocumentalCollectionNewTipoDocumental.getCreadoPor();
                    tipoDocumentalCollectionNewTipoDocumental.setCreadoPor(usuario);
                    tipoDocumentalCollectionNewTipoDocumental = em.merge(tipoDocumentalCollectionNewTipoDocumental);
                    if (oldCreadoPorOfTipoDocumentalCollectionNewTipoDocumental != null && !oldCreadoPorOfTipoDocumentalCollectionNewTipoDocumental.equals(usuario)) {
                        oldCreadoPorOfTipoDocumentalCollectionNewTipoDocumental.getTipoDocumentalCollection().remove(tipoDocumentalCollectionNewTipoDocumental);
                        oldCreadoPorOfTipoDocumentalCollectionNewTipoDocumental = em.merge(oldCreadoPorOfTipoDocumentalCollectionNewTipoDocumental);
                    }
                }
            }
            for (TipoDocumental tipoDocumentalCollection1OldTipoDocumental : tipoDocumentalCollection1Old) {
                if (!tipoDocumentalCollection1New.contains(tipoDocumentalCollection1OldTipoDocumental)) {
                    tipoDocumentalCollection1OldTipoDocumental.setModificadoPor(null);
                    tipoDocumentalCollection1OldTipoDocumental = em.merge(tipoDocumentalCollection1OldTipoDocumental);
                }
            }
            for (TipoDocumental tipoDocumentalCollection1NewTipoDocumental : tipoDocumentalCollection1New) {
                if (!tipoDocumentalCollection1Old.contains(tipoDocumentalCollection1NewTipoDocumental)) {
                    Usuario oldModificadoPorOfTipoDocumentalCollection1NewTipoDocumental = tipoDocumentalCollection1NewTipoDocumental.getModificadoPor();
                    tipoDocumentalCollection1NewTipoDocumental.setModificadoPor(usuario);
                    tipoDocumentalCollection1NewTipoDocumental = em.merge(tipoDocumentalCollection1NewTipoDocumental);
                    if (oldModificadoPorOfTipoDocumentalCollection1NewTipoDocumental != null && !oldModificadoPorOfTipoDocumentalCollection1NewTipoDocumental.equals(usuario)) {
                        oldModificadoPorOfTipoDocumentalCollection1NewTipoDocumental.getTipoDocumentalCollection1().remove(tipoDocumentalCollection1NewTipoDocumental);
                        oldModificadoPorOfTipoDocumentalCollection1NewTipoDocumental = em.merge(oldModificadoPorOfTipoDocumentalCollection1NewTipoDocumental);
                    }
                }
            }
            for (Transportador transportadorCollectionOldTransportador : transportadorCollectionOld) {
                if (!transportadorCollectionNew.contains(transportadorCollectionOldTransportador)) {
                    transportadorCollectionOldTransportador.setCreadoPor(null);
                    transportadorCollectionOldTransportador = em.merge(transportadorCollectionOldTransportador);
                }
            }
            for (Transportador transportadorCollectionNewTransportador : transportadorCollectionNew) {
                if (!transportadorCollectionOld.contains(transportadorCollectionNewTransportador)) {
                    Usuario oldCreadoPorOfTransportadorCollectionNewTransportador = transportadorCollectionNewTransportador.getCreadoPor();
                    transportadorCollectionNewTransportador.setCreadoPor(usuario);
                    transportadorCollectionNewTransportador = em.merge(transportadorCollectionNewTransportador);
                    if (oldCreadoPorOfTransportadorCollectionNewTransportador != null && !oldCreadoPorOfTransportadorCollectionNewTransportador.equals(usuario)) {
                        oldCreadoPorOfTransportadorCollectionNewTransportador.getTransportadorCollection().remove(transportadorCollectionNewTransportador);
                        oldCreadoPorOfTransportadorCollectionNewTransportador = em.merge(oldCreadoPorOfTransportadorCollectionNewTransportador);
                    }
                }
            }
            for (Transportador transportadorCollection1OldTransportador : transportadorCollection1Old) {
                if (!transportadorCollection1New.contains(transportadorCollection1OldTransportador)) {
                    transportadorCollection1OldTransportador.setModificadoPor(null);
                    transportadorCollection1OldTransportador = em.merge(transportadorCollection1OldTransportador);
                }
            }
            for (Transportador transportadorCollection1NewTransportador : transportadorCollection1New) {
                if (!transportadorCollection1Old.contains(transportadorCollection1NewTransportador)) {
                    Usuario oldModificadoPorOfTransportadorCollection1NewTransportador = transportadorCollection1NewTransportador.getModificadoPor();
                    transportadorCollection1NewTransportador.setModificadoPor(usuario);
                    transportadorCollection1NewTransportador = em.merge(transportadorCollection1NewTransportador);
                    if (oldModificadoPorOfTransportadorCollection1NewTransportador != null && !oldModificadoPorOfTransportadorCollection1NewTransportador.equals(usuario)) {
                        oldModificadoPorOfTransportadorCollection1NewTransportador.getTransportadorCollection1().remove(transportadorCollection1NewTransportador);
                        oldModificadoPorOfTransportadorCollection1NewTransportador = em.merge(oldModificadoPorOfTransportadorCollection1NewTransportador);
                    }
                }
            }
            for (ProcesoDocumental procesodocumentalCollectionOldProcesoDocumental : procesodocumentalCollectionOld) {
                if (!procesodocumentalCollectionNew.contains(procesodocumentalCollectionOldProcesoDocumental)) {
                    procesodocumentalCollectionOldProcesoDocumental.setCreadoPor(null);
                    procesodocumentalCollectionOldProcesoDocumental = em.merge(procesodocumentalCollectionOldProcesoDocumental);
                }
            }
            for (ProcesoDocumental procesodocumentalCollectionNewProcesoDocumental : procesodocumentalCollectionNew) {
                if (!procesodocumentalCollectionOld.contains(procesodocumentalCollectionNewProcesoDocumental)) {
                    Usuario oldCreadoPorOfProcesodocumentalCollectionNewProcesoDocumental = procesodocumentalCollectionNewProcesoDocumental.getCreadoPor();
                    procesodocumentalCollectionNewProcesoDocumental.setCreadoPor(usuario);
                    procesodocumentalCollectionNewProcesoDocumental = em.merge(procesodocumentalCollectionNewProcesoDocumental);
                    if (oldCreadoPorOfProcesodocumentalCollectionNewProcesoDocumental != null && !oldCreadoPorOfProcesodocumentalCollectionNewProcesoDocumental.equals(usuario)) {
                        oldCreadoPorOfProcesodocumentalCollectionNewProcesoDocumental.getProcesodocumentalCollection().remove(procesodocumentalCollectionNewProcesoDocumental);
                        oldCreadoPorOfProcesodocumentalCollectionNewProcesoDocumental = em.merge(oldCreadoPorOfProcesodocumentalCollectionNewProcesoDocumental);
                    }
                }
            }
            for (ProcesoDocumental procesodocumentalCollection1OldProcesoDocumental : procesodocumentalCollection1Old) {
                if (!procesodocumentalCollection1New.contains(procesodocumentalCollection1OldProcesoDocumental)) {
                    procesodocumentalCollection1OldProcesoDocumental.setModificadoPor(null);
                    procesodocumentalCollection1OldProcesoDocumental = em.merge(procesodocumentalCollection1OldProcesoDocumental);
                }
            }
            for (ProcesoDocumental procesodocumentalCollection1NewProcesoDocumental : procesodocumentalCollection1New) {
                if (!procesodocumentalCollection1Old.contains(procesodocumentalCollection1NewProcesoDocumental)) {
                    Usuario oldModificadoPorOfProcesodocumentalCollection1NewProcesoDocumental = procesodocumentalCollection1NewProcesoDocumental.getModificadoPor();
                    procesodocumentalCollection1NewProcesoDocumental.setModificadoPor(usuario);
                    procesodocumentalCollection1NewProcesoDocumental = em.merge(procesodocumentalCollection1NewProcesoDocumental);
                    if (oldModificadoPorOfProcesodocumentalCollection1NewProcesoDocumental != null && !oldModificadoPorOfProcesodocumentalCollection1NewProcesoDocumental.equals(usuario)) {
                        oldModificadoPorOfProcesodocumentalCollection1NewProcesoDocumental.getProcesodocumentalCollection1().remove(procesodocumentalCollection1NewProcesoDocumental);
                        oldModificadoPorOfProcesodocumentalCollection1NewProcesoDocumental = em.merge(oldModificadoPorOfProcesodocumentalCollection1NewProcesoDocumental);
                    }
                }
            }
            for (ProcesoTipoDocumento procesoTipoDocumentoCollectionOldProcesoTipoDocumento : procesoTipoDocumentoCollectionOld) {
                if (!procesoTipoDocumentoCollectionNew.contains(procesoTipoDocumentoCollectionOldProcesoTipoDocumento)) {
                    procesoTipoDocumentoCollectionOldProcesoTipoDocumento.setCreadoPor(null);
                    procesoTipoDocumentoCollectionOldProcesoTipoDocumento = em.merge(procesoTipoDocumentoCollectionOldProcesoTipoDocumento);
                }
            }
            for (ProcesoTipoDocumento procesoTipoDocumentoCollectionNewProcesoTipoDocumento : procesoTipoDocumentoCollectionNew) {
                if (!procesoTipoDocumentoCollectionOld.contains(procesoTipoDocumentoCollectionNewProcesoTipoDocumento)) {
                    Usuario oldCreadoPorOfProcesoTipoDocumentoCollectionNewProcesoTipoDocumento = procesoTipoDocumentoCollectionNewProcesoTipoDocumento.getCreadoPor();
                    procesoTipoDocumentoCollectionNewProcesoTipoDocumento.setCreadoPor(usuario);
                    procesoTipoDocumentoCollectionNewProcesoTipoDocumento = em.merge(procesoTipoDocumentoCollectionNewProcesoTipoDocumento);
                    if (oldCreadoPorOfProcesoTipoDocumentoCollectionNewProcesoTipoDocumento != null && !oldCreadoPorOfProcesoTipoDocumentoCollectionNewProcesoTipoDocumento.equals(usuario)) {
                        oldCreadoPorOfProcesoTipoDocumentoCollectionNewProcesoTipoDocumento.getProcesoTipoDocumentoCollection().remove(procesoTipoDocumentoCollectionNewProcesoTipoDocumento);
                        oldCreadoPorOfProcesoTipoDocumentoCollectionNewProcesoTipoDocumento = em.merge(oldCreadoPorOfProcesoTipoDocumentoCollectionNewProcesoTipoDocumento);
                    }
                }
            }
            for (ProcesoTipoDocumento procesoTipoDocumentoCollection1OldProcesoTipoDocumento : procesoTipoDocumentoCollection1Old) {
                if (!procesoTipoDocumentoCollection1New.contains(procesoTipoDocumentoCollection1OldProcesoTipoDocumento)) {
                    procesoTipoDocumentoCollection1OldProcesoTipoDocumento.setModificadoPor(null);
                    procesoTipoDocumentoCollection1OldProcesoTipoDocumento = em.merge(procesoTipoDocumentoCollection1OldProcesoTipoDocumento);
                }
            }
            for (ProcesoTipoDocumento procesoTipoDocumentoCollection1NewProcesoTipoDocumento : procesoTipoDocumentoCollection1New) {
                if (!procesoTipoDocumentoCollection1Old.contains(procesoTipoDocumentoCollection1NewProcesoTipoDocumento)) {
                    Usuario oldModificadoPorOfProcesoTipoDocumentoCollection1NewProcesoTipoDocumento = procesoTipoDocumentoCollection1NewProcesoTipoDocumento.getModificadoPor();
                    procesoTipoDocumentoCollection1NewProcesoTipoDocumento.setModificadoPor(usuario);
                    procesoTipoDocumentoCollection1NewProcesoTipoDocumento = em.merge(procesoTipoDocumentoCollection1NewProcesoTipoDocumento);
                    if (oldModificadoPorOfProcesoTipoDocumentoCollection1NewProcesoTipoDocumento != null && !oldModificadoPorOfProcesoTipoDocumentoCollection1NewProcesoTipoDocumento.equals(usuario)) {
                        oldModificadoPorOfProcesoTipoDocumentoCollection1NewProcesoTipoDocumento.getProcesoTipoDocumentoCollection1().remove(procesoTipoDocumentoCollection1NewProcesoTipoDocumento);
                        oldModificadoPorOfProcesoTipoDocumentoCollection1NewProcesoTipoDocumento = em.merge(oldModificadoPorOfProcesoTipoDocumentoCollection1NewProcesoTipoDocumento);
                    }
                }
            }
            for (Entidad entidadCollectionOldEntidad : entidadCollectionOld) {
                if (!entidadCollectionNew.contains(entidadCollectionOldEntidad)) {
                    entidadCollectionOldEntidad.setCreadoPor(null);
                    entidadCollectionOldEntidad = em.merge(entidadCollectionOldEntidad);
                }
            }
            for (Entidad entidadCollectionNewEntidad : entidadCollectionNew) {
                if (!entidadCollectionOld.contains(entidadCollectionNewEntidad)) {
                    Usuario oldCreadoPorOfEntidadCollectionNewEntidad = entidadCollectionNewEntidad.getCreadoPor();
                    entidadCollectionNewEntidad.setCreadoPor(usuario);
                    entidadCollectionNewEntidad = em.merge(entidadCollectionNewEntidad);
                    if (oldCreadoPorOfEntidadCollectionNewEntidad != null && !oldCreadoPorOfEntidadCollectionNewEntidad.equals(usuario)) {
                        oldCreadoPorOfEntidadCollectionNewEntidad.getEntidadCollection().remove(entidadCollectionNewEntidad);
                        oldCreadoPorOfEntidadCollectionNewEntidad = em.merge(oldCreadoPorOfEntidadCollectionNewEntidad);
                    }
                }
            }
            for (Entidad entidadCollection1OldEntidad : entidadCollection1Old) {
                if (!entidadCollection1New.contains(entidadCollection1OldEntidad)) {
                    entidadCollection1OldEntidad.setModificadoPor(null);
                    entidadCollection1OldEntidad = em.merge(entidadCollection1OldEntidad);
                }
            }
            for (Entidad entidadCollection1NewEntidad : entidadCollection1New) {
                if (!entidadCollection1Old.contains(entidadCollection1NewEntidad)) {
                    Usuario oldModificadoPorOfEntidadCollection1NewEntidad = entidadCollection1NewEntidad.getModificadoPor();
                    entidadCollection1NewEntidad.setModificadoPor(usuario);
                    entidadCollection1NewEntidad = em.merge(entidadCollection1NewEntidad);
                    if (oldModificadoPorOfEntidadCollection1NewEntidad != null && !oldModificadoPorOfEntidadCollection1NewEntidad.equals(usuario)) {
                        oldModificadoPorOfEntidadCollection1NewEntidad.getEntidadCollection1().remove(entidadCollection1NewEntidad);
                        oldModificadoPorOfEntidadCollection1NewEntidad = em.merge(oldModificadoPorOfEntidadCollection1NewEntidad);
                    }
                }
            }
            for (UnidadDocumental unidadDocumentalCollectionOldUnidadDocumental : unidadDocumentalCollectionOld) {
                if (!unidadDocumentalCollectionNew.contains(unidadDocumentalCollectionOldUnidadDocumental)) {
                    unidadDocumentalCollectionOldUnidadDocumental.setCreadoPor(null);
                    unidadDocumentalCollectionOldUnidadDocumental = em.merge(unidadDocumentalCollectionOldUnidadDocumental);
                }
            }
            for (UnidadDocumental unidadDocumentalCollectionNewUnidadDocumental : unidadDocumentalCollectionNew) {
                if (!unidadDocumentalCollectionOld.contains(unidadDocumentalCollectionNewUnidadDocumental)) {
                    Usuario oldCreadoPorOfUnidadDocumentalCollectionNewUnidadDocumental = unidadDocumentalCollectionNewUnidadDocumental.getCreadoPor();
                    unidadDocumentalCollectionNewUnidadDocumental.setCreadoPor(usuario);
                    unidadDocumentalCollectionNewUnidadDocumental = em.merge(unidadDocumentalCollectionNewUnidadDocumental);
                    if (oldCreadoPorOfUnidadDocumentalCollectionNewUnidadDocumental != null && !oldCreadoPorOfUnidadDocumentalCollectionNewUnidadDocumental.equals(usuario)) {
                        oldCreadoPorOfUnidadDocumentalCollectionNewUnidadDocumental.getUnidadDocumentalCollection().remove(unidadDocumentalCollectionNewUnidadDocumental);
                        oldCreadoPorOfUnidadDocumentalCollectionNewUnidadDocumental = em.merge(oldCreadoPorOfUnidadDocumentalCollectionNewUnidadDocumental);
                    }
                }
            }
            for (UnidadDocumental unidadDocumentalCollection1OldUnidadDocumental : unidadDocumentalCollection1Old) {
                if (!unidadDocumentalCollection1New.contains(unidadDocumentalCollection1OldUnidadDocumental)) {
                    unidadDocumentalCollection1OldUnidadDocumental.setModificadoPor(null);
                    unidadDocumentalCollection1OldUnidadDocumental = em.merge(unidadDocumentalCollection1OldUnidadDocumental);
                }
            }
            for (UnidadDocumental unidadDocumentalCollection1NewUnidadDocumental : unidadDocumentalCollection1New) {
                if (!unidadDocumentalCollection1Old.contains(unidadDocumentalCollection1NewUnidadDocumental)) {
                    Usuario oldModificadoPorOfUnidadDocumentalCollection1NewUnidadDocumental = unidadDocumentalCollection1NewUnidadDocumental.getModificadoPor();
                    unidadDocumentalCollection1NewUnidadDocumental.setModificadoPor(usuario);
                    unidadDocumentalCollection1NewUnidadDocumental = em.merge(unidadDocumentalCollection1NewUnidadDocumental);
                    if (oldModificadoPorOfUnidadDocumentalCollection1NewUnidadDocumental != null && !oldModificadoPorOfUnidadDocumentalCollection1NewUnidadDocumental.equals(usuario)) {
                        oldModificadoPorOfUnidadDocumentalCollection1NewUnidadDocumental.getUnidadDocumentalCollection1().remove(unidadDocumentalCollection1NewUnidadDocumental);
                        oldModificadoPorOfUnidadDocumentalCollection1NewUnidadDocumental = em.merge(oldModificadoPorOfUnidadDocumentalCollection1NewUnidadDocumental);
                    }
                }
            }
            for (Serie serieCollectionOldSerie : serieCollectionOld) {
                if (!serieCollectionNew.contains(serieCollectionOldSerie)) {
                    serieCollectionOldSerie.setCreadoPor(null);
                    serieCollectionOldSerie = em.merge(serieCollectionOldSerie);
                }
            }
            for (Serie serieCollectionNewSerie : serieCollectionNew) {
                if (!serieCollectionOld.contains(serieCollectionNewSerie)) {
                    Usuario oldCreadoPorOfSerieCollectionNewSerie = serieCollectionNewSerie.getCreadoPor();
                    serieCollectionNewSerie.setCreadoPor(usuario);
                    serieCollectionNewSerie = em.merge(serieCollectionNewSerie);
                    if (oldCreadoPorOfSerieCollectionNewSerie != null && !oldCreadoPorOfSerieCollectionNewSerie.equals(usuario)) {
                        oldCreadoPorOfSerieCollectionNewSerie.getSerieCollection().remove(serieCollectionNewSerie);
                        oldCreadoPorOfSerieCollectionNewSerie = em.merge(oldCreadoPorOfSerieCollectionNewSerie);
                    }
                }
            }
            for (Serie serieCollection1OldSerie : serieCollection1Old) {
                if (!serieCollection1New.contains(serieCollection1OldSerie)) {
                    serieCollection1OldSerie.setModificadoPor(null);
                    serieCollection1OldSerie = em.merge(serieCollection1OldSerie);
                }
            }
            for (Serie serieCollection1NewSerie : serieCollection1New) {
                if (!serieCollection1Old.contains(serieCollection1NewSerie)) {
                    Usuario oldModificadoPorOfSerieCollection1NewSerie = serieCollection1NewSerie.getModificadoPor();
                    serieCollection1NewSerie.setModificadoPor(usuario);
                    serieCollection1NewSerie = em.merge(serieCollection1NewSerie);
                    if (oldModificadoPorOfSerieCollection1NewSerie != null && !oldModificadoPorOfSerieCollection1NewSerie.equals(usuario)) {
                        oldModificadoPorOfSerieCollection1NewSerie.getSerieCollection1().remove(serieCollection1NewSerie);
                        oldModificadoPorOfSerieCollection1NewSerie = em.merge(oldModificadoPorOfSerieCollection1NewSerie);
                    }
                }
            }
            for (Departamento departamentoCollectionOldDepartamento : departamentoCollectionOld) {
                if (!departamentoCollectionNew.contains(departamentoCollectionOldDepartamento)) {
                    departamentoCollectionOldDepartamento.setCreadoPor(null);
                    departamentoCollectionOldDepartamento = em.merge(departamentoCollectionOldDepartamento);
                }
            }
            for (Departamento departamentoCollectionNewDepartamento : departamentoCollectionNew) {
                if (!departamentoCollectionOld.contains(departamentoCollectionNewDepartamento)) {
                    Usuario oldCreadoPorOfDepartamentoCollectionNewDepartamento = departamentoCollectionNewDepartamento.getCreadoPor();
                    departamentoCollectionNewDepartamento.setCreadoPor(usuario);
                    departamentoCollectionNewDepartamento = em.merge(departamentoCollectionNewDepartamento);
                    if (oldCreadoPorOfDepartamentoCollectionNewDepartamento != null && !oldCreadoPorOfDepartamentoCollectionNewDepartamento.equals(usuario)) {
                        oldCreadoPorOfDepartamentoCollectionNewDepartamento.getDepartamentoCollection().remove(departamentoCollectionNewDepartamento);
                        oldCreadoPorOfDepartamentoCollectionNewDepartamento = em.merge(oldCreadoPorOfDepartamentoCollectionNewDepartamento);
                    }
                }
            }
            for (Departamento departamentoCollection1OldDepartamento : departamentoCollection1Old) {
                if (!departamentoCollection1New.contains(departamentoCollection1OldDepartamento)) {
                    departamentoCollection1OldDepartamento.setModificadoPor(null);
                    departamentoCollection1OldDepartamento = em.merge(departamentoCollection1OldDepartamento);
                }
            }
            for (Departamento departamentoCollection1NewDepartamento : departamentoCollection1New) {
                if (!departamentoCollection1Old.contains(departamentoCollection1NewDepartamento)) {
                    Usuario oldModificadoPorOfDepartamentoCollection1NewDepartamento = departamentoCollection1NewDepartamento.getModificadoPor();
                    departamentoCollection1NewDepartamento.setModificadoPor(usuario);
                    departamentoCollection1NewDepartamento = em.merge(departamentoCollection1NewDepartamento);
                    if (oldModificadoPorOfDepartamentoCollection1NewDepartamento != null && !oldModificadoPorOfDepartamentoCollection1NewDepartamento.equals(usuario)) {
                        oldModificadoPorOfDepartamentoCollection1NewDepartamento.getDepartamentoCollection1().remove(departamentoCollection1NewDepartamento);
                        oldModificadoPorOfDepartamentoCollection1NewDepartamento = em.merge(oldModificadoPorOfDepartamentoCollection1NewDepartamento);
                    }
                }
            }
            for (MonitoresProceso monitoresProcesoCollectionOldMonitoresProceso : monitoresProcesoCollectionOld) {
                if (!monitoresProcesoCollectionNew.contains(monitoresProcesoCollectionOldMonitoresProceso)) {
                    monitoresProcesoCollectionOldMonitoresProceso.setCreadoPor(null);
                    monitoresProcesoCollectionOldMonitoresProceso = em.merge(monitoresProcesoCollectionOldMonitoresProceso);
                }
            }
            for (MonitoresProceso monitoresProcesoCollectionNewMonitoresProceso : monitoresProcesoCollectionNew) {
                if (!monitoresProcesoCollectionOld.contains(monitoresProcesoCollectionNewMonitoresProceso)) {
                    Usuario oldCreadoPorOfMonitoresProcesoCollectionNewMonitoresProceso = monitoresProcesoCollectionNewMonitoresProceso.getCreadoPor();
                    monitoresProcesoCollectionNewMonitoresProceso.setCreadoPor(usuario);
                    monitoresProcesoCollectionNewMonitoresProceso = em.merge(monitoresProcesoCollectionNewMonitoresProceso);
                    if (oldCreadoPorOfMonitoresProcesoCollectionNewMonitoresProceso != null && !oldCreadoPorOfMonitoresProcesoCollectionNewMonitoresProceso.equals(usuario)) {
                        oldCreadoPorOfMonitoresProcesoCollectionNewMonitoresProceso.getMonitoresProcesoCollection().remove(monitoresProcesoCollectionNewMonitoresProceso);
                        oldCreadoPorOfMonitoresProcesoCollectionNewMonitoresProceso = em.merge(oldCreadoPorOfMonitoresProcesoCollectionNewMonitoresProceso);
                    }
                }
            }
            for (MonitoresProceso monitoresProcesoCollection1OldMonitoresProceso : monitoresProcesoCollection1Old) {
                if (!monitoresProcesoCollection1New.contains(monitoresProcesoCollection1OldMonitoresProceso)) {
                    monitoresProcesoCollection1OldMonitoresProceso.setModificadoPor(null);
                    monitoresProcesoCollection1OldMonitoresProceso = em.merge(monitoresProcesoCollection1OldMonitoresProceso);
                }
            }
            for (MonitoresProceso monitoresProcesoCollection1NewMonitoresProceso : monitoresProcesoCollection1New) {
                if (!monitoresProcesoCollection1Old.contains(monitoresProcesoCollection1NewMonitoresProceso)) {
                    Usuario oldModificadoPorOfMonitoresProcesoCollection1NewMonitoresProceso = monitoresProcesoCollection1NewMonitoresProceso.getModificadoPor();
                    monitoresProcesoCollection1NewMonitoresProceso.setModificadoPor(usuario);
                    monitoresProcesoCollection1NewMonitoresProceso = em.merge(monitoresProcesoCollection1NewMonitoresProceso);
                    if (oldModificadoPorOfMonitoresProcesoCollection1NewMonitoresProceso != null && !oldModificadoPorOfMonitoresProcesoCollection1NewMonitoresProceso.equals(usuario)) {
                        oldModificadoPorOfMonitoresProcesoCollection1NewMonitoresProceso.getMonitoresProcesoCollection1().remove(monitoresProcesoCollection1NewMonitoresProceso);
                        oldModificadoPorOfMonitoresProcesoCollection1NewMonitoresProceso = em.merge(oldModificadoPorOfMonitoresProcesoCollection1NewMonitoresProceso);
                    }
                }
            }
            for (Modulo moduloCollectionOldModulo : moduloCollectionOld) {
                if (!moduloCollectionNew.contains(moduloCollectionOldModulo)) {
                    moduloCollectionOldModulo.setCreadoPor(null);
                    moduloCollectionOldModulo = em.merge(moduloCollectionOldModulo);
                }
            }
            for (Modulo moduloCollectionNewModulo : moduloCollectionNew) {
                if (!moduloCollectionOld.contains(moduloCollectionNewModulo)) {
                    Usuario oldCreadoPorOfModuloCollectionNewModulo = moduloCollectionNewModulo.getCreadoPor();
                    moduloCollectionNewModulo.setCreadoPor(usuario);
                    moduloCollectionNewModulo = em.merge(moduloCollectionNewModulo);
                    if (oldCreadoPorOfModuloCollectionNewModulo != null && !oldCreadoPorOfModuloCollectionNewModulo.equals(usuario)) {
                        oldCreadoPorOfModuloCollectionNewModulo.getModuloCollection().remove(moduloCollectionNewModulo);
                        oldCreadoPorOfModuloCollectionNewModulo = em.merge(oldCreadoPorOfModuloCollectionNewModulo);
                    }
                }
            }
            for (Modulo moduloCollection1OldModulo : moduloCollection1Old) {
                if (!moduloCollection1New.contains(moduloCollection1OldModulo)) {
                    moduloCollection1OldModulo.setModificadoPor(null);
                    moduloCollection1OldModulo = em.merge(moduloCollection1OldModulo);
                }
            }
            for (Modulo moduloCollection1NewModulo : moduloCollection1New) {
                if (!moduloCollection1Old.contains(moduloCollection1NewModulo)) {
                    Usuario oldModificadoPorOfModuloCollection1NewModulo = moduloCollection1NewModulo.getModificadoPor();
                    moduloCollection1NewModulo.setModificadoPor(usuario);
                    moduloCollection1NewModulo = em.merge(moduloCollection1NewModulo);
                    if (oldModificadoPorOfModuloCollection1NewModulo != null && !oldModificadoPorOfModuloCollection1NewModulo.equals(usuario)) {
                        oldModificadoPorOfModuloCollection1NewModulo.getModuloCollection1().remove(moduloCollection1NewModulo);
                        oldModificadoPorOfModuloCollection1NewModulo = em.merge(oldModificadoPorOfModuloCollection1NewModulo);
                    }
                }
            }
            for (Campos camposCollectionNewCampos : camposCollectionNew) {
                if (!camposCollectionOld.contains(camposCollectionNewCampos)) {
                    Usuario oldCreadoPorOfCamposCollectionNewCampos = camposCollectionNewCampos.getCreadoPor();
                    camposCollectionNewCampos.setCreadoPor(usuario);
                    camposCollectionNewCampos = em.merge(camposCollectionNewCampos);
                    if (oldCreadoPorOfCamposCollectionNewCampos != null && !oldCreadoPorOfCamposCollectionNewCampos.equals(usuario)) {
                        oldCreadoPorOfCamposCollectionNewCampos.getCamposCollection().remove(camposCollectionNewCampos);
                        oldCreadoPorOfCamposCollectionNewCampos = em.merge(oldCreadoPorOfCamposCollectionNewCampos);
                    }
                }
            }
            for (Campos camposCollection1NewCampos : camposCollection1New) {
                if (!camposCollection1Old.contains(camposCollection1NewCampos)) {
                    Usuario oldModificadoPorOfCamposCollection1NewCampos = camposCollection1NewCampos.getModificadoPor();
                    camposCollection1NewCampos.setModificadoPor(usuario);
                    camposCollection1NewCampos = em.merge(camposCollection1NewCampos);
                    if (oldModificadoPorOfCamposCollection1NewCampos != null && !oldModificadoPorOfCamposCollection1NewCampos.equals(usuario)) {
                        oldModificadoPorOfCamposCollection1NewCampos.getCamposCollection1().remove(camposCollection1NewCampos);
                        oldModificadoPorOfCamposCollection1NewCampos = em.merge(oldModificadoPorOfCamposCollection1NewCampos);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = usuario.getId();
                if (findUsuario(id) == null) {
                    throw new NonexistentEntityException("The usuario with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario usuario;
            try {
                usuario = em.getReference(Usuario.class, id);
                usuario.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The usuario with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<DestinatariosDoc> destinatariosDocCollectionOrphanCheck = usuario.getDestinatariosDocCollection();
            for (DestinatariosDoc destinatariosDocCollectionOrphanCheckDestinatariosDoc : destinatariosDocCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Usuario (" + usuario + ") cannot be destroyed since the DestinatariosDoc " + destinatariosDocCollectionOrphanCheckDestinatariosDoc + " in its destinatariosDocCollection field has a non-nullable creadoPor field.");
            }
            Collection<DestinatariosDoc> destinatariosDocCollection1OrphanCheck = usuario.getDestinatariosDocCollection1();
            for (DestinatariosDoc destinatariosDocCollection1OrphanCheckDestinatariosDoc : destinatariosDocCollection1OrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Usuario (" + usuario + ") cannot be destroyed since the DestinatariosDoc " + destinatariosDocCollection1OrphanCheckDestinatariosDoc + " in its destinatariosDocCollection1 field has a non-nullable modificadoPor field.");
            }
            Collection<DestinatariosDoc> destinatariosDocCollection2OrphanCheck = usuario.getDestinatariosDocCollection2();
            for (DestinatariosDoc destinatariosDocCollection2OrphanCheckDestinatariosDoc : destinatariosDocCollection2OrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Usuario (" + usuario + ") cannot be destroyed since the DestinatariosDoc " + destinatariosDocCollection2OrphanCheckDestinatariosDoc + " in its destinatariosDocCollection2 field has a non-nullable destinatarioId field.");
            }
            Collection<Campos> camposCollectionOrphanCheck = usuario.getCamposCollection();
            for (Campos camposCollectionOrphanCheckCampos : camposCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Usuario (" + usuario + ") cannot be destroyed since the Campos " + camposCollectionOrphanCheckCampos + " in its camposCollection field has a non-nullable creadoPor field.");
            }
            Collection<Campos> camposCollection1OrphanCheck = usuario.getCamposCollection1();
            for (Campos camposCollection1OrphanCheckCampos : camposCollection1OrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Usuario (" + usuario + ") cannot be destroyed since the Campos " + camposCollection1OrphanCheckCampos + " in its camposCollection1 field has a non-nullable modificadoPor field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Cargo cargo = usuario.getCargo();
            if (cargo != null) {
                cargo.setCreadoPor(null);
                cargo = em.merge(cargo);
            }
            Collection<ClaseDocumento> claseDocumentoCollection = usuario.getClaseDocumentoCollection();
            for (ClaseDocumento claseDocumentoCollectionClaseDocumento : claseDocumentoCollection) {
                claseDocumentoCollectionClaseDocumento.setCreadoPor(null);
                claseDocumentoCollectionClaseDocumento = em.merge(claseDocumentoCollectionClaseDocumento);
            }
            Collection<ClaseDocumento> claseDocumentoCollection1 = usuario.getClaseDocumentoCollection1();
            for (ClaseDocumento claseDocumentoCollection1ClaseDocumento : claseDocumentoCollection1) {
                claseDocumentoCollection1ClaseDocumento.setModificadoPor(null);
                claseDocumentoCollection1ClaseDocumento = em.merge(claseDocumentoCollection1ClaseDocumento);
            }
            Collection<Grupo> grupoCollection = usuario.getGrupoCollection();
            for (Grupo grupoCollectionGrupo : grupoCollection) {
                grupoCollectionGrupo.setModificadoPor(null);
                grupoCollectionGrupo = em.merge(grupoCollectionGrupo);
            }
            Collection<Documento> documentoCollection = usuario.getDocumentoCollection();
            for (Documento documentoCollectionDocumento : documentoCollection) {
                documentoCollectionDocumento.setCreadoPor(null);
                documentoCollectionDocumento = em.merge(documentoCollectionDocumento);
            }
            Collection<Documento> documentoCollection1 = usuario.getDocumentoCollection1();
            for (Documento documentoCollection1Documento : documentoCollection1) {
                documentoCollection1Documento.setModificadoPor(null);
                documentoCollection1Documento = em.merge(documentoCollection1Documento);
            }
            Collection<Acl> aclCollection = usuario.getAclCollection();
            for (Acl aclCollectionAcl : aclCollection) {
                aclCollectionAcl.setCreadoPor(null);
                aclCollectionAcl = em.merge(aclCollectionAcl);
            }
            Collection<Acl> aclCollection1 = usuario.getAclCollection1();
            for (Acl aclCollection1Acl : aclCollection1) {
                aclCollection1Acl.setModificadoPor(null);
                aclCollection1Acl = em.merge(aclCollection1Acl);
            }
            Collection<SeccionSubSeccion> seccionSubSeccionCollection = usuario.getSeccionSubSeccionCollection();
            for (SeccionSubSeccion seccionSubSeccionCollectionSeccionSubSeccion : seccionSubSeccionCollection) {
                seccionSubSeccionCollectionSeccionSubSeccion.setCreadoPor(null);
                seccionSubSeccionCollectionSeccionSubSeccion = em.merge(seccionSubSeccionCollectionSeccionSubSeccion);
            }
            Collection<SeccionSubSeccion> seccionSubSeccionCollection1 = usuario.getSeccionSubSeccionCollection1();
            for (SeccionSubSeccion seccionSubSeccionCollection1SeccionSubSeccion : seccionSubSeccionCollection1) {
                seccionSubSeccionCollection1SeccionSubSeccion.setModificadoPor(null);
                seccionSubSeccionCollection1SeccionSubSeccion = em.merge(seccionSubSeccionCollection1SeccionSubSeccion);
            }
            Collection<Preferencias> preferenciasCollection = usuario.getPreferenciasCollection();
            for (Preferencias preferenciasCollectionPreferencias : preferenciasCollection) {
                preferenciasCollectionPreferencias.setUsuario(null);
                preferenciasCollectionPreferencias = em.merge(preferenciasCollectionPreferencias);
            }
            Collection<PlantillaDocumental> plantillaDocumentalCollection = usuario.getPlantillaDocumentalCollection();
            for (PlantillaDocumental plantillaDocumentalCollectionPlantillaDocumental : plantillaDocumentalCollection) {
                plantillaDocumentalCollectionPlantillaDocumental.setCreadoPor(null);
                plantillaDocumentalCollectionPlantillaDocumental = em.merge(plantillaDocumentalCollectionPlantillaDocumental);
            }
            Collection<PlantillaDocumental> plantillaDocumentalCollection1 = usuario.getPlantillaDocumentalCollection1();
            for (PlantillaDocumental plantillaDocumentalCollection1PlantillaDocumental : plantillaDocumentalCollection1) {
                plantillaDocumentalCollection1PlantillaDocumental.setModificadoPor(null);
                plantillaDocumentalCollection1PlantillaDocumental = em.merge(plantillaDocumentalCollection1PlantillaDocumental);
            }
            Collection<Cargo> cargoCollection = usuario.getCargoCollection();
            for (Cargo cargoCollectionCargo : cargoCollection) {
                cargoCollectionCargo.setCreadoPor(null);
                cargoCollectionCargo = em.merge(cargoCollectionCargo);
            }
            Collection<Cargo> cargoCollection1 = usuario.getCargoCollection1();
            for (Cargo cargoCollection1Cargo : cargoCollection1) {
                cargoCollection1Cargo.setModificadoPor(null);
                cargoCollection1Cargo = em.merge(cargoCollection1Cargo);
            }
            Collection<TipoDocumento> tipoDocumentoCollection = usuario.getTipoDocumentoCollection();
            for (TipoDocumento tipoDocumentoCollectionTipoDocumento : tipoDocumentoCollection) {
                tipoDocumentoCollectionTipoDocumento.setCreadoPor(null);
                tipoDocumentoCollectionTipoDocumento = em.merge(tipoDocumentoCollectionTipoDocumento);
            }
            Collection<TipoDocumento> tipoDocumentoCollection1 = usuario.getTipoDocumentoCollection1();
            for (TipoDocumento tipoDocumentoCollection1TipoDocumento : tipoDocumentoCollection1) {
                tipoDocumentoCollection1TipoDocumento.setModificadoPor(null);
                tipoDocumentoCollection1TipoDocumento = em.merge(tipoDocumentoCollection1TipoDocumento);
            }
            Collection<SubSerie> subSerieCollection = usuario.getSubSerieCollection();
            for (SubSerie subSerieCollectionSubSerie : subSerieCollection) {
                subSerieCollectionSubSerie.setCreadoPor(null);
                subSerieCollectionSubSerie = em.merge(subSerieCollectionSubSerie);
            }
            Collection<SubSerie> subSerieCollection1 = usuario.getSubSerieCollection1();
            for (SubSerie subSerieCollection1SubSerie : subSerieCollection1) {
                subSerieCollection1SubSerie.setModificadoPor(null);
                subSerieCollection1SubSerie = em.merge(subSerieCollection1SubSerie);
            }
            Collection<Municipio> municipioCollection = usuario.getMunicipioCollection();
            for (Municipio municipioCollectionMunicipio : municipioCollection) {
                municipioCollectionMunicipio.setCreadoPor(null);
                municipioCollectionMunicipio = em.merge(municipioCollectionMunicipio);
            }
            Collection<Municipio> municipioCollection1 = usuario.getMunicipioCollection1();
            for (Municipio municipioCollection1Municipio : municipioCollection1) {
                municipioCollection1Municipio.setModificadoPor(null);
                municipioCollection1Municipio = em.merge(municipioCollection1Municipio);
            }
            Collection<ProcesoNegocio> procesoNegocioCollection = usuario.getProcesoNegocioCollection();
            for (ProcesoNegocio procesoNegocioCollectionProcesoNegocio : procesoNegocioCollection) {
                procesoNegocioCollectionProcesoNegocio.setCreadoPor(null);
                procesoNegocioCollectionProcesoNegocio = em.merge(procesoNegocioCollectionProcesoNegocio);
            }
            Collection<ProcesoNegocio> procesoNegocioCollection1 = usuario.getProcesoNegocioCollection1();
            for (ProcesoNegocio procesoNegocioCollection1ProcesoNegocio : procesoNegocioCollection1) {
                procesoNegocioCollection1ProcesoNegocio.setModificadoPor(null);
                procesoNegocioCollection1ProcesoNegocio = em.merge(procesoNegocioCollection1ProcesoNegocio);
            }
            Collection<Corregimiento> corregimientoCollection = usuario.getCorregimientoCollection();
            for (Corregimiento corregimientoCollectionCorregimiento : corregimientoCollection) {
                corregimientoCollectionCorregimiento.setCreadoPor(null);
                corregimientoCollectionCorregimiento = em.merge(corregimientoCollectionCorregimiento);
            }
            Collection<Corregimiento> corregimientoCollection1 = usuario.getCorregimientoCollection1();
            for (Corregimiento corregimientoCollection1Corregimiento : corregimientoCollection1) {
                corregimientoCollection1Corregimiento.setModificadoPor(null);
                corregimientoCollection1Corregimiento = em.merge(corregimientoCollection1Corregimiento);
            }
            Collection<GrupoUsuario> grupoUsuarioCollection = usuario.getGrupoUsuarioCollection();
            for (GrupoUsuario grupoUsuarioCollectionGrupoUsuario : grupoUsuarioCollection) {
                grupoUsuarioCollectionGrupoUsuario.setCreadoPor(null);
                grupoUsuarioCollectionGrupoUsuario = em.merge(grupoUsuarioCollectionGrupoUsuario);
            }
            Collection<GrupoUsuario> grupoUsuarioCollection1 = usuario.getGrupoUsuarioCollection1();
            for (GrupoUsuario grupoUsuarioCollection1GrupoUsuario : grupoUsuarioCollection1) {
                grupoUsuarioCollection1GrupoUsuario.setModificadoPor(null);
                grupoUsuarioCollection1GrupoUsuario = em.merge(grupoUsuarioCollection1GrupoUsuario);
            }
            Collection<GrupoUsuario> grupoUsuarioCollection2 = usuario.getGrupoUsuarioCollection2();
            for (GrupoUsuario grupoUsuarioCollection2GrupoUsuario : grupoUsuarioCollection2) {
                grupoUsuarioCollection2GrupoUsuario.setUsuario(null);
                grupoUsuarioCollection2GrupoUsuario = em.merge(grupoUsuarioCollection2GrupoUsuario);
            }
            Collection<Autor> autorCollection = usuario.getAutorCollection();
            for (Autor autorCollectionAutor : autorCollection) {
                autorCollectionAutor.setCreadoPor(null);
                autorCollectionAutor = em.merge(autorCollectionAutor);
            }
            Collection<Autor> autorCollection1 = usuario.getAutorCollection1();
            for (Autor autorCollection1Autor : autorCollection1) {
                autorCollection1Autor.setModificadoPor(null);
                autorCollection1Autor = em.merge(autorCollection1Autor);
            }
            Collection<Notificacion> notificacionCollection = usuario.getNotificacionCollection();
            for (Notificacion notificacionCollectionNotificacion : notificacionCollection) {
                notificacionCollectionNotificacion.setResponsable(null);
                notificacionCollectionNotificacion = em.merge(notificacionCollectionNotificacion);
            }
            Collection<Pais> paisCollection = usuario.getPaisCollection();
            for (Pais paisCollectionPais : paisCollection) {
                paisCollectionPais.setCreadoPor(null);
                paisCollectionPais = em.merge(paisCollectionPais);
            }
            Collection<Pais> paisCollection1 = usuario.getPaisCollection1();
            for (Pais paisCollection1Pais : paisCollection1) {
                paisCollection1Pais.setModificadoPor(null);
                paisCollection1Pais = em.merge(paisCollection1Pais);
            }
            Collection<Consecutivo> consecutivoCollection = usuario.getConsecutivoCollection();
            for (Consecutivo consecutivoCollectionConsecutivo : consecutivoCollection) {
                consecutivoCollectionConsecutivo.setCreadoPor(null);
                consecutivoCollectionConsecutivo = em.merge(consecutivoCollectionConsecutivo);
            }
            Collection<Consecutivo> consecutivoCollection1 = usuario.getConsecutivoCollection1();
            for (Consecutivo consecutivoCollection1Consecutivo : consecutivoCollection1) {
                consecutivoCollection1Consecutivo.setModificadoPor(null);
                consecutivoCollection1Consecutivo = em.merge(consecutivoCollection1Consecutivo);
            }
            Collection<SignaturaTopografica> signaturaTopograficaCollection = usuario.getSignaturaTopograficaCollection();
            for (SignaturaTopografica signaturaTopograficaCollectionSignaturaTopografica : signaturaTopograficaCollection) {
                signaturaTopograficaCollectionSignaturaTopografica.setCreadoPor(null);
                signaturaTopograficaCollectionSignaturaTopografica = em.merge(signaturaTopograficaCollectionSignaturaTopografica);
            }
            Collection<SignaturaTopografica> signaturaTopograficaCollection1 = usuario.getSignaturaTopograficaCollection1();
            for (SignaturaTopografica signaturaTopograficaCollection1SignaturaTopografica : signaturaTopograficaCollection1) {
                signaturaTopograficaCollection1SignaturaTopografica.setModificadoPor(null);
                signaturaTopograficaCollection1SignaturaTopografica = em.merge(signaturaTopograficaCollection1SignaturaTopografica);
            }
            Collection<TipoDocumental> tipoDocumentalCollection = usuario.getTipoDocumentalCollection();
            for (TipoDocumental tipoDocumentalCollectionTipoDocumental : tipoDocumentalCollection) {
                tipoDocumentalCollectionTipoDocumental.setCreadoPor(null);
                tipoDocumentalCollectionTipoDocumental = em.merge(tipoDocumentalCollectionTipoDocumental);
            }
            Collection<TipoDocumental> tipoDocumentalCollection1 = usuario.getTipoDocumentalCollection1();
            for (TipoDocumental tipoDocumentalCollection1TipoDocumental : tipoDocumentalCollection1) {
                tipoDocumentalCollection1TipoDocumental.setModificadoPor(null);
                tipoDocumentalCollection1TipoDocumental = em.merge(tipoDocumentalCollection1TipoDocumental);
            }
            Collection<Transportador> transportadorCollection = usuario.getTransportadorCollection();
            for (Transportador transportadorCollectionTransportador : transportadorCollection) {
                transportadorCollectionTransportador.setCreadoPor(null);
                transportadorCollectionTransportador = em.merge(transportadorCollectionTransportador);
            }
            Collection<Transportador> transportadorCollection1 = usuario.getTransportadorCollection1();
            for (Transportador transportadorCollection1Transportador : transportadorCollection1) {
                transportadorCollection1Transportador.setModificadoPor(null);
                transportadorCollection1Transportador = em.merge(transportadorCollection1Transportador);
            }
            Collection<ProcesoDocumental> procesodocumentalCollection = usuario.getProcesodocumentalCollection();
            for (ProcesoDocumental procesodocumentalCollectionProcesoDocumental : procesodocumentalCollection) {
                procesodocumentalCollectionProcesoDocumental.setCreadoPor(null);
                procesodocumentalCollectionProcesoDocumental = em.merge(procesodocumentalCollectionProcesoDocumental);
            }
            Collection<ProcesoDocumental> procesodocumentalCollection1 = usuario.getProcesodocumentalCollection1();
            for (ProcesoDocumental procesodocumentalCollection1ProcesoDocumental : procesodocumentalCollection1) {
                procesodocumentalCollection1ProcesoDocumental.setModificadoPor(null);
                procesodocumentalCollection1ProcesoDocumental = em.merge(procesodocumentalCollection1ProcesoDocumental);
            }
            Collection<ProcesoTipoDocumento> procesoTipoDocumentoCollection = usuario.getProcesoTipoDocumentoCollection();
            for (ProcesoTipoDocumento procesoTipoDocumentoCollectionProcesoTipoDocumento : procesoTipoDocumentoCollection) {
                procesoTipoDocumentoCollectionProcesoTipoDocumento.setCreadoPor(null);
                procesoTipoDocumentoCollectionProcesoTipoDocumento = em.merge(procesoTipoDocumentoCollectionProcesoTipoDocumento);
            }
            Collection<ProcesoTipoDocumento> procesoTipoDocumentoCollection1 = usuario.getProcesoTipoDocumentoCollection1();
            for (ProcesoTipoDocumento procesoTipoDocumentoCollection1ProcesoTipoDocumento : procesoTipoDocumentoCollection1) {
                procesoTipoDocumentoCollection1ProcesoTipoDocumento.setModificadoPor(null);
                procesoTipoDocumentoCollection1ProcesoTipoDocumento = em.merge(procesoTipoDocumentoCollection1ProcesoTipoDocumento);
            }
            Collection<Entidad> entidadCollection = usuario.getEntidadCollection();
            for (Entidad entidadCollectionEntidad : entidadCollection) {
                entidadCollectionEntidad.setCreadoPor(null);
                entidadCollectionEntidad = em.merge(entidadCollectionEntidad);
            }
            Collection<Entidad> entidadCollection1 = usuario.getEntidadCollection1();
            for (Entidad entidadCollection1Entidad : entidadCollection1) {
                entidadCollection1Entidad.setModificadoPor(null);
                entidadCollection1Entidad = em.merge(entidadCollection1Entidad);
            }
            Collection<UnidadDocumental> unidadDocumentalCollection = usuario.getUnidadDocumentalCollection();
            for (UnidadDocumental unidadDocumentalCollectionUnidadDocumental : unidadDocumentalCollection) {
                unidadDocumentalCollectionUnidadDocumental.setCreadoPor(null);
                unidadDocumentalCollectionUnidadDocumental = em.merge(unidadDocumentalCollectionUnidadDocumental);
            }
            Collection<UnidadDocumental> unidadDocumentalCollection1 = usuario.getUnidadDocumentalCollection1();
            for (UnidadDocumental unidadDocumentalCollection1UnidadDocumental : unidadDocumentalCollection1) {
                unidadDocumentalCollection1UnidadDocumental.setModificadoPor(null);
                unidadDocumentalCollection1UnidadDocumental = em.merge(unidadDocumentalCollection1UnidadDocumental);
            }
            Collection<Serie> serieCollection = usuario.getSerieCollection();
            for (Serie serieCollectionSerie : serieCollection) {
                serieCollectionSerie.setCreadoPor(null);
                serieCollectionSerie = em.merge(serieCollectionSerie);
            }
            Collection<Serie> serieCollection1 = usuario.getSerieCollection1();
            for (Serie serieCollection1Serie : serieCollection1) {
                serieCollection1Serie.setModificadoPor(null);
                serieCollection1Serie = em.merge(serieCollection1Serie);
            }
            Collection<Departamento> departamentoCollection = usuario.getDepartamentoCollection();
            for (Departamento departamentoCollectionDepartamento : departamentoCollection) {
                departamentoCollectionDepartamento.setCreadoPor(null);
                departamentoCollectionDepartamento = em.merge(departamentoCollectionDepartamento);
            }
            Collection<Departamento> departamentoCollection1 = usuario.getDepartamentoCollection1();
            for (Departamento departamentoCollection1Departamento : departamentoCollection1) {
                departamentoCollection1Departamento.setModificadoPor(null);
                departamentoCollection1Departamento = em.merge(departamentoCollection1Departamento);
            }
            Collection<MonitoresProceso> monitoresProcesoCollection = usuario.getMonitoresProcesoCollection();
            for (MonitoresProceso monitoresProcesoCollectionMonitoresProceso : monitoresProcesoCollection) {
                monitoresProcesoCollectionMonitoresProceso.setCreadoPor(null);
                monitoresProcesoCollectionMonitoresProceso = em.merge(monitoresProcesoCollectionMonitoresProceso);
            }
            Collection<MonitoresProceso> monitoresProcesoCollection1 = usuario.getMonitoresProcesoCollection1();
            for (MonitoresProceso monitoresProcesoCollection1MonitoresProceso : monitoresProcesoCollection1) {
                monitoresProcesoCollection1MonitoresProceso.setModificadoPor(null);
                monitoresProcesoCollection1MonitoresProceso = em.merge(monitoresProcesoCollection1MonitoresProceso);
            }
            Collection<Modulo> moduloCollection = usuario.getModuloCollection();
            for (Modulo moduloCollectionModulo : moduloCollection) {
                moduloCollectionModulo.setCreadoPor(null);
                moduloCollectionModulo = em.merge(moduloCollectionModulo);
            }
            Collection<Modulo> moduloCollection1 = usuario.getModuloCollection1();
            for (Modulo moduloCollection1Modulo : moduloCollection1) {
                moduloCollection1Modulo.setModificadoPor(null);
                moduloCollection1Modulo = em.merge(moduloCollection1Modulo);
            }
            em.remove(usuario);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Usuario> findUsuarioEntities() {
        return findUsuarioEntities(true, -1, -1);
    }

    public List<Usuario> findUsuarioEntities(int maxResults, int firstResult) {
        return findUsuarioEntities(false, maxResults, firstResult);
    }

    private List<Usuario> findUsuarioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Usuario.class));
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

    public Usuario findUsuario(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Usuario.class, id);
        } finally {
            em.close();
        }
    }

    public int getUsuarioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Usuario> rt = cq.from(Usuario.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
