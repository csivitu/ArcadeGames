# Checkers-Game
An AI based application
## Synopsis
1. Developed checkers game with different difficulty levels using alpha-beta tree search algorithm and 12 different heuristic functions.
2. The user has the option of selecting who plays first(user or computer) and the difficulty level of the game (easy,medium,hard).
3. The different difficulty levels are implemented using different cut-off levels in the alpha-beta tree search algorithm and evaluation function which is a weighted sum of 12 different heuristics functions.
4. The legal moves based on the rules of the game are highlighted in green for every selected user checkers piece.Human piece is a Red Checkers Piece and Computer Piece is a Blue Checkers Piece.
5. Checkers Alpha-Beta Tree Statistics(i.e. Max depth reached,number of nodes expanded,number of times pruning occured,alpha-beta tree cut off level) are also displayed after every computer move.The maximum time for a computer move to take place is 15 seconds.
5. Technologies Used: Java and JavaFx for GUI.
## Rules for the Game
1. The checker board consists of 6 x 6 alternating light and dark squares. The board is placed so that the left corner square on each player’s side is a dark square.
2. Each player starts out with six playing pieces.
3. Each player takes turn to make a move. There are two types of moves: regular moves and capture moves.
   1. In a regular move, a piece can move forward diagonally to an adjacent square that is empty.
   2. In a capture move, a piece can jump over and capture an opponent’s piece and land on an empty square (landing on a square that is         not empty is not allowed.)The jump must be in the forward diagonal direction and no consecutive jumps are allowed. In addition, every       opportunity to jump must be taken.In the case where there are two or more possible jump moves, the player can choose which one to take.
   3. No vertical, horizontal or backward moves are allowed for both regular and capture moves.
4. If a player has no legal move to take, his/her turn will be forfeited and the other player will make the next move.
5. A player wins when he/she captures all of the other player’s pieces. If both players do not have any legal move to take, the game will end and the player with the most number of pieces left wins; if the two players have the same number of pieces left, the game is a draw.
## How to Setup
### Steps :
1. The Checkers Java Project has two Java classes namely CheckersJavaFXApplication and CheckersNutshell.
2. The main method is in class CheckersJavaFXApplication which can be used to run the Java project.
