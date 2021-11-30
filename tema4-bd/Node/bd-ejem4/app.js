var mongoose = require('mongoose');

const url = "mongodb://localhost:27017/customersDB";

async function main() {

    await mongoose.connect(url, {
        useUnifiedTopology: true,
        useNewUrlParser: true,
        useFindAndModify: false
    });

    const conn = mongoose.connection;

    var customerSchema = new mongoose.Schema({
        firstName: String,
        lastName: String
    });

    var Customer = mongoose.model('Customer', customerSchema);

    let customer = new Customer({ firstName: 'Jack', lastName: 'Bauer' })

    await customer.save();

    conn.close();
}

main();

