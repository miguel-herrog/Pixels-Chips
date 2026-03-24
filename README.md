# 🃏 Multiverse Blackjack CLI

A modular, terminal-based Blackjack game built purely in Java. Step into a VIP Casino where you can play classic 1v1 against the Dealer, or sit at themed tables alongside legendary video game characters with their own AI, betting styles, and dynamic dialogues.

## ✨ Features

* **Themed Tables:** Play against iconic characters from different gaming universes.
    * 🧟 *The Spencer Mansion:* Albert Wesker, Chris Redfield, and Jill Valentine.
    * 🧚 *The Glade of Dreams:* Rayman, Globox, and Mr. Dark.
* **Dynamic NPC Interactions:** Characters react in real-time to your wins, losses, and busts using randomized dialogue arrays. They even interact with each other!
* **Advanced OOP Architecture:** Built with clean code principles, utilizing Inheritance, Polymorphism, and Encapsulation. Easily scalable to add new franchises with zero friction.
* **Classic Game Mechanics:** Features standard Blackjack rules, including dynamic betting, card drawing, dealer AI (hits until 17), and the **Double Down** mechanic.
* **ASCII Visuals:** Custom console rendering for cards and suits using ANSI escape codes for a vibrant terminal experience.

## 🏗️ Project Structure

The codebase is organized into logical packages for maximum scalability:
* `core/`: Contains the main game loop, display manager, deck generation, and table management.
* `entities/`: Base abstract classes (`Boss`, `Player`) defining core mechanics.
* `entities.residentevil/` & `entities.rayman/`: Sub-packages containing specific NPC logic, betting behaviors, and tailored dialogue lines.

## 🚀 How to Run

1. Clone this repository to your local machine.
2. Open your terminal and navigate to the `src` folder.
3. Compile the project: `javac core/Main.java`
4. Run the game: `java core.Main`

## 🎮 How to Play

* Choose a game mode from the Main Menu (Themed Tables or Classic Casual).
* Place your bets using your initial balance of 500 chips.
* Choose to **(H)**it, **(S)**tand, or **(D)**ouble Down.
* Watch the NPCs play their turns and react to the unfolding game.
* Survive until you clear the table or go bankrupt!

---
*Developed as a showcase of Object-Oriented Programming and modular design in Java.*