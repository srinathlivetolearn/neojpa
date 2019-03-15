package sri.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name = "TIMETABLE")
public class TimeTableEntry {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "starttime", nullable = false)
	private LocalDateTime startTime;

	@Column(name = "endtime", nullable = false)
	private LocalDateTime endtime;

	@Column(name = "subject", nullable = false)
	private String subject;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(joinColumns = { @JoinColumn(name = "fk_student") }, inverseJoinColumns = { @JoinColumn(name = "fk_timetable") })
	private Set<Student> students;

	@Version
	private long version;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public LocalDateTime getStartTime() {
		return startTime;
	}

	public void setStartTime(LocalDateTime startTime) {
		this.startTime = startTime;
	}

	public LocalDateTime getEndtime() {
		return endtime;
	}

	public void setEndtime(LocalDateTime endtime) {
		this.endtime = endtime;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
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
		TimeTableEntry that = (TimeTableEntry) o;
		return id == that.id &&
				Objects.equals(startTime, that.startTime) &&
				Objects.equals(endtime, that.endtime) &&
				Objects.equals(subject, that.subject) &&
				Objects.equals(students, that.students);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, startTime, endtime, subject, students);
	}

	@Override
	public String toString() {
		return "TimeTableEntry{" +
				"id=" + id +
				", startTime=" + startTime +
				", endtime=" + endtime +
				", subject='" + subject + '\'' +
				", students=" + students +
				", version=" + version +
				'}';
	}

	public void addStudent(Student student) {
		if (students == null) {
			students = new HashSet<>();
		}
		students.add(student);
	}
}
