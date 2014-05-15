#!/bin/bash

trap "kill 0" SIGINT

echo "Starting the dev-server, just a second..."

# Run inside subshells so they fork but still die on Ctrl-C
(
lein cljsbuild auto dev &
)

lein ring server-headless


