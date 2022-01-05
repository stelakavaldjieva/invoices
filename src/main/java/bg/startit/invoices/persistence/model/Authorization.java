package bg.startit.invoices.persistence.model;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class Authorization implements Serializable {

    private Long id;

    private Long type;

}
