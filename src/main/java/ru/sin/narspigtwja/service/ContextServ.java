package ru.sin.narspigtwja.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import ru.sin.narspigtwja.dto.ContextReq;
import ru.sin.narspigtwja.dto.ContextRes;
import ru.sin.narspigtwja.tools.RabbitTools;

@Service
@RequiredArgsConstructor
public class ContextServ {
    private final RabbitTools rabbitTools;

    public ContextRes postContext(ContextReq contextReq) {
        try {
            return new ContextRes(rabbitTools.sendAndReceiveQuery(contextReq));
        }
        catch (Exception e) {
            return new ContextRes(null);
        }
    }
}
