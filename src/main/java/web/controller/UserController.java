package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;
import java.util.List;

@Controller()
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create")
    public String createUser(@ModelAttribute User newUser, ModelMap model) {
        userService.create(newUser);
        model.addAttribute("newUser", newUser); // для таймлифа

        return "userCreated";
    }

    @GetMapping
    public String listUsers(ModelMap model) {
        List<User> listUsers = userService.readUsers();
        System.out.println(listUsers);
        if (listUsers.isEmpty()) {
            return "emptyList";
        }
        model.addAttribute("listUsers", listUsers);

        return "listUsers";
    }

    @PostMapping("/delete")
    public String deleteUser(@RequestParam(value = "id", required = false) long id, ModelMap model) {
        userService.delete(id);
        model.addAttribute("userDeleted", id);

        return "deleteUser";
    }

    @PostMapping("/update")
    public String updateUser(@ModelAttribute User user, ModelMap model) {
        userService.update(user.getId(), user);
        model.addAttribute("userUpdated", user.getId());

        return "userUpdated";
    }
}

