import express from 'express';
import url from 'url';
import mongoose from 'mongoose';

const mongoUrl = "mongodb://localhost:27017/posts";

let Post;

async function dbConnect() {
    
    await mongoose.connect(mongoUrl, {
        useUnifiedTopology: true,
        useNewUrlParser: true
    });

    console.log("Connected to Mongo");

    Post = mongoose.model('Post', new mongoose.Schema({
        user: String,
        title: String,
        text: String
    }));
}

export async function init() {

    await dbConnect();

    await new Post({ user: "Pepe", title: "Vendo moto", text: "Barata, barata" }).save();
    await new Post({ user: "Juan", title: "Compro coche", text: "Pago bien" }).save();
}

export const router = express.Router();

function validPost(post) {
    return typeof post.text == 'string'
        && typeof post.user == 'string'
        && typeof post.text == 'string';
}

function toResponse(document) {

    if (document instanceof Array) {
        return document.map(elem => toResponse(elem));
    } else {
        let response = document.toObject({ versionKey: false });
        response.id = response._id.toString();
        delete response._id;
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

        const post = new Post({
            user: req.body.user,
            title: req.body.title,
            text: req.body.text
        });

        await post.save();

        res.location(fullUrl(req) + post.id);
        res.json(toResponse(post));
    }
});

router.get('/posts', async (req, res) => {
    const allPosts = await Post.find().exec();
    res.json(toResponse(allPosts));
});

router.get('/posts/:id', async (req, res) => {
    const id = req.params.id;

    if(!mongoose.Types.ObjectId.isValid(id)){
        return res.sendStatus(400);
    }

    const post = await Post.findById(id);
    if (!post) {
        res.sendStatus(404);
    } else {
        res.json(toResponse(post));
    }
});

router.delete('/posts/:id', async (req, res) => {
    const id = req.params.id;

    if(!mongoose.Types.ObjectId.isValid(id)){
        return res.sendStatus(400);
    }

    const post = await Post.findById(id);
    if (!post) {
        res.sendStatus(404);
    } else {
        await Post.findByIdAndDelete(id);
        res.json(toResponse(post));
    }
});

router.put('/posts/:id', async (req, res) => {
    const id = req.params.id;

    if(!mongoose.Types.ObjectId.isValid(id)){
        return res.sendStatus(400);
    }

    const post = await Post.findById(id);
    if (!post) {
        res.sendStatus(404);
    } else {
        if (!validPost(req.body)) {
            res.sendStatus(400);
        } else {

            post.user = req.body.user;
            post.title = req.body.title;
            post.text = req.body.text;

            await post.save();

            res.json(toResponse(post));
        }
    }
});