package dao;

import jakarta.persistence.*;
import util.JPAUtil;
import java.util.List;

public class GenericDao <T> {

    private Class<T> entityClass;

    public GenericDao(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    public void save(T entity) {
        // 1. Guardar en PostgreSQL
        EntityManager em = JPAUtil.getEntityManagerPostgres();
        try {
            em.getTransaction().begin();
            em.merge(entity);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }

        // 2. Guardar en MySQL
        EntityManager em2 = JPAUtil.getEntityManagerMysql();
        try {
            em2.getTransaction().begin();
            em2.merge(entity);
            em2.getTransaction().commit();
        } catch (Exception e) {
            if (em2.getTransaction().isActive()) em2.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em2.close();
        }
    }

    public List<T> findAll() {
        EntityManager em = JPAUtil.getEntityManagerPostgres();

        List<T> list = em.createQuery("FROM " + entityClass.getSimpleName(), entityClass).getResultList();
        em.close();

        EntityManager em2 = JPAUtil.getEntityManagerMysql();
        List<T> list2 = em2.createQuery("FROM " + entityClass.getSimpleName(), entityClass).getResultList();
        em2.close();
        list.addAll(list2);

        return list;
    }

    public void delete(Long id) {
        // 1. Eliminar de PostgreSQL
        EntityManager em = JPAUtil.getEntityManagerPostgres();
        try {
            em.getTransaction().begin();
            T entity = em.find(entityClass, id);
            if (entity != null) {
                em.remove(entity);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }

        // 2. Eliminar de MySQL (XAMPP)
        EntityManager em2 = JPAUtil.getEntityManagerMysql();
        try {
            em2.getTransaction().begin();
            T entity2 = em2.find(entityClass, id);
            if (entity2 != null) {
                em2.remove(entity2);
            }
            em2.getTransaction().commit();
        } catch (Exception e) {
            if (em2.getTransaction().isActive()) em2.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em2.close();
        }
    }
}
