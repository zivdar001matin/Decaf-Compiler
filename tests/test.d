int main() {
    int b;
    string c;
    string cc;
    string d;

    b = 0;
    c = "c";
    cc = "c";
    d = "d";

    Print("a" != "a");
    Print(b == "a");    // Semantic Error!
    Print(c == "c");
    Print(c == "d");
    Print(c != "c");
    Print(c != "d");
    Print(c != cc);
    Print(c == d);

}
