# Decaf Compiler
Project for Compiler course at SBU

## A short description of the Decaf
Decaf is a simple small object oriented language similar to C/C++/Java languages, so it isn't hard to understand. 

This program has been simplified for easier implementation, you can see project description in ProjcetDcsp pdf file and learn more about decaf in pdf file.

## Technologies
```
- JFlex : lexer/scanner generator
- CUP :  LALR parser generator
```

## Compile JFlex
If you have installed JFlex on your OS:
```commandline
jflex ./src/scanner/Scanner.flex
```
or you can use jar file in the project
```commandline
java -jar ./src/scanner/jflex-1.8.2.jar ./src/scanner/Scanner.flex
```

## Compile CUP
```commandline
cd src/parser
java -jar java-cup-11b.jar -package parser parser.cup
```

# References
https://github.com/mhezarei/compiler