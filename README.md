# My Personal Project

## Quoridor 

This application is an implementation similar to the board game - ***Quoridor***. Anyone can use it to play with a bot or multiplayer. This project is interesting to me because I'm enthusiastic about strategy game. There are a lot of modifications about the rules. 

- As one of the players, I want to add a block into my list of blocks in the game state.
- As a user, I want to view the current game board to see the blocks that two players has placed .
- As a user, I want to move my piece.
- As a user, I want to randomly displace a block
- As a user, I want to be able to save my game to file (if I so choose)
- As a user, I want to be able to be able to load my game from file (if I so choose)

# Instructions for Grader
- When the application is open, you are asked by a pop-up window with two buttons "load game" and "new game", you can choose to load game by clicking "load game".
- In the game windows, you can save the game and quit by clicking the button "save and quit" near the bottom of the window
- In the game windows, you can quit without saving by clicking the button "quit" near the bottom of the window
- While in turn, you can add a box into the game by double-clicking an empty square
- While in turn, you can randomly displace (there is a chance it would stay at the same place) a box by double-clicking a square with a block
- While in turn, you can move a player by clicking the box where the player in turn is in and then click another adjacent box that is not blocked.
- You can always see the game board while the game window is open
- When the game ends, there will be an image of a cup pop up.
# Phase 4: Task 2
Sat Apr 06 11:33:04 PDT 2024
Added block at (3, 3)
Sat Apr 06 11:33:05 PDT 2024
Added block at (3, 4)
Sat Apr 06 11:33:05 PDT 2024
Player 2 move to (4, 7)
Sat Apr 06 11:33:07 PDT 2024
Player 1 move to (5, 0)
Sat Apr 06 11:33:07 PDT 2024
Block at (3, 3) is moved to (2, 3)
Sat Apr 06 11:33:08 PDT 2024
Block at (3, 4) is moved to (3, 5)
Sat Apr 06 11:33:09 PDT 2024
Block at (2, 3) is moved to (2, 4)
Sat Apr 06 11:33:10 PDT 2024
Added block at (3, 4)
Sat Apr 06 11:33:11 PDT 2024
Added block at (3, 1)
Sat Apr 06 11:33:12 PDT 2024
Added block at (4, 2)
Sat Apr 06 11:33:14 PDT 2024
Player 2 move to (4, 6)
Sat Apr 06 11:33:15 PDT 2024
Player 1 move to (5, 1)
Sat Apr 06 11:33:17 PDT 2024
Player 2 move to (5, 6)
Sat Apr 06 11:33:17 PDT 2024
Player 1 move to (5, 2)
# Phase 4: Task 3
- Merge GamePanel and TurnPanel to reduce coupling and increase cohesion.
- I may also merge all panels together to reduce coupling, but it would decrease cohesion.
- It may be better to make SavePanel responsible for saving the game, but then it has to store an instance of GameState which increase coupling but increase cohesion.