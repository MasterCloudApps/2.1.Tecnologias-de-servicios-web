package es.codeurjc.items;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;

@Service
public class ItemService {

	private final ConcurrentMap<Long, Item> items = new ConcurrentHashMap<>();
	private final AtomicLong nextId = new AtomicLong(1);

	public ItemService() {
		save(new Item("Leche", false));
		save(new Item("Pan", true));
	}

	public Collection<Item> findAll() {
		return items.values();
	}

	public Item findById(long id) {
		return items.get(id);
	}

	public void save(Item item) {
		
		if(item.getId() == 0) {
			long id = nextId.getAndIncrement();
			item.setId(id);
		}

		this.items.put(item.getId(), item);
	}

	public void deleteById(long id) {
		this.items.remove(id);
	}

}
