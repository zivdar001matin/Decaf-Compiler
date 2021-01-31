int main() {

    int a;

    a = 2;

    if (false) {
        a = 3;
    } else {
        a = 4;
        if(true){
            a = 5;
        } else {
            a = 6;
        }
    }

    Print(a);
}
