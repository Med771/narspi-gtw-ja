package ru.sin.narspigtwja.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.sin.narspigtwja.body.ContextReq;
import ru.sin.narspigtwja.body.ContextRes;
import ru.sin.narspigtwja.tools.RabbitTools;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ContextServ {
    private final RabbitTools rabbitTools;

    public ContextRes postContext(ContextReq contextReq) {
        List<String> docs;

        try {
            docs = rabbitTools.sendAndReceiveQuery(contextReq.query());
        }
        catch (Exception e) {
            return new ContextRes(null);
        }

        String ans = "ans";

        return new ContextRes(ans);
    }
}
