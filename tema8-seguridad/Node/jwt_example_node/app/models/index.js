import mongoose from 'mongoose';
import user from "./user.model.js";
import role from "./role.model.js";

const db = {};
db.mongoose = mongoose;
db.user = user;
db.role = role;
db.ROLES = ["user", "admin"];

export default db;