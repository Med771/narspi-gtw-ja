package ru.sin.narspigtwja.body;

import java.util.UUID;

public record QueryReq(
        UUID uuid,
        String query
) {
}
