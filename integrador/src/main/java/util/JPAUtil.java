package util;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JPAUtil {
    private static final EntityManagerFactory emf = 
        Persistence.createEntityManagerFactory("talentoPU");

    public static EntityManager getEntityManagerPostgres(){
        return emf.createEntityManager();
    }

    private static final EntityManagerFactory emfMysql =
            Persistence.createEntityManagerFactory("mysqlPU");

    public static EntityManager getEntityManagerMysql(){
        return emfMysql.createEntityManager();
    }
}

