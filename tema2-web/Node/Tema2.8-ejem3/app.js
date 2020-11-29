var express = require('express');
var session = require('express-session');
var app = express();

// Use the session middleware
app.use(session({
    secret: 'keyboard cat',
    cookie: {
        maxAge: 60000
    },
    resave: false,
    saveUninitialized: true
}));

// Access the session as req.session
app.get('/', function (req, res) {
    if (req.session.views) {
        req.session.views++;
        res.setHeader('Content-Type', 'text/html');
        res.write('<p>views: ' + req.session.views + '</p>');
        res.write('<p>expires in: ' + (req.session.cookie.maxAge / 1000) + 's</p>');
        res.end();
    } else {
        req.session.views = 1;
        res.end('Welcome to the session demo. Refresh!');
    }
});

app.listen(3000);