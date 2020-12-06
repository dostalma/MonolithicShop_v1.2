package cz.dostalma.monolithicshop.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class HomeControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void getHello() throws Exception {
        String expected = "<!DOCTYPE html>\r\n" +
                "<html>\r\n" +
                "<head>\r\n" +
                "    <meta charset=\"UTF-8\">\r\n" +
                "    <title>Home page</title>\r\n" +
                "    <link rel=\"stylesheet\" href=\"/css/format.css\">\r\n" +
                "    <link rel=\"stylesheet\" href=\"/css/main.css\">\r\n" +
                "\r\n" +
                "    <script type=\"text/javascript\" src=\"/js/main.js\"></script>\r\n" +
                "    <script type=\"text/javascript\" src=\"/js/foo.js\"></script>\r\n" +
                "</head>\r\n" +
                "<body>\r\n" +
                "\r\n" +
                "<div id=\"block\">\r\n" +
                "\r\n" +
                "    <p>\r\n" +
                "        This is home page.\r\n" +
                "    </p>\r\n" +
                "\r\n" +
                "</div>\r\n" +
                "\r\n" +
                "</body>\r\n" +
                "</html>";

        mvc.perform(MockMvcRequestBuilders.get("/").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo(expected)));

    }

}
