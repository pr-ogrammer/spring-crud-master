package pl.prutkowski.spring.hibernate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.prutkowski.spring.hibernate.model.User;
import pl.prutkowski.spring.hibernate.repositories.UserDao;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by programmer on 10/8/16.
 */
@Transactional
@Service("userServiceImpl")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public void saveUser(User user) {
        userDao.saveUser(user);
    }

    @Override
    public void delete(User user) {
        userDao.delete(user);
    }

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Override
    public User getUser(String identityCardNo) {
        return userDao.getUser(identityCardNo);
    }

    @Override
    public List<User> findBySalaryMinMax(BigDecimal salaryMin, BigDecimal salaryMax) {
        return userDao.findBySalaryMinMax(salaryMin, salaryMax);
    }
}
