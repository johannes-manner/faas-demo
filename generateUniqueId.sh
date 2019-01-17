#!/bin/bash

NEW_UUID=$(cat /dev/urandom | tr -dc 'a-z0-9' | fold -w 32 | head -n 1)

echo "Created unique identifier $NEW_UUID"

echo $NEW_UUID > $1
