var express = require('express');

var app = express();
app.use(express.json()); // support json encoded bodies
app.use(express.urlencoded({ extended: true })); // support url encoded bodies
app.use(express.text()); // support text bodies
app.use(express.raw()); // support raw bodies

app.post('/', function (req, res) {
   console.dir(req.body);
   res.send("test");
});

app.listen(3000);