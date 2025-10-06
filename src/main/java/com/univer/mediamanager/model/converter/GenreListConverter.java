package com.univer.mediamanager.model.converter;

import com.univer.mediamanager.model.enums.Genre;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Converter
public class GenreListConverter implements AttributeConverter<List<Genre>, String> {

    private static final String DELIMITER = ",";

    @Override
    public String convertToDatabaseColumn(List<Genre> genres) {
        if (genres == null || genres.isEmpty()) {
            return "";
        }
        return genres.stream()
                .map(Enum::name)
                .collect(Collectors.joining(DELIMITER));
    }

    @Override
    public List<Genre> convertToEntityAttribute(String dbData) {
        if (dbData == null || dbData.trim().isEmpty()) {
            return List.of();
        }
        return Arrays.stream(dbData.split(DELIMITER))
                .map(String::trim)
                .map(Genre::valueOf)
                .collect(Collectors.toList());
    }
}