package bg.startit.invoices.persistence.mapper;

import bg.startit.invoices.persistence.model.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

/**
 * Convert db data for user to User entity.
 */
public class UserRowMapper implements RowMapper<User> {

    /**
     * Convert row from table SALE to User entity.
     */
    @Override
    public User mapRow(ResultSet resultSet, int i) throws SQLException {
        User user = new User();

        user.setId(resultSet.getLong("ID"));
        user.setUsername(resultSet.getString("USERNAME"));
        user.setEmail(resultSet.getString("EMAIL"));
        user.setPassword(resultSet.getString("PASSWORD"));
        user.setType(resultSet.getLong("TYPE"));
        user.setLast_login_date(resultSet.getObject(6, LocalDateTime.class));
        user.setType(resultSet.getLong("STATUS"));

        return user;
    }
}
