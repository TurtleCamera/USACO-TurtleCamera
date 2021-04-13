# Information about Triangle
### Summarized concepts
  - Dynamic programming with very mild memoization.
  - Get ready for a relatively straightforward algorithm, but complicated implementation.
  - Merge sort.
    - Override compareTo.
  - Coordinates.
  - Focus on points with the same X coordinate (or same Y coordinate depending on what you sorted by first).
    - Think of a cross shape.
  - Massive output number, so remember to mod by what they gave you in the question.

### Explanation
This should result in a solution that makes you sort twice in O(N * log(N)) time, and you traverse the points three times in O(N) time. This should be good enough for a silver question. Below is an image showing some of the work that I wrote down on scratch paper to help me solve this problem.  
[Image](https://github.com/TurtleCamera/USACO-TurtleCamera/blob/main/CSE%20199%20Workspace/images/Triangle.jpg) 
