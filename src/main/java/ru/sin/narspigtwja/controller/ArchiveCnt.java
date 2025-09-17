package ru.sin.narspigtwja.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.sin.narspigtwja.body.ArchiveReq;
import ru.sin.narspigtwja.body.ArchiveRes;
import ru.sin.narspigtwja.service.HistoryServ;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "archive")
public class ArchiveCnt {
    private final HistoryServ historyServ;

    @PostMapping()
    public ResponseEntity<ArchiveRes> postHistory(@RequestBody ArchiveReq req) {
        return ResponseEntity.status(HttpStatus.OK).body(historyServ.postHistory(req));
    }
}
