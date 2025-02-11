const http = require('node:http');
const fs = require('node:fs');
const path = require('node:path');

const desiredPort = process.env.PORT ?? 3000;
const pokemonFolder = path.join(__dirname, 'pokemons'); // Carpeta donde están los JSON

const processRequest = (req, res) => {
    const { method, url } = req;

    if (method === 'GET') {
        if (url === '/') {
            // Listar archivos en la carpeta 'pokemons'
            fs.readdir(pokemonFolder, (err, files) => {
                if (err) {
                    res.statusCode = 500;
                    res.setHeader('Content-Type', 'text/html; charset=utf-8');
                    return res.end('<h1>500: Internal Server Error</h1>');
                }

                const pokemonLinks = files
                    .filter(file => file.endsWith('.json'))
                    .map(file => {
                        const name = path.basename(file, '.json');
                        return `<h1><a href="/pokemon/${name}">${name}</a></h1>`;
                    })
                    .join('<br>');

                res.statusCode = 200;
                res.setHeader('Content-Type', 'text/html; charset=utf-8');
                return res.end(pokemonLinks || '<h1>No Pokémon found</h1>');
            });
        } else if (url.startsWith('/pokemon/')) {
            const pokemonName = url.split('/').pop();
            const filePath = path.join(pokemonFolder, `${pokemonName}.json`);

            fs.readFile(filePath, 'utf8', (err, data) => {
                if (err) {
                    res.statusCode = 404;
                    res.setHeader('Content-Type', 'text/html; charset=utf-8');
                    return res.end('<h1>404: Pokémon not found</h1>');
                }

                res.statusCode = 200;
                res.setHeader('Content-Type', 'application/json; charset=utf-8');
                return res.end(data);
            });
        } else {
            res.statusCode = 404;
            res.setHeader('Content-Type', 'text/html; charset=utf-8');
            return res.end('<h1>404 NOT FOUND</h1>');
        }
    }
};


const server = http.createServer(processRequest);

server.listen(desiredPort, () => {
    console.log(`Server started on: http://localhost:${desiredPort}`);
});
