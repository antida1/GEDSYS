/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base16.gedsys.model;

import com.base16.gedsys.entities.Circular;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.base16.gedsys.entities.Usuario;
import com.base16.gedsys.entities.Circularcc;
import com.base16.gedsys.model.exceptions.IllegalOrphanException;
import com.base16.gedsys.model.exceptions.NonexistentEntityException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author rober
 */
public class CircularJpaController implements Serializable {

    public CircularJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Circular circular) {
        if (circular.getCircularccList() == null) {
            circular.setCircularccList(new ArrayList<Circularcc>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario remitente = circular.getRemitente();
            if (remitente != null) {
                remitente = em.getReference(remitente.getClass(), remitente.getId());
                circular.setRemitente(remitente);
            }
            Usuario creadoPor = circular.getCreadoPor();
            if (creadoPor != null) {
                creadoPor = em.getReference(creadoPor.getClass(), creadoPor.getId());
                circular.setCreadoPor(creadoPor);
            }
            Usuario modificadoPor = circular.getModificadoPor();
            if (modificadoPor != null) {
                modificadoPor = em.getReference(modificadoPor.getClass(), modificadoPor.getId());
                circular.setModificadoPor(modificadoPor);
            }
            List<Circularcc> attachedCircularccList = new ArrayList<Circularcc>();
            for (Circularcc circularccListCircularccToAttach : circular.getCircularccList()) {
                circularccListCircularccToAttach = em.getReference(circularccListCircularccToAttach.getClass(), circularccListCircularccToAttach.getId());
                attachedCircularccList.add(circularccListCircularccToAttach);
            }
            circular.setCircularccList(attachedCircularccList);
            em.persist(circular);
            if (remitente != null) {
                remitente.getCircularList().add(circular);
                remitente = em.merge(remitente);
            }
            if (creadoPor != null) {
                creadoPor.getCircularList().add(circular);
                creadoPor = em.merge(creadoPor);
            }
            if (modificadoPor != null) {
                modificadoPor.getCircularList().add(circular);
                modificadoPor = em.merge(modificadoPor);
            }
            for (Circularcc circularccListCircularcc : circular.getCircularccList()) {
                Circular oldCircularOfCircularccListCircularcc = circularccListCircularcc.getCircular();
                circularccListCircularcc.setCircular(circular);
                circularccListCircularcc = em.merge(circularccListCircularcc);
                if (oldCircularOfCircularccListCircularcc != null) {
                    oldCircularOfCircularccListCircularcc.getCircularccList().remove(circularccListCircularcc);
                    oldCircularOfCircularccListCircularcc = em.merge(oldCircularOfCircularccListCircularcc);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Circular circular) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Circular persistentCircular = em.find(Circular.class, circular.getId());
            Usuario remitenteOld = persistentCircular.getRemitente();
            Usuario remitenteNew = circular.getRemitente();
            Usuario creadoPorOld = persistentCircular.getCreadoPor();
            Usuario creadoPorNew = circular.getCreadoPor();
            Usuario modificadoPorOld = persistentCircular.getModificadoPor();
            Usuario modificadoPorNew = circular.getModificadoPor();
            List<Circularcc> circularccListOld = persistentCircular.getCircularccList();
            List<Circularcc> circularccListNew = circular.getCircularccList();
            List<String> illegalOrphanMessages = null;
            for (Circularcc circularccListOldCircularcc : circularccListOld) {
                if (!circularccListNew.contains(circularccListOldCircularcc)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Circularcc " + circularccListOldCircularcc + " since its circular field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (remitenteNew != null) {
                remitenteNew = em.getReference(remitenteNew.getClass(), remitenteNew.getId());
                circular.setRemitente(remitenteNew);
            }
            if (creadoPorNew != null) {
                creadoPorNew = em.getReference(creadoPorNew.getClass(), creadoPorNew.getId());
                circular.setCreadoPor(creadoPorNew);
            }
            if (modificadoPorNew != null) {
                modificadoPorNew = em.getReference(modificadoPorNew.getClass(), modificadoPorNew.getId());
                circular.setModificadoPor(modificadoPorNew);
            }
            List<Circularcc> attachedCircularccListNew = new ArrayList<Circularcc>();
            for (Circularcc circularccListNewCircularccToAttach : circularccListNew) {
                circularccListNewCircularccToAttach = em.getReference(circularccListNewCircularccToAttach.getClass(), circularccListNewCircularccToAttach.getId());
                attachedCircularccListNew.add(circularccListNewCircularccToAttach);
            }
            circularccListNew = attachedCircularccListNew;
            circular.setCircularccList(circularccListNew);
            circular = em.merge(circular);
            if (remitenteOld != null && !remitenteOld.equals(remitenteNew)) {
                remitenteOld.getCircularList().remove(circular);
                remitenteOld = em.merge(remitenteOld);
            }
            if (remitenteNew != null && !remitenteNew.equals(remitenteOld)) {
                remitenteNew.getCircularList().add(circular);
                remitenteNew = em.merge(remitenteNew);
            }
            if (creadoPorOld != null && !creadoPorOld.equals(creadoPorNew)) {
                creadoPorOld.getCircularList().remove(circular);
                creadoPorOld = em.merge(creadoPorOld);
            }
            if (creadoPorNew != null && !creadoPorNew.equals(creadoPorOld)) {
                creadoPorNew.getCircularList().add(circular);
                creadoPorNew = em.merge(creadoPorNew);
            }
            if (modificadoPorOld != null && !modificadoPorOld.equals(modificadoPorNew)) {
                modificadoPorOld.getCircularList().remove(circular);
                modificadoPorOld = em.merge(modificadoPorOld);
            }
            if (modificadoPorNew != null && !modificadoPorNew.equals(modificadoPorOld)) {
                modificadoPorNew.getCircularList().add(circular);
                modificadoPorNew = em.merge(modificadoPorNew);
            }
            for (Circularcc circularccListNewCircularcc : circularccListNew) {
                if (!circularccListOld.contains(circularccListNewCircularcc)) {
                    Circular oldCircularOfCircularccListNewCircularcc = circularccListNewCircularcc.getCircular();
                    circularccListNewCircularcc.setCircular(circular);
                    circularccListNewCircularcc = em.merge(circularccListNewCircularcc);
                    if (oldCircularOfCircularccListNewCircularcc != null && !oldCircularOfCircularccListNewCircularcc.equals(circular)) {
                        oldCircularOfCircularccListNewCircularcc.getCircularccList().remove(circularccListNewCircularcc);
                        oldCircularOfCircularccListNewCircularcc = em.merge(oldCircularOfCircularccListNewCircularcc);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = circular.getId();
                if (findCircular(id) == null) {
                    throw new NonexistentEntityException("The circular with id " + id + " no longer exists.");
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
            Circular circular;
            try {
                circular = em.getReference(Circular.class, id);
                circular.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The circular with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Circularcc> circularccListOrphanCheck = circular.getCircularccList();
            for (Circularcc circularccListOrphanCheckCircularcc : circularccListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Circular (" + circular + ") cannot be destroyed since the Circularcc " + circularccListOrphanCheckCircularcc + " in its circularccList field has a non-nullable circular field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Usuario remitente = circular.getRemitente();
            if (remitente != null) {
                remitente.getCircularList().remove(circular);
                remitente = em.merge(remitente);
            }
            Usuario creadoPor = circular.getCreadoPor();
            if (creadoPor != null) {
                creadoPor.getCircularList().remove(circular);
                creadoPor = em.merge(creadoPor);
            }
            Usuario modificadoPor = circular.getModificadoPor();
            if (modificadoPor != null) {
                modificadoPor.getCircularList().remove(circular);
                modificadoPor = em.merge(modificadoPor);
            }
            em.remove(circular);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    
    public List<Circular> findByEstadoYUsuario(int estado, Usuario usuario) {
       EntityManager em = getEntityManager();
       try {
           CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
           cq.select(cq.from(Circular.class));
            Query q = em.createNamedQuery("Circular.findByEstadoYUsuario",Integer.class)
                    .setParameter("estado", estado).setParameter("usuario", usuario);           
            return q.getResultList();
        } finally {
            em.close();
        }
   }

    public List<Circular> findCircularEntities() {
        return findCircularEntities(true, -1, -1);
    }

    public List<Circular> findCircularEntities(int maxResults, int firstResult) {
        return findCircularEntities(false, maxResults, firstResult);
    }

    private List<Circular> findCircularEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Circular.class));
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

    public Circular findCircular(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Circular.class, id);
        } finally {
            em.close();
        }
    }

    public int getCircularCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Circular> rt = cq.from(Circular.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
