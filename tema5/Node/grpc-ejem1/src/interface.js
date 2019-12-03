const grpc = require('grpc');
const protoLoader = require('@grpc/proto-loader');

var packageDefinition = protoLoader.loadSync(__dirname + '/../HelloService.proto',
    {
        keepCase: true,
        longs: String,
        enums: String,
        defaults: true,
        oneofs: true
    });

var helloServiceProto = grpc.loadPackageDefinition(packageDefinition);

module.exports = helloServiceProto.es.codeurjc.mastercloudapps.grpc.HelloService;