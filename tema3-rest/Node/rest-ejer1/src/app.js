import express from 'express';
import itemsRouter from './itemsRouter.js';

const app = express();

//Convert json bodies to JavaScript object
app.use(express.json());

app.use(itemsRouter);

app.listen(3000, () => {
    console.log('Example app listening on port 3000!');
});
