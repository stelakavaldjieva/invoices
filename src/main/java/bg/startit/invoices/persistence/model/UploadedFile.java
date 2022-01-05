package bg.startit.invoices.persistence.model;

import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class UploadedFile implements Serializable {

    private Long id;

    private String type;

    private Long file_number;

    private BigDecimal amount;

    private String currency;

    private String recipient;

    private Long id_recipient;

    private String issuer;

    private Long id_issuer;

    private LocalDateTime file_date;

    private Long payment_term;

    private LocalDateTime upload_timestamp;

    private Long id_user;

    private Long status;

}
