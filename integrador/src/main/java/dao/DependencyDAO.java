package dao;

import jakarta.persistence.EntityManager;
import model.Dependency;
import util.JPAUtil;

import java.util.List;

public class DependencyDAO {

    public void save(Dependency d) {
        EntityManager em = JPAUtil.getEntityManagerPostgres();

        em.getTransaction().begin();
        em.persist(d);
        em.getTransaction().commit();
        em.close();

        EntityManager em2 = JPAUtil.getEntityManagerMysql();
        em2.getTransaction().begin();
        em2.persist(d);
        em2.getTransaction().commit();
        em2.close();

    }

    public void delete(Long id) {
        EntityManager em = JPAUtil.getEntityManagerPostgres();
        em.getTransaction().begin();

        Dependency d = em.find(Dependency.class, id);
        if (d != null) {
            em.remove(d);
        }

        em.getTransaction().commit();
        em.close();

        EntityManager em2 = JPAUtil.getEntityManagerMysql();
        em2.getTransaction().begin();
        Dependency d2 = em2.find(Dependency.class, id);
        if (d2 != null) {
            em2.remove(d2);
        }
        em2.getTransaction().commit();
        em2.close();

    }

    public List<Dependency> findAll() {
        EntityManager em = JPAUtil.getEntityManagerPostgres();
        List<Dependency> list = em
                .createQuery("FROM Dependency", Dependency.class)
                .getResultList();

        em.close();

        EntityManager em2 = JPAUtil.getEntityManagerMysql();
        List<Dependency> list2 = em2
                .createQuery("FROM Dependency", Dependency.class)
                .getResultList();
        em2.close();
        list.addAll(list2);

        return list;
    }
}
