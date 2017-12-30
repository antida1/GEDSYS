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
import com.base16.gedsys.entities.Documento;
import com.base16.gedsys.entities.Prestamo;
import com.base16.gedsys.model.exceptions.NonexistentEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author rober
 */
public class PrestamoJpaController implements Serializable {

    public PrestamoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Prestamo prestamo) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario prestadoA = prestamo.getPrestadoA();
            if (prestadoA != null) {
                prestadoA = em.getReference(prestadoA.getClass(), prestadoA.getId());
                prestamo.setPrestadoA(prestadoA);
            }
            Usuario creadoPor = prestamo.getCreadoPor();
            if (creadoPor != null) {
                creadoPor = em.getReference(creadoPor.getClass(), creadoPor.getId());
                prestamo.setCreadoPor(creadoPor);
            }
            Usuario modificadoPor = prestamo.getModificadoPor();
            if (modificadoPor != null) {
                modificadoPor = em.getReference(modificadoPor.getClass(), modificadoPor.getId());
                prestamo.setModificadoPor(modificadoPor);
            }
//            Documento documento = prestamo.getDocumento();
//            if (documento != null) {
//                documento = em.getReference(documento.getClass(), documento.getId());
//                prestamo.setDocumento(documento);
//            }
            em.persist(prestamo);
            if (prestadoA != null) {
                prestadoA.getPrestamoList().add(prestamo);
                prestadoA = em.merge(prestadoA);
            }
            if (creadoPor != null) {
                creadoPor.getPrestamoList().add(prestamo);
                creadoPor = em.merge(creadoPor);
            }
            if (modificadoPor != null) {
                modificadoPor.getPrestamoList().add(prestamo);
                modificadoPor = em.merge(modificadoPor);
            }
//            if (documento != null) {
//                documento.getPrestamoList().add(prestamo);
//                documento = em.merge(documento);
//            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Prestamo prestamo) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Prestamo persistentPrestamo = em.find(Prestamo.class, prestamo.getId());
            Usuario prestadoAOld = persistentPrestamo.getPrestadoA();
            Usuario prestadoANew = prestamo.getPrestadoA();
            Usuario creadoPorOld = persistentPrestamo.getCreadoPor();
            Usuario creadoPorNew = prestamo.getCreadoPor();
            Usuario modificadoPorOld = persistentPrestamo.getModificadoPor();
            Usuario modificadoPorNew = prestamo.getModificadoPor();
//            Documento documentoOld = persistentPrestamo.getDocumento();
//            Documento documentoNew = prestamo.getDocumento();
            if (prestadoANew != null) {
                prestadoANew = em.getReference(prestadoANew.getClass(), prestadoANew.getId());
                prestamo.setPrestadoA(prestadoANew);
            }
            if (creadoPorNew != null) {
                creadoPorNew = em.getReference(creadoPorNew.getClass(), creadoPorNew.getId());
                prestamo.setCreadoPor(creadoPorNew);
            }
            if (modificadoPorNew != null) {
                modificadoPorNew = em.getReference(modificadoPorNew.getClass(), modificadoPorNew.getId());
                prestamo.setModificadoPor(modificadoPorNew);
            }
//            if (documentoNew != null) {
//                documentoNew = em.getReference(documentoNew.getClass(), documentoNew.getId());
//                prestamo.setDocumento(documentoNew);
//            }
            prestamo = em.merge(prestamo);
            if (prestadoAOld != null && !prestadoAOld.equals(prestadoANew)) {
                prestadoAOld.getPrestamoList().remove(prestamo);
                prestadoAOld = em.merge(prestadoAOld);
            }
            if (prestadoANew != null && !prestadoANew.equals(prestadoAOld)) {
                prestadoANew.getPrestamoList().add(prestamo);
                prestadoANew = em.merge(prestadoANew);
            }
            if (creadoPorOld != null && !creadoPorOld.equals(creadoPorNew)) {
                creadoPorOld.getPrestamoList().remove(prestamo);
                creadoPorOld = em.merge(creadoPorOld);
            }
            if (creadoPorNew != null && !creadoPorNew.equals(creadoPorOld)) {
                creadoPorNew.getPrestamoList().add(prestamo);
                creadoPorNew = em.merge(creadoPorNew);
            }
            if (modificadoPorOld != null && !modificadoPorOld.equals(modificadoPorNew)) {
                modificadoPorOld.getPrestamoList().remove(prestamo);
                modificadoPorOld = em.merge(modificadoPorOld);
            }
            if (modificadoPorNew != null && !modificadoPorNew.equals(modificadoPorOld)) {
                modificadoPorNew.getPrestamoList().add(prestamo);
                modificadoPorNew = em.merge(modificadoPorNew);
            }
//            if (documentoOld != null && !documentoOld.equals(documentoNew)) {
//                documentoOld.getPrestamoList().remove(prestamo);
//                documentoOld = em.merge(documentoOld);
//            }
//            if (documentoNew != null && !documentoNew.equals(documentoOld)) {
//                documentoNew.getPrestamoList().add(prestamo);
//                documentoNew = em.merge(documentoNew);
//            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = prestamo.getId();
                if (findPrestamo(id) == null) {
                    throw new NonexistentEntityException("The prestamo with id " + id + " no longer exists.");
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
            Prestamo prestamo;
            try {
                prestamo = em.getReference(Prestamo.class, id);
                prestamo.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The prestamo with id " + id + " no longer exists.", enfe);
            }
            Usuario prestadoA = prestamo.getPrestadoA();
            if (prestadoA != null) {
                prestadoA.getPrestamoList().remove(prestamo);
                prestadoA = em.merge(prestadoA);
            }
            Usuario creadoPor = prestamo.getCreadoPor();
            if (creadoPor != null) {
                creadoPor.getPrestamoList().remove(prestamo);
                creadoPor = em.merge(creadoPor);
            }
            Usuario modificadoPor = prestamo.getModificadoPor();
            if (modificadoPor != null) {
                modificadoPor.getPrestamoList().remove(prestamo);
                modificadoPor = em.merge(modificadoPor);
            }
//            Documento documento = prestamo.getDocumento();
//            if (documento != null) {
//                documento.getPrestamoList().remove(prestamo);
//                documento = em.merge(documento);
//            }
            em.remove(prestamo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Prestamo> findPrestamoEntities() {
        return findPrestamoEntities(true, -1, -1);
    }

    public List<Prestamo> findPrestamoEntities(int maxResults, int firstResult) {
        return findPrestamoEntities(false, maxResults, firstResult);
    }

    private List<Prestamo> findPrestamoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Prestamo.class));
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

    public Prestamo findPrestamo(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Prestamo.class, id);
        } finally {
            em.close();
        }
    }

    public int getPrestamoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Prestamo> rt = cq.from(Prestamo.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
