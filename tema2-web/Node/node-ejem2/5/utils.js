var obj = {
    prop1: 3,
    prop2: 'value'
}

function log(msg){
    console.log(msg);
}

class Date {
    constructor(date){
        this.date = date;
    }
}

exports.obj = obj;
exports.log = log;
exports.Date = Date;