const express = require('express');

var fs = require('fs');
var https = require('https');

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
https.createServer({
    key: fs.readFileSync('server.key'),
    cert: fs.readFileSync('server.cert')
    }, app).listen(3443, () => {
    console.log("Https server started in port 3443");
    });