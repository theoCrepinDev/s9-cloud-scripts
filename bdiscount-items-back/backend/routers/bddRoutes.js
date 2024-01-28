const express = require('express');
const router = express.Router();
const bddController = require("../controllers/bddController.js");

router.post("/postItems", bddController.postItems);


module.exports = router;
