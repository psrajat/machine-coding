package com.example.machinecoding.kv_store.models;

public class Attribute {

    private AttributeKey key;
    private String value;

    private Attribute(AttributeKey key, String value) {
        this.key = key;
        this.value = value;
    }

    public static Attribute build(AttributeKey key, String value) {
        return new Attribute(key, value);
    }

    @Override
    public String toString() {
        return "{" + key.getKey() + ": " + value + "}";
    }
}
