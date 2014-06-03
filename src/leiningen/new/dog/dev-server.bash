#!/bin/bash

trap "kill 0" SIGINT

echo "Starting the dev-server, just a second..."

# Run inside subshells so they fork but still die on Ctrl-C
(
lein trampoline cljsbuild auto dev &
)

lein trampoline ring server-headless


