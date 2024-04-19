# Pacman Game
Welcome to the Pacman game application! This game implements a classic arcade-style Pacman experience with additional features and graphical enhancements.

## Features
Gameplay Mechanics:
Navigate Pacman through a maze while avoiding ghosts and collecting pellets.
Every 5 seconds, enemies have a 25% chance to create upgrades that the player can collect.
Implement at least 5 different significant complex upgrades for the player.
## Main Menu:
The main menu offers the following options:
New Game: Start a new game.
## High Scores: 
View the high scores.
### Exit: 
Close the application.
## New Game:
Upon selecting "New Game," the player can choose the size of the game board, ranging from 10 to 100 rows/columns.
The game board is displayed in a new window, implemented using the JTabel component with a custom JTable model based on AbstractTableModel.
## Graphical Interface:
A fully functional graphical interface is provided, featuring elements such as score counter, time counter, life counter, and more.
Graphic files are used to create a cohesive look for the entire application, including the menu, high scores window, and game window.
Game characters move and perform tasks through simple animations, enhancing the visual experience.
## Time Handling:
Time-related tasks are implemented using the Thread class, ensuring proper synchronization of all threads.
Different functionalities are separated into individual threads for efficient handling.
## Gameplay Controls:
The game is played using keyboard controls to navigate Pacman through the maze.
The game can be interrupted at any time using the compound keyboard shortcut Ctrl+Shift+Q, returning the player to the main menu.
## High Scores:
After completing a game, the player is prompted to enter their name to be saved in the high scores.
High scores persistence is achieved using the Serializable interface to save and load rankings.
## MVC Architecture:
The application is implemented using the MVC programming pattern, with complete event handling delegated to the model.
#### Implementation Notes
Exception handling is implemented to display error messages to the user in case of any issues.
The high scores list is displayed using the JList component with scrollbars for better user experience.
Application windows are designed to be scalable to accommodate various screen sizes.
Dialogs are used where appropriate instead of JFrame for certain windows.
Enjoy playing Pacman and aiming for the high score!
