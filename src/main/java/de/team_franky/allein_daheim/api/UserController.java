package de.team_franky.allein_daheim.api;

import de.team_franky.allein_daheim.business.service.UserService;
import de.team_franky.allein_daheim.data.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;

@RestController
@RequestMapping(value = "/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @CrossOrigin(origins = "http://localhost:8100")
    @GetMapping(value = "/{id}")
    public User findOne(@PathVariable Long id) {
        return userService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @CrossOrigin(origins = "http://localhost:8100")
    @PostMapping(value = "/{id}/seen")
    public User updateLastSeen(@PathVariable Long id) {
        return userService.findById(id)
                .map(user -> {
                    user.setLastSeen(new java.sql.Timestamp(new Date().getTime()));
                    return userService.save(user);
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

}
