const express = require("express");
const bodyParser = require("body-parser");
const path = require("path");
const app = express();

app.set("view engine", "ejs");
app.use("/", express.static(path.join(__dirname)));
app.use(bodyParser.urlencoded({ extended: false }));
app.use(bodyParser.json());

app.get("/", (req, res) => {
  res.render("index.html");
});

app.listen(3000, () => {
  console.log("listening");
});
