const fs = require('node:fs')

const stats = fs.statSync('Primera Clase/archivo.txt')
console.log(
stats.isFile()+ "\n", 
stats.isDirectory()+ "\n",
stats.isSymbolicLink()+ "\n",
stats.size+ "\n",
stats.birthtime,+"\n",
)