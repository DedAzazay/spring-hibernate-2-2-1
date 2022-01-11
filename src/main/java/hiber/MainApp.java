package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      for (int i = 1; i < 5; i++) {
         User user = new User("User" + i, "Lastname" + i,
                 "user" + i + "@mail.ru");
         Car car = new Car("Model" + i, i * 28);
         user.setCar(car);
         userService.add(user);
      }



      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println("-----CAR------");
         System.out.println(("Car =[model ="+user.getCar().getModel())+
                 "; series =" + user.getCar().getSeries() + "]");
         System.out.println();
      }

      User userByCar = userService.getUserByCar("model3", 84);
      System.out.println("User by car");
      System.out.println("Id = "+userByCar.getId());
      System.out.println("First Name = "+userByCar.getFirstName());
      System.out.println("Last Name = "+userByCar.getLastName());
      System.out.println("Email = "+userByCar.getEmail());


      context.close();
   }
}
