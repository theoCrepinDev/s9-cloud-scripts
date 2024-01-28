const mongoose = require('mongoose');

// Schéma pour représenter les listes de courses des utilisateurs
const ShoppingListsSchema = new mongoose.Schema({
    userId: {
        type: String,
        required: true
    },
    items: [{
        name: String
    }],
    // Ajoute d'autres champs si nécessaire pour ton application
    // Exemple : date, lieu, etc.
}, { timestamps: true , collection: 'ShoppingLists'});

// Crée un modèle à partir du schéma pour les listes de courses
const ShoppingLists = mongoose.model('ShoppingLists', ShoppingListsSchema);

module.exports = ShoppingLists;
