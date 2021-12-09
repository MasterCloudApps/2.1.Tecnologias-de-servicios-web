// "Functions" vs "Arrow functions" in callbacks

// Common callback function
setTimeout(function () {
    console.log("I've waited 1 second!");
}, 1000);

// Callback with arrow function
setTimeout(() => {
    console.log("I've waited 2 seconds!");
}, 2000);

// Callback with arrow function (single statement)
setTimeout(() => console.log("I've waited 3 seconds!"), 3000);
