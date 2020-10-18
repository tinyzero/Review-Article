#!/bin/bash

file="/docker-entrypoint-initdb.d/my-db"
dbname=postgres

echo "Restoring DB using $file"
pg_restore -U postgres --dbname=$dbname --verbose --single-transaction < "$file" || exit 1