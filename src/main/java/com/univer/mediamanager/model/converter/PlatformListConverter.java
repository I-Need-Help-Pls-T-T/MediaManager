package com.univer.mediamanager.model.converter;

import com.univer.mediamanager.model.enums.Platform;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Converter(autoApply = false)
public class PlatformListConverter implements AttributeConverter<List<Platform>, String> {

    private static final String SEPARATOR = ",";

    @Override
    public String convertToDatabaseColumn(List<Platform> attribute) {
        if (attribute == null || attribute.isEmpty()) {
            return null;
        }
        return attribute.stream()
                .map(Platform::name)
                .collect(Collectors.joining(SEPARATOR));
    }

    @Override
    public List<Platform> convertToEntityAttribute(String dbData) {
        if (dbData == null || dbData.trim().isEmpty()) {
            return List.of();
        }

        return Arrays.stream(dbData.split(SEPARATOR))
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .map(Platform::valueOf)
                .collect(Collectors.toList());
    }
}