package es.codeurjc.items;

import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

import java.net.URI;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/items")
public class ItemController {

	@Autowired
	private ItemService items;

	@GetMapping("/")
	public Collection<Item> getItems() {
		return items.findAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Item> getItem(@PathVariable long id) {

		Item post = items.findById(id);

		if (post != null) {
			return ResponseEntity.ok(post);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping("/")
	public ResponseEntity<Item> createItem(@RequestBody Item post) {

		items.save(post);

		URI location = fromCurrentRequest().path("/{id}").buildAndExpand(post.getId()).toUri();

		return ResponseEntity.created(location).body(post);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Item> replaceItem(@PathVariable long id, @RequestBody Item newItem) {

		Item post = items.findById(id);

		if (post != null) {

			newItem.setId(id);
			items.save(newItem);

			return ResponseEntity.ok(post);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Item> deleteItem(@PathVariable long id) {

		Item post = items.findById(id);

		if (post != null) {
			items.deleteById(id);
			return ResponseEntity.ok(post);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
}
