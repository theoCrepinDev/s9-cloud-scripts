const authService = require("../services/authentificationService.js");
const bddService = require("../services/bddService.js");

const verifyToken = async (req,res) => {
    res.set('Access-Control-Allow-Origin', '*')
    if (req.body.token == null){
        console.error('Token not found !')
        res.send("Token not found !")
        return null;
    }
    const {token} = req.body;
    const user = await authService.verifyTokenWithAuthBack(token);
    if (user != null && user.isValid){
        const userId = user.user.id;
        const userList = await bddService.findUserInMongo(userId);
        res.send(userList);
    }
    else{
        console.error('Token not valid !')
        res.send("Token not valid !")
        return null;
    }
    
};



module.exports = {verifyToken};