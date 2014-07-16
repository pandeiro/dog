#!/bin/bash

lesscss=$(which lessc)

if [ -z "$lesscss" ]; then
    echo
    echo "LessCSS compiler not found; skipping Less compilation"
    echo
else
    echo
    echo "Compiling and minifying Less..."
    echo

    lessc -x app/styles/main.less > dist/styles.css
fi

echo
echo "Compiling ClojureScript..."
echo

ENV=production lein cljsbuild once production

echo
echo "Packaging assets..."
echo

tar czf target/{{name}}.tar.gz dist
