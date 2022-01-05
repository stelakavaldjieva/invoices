package bg.startit.invoices.web.dto;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class UserListDto {

    private Integer pageNumber;
    private Integer pageSize;
    private Integer count;
    private Integer total;
    private List<UserDto> content;

    public UserListDto(Integer pageNumber, Integer pageSize, Integer count, Integer total,
                      List<UserDto> content)
    {
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
        this.count = count;
        this.total = total;
        this.content = content;
    }
}
