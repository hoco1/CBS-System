package com.example.CBS.model;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply=true)
public class SubscriberTypeConverter implements AttributeConverter<SubscriberType, String> {

    @Override
    public String convertToDatabaseColumn(SubscriberType subscriberType) {
        // Converts SubscriberType to String when saving to the database
        if (subscriberType == null) {
            return null;
        }
        return subscriberType.getCode(); // Uses the code value ('0' or '1')
    }

    @Override
    public SubscriberType convertToEntityAttribute(String code) {
        // Converts the String from the database to SubscriberType
        if (code == null) {
            return null;
        }
        for (SubscriberType type : SubscriberType.values()) {
            if (type.getCode().equals(code)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Unknown subscriber type code: " + code);
    }
}
