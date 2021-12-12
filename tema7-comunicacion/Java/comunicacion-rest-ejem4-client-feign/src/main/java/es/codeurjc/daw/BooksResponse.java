package es.codeurjc.daw;

import java.util.List;

public class BooksResponse {
	public List<Book> items;
}

class Book {
	public VolumeInfo volumeInfo;
}

class VolumeInfo {
	public String title;
}
