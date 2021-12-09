import { Server, ServerCredentials } from '@grpc/grpc-js';
import { HelloService } from './proto.js';
import { hello } from './helloService.js';

const server = new Server();

server.addService(HelloService.service, { hello });

server.bindAsync('0.0.0.0:9090', ServerCredentials.createInsecure(), () => {
    server.start();
    console.log('gRPC server running at http://127.0.0.1:9090');
});