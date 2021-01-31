interface C {
    int x();
}
class A implements C {
   int x() {
        return 2;
    }
}
int main(){
    C c;
	A a;
	a = new A;
	c = a;
	Print(c.x());
}
