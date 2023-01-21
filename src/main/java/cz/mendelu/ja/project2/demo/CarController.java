package cz.mendelu.ja.project2.demo;

import java.util.stream.Collectors;

public class CarController {

    private CarService carService;
    private CarDao carDao;

    public String post(String spz, String znacka) {
        return carService.create(spz, znacka).toString();
    }

    public String getAll() {
        return carDao.findAll()
                .stream()
                .map(Car::toString)
                .collect(Collectors.joining());
    }
}
