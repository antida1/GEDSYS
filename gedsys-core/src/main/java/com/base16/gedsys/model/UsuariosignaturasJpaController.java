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
import com.base16.gedsys.entities.SignaturaTopografica;
import com.base16.gedsys.entities.Usuariosignaturas;
import com.base16.gedsys.model.exceptions.NonexistentEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author rober
 */
public class UsuariosignaturasJpaController implements Serializable {

    public UsuariosignaturasJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Usuariosignaturas usuariosignaturas) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario creadoPor = usuariosignaturas.getCreadoPor();
            if (creadoPor != null) {
                creadoPor = em.getReference(creadoPor.getClass(), creadoPor.getId());
                usuariosignaturas.setCreadoPor(creadoPor);
            }
            Usuario modificadorPor = usuariosignaturas.getModificadorPor();
            if (modificadorPor != null) {
                modificadorPor = em.getReference(modificadorPor.getClass(), modificadorPor.getId());
                usuariosignaturas.setModificadorPor(modificadorPor);
            }
            SignaturaTopografica signatura = usuariosignaturas.getSignatura();
            if (signatura != null) {
                signatura = em.getReference(signatura.getClass(), signatura.getId());
                usuariosignaturas.setSignatura(signatura);
            }
            Usuario usuario = usuariosignaturas.getUsuario();
            if (usuario != null) {
                usuario = em.getReference(usuario.getClass(), usuario.getId());
                usuariosignaturas.setUsuario(usuario);
            }
            em.persist(usuariosignaturas);
            if (creadoPor != null) {
                creadoPor.getUsuariosignaturasList().add(usuariosignaturas);
                creadoPor = em.merge(creadoPor);
            }
            if (modificadorPor != null) {
                modificadorPor.getUsuariosignaturasList().add(usuariosignaturas);
                modificadorPor = em.merge(modificadorPor);
            }
            if (signatura != null) {
                signatura.getUsuariosignaturasList().add(usuariosignaturas);
                signatura = em.merge(signatura);
            }
            if (usuario != null) {
                usuario.getUsuariosignaturasList().add(usuariosignaturas);
                usuario = em.merge(usuario);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Usuariosignaturas usuariosignaturas) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuariosignaturas persistentUsuariosignaturas = em.find(Usuariosignaturas.class, usuariosignaturas.getId());
            Usuario creadoPorOld = persistentUsuariosignaturas.getCreadoPor();
            Usuario creadoPorNew = usuariosignaturas.getCreadoPor();
            Usuario modificadorPorOld = persistentUsuariosignaturas.getModificadorPor();
            Usuario modificadorPorNew = usuariosignaturas.getModificadorPor();
            SignaturaTopografica signaturaOld = persistentUsuariosignaturas.getSignatura();
            SignaturaTopografica signaturaNew = usuariosignaturas.getSignatura();
            Usuario usuarioOld = persistentUsuariosignaturas.getUsuario();
            Usuario usuarioNew = usuariosignaturas.getUsuario();
            if (creadoPorNew != null) {
                creadoPorNew = em.getReference(creadoPorNew.getClass(), creadoPorNew.getId());
                usuariosignaturas.setCreadoPor(creadoPorNew);
            }
            if (modificadorPorNew != null) {
                modificadorPorNew = em.getReference(modificadorPorNew.getClass(), modificadorPorNew.getId());
                usuariosignaturas.setModificadorPor(modificadorPorNew);
            }
            if (signaturaNew != null) {
                signaturaNew = em.getReference(signaturaNew.getClass(), signaturaNew.getId());
                usuariosignaturas.setSignatura(signaturaNew);
            }
            if (usuarioNew != null) {
                usuarioNew = em.getReference(usuarioNew.getClass(), usuarioNew.getId());
                usuariosignaturas.setUsuario(usuarioNew);
            }
            usuariosignaturas = em.merge(usuariosignaturas);
            if (creadoPorOld != null && !creadoPorOld.equals(creadoPorNew)) {
                creadoPorOld.getUsuariosignaturasList().remove(usuariosignaturas);
                creadoPorOld = em.merge(creadoPorOld);
            }
            if (creadoPorNew != null && !creadoPorNew.equals(creadoPorOld)) {
                creadoPorNew.getUsuariosignaturasList().add(usuariosignaturas);
                creadoPorNew = em.merge(creadoPorNew);
            }
            if (modificadorPorOld != null && !modificadorPorOld.equals(modificadorPorNew)) {
                modificadorPorOld.getUsuariosignaturasList().remove(usuariosignaturas);
                modificadorPorOld = em.merge(modificadorPorOld);
            }
            if (modificadorPorNew != null && !modificadorPorNew.equals(modificadorPorOld)) {
                modificadorPorNew.getUsuariosignaturasList().add(usuariosignaturas);
                modificadorPorNew = em.merge(modificadorPorNew);
            }
            if (signaturaOld != null && !signaturaOld.equals(signaturaNew)) {
                signaturaOld.getUsuariosignaturasList().remove(usuariosignaturas);
                signaturaOld = em.merge(signaturaOld);
            }
            if (signaturaNew != null && !signaturaNew.equals(signaturaOld)) {
                signaturaNew.getUsuariosignaturasList().add(usuariosignaturas);
                signaturaNew = em.merge(signaturaNew);
            }
            if (usuarioOld != null && !usuarioOld.equals(usuarioNew)) {
                usuarioOld.getUsuariosignaturasList().remove(usuariosignaturas);
                usuarioOld = em.merge(usuarioOld);
            }
            if (usuarioNew != null && !usuarioNew.equals(usuarioOld)) {
                usuarioNew.getUsuariosignaturasList().add(usuariosignaturas);
                usuarioNew = em.merge(usuarioNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = usuariosignaturas.getId();
                if (findUsuariosignaturas(id) == null) {
                    throw new NonexistentEntityException("The usuariosignaturas with id " + id + " no longer exists.");
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
            Usuariosignaturas usuariosignaturas;
            try {
                usuariosignaturas = em.getReference(Usuariosignaturas.class, id);
                usuariosignaturas.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The usuariosignaturas with id " + id + " no longer exists.", enfe);
            }
            Usuario creadoPor = usuariosignaturas.getCreadoPor();
            if (creadoPor != null) {
                creadoPor.getUsuariosignaturasList().remove(usuariosignaturas);
                creadoPor = em.merge(creadoPor);
            }
            Usuario modificadorPor = usuariosignaturas.getModificadorPor();
            if (modificadorPor != null) {
                modificadorPor.getUsuariosignaturasList().remove(usuariosignaturas);
                modificadorPor = em.merge(modificadorPor);
            }
            SignaturaTopografica signatura = usuariosignaturas.getSignatura();
            if (signatura != null) {
                signatura.getUsuariosignaturasList().remove(usuariosignaturas);
                signatura = em.merge(signatura);
            }
            Usuario usuario = usuariosignaturas.getUsuario();
            if (usuario != null) {
                usuario.getUsuariosignaturasList().remove(usuariosignaturas);
                usuario = em.merge(usuario);
            }
            em.remove(usuariosignaturas);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Usuariosignaturas> findUsuariosignaturasEntities() {
        return findUsuariosignaturasEntities(true, -1, -1);
    }

    public List<Usuariosignaturas> findUsuariosignaturasEntities(int maxResults, int firstResult) {
        return findUsuariosignaturasEntities(false, maxResults, firstResult);
    }

    private List<Usuariosignaturas> findUsuariosignaturasEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Usuariosignaturas.class));
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

    public Usuariosignaturas findUsuariosignaturas(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Usuariosignaturas.class, id);
        } finally {
            em.close();
        }
    }

    public int getUsuariosignaturasCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();   
            Root<Usuariosignaturas> rt = cq.from(Usuariosignaturas.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public List<Usuariosignaturas> findUsuariosignaturasEntitiesByUsuario(Usuario usuario) {
        return findUsuariosignaturasEntitiesByUsuario(usuario, true, -1, -1);
    }

    public List<Usuariosignaturas> findUsuariosignaturasEntitiesByUsuario(Usuario usuario, int maxResults, int firstResult) {
        return findUsuariosignaturasEntitiesByUsuario(usuario, false, maxResults, firstResult);
    }

    private List<Usuariosignaturas> findUsuariosignaturasEntitiesByUsuario(Usuario usuario, boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Usuariosignaturas.class));
            Query q = em.createNamedQuery("Usuariosignaturas.findByUsuario", Usuariosignaturas.class)
                    .setParameter("usuario", usuario);
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
