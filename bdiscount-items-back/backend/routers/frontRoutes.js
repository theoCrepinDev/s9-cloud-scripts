const express = require('express');
const router = express.Router();
const frontController = require("../controllers/frontController.js");


router.post("/token", frontController.verifyToken);


module.exports = router;