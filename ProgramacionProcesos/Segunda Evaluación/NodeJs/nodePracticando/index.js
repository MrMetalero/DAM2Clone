
app.use(express.json)

app.use((req, res, next) => {
    console.log('request received')
    if (req.method !== 'POST') return next()
    if (req.headers['content-type'] !== 'application/json')
        return next()
    // Aquí solo llegan request POST con Content-Type:
    application/json
    let body = ''
    req.on('data', chunk => {
    body += chunk.toString()
    })
    req.on('end', () => {
    const data = JSON.parse(body)
    data.timestamp = Date.now()
    req.body = data
    next()
    })
    })

