const bddService = require("../services/bddService.js");
const authService = require("../services/authentificationService.js");

const findUser = async (req, res) => {
    const userId = req.body.id;
    const user = await bddService.findUserInMongo(userId);
    console.log("bddService : " + user);
    res.send(user);

}

//la meme chose que la fonction au dessus mais pour addUser
const postItems = async (req, res) => {
    res.set('Access-Control-Allow-Origin', '*')
    console.log("req.body.token : " + req.body.token);
    const token = req.body.token;
    console.log("token : " + token);
    const user = await authService.verifyTokenWithAuthBack(token);
    console.log("user : " + user);
    console.log("user.id : " + user.id);
    const userId = user.user.id;
    const items = req.body.items;
    const newUser = await bddService.addShoppingList(userId, items);
    res.send(newUser);

}



module.exports = { findUser, postItems };