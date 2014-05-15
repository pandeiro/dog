#!/bin/bash

lesscss=$(which lessc)

if [ -z "$lesscss" ]; then
    echo "LessCSS compiler not found; skipping Less compilation"
else
    echo "Compiling and minifying Less..."
    lessc -x app/styles/main.less > dist/styles.css
fi

ENV=production lein cljsbuild once production

