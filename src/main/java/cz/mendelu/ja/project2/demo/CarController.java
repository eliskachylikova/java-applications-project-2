package cz.mendelu.ja.project2.demo;

import cz.mendelu.ja.project2.annotations.Autowired;
import cz.mendelu.ja.project2.annotations.Component;

import java.util.stream.Collectors;

@Component
public class CarController {

    @Autowired
    private CarService carService;
    @Autowired
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
