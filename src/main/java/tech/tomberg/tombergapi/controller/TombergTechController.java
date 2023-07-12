package tech.tomberg.tombergapi.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.tomberg.tombergapi.dto.TombergTechData;
import tech.tomberg.tombergapi.entity.Contact;
import tech.tomberg.tombergapi.repository.*;

import java.util.List;

@RestController
@RequestMapping("/tomberg-tech/data")
@CrossOrigin
@RequiredArgsConstructor
public class TombergTechController {
    private final ContactRepository contactRepository;
    private final SkillRepository skillRepository;
    private final EducationRepository educationRepository;
    private final ExperienceRepository experienceRepository;
    private final QuestionRepository questionRepository;
    private final WorkRepository workRepository;
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TombergTechData> getAllContacts() {
        return ResponseEntity.ok(TombergTechData.builder()
                .contacts(contactRepository.findAll())
                .questions(questionRepository.findAll())
                .educations(educationRepository.findAll())
                .experiences(experienceRepository.findAll())
                .skills(skillRepository.findAll())
                .works(workRepository.findAll())
                .build());
    }
}
