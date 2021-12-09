import { connect } from 'amqplib';

let channel = null;

process.on('exit', (code) => {
    channel.close();
    console.log(`Closing rabbitmq channel`);
});

const rabbitClient = await connect('amqp://guest:guest@localhost');

channel = await rabbitClient.createChannel();

channel.assertQueue("messages");

let numData = 0;

setInterval(() => {

    const data = "Data " + numData++;

    console.log("Produced to queue: '" + data + "'");

    channel.sendToQueue("messages", Buffer.from(data));

}, 1000);