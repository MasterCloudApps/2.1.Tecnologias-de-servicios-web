const WebSocket = require('ws');

const wss = new WebSocket.Server({ port: 8080 });

wss.on('connection', function connection(ws, req) {

    console.log('User connected');

    ws.on('message', function (msg) {
        console.log('Message received:' + msg);
    });

    setInterval(()=>{

        ws.send("Browser message");

    },1000);

});