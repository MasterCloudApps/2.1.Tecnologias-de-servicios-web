"use strict";

var lodash = require('lodash');

function quitaCeros(arrayCeros) {
    for (var i = 0; i < arrayCeros.length; i++) {
        arrayCeros[i] = lodash.without(arrayCeros[i], 0);
    }
}

function ordena(arraySinCeros) {
    var cambio;
    do {
        cambio = false;
        for (var i = 0; i < arraySinCeros.length - 1; i++) {
            if (arraySinCeros[i].length > arraySinCeros[i + 1].length) {
                var aux = arraySinCeros[i];
                arraySinCeros[i] = arraySinCeros[i + 1];
                arraySinCeros[i + 1] = aux;
                cambio = true;
            }
        }
    } while (cambio);
}

function quitaCerosYOrdena(array) {
    quitaCeros(array);
    ordena(array);
};

exports.quitaCerosYOrdena = function (array) {
    quitaCerosYOrdena(array);
}
