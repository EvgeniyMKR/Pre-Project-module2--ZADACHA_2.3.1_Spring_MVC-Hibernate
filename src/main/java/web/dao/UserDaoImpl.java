package web.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private final EntityManager entityManager;

//    private final SessionFactory sessionFactory;

//    @Autowired
//    public UserDaoImpl(SessionFactory sessionFactory, EntityManager entityManager) {
//        this.sessionFactory = sessionFactory;
//        this.entityManager = entityManager;
//    }

    @Autowired
    public UserDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void create(User user) {
//        sessionFactory.getCurrentSession()
//                .save(user);
        entityManager.persist(user);
    }

    @Override
    public List<User> readUsers() {
//        return sessionFactory.getCurrentSession()
//                .createQuery("from User", User.class)
//                .list();
        return entityManager
                .createQuery("from User", User.class)
                .getResultList();
    }

    @Override
    public void update(long id, String firstName, String lastName, String email) {
//        User upsateUser = sessionFactory.getCurrentSession().load(User.class, id);
//        upsateUser.setFirstName(firstName != null ? firstName : upsateUser.getFirstName());
//        upsateUser.setLastName(lastName != null ? lastName : upsateUser.getLastName());
//        upsateUser.setEmail(email != null ? email : upsateUser.getEmail());

        User upsateUser = entityManager.find(User.class, id);
        upsateUser.setFirstName(firstName != null ? firstName : upsateUser.getFirstName());
        upsateUser.setLastName(lastName != null ? lastName : upsateUser.getLastName());
        upsateUser.setEmail(email != null ? email : upsateUser.getEmail());
    }

    @Override
    public void delete(long id) {
//        sessionFactory.getCurrentSession()
//                .createQuery("delete from User where id=:id")
//                .setParameter("id", id)
//                .executeUpdate();
        entityManager.remove(entityManager.find(User.class, id));
    }
}
