import os,sys
import ntpath

validpath = 'testfiles/valid'


def allfiles(path):
    files = []
    # r=root, d=directories, f = files
    for r, d, f in os.walk(path):
        for file in f:
            if '.wacc' in file:
                files.append(os.path.join(r, file))
    return files

v = allfiles(validpath)


fail = []
for file in v:
    os.system("./compile " + file)
    name = ntpath.basename(file)
