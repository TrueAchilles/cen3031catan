# Introduction #

JavaDocs is a documentation standard for .java files. Essentially, the javadoc program
(included in every SDK) parses java files for special comment syntax and creates HTML documentation from that.


# Details #

```
/**
 * Validates a chess move. Use {@link #doMove(int, int, int, int)} to move a piece.
 *
 * @param theFromFile file from which a piece is being moved
 * @param theFromRank rank from which a piece is being moved
 * @param theToFile   file to which a piece is being moved
 * @param theToRank   rank to which a piece is being moved
 * @return            true if the chess move is valid, otherwise false
 */
boolean isValidMove(int theFromFile, int theFromRank, int theToFile, int theToRank)
{
...
}
```

This demonstates the basic syntax of a comment and method. The example covers the most important JavaDoc items that we will need.

All comment blocks are formatted like the following:
```
/**
 * Simple description.
 */
```

Note the indentation of the stars. The description provides a summary of what the method actually does. DO NOT JUST REPEAT what can be learned from
the name of a method in the description. If commenting for a method called generateDialogBox(), do not type "Generates a dialog box."
as a comment. Provide more information, such as "Create a dialog box that appears whenever Player X rolls the dice."

When writing a description, the first sentence should be a summary of the method. If additional paragraphs are needed in the description,
separate the paragraphs with "&lt;p&gt;" (not including the quotes). The first sentence ends at a period, so if you need a period in the sentence but it isn't the end of the sentence you need to escape it. See http://java.sun.com/j2se/javadoc/writingdoccomments/#descriptions on the ways to do that.

After the description come the tags. The ones we will be using extensively are @param, @return, and @throws.

@param indicates a parameter of the method. @param tags should be written in the format "@param variableName variableDescription" and
are ordered based on their order in the method signature - for example, the method setMessage(Object o, double num) would list @param o and then
@param num. @param is a required tag.

@return details what is returned by the method. This tag is required in all but two cases: for constructors and for methods that return void,
the @return tag is not needed.

@throws is used to declare any exceptions that might be thrown by the method. It must report checked exceptions (ie, the ones thrown by a method), but it can also list RuntimeExceptions if it is something that might want to be catched. They are listed alphabetically by exception class names.

Note that there is a host of information on JavaDocs comments at http://java.sun.com/j2se/javadoc/writingdoccomments/. Please look over this, as it provides in-depth analysis on writing JavaDoc comments for methods and classes.