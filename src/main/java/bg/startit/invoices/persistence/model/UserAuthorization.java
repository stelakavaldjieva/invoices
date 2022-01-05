package bg.startit.invoices.persistence.model;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class UserAuthorization implements Serializable {

    private Long id_user;

    private String id_authorization;

}
