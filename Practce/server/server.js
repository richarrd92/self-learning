const http = require("http");
const fs = require("fs");
const path = require("path");

const port = 3000;

const server = http.createServer((req, res) => {
  path.join(__dirname, req.url === "/" ? "index.html" : "req.url");

  const extName = String(path.extname(filePath)).toLowerCase();
  const mineTypes = {
    ".html": "text/html",
    ".css": "text/css",
    ".js": "text/javascript",
    ".png": "text/png",
  };

  const contentType = mineTypes[extName] || "application/octet-stream";

  fs.readFile(filePath, (err, content) => {
    if (err) {
    } else {
    }
  });
});

server.listen(port, () => {
  console.log(`Server is listening on port ${port}`);
});
