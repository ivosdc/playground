package ivosdc.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class DemoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private DemoRepository demoRepository;

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

    @Test
    public void getDemos_returnsOk() throws Exception {
        mockMvc.perform(get("/api/demo"))
                .andExpect(status().isOk());
    }

    @Test
    public void getDemo_returnsDemoObject() throws Exception {
        Demo demo = new Demo("name", "description");
        Demo savedDemo = demoRepository.save(demo);

        mockMvc.perform(get("/api/demo/" + savedDemo.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("name")))
                .andExpect(jsonPath("$.description", is("description")));

        demoRepository.deleteById(savedDemo.getId());
    }

    @Test
    public void createDemo_returnsDemoObject() throws Exception {
        deleteIfExist();
        mockMvc.perform(post("/api/demo")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content("{\"description\" : \"description\","
                        + "\"name\" : \"name\"}"))
                .andExpect(status().isOk());
        deleteIfExist();
    }

    private void deleteIfExist() {
        demoRepository.findByName("name").ifPresent(demo -> {
            demoRepository.deleteById(demo.getId());
        });
    }
}
