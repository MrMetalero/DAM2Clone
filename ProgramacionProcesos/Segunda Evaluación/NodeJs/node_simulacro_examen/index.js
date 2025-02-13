const express = require('express')
const cors = require('cors')
const books = require('./books.json');

app = express()
app.use(express.json())
app.use(cors())




const getJsonByAuthor = (req, res) => {
    const { autor } = req.query
    const filteredBooks = books.filter(book => book.autor === autor)
  
    res.json(filteredBooks)
}  
app.get("/books", getJsonByAuthor)



//TODO:
const getTitlesByISBN = (req, res) => {
    const { isbn } = req.params;
    const filteredBooks = books.filter(book => book.ISBN.match(isbn));

    let titleIsbnResponse = "<ul>";
    filteredBooks.forEach(book => {
        titleIsbnResponse += `<li>${book.title}</li>`;
    });
    titleIsbnResponse += "</ul>";

    res.send(titleIsbnResponse);
};

app.get("/:isbn", getTitlesByISBN);




const getTitlesJsonByISBN = (req, res) => {
    const {isbn} = req.params
    const filteredBooks = books.filter(
        book => book.ISBN.match(isbn)
    )

    

 

    res.json(filteredBooks)
}  
app.get("/:isbn/json", getTitlesJsonByISBN)

var MessageToSend = "<ul>"
const getAllTitlesList = (req, res) => {
    
    books.forEach(bookElement => {
        MessageToSend += `<li> <a href="${bookElement.imagen_portada}">  ${bookElement.titulo} </a> </li>`
    });
    MessageToSend += "</ul>"
    res.send(MessageToSend)
}  
app.get("/", getAllTitlesList)



const PORT = process.env.PORT ?? 1234
app.listen(PORT, () => {
console.log(`server listening on port http://localhost:${PORT}`)
})