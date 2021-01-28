
int main() {
    bool a;
    int b;

    b = 1;
    a = funct(b);

    Print(a);
}
bool funct(int a) {
    if ( a > 0 ){
        return true;
    } else {
        return false;
    }
}
