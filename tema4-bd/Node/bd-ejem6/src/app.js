const express = require('express');
const postsRouter = require('./posts.js').router;
const postsInit = require('./posts.js').init;

const app = express();

//Convert json bodies to JavaScript object
app.use(express.json());

app.use(postsRouter);

async function main() {

    await postsInit();

    app.listen(3000, () => {
        console.log('Example app listening on port 3000!');
    });
}

main();