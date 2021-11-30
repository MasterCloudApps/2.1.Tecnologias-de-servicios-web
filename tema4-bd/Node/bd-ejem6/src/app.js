import express from 'express';
import { router as postsRouter, init as postsInit } from './posts.js';

const app = express();

//Convert json bodies to JavaScript object
app.use(express.json());

app.use(postsRouter);

await postsInit();

app.listen(3000, () => {
    console.log('Example app listening on port 3000!');
});
