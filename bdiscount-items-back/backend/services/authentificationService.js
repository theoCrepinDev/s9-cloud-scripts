const axios = require("axios");

const URL = process.env.URL_AUTH + "/check";


const verifyTokenWithAuthBack = async (token) => {

    try {
        const response = await axios.post(URL, {token});
        return response.data;
    }
    catch(error){
        console.error('Erreur lors de la vérification du token avec le service externe:', error);
        return null;
    }
};

module.exports = {verifyTokenWithAuthBack};