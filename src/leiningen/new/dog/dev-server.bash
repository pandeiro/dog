#!/bin/bash

trap "kill 0" SIGINT

port=$1 || 3000

echo "Starting the dev-server on port ${port}, just a second..."

# Run inside subshells so they fork but still die on Ctrl-C
(
lein trampoline cljsbuild auto dev &
)

lein trampoline ring server-headless $port


