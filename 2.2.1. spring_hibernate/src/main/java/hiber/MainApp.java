package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.CarService;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class MainApp {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);
        CarService carService = context.getBean(CarService.class);

        Car car = new Car("BMW", 3);
        Car car1 = new Car("Lada", 1233);
        Car car2 = new Car("Honda", 2);
        Car car3 = new Car("Volvo", 50);
        carService.add(car);
        carService.add(car1);
        carService.add(car2);
        carService.add(car3);

        userService.add(new User("User1", "Lastname1", "user1@mail.ru", car));
        userService.add(new User("User2", "Lastname2", "user2@mail.ru", car1));
        userService.add(new User("User3", "Lastname3", "user3@mail.ru", car2));
        userService.add(new User("User4", "Lastname4", "user4@mail.ru", car3));

        List<User> users = userService.listUsers();
        for (User user : users) {
            System.out.println("Id = " + user.getId());
            System.out.println("First Name = " + user.getFirstName());
            System.out.println("Last Name = " + user.getLastName());
            System.out.println("Email = " + user.getEmail());
            System.out.println();
        }

        List<Car> cars = carService.listCars();
        for (Car c : cars) {
            System.out.println("User = " + userService.getUserByCar(c.getModel(), c.getSeries()));
            System.out.println();
        }

        context.close();
    }
}
