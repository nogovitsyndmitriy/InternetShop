package com.gmail.nogovitsyndmitriy.dao.preparation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "T_STUDENT")
public class Student implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "F_ID")
    private Long id;
    @Column(name = "F_NAME")
    private String name;
    @Column(name = "F_SURNAME")
    private String surname;
    @Column(name = "F_BIRTH")
    private LocalDate birth;

    //    Student-RecordBook
    @OneToOne(mappedBy = "student", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private RecordBook recordBook;
    //    Student-University
    @ManyToOne
    @JoinColumn(name = "F_UNIVERSITY_ID")
    private University university;

    //     Student-Subject
    @ManyToMany
    @JoinTable(
            name = "T_STUDENT_SUBJECT",
            joinColumns = {@JoinColumn(name = "F_STUDENT_ID")},
            inverseJoinColumns = {@JoinColumn(name = "F_SUBJECT_ID")}
    )
    private Set<Subject> subjects = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student)) return false;
        Student student = (Student) o;
        return Objects.equals(id, student.id) &&
                Objects.equals(name, student.name) &&
                Objects.equals(surname, student.surname) &&
                Objects.equals(birth, student.birth);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, surname, birth);
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", birth=" + birth +
                ", recordBook=" + recordBook +
                ", university=" + university +
                ", subjects=" + subjects +
                '}';
    }
}
