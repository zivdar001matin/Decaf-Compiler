import java_cup.runtime.*;
%%

%public
%class Scanner
%standalone
%unicode
%type Symbol
%cup

%{
    StringBuffer string = new StringBuffer();
  public Symbol token(int tokenType){
      System.out.println(yytext());
      return new Symbol(tokenType, yytext());
  }
%}


//These lines are from JFlex Doc Page 12
    LineTerminator = \r|\n|\r\n
    InputCharacter = [^\r\n]
    WhiteSpace = {LineTerminator} | [ \t\f]

    /* comments */
    Comment = {TraditionalComment} | {EndOfLineComment} | {DocumentationComment}
    TraditionalComment = "/*" [^*] ~"*/" | "/*" "*"+ "/"
    // Comment can be the last line of the file, without line terminator.
    EndOfLineComment = "//" {InputCharacter}* {LineTerminator}?
    DocumentationComment = "/**" {CommentContent} "*"+ "/"
    CommentContent = ( [^*] | \*+ [^/*] )*

    Identifier = [:jletter:] [:jletterdigit:]*

    DecIntegerLiteral = [0-9]+
// Binam group code
    Types = "void" | "int" | "double" | "bool" | "string"
    Boolean = "true" | "false"
    OtherReserved = "class"| "interface"| "null"| "this"| "extends"| "implements"| "for"| "while"| "if"| "else"| "return"| "break"| "continue"| "new"| "NewArray"| "Print"| "ReadInteger"| "ReadLine"| "dtoi"| "itod"| "btoi"| "itob"| "private"| "protected"| "public"
    Sign = "+"|"-"
    Punctuations = {Sign}|"*"|"/"|"%"|"<"|"<="|">"|">="|"="|"=="|"!="|"&&"|"||"|"!"|";"|","|"."|"["|"]"|"("|")"|"{"|"}"

    HexIntegerLiteral = 0(x|X)[a-fA-F0-9]+
//    IntegerLiteral = {Sign}?{DecIntegerLiteral} | {HexIntegerLiteral}
    IntegerLiteral = {DecIntegerLiteral} | {HexIntegerLiteral}

    Numpart = [0-9]+(\.|\.[0-9])[0-9]*
    Exp = (e|E){Sign}?[0-9][0-9]*
    DoubleLiteral = {Sign}?{Numpart}{Exp}?


%state STRING


%%

<YYINITIAL> {

    //TODO return symbol insted of sout

    /* reserved */
    {Types}               {System.out.println(yytext());}
    {Boolean}             {System.out.println("T_BOOLEANLITERAL "+yytext());}
    {OtherReserved}       {System.out.println(yytext());}

    /* identifiers */
    {Identifier}          { System.out.println("T_ID "+yytext());}
    /* literals */
    {IntegerLiteral}      { System.out.println("T_INTLITERAL "+yytext());}
    {DoubleLiteral}       { System.out.println("T_DOUBLELITERAL "+yytext());}
    \"                    { string.setLength(0); yybegin(STRING); }
    /* operators */
    "="                   { System.out.println(yytext());}
    "=="                  { System.out.println(yytext());}
    "+"                   { System.out.println(yytext());}
    /* comments */
    {Comment}             { /* ignore */ }
    /* whitespace */
    {WhiteSpace}          { /* ignore */ }
    /* punctuations */
    {Punctuations}        { System.out.println(yytext());}


}

<STRING> {
    \"                    { yybegin(YYINITIAL);
                            System.out.println("T_STRINGLITERAL \""+string.toString()+"\"");
                            string = new StringBuffer();}
    [^\n\r\"\\]+          { string.append( yytext() ); }
    \\t                   { string.append("\t"); }
    \\n                   { string.append("\n"); }
    \\r                   { string.append("\r"); }
    \\\"                  { string.append("\""); }
    \\                    { string.append("\\"); }
}
