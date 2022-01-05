package bg.startit.invoices.service;

import bg.startit.invoices.persistence.dao.UserDao;
import bg.startit.invoices.persistence.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public Page<User> findAll(Pageable pageable) {

        return userDao.findAll(pageable);
    }

    public boolean checkEmailExist(String email) {

        return userDao.findByEmail(email) != null;
    }

    public boolean checkUsernameExist(String username) {

        return userDao.findByUsername(username) != null;
    }

    public boolean registerUser(User user) {

        return userDao.save(user);
    }
}
