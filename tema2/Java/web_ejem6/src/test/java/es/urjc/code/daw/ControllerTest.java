package es.urjc.code.daw;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class ControllerTest {

	@LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;
    
	@Test
	public void greetingTest() {
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		
		MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
		parameters.add("info", "CodeURJC");
		
		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(parameters, headers);
		ResponseEntity<String> response = this.restTemplate.exchange("http://localhost:" + port + "/procesarFormulario", HttpMethod.POST, request, String.class);
		List<String> cookies = response.getHeaders().get("Set-Cookie");
		assertThat(response.getBody().toString()).contains("La información ha sido guardada.");
		
		HttpHeaders cookieHeaders = new HttpHeaders();
		cookieHeaders.put(HttpHeaders.COOKIE, cookies);
		HttpEntity<Void> requestCookies = new HttpEntity<Void>(cookieHeaders);
		ResponseEntity<String> responseMostrar = this.restTemplate.exchange("http://localhost:" + port + "/mostrarDatos", HttpMethod.GET, requestCookies, String.class); 
		assertThat(responseMostrar.getBody()).contains("CodeURJC");
	}

}
