package es.codeurjc.items;

import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

import java.net.URI;
import java.util.Collection;

import jakarta.annotation.PostConstruct;

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
	private ItemRepository items;

	@PostConstruct
	public void init() {
		items.save(new Item("Leche", false));
		items.save(new Item("Pan", true));
	}
	
	@GetMapping("/")
	public Collection<Item> getItems() {
		return items.findAll();
	}

	@GetMapping("/{id}")
	public Item getItem(@PathVariable long id) {

		return items.findById(id).orElseThrow();
	}

	@PostMapping("/")
	public ResponseEntity<Item> createItem(@RequestBody Item item) {

		items.save(item);

		URI location = fromCurrentRequest().path("/{id}").buildAndExpand(item.getId()).toUri();

		return ResponseEntity.created(location).body(item);
	}

	@PutMapping("/{id}")
	public Item replaceItem(@PathVariable long id, @RequestBody Item newItem) {

		items.findById(id).orElseThrow();

		newItem.setId(id);
		items.save(newItem);
			
		return newItem;
	}

	@DeleteMapping("/{id}")
	public Item deleteItem(@PathVariable long id) {

		Item item = items.findById(id).orElseThrow();

		items.deleteById(id);
		
		return item;
	}
}
