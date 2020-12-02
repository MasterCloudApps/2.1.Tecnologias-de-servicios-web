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

module.exports = quitaCeros;
