import { WebSocketServer } from 'ws';

const wss = new WebSocketServer({ port: 3000 });

wss.on('connection', (ws, req) => {

    console.log('User connected');

    ws.on('message', msg => {
        console.log('Message received:' + msg);
    });

    setInterval(() => {

        ws.send("Client message");

    }, 1000);

});