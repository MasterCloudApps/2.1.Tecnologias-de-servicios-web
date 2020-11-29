// Traditional callback version
function callbackFunction(message, callback) {
    setTimeout(() => {
        console.log("I've waited 1 second! Message: " + message);
        callback(null);
        // If some error took place, then call callback(error) with a defined error
    }, 300);
}

// Promisified version
function promiseFunction(message) {
    return new Promise((resolve, reject) => {
        setTimeout(() => {
            console.log("I've waited 1 second! Message: " + message);
            resolve('Promise resolved');
            // If some error took place, then call reject(error)
        }, 1000);
    });
}

callbackFunction('This is callback 1!', () => {
    console.log("Callback hell 1 executed");
    callbackFunction('This is callback 2!', () => {
        console.log("Callback hell 2 executed");
        callbackFunction('This is callback 3!', () => {
            console.log("Callback hell 3 executed");
        });
    });
});

promiseFunction('This is promise 1!')
.then(data => {
    console.log(data);
    return promiseFunction('This is promise 2!');
})
.then(data => {
    console.log(data);
    return promiseFunction('This is promise 3!');
})
.then(data => {
    console.log(data);
});