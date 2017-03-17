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
import com.sucomunicacion.gedsys.entities.GrupoUsuario;
import java.util.ArrayList;
import java.util.Collection;
import com.sucomunicacion.gedsys.entities.Acl;
import com.sucomunicacion.gedsys.entities.Grupo;
import com.sucomunicacion.gedsys.model.exceptions.NonexistentEntityException;
import com.sucomunicacion.gedsys.model.exceptions.PreexistingEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author rober
 */
public class GrupoJpaController implements Serializable {

    public GrupoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Grupo grupo) throws PreexistingEntityException, Exception {
        if (grupo.getGrupoUsuarioCollection() == null) {
            grupo.setGrupoUsuarioCollection(new ArrayList<GrupoUsuario>());
        }
        if (grupo.getAclCollection() == null) {
            grupo.setAclCollection(new ArrayList<Acl>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario modificadoPor = grupo.getModificadoPor();
            if (modificadoPor != null) {
                modificadoPor = em.getReference(modificadoPor.getClass(), modificadoPor.getId());
                grupo.setModificadoPor(modificadoPor);
            }
            Collection<GrupoUsuario> attachedGrupoUsuarioCollection = new ArrayList<GrupoUsuario>();
            for (GrupoUsuario grupoUsuarioCollectionGrupoUsuarioToAttach : grupo.getGrupoUsuarioCollection()) {
                grupoUsuarioCollectionGrupoUsuarioToAttach = em.getReference(grupoUsuarioCollectionGrupoUsuarioToAttach.getClass(), grupoUsuarioCollectionGrupoUsuarioToAttach.getId());
                attachedGrupoUsuarioCollection.add(grupoUsuarioCollectionGrupoUsuarioToAttach);
            }
            grupo.setGrupoUsuarioCollection(attachedGrupoUsuarioCollection);
            Collection<Acl> attachedAclCollection = new ArrayList<Acl>();
            for (Acl aclCollectionAclToAttach : grupo.getAclCollection()) {
                aclCollectionAclToAttach = em.getReference(aclCollectionAclToAttach.getClass(), aclCollectionAclToAttach.getId());
                attachedAclCollection.add(aclCollectionAclToAttach);
            }
            grupo.setAclCollection(attachedAclCollection);
            em.persist(grupo);
            if (modificadoPor != null) {
                modificadoPor.getGrupoCollection().add(grupo);
                modificadoPor = em.merge(modificadoPor);
            }
            for (GrupoUsuario grupoUsuarioCollectionGrupoUsuario : grupo.getGrupoUsuarioCollection()) {
                Grupo oldGrupoOfGrupoUsuarioCollectionGrupoUsuario = grupoUsuarioCollectionGrupoUsuario.getGrupo();
                grupoUsuarioCollectionGrupoUsuario.setGrupo(grupo);
                grupoUsuarioCollectionGrupoUsuario = em.merge(grupoUsuarioCollectionGrupoUsuario);
                if (oldGrupoOfGrupoUsuarioCollectionGrupoUsuario != null) {
                    oldGrupoOfGrupoUsuarioCollectionGrupoUsuario.getGrupoUsuarioCollection().remove(grupoUsuarioCollectionGrupoUsuario);
                    oldGrupoOfGrupoUsuarioCollectionGrupoUsuario = em.merge(oldGrupoOfGrupoUsuarioCollectionGrupoUsuario);
                }
            }
            for (Acl aclCollectionAcl : grupo.getAclCollection()) {
                Grupo oldGrupoOfAclCollectionAcl = aclCollectionAcl.getGrupo();
                aclCollectionAcl.setGrupo(grupo);
                aclCollectionAcl = em.merge(aclCollectionAcl);
                if (oldGrupoOfAclCollectionAcl != null) {
                    oldGrupoOfAclCollectionAcl.getAclCollection().remove(aclCollectionAcl);
                    oldGrupoOfAclCollectionAcl = em.merge(oldGrupoOfAclCollectionAcl);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findGrupo(grupo.getId()) != null) {
                throw new PreexistingEntityException("Grupo " + grupo + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Grupo grupo) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Grupo persistentGrupo = em.find(Grupo.class, grupo.getId());
            Usuario modificadoPorOld = persistentGrupo.getModificadoPor();
            Usuario modificadoPorNew = grupo.getModificadoPor();
            Collection<GrupoUsuario> grupoUsuarioCollectionOld = persistentGrupo.getGrupoUsuarioCollection();
            Collection<GrupoUsuario> grupoUsuarioCollectionNew = grupo.getGrupoUsuarioCollection();
            Collection<Acl> aclCollectionOld = persistentGrupo.getAclCollection();
            Collection<Acl> aclCollectionNew = grupo.getAclCollection();
            if (modificadoPorNew != null) {
                modificadoPorNew = em.getReference(modificadoPorNew.getClass(), modificadoPorNew.getId());
                grupo.setModificadoPor(modificadoPorNew);
            }
            Collection<GrupoUsuario> attachedGrupoUsuarioCollectionNew = new ArrayList<GrupoUsuario>();
            for (GrupoUsuario grupoUsuarioCollectionNewGrupoUsuarioToAttach : grupoUsuarioCollectionNew) {
                grupoUsuarioCollectionNewGrupoUsuarioToAttach = em.getReference(grupoUsuarioCollectionNewGrupoUsuarioToAttach.getClass(), grupoUsuarioCollectionNewGrupoUsuarioToAttach.getId());
                attachedGrupoUsuarioCollectionNew.add(grupoUsuarioCollectionNewGrupoUsuarioToAttach);
            }
            grupoUsuarioCollectionNew = attachedGrupoUsuarioCollectionNew;
            grupo.setGrupoUsuarioCollection(grupoUsuarioCollectionNew);
            Collection<Acl> attachedAclCollectionNew = new ArrayList<Acl>();
            for (Acl aclCollectionNewAclToAttach : aclCollectionNew) {
                aclCollectionNewAclToAttach = em.getReference(aclCollectionNewAclToAttach.getClass(), aclCollectionNewAclToAttach.getId());
                attachedAclCollectionNew.add(aclCollectionNewAclToAttach);
            }
            aclCollectionNew = attachedAclCollectionNew;
            grupo.setAclCollection(aclCollectionNew);
            grupo = em.merge(grupo);
            if (modificadoPorOld != null && !modificadoPorOld.equals(modificadoPorNew)) {
                modificadoPorOld.getGrupoCollection().remove(grupo);
                modificadoPorOld = em.merge(modificadoPorOld);
            }
            if (modificadoPorNew != null && !modificadoPorNew.equals(modificadoPorOld)) {
                modificadoPorNew.getGrupoCollection().add(grupo);
                modificadoPorNew = em.merge(modificadoPorNew);
            }
            for (GrupoUsuario grupoUsuarioCollectionOldGrupoUsuario : grupoUsuarioCollectionOld) {
                if (!grupoUsuarioCollectionNew.contains(grupoUsuarioCollectionOldGrupoUsuario)) {
                    grupoUsuarioCollectionOldGrupoUsuario.setGrupo(null);
                    grupoUsuarioCollectionOldGrupoUsuario = em.merge(grupoUsuarioCollectionOldGrupoUsuario);
                }
            }
            for (GrupoUsuario grupoUsuarioCollectionNewGrupoUsuario : grupoUsuarioCollectionNew) {
                if (!grupoUsuarioCollectionOld.contains(grupoUsuarioCollectionNewGrupoUsuario)) {
                    Grupo oldGrupoOfGrupoUsuarioCollectionNewGrupoUsuario = grupoUsuarioCollectionNewGrupoUsuario.getGrupo();
                    grupoUsuarioCollectionNewGrupoUsuario.setGrupo(grupo);
                    grupoUsuarioCollectionNewGrupoUsuario = em.merge(grupoUsuarioCollectionNewGrupoUsuario);
                    if (oldGrupoOfGrupoUsuarioCollectionNewGrupoUsuario != null && !oldGrupoOfGrupoUsuarioCollectionNewGrupoUsuario.equals(grupo)) {
                        oldGrupoOfGrupoUsuarioCollectionNewGrupoUsuario.getGrupoUsuarioCollection().remove(grupoUsuarioCollectionNewGrupoUsuario);
                        oldGrupoOfGrupoUsuarioCollectionNewGrupoUsuario = em.merge(oldGrupoOfGrupoUsuarioCollectionNewGrupoUsuario);
                    }
                }
            }
            for (Acl aclCollectionOldAcl : aclCollectionOld) {
                if (!aclCollectionNew.contains(aclCollectionOldAcl)) {
                    aclCollectionOldAcl.setGrupo(null);
                    aclCollectionOldAcl = em.merge(aclCollectionOldAcl);
                }
            }
            for (Acl aclCollectionNewAcl : aclCollectionNew) {
                if (!aclCollectionOld.contains(aclCollectionNewAcl)) {
                    Grupo oldGrupoOfAclCollectionNewAcl = aclCollectionNewAcl.getGrupo();
                    aclCollectionNewAcl.setGrupo(grupo);
                    aclCollectionNewAcl = em.merge(aclCollectionNewAcl);
                    if (oldGrupoOfAclCollectionNewAcl != null && !oldGrupoOfAclCollectionNewAcl.equals(grupo)) {
                        oldGrupoOfAclCollectionNewAcl.getAclCollection().remove(aclCollectionNewAcl);
                        oldGrupoOfAclCollectionNewAcl = em.merge(oldGrupoOfAclCollectionNewAcl);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = grupo.getId();
                if (findGrupo(id) == null) {
                    throw new NonexistentEntityException("The grupo with id " + id + " no longer exists.");
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
            Grupo grupo;
            try {
                grupo = em.getReference(Grupo.class, id);
                grupo.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The grupo with id " + id + " no longer exists.", enfe);
            }
            Usuario modificadoPor = grupo.getModificadoPor();
            if (modificadoPor != null) {
                modificadoPor.getGrupoCollection().remove(grupo);
                modificadoPor = em.merge(modificadoPor);
            }
            Collection<GrupoUsuario> grupoUsuarioCollection = grupo.getGrupoUsuarioCollection();
            for (GrupoUsuario grupoUsuarioCollectionGrupoUsuario : grupoUsuarioCollection) {
                grupoUsuarioCollectionGrupoUsuario.setGrupo(null);
                grupoUsuarioCollectionGrupoUsuario = em.merge(grupoUsuarioCollectionGrupoUsuario);
            }
            Collection<Acl> aclCollection = grupo.getAclCollection();
            for (Acl aclCollectionAcl : aclCollection) {
                aclCollectionAcl.setGrupo(null);
                aclCollectionAcl = em.merge(aclCollectionAcl);
            }
            em.remove(grupo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Grupo> findGrupoEntities() {
        return findGrupoEntities(true, -1, -1);
    }

    public List<Grupo> findGrupoEntities(int maxResults, int firstResult) {
        return findGrupoEntities(false, maxResults, firstResult);
    }

    private List<Grupo> findGrupoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Grupo.class));
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

    public Grupo findGrupo(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Grupo.class, id);
        } finally {
            em.close();
        }
    }

    public int getGrupoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Grupo> rt = cq.from(Grupo.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
