package service.preparation.service.converter.dto;

import com.gmail.nogovitsyndmitriy.dao.preparation.RecordBook;
import service.converter.DTOConverter;
import service.preparation.service.dto.RecordBookDto;

import java.util.List;

public class RecordBookDtoConverter implements DTOConverter<RecordBookDto, RecordBook> {
    @Override
    public RecordBookDto toDTO(RecordBook entity) {
        if (entity == null) {
            return null;
        }
        RecordBookDto recordBookDto = new RecordBookDto();
        recordBookDto.setId(entity.getId());
        recordBookDto.setMark(entity.getMark());
        recordBookDto.setUpdated(entity.getUpdated());
        return recordBookDto;
    }

    @Override
    public List<RecordBookDto> toDtoList(List<RecordBook> list) {
        return null;
    }
}
