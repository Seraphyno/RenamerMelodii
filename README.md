# RenamerMelodii
Program meant to rename files in a folder to respect draft of Singer - Song.mp3

The rename script has the following flow: 
  - get a hard-coded value for folder location
  - create a File array with all files found in the folder (if any)
  - if at least 1 file is found create a "logger" - PrintWriter working on a BufferedWriter which appends to the file a string received
  as parameter to the write() function
  - write the formatted date to the file
  - for each file:
    - get the name
    - remove all parenthesis and contents
    - remove all digits
    - remove text like "Various Artists"
    - trim the name (remove whitespace from beginning and end)
    - replace .mp with .mp3
    - write to the file the original name and the modified name
    - check if there is a difference in the names and rename the file if so (also outputs to the console)
  - in any other case throw relevant errors
  
 In future versions it may be modified to allow user input in the console as path to folder.
