let socket = new WebSocket("ws://"+window.location.host+"/notifications");

socket.onopen = function (e) {
    console.log("WebSocket connection established");
};

socket.onmessage = function (event) {
    console.log(`[message] Data received from server: ${event.data}`);
};

socket.onclose = function (event) {
    if (event.wasClean) {
        console.log(`[close] Connection closed cleanly, code=${event.code} reason=${event.reason}`);
    } else {
        console.log('[close] Connection died');
    }
};

socket.onerror = function (error) {
    console.log(`[error] ${error.message}`);
};

function sendMessage() {    
    socket.send("My name is John");
    console.log("WebSocket message sent");
}