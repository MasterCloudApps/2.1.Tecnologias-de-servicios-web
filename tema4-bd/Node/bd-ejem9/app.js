const { Sequelize, Model, DataTypes } = require('sequelize');

async function main() {

    const sequelize = new Sequelize('customersDB', 'root', 'pass', {
        dialect: 'mysql'
    })

    class Customer extends Model { }

    Customer.init({
        firstName: DataTypes.STRING,
        lastName: DataTypes.STRING
    }, { sequelize, modelName: 'customer' });

    await sequelize.sync();

    console.log("Connected to MySQL");

    await Customer.create({
        firstName: 'Jack',
        lastName: 'Bauer'
    });

    console.log("Customer inserted");

    await sequelize.close();

    console.log("Connection closed");

}

main();