import os,sys
import ntpath

validpath = 'testfiles/valid'
syntax_error_path = 'testfiles/invalid/syntaxErr'
semantic_error_path = 'testfiles/invlaid/semanticErr'

def allfiles(path):
    files = []
    # r=root, d=directories, f = files
    for r, d, f in os.walk(path):
        for file in f:
            if '.wacc' in file:
                files.append(os.path.join(r, file))
    return files

def exit(path):
    f = open(file, "r")
    flag = False
    all_lines = f.readlines()
    for line in all_lines:
        if flag:
            n = int (line[2:])
            return n
        if "Exit" in line :
            flag = True
    return 0

def hasinput(file):
    f = open(file, "r")
    all_lines = f.readlines()
    for line in all_lines:
        if "input" in line :
            return True
    return False

v = allfiles(validpath)
sy = allfiles(syntax_error_path)
se = allfiles(semantic_error_path)

fails = []
for file in v:
    if os.system("./compile " + file) != 0:
        fails.append(file)
for file in sy:
    if os.system("./compile " + file) != 100 * (2 ** 8):
        fails.append(file)
for file in se:
    if os.system("./compile " + file) != 200 * (2**8):
        fails.append(file)

print ("============Front End ============================")

print ("FAILS:")
for f in fails:
    print f

if len(fails) != 0:
    sys.exit(-1)
print ("None")


print ("============Back End ============================")
print ("")
print ("")
print ("")
print ("")

failed = []
unchecked = []

for file in v:
    has_input = hasinput(file)
    name = ntpath.basename(file)
    name = name[:len(name)-5]
    if not has_input:
        exit_code_expected = exit(file) * (2 ** 8)
        os.system("arm-linux-gnueabi-gcc -o test -mcpu=arm1176jzf-s -mtune=arm1176jzf-s " + name + ".s")
        exit_code = os.system("qemu-arm -L /usr/arm-linux-gnueabi/ test")
        if exit_code != exit_code_expected:
            failed.append(file)
    else :
        unchecked.append(file)
    os.system("rm " + name + ".s")
os.system("rm test")


for i in range (20):
    print ("")

print ("unchecked files: ")
for f in unchecked:
    print (f)
print ("FAILED: ")
for f in failed:
    print (f)
if failed == []:
    print ("NONE")
else:
    sys.exit(-1)