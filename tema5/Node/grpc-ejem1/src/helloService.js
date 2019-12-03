function hello(call, callback){

    console.log('Request received: '+JSON.stringify(call));

    var { firstName, lastName } = call.request;

    callback(null, { greeting: "Hello, "+firstName+" "+lastName });
}

exports.hello = hello;