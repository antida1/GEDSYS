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
import com.sucomunicacion.gedsys.entities.Acl;
import com.sucomunicacion.gedsys.entities.Modulo;
import com.sucomunicacion.gedsys.model.exceptions.NonexistentEntityException;
import com.sucomunicacion.gedsys.model.exceptions.PreexistingEntityException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author rober
 */
public class ModuloJpaController implements Serializable {

    public ModuloJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Modulo modulo) throws PreexistingEntityException, Exception {
        if (modulo.getAclCollection() == null) {
            modulo.setAclCollection(new ArrayList<Acl>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario creadoPor = modulo.getCreadoPor();
            if (creadoPor != null) {
                creadoPor = em.getReference(creadoPor.getClass(), creadoPor.getId());
                modulo.setCreadoPor(creadoPor);
            }
            Usuario modificadoPor = modulo.getModificadoPor();
            if (modificadoPor != null) {
                modificadoPor = em.getReference(modificadoPor.getClass(), modificadoPor.getId());
                modulo.setModificadoPor(modificadoPor);
            }
            Collection<Acl> attachedAclCollection = new ArrayList<Acl>();
            for (Acl aclCollectionAclToAttach : modulo.getAclCollection()) {
                aclCollectionAclToAttach = em.getReference(aclCollectionAclToAttach.getClass(), aclCollectionAclToAttach.getId());
                attachedAclCollection.add(aclCollectionAclToAttach);
            }
            modulo.setAclCollection(attachedAclCollection);
            em.persist(modulo);
            if (creadoPor != null) {
                creadoPor.getModuloCollection().add(modulo);
                creadoPor = em.merge(creadoPor);
            }
            if (modificadoPor != null) {
                modificadoPor.getModuloCollection().add(modulo);
                modificadoPor = em.merge(modificadoPor);
            }
            for (Acl aclCollectionAcl : modulo.getAclCollection()) {
                Modulo oldModuloOfAclCollectionAcl = aclCollectionAcl.getModulo();
                aclCollectionAcl.setModulo(modulo);
                aclCollectionAcl = em.merge(aclCollectionAcl);
                if (oldModuloOfAclCollectionAcl != null) {
                    oldModuloOfAclCollectionAcl.getAclCollection().remove(aclCollectionAcl);
                    oldModuloOfAclCollectionAcl = em.merge(oldModuloOfAclCollectionAcl);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findModulo(modulo.getId()) != null) {
                throw new PreexistingEntityException("Modulo " + modulo + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Modulo modulo) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Modulo persistentModulo = em.find(Modulo.class, modulo.getId());
            Usuario creadoPorOld = persistentModulo.getCreadoPor();
            Usuario creadoPorNew = modulo.getCreadoPor();
            Usuario modificadoPorOld = persistentModulo.getModificadoPor();
            Usuario modificadoPorNew = modulo.getModificadoPor();
            Collection<Acl> aclCollectionOld = persistentModulo.getAclCollection();
            Collection<Acl> aclCollectionNew = modulo.getAclCollection();
            if (creadoPorNew != null) {
                creadoPorNew = em.getReference(creadoPorNew.getClass(), creadoPorNew.getId());
                modulo.setCreadoPor(creadoPorNew);
            }
            if (modificadoPorNew != null) {
                modificadoPorNew = em.getReference(modificadoPorNew.getClass(), modificadoPorNew.getId());
                modulo.setModificadoPor(modificadoPorNew);
            }
            Collection<Acl> attachedAclCollectionNew = new ArrayList<Acl>();
            for (Acl aclCollectionNewAclToAttach : aclCollectionNew) {
                aclCollectionNewAclToAttach = em.getReference(aclCollectionNewAclToAttach.getClass(), aclCollectionNewAclToAttach.getId());
                attachedAclCollectionNew.add(aclCollectionNewAclToAttach);
            }
            aclCollectionNew = attachedAclCollectionNew;
            modulo.setAclCollection(aclCollectionNew);
            modulo = em.merge(modulo);
            if (creadoPorOld != null && !creadoPorOld.equals(creadoPorNew)) {
                creadoPorOld.getModuloCollection().remove(modulo);
                creadoPorOld = em.merge(creadoPorOld);
            }
            if (creadoPorNew != null && !creadoPorNew.equals(creadoPorOld)) {
                creadoPorNew.getModuloCollection().add(modulo);
                creadoPorNew = em.merge(creadoPorNew);
            }
            if (modificadoPorOld != null && !modificadoPorOld.equals(modificadoPorNew)) {
                modificadoPorOld.getModuloCollection().remove(modulo);
                modificadoPorOld = em.merge(modificadoPorOld);
            }
            if (modificadoPorNew != null && !modificadoPorNew.equals(modificadoPorOld)) {
                modificadoPorNew.getModuloCollection().add(modulo);
                modificadoPorNew = em.merge(modificadoPorNew);
            }
            for (Acl aclCollectionOldAcl : aclCollectionOld) {
                if (!aclCollectionNew.contains(aclCollectionOldAcl)) {
                    aclCollectionOldAcl.setModulo(null);
                    aclCollectionOldAcl = em.merge(aclCollectionOldAcl);
                }
            }
            for (Acl aclCollectionNewAcl : aclCollectionNew) {
                if (!aclCollectionOld.contains(aclCollectionNewAcl)) {
                    Modulo oldModuloOfAclCollectionNewAcl = aclCollectionNewAcl.getModulo();
                    aclCollectionNewAcl.setModulo(modulo);
                    aclCollectionNewAcl = em.merge(aclCollectionNewAcl);
                    if (oldModuloOfAclCollectionNewAcl != null && !oldModuloOfAclCollectionNewAcl.equals(modulo)) {
                        oldModuloOfAclCollectionNewAcl.getAclCollection().remove(aclCollectionNewAcl);
                        oldModuloOfAclCollectionNewAcl = em.merge(oldModuloOfAclCollectionNewAcl);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = modulo.getId();
                if (findModulo(id) == null) {
                    throw new NonexistentEntityException("The modulo with id " + id + " no longer exists.");
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
            Modulo modulo;
            try {
                modulo = em.getReference(Modulo.class, id);
                modulo.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The modulo with id " + id + " no longer exists.", enfe);
            }
            Usuario creadoPor = modulo.getCreadoPor();
            if (creadoPor != null) {
                creadoPor.getModuloCollection().remove(modulo);
                creadoPor = em.merge(creadoPor);
            }
            Usuario modificadoPor = modulo.getModificadoPor();
            if (modificadoPor != null) {
                modificadoPor.getModuloCollection().remove(modulo);
                modificadoPor = em.merge(modificadoPor);
            }
            Collection<Acl> aclCollection = modulo.getAclCollection();
            for (Acl aclCollectionAcl : aclCollection) {
                aclCollectionAcl.setModulo(null);
                aclCollectionAcl = em.merge(aclCollectionAcl);
            }
            em.remove(modulo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Modulo> findModuloEntities() {
        return findModuloEntities(true, -1, -1);
    }

    public List<Modulo> findModuloEntities(int maxResults, int firstResult) {
        return findModuloEntities(false, maxResults, firstResult);
    }

    private List<Modulo> findModuloEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Modulo.class));
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

    public Modulo findModulo(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Modulo.class, id);
        } finally {
            em.close();
        }
    }

    public int getModuloCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Modulo> rt = cq.from(Modulo.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
