package by.lamaka.application.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.util.HashMap;
import java.util.Map;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CacheServer {
    @Getter
    static Map<Long, Employee> cache;

    static {
        cache = new HashMap<>();
    }

}
