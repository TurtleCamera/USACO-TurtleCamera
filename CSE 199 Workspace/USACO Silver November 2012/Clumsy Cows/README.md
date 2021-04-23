# Information about Clumsy Cows
### Summarized concepts
  - Greedy algorithm
  - Stack

### Explanation
This problem is about Bessie trying to balance a string of '('s and ')'s. A string is only balanced if every '(' is followed up by a ')', and Bessie must figure out the minimum number of parentheses she needs to change in order to make it balanced.  
There really isn't that much to the problem. With the way the problem is worded (only need to change parts of the string), it suggests that the string length is even. We can use a stack to determine the number of characters that needs to be changed. The first thing I thought of is to push '('s onto the stack and pop them when we see a ')'; however, when the stack is empty after reading in a ')', we need to change that ')' to a '(' and push it onto the stack. We increment the count for the number of characters we need to change by 1 every time this happens. At the end, if we have any remaining '('s on the stack, we need to change the latter half of them to ')', so we divide the size by 2 and add it to our count.  

### Pseudocode
  - Read in the string
  - Create a stack
  - count = 0
  - For each character in the string
      - Push '(' if we see a '('
      - Otherwise pop '(' if we see a ')'
          - But if the stack is empty, then change the ')' to a '(' and push it onto the stack
              - count ++
  - count += stack.size() / 2
  - Print out the count
