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

        EntityManager em2 = JPAUtil.getEntityManagerMysql();
        em2.getTransaction().begin();
        em2.persist(p);
        em2.getTransaction().commit();
        em2.close();
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

        EntityManager em2 = JPAUtil.getEntityManagerMysql();
        em2.getTransaction().begin();
        Position p2 = em2.find(Position.class, id);
        if (p2 != null) {
            em2.remove(p2);
        }
        em2.getTransaction().commit();
        em2.close();
        em2.getTransaction().begin();
        em2.remove(p);
    }

    public List<Position> findAll() {
        EntityManager em = JPAUtil.getEntityManagerPostgres();
        List<Position> list = em.createQuery("FROM Position", Position.class).getResultList();
        em.close();

        EntityManager em2 = JPAUtil.getEntityManagerMysql();
        List<Position> list2 = em2.createQuery("FROM Position", Position.class).getResultList();
        em2.close();
        list.addAll(list2);

        return list;
    }
}
