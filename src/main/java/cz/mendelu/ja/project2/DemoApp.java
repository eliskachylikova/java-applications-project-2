package cz.mendelu.ja.project2;

import cz.mendelu.ja.project2.demo.CarController;

public class DemoApp {

    private CarController carController;

    public static void main(String[] args) {
        var app = IOC.create(DemoApp.class);
        System.out.println(app.carController.post("ABC-1234", "Skoda"));
        System.out.println(app.carController.post("010-1010", "Skoda"));
        System.out.println(app.carController.getAll());
    }
}
