
int abs_mult(int a, int b) {
    int c;
    if (a > b)
        c = a - b;
    else
        c = b - a;
    return c * a * b;
}

int main() {
    int a;
    int b;

    a = 3;
    b = 6;

    Print(abs_mult(a, b));
    Print(-2*3);
}
