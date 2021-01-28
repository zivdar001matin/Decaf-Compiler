int sum(int a, int b){
    return a + b;
}

int main() {

    int i;

    for(i = 0; true; i = sum(i, 1)){
        Print(i);
    }

}
