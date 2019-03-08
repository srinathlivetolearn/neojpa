package sri;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.query.Query;
import sri.entity.Class;
import sri.entity.Student;
import sri.persistence.HibernateUtil;

import java.time.LocalDate;
import java.time.Month;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Hello world!
 *
 */
public class App 
{
    private static final Logger LOG = LogManager.getLogger("sri");
    public static void main( String[] args )
    {
        LOG.debug("Hi");
        System.out.println( "Hello World!" );
        App runner = new App();
        Student student = new Student();
        student.setFirstName("John");
        student.setLastName("Snow");
        student.setEmail("j@asd.com");
        student.setDateOfBirth(LocalDate.of(1992, Month.DECEMBER,28));
        Class class1 = new Class();
        class1.setStandard("1");
        class1.addStudent(student);
        runner.addClass(class1);
        //runner.addStudent(student);
        System.out.println(runner.listStudents());
    }

    public void addStudent(Student student) {
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.save(student);
            session.getTransaction().commit();
            System.out.println("Student added");
        }
        catch (Exception e) {
            LOG.error(e);
        }
    }

    public void addClass(Class c) {
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.save(c);
            session.getTransaction().commit();
        } catch (Exception e) {
            LOG.error(e);
        }
    }

    public List<?> listStudents() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query q = session.createQuery("SELECT s FROM Student s");
        return q.list();
    }
}
