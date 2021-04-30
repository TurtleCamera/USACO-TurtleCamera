# Information about Cow Steeplechase II
### Summarized concepts
  - Intersecting segments
  - Sweeping Line algorithm
      - Sets
          - Binary search tree for the set
  - Merge Sort or Priority Queue

### Explanation
Most of my explanations are on notebook paper, so I'll add images below.  
![Filler](https://github.com/TurtleCamera/USACO-TurtleCamera/blob/main/CSE%20199%20Workspace/images/Steeplechase_17.jpg)  
![Filler](https://github.com/TurtleCamera/USACO-TurtleCamera/blob/main/CSE%20199%20Workspace/images/Steeplechase_18.jpg)  
![Filler](https://github.com/TurtleCamera/USACO-TurtleCamera/blob/main/CSE%20199%20Workspace/images/Steeplechase_19.jpg)  
![Filler](https://github.com/TurtleCamera/USACO-TurtleCamera/blob/main/CSE%20199%20Workspace/images/Steeplechase_20.jpg)  
![Filler](https://github.com/TurtleCamera/USACO-TurtleCamera/blob/main/CSE%20199%20Workspace/images/Steeplechase_21.jpg)  
![Filler](https://github.com/TurtleCamera/USACO-TurtleCamera/blob/main/CSE%20199%20Workspace/images/Steeplechase_22.jpg)  
![Filler](https://github.com/TurtleCamera/USACO-TurtleCamera/blob/main/CSE%20199%20Workspace/images/Steeplechase_23.jpg)  
![Filler](https://github.com/TurtleCamera/USACO-TurtleCamera/blob/main/CSE%20199%20Workspace/images/Steeplechase_1.png)  
![Filler](https://github.com/TurtleCamera/USACO-TurtleCamera/blob/main/CSE%20199%20Workspace/images/Steeplechase_2.png)  
![Filler](https://github.com/TurtleCamera/USACO-TurtleCamera/blob/main/CSE%20199%20Workspace/images/Steeplechase_3.png)  
![Filler](https://github.com/TurtleCamera/USACO-TurtleCamera/blob/main/CSE%20199%20Workspace/images/Steeplechase_4.png)  
![Filler](https://github.com/TurtleCamera/USACO-TurtleCamera/blob/main/CSE%20199%20Workspace/images/Steeplechase_5.png)  
![Filler](https://github.com/TurtleCamera/USACO-TurtleCamera/blob/main/CSE%20199%20Workspace/images/Steeplechase_6.png)  
![Filler](https://github.com/TurtleCamera/USACO-TurtleCamera/blob/main/CSE%20199%20Workspace/images/Steeplechase_7.png)  
![Filler](https://github.com/TurtleCamera/USACO-TurtleCamera/blob/main/CSE%20199%20Workspace/images/Steeplechase_8.png)  
![Filler](https://github.com/TurtleCamera/USACO-TurtleCamera/blob/main/CSE%20199%20Workspace/images/Steeplechase_9.png)  
![Filler](https://github.com/TurtleCamera/USACO-TurtleCamera/blob/main/CSE%20199%20Workspace/images/Steeplechase_10.png)  
![Filler](https://github.com/TurtleCamera/USACO-TurtleCamera/blob/main/CSE%20199%20Workspace/images/Steeplechase_11.png)  
![Filler](https://github.com/TurtleCamera/USACO-TurtleCamera/blob/main/CSE%20199%20Workspace/images/Steeplechase_12.png)  
![Filler](https://github.com/TurtleCamera/USACO-TurtleCamera/blob/main/CSE%20199%20Workspace/images/Steeplechase_13.png)  
![Filler](https://github.com/TurtleCamera/USACO-TurtleCamera/blob/main/CSE%20199%20Workspace/images/Steeplechase_14.png)  
![Filler](https://github.com/TurtleCamera/USACO-TurtleCamera/blob/main/CSE%20199%20Workspace/images/Steeplechase_15.png)  
![Filler](https://github.com/TurtleCamera/USACO-TurtleCamera/blob/main/CSE%20199%20Workspace/images/Steeplechase_16.png)  
![Filler](https://github.com/TurtleCamera/USACO-TurtleCamera/blob/main/CSE%20199%20Workspace/images/Steeplechase_24.jpg)  
![Filler](https://github.com/TurtleCamera/USACO-TurtleCamera/blob/main/CSE%20199%20Workspace/images/Steeplechase_25.jpg)  
![Filler](https://github.com/TurtleCamera/USACO-TurtleCamera/blob/main/CSE%20199%20Workspace/images/Steeplechase_26.jpg)  
![Filler](https://github.com/TurtleCamera/USACO-TurtleCamera/blob/main/CSE%20199%20Workspace/images/Steeplechase_27.jpg)  
![Filler](https://github.com/TurtleCamera/USACO-TurtleCamera/blob/main/CSE%20199%20Workspace/images/Steeplechase_28.jpg)  
![Filler](https://github.com/TurtleCamera/USACO-TurtleCamera/blob/main/CSE%20199%20Workspace/images/Steeplechase_29.jpg)  
![Filler](https://github.com/TurtleCamera/USACO-TurtleCamera/blob/main/CSE%20199%20Workspace/images/Steeplechase_30.jpg)  

# Pseudocode  
  - Read in the input and create segments
  - Initialize a set
  - Run the Sweeping Line algorithm by "scanning" from left to right
      - If we encounter a starting point of a line
          - Add the starting point to the set
          - Check if adjacent points intersect with the point we just encountered (note: if you're on Java and using the TreeSet, you will probably need to get the adjacent points before adding the point we just encountered, otherwise floor and ceiling will just return our current point)
              - If so, then store the lines and break from this loop
      - If we encounter an ending point
          - Remove the starting point associated with that line's ending point
          - Check if the adjacent points intersect with the starting point associated with this ending point
              - If so, then store the lines and break from this loop
  - For each line that we have
      - Check if this line intersects either line that we stored from the Sweeping Line algorithm
      - Keep track of the count of intersections
  - Print out the line with more intersections, although if both lines have the same number of intersections, then print out the line with the earlier index
