package ru.sin.narspigtwja.dto;

import ru.sin.narspigtwja.model.HistoryMessage;

import java.util.List;
import java.util.UUID;

public record ContextReq(
        UUID uuid,
        String query,
        List<HistoryMessage> history
) {
}
