package ru.sin.narspigtwja.model;

import java.time.LocalDateTime;

public record History(
        LocalDateTime queryDate,
        String query,
        LocalDateTime answerDate,
        String answer
) {
}
