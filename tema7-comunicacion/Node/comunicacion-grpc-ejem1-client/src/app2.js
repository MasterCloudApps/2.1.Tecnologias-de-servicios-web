import { credentials } from '@grpc/grpc-js';
import { HelloService } from './proto.js';
import { promisify } from 'util';

var helloService = new HelloService('localhost:9090', credentials.createInsecure());

helloService.hello = promisify(helloService.hello.bind(helloService));

const response = await helloService.hello({firstName: 'Pepe', lastName:'Pérez'});

console.log("Response: "+JSON.stringify(response));