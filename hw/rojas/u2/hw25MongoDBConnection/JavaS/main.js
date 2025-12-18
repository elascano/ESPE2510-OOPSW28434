const { app, BrowserWindow } = require('electron');
const path = require('path');

function createWindow() {
<<<<<<< HEAD
    const win = new BrowserWindow({
        width: 900, height: 700,
=======

    const win = new BrowserWindow({
        width: 800, 
        height: 700,
        autoHideMenuBar: true,
>>>>>>> 95b5ff6cf684ae826e202c2e63b274c2ad0a5ba8
        webPreferences: {
            nodeIntegration: true,
            contextIsolation: false
        }
    });

<<<<<<< HEAD
    win.loadURL('data:text/html;charset=utf-8,<html><body style="font-family:sans-serif;"><h2>Cargando interfaz...</h2></body></html>');

    win.webContents.openDevTools();

    win.webContents.on('did-finish-load', () => {
        const appPath = path.join(__dirname, 'App.js').replace(/\\/g, '/');

        win.webContents.executeJavaScript(`
            try {
                require('${appPath}');
            } catch (e) {
                document.body.innerHTML = '<h2 style="color:red">OCURRIÓ UN ERROR:</h2><h3 style="color:red">' + e.message + '</h3><pre>' + e.stack + '</pre>';
                console.error(e);
                alert("Error: " + e.message);
=======
    win.loadURL('data:text/html;charset=utf-8,<html><body style="margin:0; background:#ece9d8;"><h2>Cargando...</h2></body></html>');

    const appPath = path.join(__dirname, 'App.js').replace(/\\/g, '/');

    win.webContents.on('did-finish-load', () => {
        win.webContents.executeJavaScript(`
            try {
                require('${appPath}');
            } catch(e) {
                alert("Error cargando App.js: " + e.message);
                console.error(e);
>>>>>>> 95b5ff6cf684ae826e202c2e63b274c2ad0a5ba8
            }
        `);
    });
}

app.whenReady().then(createWindow);