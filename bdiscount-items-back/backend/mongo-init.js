db.createCollection('ShoppingLists');
db.ShoppingLists.insert({
    "userId": "626e8b73bc8b82eb4fe36a01",
    "items": [
        { "name": "Pommes" },
        { "name": "Lait" }
    ]
});
db.ShoppingLists.insert({
    "userId": "626e8b73bc8b82eb4fe36a02",
    "items": [
        { "name": "Pain" },
        { "name": "Fromage" },
        { "name": "Tomates" }
    ]
});
db.createUser({
    user: "root",
    pwd: "example",
    roles: [
        {
            role: "readWrite",
            db: "ShoppingListsDb"
        },
        {
            role: "dbAdmin",
            db: "ShoppingListsDb"
        }
    ]
});