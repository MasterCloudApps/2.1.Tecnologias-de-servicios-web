import sequelize_pkg from 'sequelize';

//Sequelize doesn't use named exports
const { Sequelize, Model, DataTypes } = sequelize_pkg;

const sequelize = new Sequelize('posts', 'root', 'pass', {
    dialect: 'mysql'
})

class Customer extends Model { }

Customer.init({
    firstName: DataTypes.STRING,
    lastName: DataTypes.STRING
}, { sequelize, modelName: 'customer' });

await sequelize.sync();

console.log("Connected to MySQL");

const customer = await Customer.create({
    firstName: 'Jack',
    lastName: 'Bauer'
});

console.log("Customer inserted: "+ JSON.stringify(customer));

await sequelize.close();

console.log("Connection closed");

