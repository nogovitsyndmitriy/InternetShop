package service.converter.impl.entity;

import com.gmail.nogovitsyndmitriy.dao.entities.Audit;
import service.converter.Converter;
import service.model.AuditDto;

import java.util.List;

public class AuditConverter implements Converter<Audit, AuditDto> {
    @Override
    public Audit toEntity(AuditDto dto) {
        if (dto == null) {
            return null;
        }
        Audit audit = new Audit();
        audit.setId(dto.getId());
        audit.setEventType(dto.getEventType());
        audit.setCreated(dto.getCreated());

        return audit;
    }

    @Override
    public List<Audit> toEntityList(List<AuditDto> list) {
        return null;
    }
}
