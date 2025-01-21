console.log(process.argv)

process.on('exit', () => {
    console.log('El proceso terminó')
    console.log('Bye bye!')
})
 //Se cuentan los comandos que se usan para ejecutar el programa
 //osea que el argumento es el 0[node] 1[nombrearchivoejecutar] 2[soy]
console.log('Argumento: ', process.argv[2])
console.log(process.cwd())
process.exit(0)