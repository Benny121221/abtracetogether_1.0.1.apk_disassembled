# NOTE, this script only works on systems which use forward-slash for folders
# Thus, you cannot run this on Windows, I used WSL for this

import shutil
import os
from os import path

dir = os.getcwd()

file_list = []
folder_list = []

dryrun = False

for f in os.listdir(dir):
	f_absolute = path.join(dir, f)
	if(path.isfile(f_absolute)):
		file_list.append(f)
	elif(path.isdir(f_absolute)):
		folder_list.append(f)

for fname in file_list:
	path_parts = fname.split('\\')
	
	folder = path.join("", *path_parts[:-1])
	new_name = path.join(folder, path_parts[-1])
	
	for i in range(len(path_parts)):
		new_folder = path.join(dir, *path_parts[:i])
		if(not path.exists(new_folder) and not dryrun):
			#print(f"Created dir: {new_folder}")
			os.mkdir(new_folder)
	
	# print(fname)
	# print(new_name)
	# print()
	# print(dir + "/" + fname)
	# print()
	
	if(not dryrun):
		if(fname != new_name):
			shutil.copyfile(dir + "/" + fname, dir + "/" + new_name)
			os.remove(dir + "/" + fname)

