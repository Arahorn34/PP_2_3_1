package web.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import web.model.User;

import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    private SessionFactory sessionFactory;

    @Autowired
    public UserDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> getAllUsers() {
        return sessionFactory.getCurrentSession().createQuery("from User").getResultList();
    }

    @Override
    @Transactional(readOnly = true)
    public User showUser(int id) {
        return sessionFactory.getCurrentSession().get(User.class, id);
    }

    @Override
    @Transactional
    public void delete(int id) {
        Session session = sessionFactory.getCurrentSession();
        session.remove(session.get(User.class, id));
    }

    @Override
    @Transactional
    public void save(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    @Transactional
    public void update(int id, User udatedUser) {
        User toBeUpdated = sessionFactory.getCurrentSession().get(User.class, id);

        toBeUpdated.setName(udatedUser.getName());
        toBeUpdated.setAge(udatedUser.getAge());
        toBeUpdated.setEmail(udatedUser.getEmail());
    }
}
