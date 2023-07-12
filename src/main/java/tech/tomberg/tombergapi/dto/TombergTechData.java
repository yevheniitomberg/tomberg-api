package tech.tomberg.tombergapi.dto;

import lombok.*;
import tech.tomberg.tombergapi.entity.*;

import java.util.List;

@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class TombergTechData {
    private List<Contact> contacts;
    private List<Education> educations;
    private List<Experience> experiences;
    private List<Question> questions;
    private List<Skill> skills;
    private List<Work> works;
}
