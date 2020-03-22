package de.team_franky.allein_daheim.api;

import de.team_franky.allein_daheim.business.service.ChatRequestService;
import de.team_franky.allein_daheim.business.service.TopicService;
import de.team_franky.allein_daheim.business.service.UserService;
import de.team_franky.allein_daheim.data.entity.ChatRequest;
import de.team_franky.allein_daheim.data.entity.Topic;
import de.team_franky.allein_daheim.data.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(ChatRequestController.class)
public class ChatRequestControllerTest {
    @MockBean
    private ChatRequestService chatRequestService;

    @MockBean
    private UserService userService;

    @MockBean
    private TopicService topicService;

    @Autowired
    private MockMvc mockMvc;

    private static final DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");

    @Test
    public void findMatch() throws Exception {
        User user1 = new User();
        user1.setName("User1");
        user1.setId(1L);
        User user2 = new User();
        user1.setName("User2");
        user2.setId(2L);

        Topic testTopic = new Topic();
        testTopic.setName("Test Topic 2");
        testTopic.setId(1L);

        List<ChatRequest> chatRequests = new ArrayList<>();
        chatRequests.add(new ChatRequest(user2, testTopic));

        List<ChatRequest> ownRequests = new ArrayList<>();
        ownRequests.add(new ChatRequest(user1, testTopic));

        List userTopics = new ArrayList<>();
        userTopics.add(testTopic);

        given(userService.findById(user1.getId())).willReturn(java.util.Optional.of(user1));
        given(topicService.findById(testTopic.getId())).willReturn(java.util.Optional.of(testTopic));

        given(chatRequestService.findByUser(user1)).willReturn(ownRequests);
        given(chatRequestService.findByTopics(userTopics, user1)).willReturn(chatRequests);
        ResultActions resultActions = this.mockMvc.perform(get("/api/chat-request?user=" + user1.getId()));
        resultActions.andExpect(status().isOk())
                .andExpect(content().string(containsString("\"userId\":" + user2.getId())));
    }

}