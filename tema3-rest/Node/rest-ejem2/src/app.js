const express = require('express');
const postsRouter = require('./postsRouter.js');

const app = express();

//Convert json bodies to JavaScript object
app.use(express.json());

app.use(postsRouter);

app.listen(3000, () => {
    console.log('Example app listening on port 3000!');
});