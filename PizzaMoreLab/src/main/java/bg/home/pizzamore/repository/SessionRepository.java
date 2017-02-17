/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bg.home.pizzamore.repository;

import bg.home.pizzamore.models.Session;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author kalin
 */
public class SessionRepository {

    public long createSession(Session session) {
        long id;
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pizzaMore");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(session);
        id = session.getId();
        em.getTransaction().commit();
        em.close();
        emf.close();
        return id;
    }

    public  Session findById(long id){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pizzaMore");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Query query = em.createQuery("SELECT s FROM Session AS s WHERE s.id = :id");
        query.setParameter("id", id);
        Session session = null;
        
        if (!(query.getResultList().size() == 0)) {
            session = (Session) query.getSingleResult();
        }
        em.getTransaction().commit();
        em.close();
        emf.close();
        
        return session;
    }
    
    public void delete (long id){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pizzaMore");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        
        Query query = em.createQuery("DELETE FROM Session AS s WHERE s.id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
        em.getTransaction().commit();
        em.close();
        emf.close();
    
    }
}
