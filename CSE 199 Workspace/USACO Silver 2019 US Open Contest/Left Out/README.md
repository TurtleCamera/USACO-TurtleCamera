# Information about Left Out
### Summarized concepts
  - This problem doesn't use any set algorithms that you've learned
  - Find the pattern in the problem and exploit it

### Explanation
All of my explanations are on notebook paper, so I'll put images below.  
![Filler](https://github.com/TurtleCamera/USACO-TurtleCamera/blob/main/CSE%20199%20Workspace/images/Left_Out_1.jpg)  
![Filler](https://github.com/TurtleCamera/USACO-TurtleCamera/blob/main/CSE%20199%20Workspace/images/Left_Out_2.jpg)  
![Filler](https://github.com/TurtleCamera/USACO-TurtleCamera/blob/main/CSE%20199%20Workspace/images/Left_Out_3.jpg)  
![Filler](https://github.com/TurtleCamera/USACO-TurtleCamera/blob/main/CSE%20199%20Workspace/images/Left_Out_4.jpg)  
![Filler](https://github.com/TurtleCamera/USACO-TurtleCamera/blob/main/CSE%20199%20Workspace/images/Left_Out_5.jpg)  
![Filler](https://github.com/TurtleCamera/USACO-TurtleCamera/blob/main/CSE%20199%20Workspace/images/Left_Out_6.jpg)  

### Pseudocode
  - Read in all the input and store the Ls and Rs in a matrix
  - Go through all rows, and find the row that's neither a copy of other rows or an inverted version of other rows -- that is, find the row that has one cow that doesn't match the normal or inverted pattern of other rows
  - Do the same thing for columns
  - Print out the row and column, although if all of the rows and columns are the same or inverted from each other, then output -1 instead
