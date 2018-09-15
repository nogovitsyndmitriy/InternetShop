package service.preparation.service.converter.dto;

import com.gmail.nogovitsyndmitriy.dao.preparation.RecordBook;
import com.gmail.nogovitsyndmitriy.dao.preparation.Student;
import com.gmail.nogovitsyndmitriy.dao.preparation.Subject;
import service.converter.DTOConverter;
import service.preparation.service.converter.entity.RecordBookConverter;
import service.preparation.service.dto.RecordBookDto;
import service.preparation.service.dto.StudentDto;
import service.preparation.service.dto.SubjectDto;
import service.preparation.service.dto.UniversityDto;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class StudentDtoConverter implements DTOConverter<StudentDto, Student> {
    @Override
    public StudentDto toDTO(Student entity) {
        if (entity == null) {
            return null;
        }
        StudentDto studentDto = new StudentDto();
        studentDto.setId(entity.getId());
        studentDto.setName(entity.getName());
        studentDto.setSurname(entity.getSurname());
        studentDto.setBirth(entity.getBirth());
//        Add RecordBook
        RecordBookDtoConverter recordBookDtoConverter = new RecordBookDtoConverter();
        if (entity.getRecordBook() != null) {
            RecordBookDto recordBookDto = recordBookDtoConverter.toDTO(entity.getRecordBook());
            studentDto.setRecordBookDto(recordBookDto);
        }
//         Add University
        UniversityDtoConverter universityDtoConverter = new UniversityDtoConverter();
        if (entity.getUniversity() != null) {
            UniversityDto universityDto = universityDtoConverter.toDTO(entity.getUniversity());
            studentDto.setUniversityDto(universityDto);
        }
//        Add Subjects
        SubjectDtoConverter subjectDtoConverter = new SubjectDtoConverter();
        Set<SubjectDto> subjectDtoSet = new HashSet<>();
        for (Subject subject : entity.getSubjects()) {
            subjectDtoSet.add(subjectDtoConverter.toDTO(subject));
        }
        studentDto.setSubjectDtoSet(subjectDtoSet);
        return studentDto;
    }

    @Override
    public List<StudentDto> toDtoList(List<Student> list) {
        return null;
    }
}
