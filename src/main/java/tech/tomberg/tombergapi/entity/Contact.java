package tech.tomberg.tombergapi.entity;

import jakarta.persistence.*;
import lombok.*;
import tech.tomberg.tombergapi.enums.SocialMedia;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private SocialMedia portal;
    private String link;
}
