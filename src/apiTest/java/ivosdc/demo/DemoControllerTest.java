package ivosdc.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class DemoControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getHelloWorld_returnsOk() throws Exception {
        mockMvc.perform(get("/api/demo/helloworld"))
                .andExpect(status().isOk());
    }

    @Test
    public void getHelloWorld_returnsHelloWorld() throws Exception {
        MvcResult result = mockMvc.perform(get("/api/demo/helloworld"))
                .andReturn();

        assertEquals(result.getResponse().getContentAsString(), "Hello World");
    }
}
