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
import com.base16.gedsys.entities.PlanillaEnvio;
import com.base16.gedsys.entities.PlanillaEnvioDocumento;
import com.base16.gedsys.model.exceptions.NonexistentEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author robert
 */
public class PlanillaEnvioDocumentoJpaController implements Serializable {

    public PlanillaEnvioDocumentoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(PlanillaEnvioDocumento planillaEnvioDocumento) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario modificadoPor = planillaEnvioDocumento.getModificadoPor();
            if (modificadoPor != null) {
                modificadoPor = em.getReference(modificadoPor.getClass(), modificadoPor.getId());
                planillaEnvioDocumento.setModificadoPor(modificadoPor);
            }
            Documento documento = planillaEnvioDocumento.getDocumento();
            if (documento != null) {
                documento = em.getReference(documento.getClass(), documento.getId());
                planillaEnvioDocumento.setDocumento(documento);
            }
            PlanillaEnvio planillaEnvio = planillaEnvioDocumento.getPlanillaEnvio();
            if (planillaEnvio != null) {
                planillaEnvio = em.getReference(planillaEnvio.getClass(), planillaEnvio.getId());
                planillaEnvioDocumento.setPlanillaEnvio(planillaEnvio);
            }
            Usuario creadoPor = planillaEnvioDocumento.getCreadoPor();
            if (creadoPor != null) {
                creadoPor = em.getReference(creadoPor.getClass(), creadoPor.getId());
                planillaEnvioDocumento.setCreadoPor(creadoPor);
            }
            em.persist(planillaEnvioDocumento);
            if (modificadoPor != null) {
                modificadoPor.getPlanillaEnvioDocumentoList().add(planillaEnvioDocumento);
                modificadoPor = em.merge(modificadoPor);
            }
            if (documento != null) {
                documento.getPlanillaEnvioDocumentoList().add(planillaEnvioDocumento);
                documento = em.merge(documento);
            }
            if (planillaEnvio != null) {
                planillaEnvio.getPlanillaEnvioDocumentoList().add(planillaEnvioDocumento);
                planillaEnvio = em.merge(planillaEnvio);
            }
            if (creadoPor != null) {
                creadoPor.getPlanillaEnvioDocumentoList().add(planillaEnvioDocumento);
                creadoPor = em.merge(creadoPor);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(PlanillaEnvioDocumento planillaEnvioDocumento) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            PlanillaEnvioDocumento persistentPlanillaEnvioDocumento = em.find(PlanillaEnvioDocumento.class, planillaEnvioDocumento.getId());
            Usuario modificadoPorOld = persistentPlanillaEnvioDocumento.getModificadoPor();
            Usuario modificadoPorNew = planillaEnvioDocumento.getModificadoPor();
            Documento documentoOld = persistentPlanillaEnvioDocumento.getDocumento();
            Documento documentoNew = planillaEnvioDocumento.getDocumento();
            PlanillaEnvio planillaEnvioOld = persistentPlanillaEnvioDocumento.getPlanillaEnvio();
            PlanillaEnvio planillaEnvioNew = planillaEnvioDocumento.getPlanillaEnvio();
            Usuario creadoPorOld = persistentPlanillaEnvioDocumento.getCreadoPor();
            Usuario creadoPorNew = planillaEnvioDocumento.getCreadoPor();
            if (modificadoPorNew != null) {
                modificadoPorNew = em.getReference(modificadoPorNew.getClass(), modificadoPorNew.getId());
                planillaEnvioDocumento.setModificadoPor(modificadoPorNew);
            }
            if (documentoNew != null) {
                documentoNew = em.getReference(documentoNew.getClass(), documentoNew.getId());
                planillaEnvioDocumento.setDocumento(documentoNew);
            }
            if (planillaEnvioNew != null) {
                planillaEnvioNew = em.getReference(planillaEnvioNew.getClass(), planillaEnvioNew.getId());
                planillaEnvioDocumento.setPlanillaEnvio(planillaEnvioNew);
            }
            if (creadoPorNew != null) {
                creadoPorNew = em.getReference(creadoPorNew.getClass(), creadoPorNew.getId());
                planillaEnvioDocumento.setCreadoPor(creadoPorNew);
            }
            planillaEnvioDocumento = em.merge(planillaEnvioDocumento);
            if (modificadoPorOld != null && !modificadoPorOld.equals(modificadoPorNew)) {
                modificadoPorOld.getPlanillaEnvioDocumentoList().remove(planillaEnvioDocumento);
                modificadoPorOld = em.merge(modificadoPorOld);
            }
            if (modificadoPorNew != null && !modificadoPorNew.equals(modificadoPorOld)) {
                modificadoPorNew.getPlanillaEnvioDocumentoList().add(planillaEnvioDocumento);
                modificadoPorNew = em.merge(modificadoPorNew);
            }
            if (documentoOld != null && !documentoOld.equals(documentoNew)) {
                documentoOld.getPlanillaEnvioDocumentoList().remove(planillaEnvioDocumento);
                documentoOld = em.merge(documentoOld);
            }
            if (documentoNew != null && !documentoNew.equals(documentoOld)) {
                documentoNew.getPlanillaEnvioDocumentoList().add(planillaEnvioDocumento);
                documentoNew = em.merge(documentoNew);
            }
            if (planillaEnvioOld != null && !planillaEnvioOld.equals(planillaEnvioNew)) {
                planillaEnvioOld.getPlanillaEnvioDocumentoList().remove(planillaEnvioDocumento);
                planillaEnvioOld = em.merge(planillaEnvioOld);
            }
            if (planillaEnvioNew != null && !planillaEnvioNew.equals(planillaEnvioOld)) {
                planillaEnvioNew.getPlanillaEnvioDocumentoList().add(planillaEnvioDocumento);
                planillaEnvioNew = em.merge(planillaEnvioNew);
            }
            if (creadoPorOld != null && !creadoPorOld.equals(creadoPorNew)) {
                creadoPorOld.getPlanillaEnvioDocumentoList().remove(planillaEnvioDocumento);
                creadoPorOld = em.merge(creadoPorOld);
            }
            if (creadoPorNew != null && !creadoPorNew.equals(creadoPorOld)) {
                creadoPorNew.getPlanillaEnvioDocumentoList().add(planillaEnvioDocumento);
                creadoPorNew = em.merge(creadoPorNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = planillaEnvioDocumento.getId();
                if (findPlanillaEnvioDocumento(id) == null) {
                    throw new NonexistentEntityException("The planillaEnvioDocumento with id " + id + " no longer exists.");
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
            PlanillaEnvioDocumento planillaEnvioDocumento;
            try {
                planillaEnvioDocumento = em.getReference(PlanillaEnvioDocumento.class, id);
                planillaEnvioDocumento.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The planillaEnvioDocumento with id " + id + " no longer exists.", enfe);
            }
            Usuario modificadoPor = planillaEnvioDocumento.getModificadoPor();
            if (modificadoPor != null) {
                modificadoPor.getPlanillaEnvioDocumentoList().remove(planillaEnvioDocumento);
                modificadoPor = em.merge(modificadoPor);
            }
            Documento documento = planillaEnvioDocumento.getDocumento();
            if (documento != null) {
                documento.getPlanillaEnvioDocumentoList().remove(planillaEnvioDocumento);
                documento = em.merge(documento);
            }
            PlanillaEnvio planillaEnvio = planillaEnvioDocumento.getPlanillaEnvio();
            if (planillaEnvio != null) {
                planillaEnvio.getPlanillaEnvioDocumentoList().remove(planillaEnvioDocumento);
                planillaEnvio = em.merge(planillaEnvio);
            }
            Usuario creadoPor = planillaEnvioDocumento.getCreadoPor();
            if (creadoPor != null) {
                creadoPor.getPlanillaEnvioDocumentoList().remove(planillaEnvioDocumento);
                creadoPor = em.merge(creadoPor);
            }
            em.remove(planillaEnvioDocumento);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<PlanillaEnvioDocumento> findPlanillaEnvioDocumentoEntities() {
        return findPlanillaEnvioDocumentoEntities(true, -1, -1);
    }

    public List<PlanillaEnvioDocumento> findPlanillaEnvioDocumentoEntities(int maxResults, int firstResult) {
        return findPlanillaEnvioDocumentoEntities(false, maxResults, firstResult);
    }

    private List<PlanillaEnvioDocumento> findPlanillaEnvioDocumentoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(PlanillaEnvioDocumento.class));
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

    public PlanillaEnvioDocumento findPlanillaEnvioDocumento(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(PlanillaEnvioDocumento.class, id);
        } finally {
            em.close();
        }
    }

    public int getPlanillaEnvioDocumentoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<PlanillaEnvioDocumento> rt = cq.from(PlanillaEnvioDocumento.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
