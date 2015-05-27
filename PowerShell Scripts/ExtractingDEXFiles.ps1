#to set the parent folder which contains the apks\edex
$parent='D:\project\apks\rar'
#to set the destination folder to save the compiled dex files
$destination='D:\project\apks\edex'
#to recurse through the parent folder for files with extension .rar
$files= @()
Get-ChildItem $parent -Recurse -Filter "*.rar" | %{
$files=$files+$_.FullName
} 
foreach($f in $files){
#to run the winrar command for unzippping
&'C:\Program Files\WinRaR\UnRaR.exe' x -or $f *.dex $destination
}