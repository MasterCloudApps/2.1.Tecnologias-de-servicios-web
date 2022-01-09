import mongoose from 'mongoose';
const { model, Schema } = mongoose;

const Role = model(
  "Role",
  new Schema({
    name: String
  })
);

export default Role;
