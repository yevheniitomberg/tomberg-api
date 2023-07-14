package tech.tomberg.tombergapi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.tomberg.tombergapi.dto.ResponseDto;
import tech.tomberg.tombergapi.utils.Utils;

import java.time.LocalDateTime;

@RestController
@CrossOrigin
@RequestMapping("")
public class HomeController {
    @GetMapping
    public ResponseEntity<ResponseDto> welcomePage() {
        return new ResponseEntity<>(ResponseDto.builder().timestamp(LocalDateTime.now()).data(Utils.getDefaultDataMap("Welcome to Tomberg API", false)).build(), HttpStatus.OK);
    }
}
