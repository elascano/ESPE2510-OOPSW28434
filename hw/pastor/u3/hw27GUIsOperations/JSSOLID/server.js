const express = require('express');
const bodyParser = require('body-parser');
const cors = require('cors');
const connectDB = require('./config/db');

// CAMBIO: Importamos rutas de esculturas
const sculptureRoutes = require('./routes/sculptureRoutes');

const app = express();
connectDB();

app.use(cors());
app.use(bodyParser.json());
app.use(express.static('public')); 

// CAMBIO: La URL base ahora será /api/sculptures
app.use('/api/sculptures', sculptureRoutes);

const PORT = 3000;
app.listen(PORT, () => {
    console.log(`Server running on http://localhost:${PORT}`);
});