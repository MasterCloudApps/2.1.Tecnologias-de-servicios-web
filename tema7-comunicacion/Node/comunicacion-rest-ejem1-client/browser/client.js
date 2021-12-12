const response = await fetch('http://localhost:3000/posts/');

let posts = await response.json();
let postsElem = document.getElementById('posts');

for(let post of posts){

    const postText = 'User:'+post.user+' Title:'+post.title+' Text:'+post.text;

    const li = document.createElement('li');
    postsElem.appendChild(li);
    li.appendChild(document.createTextNode(postText));
    
}

