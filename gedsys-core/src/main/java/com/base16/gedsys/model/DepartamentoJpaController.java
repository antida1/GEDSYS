/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base16.gedsys.model;

import com.base16.gedsys.entities.Departamento;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.base16.gedsys.entities.Usuario;
import com.base16.gedsys.entities.Pais;
import com.base16.gedsys.entities.Municipio;
import com.base16.gedsys.model.exceptions.NonexistentEntityException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author rober
 */
public class DepartamentoJpaController implements Serializable {

    public DepartamentoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Departamento departamento) {
        if (departamento.getMunicipioCollection() == null) {
            departamento.setMunicipioCollection(new ArrayList<Municipio>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario creadoPor = departamento.getCreadoPor();
            if (creadoPor != null) {
                creadoPor = em.getReference(creadoPor.getClass(), creadoPor.getId());
                departamento.setCreadoPor(creadoPor);
            }
            Usuario modificadoPor = departamento.getModificadoPor();
            if (modificadoPor != null) {
                modificadoPor = em.getReference(modificadoPor.getClass(), modificadoPor.getId());
                departamento.setModificadoPor(modificadoPor);
            }
            Pais pais = departamento.getPais();
            if (pais != null) {
                pais = em.getReference(pais.getClass(), pais.getId());
                departamento.setPais(pais);
            }
            Collection<Municipio> attachedMunicipioCollection = new ArrayList<Municipio>();
            for (Municipio municipioCollectionMunicipioToAttach : departamento.getMunicipioCollection()) {
                municipioCollectionMunicipioToAttach = em.getReference(municipioCollectionMunicipioToAttach.getClass(), municipioCollectionMunicipioToAttach.getId());
                attachedMunicipioCollection.add(municipioCollectionMunicipioToAttach);
            }
            departamento.setMunicipioCollection(attachedMunicipioCollection);
            em.persist(departamento);
            if (creadoPor != null) {
                creadoPor.getDepartamentoCollection().add(departamento);
                creadoPor = em.merge(creadoPor);
            }
            if (modificadoPor != null) {
                modificadoPor.getDepartamentoCollection().add(departamento);
                modificadoPor = em.merge(modificadoPor);
            }
            if (pais != null) {
                pais.getDepartamentoCollection().add(departamento);
                pais = em.merge(pais);
            }
            for (Municipio municipioCollectionMunicipio : departamento.getMunicipioCollection()) {
                Departamento oldDepartamentoOfMunicipioCollectionMunicipio = municipioCollectionMunicipio.getDepartamento();
                municipioCollectionMunicipio.setDepartamento(departamento);
                municipioCollectionMunicipio = em.merge(municipioCollectionMunicipio);
                if (oldDepartamentoOfMunicipioCollectionMunicipio != null) {
                    oldDepartamentoOfMunicipioCollectionMunicipio.getMunicipioCollection().remove(municipioCollectionMunicipio);
                    oldDepartamentoOfMunicipioCollectionMunicipio = em.merge(oldDepartamentoOfMunicipioCollectionMunicipio);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Departamento departamento) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Departamento persistentDepartamento = em.find(Departamento.class, departamento.getId());
            Usuario creadoPorOld = persistentDepartamento.getCreadoPor();
            Usuario creadoPorNew = departamento.getCreadoPor();
            Usuario modificadoPorOld = persistentDepartamento.getModificadoPor();
            Usuario modificadoPorNew = departamento.getModificadoPor();
            Pais paisOld = persistentDepartamento.getPais();
            Pais paisNew = departamento.getPais();
            Collection<Municipio> municipioCollectionOld = persistentDepartamento.getMunicipioCollection();
            Collection<Municipio> municipioCollectionNew = departamento.getMunicipioCollection();
            if (creadoPorNew != null) {
                creadoPorNew = em.getReference(creadoPorNew.getClass(), creadoPorNew.getId());
                departamento.setCreadoPor(creadoPorNew);
            }
            if (modificadoPorNew != null) {
                modificadoPorNew = em.getReference(modificadoPorNew.getClass(), modificadoPorNew.getId());
                departamento.setModificadoPor(modificadoPorNew);
            }
            if (paisNew != null) {
                paisNew = em.getReference(paisNew.getClass(), paisNew.getId());
                departamento.setPais(paisNew);
            }
            Collection<Municipio> attachedMunicipioCollectionNew = new ArrayList<Municipio>();
            for (Municipio municipioCollectionNewMunicipioToAttach : municipioCollectionNew) {
                municipioCollectionNewMunicipioToAttach = em.getReference(municipioCollectionNewMunicipioToAttach.getClass(), municipioCollectionNewMunicipioToAttach.getId());
                attachedMunicipioCollectionNew.add(municipioCollectionNewMunicipioToAttach);
            }
            municipioCollectionNew = attachedMunicipioCollectionNew;
            departamento.setMunicipioCollection(municipioCollectionNew);
            departamento = em.merge(departamento);
            if (creadoPorOld != null && !creadoPorOld.equals(creadoPorNew)) {
                creadoPorOld.getDepartamentoCollection().remove(departamento);
                creadoPorOld = em.merge(creadoPorOld);
            }
            if (creadoPorNew != null && !creadoPorNew.equals(creadoPorOld)) {
                creadoPorNew.getDepartamentoCollection().add(departamento);
                creadoPorNew = em.merge(creadoPorNew);
            }
            if (modificadoPorOld != null && !modificadoPorOld.equals(modificadoPorNew)) {
                modificadoPorOld.getDepartamentoCollection().remove(departamento);
                modificadoPorOld = em.merge(modificadoPorOld);
            }
            if (modificadoPorNew != null && !modificadoPorNew.equals(modificadoPorOld)) {
                modificadoPorNew.getDepartamentoCollection().add(departamento);
                modificadoPorNew = em.merge(modificadoPorNew);
            }
            if (paisOld != null && !paisOld.equals(paisNew)) {
                paisOld.getDepartamentoCollection().remove(departamento);
                paisOld = em.merge(paisOld);
            }
            if (paisNew != null && !paisNew.equals(paisOld)) {
                paisNew.getDepartamentoCollection().add(departamento);
                paisNew = em.merge(paisNew);
            }
            for (Municipio municipioCollectionOldMunicipio : municipioCollectionOld) {
                if (!municipioCollectionNew.contains(municipioCollectionOldMunicipio)) {
                    municipioCollectionOldMunicipio.setDepartamento(null);
                    municipioCollectionOldMunicipio = em.merge(municipioCollectionOldMunicipio);
                }
            }
            for (Municipio municipioCollectionNewMunicipio : municipioCollectionNew) {
                if (!municipioCollectionOld.contains(municipioCollectionNewMunicipio)) {
                    Departamento oldDepartamentoOfMunicipioCollectionNewMunicipio = municipioCollectionNewMunicipio.getDepartamento();
                    municipioCollectionNewMunicipio.setDepartamento(departamento);
                    municipioCollectionNewMunicipio = em.merge(municipioCollectionNewMunicipio);
                    if (oldDepartamentoOfMunicipioCollectionNewMunicipio != null && !oldDepartamentoOfMunicipioCollectionNewMunicipio.equals(departamento)) {
                        oldDepartamentoOfMunicipioCollectionNewMunicipio.getMunicipioCollection().remove(municipioCollectionNewMunicipio);
                        oldDepartamentoOfMunicipioCollectionNewMunicipio = em.merge(oldDepartamentoOfMunicipioCollectionNewMunicipio);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = departamento.getId();
                if (findDepartamento(id) == null) {
                    throw new NonexistentEntityException("The departamento with id " + id + " no longer exists.");
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
            Departamento departamento;
            try {
                departamento = em.getReference(Departamento.class, id);
                departamento.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The departamento with id " + id + " no longer exists.", enfe);
            }
            Usuario creadoPor = departamento.getCreadoPor();
            if (creadoPor != null) {
                creadoPor.getDepartamentoCollection().remove(departamento);
                creadoPor = em.merge(creadoPor);
            }
            Usuario modificadoPor = departamento.getModificadoPor();
            if (modificadoPor != null) {
                modificadoPor.getDepartamentoCollection().remove(departamento);
                modificadoPor = em.merge(modificadoPor);
            }
            Pais pais = departamento.getPais();
            if (pais != null) {
                pais.getDepartamentoCollection().remove(departamento);
                pais = em.merge(pais);
            }
            Collection<Municipio> municipioCollection = departamento.getMunicipioCollection();
            for (Municipio municipioCollectionMunicipio : municipioCollection) {
                municipioCollectionMunicipio.setDepartamento(null);
                municipioCollectionMunicipio = em.merge(municipioCollectionMunicipio);
            }
            em.remove(departamento);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Departamento> findDepartamentoEntities() {
        return findDepartamentoEntities(true, -1, -1);
    }

    public List<Departamento> findDepartamentoEntities(int maxResults, int firstResult) {
        return findDepartamentoEntities(false, maxResults, firstResult);
    }

    private List<Departamento> findDepartamentoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Departamento.class));
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

    public Departamento findDepartamento(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Departamento.class, id);
        } finally {
            em.close();
        }
    }

    public int getDepartamentoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Departamento> rt = cq.from(Departamento.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public List<Departamento> findDepartamentosByPais(Pais pais) {
        return findDepartamentosByPais(pais, -1, -1);
    }

    public List<Departamento> findDepartamentosByPais(Pais pais, int maxResults, int firstResult) {
        return findDepartamentosByPais(pais, true, maxResults, firstResult);
    }

    private List<Departamento> findDepartamentosByPais(Pais pais, boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Departamento.class));
            Query q = em.createNamedQuery("Departamentos.findByPais", Departamento.class)
                    .setParameter("pais", pais);
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
