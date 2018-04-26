/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base16.gedsys.model;

import com.base16.gedsys.entities.PlanillaEnvio;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.base16.gedsys.entities.Usuario;
import com.base16.gedsys.entities.PlanillaEnvioDocumento;
import com.base16.gedsys.model.exceptions.NonexistentEntityException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author robert
 */
public class PlanillaEnvioJpaController implements Serializable {

    public PlanillaEnvioJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(PlanillaEnvio planillaEnvio) {
        if (planillaEnvio.getPlanillaEnvioDocumentoList() == null) {
            planillaEnvio.setPlanillaEnvioDocumentoList(new ArrayList<PlanillaEnvioDocumento>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario creadoPor = planillaEnvio.getCreadoPor();
            if (creadoPor != null) {
                creadoPor = em.getReference(creadoPor.getClass(), creadoPor.getId());
                planillaEnvio.setCreadoPor(creadoPor);
            }
            Usuario modificadoPor = planillaEnvio.getModificadoPor();
            if (modificadoPor != null) {
                modificadoPor = em.getReference(modificadoPor.getClass(), modificadoPor.getId());
                planillaEnvio.setModificadoPor(modificadoPor);
            }
            List<PlanillaEnvioDocumento> attachedPlanillaEnvioDocumentoList = new ArrayList<PlanillaEnvioDocumento>();
            for (PlanillaEnvioDocumento planillaEnvioDocumentoListPlanillaEnvioDocumentoToAttach : planillaEnvio.getPlanillaEnvioDocumentoList()) {
                planillaEnvioDocumentoListPlanillaEnvioDocumentoToAttach = em.getReference(planillaEnvioDocumentoListPlanillaEnvioDocumentoToAttach.getClass(), planillaEnvioDocumentoListPlanillaEnvioDocumentoToAttach.getId());
                attachedPlanillaEnvioDocumentoList.add(planillaEnvioDocumentoListPlanillaEnvioDocumentoToAttach);
            }
            planillaEnvio.setPlanillaEnvioDocumentoList(attachedPlanillaEnvioDocumentoList);
            em.persist(planillaEnvio);
            if (creadoPor != null) {
                creadoPor.getPlanillaEnvioList().add(planillaEnvio);
                creadoPor = em.merge(creadoPor);
            }
            if (modificadoPor != null) {
                modificadoPor.getPlanillaEnvioList().add(planillaEnvio);
                modificadoPor = em.merge(modificadoPor);
            }
            for (PlanillaEnvioDocumento planillaEnvioDocumentoListPlanillaEnvioDocumento : planillaEnvio.getPlanillaEnvioDocumentoList()) {
                PlanillaEnvio oldPlanillaEnvioOfPlanillaEnvioDocumentoListPlanillaEnvioDocumento = planillaEnvioDocumentoListPlanillaEnvioDocumento.getPlanillaEnvio();
                planillaEnvioDocumentoListPlanillaEnvioDocumento.setPlanillaEnvio(planillaEnvio);
                planillaEnvioDocumentoListPlanillaEnvioDocumento = em.merge(planillaEnvioDocumentoListPlanillaEnvioDocumento);
                if (oldPlanillaEnvioOfPlanillaEnvioDocumentoListPlanillaEnvioDocumento != null) {
                    oldPlanillaEnvioOfPlanillaEnvioDocumentoListPlanillaEnvioDocumento.getPlanillaEnvioDocumentoList().remove(planillaEnvioDocumentoListPlanillaEnvioDocumento);
                    oldPlanillaEnvioOfPlanillaEnvioDocumentoListPlanillaEnvioDocumento = em.merge(oldPlanillaEnvioOfPlanillaEnvioDocumentoListPlanillaEnvioDocumento);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(PlanillaEnvio planillaEnvio) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            PlanillaEnvio persistentPlanillaEnvio = em.find(PlanillaEnvio.class, planillaEnvio.getId());
            Usuario creadoPorOld = persistentPlanillaEnvio.getCreadoPor();
            Usuario creadoPorNew = planillaEnvio.getCreadoPor();
            Usuario modificadoPorOld = persistentPlanillaEnvio.getModificadoPor();
            Usuario modificadoPorNew = planillaEnvio.getModificadoPor();
            List<PlanillaEnvioDocumento> planillaEnvioDocumentoListOld = persistentPlanillaEnvio.getPlanillaEnvioDocumentoList();
            List<PlanillaEnvioDocumento> planillaEnvioDocumentoListNew = planillaEnvio.getPlanillaEnvioDocumentoList();
            if (creadoPorNew != null) {
                creadoPorNew = em.getReference(creadoPorNew.getClass(), creadoPorNew.getId());
                planillaEnvio.setCreadoPor(creadoPorNew);
            }
            if (modificadoPorNew != null) {
                modificadoPorNew = em.getReference(modificadoPorNew.getClass(), modificadoPorNew.getId());
                planillaEnvio.setModificadoPor(modificadoPorNew);
            }
            List<PlanillaEnvioDocumento> attachedPlanillaEnvioDocumentoListNew = new ArrayList<PlanillaEnvioDocumento>();
            for (PlanillaEnvioDocumento planillaEnvioDocumentoListNewPlanillaEnvioDocumentoToAttach : planillaEnvioDocumentoListNew) {
                planillaEnvioDocumentoListNewPlanillaEnvioDocumentoToAttach = em.getReference(planillaEnvioDocumentoListNewPlanillaEnvioDocumentoToAttach.getClass(), planillaEnvioDocumentoListNewPlanillaEnvioDocumentoToAttach.getId());
                attachedPlanillaEnvioDocumentoListNew.add(planillaEnvioDocumentoListNewPlanillaEnvioDocumentoToAttach);
            }
            planillaEnvioDocumentoListNew = attachedPlanillaEnvioDocumentoListNew;
            planillaEnvio.setPlanillaEnvioDocumentoList(planillaEnvioDocumentoListNew);
            planillaEnvio = em.merge(planillaEnvio);
            if (creadoPorOld != null && !creadoPorOld.equals(creadoPorNew)) {
                creadoPorOld.getPlanillaEnvioList().remove(planillaEnvio);
                creadoPorOld = em.merge(creadoPorOld);
            }
            if (creadoPorNew != null && !creadoPorNew.equals(creadoPorOld)) {
                creadoPorNew.getPlanillaEnvioList().add(planillaEnvio);
                creadoPorNew = em.merge(creadoPorNew);
            }
            if (modificadoPorOld != null && !modificadoPorOld.equals(modificadoPorNew)) {
                modificadoPorOld.getPlanillaEnvioList().remove(planillaEnvio);
                modificadoPorOld = em.merge(modificadoPorOld);
            }
            if (modificadoPorNew != null && !modificadoPorNew.equals(modificadoPorOld)) {
                modificadoPorNew.getPlanillaEnvioList().add(planillaEnvio);
                modificadoPorNew = em.merge(modificadoPorNew);
            }
            for (PlanillaEnvioDocumento planillaEnvioDocumentoListOldPlanillaEnvioDocumento : planillaEnvioDocumentoListOld) {
                if (!planillaEnvioDocumentoListNew.contains(planillaEnvioDocumentoListOldPlanillaEnvioDocumento)) {
                    planillaEnvioDocumentoListOldPlanillaEnvioDocumento.setPlanillaEnvio(null);
                    planillaEnvioDocumentoListOldPlanillaEnvioDocumento = em.merge(planillaEnvioDocumentoListOldPlanillaEnvioDocumento);
                }
            }
            for (PlanillaEnvioDocumento planillaEnvioDocumentoListNewPlanillaEnvioDocumento : planillaEnvioDocumentoListNew) {
                if (!planillaEnvioDocumentoListOld.contains(planillaEnvioDocumentoListNewPlanillaEnvioDocumento)) {
                    PlanillaEnvio oldPlanillaEnvioOfPlanillaEnvioDocumentoListNewPlanillaEnvioDocumento = planillaEnvioDocumentoListNewPlanillaEnvioDocumento.getPlanillaEnvio();
                    planillaEnvioDocumentoListNewPlanillaEnvioDocumento.setPlanillaEnvio(planillaEnvio);
                    planillaEnvioDocumentoListNewPlanillaEnvioDocumento = em.merge(planillaEnvioDocumentoListNewPlanillaEnvioDocumento);
                    if (oldPlanillaEnvioOfPlanillaEnvioDocumentoListNewPlanillaEnvioDocumento != null && !oldPlanillaEnvioOfPlanillaEnvioDocumentoListNewPlanillaEnvioDocumento.equals(planillaEnvio)) {
                        oldPlanillaEnvioOfPlanillaEnvioDocumentoListNewPlanillaEnvioDocumento.getPlanillaEnvioDocumentoList().remove(planillaEnvioDocumentoListNewPlanillaEnvioDocumento);
                        oldPlanillaEnvioOfPlanillaEnvioDocumentoListNewPlanillaEnvioDocumento = em.merge(oldPlanillaEnvioOfPlanillaEnvioDocumentoListNewPlanillaEnvioDocumento);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = planillaEnvio.getId();
                if (findPlanillaEnvio(id) == null) {
                    throw new NonexistentEntityException("The planillaEnvio with id " + id + " no longer exists.");
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
            PlanillaEnvio planillaEnvio;
            try {
                planillaEnvio = em.getReference(PlanillaEnvio.class, id);
                planillaEnvio.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The planillaEnvio with id " + id + " no longer exists.", enfe);
            }
            Usuario creadoPor = planillaEnvio.getCreadoPor();
            if (creadoPor != null) {
                creadoPor.getPlanillaEnvioList().remove(planillaEnvio);
                creadoPor = em.merge(creadoPor);
            }
            Usuario modificadoPor = planillaEnvio.getModificadoPor();
            if (modificadoPor != null) {
                modificadoPor.getPlanillaEnvioList().remove(planillaEnvio);
                modificadoPor = em.merge(modificadoPor);
            }
            List<PlanillaEnvioDocumento> planillaEnvioDocumentoList = planillaEnvio.getPlanillaEnvioDocumentoList();
            for (PlanillaEnvioDocumento planillaEnvioDocumentoListPlanillaEnvioDocumento : planillaEnvioDocumentoList) {
                planillaEnvioDocumentoListPlanillaEnvioDocumento.setPlanillaEnvio(null);
                planillaEnvioDocumentoListPlanillaEnvioDocumento = em.merge(planillaEnvioDocumentoListPlanillaEnvioDocumento);
            }
            em.remove(planillaEnvio);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<PlanillaEnvio> findPlanillaEnvioEntities() {
        return findPlanillaEnvioEntities(true, -1, -1);
    }

    public List<PlanillaEnvio> findPlanillaEnvioEntities(int maxResults, int firstResult) {
        return findPlanillaEnvioEntities(false, maxResults, firstResult);
    }

    private List<PlanillaEnvio> findPlanillaEnvioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(PlanillaEnvio.class));
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

    public PlanillaEnvio findPlanillaEnvio(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(PlanillaEnvio.class, id);
        } finally {
            em.close();
        }
    }

    public int getPlanillaEnvioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<PlanillaEnvio> rt = cq.from(PlanillaEnvio.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
