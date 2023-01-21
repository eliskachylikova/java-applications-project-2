package cz.mendelu.ja.project2.demo;

public class Application {

    public static void main(String[] args) {
        try {
            ApplicationContext applicationContext = new ApplicationContext(ApplicationConfig.class);
            CarController carController = applicationContext.getInstance(CarController.class);

            carController.post("123", "Hondicka");
            System.out.println(carController.getAll());

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
