package service.preparation.service.dto;

import com.gmail.nogovitsyndmitriy.dao.preparation.RecordBook;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentDto {

    private Long id;
    private String name;
    private String surname;
    private LocalDate birth;
    private RecordBookDto recordBookDto;
    private UniversityDto universityDto;
    private Set<SubjectDto> subjectDtoSet;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StudentDto)) return false;
        StudentDto that = (StudentDto) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(surname, that.surname) &&
                Objects.equals(birth, that.birth);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, surname, birth);
    }
}
