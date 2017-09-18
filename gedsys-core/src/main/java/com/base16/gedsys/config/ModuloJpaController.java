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
import com.base16.gedsys.entities.Usuario;
import com.base16.gedsys.entities.Modulo;
import com.base16.gedsys.entities.Acl;
import java.util.ArrayList;
import java.util.Collection;
import com.base16.gedsys.entities.CamposPlantilla;
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

    public void create(Modulo modulo) {
        if (modulo.getAclCollection() == null) {
            modulo.setAclCollection(new ArrayList<Acl>());
        }
        if (modulo.getModuloCollection() == null) {
            modulo.setModuloCollection(new ArrayList<Modulo>());
        }
        if (modulo.getCamposPlantillaCollection() == null) {
            modulo.setCamposPlantillaCollection(new ArrayList<CamposPlantilla>());
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
            Modulo dependeDe = modulo.getDependeDe();
            if (dependeDe != null) {
                dependeDe = em.getReference(dependeDe.getClass(), dependeDe.getId());
                modulo.setDependeDe(dependeDe);
            }
            Collection<Acl> attachedAclCollection = new ArrayList<Acl>();
            for (Acl aclCollectionAclToAttach : modulo.getAclCollection()) {
                aclCollectionAclToAttach = em.getReference(aclCollectionAclToAttach.getClass(), aclCollectionAclToAttach.getId());
                attachedAclCollection.add(aclCollectionAclToAttach);
            }
            modulo.setAclCollection(attachedAclCollection);
            Collection<Modulo> attachedModuloCollection = new ArrayList<Modulo>();
            for (Modulo moduloCollectionModuloToAttach : modulo.getModuloCollection()) {
                moduloCollectionModuloToAttach = em.getReference(moduloCollectionModuloToAttach.getClass(), moduloCollectionModuloToAttach.getId());
                attachedModuloCollection.add(moduloCollectionModuloToAttach);
            }
            modulo.setModuloCollection(attachedModuloCollection);
            Collection<CamposPlantilla> attachedCamposPlantillaCollection = new ArrayList<CamposPlantilla>();
            for (CamposPlantilla camposPlantillaCollectionCamposPlantillaToAttach : modulo.getCamposPlantillaCollection()) {
                camposPlantillaCollectionCamposPlantillaToAttach = em.getReference(camposPlantillaCollectionCamposPlantillaToAttach.getClass(), camposPlantillaCollectionCamposPlantillaToAttach.getId());
                attachedCamposPlantillaCollection.add(camposPlantillaCollectionCamposPlantillaToAttach);
            }
            modulo.setCamposPlantillaCollection(attachedCamposPlantillaCollection);
            em.persist(modulo);
            if (creadoPor != null) {
                creadoPor.getModuloCollection().add(modulo);
                creadoPor = em.merge(creadoPor);
            }
            if (modificadoPor != null) {
                modificadoPor.getModuloCollection().add(modulo);
                modificadoPor = em.merge(modificadoPor);
            }
            if (dependeDe != null) {
                dependeDe.getModuloCollection().add(modulo);
                dependeDe = em.merge(dependeDe);
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
            for (Modulo moduloCollectionModulo : modulo.getModuloCollection()) {
                Modulo oldDependeDeOfModuloCollectionModulo = moduloCollectionModulo.getDependeDe();
                moduloCollectionModulo.setDependeDe(modulo);
                moduloCollectionModulo = em.merge(moduloCollectionModulo);
                if (oldDependeDeOfModuloCollectionModulo != null) {
                    oldDependeDeOfModuloCollectionModulo.getModuloCollection().remove(moduloCollectionModulo);
                    oldDependeDeOfModuloCollectionModulo = em.merge(oldDependeDeOfModuloCollectionModulo);
                }
            }
            for (CamposPlantilla camposPlantillaCollectionCamposPlantilla : modulo.getCamposPlantillaCollection()) {
                Modulo oldModuloOfCamposPlantillaCollectionCamposPlantilla = camposPlantillaCollectionCamposPlantilla.getModulo();
                camposPlantillaCollectionCamposPlantilla.setModulo(modulo);
                camposPlantillaCollectionCamposPlantilla = em.merge(camposPlantillaCollectionCamposPlantilla);
                if (oldModuloOfCamposPlantillaCollectionCamposPlantilla != null) {
                    oldModuloOfCamposPlantillaCollectionCamposPlantilla.getCamposPlantillaCollection().remove(camposPlantillaCollectionCamposPlantilla);
                    oldModuloOfCamposPlantillaCollectionCamposPlantilla = em.merge(oldModuloOfCamposPlantillaCollectionCamposPlantilla);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Modulo modulo) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Modulo persistentModulo = em.find(Modulo.class, modulo.getId());
            Usuario creadoPorOld = persistentModulo.getCreadoPor();
            Usuario creadoPorNew = modulo.getCreadoPor();
            Usuario modificadoPorOld = persistentModulo.getModificadoPor();
            Usuario modificadoPorNew = modulo.getModificadoPor();
            Modulo dependeDeOld = persistentModulo.getDependeDe();
            Modulo dependeDeNew = modulo.getDependeDe();
            Collection<Acl> aclCollectionOld = persistentModulo.getAclCollection();
            Collection<Acl> aclCollectionNew = modulo.getAclCollection();
            Collection<Modulo> moduloCollectionOld = persistentModulo.getModuloCollection();
            Collection<Modulo> moduloCollectionNew = modulo.getModuloCollection();
            Collection<CamposPlantilla> camposPlantillaCollectionOld = persistentModulo.getCamposPlantillaCollection();
            Collection<CamposPlantilla> camposPlantillaCollectionNew = modulo.getCamposPlantillaCollection();
            List<String> illegalOrphanMessages = null;
            for (CamposPlantilla camposPlantillaCollectionOldCamposPlantilla : camposPlantillaCollectionOld) {
                if (!camposPlantillaCollectionNew.contains(camposPlantillaCollectionOldCamposPlantilla)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain CamposPlantilla " + camposPlantillaCollectionOldCamposPlantilla + " since its modulo field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (creadoPorNew != null) {
                creadoPorNew = em.getReference(creadoPorNew.getClass(), creadoPorNew.getId());
                modulo.setCreadoPor(creadoPorNew);
            }
            if (modificadoPorNew != null) {
                modificadoPorNew = em.getReference(modificadoPorNew.getClass(), modificadoPorNew.getId());
                modulo.setModificadoPor(modificadoPorNew);
            }
            if (dependeDeNew != null) {
                dependeDeNew = em.getReference(dependeDeNew.getClass(), dependeDeNew.getId());
                modulo.setDependeDe(dependeDeNew);
            }
            Collection<Acl> attachedAclCollectionNew = new ArrayList<Acl>();
            for (Acl aclCollectionNewAclToAttach : aclCollectionNew) {
                aclCollectionNewAclToAttach = em.getReference(aclCollectionNewAclToAttach.getClass(), aclCollectionNewAclToAttach.getId());
                attachedAclCollectionNew.add(aclCollectionNewAclToAttach);
            }
            aclCollectionNew = attachedAclCollectionNew;
            modulo.setAclCollection(aclCollectionNew);
            Collection<Modulo> attachedModuloCollectionNew = new ArrayList<Modulo>();
            for (Modulo moduloCollectionNewModuloToAttach : moduloCollectionNew) {
                moduloCollectionNewModuloToAttach = em.getReference(moduloCollectionNewModuloToAttach.getClass(), moduloCollectionNewModuloToAttach.getId());
                attachedModuloCollectionNew.add(moduloCollectionNewModuloToAttach);
            }
            moduloCollectionNew = attachedModuloCollectionNew;
            modulo.setModuloCollection(moduloCollectionNew);
            Collection<CamposPlantilla> attachedCamposPlantillaCollectionNew = new ArrayList<CamposPlantilla>();
            for (CamposPlantilla camposPlantillaCollectionNewCamposPlantillaToAttach : camposPlantillaCollectionNew) {
                camposPlantillaCollectionNewCamposPlantillaToAttach = em.getReference(camposPlantillaCollectionNewCamposPlantillaToAttach.getClass(), camposPlantillaCollectionNewCamposPlantillaToAttach.getId());
                attachedCamposPlantillaCollectionNew.add(camposPlantillaCollectionNewCamposPlantillaToAttach);
            }
            camposPlantillaCollectionNew = attachedCamposPlantillaCollectionNew;
            modulo.setCamposPlantillaCollection(camposPlantillaCollectionNew);
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
            if (dependeDeOld != null && !dependeDeOld.equals(dependeDeNew)) {
                dependeDeOld.getModuloCollection().remove(modulo);
                dependeDeOld = em.merge(dependeDeOld);
            }
            if (dependeDeNew != null && !dependeDeNew.equals(dependeDeOld)) {
                dependeDeNew.getModuloCollection().add(modulo);
                dependeDeNew = em.merge(dependeDeNew);
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
            for (Modulo moduloCollectionOldModulo : moduloCollectionOld) {
                if (!moduloCollectionNew.contains(moduloCollectionOldModulo)) {
                    moduloCollectionOldModulo.setDependeDe(null);
                    moduloCollectionOldModulo = em.merge(moduloCollectionOldModulo);
                }
            }
            for (Modulo moduloCollectionNewModulo : moduloCollectionNew) {
                if (!moduloCollectionOld.contains(moduloCollectionNewModulo)) {
                    Modulo oldDependeDeOfModuloCollectionNewModulo = moduloCollectionNewModulo.getDependeDe();
                    moduloCollectionNewModulo.setDependeDe(modulo);
                    moduloCollectionNewModulo = em.merge(moduloCollectionNewModulo);
                    if (oldDependeDeOfModuloCollectionNewModulo != null && !oldDependeDeOfModuloCollectionNewModulo.equals(modulo)) {
                        oldDependeDeOfModuloCollectionNewModulo.getModuloCollection().remove(moduloCollectionNewModulo);
                        oldDependeDeOfModuloCollectionNewModulo = em.merge(oldDependeDeOfModuloCollectionNewModulo);
                    }
                }
            }
            for (CamposPlantilla camposPlantillaCollectionNewCamposPlantilla : camposPlantillaCollectionNew) {
                if (!camposPlantillaCollectionOld.contains(camposPlantillaCollectionNewCamposPlantilla)) {
                    Modulo oldModuloOfCamposPlantillaCollectionNewCamposPlantilla = camposPlantillaCollectionNewCamposPlantilla.getModulo();
                    camposPlantillaCollectionNewCamposPlantilla.setModulo(modulo);
                    camposPlantillaCollectionNewCamposPlantilla = em.merge(camposPlantillaCollectionNewCamposPlantilla);
                    if (oldModuloOfCamposPlantillaCollectionNewCamposPlantilla != null && !oldModuloOfCamposPlantillaCollectionNewCamposPlantilla.equals(modulo)) {
                        oldModuloOfCamposPlantillaCollectionNewCamposPlantilla.getCamposPlantillaCollection().remove(camposPlantillaCollectionNewCamposPlantilla);
                        oldModuloOfCamposPlantillaCollectionNewCamposPlantilla = em.merge(oldModuloOfCamposPlantillaCollectionNewCamposPlantilla);
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

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
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
            List<String> illegalOrphanMessages = null;
            Collection<CamposPlantilla> camposPlantillaCollectionOrphanCheck = modulo.getCamposPlantillaCollection();
            for (CamposPlantilla camposPlantillaCollectionOrphanCheckCamposPlantilla : camposPlantillaCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Modulo (" + modulo + ") cannot be destroyed since the CamposPlantilla " + camposPlantillaCollectionOrphanCheckCamposPlantilla + " in its camposPlantillaCollection field has a non-nullable modulo field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
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
            Modulo dependeDe = modulo.getDependeDe();
            if (dependeDe != null) {
                dependeDe.getModuloCollection().remove(modulo);
                dependeDe = em.merge(dependeDe);
            }
            Collection<Acl> aclCollection = modulo.getAclCollection();
            for (Acl aclCollectionAcl : aclCollection) {
                aclCollectionAcl.setModulo(null);
                aclCollectionAcl = em.merge(aclCollectionAcl);
            }
            Collection<Modulo> moduloCollection = modulo.getModuloCollection();
            for (Modulo moduloCollectionModulo : moduloCollection) {
                moduloCollectionModulo.setDependeDe(null);
                moduloCollectionModulo = em.merge(moduloCollectionModulo);
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
