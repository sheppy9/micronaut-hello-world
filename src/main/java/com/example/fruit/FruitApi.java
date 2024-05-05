package com.example.fruit;


import io.micronaut.http.annotation.*;
import io.micronaut.serde.ObjectMapper;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Controller(value = "/fruit")
public class FruitApi {

    private final Map<Long, Fruit> fruits = new HashMap<>();

    public Map<Long, Fruit> getFruits() {
        if (fruits.isEmpty()) {
            Fruit fruit1 = new Fruit(1L, "apple", 1.1);
            Fruit fruit2 = new Fruit(2L, "orange", 2.2);
            Fruit fruit3 = new Fruit(3L, "watermelon", 3.3);

            fruits.put(fruit1.getId(), fruit1);
            fruits.put(fruit2.getId(), fruit2);
            fruits.put(fruit3.getId(), fruit3);
        }
        return fruits;
    }

    @Get(value = "/api/all")
    public Object getAll() {
        return getFruits().values();
    }

    @Get(value = "/api/{id}")
    public Object getById(Long id) {
        return getFruits().get(id);
    }

    @Post(value = "/api/newFruit")
    public Object newEntity(@Body String jsonBody) {
        Map<Long, Fruit> fruits = getFruits();
        Long lastId = Collections.max(fruits.keySet());
        ObjectMapper mapper = ObjectMapper.getDefault();
        try {
            Long newId = lastId + 1;
            Fruit fruit = mapper.readValue(jsonBody, Fruit.class);
            fruit.setId(newId);
            fruits.put(newId, fruit);
            System.out.println("Added new fruit into list. id: " + newId + ", fruit: " + jsonBody);
            return fruits.get(newId);
        } catch (IOException e) {
            System.out.print("Error reading json body " + e.getMessage());
        }
        return "Error reading body";
    }

    @Put(value = "/api/{id}")
    public Object updateEntity(Long id, @Body String body) {
        Map<Long, Fruit> fruits = getFruits();
        if (!fruits.containsKey(id)) {
            return "Fruit not found";
        }
        ObjectMapper mapper = ObjectMapper.getDefault();
        try {
            Fruit fruit = mapper.readValue(body, Fruit.class);
            fruit.setId(id);
            fruits.put(id, fruit);
        } catch (IOException e) {
            System.out.print("Error reading json body " + e.getMessage());
        }
        return fruits.get(id);
    }

    @Delete(value = "/api/{id}")
    public Object removeEntity(Long id) {
        Map<Long, Fruit> fruits = getFruits();
        if (!fruits.containsKey(id)) {
            return "Fruit not found";
        }
        return fruits.remove(id);
    }
}
