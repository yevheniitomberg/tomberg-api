package tech.tomberg.tombergapi.dto;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EmojiDataResponse {
    private String emoji;
}
