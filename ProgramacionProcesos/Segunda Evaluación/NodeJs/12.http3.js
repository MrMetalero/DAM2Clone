const http = require('node:http')
console.log(process.env)
const desiredPort = process.env.PORT ?? 3000


const processRequest = (req, res) => {
    const { method, url } = req
    
    switch (method) {
        case 'GET':
            switch (url) {
                case '/':
                    res.statusCode = 200
                    res.setHeader('Content-Type', 'text/html; charset=utf-8')
                    return res.end('<h1><a href="./pokemon/bulbasaur">Bulbasaur</a></h1><br>')

                    case '/pokemon/bulbasaur':
                    res.statusCode = 200
                    res.setHeader('Content-Type', 'text/html; charset=utf-8')
                    const bulbasaur = require('./bulbasaur.json')
                    return res.end(JSON.stringify(bulbasaur))
                default:
                    res.statusCode = 404
                    res.setHeader('Content-Type', 'text/html; charset=utf-8')
                    return res.end('<h1>404 NOT FOUND</h1>')


            }
    }
}


const server = http.createServer(processRequest)


server.listen(desiredPort, () => {
    console.log(`server started on: http://localhost:${desiredPort}`)
})