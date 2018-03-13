/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base16.gedsys.model;

import com.base16.gedsys.entities.Comunicacion;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.base16.gedsys.entities.Usuario;
import com.base16.gedsys.entities.Comunicacioncc;
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
public class ComunicacionJpaController implements Serializable {

    public ComunicacionJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Comunicacion comunicacion) {
        if (comunicacion.getComunicacionccList() == null) {
            comunicacion.setComunicacionccList(new ArrayList<Comunicacioncc>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario destinatario = comunicacion.getDestinatario();
            if (destinatario != null) {
                destinatario = em.getReference(destinatario.getClass(), destinatario.getId());
                comunicacion.setDestinatario(destinatario);
            }
            Usuario remitente = comunicacion.getRemitente();
            if (remitente != null) {
                remitente = em.getReference(remitente.getClass(), remitente.getId());
                comunicacion.setRemitente(remitente);
            }
            Usuario creadoPor = comunicacion.getCreadoPor();
            if (creadoPor != null) {
                creadoPor = em.getReference(creadoPor.getClass(), creadoPor.getId());
                comunicacion.setCreadoPor(creadoPor);
            }
            Usuario modificadoPor = comunicacion.getModificadoPor();
            if (modificadoPor != null) {
                modificadoPor = em.getReference(modificadoPor.getClass(), modificadoPor.getId());
                comunicacion.setModificadoPor(modificadoPor);
            }
            List<Comunicacioncc> attachedComunicacionccList = new ArrayList<Comunicacioncc>();
            for (Comunicacioncc comunicacionccListComunicacionccToAttach : comunicacion.getComunicacionccList()) {
                comunicacionccListComunicacionccToAttach = em.getReference(comunicacionccListComunicacionccToAttach.getClass(), comunicacionccListComunicacionccToAttach.getId());
                attachedComunicacionccList.add(comunicacionccListComunicacionccToAttach);
            }
            comunicacion.setComunicacionccList(attachedComunicacionccList);
            em.persist(comunicacion);
            if (destinatario != null) {
                destinatario.getComunicacionList().add(comunicacion);
                destinatario = em.merge(destinatario);
            }
            if (remitente != null) {
                remitente.getComunicacionList().add(comunicacion);
                remitente = em.merge(remitente);
            }
            if (creadoPor != null) {
                creadoPor.getComunicacionList().add(comunicacion);
                creadoPor = em.merge(creadoPor);
            }
            if (modificadoPor != null) {
                modificadoPor.getComunicacionList().add(comunicacion);
                modificadoPor = em.merge(modificadoPor);
            }
            for (Comunicacioncc comunicacionccListComunicacioncc : comunicacion.getComunicacionccList()) {
                Comunicacion oldComunicacionOfComunicacionccListComunicacioncc = comunicacionccListComunicacioncc.getComunicacion();
                comunicacionccListComunicacioncc.setComunicacion(comunicacion);
                comunicacionccListComunicacioncc = em.merge(comunicacionccListComunicacioncc);
                if (oldComunicacionOfComunicacionccListComunicacioncc != null) {
                    oldComunicacionOfComunicacionccListComunicacioncc.getComunicacionccList().remove(comunicacionccListComunicacioncc);
                    oldComunicacionOfComunicacionccListComunicacioncc = em.merge(oldComunicacionOfComunicacionccListComunicacioncc);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Comunicacion comunicacion) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Comunicacion persistentComunicacion = em.find(Comunicacion.class, comunicacion.getId());
            Usuario destinatarioOld = persistentComunicacion.getDestinatario();
            Usuario destinatarioNew = comunicacion.getDestinatario();
            Usuario remitenteOld = persistentComunicacion.getRemitente();
            Usuario remitenteNew = comunicacion.getRemitente();
            Usuario creadoPorOld = persistentComunicacion.getCreadoPor();
            Usuario creadoPorNew = comunicacion.getCreadoPor();
            Usuario modificadoPorOld = persistentComunicacion.getModificadoPor();
            Usuario modificadoPorNew = comunicacion.getModificadoPor();
            List<Comunicacioncc> comunicacionccListOld = persistentComunicacion.getComunicacionccList();
            List<Comunicacioncc> comunicacionccListNew = comunicacion.getComunicacionccList();
            List<String> illegalOrphanMessages = null;
            for (Comunicacioncc comunicacionccListOldComunicacioncc : comunicacionccListOld) {
                if (!comunicacionccListNew.contains(comunicacionccListOldComunicacioncc)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Comunicacioncc " + comunicacionccListOldComunicacioncc + " since its comunicacion field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (destinatarioNew != null) {
                destinatarioNew = em.getReference(destinatarioNew.getClass(), destinatarioNew.getId());
                comunicacion.setDestinatario(destinatarioNew);
            }
            if (remitenteNew != null) {
                remitenteNew = em.getReference(remitenteNew.getClass(), remitenteNew.getId());
                comunicacion.setRemitente(remitenteNew);
            }
            if (creadoPorNew != null) {
                creadoPorNew = em.getReference(creadoPorNew.getClass(), creadoPorNew.getId());
                comunicacion.setCreadoPor(creadoPorNew);
            }
            if (modificadoPorNew != null) {
                modificadoPorNew = em.getReference(modificadoPorNew.getClass(), modificadoPorNew.getId());
                comunicacion.setModificadoPor(modificadoPorNew);
            }
            List<Comunicacioncc> attachedComunicacionccListNew = new ArrayList<Comunicacioncc>();
            for (Comunicacioncc comunicacionccListNewComunicacionccToAttach : comunicacionccListNew) {
                comunicacionccListNewComunicacionccToAttach = em.getReference(comunicacionccListNewComunicacionccToAttach.getClass(), comunicacionccListNewComunicacionccToAttach.getId());
                attachedComunicacionccListNew.add(comunicacionccListNewComunicacionccToAttach);
            }
            comunicacionccListNew = attachedComunicacionccListNew;
            comunicacion.setComunicacionccList(comunicacionccListNew);
            comunicacion = em.merge(comunicacion);
            if (destinatarioOld != null && !destinatarioOld.equals(destinatarioNew)) {
                destinatarioOld.getComunicacionList().remove(comunicacion);
                destinatarioOld = em.merge(destinatarioOld);
            }
            if (destinatarioNew != null && !destinatarioNew.equals(destinatarioOld)) {
                destinatarioNew.getComunicacionList().add(comunicacion);
                destinatarioNew = em.merge(destinatarioNew);
            }
            if (remitenteOld != null && !remitenteOld.equals(remitenteNew)) {
                remitenteOld.getComunicacionList().remove(comunicacion);
                remitenteOld = em.merge(remitenteOld);
            }
            if (remitenteNew != null && !remitenteNew.equals(remitenteOld)) {
                remitenteNew.getComunicacionList().add(comunicacion);
                remitenteNew = em.merge(remitenteNew);
            }
            if (creadoPorOld != null && !creadoPorOld.equals(creadoPorNew)) {
                creadoPorOld.getComunicacionList().remove(comunicacion);
                creadoPorOld = em.merge(creadoPorOld);
            }
            if (creadoPorNew != null && !creadoPorNew.equals(creadoPorOld)) {
                creadoPorNew.getComunicacionList().add(comunicacion);
                creadoPorNew = em.merge(creadoPorNew);
            }
            if (modificadoPorOld != null && !modificadoPorOld.equals(modificadoPorNew)) {
                modificadoPorOld.getComunicacionList().remove(comunicacion);
                modificadoPorOld = em.merge(modificadoPorOld);
            }
            if (modificadoPorNew != null && !modificadoPorNew.equals(modificadoPorOld)) {
                modificadoPorNew.getComunicacionList().add(comunicacion);
                modificadoPorNew = em.merge(modificadoPorNew);
            }
            for (Comunicacioncc comunicacionccListNewComunicacioncc : comunicacionccListNew) {
                if (!comunicacionccListOld.contains(comunicacionccListNewComunicacioncc)) {
                    Comunicacion oldComunicacionOfComunicacionccListNewComunicacioncc = comunicacionccListNewComunicacioncc.getComunicacion();
                    comunicacionccListNewComunicacioncc.setComunicacion(comunicacion);
                    comunicacionccListNewComunicacioncc = em.merge(comunicacionccListNewComunicacioncc);
                    if (oldComunicacionOfComunicacionccListNewComunicacioncc != null && !oldComunicacionOfComunicacionccListNewComunicacioncc.equals(comunicacion)) {
                        oldComunicacionOfComunicacionccListNewComunicacioncc.getComunicacionccList().remove(comunicacionccListNewComunicacioncc);
                        oldComunicacionOfComunicacionccListNewComunicacioncc = em.merge(oldComunicacionOfComunicacionccListNewComunicacioncc);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = comunicacion.getId();
                if (findComunicacion(id) == null) {
                    throw new NonexistentEntityException("The comunicacion with id " + id + " no longer exists.");
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
            Comunicacion comunicacion;
            try {
                comunicacion = em.getReference(Comunicacion.class, id);
                comunicacion.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The comunicacion with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Comunicacioncc> comunicacionccListOrphanCheck = comunicacion.getComunicacionccList();
            for (Comunicacioncc comunicacionccListOrphanCheckComunicacioncc : comunicacionccListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Comunicacion (" + comunicacion + ") cannot be destroyed since the Comunicacioncc " + comunicacionccListOrphanCheckComunicacioncc + " in its comunicacionccList field has a non-nullable comunicacion field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Usuario destinatario = comunicacion.getDestinatario();
            if (destinatario != null) {
                destinatario.getComunicacionList().remove(comunicacion);
                destinatario = em.merge(destinatario);
            }
            Usuario remitente = comunicacion.getRemitente();
            if (remitente != null) {
                remitente.getComunicacionList().remove(comunicacion);
                remitente = em.merge(remitente);
            }
            Usuario creadoPor = comunicacion.getCreadoPor();
            if (creadoPor != null) {
                creadoPor.getComunicacionList().remove(comunicacion);
                creadoPor = em.merge(creadoPor);
            }
            Usuario modificadoPor = comunicacion.getModificadoPor();
            if (modificadoPor != null) {
                modificadoPor.getComunicacionList().remove(comunicacion);
                modificadoPor = em.merge(modificadoPor);
            }
            em.remove(comunicacion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    
    public List<Comunicacion> findByEstadoYUsuario(String estado, Usuario usuario) {
       EntityManager em = getEntityManager();
       try {
           CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
           cq.select(cq.from(Comunicacion.class));
            Query q = em.createNamedQuery("Comunicacion.findByEstadoYUsuario",Integer.class)
                    .setParameter("estado", estado).setParameter("usuario", usuario);           
            return q.getResultList();
        } finally {
            em.close();
        }
   }
    
    public List<Comunicacion> findComunicacionEntities() {
        return findComunicacionEntities(true, -1, -1);
    }

    public List<Comunicacion> findComunicacionEntities(int maxResults, int firstResult) {
        return findComunicacionEntities(false, maxResults, firstResult);
    }

    private List<Comunicacion> findComunicacionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Comunicacion.class));
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

    public Comunicacion findComunicacion(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Comunicacion.class, id);
        } finally {
            em.close();
        }
    }

    public int getComunicacionCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Comunicacion> rt = cq.from(Comunicacion.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
