package service.preparation.service.converter.entity;

import com.gmail.nogovitsyndmitriy.dao.preparation.RecordBook;
import service.converter.Converter;
import service.preparation.service.dto.RecordBookDto;

import java.util.List;

public class RecordBookConverter implements Converter<RecordBook, RecordBookDto> {
    @Override
    public RecordBook toEntity(RecordBookDto dto) {
        if (dto == null) {
            return null;
        }
        RecordBook recordBook = new RecordBook();
        recordBook.setId(dto.getId());
        recordBook.setMark(dto.getMark());
        recordBook.setUpdated(dto.getUpdated());
        return recordBook;
    }

    @Override
    public List<RecordBook> toEntityList(List<RecordBookDto> list) {
        return null;
    }
}
