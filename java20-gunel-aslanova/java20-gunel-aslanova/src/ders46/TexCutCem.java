package ders46;

import java.util.Scanner;

public class TexCutCem {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
        
        int N = scanner.nextInt();

        int tekCem = 0;
        int cutCem = 0;

        for (int i = 1; i <= N; i++) {
            if (i % 2 == 0) {
                cutCem += i;
            } else {
                tekCem += i;
            }
			System.out.println("Tək ədədlərin cəmi: " + tekCem);
			System.out.println("Cüt ədədlərin cəmi: " + cutCem);
		}
	}
	}

