#!/usr/bin/env bash

echo "Generating javadoc"
javadoc -d docs/ -sourcepath src/ -subpackages "$(ls src/ -1 | grep -v test | xargs | tr ' ' ':')"
