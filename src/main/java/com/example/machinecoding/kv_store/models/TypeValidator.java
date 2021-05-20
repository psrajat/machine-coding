package com.example.machinecoding.kv_store.models;

import java.util.Optional;

public class TypeValidator {

    public static Validator boolValidator = (String str) -> {
        if ("true".equalsIgnoreCase(str) || "false".equalsIgnoreCase(str))
            return Optional.of(AttributeType.BOOLEAN);
        return Optional.empty();
    };

    public static Validator intValidator = (String str) -> {
        try {
            if (str == null)
                return Optional.empty();
            Integer.parseInt(str);
            return Optional.of(AttributeType.INTEGER);
        } catch (NumberFormatException ex) {
            return Optional.empty();
        }
    };

    public static Validator doubleValidator = (String str) -> {
        try {
            if (str == null)
                return Optional.empty();
            Double.parseDouble(str);
            return Optional.of(AttributeType.DOUBLE);
        } catch (NumberFormatException ex) {
            return Optional.empty();
        }
    };

    public static AttributeType inferType(String str) {
        return boolValidator
                .andThen(intValidator)
                .andThen(doubleValidator)
                .validate(str)
                .orElse(AttributeType.STRING);
    }

}