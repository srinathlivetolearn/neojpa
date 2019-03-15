package sri;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

import javax.persistence.TypedQuery;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.query.Query;
import sri.entity.Class;
import sri.entity.Student;
import sri.entity.TimeTableEntry;
import sri.persistence.HibernateUtil;

public class App {
	private static final Logger LOG = LogManager.getLogger("sri");

	public static void main(String[] args) {
		LOG.debug("Hi");
		System.out.println("Hello World!");
		App runner = new App();
		Student student = new Student();
		student.setFirstName("John");
		student.setLastName("Snow");
		student.setEmail("j@asd.com");
		student.setDateOfBirth(LocalDate.of(1992, Month.DECEMBER, 28));
		Student student1 = new Student();
		student1.setFirstName("Jane");
		student1.setLastName("Hopper");
		student1.setEmail("jh@asd.com");
		student1.setDateOfBirth(LocalDate.of(1994, Month.DECEMBER, 28));
		Class class1 = new Class();
		class1.setStandard("1");
		class1.addStudent(student);
		class1.addStudent(student1);
		TimeTableEntry timeTable = new TimeTableEntry();
		timeTable.setSubject("Kannada");
		timeTable.setStartTime(LocalDateTime.now());
		timeTable.setEndtime(LocalDateTime.now().plusMinutes(45));
		timeTable.addStudent(student);
		timeTable.addStudent(student1);
		runner.addClass(class1);
		runner.addTimeTable(timeTable);
		LOG.info(runner.getFromNamedQuery());
	}

	private void addTimeTable(TimeTableEntry timeTable) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			session.beginTransaction();
			session.save(timeTable);
			session.getTransaction().commit();
			System.out.println("Student added");
		}
		catch (Exception e) {
			LOG.error(e);
		}
	}

	public void addStudent(Student student) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
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
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			session.beginTransaction();
			session.save(c);
			session.getTransaction().commit();
		}
		catch (Exception e) {
			LOG.error(e);
		}
	}

	public List<?> listStudents() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Query q = session.createQuery("SELECT s FROM Student s");
		return q.list();
	}

	public List<Student> getFromNamedQuery() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		TypedQuery<Student> query = session.getNamedQuery("student_by_fname");
		query.setParameter("fname","Jane");
		return query.getResultList();
	}
}
