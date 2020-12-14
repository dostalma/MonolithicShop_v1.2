package cz.dostalma.monolithicshop.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

import java.net.URL;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HomeControllerIT {

    @LocalServerPort
    private int port;

    private URL base;

    @Autowired
    private TestRestTemplate template;

    @BeforeEach
    public void setUp() throws Exception {
        this.base = new URL("http://localhost:" + port + "/");
    }

    @Test
    public void getHello() throws Exception {
        ResponseEntity<String> response = template.getForEntity(base.toString(),
                String.class);
        String expected = "<!DOCTYPE html>\r\n" +
                "<html>\r\n" +
                "<head>\r\n" +
                "    <meta charset=\"UTF-8\">\r\n" +
                "    <title>Home page</title>\r\n" +
                "    <link rel=\"stylesheet\" href=\"/css/format.css\">\r\n" +
                "    <link rel=\"stylesheet\" href=\"/css/main.css\">\r\n" +
                "\r\n" +
                "    <script type=\"text/javascript\" src=\"/js/main.js\"></script>\r\n" +
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

        assertThat(response.getBody()).isEqualTo(expected);
    }
}