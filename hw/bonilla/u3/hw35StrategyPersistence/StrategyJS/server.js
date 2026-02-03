import express from 'express';
import path from 'path';
import { JsonPersistence } from './utils/jsonpersistence.js';
import { CsvPersistence } from './utils/csvpersistence.js';
import { MongoPersistence } from './utils/mongopersistence.js';

const app = express();
const PORT = 3000;

app.use(express.json());
app.use(express.static('view')); 

app.get('/api/parkings', async (req, res) => {
  const strategy = req.query.strategy;
  let data = [];

  switch(strategy) {
    case 'JSON':
      data = await jsonPersistence.read();
      break;
    case 'CSV':
      data = await csvPersistence.read();
      break;
    case 'MongoDB':
      data = await mongoPersistence.read();
      break;
  }

  res.json(data);
});

app.get('/', (req, res) => {
  res.sendFile(path.resolve('view/frmparking.html'));
});

app.listen(PORT, () => {
  console.log(`Server running at http://localhost:${PORT}`);
});