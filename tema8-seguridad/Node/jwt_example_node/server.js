import express from "express";
import cors from "cors";

import  verifySignUp  from "./app/middlewares/verifySignUp.js";
import { signup, signin } from "./app/controllers/auth.controller.js";
import authJwt from "./app/middlewares/authJwt.js";
import { allAccess, userBoard, adminBoard } from "./app/controllers/user.controller.js";

const HOST = "localhost";
const PORT_DB = 27017;
const DB = "cloudapps";

const app = express();

app.use(cors());

// parse requests of content-type - application/json
app.use(express.json());

// parse requests of content-type - application/x-www-form-urlencoded
app.use(express.urlencoded({ extended: true }));

import db from "./app/models/index.js";
const Role = db.role;

db.mongoose
  .connect(`mongodb://${HOST}:${PORT_DB}/${DB}`, {
    useNewUrlParser: true,
    useUnifiedTopology: true
  })
  .then(() => {
    console.log("Successfully connect to MongoDB.");
    initial();
  })
  .catch(err => {
    console.error("Connection error", err);
    process.exit();
  });

// routes
app.use(function(req, res, next) {
  res.header(
    "Access-Control-Allow-Headers",
    "x-access-token, Origin, Content-Type, Accept"
  );
  next();
});

app.post("/api/auth/signup",
  [
    verifySignUp.checkDuplicateUsernameOrEmail,
    verifySignUp.checkRolesExisted
  ],
  signup
);

app.post("/api/auth/signin", signin);

app.get("/api/test/all", allAccess);

app.get("/api/test/user", [authJwt.verifyToken], userBoard);

app.get("/api/test/admin", [authJwt.verifyToken, authJwt.isAdmin], adminBoard);

// set port, listen for requests
const PORT = process.env.PORT || 8080;
app.listen(PORT, () => {
  console.log(`Server is running on port ${PORT}.`);
});

function initial() {
  Role.estimatedDocumentCount((err, count) => {
    if (!err && count === 0) {
      new Role({
        name: "user"
      }).save(err => {
        if (err) {
          console.log("error", err);
        }

        console.log("added 'user' to roles collection");
      });

      new Role({
        name: "admin"
      }).save(err => {
        if (err) {
          console.log("error", err);
        }

        console.log("added 'admin' to roles collection");
      });
    }
  });
}
