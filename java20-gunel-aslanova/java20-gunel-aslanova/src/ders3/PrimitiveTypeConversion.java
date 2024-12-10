package ders3;

public class PrimitiveTypeConversion {
    public static void main(String[] args) {
        int variable1=55;
        byte variable2= (byte) variable1;
        System.out.println("varibale2:"+ variable2);

        short variable3=636;
        byte variable4=(byte) variable3;
        System.out.println("varibale4:"+ variable4);

        long variable5= 458;
        short variable6= (short) variable5;
        System.out.println("varibale6:"+ variable6);

        long variable7= 92523635483L;
        int variable8=(int)92523635483L;
        System.out.println("varibale8:"+ variable8);

        double variable9=65787.3;
        int variable10= (int) variable9;
        System.out.println("varibale10:"+ variable10);

        char variable11='q';
        int variable12=(int) variable11;
        System.out.println("varibale12:"+ variable12);

        int variable13=266;
        char variable14=(char) variable13;
        System.out.println("varibale14:"+ variable14);

    }
}
