package service.preparation.service.converter.entity;

import com.gmail.nogovitsyndmitriy.dao.preparation.Subject;
import service.converter.Converter;
import service.preparation.service.dto.SubjectDto;

import java.util.List;

public class SubjectConverter implements Converter<Subject, SubjectDto> {
    @Override
    public Subject toEntity(SubjectDto dto) {
        if (dto == null) {
            return null;
        }
        Subject subject = new Subject();
        subject.setId(dto.getId());
        subject.setName(dto.getName());
        subject.setComplexity(dto.getComplexity());
        return subject;
    }

    @Override
    public List<Subject> toEntityList(List<SubjectDto> list) {
        return null;
    }
}
