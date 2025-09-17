package ru.sin.narspigtwja.body;

import java.util.List;
import java.util.UUID;

public record QueryRes(
        UUID uuid,
        List<String> docs
) {
}
