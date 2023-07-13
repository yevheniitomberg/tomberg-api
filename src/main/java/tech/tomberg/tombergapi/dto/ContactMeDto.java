package tech.tomberg.tombergapi.dto;

import lombok.*;

@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ContactMeDto {
    private String name;
    private String email;
    private String message;
}
