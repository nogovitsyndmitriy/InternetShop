package service.preparation.service.converter.dto;

import com.gmail.nogovitsyndmitriy.dao.preparation.Subject;
import service.converter.DTOConverter;
import service.preparation.service.dto.SubjectDto;

import java.util.List;

public class SubjectDtoConverter implements DTOConverter<SubjectDto, Subject> {
    @Override
    public SubjectDto toDTO(Subject entity) {
        if (entity == null) {
            return null;
        }
        SubjectDto subjectDto = new SubjectDto();
        subjectDto.setId(entity.getId());
        subjectDto.setName(entity.getName());
        subjectDto.setComplexity(entity.getComplexity());
        return subjectDto;
    }

    @Override
    public List<SubjectDto> toDtoList(List<Subject> list) {
        return null;
    }
}
