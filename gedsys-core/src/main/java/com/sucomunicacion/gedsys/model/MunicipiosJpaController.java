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
import com.sucomunicacion.gedsys.entities.Departamentos;
import com.sucomunicacion.gedsys.entities.Corregimientos;
import com.sucomunicacion.gedsys.entities.Municipios;
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
public class MunicipiosJpaController implements Serializable {

    public MunicipiosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Municipios municipios) throws PreexistingEntityException, Exception {
        if (municipios.getCorregimientosList() == null) {
            municipios.setCorregimientosList(new ArrayList<Corregimientos>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Departamentos departamento = municipios.getDepartamento();
            if (departamento != null) {
                departamento = em.getReference(departamento.getClass(), departamento.getId());
                municipios.setDepartamento(departamento);
            }
            List<Corregimientos> attachedCorregimientosList = new ArrayList<Corregimientos>();
            for (Corregimientos corregimientosListCorregimientosToAttach : municipios.getCorregimientosList()) {
                corregimientosListCorregimientosToAttach = em.getReference(corregimientosListCorregimientosToAttach.getClass(), corregimientosListCorregimientosToAttach.getId());
                attachedCorregimientosList.add(corregimientosListCorregimientosToAttach);
            }
            municipios.setCorregimientosList(attachedCorregimientosList);
            em.persist(municipios);
            if (departamento != null) {
                departamento.getMunicipiosList().add(municipios);
                departamento = em.merge(departamento);
            }
            for (Corregimientos corregimientosListCorregimientos : municipios.getCorregimientosList()) {
                Municipios oldMunicipioOfCorregimientosListCorregimientos = corregimientosListCorregimientos.getMunicipio();
                corregimientosListCorregimientos.setMunicipio(municipios);
                corregimientosListCorregimientos = em.merge(corregimientosListCorregimientos);
                if (oldMunicipioOfCorregimientosListCorregimientos != null) {
                    oldMunicipioOfCorregimientosListCorregimientos.getCorregimientosList().remove(corregimientosListCorregimientos);
                    oldMunicipioOfCorregimientosListCorregimientos = em.merge(oldMunicipioOfCorregimientosListCorregimientos);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findMunicipios(municipios.getId()) != null) {
                throw new PreexistingEntityException("Municipios " + municipios + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Municipios municipios) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Municipios persistentMunicipios = em.find(Municipios.class, municipios.getId());
            Departamentos departamentoOld = persistentMunicipios.getDepartamento();
            Departamentos departamentoNew = municipios.getDepartamento();
            List<Corregimientos> corregimientosListOld = persistentMunicipios.getCorregimientosList();
            List<Corregimientos> corregimientosListNew = municipios.getCorregimientosList();
            if (departamentoNew != null) {
                departamentoNew = em.getReference(departamentoNew.getClass(), departamentoNew.getId());
                municipios.setDepartamento(departamentoNew);
            }
            List<Corregimientos> attachedCorregimientosListNew = new ArrayList<Corregimientos>();
            for (Corregimientos corregimientosListNewCorregimientosToAttach : corregimientosListNew) {
                corregimientosListNewCorregimientosToAttach = em.getReference(corregimientosListNewCorregimientosToAttach.getClass(), corregimientosListNewCorregimientosToAttach.getId());
                attachedCorregimientosListNew.add(corregimientosListNewCorregimientosToAttach);
            }
            corregimientosListNew = attachedCorregimientosListNew;
            municipios.setCorregimientosList(corregimientosListNew);
            municipios = em.merge(municipios);
            if (departamentoOld != null && !departamentoOld.equals(departamentoNew)) {
                departamentoOld.getMunicipiosList().remove(municipios);
                departamentoOld = em.merge(departamentoOld);
            }
            if (departamentoNew != null && !departamentoNew.equals(departamentoOld)) {
                departamentoNew.getMunicipiosList().add(municipios);
                departamentoNew = em.merge(departamentoNew);
            }
            for (Corregimientos corregimientosListOldCorregimientos : corregimientosListOld) {
                if (!corregimientosListNew.contains(corregimientosListOldCorregimientos)) {
                    corregimientosListOldCorregimientos.setMunicipio(null);
                    corregimientosListOldCorregimientos = em.merge(corregimientosListOldCorregimientos);
                }
            }
            for (Corregimientos corregimientosListNewCorregimientos : corregimientosListNew) {
                if (!corregimientosListOld.contains(corregimientosListNewCorregimientos)) {
                    Municipios oldMunicipioOfCorregimientosListNewCorregimientos = corregimientosListNewCorregimientos.getMunicipio();
                    corregimientosListNewCorregimientos.setMunicipio(municipios);
                    corregimientosListNewCorregimientos = em.merge(corregimientosListNewCorregimientos);
                    if (oldMunicipioOfCorregimientosListNewCorregimientos != null && !oldMunicipioOfCorregimientosListNewCorregimientos.equals(municipios)) {
                        oldMunicipioOfCorregimientosListNewCorregimientos.getCorregimientosList().remove(corregimientosListNewCorregimientos);
                        oldMunicipioOfCorregimientosListNewCorregimientos = em.merge(oldMunicipioOfCorregimientosListNewCorregimientos);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = municipios.getId();
                if (findMunicipios(id) == null) {
                    throw new NonexistentEntityException("The municipios with id " + id + " no longer exists.");
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
            Municipios municipios;
            try {
                municipios = em.getReference(Municipios.class, id);
                municipios.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The municipios with id " + id + " no longer exists.", enfe);
            }
            Departamentos departamento = municipios.getDepartamento();
            if (departamento != null) {
                departamento.getMunicipiosList().remove(municipios);
                departamento = em.merge(departamento);
            }
            List<Corregimientos> corregimientosList = municipios.getCorregimientosList();
            for (Corregimientos corregimientosListCorregimientos : corregimientosList) {
                corregimientosListCorregimientos.setMunicipio(null);
                corregimientosListCorregimientos = em.merge(corregimientosListCorregimientos);
            }
            em.remove(municipios);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Municipios> findMunicipiosEntities() {
        return findMunicipiosEntities(true, -1, -1);
    }

    public List<Municipios> findMunicipiosEntities(int maxResults, int firstResult) {
        return findMunicipiosEntities(false, maxResults, firstResult);
    }

    private List<Municipios> findMunicipiosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Municipios.class));
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

    public Municipios findMunicipios(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Municipios.class, id);
        } finally {
            em.close();
        }
    }

    public int getMunicipiosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Municipios> rt = cq.from(Municipios.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
