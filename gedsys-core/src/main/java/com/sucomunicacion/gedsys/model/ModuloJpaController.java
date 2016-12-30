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
import com.sucomunicacion.gedsys.entities.Acl;
import com.sucomunicacion.gedsys.entities.Modulo;
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
public class ModuloJpaController implements Serializable {

    public ModuloJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Modulo modulo) throws PreexistingEntityException, Exception {
        if (modulo.getAclList() == null) {
            modulo.setAclList(new ArrayList<Acl>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Acl> attachedAclList = new ArrayList<Acl>();
            for (Acl aclListAclToAttach : modulo.getAclList()) {
                aclListAclToAttach = em.getReference(aclListAclToAttach.getClass(), aclListAclToAttach.getId());
                attachedAclList.add(aclListAclToAttach);
            }
            modulo.setAclList(attachedAclList);
            em.persist(modulo);
            for (Acl aclListAcl : modulo.getAclList()) {
                Modulo oldModuleIdOfAclListAcl = aclListAcl.getModuleId();
                aclListAcl.setModuleId(modulo);
                aclListAcl = em.merge(aclListAcl);
                if (oldModuleIdOfAclListAcl != null) {
                    oldModuleIdOfAclListAcl.getAclList().remove(aclListAcl);
                    oldModuleIdOfAclListAcl = em.merge(oldModuleIdOfAclListAcl);
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
            List<Acl> aclListOld = persistentModulo.getAclList();
            List<Acl> aclListNew = modulo.getAclList();
            List<Acl> attachedAclListNew = new ArrayList<Acl>();
            for (Acl aclListNewAclToAttach : aclListNew) {
                aclListNewAclToAttach = em.getReference(aclListNewAclToAttach.getClass(), aclListNewAclToAttach.getId());
                attachedAclListNew.add(aclListNewAclToAttach);
            }
            aclListNew = attachedAclListNew;
            modulo.setAclList(aclListNew);
            modulo = em.merge(modulo);
            for (Acl aclListOldAcl : aclListOld) {
                if (!aclListNew.contains(aclListOldAcl)) {
                    aclListOldAcl.setModuleId(null);
                    aclListOldAcl = em.merge(aclListOldAcl);
                }
            }
            for (Acl aclListNewAcl : aclListNew) {
                if (!aclListOld.contains(aclListNewAcl)) {
                    Modulo oldModuleIdOfAclListNewAcl = aclListNewAcl.getModuleId();
                    aclListNewAcl.setModuleId(modulo);
                    aclListNewAcl = em.merge(aclListNewAcl);
                    if (oldModuleIdOfAclListNewAcl != null && !oldModuleIdOfAclListNewAcl.equals(modulo)) {
                        oldModuleIdOfAclListNewAcl.getAclList().remove(aclListNewAcl);
                        oldModuleIdOfAclListNewAcl = em.merge(oldModuleIdOfAclListNewAcl);
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
            List<Acl> aclList = modulo.getAclList();
            for (Acl aclListAcl : aclList) {
                aclListAcl.setModuleId(null);
                aclListAcl = em.merge(aclListAcl);
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
