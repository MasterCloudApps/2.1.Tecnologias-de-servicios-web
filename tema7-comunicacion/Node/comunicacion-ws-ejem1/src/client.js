import WebSocket from 'ws';

let socket = new WebSocket("ws://localhost:3000/");

socket.on('open', event => {
    console.log("WebSocket connection established");
});

socket.on('message', data => {
    console.log(`[message] Data received from server: ${data}`);
});

socket.on('close', event => {
    if (event.wasClean) {
        console.log(`[close] Connection closed cleanly, code=${event.code} reason=${event.reason}`);
    } else {
        console.log('[close] Connection died');
    }
});

socket.on('error', error => {
    console.log(`[error] ${error.message}`);
});

function sendMessage() {    
    socket.send("My name is John");
    console.log("WebSocket message sent");
}

setInterval(sendMessage, 1000);