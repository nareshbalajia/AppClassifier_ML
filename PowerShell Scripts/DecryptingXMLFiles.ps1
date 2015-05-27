#to set the parent folder which contains the encrypted xml files 
$parent='D:\project\apks\exml'
#to set the destination folder to save the decrypted xml files
$destination='D:\project\apks\dexml\.xml'
#To recurse through the parent folder for files which extension .xml
$files= @()
Get-ChildItem $parent -Recurse -Filter "*.xml" | %{
$files=$files+$_.FullName
} 
foreach($f in $files){
#to run the AXMLPrinter2.jar
cmd.exe /c "java -jar AXMLPrinter2.jar $f >> $f.xml"
}