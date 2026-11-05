# Tic-Tac-Toe: code and execution guide

## What is included

This project contains two independent versions of the same two-player game:

- `TicTacToe.java` is the text-based version. Players enter row and column numbers in a terminal.
- `TicTacToeGraphique.java` is the desktop version. It uses Java Swing buttons and a status bar.

Both classes are in Java's default package and have their own `main` method. They can be compiled together, but run one entry point at a time. The versions keep separate game state and do not depend on one another.

## Requirements

- A Java Development Kit (JDK) installed. JDK 8 or later is sufficient; JDK 21 is also supported.
- A terminal, or Visual Studio Code with the Java extensions for the launch configurations.
- No external libraries or build tool are required.

Check that Java is available from a terminal:

```powershell
java -version
javac -version
```

## Compile and run from PowerShell

Open PowerShell in the folder containing the two `.java` files.

Compile both versions:

```powershell
javac TicTacToe.java TicTacToeGraphique.java
```

This creates `TicTacToe.class` and `TicTacToeGraphique.class` in the same folder.

Run the console version:

```powershell
java TicTacToe
```

Run the graphical version:

```powershell
java TicTacToeGraphique
```

The two `java` commands are alternatives: choose the version to play. If the graphical window does not appear, use a desktop environment rather than a headless terminal session.

## Run from Visual Studio Code

Open this project folder in Visual Studio Code, then open **Run and Debug**. Choose either **TicTacToe** for the terminal game or **TicTacToeGraphique** for the Swing window and start debugging. The launch settings are in `.vscode/launch.json`.

The obsolete `App` launch entry was removed because this project does not contain that class.

The workspace Java settings in `.vscode/settings.json` select the installed Eclipse Temurin JDK 21 for both the Java language server and this project's run configuration. If that JDK is installed in a different location on your computer, update both `path` values to its JDK root folder (the folder containing `bin` and `lib`), then reload the Java project in VS Code.

## How the game logic works

### Console version

`TicTacToe` stores the board in a 3-by-3 `char` array. A blank cell contains a space; each move writes the current player's `X` or `O` into one cell.

1. `initialiserPlateau()` clears the board.
2. `afficherPlateau()` prints the numbered board.
3. The main game loop prompts for a row and a column, checks that each is an integer from 0 to 2, and rejects occupied cells.
4. A valid move is recorded, then `checkWin()` checks all rows, columns, and both diagonals.
5. If there is no winner, `isBoardFull()` checks for a draw. Otherwise the current player changes from `X` to `O` or vice versa.
6. End-of-input (for example, Ctrl+Z followed by Enter on Windows) ends the game cleanly.

Coordinates are zero-based: the top-left cell is row `0`, column `0`; the bottom-right cell is row `2`, column `2`.

### Graphical version

`TicTacToeGraphique` extends `JFrame` to create the window and implements `ActionListener` to receive button clicks. A 3-by-3 `JButton` array represents the cells. The current player is displayed in a clicked cell, with blue for `X` and red for `O`.

The same game rules are applied after every move: `checkWin()` checks rows, columns, and diagonals; `isBoardFull()` detects a draw. At the end of the game, the board is disabled and a dialog offers a new game. Choosing **Yes** clears the cells and starts with `X`; choosing **No** closes the application.

The `main` method uses `SwingUtilities.invokeLater(...)` so Swing components are created on the Event Dispatch Thread, which is the thread Swing uses for UI updates.

## Troubleshooting

- **`javac` or `java` is not recognized:** install a JDK and add its `bin` folder to `PATH`, or open a new terminal after configuring the JDK.
- **`Unable to load ...\\lib\\modules`:** the selected JDK installation is missing or has a damaged runtime image. Select a working JDK in VS Code and make sure the configured path is the JDK root, not its `bin` folder. This workspace is configured to use Eclipse Temurin JDK 21 at `C:\Program Files\Eclipse Adoptium\jdk-21.0.12.101-hotspot`.
- **`Could not find or load main class`:** run the command from the folder containing the compiled `.class` file, and spell the class name exactly as shown (without `.java` or `.class`).
- **The console game appears stuck:** it is waiting for the current player's row and column. Enter two numbers from `0` to `2`.
- **A cell is rejected:** it is either outside the 3-by-3 board or already occupied; choose an empty cell.
- **The GUI does not start in a server/container:** Swing requires a graphical desktop session.
