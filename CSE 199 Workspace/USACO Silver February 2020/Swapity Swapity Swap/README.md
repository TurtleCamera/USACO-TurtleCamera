# Information about Swapity Swapity Swap
- Summarized concepts:
  - N and K are massive, which means the time complexity limit is probably very strict; just from the numbers, it should immediately be a red flag, that you that you need an O(N) (maybe O(N + K)) solution.
  - Cycles.
  - Think of it in terms of a graph..., but don't actually implement it in terms of a graph.
  - Avoid repeating the same calculation over and over again -- use a smaller solution to solver a bigger solution. I think this counts as dynamic programming, because even though you don't use memoization, you still calculate subproblems to solve bigger problems.
  - Visited array
