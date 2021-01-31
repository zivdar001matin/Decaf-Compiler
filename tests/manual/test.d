int main() {
    int i;
    i = 0;
    while (i < 10) {
        if (i == 4) {
            i = i + 1;
            continue;
        }
        Print(i);
        i = i + 1;
    }
}
