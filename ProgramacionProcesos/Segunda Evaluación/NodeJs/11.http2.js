const http = require('node:http')
console.log(process.env)
const desiredPort = process.env.PORT ?? 3000

const server = http.createServer((req, res) => {
    if (req.url === '/') {
        res.statusCode = 200 // OK
        res.setHeader('Content-Type', 'text/html; charset=utf-8')
        res.end('<h1>Bienvenido a mi página web</h1>')
    } else if (req.url === '/image') {
        res.statusCode = 200 // OK
        res.setHeader('Content-Type', 'image/png')
        fs.readFile('./Saul.png', (err, data) => {
            if (err) {
                res.statusCode = 500 // Internal Server Error
                res.end('<h1>500 : Internal Server Error</h1>')
            } else {
                res.end(data)
            }
        })
    } else if (req.url === '/png') {
        res.statusCode = 200 //OK
        res.setHeader('Content-Type', 'image/png')
        fs.readFile('./Saul.png', (err,data) => {
            if (err) {
                res,statusCode = 500
                res.end('<h1>500: internal Server Error')
            } else {
                res.statusCode = 200
                res.end(data)
            }
        })
        res.end('<h1>Bienvenido</h1>')
    }
    else {
        res.statusCode = 404
        res.setHeader('Content-Type','text/html; charset=utf-8')
        res.end('<h1>404 NOT FOUND</h1>')

    }
})


server.listen(desiredPort, () => {
    console.log(`server started on: http://localhost:${desiredPort}`)
    })