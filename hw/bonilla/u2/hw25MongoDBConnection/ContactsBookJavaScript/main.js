const { app, BrowserWindow, ipcMain } = require('electron');
const mongoose = require('mongoose');
const path = require('path');

const MONGO_URI = "mongodb+srv://Arelis:Arelis2006@cluster0.qdn4zsf.mongodb.net/ContactsBook";

const contactSchema = new mongoose.Schema({
    first_name: { type: String, required: true },
    last_name: { type: String, required: true },
    birth_date: { type: String, default: "01/01/2000" },
    age: { type: Number, default: 0 },
    type: { type: String },
    sex: { type: String },
    hobbies: { type: String },
    comments: { type: String },
    created_at: { type: Date, default: Date.now }
});

const Contact = mongoose.model('ContactsBook', contactSchema, 'ContactsJS');

mongoose.connect(MONGO_URI);

function createWindow() {
    const mainWindow = new BrowserWindow({
        width: 700,
        height: 700,
        resizable: false,
        webPreferences: {
            preload: path.join(__dirname, 'preload.js'),
            contextIsolation: true,
            nodeIntegration: false
        }
    });

    mainWindow.loadFile(path.join(__dirname, 'index.html'));
}

app.whenReady().then(() => {
    createWindow();

    app.on('activate', () => {
        if (BrowserWindow.getAllWindows().length === 0) {
            createWindow();
        }
    });
});

app.on('window-all-closed', () => {
    if (process.platform !== 'darwin') {
        app.quit();
    }
});

ipcMain.handle('save-contact', async (event, data) => {
    const { first_name, last_name, age, hobbies, type, sex, comments, birth_date } = data;

    if (!first_name || !last_name) {
        return { error: "ERROR: First Name and Last Name are required." };
    }

    const parsedAge = parseInt(age, 10);
    const finalAge = isNaN(parsedAge) ? 0 : parsedAge;

    try {
        const newContact = new Contact({
            first_name,
            last_name,
            birth_date: birth_date || "01/01/2000",
            age: finalAge,
            type,
            sex,
            hobbies,
            comments
        });

        await newContact.save();

        return { message: `Contact '${first_name} ${last_name}' saved.` };

    } catch (e) {
        return { error: `ERROR to save in MongoDB: ${e.message}` };
    }
});