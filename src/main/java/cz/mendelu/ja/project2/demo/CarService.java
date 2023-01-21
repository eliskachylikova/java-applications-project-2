package cz.mendelu.ja.project2.demo;

public class CarService {

    private CarDao carDao;
    private ZnackaService znackaService;

    public Car create(String spz, String znacka) {
        if (!znackaService.isValid(znacka)) {
            throw new IllegalArgumentException("Neni Skoda");
        }
        var car = new Car(spz, znacka);
        var saved = carDao.save(car);
        return saved;
    }

}
