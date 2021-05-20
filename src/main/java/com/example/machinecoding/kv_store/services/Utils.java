package com.example.machinecoding.kv_store.services;

public class Utils {

    public static String getAttributeKey(String key, String val) {
        return key + "#" + val;
    }
}