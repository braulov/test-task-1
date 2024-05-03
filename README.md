To solve this problem, let's define a directed graph G (V, E), where V represents the lowercase letters of the English alphabet. 
We will construct E iteratively: consider all possible pairs names[i], names[j] : i < j. Let k be the first position where these strings differ. We add an edge (names[i][k], names[j][k]).

The existence of a permutation of the letters of the English alphabet, such that names will be ordered lexicographically, is equivalent to the existence of a topological sorting in the graph G (by the definition of graph G). A topological sorting exists if and only if there are no cycles in the graph. We can check for cycles using DFS. If no cycles are found, we output the topological sorting (upon exiting each vertex, we record it in the topsort array, and then we reverse the array).

It is also worth noting that if there exist names[i], names[j] : i < j, such that names[j] is a prefix of names[i], the answer is automatically "Impossible" (because for any permutation, names[j] should come before names[i]).
