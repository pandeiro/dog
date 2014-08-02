#!/bin/bash

function try {
    "$@"
    local status=$?
    if [ $status -ne 0 ]; then
        echo "error with $1" >&2
    fi
    return $status
}

lesscss=$(which lessc)

if [ -z "$lesscss" ]; then
    echo
    echo "LessCSS compiler not found; skipping Less compilation"
    echo
else
    echo
    echo "Compiling and minifying Less..."
    echo

    try lessc -x app/styles/main.less > dist/styles.css || exit 1
fi

echo
echo "Compiling ClojureScript..."
echo

ENV=production try lein cljsbuild once production || exit 1


echo
echo "Transforming and minifying HTML..."
echo

try lein run -m {{name}}.html || exit 1

echo
echo "Packaging assets..."
echo

try tar czf target/{{name}}.tar.gz dist || exit 1
