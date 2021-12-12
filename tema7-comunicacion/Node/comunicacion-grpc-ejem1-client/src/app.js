import { credentials } from '@grpc/grpc-js';
import { HelloService } from './proto.js';

var helloService = new HelloService('localhost:9090', credentials.createInsecure());

helloService.hello({firstName: 'Pepe', lastName:'Pérez'}, (error, response) => {

    if(error){
        return console.error(error);
    } else {
        console.log("Response: "+JSON.stringify(response));
    }

});