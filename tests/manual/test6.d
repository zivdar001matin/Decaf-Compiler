int main() {
    int a;
    int b;
    int i;

    b = 0;
    for(i = 1; true; i = i + 1) {
        a = ReadInteger();
        if (a < 0){
            continue;
        } else {
            if ( a > 100)
                break;
        }
        b = b + a;
    }

    //Print("Sum of ", i, " items is: ", b);
    Print(i);
    Print(b);
}