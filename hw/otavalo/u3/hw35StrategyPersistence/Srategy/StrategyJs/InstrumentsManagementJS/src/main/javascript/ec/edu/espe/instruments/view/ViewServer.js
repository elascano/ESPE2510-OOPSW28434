const path = require("path");

class ViewServer {
  constructor(app) {
    this.app = app;
  }

  mount(baseDir) {
    this.app.get("/", (req, res) => {
      res.sendFile(path.join(baseDir, "index.html"));
    });
    this.app.get("/app.js", (req, res) => {
      res.sendFile(path.join(baseDir, "app.js"));
    });
  }
}

module.exports = ViewServer;
