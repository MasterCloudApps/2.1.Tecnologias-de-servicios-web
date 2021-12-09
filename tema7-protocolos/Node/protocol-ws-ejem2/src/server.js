import express from 'express';
import expressws from 'express-ws';
import { __dirname } from './dirname.js';

const app = express();
expressws(app);

app.use(express.static(__dirname + '/../public'));

app.get('/test', (req, res) => {
    console.log('/test endpoint executed');
    res.json({ message: "Test response" });
    res.end();
});

app.ws('/notifications', (ws, req) => {

    console.log('User connected');

    ws.on('message', (msg) => {
        console.log('Message received:' + msg);
    });

    setInterval(() => {
        ws.send("Browser message");
    }, 1000);
});

app.listen(3000, () => console.log('Listening on port 3000!'));

