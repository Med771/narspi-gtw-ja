package ru.sin.narspigtwja.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.sin.narspigtwja.body.HistoryReq;
import ru.sin.narspigtwja.body.HistoryRes;
import ru.sin.narspigtwja.service.HistoryServ;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "history")
public class HistoryCnt {
    private final HistoryServ historyServ;

    @PostMapping()
    public ResponseEntity<HistoryRes> postHistory(@RequestBody HistoryReq req) {
        return ResponseEntity.status(HttpStatus.OK).body(historyServ.postHistory(req));
    }
}
