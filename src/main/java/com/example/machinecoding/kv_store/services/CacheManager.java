package com.example.machinecoding.kv_store.services;

import com.example.machinecoding.kv_store.models.Attribute;
import com.example.machinecoding.kv_store.models.AttributeKey;
import com.example.machinecoding.kv_store.models.AttributeType;
import com.example.machinecoding.kv_store.models.TypeValidator;
import javafx.util.Pair;

import java.util.*;

public class CacheManager {

    private Map<String, List<Attribute>> keyValueMap;
    private Map<String, Set<String>> attributeCache;
    private Map<String, AttributeKey> attributeTypeMap;

    public CacheManager() {
        keyValueMap = new HashMap<>();
        attributeCache = new HashMap<>();
        attributeTypeMap = new HashMap<>();
    }

    public void get(String key) {
        if (!keyValueMap.containsKey(key))
            System.out.println("No entry found for " + key);
        else
            System.out.println(keyValueMap.get(key));
    }

    public void search(String attrKey, String attrVal) {
        String key = Utils.getAttributeKey(attrKey, attrVal);
        if (attributeCache.containsKey(key)) {
            System.out.println(attributeCache.get(key));
        } else {
            System.out.println("No attribute entry found for " + attrKey + "," + attrVal);
        }
    }

    public void put(String key, List<Pair<String, String>> listOfAttributePairs) {
        if (listOfAttributePairs == null || listOfAttributePairs.isEmpty())
            throw new IllegalArgumentException("Attribute List should not be empty");

        List<Attribute> attributes = new ArrayList<>();

        for (Pair<String, String> pair: listOfAttributePairs) {
            String attrKey = pair.getKey();
            String attrVal = pair.getValue();

            // Type Inference
            AttributeType attrType = inferAttributeType(attrKey, attrVal);
            AttributeKey attrKeyObj = new AttributeKey(attrKey, attrType);
            attributeTypeMap.put(attrKey, attrKeyObj);

            // Validation + Creation
            Attribute attribute = Attribute.build(attrKeyObj, attrVal);
            attributes.add(attribute);

            // add to attributeCache
            Set<String> attrKeysSet;
            String attributeKeyStr = Utils.getAttributeKey(attrKey, attrVal);
            if (!attributeCache.containsKey(attributeKeyStr)) {
                attrKeysSet = new LinkedHashSet<>();
                attributeCache.put(attributeKeyStr, attrKeysSet);
            } else {
                attrKeysSet = attributeCache.get(attributeKeyStr);
            }
            attrKeysSet.add(key);
        }

        // add to main kv table
        keyValueMap.put(key, attributes);

    }

    private AttributeType inferAttributeType(String key, String val) {
        AttributeType inferredType = TypeValidator.inferType(val);;
        if (attributeTypeMap.containsKey(key)) {
            AttributeType existingType = attributeTypeMap.get(key).getType();
            if (existingType != inferredType)
                throw new IllegalArgumentException("Data Type Error");
        }
        return inferredType;
    }

    // No need to delete from attributeTypeMap
    public void delete(String key) {
        keyValueMap.remove(key); // remove from main map

        List<String> keysToBeRemoved = new ArrayList<>();
        for (String attrKey: attributeCache.keySet()) {
            Set<String> val = attributeCache.get(attrKey);
            if (val.contains(key))  val.remove(key);    // remove the key from cache
            if (val.size() == 0)    // remove the attrikv if set is empty
                keysToBeRemoved.add(attrKey);
        }

        keysToBeRemoved.stream().forEach(k -> attributeCache.remove(k));
    }

    public void keys() {
        System.out.println(keyValueMap.keySet());
    }

}
