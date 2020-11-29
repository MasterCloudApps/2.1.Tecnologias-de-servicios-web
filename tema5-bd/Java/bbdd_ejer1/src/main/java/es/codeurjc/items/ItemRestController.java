package es.codeurjc.items;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/items")
public class ItemRestController {
	
	@Autowired
	private ItemRepository repository;

	@GetMapping("/")
	public List<Item> findItems() {
		return repository.findAll();
	}

	@PostMapping("/")
	public ResponseEntity<Item> addItem(@RequestBody Item item) {
		item.setId(null);
		Item newItem = repository.saveAndFlush(item);
		return new ResponseEntity<>(newItem,HttpStatus.CREATED);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Item> getItem(@PathVariable long id) {

		Optional<Item> op = repository.findById(id);

		if (op.isPresent()) {
			Item item = op.get();
			return new ResponseEntity<>(item, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<Item> updateItem(@RequestBody Item updatedItem,
			@PathVariable long id) {
		updatedItem.setId(id);
		Item item = repository.saveAndFlush(updatedItem);
		return new ResponseEntity<>(item,HttpStatus.CREATED);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Item> deleteItem(@PathVariable long id) {
		repository.deleteById(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
