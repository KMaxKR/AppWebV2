package ms.kx.Application.dto;

import lombok.*;

@Builder
@Data
@AllArgsConstructor
public class UserDTO {

    @NonNull
    private String username;

    @NonNull
    private String password;

    private String email;

    private String user_icon;
}
