# Information about Icy Perimeter
### Summarized concepts
  - Recursion
  - Matrices
      - Flood filling (I didn't know that it was called this algorithm at the time).
      - Visited matrix.
      - Count a "." more than once if there are multiple # symbols adjacent to it.
      - Padding the edges of the matrix (I didn't do this, but it's still valid)

### Explanation
The implementation of this problem isn't significant. This problem, however, pretty much screams recursion. One thing to note about this problem is that N is at most 1,000, which suggests that an O(N^2) solution is probably allowed. It does make sense, though, since we're dealing with a matrix. You pretty much want to compute the perimeter and area of all the blobs after loading in the data into your matrix; however, to actually achieve an O(N^2) solution, you need to make sure you don't revisit the same slot in the matrix twice. This can be accomplished by making a second matrix that marks which slots have been visited. Other than that, this is pretty much how I computed the perimeters and areas of the blobs:  
- I
