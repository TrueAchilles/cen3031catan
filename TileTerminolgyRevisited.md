# Introduction #

The board closely resembles two things. An undirected graph and a co-ordinate system. We all learned about graphs in our Data Structures class, and implementing co-ordinates can be done in several ways. We will use this data structures to store all the roads built  ( edge.hasRoad() ) and all the settlements/cities built( node.hasBuilding() ). We will then apply graph theory to discover each players longest road and to ensure no two settlements are built next to each other.

## Logic Boards Main Goal ##
Our main goal is to have a clean internal representation of the board so that the logic team can utilize the board with very little difficulty.

## Logic Boards Main Problems ##
Our biggest problem is translating the the actual GUI board in to something that is understood on the Logic side and vice-versa.

Also the exact structure needs major consideration.

## Project Requirement Goals ##
We will be completing, or at least facilitating in the completion of, the following [Project Requirements](http://www.cise.ufl.edu/~s73022dg/CEN3031/project_requirements.php)
  * **42** Longest roads
  * **43** Rewrite road system
  * **44** Build conditions for cities
  * **45** Build conditions for settlements
  * **46** Build conditions for roads


# Board layout #
The physical board, shown at the left, consists of a bunch of interconnected hexagons. If we straighten out those hexagons while maintaining all of the intersections then we get the graph on the right side.

![http://www.cise.ufl.edu/~pmeyer/equals.png](http://www.cise.ufl.edu/~pmeyer/equals.png)

From this new board we notice several things
  * A node can only have three edges.
  * Every tile has six nodes.

# Basic principles of Graph Theory #

see [wikipedias graph theory](http://en.wikipedia.org/wiki/Graph_theory) page.