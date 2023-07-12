package tech.tomberg.tombergapi.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Work {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String header;
    @Column(length = 1000)
    private String description;
    private String detailsLink;
    private String gitRepo;
    private String destinationLink;
    @OneToMany(mappedBy = "work")
    @ToString.Exclude
    private List<Download> downloads;
}
