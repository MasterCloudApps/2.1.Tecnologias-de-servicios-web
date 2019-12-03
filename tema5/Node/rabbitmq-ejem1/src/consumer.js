var amqp = require('amqplib/callback_api');

const CONN_URL = 'amqp://guest:guest@localhost';

amqp.connect(CONN_URL, async function (err, conn) {

    let ch = await conn.createChannel();

    ch.consume('messages', function (msg) {

        console.log("Message:", msg.content.toString());

    }, { noAck: true }
    );

});