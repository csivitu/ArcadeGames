package main

import (
	"bufio"
	"flag"
	"fmt"
	"log"
	"math/rand"
	"os"
	"strings"
	"time"
)

var words = []string{"compiler", "linux", "torrent", "librewolf", "brave", "software", "open source", "internet", "web development", "backend"}

const maxGuesses = 7

type Hangman struct {
	currentWordState, guesses []string
	maxGuesses, numOfTries    int
	word                      string
	verbose                   bool
}

func getWord() string {
	return words[rand.Intn(len(words))]
}

func (h *Hangman) drawBoard() {
	fmt.Printf("Guesses left: %v \n", h.maxGuesses-h.numOfTries)
	fmt.Printf("Guesses: %v \n", h.guesses)
	fmt.Println(h.currentWordState)
}

func (h *Hangman) getGuess() string {
	reader := bufio.NewReader(os.Stdin)
	guess, err := reader.ReadString('\n')
	if err != nil {
		log.Fatal(err)
	}
	guess = strings.TrimSpace(guess)
	if guess == "" {
		fmt.Println("please enter a letter")
		return guess
	}
	if strings.Contains(strings.Join(h.guesses, ""), guess) {
		fmt.Println("You've already guesses that letter. Please select another one.")
		return ""
	}
	if h.verbose {
		fmt.Printf("This guess %v \n", guess)
	}
	h.numOfTries++
	h.guesses = append(h.guesses, guess)
	return guess
}

func (h *Hangman) isMatch(guess string) bool {
	if strings.Contains(h.word, guess) {
		if h.verbose {
			fmt.Printf("%v is a match for %v word \n", guess, h.word)
		}
		return true
	}
	if h.verbose {
		fmt.Printf("%v is NOT a match for %v word \n", guess, h.word)
	}
	return false
}

func (h *Hangman) updateWordState(letter string) {
	// initialize state
	if letter == " " {
		for i := 0; i < len(h.word); i++ {
			h.currentWordState = append(h.currentWordState, "_")
		}
	} else {
		for i, l := range h.word {
			if letter == string(l) {
				h.currentWordState[i] = letter
			}
		}
	}
}

func (h *Hangman) continueGame() bool {
	if len(h.guesses) == h.maxGuesses {
		fmt.Println("you've finished your hangman game, losing.")
		return false
	}
	if strings.Join(h.currentWordState, "") == h.word {
		fmt.Println("you've finished your hangman game, and you've won, congrats!")
		return false
	}
	return true
}

func main() {
	rand.Seed(time.Now().UTC().UnixNano())

	verbose := flag.Bool("v", false, "verbose mode for debugging purposes")
	flag.Parse()

	fmt.Printf(
		`Welcome to Nerdy Hangman!
    I will choose a random word related to computer science and programming, and you will guess letters you think the word contains.
    You have %v guesses`, maxGuesses)

	game := Hangman{
		word:       getWord(),
		maxGuesses: maxGuesses,
		verbose:    *verbose,
	}
	game.updateWordState(" ")
	if game.verbose {
		fmt.Printf("Word: %v \n", game.word)
	}

	for game.continueGame() {
		game.drawBoard()
		guess := game.getGuess()
		if game.isMatch(guess) {
			game.updateWordState(guess)
		}
	}
}