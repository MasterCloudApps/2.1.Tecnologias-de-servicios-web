const express = require('express');

const app = express();

//Convert json bodies to JavaScript object
app.use(express.json());

app.get('/posts/1', (req, res) => {
    res.json({
        id: 1,
        user: 'Pepe',
        title: 'Vendo moto',
        text: 'Barata, barata'
    });
});

app.listen(3000, () => {
    console.log('Example app listening on port 3000!');
});