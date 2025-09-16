package ru.sin.narspigtwja.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record History(
        LocalDateTime queryDate,
        String query,
        LocalDate answerDate,
        String answer
) {
}
