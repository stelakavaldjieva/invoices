package bg.startit.invoices.service;

import bg.startit.invoices.persistence.dao.UserAuthorizationDao;
import bg.startit.invoices.persistence.dao.UserDao;
import bg.startit.invoices.persistence.dao.UserTypeDao;
import bg.startit.invoices.persistence.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final static Logger LOGGER = LoggerFactory.getLogger(UserDetailsService.class);

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserTypeDao userTypeDao;

    @Autowired
    private UserAuthorizationDao userAuthorizationDao;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userDao.findByUsername(userName);

        if (user == null) {
            LOGGER.debug("User not found: " + userName);
            throw new UsernameNotFoundException("User " + userName + " was not found in the database");
        }

        System.out.println("Found User: " + user);

        // [USER, ADMIN...]
        String userTypeName = userTypeDao.getUserType(user.getId());

        List<GrantedAuthority> grantList = new ArrayList<>();
        if (userTypeName != null) {

            // ROLE_USER, ROLE_ADMIN
            if (userTypeName.equals("USER")) {
                GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_USER");
                grantList.add(authority);
            }

            if (userTypeName.equals("ADMIN")) {
                GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_ADMIN");
                grantList.add(authority);
            }
        }

        UserDetails userDetails = new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), grantList);

        return userDetails;
    }
}
