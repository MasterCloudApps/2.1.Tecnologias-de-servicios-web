import { connect } from 'amqplib';

let channel = null;

process.on('exit', (code) => {
    channel.close();
    console.log(`Closing rabbitmq channel`);
});

const rabbitClient = await connect('amqp://guest:guest@localhost');

channel = await rabbitClient.createChannel();

channel.assertQueue("messages", {
    durable: true,
    arguments: {"x-queue-type": "stream"}
});

channel.consume("messages", (msg) => {

    console.log("Consumed from queue: '", msg.content.toString()+ "'");

}, { noAck: true });