package ms.kx.Application.service;

import lombok.RequiredArgsConstructor;
import ms.kx.Application.dto.UserDTO;
import ms.kx.Application.entity.Roles;
import ms.kx.Application.entity.User;
import ms.kx.Application.repository.UserRepository;
import ms.kx.Application.utility.RegisterAuthWithMail;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final RegisterAuthWithMail registerAuthWithMail;
    String DEFAULT_USER_ICON_URL = "https://uxwing.com/wp-content/themes/uxwing/download/peoples-avatars/corporate-user-icon.png";
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findUserByUsername(username).orElseThrow(() -> new UsernameNotFoundException("UsernameNotFoundException"));
    }

    public User getUserById(Long id){
        return userRepository.findById(id).orElseThrow();
    }

    public void changeUserLabels(Long id, UserDTO userDTO){
        User user = getUserById(id);
        if (userDTO.getUser_icon().isBlank()) userDTO.setUser_icon(DEFAULT_USER_ICON_URL);
        userRepository.save(
                User.builder()
                        .id(id)
                        .username(userDTO.getUsername())
                        .email(userDTO.getEmail())
                        .password(new BCryptPasswordEncoder(12).encode(userDTO.getPassword()))
                        .user_icon(userDTO.getUser_icon())
                        .role(user.getRole())
                        .account_non_locked(user.isAccount_non_locked())
                        .build()
        );
    }

    public void registerNewUser(UserDTO userDTO){
            User user = User.builder()
                    .username(userDTO.getUsername())
                    .email(userDTO.getEmail())
                    .password(new BCryptPasswordEncoder(12).encode(userDTO.getPassword()))
                    .user_icon(DEFAULT_USER_ICON_URL)
                    .account_non_locked(true)
                    .role(Roles.USER)
                    .build();
            userRepository.save(user);
    }

}
