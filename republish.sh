#!/bin/bash

./drafts-site-gen
git add .
git commit -m "$1"
git push
./tickle-pull-drafts.sh





