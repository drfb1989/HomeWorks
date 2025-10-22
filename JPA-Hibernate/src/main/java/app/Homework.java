package app;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "homework")
public class Homework {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 500)
    private String description;

    @Column
    private LocalDate deadline;

    @Column
    private int mark;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "student_id", nullable = false,
            foreignKey = @ForeignKey(name = "fk_homework_student"))
    private Student student;

    public Homework() { }

    public Homework(String description, LocalDate deadline, int mark) {
        this.description = description;
        this.deadline = deadline;
        this.mark = mark;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public LocalDate getDeadline() { return deadline; }
    public void setDeadline(LocalDate deadline) { this.deadline = deadline; }

    public int getMark() { return mark; }
    public void setMark(int mark) { this.mark = mark; }

    public Student getStudent() { return student; }
    public void setStudent(Student student) { this.student = student; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Homework other)) return false;
        return id != null && id.equals(other.id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Homework{id=" + id +
               ", description='" + description + '\'' +
               ", deadline=" + deadline +
               ", mark=" + mark +
               ", studentId=" + (student == null ? null : student.getId()) +
               '}';
    }
}
