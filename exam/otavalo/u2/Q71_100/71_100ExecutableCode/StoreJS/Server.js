const express = require('express');
const bodyParser = require('body-parser');
const ProfessorManager = require('./ProfessorManager');
const path = require('path');
const app = express();
const PORT = 3000;
const manager = new ProfessorManager();

app.use(bodyParser.json());

app.use((req, res, next) => {
    res.setHeader('Access-Control-Allow-Origin', '*');
    res.setHeader('Access-Control-Allow-Methods', 'GET, POST, PUT, DELETE');
    res.setHeader('Access-Control-Allow-Headers', 'Content-Type');
    next();
});

app.use(express.static(path.join(__dirname, '')));
app.get('/', (req, res) => {
    res.sendFile(path.join(__dirname, 'index2.html'));
});

// --- API ENDPOINTS ---

app.get('/api/professors', async (req, res) => {
    try {
        const searchTerm = req.query.search || "";
        const professors = await manager.findprofessors(searchTerm);
        res.json(professors);
    } catch (error) {
        res.status(500).json({ error: error.message });
    }
});

app.post('/api/professors', async (req, res) => {
    try {
        const professorId = await manager.saveprofessor(req.body);
        res.status(201).json({ success: true, id: professorId });
    } catch (error) {
        res.status(400).json({ error: error.message });
    }
});

app.delete('/api/professors/:id', async (req, res) => {
    try {
        await manager.deleteprofessor(req.params.id);
        res.json({ success: true, message: "Professor deleted successfully." });
    } catch (error) {
        res.status(400).json({ error: error.message });
    }
});


// 3. SALES (Transaction/POS)
app.post('/api/payment', async (req, res) => {
    try {
        const { customerId, cartItems, paymentMethod } = req.body;
        const result = await manager.processpayment(customerId, cartItems, paymentMethod);
        res.status(201).json({
            success: true,
            message: `payment processed. Total (incl. IVA 15%): $${result.total.toFixed(2)}`,
            paymentId: result.paymentId
        });
    } catch (error) {
        res.status(400).json({ error: error.message });
    }
});


async function startServer() {
    try {
        await manager.connect();

        app.listen(PORT, () => {
            console.log(` Express Server listening on http://localhost:${PORT}`);
            console.log(`Open http://localhost:${PORT} to view index2.html`);
        });
    } catch (error) {
        console.error("Could not start server due to DB connection error:", error.message);
        process.exit(1);
    }
}

startServer();