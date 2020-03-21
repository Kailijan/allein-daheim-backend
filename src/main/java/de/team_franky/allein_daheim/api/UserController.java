package de.team_franky.allein_daheim.api;

import de.team_franky.allein_daheim.business.service.UserService;
import de.team_franky.allein_daheim.data.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.websocket.server.PathParam;
import java.util.Date;

@RestController
@RequestMapping(value = "/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/{id}")
    public User findOne(@PathVariable Long id) {
        return userService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping(value = "/{id}/seen")
    public User updateLastSeen(@PathVariable Long id) {
        return userService.findById(id)
                .map(user -> {
                    user.setLastSeen(new java.sql.Date(new Date().getTime()));
                    return userService.save(user);
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

}
