package tech.tomberg.tombergapi.controller;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.*;
import tech.tomberg.tombergapi.dto.ContactMeDto;
import tech.tomberg.tombergapi.dto.ResponseDto;
import tech.tomberg.tombergapi.dto.TombergTechData;
import tech.tomberg.tombergapi.entity.Contact;
import tech.tomberg.tombergapi.entity.ContactedPerson;
import tech.tomberg.tombergapi.repository.*;
import tech.tomberg.tombergapi.utils.Constants;
import tech.tomberg.tombergapi.utils.Utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDateTime;

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
    private final ContactedPersonRepository contactedPersonRepository;

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
    public ResponseEntity<ResponseDto> userContactRequest(@RequestBody ContactMeDto contactMeDto) throws MessagingException {
        ContactedPerson person = contactedPersonRepository.findById(contactMeDto.getEmail()).orElse(ContactedPerson.builder().email(contactMeDto.getEmail()).request(0).build());
        if (person.getRequest() == Constants.EMAILS_PER_PERSON_LIMIT) {
            return new ResponseEntity<>(ResponseDto.builder().timestamp(LocalDateTime.now()).data(Utils.getDefaultDataMap("Request limit exceeded!", true)).build(), HttpStatus.TOO_MANY_REQUESTS);
        }
        person.setRequest(person.getRequest() + 1);
        contactedPersonRepository.save(person);
        sendEmailToUser(contactMeDto);
        return new ResponseEntity<>(ResponseDto.builder().timestamp(LocalDateTime.now()).data(Utils.getDefaultDataMap("Email was successfully sent!", false)).build(), HttpStatus.OK);
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
