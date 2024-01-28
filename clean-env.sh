#!/bin/sh
echo Cleaning up
kubectl delete -f postgres.yaml
kubectl delete -f mongo.yaml
kubectl delete -f bdiscount-items-back.yaml
kubectl delete -f bdiscount-authentication-back.yaml
kubectl delete -f gateway.yaml
echo Done
