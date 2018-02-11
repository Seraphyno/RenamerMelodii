# RenamerMelodii
Program meant to rename files in a folder (and subfolders). Target: Singer - Song.mp3

The rename script has the following flow: 
 - Generate a UI where the user should paste a folder location
 - check location and retrieve a list of paths for files from folder/subfolder(s)
 - pass each name path through a renamer and retrieve a new name
 - store pairs of (currentName, newName) including paths
 - for each pair attempt a rename
 - output in the UI's log area
 - output all to a file named "history.txt"
 - in any other case throw relevant errors
 
 Currently the rename works as follows:
 - delete all digits
 - delete all paranthesis and contents ()
 - delete all brackets and contents []
 - delete keywords: "Various Artists - ", "Various Artists", "OST", "Official Soundtrack", "Soundtrack"
 - delete all links
 - delete whitespace from beginning and end of name (extension is included)
 - replace all underlines with spaces ("_" -> " ")
 - after the digit reduction change extension back to "mp3"
 
 For issues encountered or suggestions please submit an issue here:
 	- https://github.com/Seraphyno/RenamerMelodii/issues