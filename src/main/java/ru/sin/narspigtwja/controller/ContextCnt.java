package ru.sin.narspigtwja.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.sin.narspigtwja.body.ContextReq;
import ru.sin.narspigtwja.body.ContextRes;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "context")
public class ContextCnt {
    @PostMapping()
    public ResponseEntity<ContextRes> postContext(@RequestBody ContextReq req) {
        return ResponseEntity.status(HttpStatus.OK).body(new ContextRes(""));
    }
}
