#!/bin/bash

set -e

./mvnw clean package
printf "\e[32mDONE: find-pair build is complete \e[0m\n"