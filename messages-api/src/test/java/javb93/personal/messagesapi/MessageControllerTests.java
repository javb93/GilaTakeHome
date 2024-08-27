package javb93.personal.messagesapi;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class MessageControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testPostMessage() throws Exception{
        String testMessage = "{\"message\":\"TEST\",\"category\":\"Sports\"}";

        ResultActions result = mockMvc.perform(post("/messages")
                .contentType(MediaType.APPLICATION_JSON)
                .content(testMessage));
        result.andExpect(status().isOk())
                .andExpect(content().string( "Notifications Succesful"));
    }
    @Test
    public void testEmptyMessage() throws Exception{
        String testMessage = "{\"message\":\"\",\"category\":\"Sports\"}";

        ResultActions result = mockMvc.perform(post("/messages")
                .contentType(MediaType.APPLICATION_JSON)
                .content(testMessage));
        //Check that we recieve a bad request since message was empty
        result.andExpect(status().isBadRequest())
                .andExpect(content().string( "Message was not saved to history"));
    }
}
