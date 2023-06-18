&nbsp;&nbsp;&nbsp;&nbsp;Dijkstra's shortest path algo (short for algorithm) can't be used for graphs with negative weights because you could end up going in a circle of negative weights.<br>This is an extension of Dijkstra's shortest that allows it to be used with negative weights. When vertex A is taken off the priority queue, A will only offer its value to B, A's neighbor, if B is not part of the path that led to A. To implement this, having each vertex wrapper store a collection of paths would require a lot of memory as well as time, because if B accepts A's offer then B will have to copy A's collection in order to add itself to the path so B can offer its collection to its neighbors (B can't just add itself to A's collection and hold a reference to it because A could have neighbors other than B who might accept the path from A and having B as part of the path would be wrong). So what I do is assign a unique prime number to each vertex, and every path is represented by the product of all of its vertexes prime numbers. A can check if B is already part of A's path by dividing A's path-product by B's prime number. If the quotient is a whole number then you knowB was already used in A's path. If the quotient is not a whole number then you know that B was not used in A's path. A prime number is only divisible meaning there's no remainder) by 1 and itself (it has no other factors), so a product of primes won't have any factors aside from the prime numbers that multiplied to get the produ
