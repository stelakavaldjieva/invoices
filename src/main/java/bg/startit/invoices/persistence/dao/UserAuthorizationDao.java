package bg.startit.invoices.persistence.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;

@Repository
@Transactional
public class UserAuthorizationDao extends JdbcDaoSupport {

    /**
     * Use basic JDBC operations using named parameters instead of '?' placeholders
     */
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public UserAuthorizationDao(DataSource dataSource) {

        this.setDataSource(dataSource);
    }

    public String getUserAuthorizationNames(Long userId) {

        final String query = "SELECT at.name " +
                "FROM AUTHORIZATION_TYPE at, AUTHORIZATION a, USER_AUTHORIZATIONS ua, USERS u " +
                "WHERE ua.ID_USER = :userId AND a.ID = ua.ID_AUTHORIZATION AND at.ID = a.TYPE";

        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("userId", userId);

        return namedParameterJdbcTemplate.queryForObject(query, mapSqlParameterSource, String.class);
    }
}
