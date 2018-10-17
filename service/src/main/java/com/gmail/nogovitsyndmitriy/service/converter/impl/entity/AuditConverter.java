package com.gmail.nogovitsyndmitriy.service.converter.impl.entity;

import com.gmail.nogovitsyndmitriy.dao.entities.Audit;
import com.gmail.nogovitsyndmitriy.service.converter.Converter;
import com.gmail.nogovitsyndmitriy.service.model.AuditDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component("auditConverter")
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
        return new ArrayList<>();
    }
}
