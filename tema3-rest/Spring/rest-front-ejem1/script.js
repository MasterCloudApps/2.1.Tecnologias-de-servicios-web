let result = await fetch("https://www.googleapis.com/books/v1/volumes?q=intitle:java");
let books = await result.json();
console.log(books);