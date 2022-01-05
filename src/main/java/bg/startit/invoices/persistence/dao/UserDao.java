package bg.startit.invoices.persistence.dao;

import bg.startit.invoices.persistence.mapper.UserRowMapper;
import bg.startit.invoices.persistence.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.util.List;

@Repository
@Transactional
public class UserDao extends JdbcDaoSupport {

    /**
     * Use basic JDBC operations using named parameters instead of '?' placeholders
     */
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public UserDao(DataSource dataSource) {

        namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);

        this.setDataSource(dataSource);
    }

    public Page<User> findAll(Pageable pageable) {

        final String query = "SELECT * FROM USERS";

        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();

        List<User> userList = namedParameterJdbcTemplate.query(query, mapSqlParameterSource, new UserRowMapper());

        return new PageImpl<>(userList, pageable, userList.size());
    }

    public User findByEmail(String email) {

        final String query = "SELECT * FROM USERS WHERE EMAIL = :email";

        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("email", email);

        return namedParameterJdbcTemplate.queryForObject(query, mapSqlParameterSource, new UserRowMapper());
    }

    public User findByUsername(String userName) {

        final String query = "SELECT * FROM USERS WHERE USERNAME = :username";

        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("username", userName);

        return namedParameterJdbcTemplate.queryForObject(query, mapSqlParameterSource, new UserRowMapper());
    }

    public boolean save(User user) {

        final String query = "INSERT INTO USERS (USERNAME, EMAIL, PASSWORD, TYPE, LAST_LOGIN_DATE, STATUS) " +
                "VALUES (:username, :email, :password, :type, :last_login_date, :status)";

        return namedParameterJdbcTemplate.update(
                query,
                new BeanPropertySqlParameterSource(user)) == 1;
    }
}
