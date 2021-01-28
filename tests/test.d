void print(int a){
    Print(a + 2);
    return ;
}

void printAgain(int a){
    print(a + 3);
}

int main(){
    int a;

    a = 5;

    printAgain(a + 1);
}

