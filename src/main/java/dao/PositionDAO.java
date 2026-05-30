package dao;

import jakarta.persistence.EntityManager;
import model.Position;
import util.JPAUtil;

import java.util.List;

public class PositionDAO {

    public void save(Position p) {
        EntityManager em = JPAUtil.getEntityManagerPostgres();
        em.getTransaction().begin();
        em.persist(p);
        em.getTransaction().commit();
        em.close();


    }

    public void delete(Long id) {
        EntityManager em = JPAUtil.getEntityManagerPostgres();
        em.getTransaction().begin();
        Position p = em.find(Position.class, id);
        if (p != null) {
            em.remove(p);
        }
        em.getTransaction().commit();
        em.close();
    }

    public List<Position> findAll() {
        EntityManager em = JPAUtil.getEntityManagerPostgres();
        List<Position> list = em.createQuery("FROM Position", Position.class).getResultList();
        em.close();



        return list;
    }
}
