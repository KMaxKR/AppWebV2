package ms.kx.Application.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import static ms.kx.Application.entity.Permissions.*;

@RequiredArgsConstructor
public enum Roles {
    Guest(
            Collections.EMPTY_SET
    ),

    USER(
            Set.of(
                    USER_READ,
                    USER_WRITE
            )),

    FULL_USER(
            Set.of(
                    USER_READ,
                    USER_WRITE,
                    USER_UPDATE,
                    USER_DELETE
            )),

    ADMIN(
            Set.of(
                    ADMIN_READ,
                    ADMIN_WRITE
            )),

    FULL_ADMIN(
            Set.of(
                    ADMIN_READ,
                    ADMIN_WRITE,
                    ADMIN_UPDATE,
                    ADMIN_DELETE
            )),

    ROOT(
            Set.of(
                    ROOT_READ,
                    ROOT_WRITE,
                    ROOT_UPDATE,
                    ROOT_DELETE,
                    ADMIN_READ,
                    ADMIN_WRITE,
                    ADMIN_UPDATE,
                    ADMIN_DELETE,
                    USER_READ,
                    USER_WRITE,
                    USER_UPDATE,
                    USER_DELETE
            ));

    @Getter
    private final Set<Permissions> permissionsSet;

    public List<SimpleGrantedAuthority> getAuthorities(){
        var authorities = new java.util.ArrayList<>(getPermissionsSet()
                .stream()
                .map(permissions -> new SimpleGrantedAuthority(permissions.name()))
                .toList());
        authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return authorities;
    }
}
