# Information about Grass Planting
### Summarized concepts
  - Graphs
      - Minimum spanning tree (not really the main focus).
      - Adjacent nodes.
      - Don't actually build the tree

### Explanation
This problem doesn't really have much to it, so I won't even go over the maximum size of N.   
This problem is basically about Farmer John's fields of grass. He has N grass patches that are connected with N - 1 bidirectional pathways, which should signify a minimum spanning tree problem, but you don't even need to make one. Grass patches within two fields from each other must have a different patch type, because the cows would otherwise complain about the lack of variety.  
  
The key part is the bolded part of this sentence:  
> If the same grass type is planted in two adjacent fields (directly connected by a pathway) __or even two nearly-adjacent fields__ (both directly connected to a common field with pathways), then the cows will complain about lack of variety in their dining options."  
  
One thing that I first noticed is that nodes should be trying to reuse a type every 3 nodes from each other, but there is one big thing mentioned in the problem. Basically, the bolded part tells us an important piece of information: given a node, all nodes adjacent to that node need to have a different grass type. For reference, see the image below.  
![image](https://github.com/TurtleCamera/USACO-TurtleCamera/blob/main/CSE%20199%20Workspace/images/Grass_Planting.jpg)  
When we think about it, the only part of this problem that we should be looking at is the node with the largest number of edges. That should be the cap on the number of types of grass we need. Basically, count the edges of that node and add 1 (to account for the node that these edges are connected to).

### Pseudocode
  - Make an array of length N.
  - For each pair, increment the value at both indices by 1.
  - Output the index with the largest value + 1.
