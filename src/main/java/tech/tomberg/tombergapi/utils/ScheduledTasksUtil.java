package tech.tomberg.tombergapi.utils;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import tech.tomberg.tombergapi.repository.ContactedPersonRepository;

@Component
@RequiredArgsConstructor
public class ScheduledTasksUtil {
    private final ContactedPersonRepository contactedPersonRepository;
    @Scheduled(cron = "@midnight")
    public void cleanContactedPersonList() {
        contactedPersonRepository.deleteAll();
    }
}
