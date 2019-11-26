package es.urjc.code.daw;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class ControllerTest {

	@LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;
    
	@Test
	public void basicTest() {
		
		assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/basic",
                String.class)).contains("Hello!");
	}

	@Test
	public void iterationTest() {
		
		assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/list",
                String.class)).contains("Red", "Blue", "Green");
	}
}
