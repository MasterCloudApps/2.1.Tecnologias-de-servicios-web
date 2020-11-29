function promiseFunction(message) {
    return new Promise((resolve, reject) => {
        setTimeout(() => {
            console.log("I've waited 1 second! Message: " + message);
            resolve('Promise resolved');
            // If some error took place, then call reject(error)
        }, 1000);
    });
}

(async function () {
    await promiseFunction('This is promise 1!');
    await promiseFunction('This is promise 2!');
    await promiseFunction('This is promise 3!');
}());