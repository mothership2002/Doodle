package hyun.dashboard.publisher.common.model.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Sort;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrderBy {

    private String columnName;
    private Sort.Direction direction;

    public OrderBy(String columnName, String direction) {
        this.columnName = columnName;
        this.direction = Sort.Direction.fromString(direction);
    }
}

