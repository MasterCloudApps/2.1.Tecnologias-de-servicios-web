import { loadPackageDefinition } from '@grpc/grpc-js';
import { loadSync } from '@grpc/proto-loader';
import { __dirname } from './dirname.js'

const proto = loadPackageDefinition(loadSync(__dirname + '/../HelloService.proto',
{
    keepCase: true,
    longs: String,
    enums: String,
    defaults: true,
    oneofs: true
}));

export const HelloService = proto.helloworld.HelloService;