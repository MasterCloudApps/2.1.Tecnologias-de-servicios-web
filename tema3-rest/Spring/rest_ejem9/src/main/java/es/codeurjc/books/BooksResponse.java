package es.codeurjc.books;

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
