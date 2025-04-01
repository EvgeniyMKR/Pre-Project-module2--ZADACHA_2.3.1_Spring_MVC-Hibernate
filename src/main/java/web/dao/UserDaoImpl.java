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

    @Autowired
    public UserDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void create(User user) {
        entityManager.persist(user);
    }

    @Override
    public List<User> readUsers() {
        return entityManager
                .createQuery("from User", User.class)
                .getResultList();
    }

    @Override
    public void update(long id, User user) {

        entityManager.createQuery("update User u set u.firstName = :firstName , u.lastName =:lastname, u.email = :email where u.id = :id")
                .setParameter("firstName", user.getFirstName())
                .setParameter("lastname", user.getLastName())
                .setParameter("email", user.getEmail())
                .setParameter("id", id)
                .executeUpdate();
    }

    @Override
    public void delete(long id) {
        entityManager.createQuery("delete from User where id =:id")
                .setParameter("id", id)
                .executeUpdate();
    }
}
