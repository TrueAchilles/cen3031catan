# Introduction #

This page is to describe bugs that I have found in my code for the Player Info Panel.


# Details #

| **Bug Number** | **Bug Description** | **Severity (10 is high)** | **Solution known?** | **Fixed?** |
|:---------------|:--------------------|:--------------------------|:--------------------|:-----------|
| 1 | Development Card will not disappear from main GUI board if you click the button to purchase a development card button while the current development card is still on the screen. | 9 | No | No |
| 2 | When development card deck is empty it throws an exception rather then disallowing the user to purchase a new development card | 5 | Yes | No|
| 3 | Development Card panel does not scroll as the window is resizable, thusly if you have more than 4 development cards in your hand, it is difficult to see the 5th and so on. | 5 | Yes | **_Yes_** |
| 4 | Resource card counts to not accurately reflect the amount of resource cards held by each player. | 10 | Yes | **_Yes_** |
| 5 | Resource card counts and resource counts do not update when the dice are rolled. | 10 | Yes | **_Yes_** |
| 6 | Initial titled border of development card panel for player 1 is messed up.  It does not properly outline then entire box.  However, this is fixed on the next turn and does not seem to reappear. | 2 | No | **_Yes_** |
| ~~7~~ | ~~GridLayout for Development cards is static and needs to be set dependent upon the amount of cards held by each player.  However the look and feel should be the same player to player.~~ | ~~6~~ | ~~No~~ | ~~No~~ |
| 8 | Current player titled border for current individual resources is does not accurately reflect the players color | 3 | Yes | **_Yes_** |
| 9 | There is a concurrent modification exception thrown when the player information panel starts up.  ~~May not be because of the player information panel~~ | 5 | Yes | **_Yes_** |
| ~~10~~ | ~~Location of Player Information Panel does is set to initialize over the game board. While not a major problem it is very annoying to have to move it all the time~~ | 4 | Yes | **_Yes_** |
| ~~11~~ | ~~If the Player Information Panel is closed, while it appears to hide on exit, there is not current method to get the panel back.~~ | 8 | Yes | **_Yes_** |
| 12 | The Development card panel does not scroll if the panel is not re-sizable.  If the panel is re-sizable then the scroll bar does not show up either. | 9 | Maybe | **_Yes_** |
| ~~13~~ | ~~Player Info Panel is not accessible via the GUI Container Class.~~ | 7 | Yes | **_Yes_** |
| ~~14~~ | ~~Say what?.~~ | 7 | Yes | **_Yes_** |