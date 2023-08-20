package ms.kx.Application.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Permissions {
    ROOT_READ("ROOT:READ"),
    ROOT_WRITE("ROOT:WRITE"),
    ROOT_UPDATE("ROOT:UPDATE"),
    ROOT_DELETE("ROOT:DELETE"),

    ADMIN_READ("ADMIN:READ"),
    ADMIN_WRITE("ADMIN:WRITE"),
    ADMIN_UPDATE("ADMIN:UPDATE"),
    ADMIN_DELETE("ADMIN:DELETE"),

    USER_READ("USER:READ"),
    USER_WRITE("USER:WRITE"),
    USER_UPDATE("USER:UPDATE"),
    USER_DELETE("USER:DELETE")
    ;

    @Getter
    private final String permission;
}
