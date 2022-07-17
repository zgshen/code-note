#!/usr/bin/python3
# -*- coding: UTF-8 -*-
# hexo迁移hugo对markdown文件的处理

import os

path = './_posts/'
files = os.listdir(path)

for file in files:
    f = open(path + file)
    fw = open('./hugo/' + file, "w")
    print(f.name)

    lineList = f.readlines()
    s1 = 'categories: '
    s2 = 'tags: '

    s11 = 0
    s22 = 0

    for line in lineList:
        strLen = len(line)  # len=7

        if s1 in line and s11 == 0:
            line = line.replace('categories: ', 'categories: \n    - ')
            s11 = 1
        elif s2 in line and s22 == 0 and strLen > 7:
            line = line.replace('tags: ', 'tags: \n    - ')
            s22 = 1
        fw.write(line)
print('completed...')
