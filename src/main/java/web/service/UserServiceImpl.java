package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.dao.UserDao;
import web.model.User;

import java.util.List;
import java.util.logging.Logger;

@Service
public class UserServiceImpl implements UserService {

    private final Logger logger = Logger.getLogger(this.getClass().getName());
    private final UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    @Transactional
    public void create(User user) {
        userDao.create(user);
        logger.info("User created: " + user);
    }

    @Override
    @Transactional
    public List<User> readUsers() {
        return userDao.readUsers();
    }

    @Override
    @Transactional
    public void update(long id, User user) {
        userDao.update(id, user);
        logger.info("User updated");
    }

    @Override
    @Transactional
    public void delete(long id) {
        userDao.delete(id);
        logger.info("User deleted with id = " + id + " deleted.");
    }
}
