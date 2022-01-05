package bg.startit.invoices.persistence.model;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class AuthorizationType  implements Serializable {

    private Long id;

    private String name;

}
