#!/bin/sh
echo Launching postgres database
kubectl apply -f postgres.yaml
echo Please wait for postgres to be ready
kubectl wait --for=condition=ready pod -l app=postgres --timeout=120s
echo Launching MongoDB database
kubectl apply -f mongo.yaml
echo Please wait for MongoDB to be ready
kubectl wait --for=condition=ready pod -l app=mongo --timeout=120s
echo Launching Items API
kubectl apply -f bdiscount-items-back.yaml
echo Launching Authentication API
kubectl apply -f bdiscount-authentication-back.yaml
echo Lauching gateway
kubectl apply -f gateway.yaml
echo Please wait for APIs pods to be ready
kubectl wait --for=condition=ready pod -l app=bdiscount-items-back --timeout=120s
kubectl wait --for=condition=ready pod -l app=bdiscount-authentication-back --timeout=120s
echo Launching Frontend
cd bdiscount-front
npm install
npm run dev &
cd ..
echo Launching Ingress
./ingress-forward.sh