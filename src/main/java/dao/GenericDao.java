package dao;

import jakarta.persistence.EntityManager;
import util.JPAUtil;

import java.util.List;

public class GenericDao<T> {

    private Class<T> entityClass;

    public GenericDao(Class<T> entityClass) {

        this.entityClass = entityClass;
    }

    public void save(T entity) {

        EntityManager em =
                JPAUtil.getEntityManagerPostgres();

        try {

            em.getTransaction().begin();

            em.merge(entity);

            em.getTransaction().commit();

        } catch (Exception e) {

            if (em.getTransaction().isActive()) {

                em.getTransaction().rollback();
            }

            e.printStackTrace();

        } finally {

            em.close();
        }
    }

    public List<T> findAll() {

        EntityManager em =
                JPAUtil.getEntityManagerPostgres();

        List<T> list =
                em.createQuery(
                                "FROM "
                                        + entityClass.getSimpleName(),
                                entityClass
                        )
                        .getResultList();

        em.close();

        return list;
    }

    public T findById(Long id) {

        EntityManager em =
                JPAUtil.getEntityManagerPostgres();

        T entity =
                em.find(entityClass, id);

        em.close();

        return entity;
    }

    public void delete(Long id) {

        EntityManager em =
                JPAUtil.getEntityManagerPostgres();

        try {

            em.getTransaction().begin();

            T entity =
                    em.find(entityClass, id);

            if (entity != null) {

                em.remove(entity);
            }

            em.getTransaction().commit();

        } catch (Exception e) {

            if (em.getTransaction().isActive()) {

                em.getTransaction().rollback();
            }

            e.printStackTrace();

        } finally {

            em.close();
        }
    }
}