package ru.sin.narspigtwja.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.sin.narspigtwja.dto.ContextReq;
import ru.sin.narspigtwja.dto.ContextRes;
import ru.sin.narspigtwja.service.ContextServ;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "context")
public class ContextCnt {
    private final ContextServ contextServ;

    @PostMapping()
    public ResponseEntity<ContextRes> postContext(@RequestBody ContextReq contextReq) {
        return ResponseEntity.status(HttpStatus.OK).body(contextServ.postContext(contextReq));
    }
}
