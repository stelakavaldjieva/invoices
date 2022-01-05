package bg.startit.invoices.persistence.model;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class User implements Serializable {

    private Long Id;

    private String username;

    private String email;

    private String password;

    private Long type;

    private LocalDateTime last_login_date;

    private Long status;

}
