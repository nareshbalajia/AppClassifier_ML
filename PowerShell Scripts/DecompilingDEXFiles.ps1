#Setting the source folder which contains the dex files and to set the destination folder to save the decompiled dex files
$parent='D:\project\apks\edex'
$destination='D:\project\apks\dedex'
#To loop through the parent folder for all the files which has the extension .dex
$files=Get-ChildItem $parent -Recurse -Filter "*.dex"
foreach($f in $files){
#To get the  file name to create folders for each apk's source code
$fname=$f.Name.SubString(0);
if(-not (Test-Path $fname)){mkdir $fname};
$fullpath=$destination+"\"+$fname
#To run the dedexer.jar file
cmd.exe /c "java -jar ddx.jar -o -D -r -d $fullpath $f"
}