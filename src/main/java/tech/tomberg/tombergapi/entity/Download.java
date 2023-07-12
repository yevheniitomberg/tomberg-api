package tech.tomberg.tombergapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import tech.tomberg.tombergapi.enums.OperatingSystem;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Download {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name="work_id", nullable=false)
    @JsonIgnore
    private Work work;

    @Enumerated(value = EnumType.STRING)
    private OperatingSystem os;

    private String ftp;
}
