var fs = require('fs');

var readableStream = fs.createReadStream('./hello.txt');
var text = "";

readableStream.on('open', () => {
    console.log('The file is open');
});

readableStream.on('data', chunk => {
    console.log('Received ' + chunk.length + ' bytes of data: ' + chunk);
    text += chunk;
});

readableStream.on('end', () => {
    console.log('There will be no more data');
    console.log('Final data: ' + text);
});

console.log('This may be the first statement executed');