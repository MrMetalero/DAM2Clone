const os = require('node:os')

console.log('Info OS: ')
console.log("Nombre OS: ", os.platform())
console.log("Version OS: ", os.version())
console.log("Arquitectura: ", os.arch())
console.log("CPUs: ", os.cpus())
console.log("Memoria ", os.totalmem()/1024/1024)
console.log("Uptime: ", os.uptime())
