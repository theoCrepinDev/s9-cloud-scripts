require('dotenv').config();
const express = require('express');
const app = express();
const port = process.env.PORT;
const authRoutes = require('./routers/frontRoutes.js'); // Importer les routes d'authentification
const bddRoutes = require('./routers/bddRoutes.js'); // Importer les routes d'authentification
const cors=require('cors');

console.debug(process.env.URL_AUTH)

app.use(express.json());
app.use(cors());
app.listen(port, () => {
    console.log(`Example app listening on port ${port}`)
    console.log('version 1.5')
})
app.use('/auth', authRoutes); // Utiliser les routes d'authentification
app.use('/bdd', bddRoutes); // Utiliser les routes d'authentification

