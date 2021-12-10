import express from 'express';
import url from 'url';

const router = express.Router();

const posts = new Map();
let nextId = 1;

addPost({ user: "Pepe", title: "Vendo moto", text: "Barata, barata" });
addPost({ user: "Juan", title: "Compro coche", text: "Pago bien" });

function addPost(post) {

    let id = nextId++;

    post.id = id.toString();

    posts.set(post.id, post);
}

function validPost(post) {
    return typeof post.text == 'string'
        && typeof post.user == 'string'
        && typeof post.title == 'string';
}

function fullUrl(req) {
  const fullUrl = url.format({
    protocol: req.protocol,
    host: req.get('host'),
    pathname: req.originalUrl
  });
  
  return fullUrl + (fullUrl.endsWith('/')?'':'/');
}

router.post('/posts', (req, res) => {

    if (!validPost(req.body)) {
        res.sendStatus(400);
    } else {

        //const { user, title, text } = req.body;
        //let post = { user, title, text};

        let post = {
            user: req.body.user,
            title: req.body.title,
            text: req.body.text
        };

        addPost(post);
        res.location(fullUrl(req)+post.id);
        res.json(post);
    }
});

router.get('/posts', (req, res) => {
    res.json([...posts.values()]);
});

router.get('/posts/:id', (req, res) => {
    const id = req.params.id;
    const post = posts.get(id);
    if (!post) {
        res.sendStatus(404);
    } else {
        res.json(post);
    }
});

router.delete('/posts/:id', (req, res) => {
    const id = req.params.id;
    const post = posts.get(id);
    if (!post) {
        res.sendStatus(404);
    } else {
        posts.delete(id);
        res.json(post);
    }
});

router.put('/posts/:id', (req, res) => {
    const id = req.params.id;
    const post = posts.get(id);
    if (!post) {
        res.sendStatus(404);
    } else {
        if (!validPost(req.body)) {
            res.sendStatus(400);
        } else {

            let newPost = {
                id,
                user: req.body.user,
                title: req.body.title,
                text: req.body.text
            };

            posts.set(id, newPost);

            res.json(newPost);
        }
    }
});

export default router;


