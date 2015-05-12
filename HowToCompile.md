Instructions to compile the game:

1. Download and install Cygwin from http://www.cygwin.com/

` http://www.cygwin.com/setup.exe `


2. While in Cygwin, cd to your current svn cen3031catan folder. For example, mine is:

` cd /cygdrive/c/Users/patch/Desktop/cen3031catan/ `

Take note that in cygwin your C:\ drive is located in /cygdrive/c/
Also, do not cd in to the Settlers folder.


3. type in ./Makefile

` ./Makefile `

You will get three warnings, but it still should compile.
All the Makefile does is run

` javac Settlers/*/*/*.java Settlers/*/*.java Settlers/*.java `

So if you get an error such as javac command cannot be found, then place your JDK/bin folder
in to your Windows PATH env variable. ([They taught you this in cis3020/3022](http://www.cise.ufl.edu/~dts/class/2006/fal/software-java.html)).


4. type in java settlers.Settlers

` java settlers.Settlers `

and hopefully the game will execute.

That concludes my instructions on how to compile the game.