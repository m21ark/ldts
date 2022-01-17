# LDTS_G0900 - Run Bird Run!

Run Bird Run is a platformer game and a mock version of the mobile phone game with the same
name (https://play.google.com/store/apps/details?id=com.ketchapp.runbirdrun&hl=en_US&gl=US). The player controls a lazy
bird that can't fly much without getting tired. The main objective is to stay alive for as long as you can, while
collecting the occasional falling coins. You must avoid the neverending boxes that fall from the sky, otherwise you
lose!

> This project was developed by Marco André (up202004891@fe.up.pt), João Alves (up202007614@fe.up.pt) and José Sousa (up202006141@fe.up.pt) for LDTS 2021/2022.

## Game Demo

<p align="center" justify="center">
  <img src="gifs/RunBirdRun.gif" width=60% height=90%/>
</p>

<br>
<br />

## Implemented Features

- **Start Screen** - When the game starts a simple initial screen is presented
- **End Screen** - When the player dies, a screen is shown with their score
- **Instructions Screen** - Instruction screens explaining the game and the key inputs
- **Movement** - The game character can move left, right and fly using the Arrow Keys
- **Gravity** - All in-game entities are affected by gravity, thus they fall a small amount per second
- **Catch Coins** - When the bird touches a coin or vice-versa, the player wins a point
- **Take Damage** - When a bird is hit on the head by a block, it takes damage, loosing one of its 3 initial lives
- **Full Bottom Row** - Like in tetriz, when the last row is fully occupied by blocks, it vanishes, preventing the
  window from filling up
- **Collisions** - Right now, the bird can move through blocks and coins laterally without a problem. Therefore, more
  collision detection needs to be implemented
- **Bg Music & Sound Effects** - The game has background music and sound effects when player catches something, dies,
  takes damage...
- **Pausing Game** - Pressing P mid-game pauses the game when needed
- **Play again Option** - In the end screen, the player has the option to play again
- **Bird Stamina** - The player controlled bird now changes color to reflect its stamina. Without stamina, the bird
  can't fly
- **Extra Lives** - Randomly throughout the game, collectable lives drop from the sky. If the player gets them, it
  gains a hp point back.

## Gameplay images

<p align="center" justify="center">
  <img src="images/screenshots/initMenu.png" width=60% height=90%/>
</p>
<p align="center">
  <b><i>Fig 1. Starting Menu </i></b>
</p>  

<br>
<br />

<p align="center" justify="center">
  <img src="images/screenshots/game.png" width=60% height=90%/>
</p>
<p align="center">
  <b><i>Fig 2. Game </i></b>  
</p>  

<br>
<br />

<p align="center" justify="center">
  <img src="images/screenshots/endMenu.png" width=60% height=90%/>
</p>
<p align="center">
  <b><i>Fig 3. Game Over  </i></b>
</p>  


<p align="center" justify="center">
  <img src="images/screenshots/pause.png" width=60% height=90%/>
</p>
<p align="center">
  <b><i>Fig 4. Pause  </i></b>
</p>  

<p align="center" justify="center">
  <img src="images/screenshots/instuctions.png" width=60% height=90%/>
</p>
<p align="center">
  <b><i>Fig 5. Instructions  </i></b>
</p>  

## Planned Features

- **Shop** - With the coins collected in-game, the user can then buy different power-ups, to help him better perform at
  the game.
- **Power-Ups** - These would be special powers, that the user does not have initially. They could vary from "Box
  Destruction" to "Super birdrun.model.Coin Addition".
- **Hard Level Meter** - Players could have an option to choose if they want a harder level (with more and faster blocks
  for example)

## Design pattens

### Factory method

> **Problem in Context**
>
>  In the arena Class, we need to be able to create a matrix with specific contents each game loop.
>
>  **The Pattern**
>
>  We have applied the **Factory Method** pattern. This pattern allows us to get the exact matrix we need each game loop.
>
>  **Implementation**
>
>  Creation of birdrun.controller.MatrixFactory() class with getMatrix() method to gives us the desired matrix.
>
>  **Consequences**
>
> We can always have a matrix that fits our needs.

### Game Loop

> **Problem in Context**
>
>  Each second we need the game to take user input, update all Elements positions and draw them all.
>
>  **The Pattern**
>
>  We have applied the **Game Loop** pattern. This pattern allows us to have a loop that calls the right methods to do the above mencioned action.
>
>  **Implementation**
>
>  Added a while(gameRun) in the game class.
>
>  **Consequences**
>
> We achieve a game that updates whenever we want.

### MVC - Model View Controller (Architectural Pattern)

> **Problem in Context**
>
>  With the increasing code size, classes should be organized to facilitate their communication and usage.
>
>  **The Pattern**
>
>  Classes should be divided into 3 types : Model (Simply exists), Controller (manages the interactions) and Viewer (draws the model)
>
>  **Implementation**
>
>  We are still developing this design pattern but some classes already have this structure complete (Arena)
>
>  **Consequences**
>
> This code structure is more versatile and every class is organized based on the actions they perform.

## UML

<p align="center" justify="center">
  <img src="images/UML/UML.png"/>
</p>
<p align="center">
  <b><i>Fig 6. UML </i></b>  
</p>  

<br>
<br />

This is a simplified version of the project's diagram.

See Java Classes here - [Java Classes](src/main/java/birdrun)

## Code Smells

## Testing

[![BCH compliance](https://bettercodehub.com/edge/badge/FEUP-LDTS-2021/ldts-project-assignment-G0900?branch=main&token=c37e0a511313f827d4117162ff4c614ba7c4366d)](https://bettercodehub.com/)

</br>
<p align="center" justify="center">
  <img src="images/test/coverage.png"/>
</p>
<p align="center">
  <b><i>Fig 7. Coverage Report </i></b>  
</p>  

<br>



<p align="center" justify="center">
  <img src="images/test/pittest.png"/>
</p>
<p align="center">
  <b><i>Fig 8. Pitest Report </i></b>  
</p>  

</br>

See full coverage report here - [Coverage Report ](coverage_report/index.html)

See full mutation report here - [Pit Test Report ](pitest_report/index.html)

## SELF-EVALUATION

- Marco André 40%
- João Alves 30%
- José Sousa 30%
