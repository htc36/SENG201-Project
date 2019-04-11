#!/usr/bin/env bash

echo "Generating javadoc"
javadoc -d docs/ -exclude tags -sourcepath src/ -subpackages "$(ls src/ -1 | grep -v test | xargs | tr ' ' ':')"
