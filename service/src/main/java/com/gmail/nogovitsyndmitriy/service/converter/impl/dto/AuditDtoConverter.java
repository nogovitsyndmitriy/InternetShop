package com.gmail.nogovitsyndmitriy.service.converter.impl.dto;

import com.gmail.nogovitsyndmitriy.dao.entities.Audit;
import com.gmail.nogovitsyndmitriy.service.converter.DTOConverter;
import com.gmail.nogovitsyndmitriy.service.model.AuditDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component("auditDtoConverter")
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
        return new ArrayList<>();
    }
}
