/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base16.gedsys.model;

import com.base16.gedsys.entities.Acl;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.base16.gedsys.entities.Usuario;
import com.base16.gedsys.entities.Grupo;
import com.base16.gedsys.entities.Modulo;
import com.base16.gedsys.model.exceptions.NonexistentEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

/**
 *
 * @author rober
 */
public class AclJpaController implements Serializable {

    public AclJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Acl acl) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario creadoPor = acl.getCreadoPor();
            if (creadoPor != null) {
                creadoPor = em.getReference(creadoPor.getClass(), creadoPor.getId());
                acl.setCreadoPor(creadoPor);
            }
            Grupo grupo = acl.getGrupo();
            if (grupo != null) {
                grupo = em.getReference(grupo.getClass(), grupo.getId());
                acl.setGrupo(grupo);
            }
            Usuario modificadoPor = acl.getModificadoPor();
            if (modificadoPor != null) {
                modificadoPor = em.getReference(modificadoPor.getClass(), modificadoPor.getId());
                acl.setModificadoPor(modificadoPor);
            }
            Modulo modulo = acl.getModulo();
            if (modulo != null) {
                modulo = em.getReference(modulo.getClass(), modulo.getId());
                acl.setModulo(modulo);
            }
            em.persist(acl);
            if (creadoPor != null) {
                creadoPor.getAclCollection().add(acl);
                creadoPor = em.merge(creadoPor);
            }
            if (grupo != null) {
                grupo.getAclCollection().add(acl);
                grupo = em.merge(grupo);
            }
            if (modificadoPor != null) {
                modificadoPor.getAclCollection().add(acl);
                modificadoPor = em.merge(modificadoPor);
            }
            if (modulo != null) {
                modulo.getAclCollection().add(acl);
                modulo = em.merge(modulo);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Acl acl) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Acl persistentAcl = em.find(Acl.class, acl.getId());
            Usuario creadoPorOld = persistentAcl.getCreadoPor();
            Usuario creadoPorNew = acl.getCreadoPor();
            Grupo grupoOld = persistentAcl.getGrupo();
            Grupo grupoNew = acl.getGrupo();
            Usuario modificadoPorOld = persistentAcl.getModificadoPor();
            Usuario modificadoPorNew = acl.getModificadoPor();
            Modulo moduloOld = persistentAcl.getModulo();
            Modulo moduloNew = acl.getModulo();
            if (creadoPorNew != null) {
                creadoPorNew = em.getReference(creadoPorNew.getClass(), creadoPorNew.getId());
                acl.setCreadoPor(creadoPorNew);
            }
            if (grupoNew != null) {
                grupoNew = em.getReference(grupoNew.getClass(), grupoNew.getId());
                acl.setGrupo(grupoNew);
            }
            if (modificadoPorNew != null) {
                modificadoPorNew = em.getReference(modificadoPorNew.getClass(), modificadoPorNew.getId());
                acl.setModificadoPor(modificadoPorNew);
            }
            if (moduloNew != null) {
                moduloNew = em.getReference(moduloNew.getClass(), moduloNew.getId());
                acl.setModulo(moduloNew);
            }
            acl = em.merge(acl);
            if (creadoPorOld != null && !creadoPorOld.equals(creadoPorNew)) {
                creadoPorOld.getAclCollection().remove(acl);
                creadoPorOld = em.merge(creadoPorOld);
            }
            if (creadoPorNew != null && !creadoPorNew.equals(creadoPorOld)) {
                creadoPorNew.getAclCollection().add(acl);
                creadoPorNew = em.merge(creadoPorNew);
            }
            if (grupoOld != null && !grupoOld.equals(grupoNew)) {
                grupoOld.getAclCollection().remove(acl);
                grupoOld = em.merge(grupoOld);
            }
            if (grupoNew != null && !grupoNew.equals(grupoOld)) {
                grupoNew.getAclCollection().add(acl);
                grupoNew = em.merge(grupoNew);
            }
            if (modificadoPorOld != null && !modificadoPorOld.equals(modificadoPorNew)) {
                modificadoPorOld.getAclCollection().remove(acl);
                modificadoPorOld = em.merge(modificadoPorOld);
            }
            if (modificadoPorNew != null && !modificadoPorNew.equals(modificadoPorOld)) {
                modificadoPorNew.getAclCollection().add(acl);
                modificadoPorNew = em.merge(modificadoPorNew);
            }
            if (moduloOld != null && !moduloOld.equals(moduloNew)) {
                moduloOld.getAclCollection().remove(acl);
                moduloOld = em.merge(moduloOld);
            }
            if (moduloNew != null && !moduloNew.equals(moduloOld)) {
                moduloNew.getAclCollection().add(acl);
                moduloNew = em.merge(moduloNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = acl.getId();
                if (findAcl(id) == null) {
                    throw new NonexistentEntityException("The acl with id " + id + " no longer exists.");
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
            Acl acl;
            try {
                acl = em.getReference(Acl.class, id);
                acl.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The acl with id " + id + " no longer exists.", enfe);
            }
            Usuario creadoPor = acl.getCreadoPor();
            if (creadoPor != null) {
                creadoPor.getAclCollection().remove(acl);
                creadoPor = em.merge(creadoPor);
            }
            Grupo grupo = acl.getGrupo();
            if (grupo != null) {
                grupo.getAclCollection().remove(acl);
                grupo = em.merge(grupo);
            }
            Usuario modificadoPor = acl.getModificadoPor();
            if (modificadoPor != null) {
                modificadoPor.getAclCollection().remove(acl);
                modificadoPor = em.merge(modificadoPor);
            }
            Modulo modulo = acl.getModulo();
            if (modulo != null) {
                modulo.getAclCollection().remove(acl);
                modulo = em.merge(modulo);
            }
            em.remove(acl);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Acl> findAclEntities() {
        return findAclEntities(true, -1, -1);
    }

    public List<Acl> findAclEntities(int maxResults, int firstResult) {
        return findAclEntities(false, maxResults, firstResult);
    }

    private List<Acl> findAclEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Acl.class));
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

    public Acl findAcl(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Acl.class, id);
        } finally {
            em.close();
        }
    }

    public int getAclCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Acl> rt = cq.from(Acl.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    public Acl getAclByGrupoIdAndModuleId( Integer grupoId, Integer moduleId  ) {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Acl> query = em.createQuery("SELECT a FROM Acl a WHERE a.grupo = :grupo AND a.modulo = :modulo", Acl.class);
            query.setParameter("grupo", grupoId);
            query.setParameter("modulo", moduleId);
            return query.getSingleResult();
        } finally {
            em.close();
        } 
    }
    
    public List<Acl> findAclByGrupo( Grupo grupo  ) {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Acl> query = em.createQuery("SELECT a FROM Acl a WHERE a.grupo = :grupo", Acl.class);
            query.setParameter("grupo", grupo);
            return query.getResultList();
        } finally {
            em.close();
        } 
    }
    
}
