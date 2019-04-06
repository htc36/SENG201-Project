#!/usr/bin/env python3

import os
import re

FUNC_REGEX = "^\s+public\s(\w+)\s(\w+)\((.*)\).*"

def get_java_files():
    java_files = []
    for root, dirs, files in os.walk("./src/"):
        if "/test" in root:
            continue
        for f in files:
            if f.endswith(".java"):
                fullpath = os.path.join(root, f)
                java_files.append(fullpath)
                
    return java_files

def get_uncommented_functions(java_file):
    with open(java_file, 'r') as f:
        lines = f.readlines()

    functions = dict()
    prev_line = ""
    for line in lines:
        matches = re.match(FUNC_REGEX, line) 
        if matches and "*/" not in prev_line:
            # whole line, return type, arguments
            functions[matches.group(0)] = (matches.group(1), matches.group(3))

        prev_line = line

    return functions

def add_comments(java_file, uncommented_funcs):
    temp_file = java_file + ".tmp"
    skip = False
    with open(java_file, 'r') as f:
        with open(temp_file, 'w') as t:
            for line in f.readlines():
                clean_line = line.rstrip()
                if "@Override" in clean_line:
                    skip = True
                elif clean_line in uncommented_funcs.keys():
                    num_spaces = len(clean_line.split("public")[0])
                    t.write(" " * num_spaces + "/**\n")
                    t.write(" " * num_spaces + " * <<auto generated javadoc comment>>\n")
                    return_type, args = uncommented_funcs[clean_line]
                    if args:
                        for arg in args.split(','):
                            arg_type, arg_name = arg.split(' ')
                            t.write(" " * num_spaces + " * @param " + arg_name + " <<Param Description>>\n")
                    if return_type != "void":
                        t.write(" " * num_spaces + " * @return " + return_type + " <<Return Description>>\n")
                    t.write(" " * num_spaces + " */\n")
                    if skip:
                        t.write(" " * num_spaces + "@Override\n")
                        skip = False
                if not skip:
                    t.write(line)
                

def rewrite_original_file(java_file):
    os.rename(java_file + ".tmp", java_file)

def main():
    java_files = get_java_files()
    for java_file in java_files:
        uncommented_funcs = get_uncommented_functions(java_file)
        add_comments(java_file, uncommented_funcs)
        rewrite_original_file(java_file)

if __name__ == "__main__":
    main()
