import os, sys

path = 'testfiles'

def allfiles(path):
    files = []
    # r=root, d=directories, f = files
    for r, d, f in os.walk(path):
        for file in f:
            if '.wacc' in file:
                files.append(os.path.join(r, file))
    return files


files = allfiles(path)

for file in files:
    os.system("./compile " + file)
    fileNoExt = (file.split('.'))[0]
    os.system("arm-linux-gnueabi-gcc -o " + file +
              "-mcpu=arm1176jzf-s -mtune=arm1176jzf-s " + fileNoExt + ".s")
    os.system(" qemu-arm -L /usr/arm-linux-gnueabi/ " + file)
    
