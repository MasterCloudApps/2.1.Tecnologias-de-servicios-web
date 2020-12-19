var express = require('express');
var app = express();
var expressWs = require('express-ws')(app);

app.use(express.static('public'));

app.get('/test', function (req, res, next) {
    console.log('/test endpoint executed');
    res.json({message:"Test response"});
    res.end();
});

app.ws('/notifications', function (ws, req) {

    console.log('User connected');

    ws.on('message', function (msg) {
        console.log('Message received:' + msg);
    });

    setInterval(()=>{

        ws.send("Browser message");

    },1000);

});

app.listen(8080);

