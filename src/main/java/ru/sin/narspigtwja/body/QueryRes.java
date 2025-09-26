package ru.sin.narspigtwja.body;

import java.util.UUID;

public record QueryRes(
        UUID uuid,
        String answer
) {
}
