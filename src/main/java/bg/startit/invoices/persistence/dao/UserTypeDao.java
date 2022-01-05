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
public class UserTypeDao extends JdbcDaoSupport {

    /**
     * Use basic JDBC operations using named parameters instead of '?' placeholders
     */
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public UserTypeDao(DataSource dataSource) {

        namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);

        this.setDataSource(dataSource);
    }

    public String getUserType(Long userId) {

        final String query = "SELECT ut.name " +
                "FROM USER_TYPE ut, USERS u " +
                "WHERE u.ID = :userId AND ut.ID = u.TYPE";

        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("userId", userId);

        return namedParameterJdbcTemplate.queryForObject(query, mapSqlParameterSource, String.class);
    }
}
