package sri.entity;

import javax.persistence.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "classes")
public class Class {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "standard", nullable = false)
	private String standard;

	@Column(name = "section")
	private String section;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "class_id")
	private Set<Student> students;

	@Version
	private long version;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getStandard() {
		return standard;
	}

	public void setStandard(String standard) {
		this.standard = standard;
	}

	public String getSection() {
		return section;
	}

	public void setSection(String section) {
		this.section = section;
	}

	public void addStudent(Student student) {
		if (students == null) {
			students = new HashSet<>();
		}
		students.add(student);
	}

	public Set<Student> getStudents() {
		return students;
	}

	public void setStudents(Set<Student> students) {
		this.students = students;
	}

	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Class aClass = (Class) o;
		return id == aClass.id &&
				Objects.equals(standard, aClass.standard) &&
				Objects.equals(section, aClass.section) &&
				Objects.equals(students, aClass.students);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, standard, section, students);
	}

	@Override
	public String toString() {
		return "Class{" +
				"id=" + id +
				", standard='" + standard + '\'' +
				", section='" + section + '\'' +
				", students=" + students +
				", version=" + version +
				'}';
	}
}
