package tech.tomberg.tombergapi.controller;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.*;
import tech.tomberg.tombergapi.dto.ContactMeDto;
import tech.tomberg.tombergapi.dto.TombergTechData;
import tech.tomberg.tombergapi.entity.Contact;
import tech.tomberg.tombergapi.repository.*;
import org.apache.commons.io.IOUtils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

@RestController
@RequestMapping("/tomberg-tech")
@CrossOrigin
@RequiredArgsConstructor
public class TombergTechController {
    @Value("${spring.mail.username}")
    private String from;
    private final ContactRepository contactRepository;
    private final SkillRepository skillRepository;
    private final EducationRepository educationRepository;
    private final ExperienceRepository experienceRepository;
    private final QuestionRepository questionRepository;
    private final WorkRepository workRepository;
    private final JavaMailSender javaMailSender;

    @GetMapping(value = "/data", produces = MediaType.APPLICATION_JSON_VALUE)
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

    @PostMapping("/contact")
    public ResponseEntity<?> userContactRequest(@RequestBody ContactMeDto contactMeDto) throws MessagingException {
        sendEmailToUser(contactMeDto);
        return ResponseEntity.ok(true);
    }

    public void sendEmailToUser(ContactMeDto contactMeDto) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, "utf-8");
        helper.setFrom(from);
        helper.setTo(contactMeDto.getEmail().toLowerCase());
        helper.setSubject("Auto Reply");
        try (
                FileInputStream template = new FileInputStream("src/main/resources/templates/template.html")
        ) {
            String text = IOUtils.toString(template.readAllBytes(), "UTF-8");
            for (Contact contact : contactRepository.findAll()) {
                text = text.replace(contact.getPortal().name(), contact.getLink());
            }
            text = text.replace("USERNAME", contactMeDto.getName());
            helper.setText(text, true);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        javaMailSender.send(message);
    }
}
