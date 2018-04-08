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
import com.base16.gedsys.entities.Grupo;
import com.base16.gedsys.entities.GrupoUsuario;
import com.base16.gedsys.model.exceptions.NonexistentEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author rober
 */
public class GrupoUsuarioJpaController implements Serializable {

    public GrupoUsuarioJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(GrupoUsuario grupoUsuario) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario creadoPor = grupoUsuario.getCreadoPor();
            if (creadoPor != null) {
                creadoPor = em.getReference(creadoPor.getClass(), creadoPor.getId());
                grupoUsuario.setCreadoPor(creadoPor);
            }
            Grupo grupo = grupoUsuario.getGrupo();
            if (grupo != null) {
                grupo = em.getReference(grupo.getClass(), grupo.getId());
                grupoUsuario.setGrupo(grupo);
            }
            Usuario modificadoPor = grupoUsuario.getModificadoPor();
            if (modificadoPor != null) {
                modificadoPor = em.getReference(modificadoPor.getClass(), modificadoPor.getId());
                grupoUsuario.setModificadoPor(modificadoPor);
            }
            Usuario usuario = grupoUsuario.getUsuario();
            if (usuario != null) {
                usuario = em.getReference(usuario.getClass(), usuario.getId());
                grupoUsuario.setUsuario(usuario);
            }
            em.persist(grupoUsuario);
            if (creadoPor != null) {
                creadoPor.getGrupoUsuarioCollection().add(grupoUsuario);
                creadoPor = em.merge(creadoPor);
            }
            if (grupo != null) {
                grupo.getGrupoUsuarioCollection().add(grupoUsuario);
                grupo = em.merge(grupo);
            }
            if (modificadoPor != null) {
                modificadoPor.getGrupoUsuarioCollection().add(grupoUsuario);
                modificadoPor = em.merge(modificadoPor);
            }
            if (usuario != null) {
                usuario.getGrupoUsuarioCollection().add(grupoUsuario);
                usuario = em.merge(usuario);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(GrupoUsuario grupoUsuario) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            GrupoUsuario persistentGrupoUsuario = em.find(GrupoUsuario.class, grupoUsuario.getId());
            Usuario creadoPorOld = persistentGrupoUsuario.getCreadoPor();
            Usuario creadoPorNew = grupoUsuario.getCreadoPor();
            Grupo grupoOld = persistentGrupoUsuario.getGrupo();
            Grupo grupoNew = grupoUsuario.getGrupo();
            Usuario modificadoPorOld = persistentGrupoUsuario.getModificadoPor();
            Usuario modificadoPorNew = grupoUsuario.getModificadoPor();
            Usuario usuarioOld = persistentGrupoUsuario.getUsuario();
            Usuario usuarioNew = grupoUsuario.getUsuario();
            if (creadoPorNew != null) {
                creadoPorNew = em.getReference(creadoPorNew.getClass(), creadoPorNew.getId());
                grupoUsuario.setCreadoPor(creadoPorNew);
            }
            if (grupoNew != null) {
                grupoNew = em.getReference(grupoNew.getClass(), grupoNew.getId());
                grupoUsuario.setGrupo(grupoNew);
            }
            if (modificadoPorNew != null) {
                modificadoPorNew = em.getReference(modificadoPorNew.getClass(), modificadoPorNew.getId());
                grupoUsuario.setModificadoPor(modificadoPorNew);
            }
            if (usuarioNew != null) {
                usuarioNew = em.getReference(usuarioNew.getClass(), usuarioNew.getId());
                grupoUsuario.setUsuario(usuarioNew);
            }
            grupoUsuario = em.merge(grupoUsuario);
            if (creadoPorOld != null && !creadoPorOld.equals(creadoPorNew)) {
                creadoPorOld.getGrupoUsuarioCollection().remove(grupoUsuario);
                creadoPorOld = em.merge(creadoPorOld);
            }
            if (creadoPorNew != null && !creadoPorNew.equals(creadoPorOld)) {
                creadoPorNew.getGrupoUsuarioCollection().add(grupoUsuario);
                creadoPorNew = em.merge(creadoPorNew);
            }
            if (grupoOld != null && !grupoOld.equals(grupoNew)) {
                grupoOld.getGrupoUsuarioCollection().remove(grupoUsuario);
                grupoOld = em.merge(grupoOld);
            }
            if (grupoNew != null && !grupoNew.equals(grupoOld)) {
                grupoNew.getGrupoUsuarioCollection().add(grupoUsuario);
                grupoNew = em.merge(grupoNew);
            }
            if (modificadoPorOld != null && !modificadoPorOld.equals(modificadoPorNew)) {
                modificadoPorOld.getGrupoUsuarioCollection().remove(grupoUsuario);
                modificadoPorOld = em.merge(modificadoPorOld);
            }
            if (modificadoPorNew != null && !modificadoPorNew.equals(modificadoPorOld)) {
                modificadoPorNew.getGrupoUsuarioCollection().add(grupoUsuario);
                modificadoPorNew = em.merge(modificadoPorNew);
            }
            if (usuarioOld != null && !usuarioOld.equals(usuarioNew)) {
                usuarioOld.getGrupoUsuarioCollection().remove(grupoUsuario);
                usuarioOld = em.merge(usuarioOld);
            }
            if (usuarioNew != null && !usuarioNew.equals(usuarioOld)) {
                usuarioNew.getGrupoUsuarioCollection().add(grupoUsuario);
                usuarioNew = em.merge(usuarioNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = grupoUsuario.getId();
                if (findGrupoUsuario(id) == null) {
                    throw new NonexistentEntityException("The grupoUsuario with id " + id + " no longer exists.");
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
            GrupoUsuario grupoUsuario;
            try {
                grupoUsuario = em.getReference(GrupoUsuario.class, id);
                grupoUsuario.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The grupoUsuario with id " + id + " no longer exists.", enfe);
            }
            Usuario creadoPor = grupoUsuario.getCreadoPor();
            if (creadoPor != null) {
                creadoPor.getGrupoUsuarioCollection().remove(grupoUsuario);
                creadoPor = em.merge(creadoPor);
            }
            Grupo grupo = grupoUsuario.getGrupo();
            if (grupo != null) {
                grupo.getGrupoUsuarioCollection().remove(grupoUsuario);
                grupo = em.merge(grupo);
            }
            Usuario modificadoPor = grupoUsuario.getModificadoPor();
            if (modificadoPor != null) {
                modificadoPor.getGrupoUsuarioCollection().remove(grupoUsuario);
                modificadoPor = em.merge(modificadoPor);
            }
            Usuario usuario = grupoUsuario.getUsuario();
            if (usuario != null) {
                usuario.getGrupoUsuarioCollection().remove(grupoUsuario);
                usuario = em.merge(usuario);
            }
            em.remove(grupoUsuario);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<GrupoUsuario> findGrupoUsuarioEntities() {
        return findGrupoUsuarioEntities(true, -1, -1);
    }

    public List<GrupoUsuario> findGrupoUsuarioEntities(int maxResults, int firstResult) {
        return findGrupoUsuarioEntities(false, maxResults, firstResult);
    }

    private List<GrupoUsuario> findGrupoUsuarioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(GrupoUsuario.class));
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

    public GrupoUsuario findGrupoUsuario(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(GrupoUsuario.class, id);
        } finally {
            em.close();
        }
    }

    public int getGrupoUsuarioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<GrupoUsuario> rt = cq.from(GrupoUsuario.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
