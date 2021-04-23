# Information about Clock Tree
### Summarized concepts
  - You should realize that O(N^2) is acceptable, even though there's an O(N) solution, because the standards are low for silver.
  - Minimum spanning tree
  - Get rid of leaf nodes first
  - DFS or BFS (DFS recommended)

### Explanation
###### What I learned from the problem
  - There are N rooms that are connected with N - 1 corridors. This basically screams minimum spanning tree.  
  - Each room has a clock such that the handle points to one of the 12 hours.
  - Bessie wants to change all the clocks to point to 12, but she can only do so by moving each clock handle one at a time. The only way she can move a handle is by walking into a room.

###### Some initial ideas after reading the problem
  - One problem I had with this question is that I kept thinking in terms of finding the most optimal path to synchronize the clocks starting from each node on the tree, but this isn't necessary. You simply need to see if each node is "solvable" -- that is, does there exist a path from a node such that Bessie can synchronize all clocks?
  - I thought of a really simple test case, which just invovles a room with the clock at 12 and a room with a clock at 11. Thinking about it, the only node that's "solvable" is the room set to 12. This gave me one important piece of information: Bessie doesn't have to end on the node she started on.

###### Sample test case
Below shows how I solved the sample test case:  
![Sample](https://github.com/TurtleCamera/USACO-TurtleCamera/blob/main/CSE%20199%20Workspace/images/Clock_Tree_5.png)
![Sample](https://github.com/TurtleCamera/USACO-TurtleCamera/blob/main/CSE%20199%20Workspace/images/Clock_Tree_6.png)
![Sample](https://github.com/TurtleCamera/USACO-TurtleCamera/blob/main/CSE%20199%20Workspace/images/Clock_Tree_7.png)
![Sample](https://github.com/TurtleCamera/USACO-TurtleCamera/blob/main/CSE%20199%20Workspace/images/Clock_Tree_8.png)
![Sample](https://github.com/TurtleCamera/USACO-TurtleCamera/blob/main/CSE%20199%20Workspace/images/Clock_Tree_9.png)
![Sample](https://github.com/TurtleCamera/USACO-TurtleCamera/blob/main/CSE%20199%20Workspace/images/Clock_Tree_10.png)  
I didn't really learn anything from this, so I started making my own test cases on paper.  

###### My own test cases
![Scratch Paper 1](https://github.com/TurtleCamera/USACO-TurtleCamera/blob/main/CSE%20199%20Workspace/images/Clock_Tree_1.jpg)  
![Scratch Paper 2](https://github.com/TurtleCamera/USACO-TurtleCamera/blob/main/CSE%20199%20Workspace/images/Clock_Tree_2.jpg)  
At this point, I realized something crucial: the only way you can get rid of a leaf node is by going back and forth between the leaf node and its parent. Immediately, I thought of a DFS solution, because getting rid of leaf nodes first implies that there is some depth prioritization in this problem, but I felt that calling DFS starting from every node seemed too much along the lines of brute force, so I continued to draw things out on paper.  
![Scratch Paper 3](https://github.com/TurtleCamera/USACO-TurtleCamera/blob/main/CSE%20199%20Workspace/images/Clock_Tree_3.jpg)  
Around this point, I realized another problem that I might have to deal with. What if there is an internal node that's already set to 12. While I was drawing things out, I realized that my potential DFA algorithm is probably going to make that clock go over 12 to 1; however, I remembered something near the beginning of this endeavor that would serve to be useful for resolving this potential edge case, which I will copy and paste below:  
> One problem I had with this question is that I kept thinking in terms of finding the most optimal path to synchronize the clocks starting from each node on the tree, but this isn't necessary. You simply need to see if each node is "solvable" -- that is, does there exist a path from a node such that Bessie can synchronize all clocks?  

This means that going over 12 is fine, as long as I manage to get rid of the leaf nodes. This is because that clock that goes over 12 will eventually become a leaf node, and I'll get rid of it anyway. Thus, I continued to my final page:  
![Scratch Paper 4](https://github.com/TurtleCamera/USACO-TurtleCamera/blob/main/CSE%20199%20Workspace/images/Clock_Tree_4.jpg)  
I started to notice a pattern here. It's that if there's a node that's "solvable," then every node that's even distance from it is also solvable. It was at this moment that I realized that I made a big mistake, however, which is forgetting to check the maximum value of N in the problem: ![Sample](https://github.com/TurtleCamera/USACO-TurtleCamera/blob/main/CSE%20199%20Workspace/images/Clock_Tree_11.png).  
In my mind around this time, I was thinking wait, this is silver we're talking about. Knowing how USACO likes to set up their problems, surely we can get away with a O(N^2) solution, right? This was a massive facepalm moment, because I found out that I just wasted several hours trying to design an algorithm, when it probably wasn't required. 
But I was already too deep into this question, so I went for the O(N) solution anyway. After thinking about this for about half an hour, I thought of a mini proof shown in the last page of my work.

###### Writing some pseudocode
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
