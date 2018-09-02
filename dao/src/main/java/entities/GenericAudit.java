package entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "T_GENERIC_AUDIT")
public class GenericAudit implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false, updatable = false)
    private long id;
    @Column(name = "USER_ID")
    private long user_id;
    @Column(name = "EVENT_TYPE")
    private String event_type;
    @Column(name = "CREATED")
    private LocalDateTime created;
}
