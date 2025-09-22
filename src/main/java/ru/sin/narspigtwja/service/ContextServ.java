package ru.sin.narspigtwja.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.sin.narspigtwja.dto.ContextReq;
import ru.sin.narspigtwja.dto.ContextRes;
import ru.sin.narspigtwja.tools.RabbitTools;
import ru.sin.narspigtwja.tools.YandexTools;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ContextServ {
    private final RabbitTools rabbitTools;

    private final YandexTools yandexTools;

    public ContextRes postContext(ContextReq contextReq) {
        List<String> docs;

        try {
            docs = rabbitTools.sendAndReceiveQuery(contextReq.uuid(), contextReq.query());
        }
        catch (Exception e) {
            return new ContextRes(null);
        }

        try {
             String ans = yandexTools.getQuery(contextReq.history(), docs, contextReq.query());

            return new ContextRes(ans);
        }
        catch (Exception e) {
            return new ContextRes(null);
        }
    }
}
