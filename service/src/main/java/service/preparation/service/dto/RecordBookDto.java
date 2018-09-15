package service.preparation.service.dto;

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
public class RecordBookDto {
    private Long id;
    private double mark;
    private LocalDateTime updated;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RecordBookDto)) return false;
        RecordBookDto that = (RecordBookDto) o;
        return Double.compare(that.mark, mark) == 0 &&
                Objects.equals(id, that.id) &&
                Objects.equals(updated, that.updated);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, mark, updated);
    }
}
