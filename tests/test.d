int mult(int a, int b) {
    return a * b;
}

int div(int b, int a) {
    return a / b;
}

int add(int a, int b) {
    return a + b;
}

int main() {
    int a;
    int b;
    int c;

    a = 2;
    b = 3;

    c = add(a, b);
    Print(c);

    c = mult(a, b) + div(a, mult(a, b));
    Print(c);

    Print(mult(a, b) + div(a, mult(a, b)) + 2);
}
