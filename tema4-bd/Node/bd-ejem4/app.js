import mongoose from 'mongoose';

const url = "mongodb://localhost:27017/customersDB";

await mongoose.connect(url, {
    useUnifiedTopology: true,
    useNewUrlParser: true
});

const customerSchema = new mongoose.Schema({
    firstName: String,
    lastName: String
});

const Customer = mongoose.model('Customer', customerSchema);

const customer = new Customer({ firstName: 'Jack', lastName: 'Bauer' })

await customer.save();

console.log(customer);

mongoose.connection.close();

