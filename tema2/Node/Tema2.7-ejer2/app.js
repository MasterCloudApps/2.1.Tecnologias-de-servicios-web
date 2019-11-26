"use strict";

var quitaCerosYOrdenaAnonymFunc = require('./anonymFunc.js');
var quitaCerosYOrdenaNamedFunc = require('./namedFunc.js').quitaCerosYOrdena;

var array = [[7, 0, 2, 1, 0, 1], [3, 0, 0, 2], [7, 9, 0], [6, 5, 0, 1, 0, 2, 0]];

quitaCerosYOrdenaAnonymFunc(array);
console.log('Anonym function', array);

quitaCerosYOrdenaNamedFunc(array);
console.log('Named function', array);
