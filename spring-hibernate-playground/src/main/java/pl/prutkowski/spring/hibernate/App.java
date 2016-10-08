package pl.prutkowski.spring.hibernate;

import org.joda.time.LocalDate;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import pl.prutkowski.spring.hibernate.configuration.AppConfig;
import pl.prutkowski.spring.hibernate.model.User;
import pl.prutkowski.spring.hibernate.service.UserService;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by programmer on 10/8/16.
 */
public class App {

    public static void main(String[] args) {
        AbstractApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        UserService userService = (UserService) context.getBean("userServiceImpl");

        User user = new User();
        user.setName("Piotr");
        user.setSalary(new BigDecimal("10000"));
        user.setIdentityCartNo("123456");
        user.setStartedDate(new LocalDate().plusMonths(3));

        User anotherUser = new User();
        anotherUser.setName("Paweł");
        anotherUser.setSalary(new BigDecimal("12000"));
        anotherUser.setIdentityCartNo("654321");
        anotherUser.setStartedDate(new LocalDate());

        userService.saveUser(user);
        userService.saveUser(anotherUser);

        System.out.println();
        System.out.println();

        System.out.println("User: ");
        System.out.println("----------------------------------");
        System.out.println(user);
        System.out.println("----------------------------------");
        System.out.println();

        System.out.println("Another user: ");
        System.out.println("----------------------------------");
        System.out.println(anotherUser);
        System.out.println("----------------------------------");
        System.out.println();

        System.out.println("User list: ");
        System.out.println("----------------------------------");
        List<User> usersList = userService.findAll();
        usersList.forEach(System.out::println);
        System.out.println("----------------------------------");
        System.out.println();

        System.out.println("Find by identity cardo no: 123456");
        System.out.println("----------------------------------");
        System.out.println(userService.getUser("123456"));
        System.out.println("----------------------------------");
        System.out.println();

        System.out.println("Find by identity cardo no: 654321");
        System.out.println("----------------------------------");
        System.out.println(userService.getUser("123456"));
        System.out.println("----------------------------------");
        System.out.println();

        System.out.println("Find by identity cardo no: n/a");
        System.out.println("----------------------------------");
        System.out.println(userService.getUser("n/a"));
        System.out.println("----------------------------------");
        System.out.println();

        System.out.println("Find by salary min/max: 9000-9500");
        System.out.println("----------------------------------");
        usersList = userService.findBySalaryMinMax(new BigDecimal("9000"), new BigDecimal("9500"));
        usersList.forEach(System.out::println);
        System.out.println("----------------------------------");
        System.out.println();

        System.out.println("Find by salary min/max: 9000-10000");
        System.out.println("----------------------------------");
        usersList = userService.findBySalaryMinMax(new BigDecimal("9000"), new BigDecimal("10000"));
        usersList.forEach(System.out::println);
        System.out.println("----------------------------------");
        System.out.println();

        System.out.println("Find by salary min/max: 10000-12000");
        System.out.println("----------------------------------");
        usersList = userService.findBySalaryMinMax(new BigDecimal("10000"), new BigDecimal("12000"));
        usersList.forEach(System.out::println);
        System.out.println("----------------------------------");
        System.out.println();

        System.out.println("Delete user");
        System.out.println("----------------------------------");
        System.out.println(user);
        userService.delete(user);
        System.out.println("----------------------------------");
        System.out.println();

        System.out.println("Find by identity cardo no: 123456");
        System.out.println("----------------------------------");
        System.out.println(userService.getUser("123456"));
        System.out.println("----------------------------------");
        System.out.println();

        System.out.println("Delete another user");
        System.out.println("----------------------------------");
        System.out.println(anotherUser);
        userService.delete(anotherUser);
        System.out.println("----------------------------------");
        System.out.println();


        System.out.println("User list: ");
        System.out.println("----------------------------------");
        usersList = userService.findAll();
        usersList.forEach(System.out::println);
        System.out.println("----------------------------------");
        System.out.println();
    }
}
