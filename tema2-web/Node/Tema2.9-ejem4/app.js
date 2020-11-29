function promiseFunction(endSuccess) {
    return new Promise((resolve, reject) => {
        setTimeout(() => {
            console.log("I've waited 1 second!");
            if (endSuccess) {
                resolve('Promise resolved');
            } else {
                reject('Promise rejected');
            }
        }, 1000);
    });
}

// Stops whenever some promise is rejected
Promise.all([promiseFunction(true), promiseFunction(true), promiseFunction(true)])
.then(values => {
    console.log('All Promise.all promises have been successfully resolved');
}).catch(error => console.error(error));

// Never returns error
Promise.allSettled([promiseFunction(true), promiseFunction(false), promiseFunction(true)])
.then(results => {
    for (i = 0; i < results.length; i++) {
        if (results[i].status === 'fulfilled') {
            console.log(results[i].value);
        } else { //results[0].status === ‘rejected’
            console.error(results[i].reason);
        }
    }
});