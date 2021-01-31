
int factor(int a){
    if (a <= 1){
        return 1;
    }
    return factor(a - 1) * a;
}
int main() {
    int b;
    b = factor(3);
    Print(b);
}
