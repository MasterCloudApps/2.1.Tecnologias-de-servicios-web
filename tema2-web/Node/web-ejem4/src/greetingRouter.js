let express = require('express');
let router = express.Router();

router.get('/greeting', (req, res) => {

    res.render('greeting', {
        name: req.query.userName
    });
});

module.exports = router;