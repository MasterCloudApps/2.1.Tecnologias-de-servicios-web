let express = require('express');
let router = express.Router();

router.get('/', (req, res) => {

    res.render('index', {
        name: "World"
    });
});

module.exports = router;