package by.lamaka.application.entity;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class CacheServer {
    private static Map<Long, Employee> cache;

    static {
        cache = new HashMap<>();
    }

    public static Map<Long, Employee> getCache() {
        return cache;
    }
}
