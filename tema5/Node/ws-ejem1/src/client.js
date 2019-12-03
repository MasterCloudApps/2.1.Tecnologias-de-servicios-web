const WebSocket = require('ws');

let socket = new WebSocket("ws://localhost:8080/");

socket.on('open', function (e) {
    console.log("WebSocket connection established");
});

socket.on('message', function (data) {
    console.log(`[message] Data received from server: ${data}`);
});

socket.on('close', function (event) {
    if (event.wasClean) {
        console.log(`[close] Connection closed cleanly, code=${event.code} reason=${event.reason}`);
    } else {
        console.log('[close] Connection died');
    }
});

socket.on('error', function (error) {
    console.log(`[error] ${error.message}`);
});

function sendMessage() {    
    socket.send("My name is John");
    console.log("WebSocket message sent");
}

setInterval(sendMessage, 1000);