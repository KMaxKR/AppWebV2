package ms.kx.Application.controller;

import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import ms.kx.Application.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.security.Principal;

@Controller
@AllArgsConstructor
public class MainController {
    private final UserService userService;

    @RequestMapping("/")
    public void getMainPage(HttpServletResponse response) throws IOException {
        response.sendRedirect("/api/v1/dashboard");
    }

    @RequestMapping("/api/v1/dashboard")
    public String getDashBoardPage(Model model, Principal principal){
        if (principal == null) return "login";
        model.addAttribute("user", userService.loadUserByUsername(principal.getName()));
        return "main";
    }

    @RequestMapping("/api/v1/dashboard/{id}")
    public String getChangePage(@PathVariable(name = "id") Long id, Model model){
        model.addAttribute("user", userService.getUserById(id));
        return "change";
    }

}
