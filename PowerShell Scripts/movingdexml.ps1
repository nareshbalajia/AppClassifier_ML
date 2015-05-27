$location='D:\project\apks\exml'
$dest='D:\project\apks\dexml'
Get-ChildItem -Path $location |
Where-Object{$_.name -like "*.xml.xml"} |
Move-Item -destination $dest

