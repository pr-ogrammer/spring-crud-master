package pl.prutkowski.spring.hibernate.service;

import pl.prutkowski.spring.hibernate.model.User;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by programmer on 10/8/16.
 */
public interface UserService {

    void saveUser(User user);
    void delete(User user);
    List<User> findAll();
    User getUser(String identityCardNo);
    List<User> findBySalaryMinMax(BigDecimal salaryMin, BigDecimal salaryMax);
}
