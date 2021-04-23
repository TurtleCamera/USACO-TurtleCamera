# Information about Icy Perimeter
### Summarized concepts
  - Recursion
  - Matrices
      - Flood filling (I didn't know that it was called this algorithm at the time).
      - Visited matrix.
      - Count a "." more than once if there are multiple # symbols adjacent to it.
      - Padding the edges of the matrix (I didn't do this, but it's still valid)

###### What I learned from this problem
  - There are ice cream blobs represented by a N x N grid of #'s and .'s such that # represents a slot on the grid occupied by ice cream while a . represents a slot on the grid that's empty.  
  - Farmer John wants to compute the are and perimeter of the blob with the largest area.
      - If there are two blobs that have the same area, then Farmer John will prioritize the ice cream blob with a smaller perimeter.

###### Some ideas that I have from the problem
Usually in USACO, it's pretty hard to avoid an O(N^2) solution, because you will have to read in all the entires in O(N^2) anyway. Plus, N is at most 1,000, and the standards aren't that high in silver, so it makes sense for an O(N^2) solution to work. Also, this problem to me screams recursion, however, we need to implement it in such a way that it actually runs in O(N^2).

###### Confusion when doing the sample test case
Initially when I read this problem, I thought it just meant to count the number of # symbols on the edge of each blob. This turns out to be wrong. Let's first examine our test case grid.  
![a](https://github.com/TurtleCamera/USACO-TurtleCamera/blob/main/CSE%20199%20Workspace/images/Ice_Perimeter_2.png)  
This is how I initially pictured the perimeter.  
![a](https://github.com/TurtleCamera/USACO-TurtleCamera/blob/main/CSE%20199%20Workspace/images/Ice_Perimeter_7.png)  
Clearly, this is wrong, because the problem told us that the blob with an area of 2 (the one I circled) has a perimeter of 6, so I immediately changed the way I viewed this question.  

For the purpose of further demonstration, I will pad the edges of the matrix with dots. I didn't do this in my solution, but this is a valid solution, nonetheless.  
![a](https://github.com/TurtleCamera/USACO-TurtleCamera/blob/main/CSE%20199%20Workspace/images/Ice_Perimeter_3.png)  
Next, I circled the dots that touch the # signs, which does total up to 6.  
![a](https://github.com/TurtleCamera/USACO-TurtleCamera/blob/main/CSE%20199%20Workspace/images/Ice_Perimeter_4.png)  
However, we run into the problem when trying to circle the bordering dots of the other blob.  
![a](https://github.com/TurtleCamera/USACO-TurtleCamera/blob/main/CSE%20199%20Workspace/images/Ice_Perimeter_8.png)  
As we can see, this only totals up to 17 blobs, when the problem says that the perimeter of this blob is 22. This took me quite a while to figure out, but it turns out that you count a dot more than once if there are multiple # signs adjacent to the blob. I illustrate this using arrows below.  
![a](https://github.com/TurtleCamera/USACO-TurtleCamera/blob/main/CSE%20199%20Workspace/images/Ice_Perimeter_6.png)  
Now this totals up to 22, and it actually makes the recursion solution slightly easier.  

###### The solution
The solution to the problem is rather straightforward, or definitely much easier to figure out than the problems in the Februrary 2020 contest. I didn't know this at the time, but the recursive algorithm that gets used here is Flood Fill. Once you read in all the symbols into a matrix, you then need to make another matrix of equal size that keeps track of which slots in the matrix that you've already visited. From there, you loop through all the indices in the matrix, marking a spot as visited if we've gone through an index. As soon as we hit a # symbol, we then use recursion to go in all 4 directions from each # symbol to find adjacent # symbols. Note that while you're using recursion, you need to update the visited matrix to make sure recursion doesn't visit the same location twice and thus trigger an infinite runtime stack. As for computing the area and perimeter of each blob, you should increment the area count every time recursion reaches a # symbol and increment the perimeter count every time recursion reaches a . symbol, even if it's the same . symbol that has already been looked at. You should also keep track of the biggest area, and break ties if we get matching areas of blobs.

### Pseudocode
- Read in everything, and create a visited matrix, both of N x N size.
- When finding blobs, you should be looping through all the locations of the matrix.
- Calculating the perimeter of each blob:
    - Incrememnt the perimeter count if the recursion calls hit two cases:
        - We go out of bounds of the matrix (unless the person padded their matrix).
        - We hit a dot.
- Calculating the area of each blob:
    - Increment the area every time we run into a # symbol.
        - Note that the only time we should be marking a location as visited is if we reach a # symbol. This is because the recursion algorithm should stop branching out if it sees dots.  
[Here](https://github.com/TurtleCamera/USACO-TurtleCamera/blob/main/CSE%20199%20Workspace/images/Ice_Perimeter.jpg) is an image of what I wrote on my scratch paper.
