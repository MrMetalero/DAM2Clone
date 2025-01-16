const fs = require('node:fs')
 // Esta expresion lambda es el callback en los que se obtienen los ressultados _err y text
 //y se utilizan en el cuerpo de la funcion
fs.readFile('./archivo.txt', 'utf-8', (_err, text) => { 
    console.log('primer texto: ', text)
    })
    console.log('Leyendo segundo archivo...')
    fs.readFile('./archivo2.txt', 'utf-8', (_err, text2) => {
    console.log('segundo texto: ', text2)
    })

    console.log("fin de lecturas")