export function hello(call, callback){

    console.log('Request received: '+JSON.stringify(call));

    const response = { greeting: "Hello, "+call.request.firstName+" "+call.request.lastName };

    callback(null, response);
}