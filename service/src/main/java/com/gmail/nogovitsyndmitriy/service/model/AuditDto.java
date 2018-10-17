package com.gmail.nogovitsyndmitriy.service.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuditDto {
    private Long id;
    private Long userId;
    private String eventType;
    private LocalDateTime created;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AuditDto)) return false;
        AuditDto auditDto = (AuditDto) o;
        return Objects.equals(id, auditDto.id) &&
                Objects.equals(userId, auditDto.userId) &&
                Objects.equals(eventType, auditDto.eventType) &&
                Objects.equals(created, auditDto.created);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, userId, eventType, created);
    }
}
