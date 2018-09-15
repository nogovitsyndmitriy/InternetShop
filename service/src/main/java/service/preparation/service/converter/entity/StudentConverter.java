package service.preparation.service.converter.entity;

import com.gmail.nogovitsyndmitriy.dao.preparation.Student;
import service.converter.Converter;
import service.preparation.service.dto.StudentDto;

import java.util.List;

public class StudentConverter implements Converter<Student, StudentDto> {
    @Override
    public Student toEntity(StudentDto dto) {
        if (dto == null) {
            return null;
        }
        Student student = new Student();
        student.setId(dto.getId());
        student.setName(dto.getName());
        student.setSurname(dto.getSurname());
        student.setBirth(dto.getBirth());
        return student;
    }

    @Override
    public List<Student> toEntityList(List<StudentDto> list) {
        return null;
    }
}
