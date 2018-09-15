package service.preparation.service.converter.dto;

import com.gmail.nogovitsyndmitriy.dao.preparation.University;
import service.converter.DTOConverter;
import service.preparation.service.dto.UniversityDto;

import java.util.List;

public class UniversityDtoConverter implements DTOConverter<UniversityDto, University> {
    @Override
    public UniversityDto toDTO(University entity) {
        if (entity == null) {
            return null;
        }
        UniversityDto universityDto = new UniversityDto();
        universityDto.setId(entity.getId());
        universityDto.setName(entity.getName());
        universityDto.setAbbreviation(entity.getAbbreviation());
        universityDto.setRating(entity.getRating());
        return universityDto;
    }

    @Override
    public List<UniversityDto> toDtoList(List<University> list) {
        return null;
    }
}
