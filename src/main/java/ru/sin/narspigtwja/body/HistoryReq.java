package ru.sin.narspigtwja.body;

import java.util.UUID;

public record HistoryReq(
        UUID uuid,
        int page,
        int size
) {
}
