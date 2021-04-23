# Information about Distant Pastures
### Summarized concepts
  - Matrices
  - Greedy algorithm
      - Dijkstra's algorithm
          - Priority Queue

### Explanation
This problem is about Bessie's movement cost around a field of pastures. Her farm consists of a N x N grid of pastures of two different types: type '(' and type ')'. When Bessie travels between pastures of the same type, it will cost her A units of time. For pastures of different types, it will cost her B units of time to travel between them. Bessie wants to figure which pair of pastures would take her the most time to traverse to, assuming she always takes the shortest path.  


This problem isn't that complicated either, although I do want to mention that N is really small (at most 30), so we can brute force this problem. With that in mind, I just thought of converting the entire matrix of pastures into a graph and running Dijkstra's algorithm starting from each node in the matrix. I then keep track of the biggest cost value that Dijkstra's algorithm produces. One thing to note, however, is that in my implementation, I didn't actually create a graph, but creating a graph is certainly valid.  

### Pseudocode
  - Read in the grid, A value, and B value.
  - worstCost = 0
  - Convert the grid to a graph 
      - For each node in the graph
          - Run Dijkstra's algorithm starting from that node
          - Update worstCost based if Dijkstra's algorithm got a new worst distance
  - Print out the solution
