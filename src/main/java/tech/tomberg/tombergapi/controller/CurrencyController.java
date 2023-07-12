package tech.tomberg.tombergapi.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.tomberg.tombergapi.dto.EmojiDataResponse;
import tech.tomberg.tombergapi.enums.EmojiEnum;

@RestController
@RequestMapping("/currency/emoji")
@CrossOrigin
public class CurrencyController {
    @GetMapping(value = "/{currency}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EmojiDataResponse> getFlagByCurrency(@PathVariable("currency") String currency) {
        return ResponseEntity.ok(EmojiDataResponse.builder().emoji(EmojiEnum.valueOf(currency).toString()).build());
    }
}
