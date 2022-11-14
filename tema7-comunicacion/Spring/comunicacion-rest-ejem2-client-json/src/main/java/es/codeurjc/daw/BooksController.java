package es.codeurjc.daw;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

@RestController
public class BooksController {

	@GetMapping("/booktitles")
	public List<String> getBookTitles(@RequestParam String title) {

		RestTemplate restTemplate = new RestTemplate();

		String url = "https://www.googleapis.com/books/v1/volumes?q=intitle:" + title;

		ObjectNode data = restTemplate.getForObject(url, ObjectNode.class);

		List<String> bookTitles = new ArrayList<String>();

		ArrayNode items = (ArrayNode) data.get("items");
		for (int i = 0; i < items.size(); i++) {
			JsonNode item = items.get(i);
			String bookTitle = item.get("volumeInfo").get("title").asText();
			bookTitles.add(bookTitle);
		}

		return bookTitles;
	}
}
