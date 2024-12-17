package ders5.ev;

public class MethodsHome {
    public static void main(String[] args) {
        MethodsHome methodsHome=new MethodsHome();
        methodsHome.printsNumber(3,6);
        methodsHome.printsNumber(1,9);
        methodsHome.printsNumber(2,7);
    }

    void printsNumber(int begin, int end){
        for (int i = begin; i <= end; i++) {
            System.out.print(i+" ");
        }
        System.out.println();
    }
}
