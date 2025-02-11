const express = require('express') // require -> commonJS
const cors = require('cors')
const movies = require('./movies.json')

const app = express()
app.use(express.json())
app.use(cors())

// AQUI VA EL RESTO DE MIS COSAS
// RECUPERAR TODAS LAS PELICULAS
// Y OPCION DE FILTRAR POR GÉNERO
//RECORDAR QUE EL PARÁMETRO GENRE TIENE QUE SER EL MISMO QUE EL DE LA LLAMADA HTTP
const getAllmovies =(req, res) => {
    const { genre } = req.query
    if (genre) {
        const filteredMovies = movies.filter(
            // movie => movie.genre.includes(genre)
            movie => movie.genre.some(
                eachgenre => eachgenre.toLowerCase() === genre.toLowerCase()
            )
        )
        res.json(filteredMovies)
    } else{
        res.json(movies)
    }
  
    

}
app.get("/movies", getAllmovies)

//RECUPERAR PELÍCULAS POR ID

const getMovieID =(req, res) => {
    const { id } = req.params
    const movie = movies.find(movie => movie.id === id)
    if (movie) {
        return res.send(`la peli es : ${movie.title}`)
    }else{
        res.status(404).send(`No se ha encontrado la película con ID: ${id}`)
    }
    res.send(`has pedido la película con ID: ${id}`)

}
app.get('/movies/:id', getMovieID)





const PORT = process.env.PORT ?? 1234
app.listen(PORT, () => {
console.log(`server listening on port http://localhost:${PORT}`)
})