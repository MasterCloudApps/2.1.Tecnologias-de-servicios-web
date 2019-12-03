const amqp = require('amqplib/callback_api');

const CONN_URL = 'amqp://guest:guest@localhost';

let ch = null;

process.on('exit', (code) => {
    ch.close();
    console.log(`Closing rabbitmq channel`);
});

amqp.connect(CONN_URL, async function (err, conn) {

    ch = await conn.createChannel();

    var numData = 0;

    setInterval(() => {

        const data = "Data " + numData++;

        console.log("publishToQueue: '" + data + "'");

        ch.sendToQueue("messages", Buffer.from(data));

    }, 1000);
});



