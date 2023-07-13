package tech.tomberg.tombergapi.controller;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.IOUtils;
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
import tech.tomberg.tombergapi.utils.Constants;

import java.io.FileInputStream;
import java.io.IOException;

@RestController
@RequestMapping("/tomberg-tech")
@CrossOrigin
@RequiredArgsConstructor
public class TombergTechController {
    @Value("${spring.mail.username}")
    private String from;
    @Value("${local.dev}")
    private boolean isDev;
    private String path = Constants.PATH_TO_TEMPLATE_PROD;
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
        if (isDev) {
            path = Constants.PATH_TO_TEMPLATE_DEV;
        }
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        helper.setFrom(from);
        helper.setTo(contactMeDto.getEmail().toLowerCase());
        helper.setSubject("Auto Reply");
        try (
                FileInputStream template = new FileInputStream(String.format("%s/template.html", path))
        ) {
            String text = IOUtils.toString(template.readAllBytes(), "UTF-8");
            for (Contact contact : contactRepository.findAll()) {
                text = text.replace(contact.getPortal().name(), contact.getLink());
            }
            text = text.replace("USERNAME", contactMeDto.getName());
            message.setContent(text, "text/html");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        javaMailSender.send(message);
    }
}
