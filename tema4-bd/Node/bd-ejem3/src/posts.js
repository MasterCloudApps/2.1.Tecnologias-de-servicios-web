const express = require('express');
const { ObjectId } = require('mongodb');
const url = require('url');
const MongoClient = require('mongodb').MongoClient;

const mongoUrl = "mongodb://localhost:27017/posts";

let posts;

async function dbConnect() {
    const conn = await MongoClient.connect(mongoUrl, {
        useUnifiedTopology: true,
        useNewUrlParser: true
    });

    posts = conn.db().collection('posts');
}

async function init() {

    await dbConnect();

    await posts.insertOne({ user: "Pepe", title: "Vendo moto", text: "Barata, barata" });
    await posts.insertOne({ user: "Juan", title: "Compro coche", text: "Pago bien" });
}

const router = express.Router();

function validPost(post) {
    return typeof post.text == 'string'
        && typeof post.user == 'string'
        && typeof post.text == 'string';
}

function toResponse(document) {

    if (document instanceof Array) {
        return document.map(elem => toResponse(elem));
    } else {
        let { _id, ...response } = document;
        response.id = _id.toString();
        return response;
    }
}

function fullUrl(req) {
    const fullUrl = url.format({
        protocol: req.protocol,
        host: req.get('host'),
        pathname: req.originalUrl
    });

    return fullUrl + (fullUrl.endsWith('/') ? '' : '/');
}

router.post('/posts', async (req, res) => {

    if (!validPost(req.body)) {
        res.sendStatus(400);
    } else {

        let post = {
            user: req.body.user,
            title: req.body.title,
            text: req.body.text
        };

        await posts.insertOne(post);

        res.location(fullUrl(req) + post.id);
        res.json(toResponse(post));
    }
});

router.get('/posts', async (req, res) => {
    const allPosts = await posts.find({}).toArray();
    res.json(toResponse(allPosts));
});

router.get('/posts/:id', async (req, res) => {
    const id = req.params.id;

    if(!ObjectId.isValid(id)){
        return res.sendStatus(400);
    }

    const post = await posts.findOne({ _id: new ObjectId(id) });
    if (!post) {
        res.sendStatus(404);
    } else {
        res.json(toResponse(post));
    }
});

router.delete('/posts/:id', async (req, res) => {
    const id = req.params.id;
    
    if(!ObjectId.isValid(id)){
        return res.sendStatus(400);
    }

    const post = await posts.findOne({ _id: new ObjectId(id) });
    
    if (!post) {
        res.sendStatus(404);
    } else {
        await posts.deleteOne({ _id: new ObjectId(id) });
        res.json(toResponse(post));
    }
});

router.put('/posts/:id', async (req, res) => {
    const id = req.params.id;

    if(!ObjectId.isValid(id)){
        return res.sendStatus(400);
    }

    const post = await posts.findOne({ _id: new ObjectId(id) });
    if (!post) {
        res.sendStatus(404);
    } else {
        if (!validPost(req.body)) {
            res.sendStatus(400);
        } else {

            let newPost = {
                user: req.body.user,
                title: req.body.title,
                text: req.body.text
            };

            await posts.updateOne({ _id: new ObjectId(id) },
                { $set: newPost });

            newPost.id = id;

            res.json(newPost);
        }
    }
});

module.exports = { router, init }