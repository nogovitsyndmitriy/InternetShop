package service.preparation.service.converter.entity;

import com.gmail.nogovitsyndmitriy.dao.preparation.University;
import service.converter.Converter;
import service.preparation.service.dto.UniversityDto;

import java.util.List;

public class UniversityConverter implements Converter<University, UniversityDto> {
    @Override
    public University toEntity(UniversityDto dto) {
        if(dto == null){
            return null;
        }
        University university = new University();
        university.setId(dto.getId());
        university.setName(dto.getName());
        university.setAbbreviation(dto.getAbbreviation());
        university.setRating(dto.getRating());
        return null;
    }

    @Override
    public List<University> toEntityList(List<UniversityDto> list) {
        return null;
    }
}
