package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import web.model.User;
import web.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller()
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/users/create")
    public String createUser(@RequestParam(value = "name", required = false) String firstName,
                             @RequestParam(value = "lastName", required = false) String lastName,
                             @RequestParam(value = "email", required = false) String email,
                             Model model) {

        User newUser = new User(firstName, lastName, email);
        userService.create(newUser);
        model.addAttribute("newUser", newUser); // для таймлифа

        return "userCreated";
    }

    @GetMapping("/users")
    public String listUsers(ModelMap model) {
        List<User> listUsers = userService.readUsers();
        System.out.println(listUsers);
        if (listUsers.isEmpty()) {
            return "emptyList";
        }
        model.addAttribute("listUsers", listUsers);
        return "listUsers";
    }

    @PostMapping("/users/delete")
    public String deleteUser(@RequestParam(value = "id", required = false) long id, ModelMap model) {
        userService.delete(id);
        model.addAttribute("userDeleted", id);
        return "deleteUser";
    }

    @PostMapping("/users/update")
    public String updateUser(@RequestParam(value = "id", required = false) long id,
                           @RequestParam(value = "new_name", required = false) String firstName,
                           @RequestParam(value = "new_lastName", required = false) String lastName,
                           @RequestParam(value = "new_email", required = false) String email,ModelMap model) {
        userService.update(id, firstName, lastName, email);
        model.addAttribute("userUpdated", id);
        return "userUpdated";
    }
}
