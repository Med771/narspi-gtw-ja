package ru.sin.narspigtwja.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.sin.narspigtwja.body.ArchiveReq;
import ru.sin.narspigtwja.body.ArchiveRes;
import ru.sin.narspigtwja.model.History;
import ru.sin.narspigtwja.tools.RabbitTools;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ArchiveServ {
    private final RabbitTools rabbitTools;

    public ArchiveRes postHistory(ArchiveReq archiveReq) {
        List<History> messages;

        try {
            messages = rabbitTools.sendAndReceiveHistory(archiveReq.page(), archiveReq.size());
        }
        catch (Exception e) {
            return new ArchiveRes(null);
        }

        return new ArchiveRes(messages);
    }
}
