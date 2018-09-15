package com.gmail.nogovitsyndmitriy.dao.preparation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "T_RECORD_BOOK")
public class RecordBook implements Serializable {
    @GenericGenerator(
            name = "generator",
            strategy = "foreign",
            parameters = @Parameter(name = "property", value ="student" )
    )
    @Id
    @GeneratedValue(generator = "generator")
    @Column(name = "F_STUDENT_ID")
    private Long id;
    @Column(name = "F_MARK")
    private double mark;
    @Column(name = "F_UPDATED")
    private LocalDateTime updated;

    @OneToOne(fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn
    private Student student;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RecordBook)) return false;
        RecordBook that = (RecordBook) o;
        return Double.compare(that.mark, mark) == 0 &&
                Objects.equals(id, that.id) &&
                Objects.equals(updated, that.updated);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, mark, updated);
    }

    @Override
    public String toString() {
        return "RecordBook{" +
                "id=" + id +
                ", mark=" + mark +
                ", updated=" + updated +
                '}';
    }
}
