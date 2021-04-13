# Information about Clock Tree
### Summarized concepts
  - You should realize that O(N^2) is acceptable, even though there's an O(N) solution, because the standards are low for silver.
  - Minimum spanning tree
  - Get rid of leaf nodes first
  - DFS or BFS (DFS recommended)

### Explanation
If you do enough test cases, you may end up realizing that there's an O(N) solution that's possible for this question. I'll leave a few images here to reference to see how 
I figured it out, but it took a long time to figure that out, so I would still stick with an O(N^2) solution. The images are listed below:
1. [Scratch Paper 1](https://github.com/TurtleCamera/USACO-TurtleCamera/blob/main/CSE%20199%20Workspace/images/Clock_Tree_1.jpg)
2. [Scratch Paper 2](https://github.com/TurtleCamera/USACO-TurtleCamera/blob/main/CSE%20199%20Workspace/images/Clock_Tree_2.jpg)
3. [Scratch Paper 3](https://github.com/TurtleCamera/USACO-TurtleCamera/blob/main/CSE%20199%20Workspace/images/Clock_Tree_3.jpg)
4. [Scratch Paper 4](https://github.com/TurtleCamera/USACO-TurtleCamera/blob/main/CSE%20199%20Workspace/images/Clock_Tree_4.jpg)

O(N^2) is accepted here because the max value for N is just 2,500, which is pretty low; however, I'll still write down the stuff for the O(N) solution too. These are the things
that came to my mind while I was attempting this question:
1. Build a minimum spanning tree 
