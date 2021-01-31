int sum(int a, int b){
    return a + b;
}

int main() {
    int i;

    i = 0;

    while(true){
        i = sum(i, sum(4, 6));
        Print(i);
    }

}
