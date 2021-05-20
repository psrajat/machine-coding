package com.example.machinecoding.kv_store;

import com.example.machinecoding.kv_store.services.CacheManager;
import javafx.util.Pair;

import java.util.Arrays;

public class Driver {

    public static void main(String[] args) {

        CacheManager cacheManager = new CacheManager();

        cacheManager.put("sde_bootcamp", Arrays.asList(
                new Pair<String, String>("title", "SDE-Bootcamp"),
                new Pair<String, String>("price", "30000.00"),
                new Pair<String, String>("enrolled", "false"),
                new Pair<String, String>("estimated_time", "30")
        ));
        cacheManager.get("sde_bootcamp");
        cacheManager.keys();


        try {
            cacheManager.put("sde_kickstart", Arrays.asList(
                    new Pair<String, String>("title", "SDE-Kickstart"),
                    new Pair<String, String>("price", "4000"),
                    new Pair<String, String>("enrolled", "true"),
                    new Pair<String, String>("estimated_time", "8")
            ));
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        cacheManager.get("sde_kickstart");
        cacheManager.keys();



        try {
            cacheManager.put("sde_kickstart", Arrays.asList(
                    new Pair<String, String>("title", "SDE-Kickstart"),
                    new Pair<String, String>("price", "4000.00"),
                    new Pair<String, String>("enrolled", "true"),
                    new Pair<String, String>("estimated_time", "8")
            ));
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        cacheManager.get("sde_kickstart");
        cacheManager.keys();


        cacheManager.delete("sde_bootcamp");
        cacheManager.get("sde_bootcamp");
        cacheManager.keys();


        cacheManager.put("sde_bootcamp", Arrays.asList(
                new Pair<String, String>("title", "SDE-Bootcamp"),
                new Pair<String, String>("price", "30000.00"),
                new Pair<String, String>("enrolled", "true"),
                new Pair<String, String>("estimated_time", "30")
        ));
        cacheManager.search("price", "30000.00");
        cacheManager.search("enrolled", "true");

    }

}
