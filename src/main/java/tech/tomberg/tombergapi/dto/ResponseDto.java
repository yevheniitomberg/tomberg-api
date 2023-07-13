package tech.tomberg.tombergapi.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.Map;

@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDto {
    private LocalDateTime timestamp;
    private Map<String, Object> data;
}
