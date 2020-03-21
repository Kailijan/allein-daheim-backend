package de.team_franky.allein_daheim.api;

import de.team_franky.allein_daheim.business.service.UserService;
import de.team_franky.allein_daheim.data.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {
    @MockBean
    private UserService userService;

    @Autowired
    private MockMvc mockMvc;

    private static final DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");

    @Test
    public void getUser() throws Exception {
        User mockUser = new User();
        mockUser.setName("Günther");

        Date today = new Date();

        given(userService.findById(1L)).willReturn(java.util.Optional.of(mockUser));
        given(userService.save(mockUser)).willReturn(mockUser);
        this.mockMvc.perform(post("/api/users/1/seen"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(dateFormat.format(today))));
    }

}
