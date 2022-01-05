package bg.startit.invoices.web.dto;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class UserDto implements Serializable {

    private Long id;
    
    private String username;
    
    private String email;
    
    private String password;
    
    private Long type;
    
    private LocalDateTime last_login_date;

    private Long status;

    public UserDto(Long id, String username, String email, String password, Long type, LocalDateTime last_login_date, Long status) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.type = type;
        this.last_login_date = last_login_date;
        this.status = status;
    }
}
