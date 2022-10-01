

## Hangman 

Welcome to Hangman! This is a single-player game built using Java in which the player attempts to guess a word one letter at a time.

### Table of Contents
1. [Introduction](#Introduction)
2. [Game Rules](#Game-Rules)
3. [Requirements](#Requirements)
4. [Installation](#Installation)
5. [Features](#Features)
6. [Contribution](#Contribution)
7. [Credits](#Credits)
8. [License](#License)

### Introduction

Hangman is a word-guessing game played by two or more players. One player is tasked with choosing a word. The word is represented as a series of "dashes" indicating the total number of letters. Players take turns to guess letters to spell out the entire word. For each incorrect letter, a part of the "hangman" is drawn.

### Game Rules

In this version of the game, there is one Player and the word is chosen by the Computer. The Computer prompts the Player to enter a letter each turn. The letter is then checked with the chosen word. If the letter is not present, the next body part of the "hangman" is drawn. 

The following body parts of the "hangman" are drawn:
* Head
* Body
* Right arm
* Left arm
* Right leg
* Left leg
* Right hand
* Left hand
* Right foot
* Left foot

**Win Condition:** The Player must guess all the letters of the Computer chosen word.
**Lose Condition:** All body parts of the "hangman" are drawn before the word is guessed.


### Requirments

This game requires the User to have Java installed on their system.

### Installation
1. Go to the repository to access csivitu/ArcadeGames
2. Clone the repository onto your local machine
3. Open the file using Java Compiler and enjoy!
```sh
git clone https://github.com/csivitu/ArcadeGames.git
```

### Features

This game of Hangman includes the following features:

* An interactive window in which each player turn and outcome is displayed.
* The computer chooses from 50 different words ranging in difficulty.
* The body parts of the hangman are displayed as the game progresses.
* The letters already used by the player are displayed.

### Contribution

This project is **Open Source** and contributions to this project are always appreciated!
To contribute to this project, follow these steps:
1. Fork the project repository on GitHub.
2. Clone the forked repository to your local machine.
3. Create a new feature branch.
4. Do the necessary changes or updation.
5. Add the changes to the staging area.
6. Commit the changes with a proper message.
7. Push to your forked repository.
8. Create a Pull Request.

If your Pull Request is approved, your branch will be merged to the project and you will be added to the list of Project Contributors!

### Credits

This game was developed by the members of **CSI-VIT**. Contributions were made by the participants of ForkThis, an event organised by CSI-VIT for GraVitas 2022. :sparkles:


### License

Distributed under the **MIT License**.
