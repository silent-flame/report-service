#!/bin/bash
set -e
mvn clean install
git add -A && git commit -m "$1"