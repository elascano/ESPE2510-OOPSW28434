const { app, BrowserWindow, ipcMain } = require("electron");
const path = require("path");
const mongoose = require("mongoose");

function createWindow() {
    const win = new BrowserWindow({
        width: 720,
        height: 600,
        webPreferences: {
            preload: path.join(__dirname, "renderer.js"),
            nodeIntegration: false,
            contextIsolation: true
        }
    });

    win.loadFile("index.html");
}

app.whenReady().then(() => {
    createWindow();

    app.on("activate", () => {
        if (BrowserWindow.getAllWindows().length === 0) createWindow();
    });
});


mongoose.connect(
    "mongodb+srv://Arelys:Arelys1234@cluster0.3u6ujwz.mongodb.net/Contacts"
).then(() => console.log("MongoDB conectado"))
 .catch(err => console.error(err));

const ContactSchema = new mongoose.Schema({
    first_name: String,
    last_name: String,
    birth_date: Date,
    age: Number,
    type: String,
    sex: String,
    hobbies: [String],
    comments: String,
    created_at: { type: Date, default: Date.now }
});

const Contact = mongoose.model("ContactsBook", ContactSchema);

ipcMain.handle("save-contact", async (event, data) => {
    const contact = new Contact(data);
    let saved = await contact.save();
    return saved._id.toString();
});
