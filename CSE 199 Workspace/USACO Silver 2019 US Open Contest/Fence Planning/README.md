# Information about Fence Planning
### Summarized concepts
  - Graphs
      - BFS
  - Visited array or parameter
  - Storing min and max X and Y values

### Explanation
It's all on notebook paper again.  
![Filler](https://github.com/TurtleCamera/USACO-TurtleCamera/blob/main/CSE%20199%20Workspace/images/Fence_Planning_1.jpg)  
![Filler](https://github.com/TurtleCamera/USACO-TurtleCamera/blob/main/CSE%20199%20Workspace/images/Fence_Planning_2.jpg)  
![Filler](https://github.com/TurtleCamera/USACO-TurtleCamera/blob/main/CSE%20199%20Workspace/images/Fence_Planning_3.jpg)  

# Pseudocode
  - Read in the N coordinate values and create nodes for each of them
  - Read in the M edges and link the nodes
  - For each node on the graph
      - Run BFS if we have not visited that node yet
          - Compute the minimum and maximum X and Y values based on the cooridantes of each point in that part of the graph
          - Compute the perimeter, and see if it's the new smallest perimeter
  - Output the perimeter that we deem to be the smallest
