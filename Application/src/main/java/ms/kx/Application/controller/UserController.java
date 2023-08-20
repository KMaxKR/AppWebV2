package ms.kx.Application.controller;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import ms.kx.Application.dto.UserDTO;
import ms.kx.Application.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @RequestMapping("/api/v1/dashboard/change/{id}")
    public void getMainPageAfterSuccessChange(@PathVariable(name = "id") Long id,
                                                @RequestParam(name = "username") String username,
                                                @RequestParam(name = "password") String password,
                                                @RequestParam(name = "email") String email,
                                                @RequestParam(name = "user_icon") String user_icon,
                                                HttpServletResponse response) throws IOException {
        userService.changeUserLabels(id, UserDTO.builder()
                        .username(username)
                        .password(password)
                        .email(email)
                        .user_icon(user_icon)
                .build());
        response.setStatus(200);
        response.sendRedirect("/");
    }

    @RequestMapping("/register")
    public void registerUser(@RequestParam(name = "username")String username,
                             @RequestParam(name = "email")String email,
                             @RequestParam(name = "password")String password,
                             HttpServletResponse response) throws IOException {
        userService.registerNewUser(UserDTO.builder()
                .username(username)
                .email(email)
                .password(password)
                .build());
        response.setStatus(200);
        response.sendRedirect("/");
    }
}
