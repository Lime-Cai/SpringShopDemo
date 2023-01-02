#!/bin/sh
CURRENT_DIRECTORY=`dirname $0`
npx tailwindcss -i "$CURRENT_DIRECTORY/input.css" -o "$CURRENT_DIRECTORY/tailwind.css"