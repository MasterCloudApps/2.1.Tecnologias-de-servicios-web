let result = await fetch("https://www.googleapis.com/books/v1/volumes?q=intitle:java");
let books = await result.json();
let body = document.getElementsByTagName("body");
for (let i = 0; i < books.items.length; i++) {
    body[0].innerHTML += `<p> ${books.items[i].volumeInfo.title}</p>`;
}
