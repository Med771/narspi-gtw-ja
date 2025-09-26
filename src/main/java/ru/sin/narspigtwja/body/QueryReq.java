package ru.sin.narspigtwja.body;

import ru.sin.narspigtwja.model.HistoryMessage;

import java.util.List;
import java.util.UUID;

public record QueryReq(
        UUID uuid,
        String query,
        List<HistoryMessage> history
) {
}
