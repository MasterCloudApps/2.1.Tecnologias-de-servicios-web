"use strict";

function quitaCeros(arrayCeros) {
    for (var i = 0; i < arrayCeros.length; i++) {
        var fila = arrayCeros[i];
        for (var j = 0; j < fila.length; j++) {
            var v = fila[j];
            if (v === 0) {
                fila.splice(j, 1);
                j--;
            }
        }
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

var array = [[7, 0, 2, 1, 0, 1], [3, 0, 0, 2], [7, 9, 0], [6, 5, 0, 1, 0, 2, 0]];

quitaCerosYOrdena(array);

console.log(array);

