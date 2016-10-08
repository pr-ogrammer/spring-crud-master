package pl.prutkowski.spring.hibernate.repositories;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import pl.prutkowski.spring.hibernate.model.User;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by programmer on 10/8/16.
 */
@Repository("userDaoImpl")
public class UserDaoImpl extends AbstractDao implements UserDao {
    @Override
    public void saveUser(User user) {
        persist(user);
    }

    @Override
    public void delete(User user) {
        Query query = getSession().createQuery("delete from User where identityCartNo = :identityCartNo");
        query.setString("identityCartNo", user.getIdentityCartNo());
        query.executeUpdate();
    }

    @Override
    public List<User> findAll() {
        Criteria criteria = getSession().createCriteria(User.class);
        return (List<User>) criteria.list();
    }

    @Override
    public User getUser(String identityCardNo) {
        Criteria criteria = getSession().createCriteria(User.class);
        criteria.add(Restrictions.eq("identityCartNo", identityCardNo));
        return (User) criteria.uniqueResult();
    }

    @Override
    public List<User> findBySalaryMinMax(BigDecimal salaryMin, BigDecimal salaryMax) {
        Criteria criteria = getSession().createCriteria(User.class);
        criteria.add(Restrictions.between("salary", salaryMin, salaryMax));
        return (List<User>) criteria.list();
    }
}
