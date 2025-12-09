const { app, BrowserWindow, ipcMain } = require("electron");
const path = require("path");
const mongoose = require("mongoose");

function createWindow() {
    const win = new BrowserWindow({
        width: 720,
        height: 850,
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
).then(() => console.log("MongoDB conected"))
 .catch(err => console.error("MongoDB connection error at startup", err));

const ContactSchema = new mongoose.Schema({
    // id as a number to allow the crud operation 
    contact_id: { type: Number, unique: true },
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

// A handler = function that responds to a call from the renderer, save contact   
ipcMain.handle("save-contact", async (event, data) => {
    if (mongoose.connection.readyState !== 1) {
        throw new Error("Failed MongoDB is not connected.");
    }

    try {
        if (data.birth_date) {
            data.birth_date = new Date(data.birth_date);
            if (isNaN(data.birth_date.getTime())) {
                throw new Error("Invalid Birth Date format.");
            }
        }
        
        // id generation
        const lastContact = await Contact.findOne({}, {}, { sort: { 'contact_id': -1 } });

        const lastId = (lastContact && lastContact.contact_id) 
            ? parseInt(lastContact.contact_id) 
            : 0;
            
        if (isNaN(lastId)) {
            throw new Error("The last ID in the database is not readable as a number.");
        }
            
        const nextId = lastId + 1;

        //  Add the new ID to the data
        const contactData = { ...data, contact_id: nextId };

        //  Save the new document
        const contact = new Contact(contactData);
        let saved = await contact.save();
        
        if (!saved || !saved.contact_id) {
             throw new Error("Failed to save contact: Empty document returned.");
        }
        
        return saved.contact_id.toString();
    } catch (error) {
        console.error("Error saving contact:", error.message);
        throw new Error("Failed to save contact in the database. Reason: " + error.message);
    }
});

// Handler to find contacts 
ipcMain.handle("find-contact", async (event, query) => {
    if (mongoose.connection.readyState !== 1) {
        throw new Error("MongoDB is not connected. Cannot perform search.");
    }
    
    const regex = new RegExp(query, 'i');
    const queryNum = parseInt(query);

    try {
        let findQuery = {
            $or: [
                { first_name: { $regex: regex } },
                { last_name: { $regex: regex } }
            ]
        };
        if (!isNaN(queryNum)) {
            findQuery.$or.push({ contact_id: queryNum });
        }
        const contacts = await Contact.find(findQuery).lean().exec(); 
        return contacts;
    } catch (error) {
        console.error("Error searching contacts:", error);
        throw new Error("Failed to search contacts in the database. Reason: " + error.message); 
    }
});

// Handler to update contact 
ipcMain.handle("update-contact", async (event, idStr, data) => {
    if (mongoose.connection.readyState !== 1) {
        throw new Error("MongoDB is not connected. Cannot update.");
    }
    
    const id = parseInt(idStr);
    if (isNaN(id)) {
        throw new Error("The provided ID is not a valid number for update.");
    }

    try {
        if (data.birth_date) {
            data.birth_date = new Date(data.birth_date);
            if (isNaN(data.birth_date.getTime())) {
                throw new Error("Invalid Birth Date format for update.");
            }
        }
        
        // Exclude contact_id from the data to update to avoid errors
        delete data.contact_id; 
        
        const updated = await Contact.findOneAndUpdate({ contact_id: id }, data, { new: true }).exec();
        if (!updated) {
            throw new Error("Contact not found for update.");
        }
        return updated.contact_id.toString();
    } catch (error) {
        console.error("Error updating contact:", error.message);
        throw new Error("Failed to update contact in the database. Reason: " + error.message);
    }
});
