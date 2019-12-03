const grpc = require('grpc');
const HelloService = require('./interface');

var client = new HelloService('localhost:9090', grpc.credentials.createInsecure());

client.hello({firstName: 'Pepe', lastName:'Pérez'}, (error, response) => {

    if(error){
        return console.error(error);
    } else {
        console.log("Response: "+JSON.stringify(response));
    }

});