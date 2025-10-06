package com.univer.mediamanager.model.converter;

import com.univer.mediamanager.model.enums.Language;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Converter
public class LanguageListConverter implements AttributeConverter<List<Language>, String> {

    private static final String DELIMITER = ",";

    @Override
    public String convertToDatabaseColumn(List<Language> languages) {
        if (languages == null || languages.isEmpty()) {
            return "";
        }
        return languages.stream()
                .map(Enum::name)
                .collect(Collectors.joining(DELIMITER));
    }

    @Override
    public List<Language> convertToEntityAttribute(String dbData) {
        if (dbData == null || dbData.trim().isEmpty()) {
            return List.of();
        }
        return Arrays.stream(dbData.split(DELIMITER))
                .map(String::trim)
                .map(Language::valueOf)
                .collect(Collectors.toList());
    }
}