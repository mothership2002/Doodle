package hyun.dashboard.publisher.member.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Setter
public class MemberReq {

    private Long id;

    @NotBlank(message = "Account cannot be blank")
    @Size(min = 6, max = 13, message = "Account length must be between 6 and 20")
    private String account;

    @NotBlank(message = "Password cannot be blank")
    @Size(min = 9, max = 20, message = "Password length must be between 9 and 20")
    private String password;

    public MemberReq(String account, String password) {
        this.account = account;
        this.password = password;
    }

    /** Dynamic column field function
     * ** Risky ?
     * @return query column for update
     */
    public Map<String, String> getUpdateMap() {
        return Arrays.stream(this.getClass().getDeclaredFields())
                .filter(e -> !e.getName().equals("id"))
                .collect(Collectors.toMap(
                        Field::getName, e -> {
                            try {
                                e.setAccessible(true);
                                return (String) e.get(this);
                            } catch (IllegalAccessException ex) {
                                throw new RuntimeException(ex);
                            }
                        }
                ));
    }
}
