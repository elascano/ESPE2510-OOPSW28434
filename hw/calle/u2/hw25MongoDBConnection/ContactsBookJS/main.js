const { app, BrowserWindow, ipcMain } = require('electron');
const mongoose = require('mongoose');
const path = require('path');

const MONGO_URI = "mongodb+srv://Emily:Emily2006@cluster0.ynnit6l.mongodb.net/ContactsBookJS?retryWrites=true&w=majority"; 

const contactSchema = new mongoose.Schema({
    contact_id: { type: String, default: null },
    first_name: { type: String, required: true },
    last_name: { type: String, required: true },
    birth_date: { type: String },
    age: { type: Number, default: 0 },
    type: { type: String },
    sex: { type: String },
    hobbies: { type: String },
    comments: { type: String },
    created_at: { type: Date, default: Date.now }
});
const Contact = mongoose.model('Contact', contactSchema, 'Contacts'); 

mongoose.connect(MONGO_URI)
    .then(() => console.log("Connection to MongoDB successful."))
    .catch(err => console.error("Error connecting to MongoDB:", err));

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

  mainWindow.loadFile('index.html');
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
    const { contact_id, first_name, last_name, age, hobbies, type, sex, comments, birth_date } = data;

    if (!first_name || !last_name) {
        return { error: "ERROR: First Name and Last Name are required." };
    }

    if (contact_id && !/^\d+$/.test(contact_id)) {
        return { error: "ERROR: ID must contain only digits." };
    }
    
    const parsedAge = parseInt(age, 10);
    const finalAge = (age && isNaN(parsedAge)) ? 0 : parsedAge || 0;

    try {
        const newContact = new Contact({
            contact_id: contact_id || null,
            first_name: first_name,
            last_name: last_name,
            birth_date: birth_date, 
            age: finalAge,
            type: type,
            sex: sex,
            hobbies: hobbies,
            comments: comments
        });

        await newContact.save();
        
        return { message: `Contact '${first_name} ${last_name}' saved successfully.` };

    } catch (e) {
        console.error("ERROR while saving to MongoDB:", e);
        return { error: `ERROR while saving to MongoDB: ${e.message}` };
    }
});