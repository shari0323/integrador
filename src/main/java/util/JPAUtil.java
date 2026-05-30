package util;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JPAUtil {

    // Solo cargamos el "talentoPU" que ya configuramos con XAMPP
    private static final EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("talentoPU");

    // Dejamos este nombre intacto por si sus DAOs lo están usando
    public static EntityManager getEntityManagerPostgres(){
        return emf.createEntityManager();
    }

    // Apuntamos este también al mismo sitio para que no estalle
    public static EntityManager getEntityManagerMysql(){
        return emf.createEntityManager();
    }
}