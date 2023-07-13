package tech.tomberg.tombergapi.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class ContactedPerson {
    @Id
    @Column(name = "email", nullable = false)
    private String email;
    private int request;
}