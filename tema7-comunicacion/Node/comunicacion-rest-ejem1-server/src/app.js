import express from 'express';
import postsRouter from './postsRouter.js';
import cors from 'cors';

const app = express();

//Convert json bodies to JavaScript object
app.use(express.json());

//Allow requests from browser apps loaded in other domain
app.use(cors());

app.use(postsRouter);

app.listen(3000, () => {
    console.log('Example app listening on port 3000!');
});