"use strict";

var quitaCeros = require('./quitaCeros.js');
var ordena = require('./ordena.js').ordena;

function quitaCerosYOrdena(array) {
    quitaCeros(array);
    ordena(array);
};

var array = [[7, 0, 2, 1, 0, 1], [3, 0, 0, 2], [7, 9, 0], [6, 5, 0, 1, 0, 2, 0]];

quitaCerosYOrdena(array);
console.log('Anonym function', array);