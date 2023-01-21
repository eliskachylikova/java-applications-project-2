package cz.mendelu.ja.project2.demo;

import cz.mendelu.ja.project2.annotations.Component;

import java.util.*;

@Component
public class CarDao {

    private Map<String, Car> cars = new HashMap<>();

    public Car save(Car car) {
        var withId = car.with(UUID.randomUUID());
        cars.put(car.getSpz(), withId);
        return withId;
    }

    public Collection<Car> findAll() {
        return cars.values();
    }
}
