package sri.persistence;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Environment;
import sri.entity.Class;
import sri.entity.Student;

import java.util.HashMap;
import java.util.Map;

public class HibernateUtil {
    private static SessionFactory sessionFactory;
    private static final Logger LOGGER = LogManager.getLogger("sri");

    public static SessionFactory getSessionFactory() {
        if(sessionFactory == null) {
            try {
                Map<String,String> settings = new HashMap<>();
                settings.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
                settings.put(Environment.URL, "jdbc:mysql://localhost:3306/test?useSSL=false");
                settings.put(Environment.USER, "root");
                settings.put(Environment.PASS, "");
                settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");
                settings.put(Environment.HBM2DDL_AUTO, "create-drop");
                settings.put(Environment.SHOW_SQL, "true");
                StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                        .applySettings(settings)
                        .build();
                sessionFactory = new MetadataSources(registry)
                        .addAnnotatedClass(Student.class)
                        .addAnnotatedClass(Class.class)
                        .buildMetadata()
                        .buildSessionFactory();
            }
            catch (Exception e) {
                LOGGER.error(e);
            }
        }

        return sessionFactory;
    }
}
