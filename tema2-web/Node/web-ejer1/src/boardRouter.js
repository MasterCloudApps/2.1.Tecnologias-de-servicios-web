let express = require('express');
let router = express.Router();

let posts = new Map();
let nextId = 0;

addPost({ user: "Pepe", title: "Vendo moto", text: "Barata, barata" });
addPost({ user: "Juan", title: "Compro coche", text: "Pago bien" });

function addPost(post) {

    let id = nextId++;

    post.id = id.toString();

    posts.set(post.id, post);
}

router.get('/', (req, res) => {

    res.render('index', { posts: [...posts.values()] });

});

router.post('/post/new', (req, res) => {

    let { user, title, text } = req.body;

    addPost({ user, title, text });

    res.render('saved_post');
});

router.get('/post/:id', (req, res) => {

    let post = posts.get(req.params.id);

    res.render('show_post', { post });
});

router.get('/post/:id/delete', (req, res) => {

    posts.delete(req.params.id);

    res.render('deleted_post');
});


module.exports = router;