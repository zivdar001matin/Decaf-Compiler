# Decaf Compiler
Project for Compiler course at SBU

## A short description of the Decaf
Decaf is a simple small object-oriented language similar to C/C++/Java languages, so it isn't hard to understand. 

This program has been simplified for easier implementation, you can see project description in ProjcetDcsp pdf file and learn more about decaf in pdf file.

## Technologies

- JFlex :   lexer/scanner generator
- CUP   :   LALR parser generator
- SPIM  :   simulator for the MIPS R2000/R3000 RISC computers

## Requirements
```
sudo apt-get install -y spim
```

## Walkthrough
First add `java-cup-runtime-11b-20160615.jar` to project.

Project Structure -> Modules -> Compile -> Dependencies -> Add...

### Compile JFlex
If you have installed JFlex on your OS:
```commandline
jflex src/scanner/Scanner.flex
```
or you can use jar file in the project
```commandline
cd src/scanner/
java -jar ../../libraries/jflex-1.8.2.jar Scanner.flex
```
then add Scanner.java to scanner package by writing 
 ``package scanner;``
 on the first line of `Scanner.java` file.

### Compile CUP
```commandline
cd src/parser/
java -jar ../../libraries/java-cup-11b.jar parser.cup
```

### Run tests
#### manually
Change `tests/manual/test.d` file to whatever decaf code you want then run `src/Compiler.main`.
### automatically
```commandline
cd tests/
./run.sh
```

# References
https://github.com/mhezarei/compiler