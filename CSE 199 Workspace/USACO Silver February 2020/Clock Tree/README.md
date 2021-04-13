# Information about Clock Tree
### Summarized concepts
  - You should realize that O(N^2) is acceptable, even though there's an O(N) solution, because the standards are low for silver.
  - Minimum spanning tree
  - Get rid of leaf nodes first
  - DFS or BFS (DFS recommended)

### Explanation
If you do enough test cases, you may end up realizing that there's an O(N) solution that's possible for this question. I'll leave a few images here to reference to see how I figured it out, but it took a long time to figure that out, so I would still stick with an O(N^2) solution. The images are listed below:
1. [Scratch Paper 1](https://github.com/TurtleCamera/USACO-TurtleCamera/blob/main/CSE%20199%20Workspace/images/Clock_Tree_1.jpg)
2. [Scratch Paper 2](https://github.com/TurtleCamera/USACO-TurtleCamera/blob/main/CSE%20199%20Workspace/images/Clock_Tree_2.jpg)
3. [Scratch Paper 3](https://github.com/TurtleCamera/USACO-TurtleCamera/blob/main/CSE%20199%20Workspace/images/Clock_Tree_3.jpg)
4. [Scratch Paper 4](https://github.com/TurtleCamera/USACO-TurtleCamera/blob/main/CSE%20199%20Workspace/images/Clock_Tree_4.jpg)

O(N^2) is accepted here because the max value for N is just 2,500, which is pretty low; however, I'll still write down the stuff for the O(N) solution too. These are the things that came to my mind while I was attempting this question:
1. Build a minimum spanning tree using the information provided in the question and input
2. Use DFS, because we want to clear all the leaf nodes first. This is because the only way to clear a leaf node is to keep going back and forth between that node and its parent.
    - Increment the clock handle position of each node you traverse by 1.
    - You can make this calculation O(1) time by just taking 12 - leaf node's position and adding that value to the handle value of both the leaf node and its parent.
    - Note that sometimes, you end up incrementing the clock's handle value past 12, in which you would want to mod the value by 12.
3. Keep doing this until you finish traversing all nodes. If your root node's clock handle either ends in 12 or 1 (13 with the way my program was implemented), then that means that node is "solvable."
    - Ending on 13 works, because this means we don't need to stop on the same node that we started from. This technically means we finished setting all clock handles to 12 on a node that's adjacent to the root node, but we decided to advance to the root node and mess up a clock anyway.
4. This is where the program splits between the O(N) and O(N^2) solution:
    - O(N^2) path: Just run DFS on all the nodes and count the nodes that are "solvable."
    - O(N) path: One rule with this particular minimum spanning tree is that if there's a node that's "solvable," then all nodes that are equal distance from it are also "solvable." Basically, this means you only need to run DFS on two nodes in the minimum spanning tree: an arbitrarily selected node and a node adjacent to it. You can then use that information to count the number of nodes are "solvable."
