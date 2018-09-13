package service.converter.impl.dto;

import com.gmail.nogovitsyndmitriy.dao.entities.Audit;
import service.converter.DTOConverter;
import service.model.AuditDto;

import java.util.List;

public class AuditDtoConverter implements DTOConverter<AuditDto, Audit> {
    @Override
    public AuditDto toDTO(Audit entity) {
        if (entity == null) {
            return null;
        }
        AuditDto auditDto = new AuditDto();
        auditDto.setId(entity.getId());
        auditDto.setEventType(entity.getEventType());
        auditDto.setCreated(entity.getCreated());
        return auditDto;
    }

    @Override
    public List<AuditDto> toDtoList(List<Audit> list) {
        return null;
    }
}
