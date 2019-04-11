#!/usr/bin/env python3

import os
import re

FUNC_REGEX = "^\s+public(\sstatic)?\s([\w<>]+\s)?(\w+)\((.*)\).*"

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

def get_functions(java_file):
    with open(java_file, 'r') as f:
        lines = f.readlines()

    functions = dict()
    prev_line = ""
    for line in lines:
        matches = re.match(FUNC_REGEX, line) 
        if matches:
            # whole line, return type, arguments
            if matches.group(2):
                functions[matches.group(0)] = (matches.group(2).rstrip(), matches.group(4))
            else:
                functions[matches.group(0)] = ("" , matches.group(4))

        prev_line = line

    return functions

def add_comments(java_file, funcs):
    temp_file = java_file + ".tmp"
    record = False
    override = False
    rewrite = False
    comments = []
    with open(java_file, 'r') as f:
        with open(temp_file, 'w') as t:
            for line in f.readlines():
                clean_line = line.rstrip()
                num_spaces = len(clean_line.split("public")[0])
                if "*/" in clean_line and record:
                    comments.append(line)
                    record = False
                    continue
                elif "@param" in clean_line and not record:
                    continue
                elif "@return" in clean_line and not record:
                    continue
                elif "*/" in clean_line and not record:
                    continue
                elif record:
                    if "auto generated javadoc comment" in clean_line:
                        comments = []
                        record = False
                        continue
                    comments.append(line)
                    continue
                elif "/*" in clean_line:
                    comments.append(line)
                    record = True
                    continue
                elif "@Override" in clean_line:
                    override = True
                    continue
                elif clean_line in funcs.keys():
                    return_type, args = funcs[clean_line]
                    if comments:
                        for comment in comments:
                            t.write(comment)
                        comments = []

                    else:
                        t.write(" " * num_spaces + "/**\n")
                        t.write(" " * num_spaces + " * <<auto generated javadoc comment>>\n")
                        if args:
                            for arg in args.split(','):
                                param = arg.lstrip()
                                _, arg_var = param.split(' ')
                                t.write(" " * num_spaces + " * @param " + arg_var + " <<Param Desc>>\n")
                        if return_type and return_type != "void":
                            t.write(" " * num_spaces + " * @return " + return_type + " <<Return Desc>>\n")
                        t.write(" " * num_spaces + " */\n")

                    if override:
                        t.write(" " * num_spaces + "@Override\n")
                        override = False
                t.write(line)

def rewrite_original_file(java_file):
    os.rename(java_file + ".tmp", java_file)

def main():
    java_files = get_java_files()
    for java_file in java_files:
        uncommented_funcs = get_functions(java_file)
        add_comments(java_file, uncommented_funcs)
        rewrite_original_file(java_file)

if __name__ == "__main__":
    main()
