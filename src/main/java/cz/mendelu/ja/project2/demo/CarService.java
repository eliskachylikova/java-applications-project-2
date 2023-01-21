package cz.mendelu.ja.project2.demo;

import cz.mendelu.ja.project2.annotations.Autowired;
import cz.mendelu.ja.project2.annotations.Component;

@Component
public class CarService {

    @Autowired
    private CarDao carDao;

    @Autowired
    private ZnackaService znackaService;

    public Car create(String spz, String znacka) {
        if (!znackaService.isValid(znacka)) {
            throw new IllegalArgumentException("Neni Hondicka");
        }
        var car = new Car(spz, znacka);
        var saved = carDao.save(car);
        return saved;
    }

}
