FROM node:lts

WORKDIR /usr/src/app

COPY package*.json ./

RUN npm install

COPY ./backend  ./backend

ENV URL_AUTH="http://bdiscount-authentication-back:8080/api/auth"
ENV PORT="3000"
ENV URL_BDD="mongodb://root:example@mongo-service:27017/ShoppingListsDb?authSource=admin"

EXPOSE 3000

CMD [ "node" , "backend/app.js" ]