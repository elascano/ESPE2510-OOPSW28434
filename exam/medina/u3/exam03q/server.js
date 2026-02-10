import express from 'express';
import mongoose from 'mongoose';
import cors from 'cors';
import dotenv from 'dotenv';
import path from 'path';
import { fileURLToPath } from 'url';

dotenv.config();

const __filename = fileURLToPath(import.meta.url);
const __dirname = path.dirname(__filename);

const app = express();
const PORT = process.env.PORT || 3012;
const MONGODB_URI = process.env.MONGODB_URI || 'mongodb+srv://Joseph:Joseph1751774793@cluster0.h8pi0ir.mongodb.net/strategyMedina?retryWrites=true&w=majority';

app.use(cors());
app.use(express.json());
app.use(express.static(path.join(__dirname, 'ec/edu/espe/question')));


mongoose
  .connect(MONGODB_URI, {
    useNewUrlParser: true,
    useUnifiedTopology: true
  })
  .then(() => {
    console.log('MongoDB connected successfully');
    console.log('Database:', MONGODB_URI.split('/').pop().split('?')[0]);
  })
  .catch(err => {
    console.error('MongoDB connection error:', err.message);
    console.error('Attempted URI:', MONGODB_URI);
    process.exit(1);
  });

const sortingResultSchema = new mongoose.Schema({
  unsorted: {
    type: String,
    required: true
  },
  size: {
    type: Number,
    required: true,
    min: 2
  },
  sortAlgorithm: {
    type: String,
    required: true,
    enum: ['BubbleSort', 'InsertionSort', 'QuickSort']
  },
  sorted: {
    type: String,
    required: true
  },
  createdAt: {
    type: Date,
    default: Date.now
  }
});

const SortingResult = mongoose.model('SortingResult', sortingResultSchema);


app.get('/api/sorting-results', async (req, res) => {
  try {
    const results = await SortingResult.find().sort({ createdAt: -1 });
    res.json({
      success: true,
      count: results.length,
      data: results
    });
  } catch (error) {
    console.error('Error fetching results:', error);
    res.status(500).json({
      success: false,
      error: 'Failed to fetch sorting results'
    });
  }
});


app.post('/api/sorting-results', async (req, res) => {
  try {
    const { unsorted, size, sortAlgorithm, sorted } = req.body;

    console.log('POST request received with data:', { unsorted, size, sortAlgorithm, sorted });

    if (!unsorted || !size || !sortAlgorithm || !sorted) {
      console.log('Missing required fields');
      return res.status(400).json({
        success: false,
        error: 'Missing required fields'
      });
    }

    if (size < 2) {
      return res.status(400).json({
        success: false,
        error: 'Array size must be at least 2'
      });
    }

    const validAlgorithms = ['BubbleSort', 'InsertionSort', 'QuickSort'];
    if (!validAlgorithms.includes(sortAlgorithm)) {
      return res.status(400).json({
        success: false,
        error: 'Invalid sorting algorithm'
      });
    }

    const newResult = new SortingResult({
      unsorted,
      size,
      sortAlgorithm,
      sorted
    });

    const savedResult = await newResult.save();
    console.log('Data saved to MongoDB successfully:', savedResult);

    res.status(201).json({
      success: true,
      message: 'Sorting result saved successfully',
      data: savedResult
    });
  } catch (error) {
    console.error('Error saving result:', error);
    res.status(500).json({
      success: false,
      error: 'Failed to save sorting result'
    });
  }
});

app.get('/api/sorting-results/:id', async (req, res) => {
  try {
    const result = await SortingResult.findById(req.params.id);

    if (!result) {
      return res.status(404).json({
        success: false,
        error: 'Sorting result not found'
      });
    }

    res.json({
      success: true,
      data: result
    });
  } catch (error) {
    console.error('Error fetching result:', error);
    res.status(500).json({
      success: false,
      error: 'Failed to fetch sorting result'
    });
  }
});

app.delete('/api/sorting-results/:id', async (req, res) => {
  try {
    const result = await SortingResult.findByIdAndDelete(req.params.id);

    if (!result) {
      return res.status(404).json({
        success: false,
        error: 'Sorting result not found'
      });
    }

    res.json({
      success: true,
      message: 'Sorting result deleted successfully'
    });
  } catch (error) {
    console.error('Error deleting result:', error);
    res.status(500).json({
      success: false,
      error: 'Failed to delete sorting result'
    });
  }
});

app.get('/', (req, res) => {
  res.sendFile(path.join(__dirname, 'ec/edu/espe/question/view/index.html'));
});

app.use((err, req, res, next) => {
  console.error('Unhandled error:', err);
  res.status(500).json({
    success: false,
    error: 'Internal server error'
  });
});

app.listen(PORT, () => {
  console.log(`Server is running on http://localhost:${PORT}`);
  console.log(`MongoDB database: strategyMedina`);
});

export default app;
