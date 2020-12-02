let express = require('express');
let mustacheExpress = require('mustache-express');
let bodyParser = require('body-parser');
let greetingRouter = require('./boardRouter.js');

let app = express();

app.set('views', __dirname + '/../views');
app.set('view engine', 'mustache');
app.engine('mustache', mustacheExpress());

app.use(bodyParser.urlencoded({ extended: true }));

app.use(express.static(__dirname + '/../public'));

app.use('/', greetingRouter);

app.listen(3000, () => {
   console.log('Example app listening on port 3000!');
});