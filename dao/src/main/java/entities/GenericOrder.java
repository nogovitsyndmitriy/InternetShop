package entities;

import lombok.*;
import org.apache.logging.log4j.core.tools.picocli.CommandLine;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "T_GENERIC_ORDER")
public class GenericOrder implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false, updatable = false)
    private long id;
    @Column(name = "USER_ID")
    private long user_id;
    @Column(name = "ITEM_ID")
    private long item_id;
    @Column(name = "QUANTITY")
    private int quantity;
    @Column(name = "CREATED")
    private LocalDateTime created;
}
