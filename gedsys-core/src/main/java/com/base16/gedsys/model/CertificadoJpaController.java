/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base16.gedsys.model;

import com.base16.gedsys.entities.Certificado;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.base16.gedsys.entities.Usuario;
import com.base16.gedsys.model.exceptions.NonexistentEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author rober
 */
public class CertificadoJpaController implements Serializable {

    public CertificadoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Certificado certificado) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario remitente = certificado.getRemitente();
            if (remitente != null) {
                remitente = em.getReference(remitente.getClass(), remitente.getId());
                certificado.setRemitente(remitente);
            }
            Usuario creadoPor = certificado.getCreadoPor();
            if (creadoPor != null) {
                creadoPor = em.getReference(creadoPor.getClass(), creadoPor.getId());
                certificado.setCreadoPor(creadoPor);
            }
            Usuario modificadoPor = certificado.getModificadoPor();
            if (modificadoPor != null) {
                modificadoPor = em.getReference(modificadoPor.getClass(), modificadoPor.getId());
                certificado.setModificadoPor(modificadoPor);
            }
            em.persist(certificado);
            if (remitente != null) {
                remitente.getCertificadoList().add(certificado);
                remitente = em.merge(remitente);
            }
            if (creadoPor != null) {
                creadoPor.getCertificadoList().add(certificado);
                creadoPor = em.merge(creadoPor);
            }
            if (modificadoPor != null) {
                modificadoPor.getCertificadoList().add(certificado);
                modificadoPor = em.merge(modificadoPor);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Certificado certificado) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Certificado persistentCertificado = em.find(Certificado.class, certificado.getId());
            Usuario remitenteOld = persistentCertificado.getRemitente();
            Usuario remitenteNew = certificado.getRemitente();
            Usuario creadoPorOld = persistentCertificado.getCreadoPor();
            Usuario creadoPorNew = certificado.getCreadoPor();
            Usuario modificadoPorOld = persistentCertificado.getModificadoPor();
            Usuario modificadoPorNew = certificado.getModificadoPor();
            if (remitenteNew != null) {
                remitenteNew = em.getReference(remitenteNew.getClass(), remitenteNew.getId());
                certificado.setRemitente(remitenteNew);
            }
            if (creadoPorNew != null) {
                creadoPorNew = em.getReference(creadoPorNew.getClass(), creadoPorNew.getId());
                certificado.setCreadoPor(creadoPorNew);
            }
            if (modificadoPorNew != null) {
                modificadoPorNew = em.getReference(modificadoPorNew.getClass(), modificadoPorNew.getId());
                certificado.setModificadoPor(modificadoPorNew);
            }
            certificado = em.merge(certificado);
            if (remitenteOld != null && !remitenteOld.equals(remitenteNew)) {
                remitenteOld.getCertificadoList().remove(certificado);
                remitenteOld = em.merge(remitenteOld);
            }
            if (remitenteNew != null && !remitenteNew.equals(remitenteOld)) {
                remitenteNew.getCertificadoList().add(certificado);
                remitenteNew = em.merge(remitenteNew);
            }
            if (creadoPorOld != null && !creadoPorOld.equals(creadoPorNew)) {
                creadoPorOld.getCertificadoList().remove(certificado);
                creadoPorOld = em.merge(creadoPorOld);
            }
            if (creadoPorNew != null && !creadoPorNew.equals(creadoPorOld)) {
                creadoPorNew.getCertificadoList().add(certificado);
                creadoPorNew = em.merge(creadoPorNew);
            }
            if (modificadoPorOld != null && !modificadoPorOld.equals(modificadoPorNew)) {
                modificadoPorOld.getCertificadoList().remove(certificado);
                modificadoPorOld = em.merge(modificadoPorOld);
            }
            if (modificadoPorNew != null && !modificadoPorNew.equals(modificadoPorOld)) {
                modificadoPorNew.getCertificadoList().add(certificado);
                modificadoPorNew = em.merge(modificadoPorNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = certificado.getId();
                if (findCertificado(id) == null) {
                    throw new NonexistentEntityException("The certificado with id " + id + " no longer exists.");
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
            Certificado certificado;
            try {
                certificado = em.getReference(Certificado.class, id);
                certificado.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The certificado with id " + id + " no longer exists.", enfe);
            }
            Usuario remitente = certificado.getRemitente();
            if (remitente != null) {
                remitente.getCertificadoList().remove(certificado);
                remitente = em.merge(remitente);
            }
            Usuario creadoPor = certificado.getCreadoPor();
            if (creadoPor != null) {
                creadoPor.getCertificadoList().remove(certificado);
                creadoPor = em.merge(creadoPor);
            }
            Usuario modificadoPor = certificado.getModificadoPor();
            if (modificadoPor != null) {
                modificadoPor.getCertificadoList().remove(certificado);
                modificadoPor = em.merge(modificadoPor);
            }
            em.remove(certificado);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Certificado> findCertificadoEntities() {
        return findCertificadoEntities(true, -1, -1);
    }

    public List<Certificado> findCertificadoEntities(int maxResults, int firstResult) {
        return findCertificadoEntities(false, maxResults, firstResult);
    }

    private List<Certificado> findCertificadoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Certificado.class));
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

    public Certificado findCertificado(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Certificado.class, id);
        } finally {
            em.close();
        }
    }

    public int getCertificadoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Certificado> rt = cq.from(Certificado.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
