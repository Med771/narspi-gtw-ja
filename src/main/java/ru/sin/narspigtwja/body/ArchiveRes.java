package ru.sin.narspigtwja.body;

import ru.sin.narspigtwja.model.History;

import java.util.List;

public record ArchiveRes(
        List<History> history
) {
}
