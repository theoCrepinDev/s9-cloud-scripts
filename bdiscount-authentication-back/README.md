# To start the application :
## First launch a docker for postgres
    docker run -d --name postgres -e POSTGRES_PASSWORD=monmotdepasse -p 5000:5432 postgres
## Create db structure
Use the sql_template.sql file to create database
