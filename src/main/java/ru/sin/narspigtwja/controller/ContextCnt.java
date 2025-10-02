package ru.sin.narspigtwja.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.sin.narspigtwja.dto.ContextReq;
import ru.sin.narspigtwja.dto.ContextRes;
import ru.sin.narspigtwja.service.ContextServ;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "context")
@CrossOrigin(origins = {"https://narspi21.ru", "http://localhost:3000"}, allowCredentials = "true")
public class ContextCnt {
    private final ContextServ contextServ;

    @PostMapping()
    public ResponseEntity<ContextRes> postContext(@RequestBody ContextReq contextReq) {
        return ResponseEntity.status(HttpStatus.OK).body(contextServ.postContext(contextReq));
    }
}
