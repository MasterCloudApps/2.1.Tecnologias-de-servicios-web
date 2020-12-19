const grpc = require('grpc');
const HelloService = require('./interface');
const helloServiceImpl = require('./helloService');

const server = new grpc.Server();

server.addService(HelloService.service, helloServiceImpl);

server.bind('127.0.0.1:9090', grpc.ServerCredentials.createInsecure());

console.log('gRPC server running at http://127.0.0.1:9090');

server.start();