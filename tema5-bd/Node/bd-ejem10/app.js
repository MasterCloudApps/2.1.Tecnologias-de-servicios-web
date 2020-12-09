const { Sequelize, Model, DataTypes } = require('sequelize');

let Customer;

function toPlainObj(model){

    //JSON.stringify(model) returns a plain JSON as string

    if(model instanceof Array){
        return model.map( m => toPlainObj(m));
    } else {
        return model.get({ plain: true });
    }
}


async function insertOne() {

    await Customer.create({ firstName: "Jack", lastName: "Bauer" });

    console.log('Customer inserted');
}

async function insertOneWithId() {

    const { dataValues: { id } } = await Customer.create({ firstName: "Jack", lastName: "Bauer" });

    console.log('Customer inserted with id:', id);

    return id;
}

async function findCustomerWithQuery() {

    const rows = await Customer.findAll({ where: { firstName: 'Jack' } });

    console.log('Customers with firstName = "Jack":', toPlainObj(rows));
}

async function findCustomerById(id) {

    const rows = await Customer.findAll({ where: { id } });

    console.log('Customer with id:', toPlainObj(rows[0]));
}

async function updateCustomerById(id) {

    await Customer.update({ firstName: 'Pedro' }, { where: { id } });

    console.log('Updated customer with id:', id);
}

async function updateCustomersByFirstName() {

    const affectedRows = await Customer.update({ firstName: 'John' }, { where: { firstName: 'Pedro' } });

    console.log(`Updated ${affectedRows} customers with name "Pedro"`);
}

async function deleteCustomerById(id) {

    await Customer.destroy({ where: { id } });

    console.log('Deleted customer with id:', id);
}

async function deleteCustomersByFirstName() {

    const affectedRows = await Customer.destroy({ where: { firstName: 'Jack' } });

    console.log(`Deleted ${affectedRows} customers with name "John"`);
}


async function main() {

    const sequelize = new Sequelize('customersDB', 'root', 'pass', {
        dialect: 'mysql'
    })

    Customer = class extends Model { }

    Customer.init({
        firstName: DataTypes.STRING,
        lastName: DataTypes.STRING
    }, { sequelize, modelName: 'customer' });

    await sequelize.sync();

    console.log("Connected to MySQL");

    await insertOne();
    const id = await insertOneWithId();
    await findCustomerWithQuery();
    await findCustomerById(id);
    await updateCustomerById(id);
    await updateCustomersByFirstName();
    await deleteCustomerById(id);
    await deleteCustomersByFirstName();

    await sequelize.close();

    console.log("Connection closed");

}

main();