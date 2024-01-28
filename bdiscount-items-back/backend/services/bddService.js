const mongoose = require('mongoose');
const ShoppingLists = require('../models/ShoppingLists.js');
const URL_BDD = process.env.URL_BDD;


// Connexion à la base de données MongoDB sans les options dépréciées

const run = async () => {
    mongoose.connect(URL_BDD )
};
run()
.catch(error => {
    console.log(process.env.URL_AUTH)
    console.log("Erreur connexion base de données : " + error)});



const db = mongoose.connection;

// Gestion des événements de la connexion
db.on('error', console.error.bind(console, 'Erreur de connexion à la base de données :'));
db.once('open', async () => console.log('Connexion a la base de données réussi'));


const findUserInMongo = async (id) => {
    try {
    const UserShoppingList = await ShoppingLists.findOne({ userId: id }).exec();

        if (UserShoppingList){
            console.log('Liste de courses trouvée :', UserShoppingList);
            return UserShoppingList;
        }
        else{
            console.log('Aucune liste de courses trouvée pour cet utilisateur.');
        }
    } catch (error) {
        console.error('Erreur lors de la recherche de la liste de courses :', error);
    }
}

// fonction qui permet d'ajouter les elements de la liste de course dans la base de données si l'utilisateur existe déjà mais créer l'utilisateur si il n'existe pas
const addShoppingList = async (id, items) => {
    try {

        if (id == null || items == null){
            console.log('Liste de courses vide ou pas de id, pas de mise à jour.');
            return;
        }
        const UserShoppingList = await ShoppingLists.findOne({ userId: id }).exec();
        if (UserShoppingList){
            console.log('Liste de courses trouvée :', UserShoppingList);
            //concatenation des deux listes UserShoppingList.list et list
            console.log(UserShoppingList.items);
            console.log(items);
            UserShoppingList.items = UserShoppingList.items.concat(items);
            console.log(UserShoppingList.items);
            console.log('Liste de courses mise à jour :', UserShoppingList);
            UserShoppingList.save();
            console.log('Liste de courses mise à jour :', UserShoppingList);
            return UserShoppingList;
        }
        else{
            console.log('Aucune liste de courses trouvée pour cet utilisateur.');
            console.log('voici les items: ', items)
            const newShoppingList = new ShoppingLists({userId: id, items: items});
            newShoppingList.save();
            console.log('Nouvelle liste de courses créée :', newShoppingList);
            return newShoppingList;
        }
    } catch (error) {
        console.error('Erreur lors de la recherche de la liste de courses :', error);
    }
}



module.exports = {findUserInMongo, addShoppingList};