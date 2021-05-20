package com.example.machinecoding.kv_store.models;

import java.util.Objects;
import java.util.Optional;

@FunctionalInterface
public interface Validator {

    Optional<AttributeType> validate(String str);

    default Validator andThen(Validator after) {
        Objects.nonNull(after);
        return (String str) -> {
            Optional<AttributeType> type = validate(str);
            if (type.isPresent())
                return type;
            return after.validate(str);
        };
    }

}


