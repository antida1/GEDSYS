/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base16.gedsys.model;

import com.base16.gedsys.entities.Acta;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.base16.gedsys.entities.Usuario;
import com.base16.gedsys.entities.Actaausente;
import java.util.ArrayList;
import java.util.List;
import com.base16.gedsys.entities.Actaasistente;
import com.base16.gedsys.entities.Actainvitado;
import com.base16.gedsys.model.exceptions.IllegalOrphanException;
import com.base16.gedsys.model.exceptions.NonexistentEntityException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author rober
 */
public class ActaJpaController implements Serializable {

    public ActaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Acta acta) {
        if (acta.getActaausenteList() == null) {
            acta.setActaausenteList(new ArrayList<Actaausente>());
        }
        if (acta.getActaasistenteList() == null) {
            acta.setActaasistenteList(new ArrayList<Actaasistente>());
        }
        if (acta.getActainvitadoList() == null) {
            acta.setActainvitadoList(new ArrayList<Actainvitado>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario creadoPor = acta.getCreadoPor();
            if (creadoPor != null) {
                creadoPor = em.getReference(creadoPor.getClass(), creadoPor.getId());
                acta.setCreadoPor(creadoPor);
            }
            Usuario modificadoPor = acta.getModificadoPor();
            if (modificadoPor != null) {
                modificadoPor = em.getReference(modificadoPor.getClass(), modificadoPor.getId());
                acta.setModificadoPor(modificadoPor);
            }
            Usuario presidente = acta.getPresidente();
            if (presidente != null) {
                presidente = em.getReference(presidente.getClass(), presidente.getId());
                acta.setPresidente(presidente);
            }
            Usuario secretaria = acta.getSecretaria();
            if (secretaria != null) {
                secretaria = em.getReference(secretaria.getClass(), secretaria.getId());
                acta.setSecretaria(secretaria);
            }
            List<Actaausente> attachedActaausenteList = new ArrayList<Actaausente>();
            for (Actaausente actaausenteListActaausenteToAttach : acta.getActaausenteList()) {
                actaausenteListActaausenteToAttach = em.getReference(actaausenteListActaausenteToAttach.getClass(), actaausenteListActaausenteToAttach.getId());
                attachedActaausenteList.add(actaausenteListActaausenteToAttach);
            }
            acta.setActaausenteList(attachedActaausenteList);
            List<Actaasistente> attachedActaasistenteList = new ArrayList<Actaasistente>();
            for (Actaasistente actaasistenteListActaasistenteToAttach : acta.getActaasistenteList()) {
                actaasistenteListActaasistenteToAttach = em.getReference(actaasistenteListActaasistenteToAttach.getClass(), actaasistenteListActaasistenteToAttach.getId());
                attachedActaasistenteList.add(actaasistenteListActaasistenteToAttach);
            }
            acta.setActaasistenteList(attachedActaasistenteList);
            List<Actainvitado> attachedActainvitadoList = new ArrayList<Actainvitado>();
            for (Actainvitado actainvitadoListActainvitadoToAttach : acta.getActainvitadoList()) {
                actainvitadoListActainvitadoToAttach = em.getReference(actainvitadoListActainvitadoToAttach.getClass(), actainvitadoListActainvitadoToAttach.getId());
                attachedActainvitadoList.add(actainvitadoListActainvitadoToAttach);
            }
            acta.setActainvitadoList(attachedActainvitadoList);
            em.persist(acta);
            if (creadoPor != null) {
                creadoPor.getActaList().add(acta);
                creadoPor = em.merge(creadoPor);
            }
            if (modificadoPor != null) {
                modificadoPor.getActaList().add(acta);
                modificadoPor = em.merge(modificadoPor);
            }
            if (presidente != null) {
                presidente.getActaList().add(acta);
                presidente = em.merge(presidente);
            }
            if (secretaria != null) {
                secretaria.getActaList().add(acta);
                secretaria = em.merge(secretaria);
            }
            for (Actaausente actaausenteListActaausente : acta.getActaausenteList()) {
                Acta oldActaOfActaausenteListActaausente = actaausenteListActaausente.getActa();
                actaausenteListActaausente.setActa(acta);
                actaausenteListActaausente = em.merge(actaausenteListActaausente);
                if (oldActaOfActaausenteListActaausente != null) {
                    oldActaOfActaausenteListActaausente.getActaausenteList().remove(actaausenteListActaausente);
                    oldActaOfActaausenteListActaausente = em.merge(oldActaOfActaausenteListActaausente);
                }
            }
            for (Actaasistente actaasistenteListActaasistente : acta.getActaasistenteList()) {
                Acta oldActaOfActaasistenteListActaasistente = actaasistenteListActaasistente.getActa();
                actaasistenteListActaasistente.setActa(acta);
                actaasistenteListActaasistente = em.merge(actaasistenteListActaasistente);
                if (oldActaOfActaasistenteListActaasistente != null) {
                    oldActaOfActaasistenteListActaasistente.getActaasistenteList().remove(actaasistenteListActaasistente);
                    oldActaOfActaasistenteListActaasistente = em.merge(oldActaOfActaasistenteListActaasistente);
                }
            }
            for (Actainvitado actainvitadoListActainvitado : acta.getActainvitadoList()) {
                Acta oldActaOfActainvitadoListActainvitado = actainvitadoListActainvitado.getActa();
                actainvitadoListActainvitado.setActa(acta);
                actainvitadoListActainvitado = em.merge(actainvitadoListActainvitado);
                if (oldActaOfActainvitadoListActainvitado != null) {
                    oldActaOfActainvitadoListActainvitado.getActainvitadoList().remove(actainvitadoListActainvitado);
                    oldActaOfActainvitadoListActainvitado = em.merge(oldActaOfActainvitadoListActainvitado);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Acta acta) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Acta persistentActa = em.find(Acta.class, acta.getId());
            Usuario creadoPorOld = persistentActa.getCreadoPor();
            Usuario creadoPorNew = acta.getCreadoPor();
            Usuario modificadoPorOld = persistentActa.getModificadoPor();
            Usuario modificadoPorNew = acta.getModificadoPor();
            Usuario presidenteOld = persistentActa.getPresidente();
            Usuario presidenteNew = acta.getPresidente();
            Usuario secretariaOld = persistentActa.getSecretaria();
            Usuario secretariaNew = acta.getSecretaria();
            List<Actaausente> actaausenteListOld = persistentActa.getActaausenteList();
            List<Actaausente> actaausenteListNew = acta.getActaausenteList();
            List<Actaasistente> actaasistenteListOld = persistentActa.getActaasistenteList();
            List<Actaasistente> actaasistenteListNew = acta.getActaasistenteList();
            List<Actainvitado> actainvitadoListOld = persistentActa.getActainvitadoList();
            List<Actainvitado> actainvitadoListNew = acta.getActainvitadoList();
            List<String> illegalOrphanMessages = null;
            for (Actaausente actaausenteListOldActaausente : actaausenteListOld) {
                if (!actaausenteListNew.contains(actaausenteListOldActaausente)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Actaausente " + actaausenteListOldActaausente + " since its acta field is not nullable.");
                }
            }
            for (Actaasistente actaasistenteListOldActaasistente : actaasistenteListOld) {
                if (!actaasistenteListNew.contains(actaasistenteListOldActaasistente)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Actaasistente " + actaasistenteListOldActaasistente + " since its acta field is not nullable.");
                }
            }
            for (Actainvitado actainvitadoListOldActainvitado : actainvitadoListOld) {
                if (!actainvitadoListNew.contains(actainvitadoListOldActainvitado)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Actainvitado " + actainvitadoListOldActainvitado + " since its acta field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (creadoPorNew != null) {
                creadoPorNew = em.getReference(creadoPorNew.getClass(), creadoPorNew.getId());
                acta.setCreadoPor(creadoPorNew);
            }
            if (modificadoPorNew != null) {
                modificadoPorNew = em.getReference(modificadoPorNew.getClass(), modificadoPorNew.getId());
                acta.setModificadoPor(modificadoPorNew);
            }
            if (presidenteNew != null) {
                presidenteNew = em.getReference(presidenteNew.getClass(), presidenteNew.getId());
                acta.setPresidente(presidenteNew);
            }
            if (secretariaNew != null) {
                secretariaNew = em.getReference(secretariaNew.getClass(), secretariaNew.getId());
                acta.setSecretaria(secretariaNew);
            }
            List<Actaausente> attachedActaausenteListNew = new ArrayList<Actaausente>();
            for (Actaausente actaausenteListNewActaausenteToAttach : actaausenteListNew) {
                actaausenteListNewActaausenteToAttach = em.getReference(actaausenteListNewActaausenteToAttach.getClass(), actaausenteListNewActaausenteToAttach.getId());
                attachedActaausenteListNew.add(actaausenteListNewActaausenteToAttach);
            }
            actaausenteListNew = attachedActaausenteListNew;
            acta.setActaausenteList(actaausenteListNew);
            List<Actaasistente> attachedActaasistenteListNew = new ArrayList<Actaasistente>();
            for (Actaasistente actaasistenteListNewActaasistenteToAttach : actaasistenteListNew) {
                actaasistenteListNewActaasistenteToAttach = em.getReference(actaasistenteListNewActaasistenteToAttach.getClass(), actaasistenteListNewActaasistenteToAttach.getId());
                attachedActaasistenteListNew.add(actaasistenteListNewActaasistenteToAttach);
            }
            actaasistenteListNew = attachedActaasistenteListNew;
            acta.setActaasistenteList(actaasistenteListNew);
            List<Actainvitado> attachedActainvitadoListNew = new ArrayList<Actainvitado>();
            for (Actainvitado actainvitadoListNewActainvitadoToAttach : actainvitadoListNew) {
                actainvitadoListNewActainvitadoToAttach = em.getReference(actainvitadoListNewActainvitadoToAttach.getClass(), actainvitadoListNewActainvitadoToAttach.getId());
                attachedActainvitadoListNew.add(actainvitadoListNewActainvitadoToAttach);
            }
            actainvitadoListNew = attachedActainvitadoListNew;
            acta.setActainvitadoList(actainvitadoListNew);
            acta = em.merge(acta);
            if (creadoPorOld != null && !creadoPorOld.equals(creadoPorNew)) {
                creadoPorOld.getActaList().remove(acta);
                creadoPorOld = em.merge(creadoPorOld);
            }
            if (creadoPorNew != null && !creadoPorNew.equals(creadoPorOld)) {
                creadoPorNew.getActaList().add(acta);
                creadoPorNew = em.merge(creadoPorNew);
            }
            if (modificadoPorOld != null && !modificadoPorOld.equals(modificadoPorNew)) {
                modificadoPorOld.getActaList().remove(acta);
                modificadoPorOld = em.merge(modificadoPorOld);
            }
            if (modificadoPorNew != null && !modificadoPorNew.equals(modificadoPorOld)) {
                modificadoPorNew.getActaList().add(acta);
                modificadoPorNew = em.merge(modificadoPorNew);
            }
            if (presidenteOld != null && !presidenteOld.equals(presidenteNew)) {
                presidenteOld.getActaList().remove(acta);
                presidenteOld = em.merge(presidenteOld);
            }
            if (presidenteNew != null && !presidenteNew.equals(presidenteOld)) {
                presidenteNew.getActaList().add(acta);
                presidenteNew = em.merge(presidenteNew);
            }
            if (secretariaOld != null && !secretariaOld.equals(secretariaNew)) {
                secretariaOld.getActaList().remove(acta);
                secretariaOld = em.merge(secretariaOld);
            }
            if (secretariaNew != null && !secretariaNew.equals(secretariaOld)) {
                secretariaNew.getActaList().add(acta);
                secretariaNew = em.merge(secretariaNew);
            }
            for (Actaausente actaausenteListNewActaausente : actaausenteListNew) {
                if (!actaausenteListOld.contains(actaausenteListNewActaausente)) {
                    Acta oldActaOfActaausenteListNewActaausente = actaausenteListNewActaausente.getActa();
                    actaausenteListNewActaausente.setActa(acta);
                    actaausenteListNewActaausente = em.merge(actaausenteListNewActaausente);
                    if (oldActaOfActaausenteListNewActaausente != null && !oldActaOfActaausenteListNewActaausente.equals(acta)) {
                        oldActaOfActaausenteListNewActaausente.getActaausenteList().remove(actaausenteListNewActaausente);
                        oldActaOfActaausenteListNewActaausente = em.merge(oldActaOfActaausenteListNewActaausente);
                    }
                }
            }
            for (Actaasistente actaasistenteListNewActaasistente : actaasistenteListNew) {
                if (!actaasistenteListOld.contains(actaasistenteListNewActaasistente)) {
                    Acta oldActaOfActaasistenteListNewActaasistente = actaasistenteListNewActaasistente.getActa();
                    actaasistenteListNewActaasistente.setActa(acta);
                    actaasistenteListNewActaasistente = em.merge(actaasistenteListNewActaasistente);
                    if (oldActaOfActaasistenteListNewActaasistente != null && !oldActaOfActaasistenteListNewActaasistente.equals(acta)) {
                        oldActaOfActaasistenteListNewActaasistente.getActaasistenteList().remove(actaasistenteListNewActaasistente);
                        oldActaOfActaasistenteListNewActaasistente = em.merge(oldActaOfActaasistenteListNewActaasistente);
                    }
                }
            }
            for (Actainvitado actainvitadoListNewActainvitado : actainvitadoListNew) {
                if (!actainvitadoListOld.contains(actainvitadoListNewActainvitado)) {
                    Acta oldActaOfActainvitadoListNewActainvitado = actainvitadoListNewActainvitado.getActa();
                    actainvitadoListNewActainvitado.setActa(acta);
                    actainvitadoListNewActainvitado = em.merge(actainvitadoListNewActainvitado);
                    if (oldActaOfActainvitadoListNewActainvitado != null && !oldActaOfActainvitadoListNewActainvitado.equals(acta)) {
                        oldActaOfActainvitadoListNewActainvitado.getActainvitadoList().remove(actainvitadoListNewActainvitado);
                        oldActaOfActainvitadoListNewActainvitado = em.merge(oldActaOfActainvitadoListNewActainvitado);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = acta.getId();
                if (findActa(id) == null) {
                    throw new NonexistentEntityException("The acta with id " + id + " no longer exists.");
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
            Acta acta;
            try {
                acta = em.getReference(Acta.class, id);
                acta.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The acta with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Actaausente> actaausenteListOrphanCheck = acta.getActaausenteList();
            for (Actaausente actaausenteListOrphanCheckActaausente : actaausenteListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Acta (" + acta + ") cannot be destroyed since the Actaausente " + actaausenteListOrphanCheckActaausente + " in its actaausenteList field has a non-nullable acta field.");
            }
            List<Actaasistente> actaasistenteListOrphanCheck = acta.getActaasistenteList();
            for (Actaasistente actaasistenteListOrphanCheckActaasistente : actaasistenteListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Acta (" + acta + ") cannot be destroyed since the Actaasistente " + actaasistenteListOrphanCheckActaasistente + " in its actaasistenteList field has a non-nullable acta field.");
            }
            List<Actainvitado> actainvitadoListOrphanCheck = acta.getActainvitadoList();
            for (Actainvitado actainvitadoListOrphanCheckActainvitado : actainvitadoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Acta (" + acta + ") cannot be destroyed since the Actainvitado " + actainvitadoListOrphanCheckActainvitado + " in its actainvitadoList field has a non-nullable acta field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Usuario creadoPor = acta.getCreadoPor();
            if (creadoPor != null) {
                creadoPor.getActaList().remove(acta);
                creadoPor = em.merge(creadoPor);
            }
            Usuario modificadoPor = acta.getModificadoPor();
            if (modificadoPor != null) {
                modificadoPor.getActaList().remove(acta);
                modificadoPor = em.merge(modificadoPor);
            }
            Usuario presidente = acta.getPresidente();
            if (presidente != null) {
                presidente.getActaList().remove(acta);
                presidente = em.merge(presidente);
            }
            Usuario secretaria = acta.getSecretaria();
            if (secretaria != null) {
                secretaria.getActaList().remove(acta);
                secretaria = em.merge(secretaria);
            }
            em.remove(acta);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Acta> findActaEntities() {
        return findActaEntities(true, -1, -1);
    }

    public List<Acta> findActaEntities(int maxResults, int firstResult) {
        return findActaEntities(false, maxResults, firstResult);
    }

    private List<Acta> findActaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Acta.class));
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

    public Acta findActa(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Acta.class, id);
        } finally {
            em.close();
        }
    }

    public int getActaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Acta> rt = cq.from(Acta.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
