package ru.sin.narspigtwja.dto;

import ru.sin.narspigtwja.model.History;

import java.util.List;

public record ArchiveRes(
        List<History> history
) {
}
