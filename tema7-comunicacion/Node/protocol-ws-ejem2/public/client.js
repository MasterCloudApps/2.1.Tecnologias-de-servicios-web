let socket = new WebSocket("ws://"+window.location.host+"/notifications");

socket.onopen = e => {
    console.log("WebSocket connection established");
};

socket.onmessage = event => {
    console.log(`[message] Data received from server: ${event.data}`);
};

socket.onclose = event => {
    if (event.wasClean) {
        console.log(`[close] Connection closed cleanly, code=${event.code} reason=${event.reason}`);
    } else {
        console.log('[close] Connection died');
    }
};

socket.onerror = error => {
    console.log(`[error] ${error.message}`);
};

function sendMessage() {    
    socket.send("My name is John");
    console.log("WebSocket message sent");
}