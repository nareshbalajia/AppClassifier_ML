#to set the parent folder which contains the apks
$parent='D:\project\apks\malicious'
#to set the destination folder to save the encrypted xml files
$destination='D:\project\apks\malicious\exml'
#to recurse through the parent folder for files with extension .xml
$files= @()
Get-ChildItem $parent -Recurse -Filter "*.rar" | %{
$files=$files+$_.FullName
} 
foreach($f in $files){
#to run the winrar command for unzipping
&'C:\Program Files\WinRaR\UnRaR.exe' x -or $f AndroidManifest.xml $destination
}