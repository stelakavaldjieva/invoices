package bg.startit.invoices.web.dto;

import bg.startit.invoices.persistence.model.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class UserCreationDto {

    private List<User> users;

    public UserCreationDto(List<User> users) {
        this.users = users;
    }

    public void addUser(User user) {
        this.users.add(user);
    }
}
