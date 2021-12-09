package tp_chess;

import java.util.NoSuchElementException;
import java.util.Scanner;

import tp_chess.CasseTete;

public class Main {
    public static void main(String[] args) {
        CasseTete ct  = new CasseTete();
        boolean gagne = ct.gagne();
        int nb = 0;

        try ( Scanner sc = new Scanner(System.in) ) {
            while(!gagne) {
                System.out.println(ct.toString());
                System.out.println("0123456");
                System.out.println("Donnez un numéro : ");
                nb = sc.nextInt();
                sc.nextLine();
                try {
                    if(nb >= 0 && nb <= 6) {
                        if(ct.destination(nb) != -1) {
                            ct.jouer(nb);
                            System.out.println(ct.toString());
                            System.out.println("0123456");
                        }
                        if(ct.estFini()) {
                            gagne = ct.gagne();
                            System.out.println("GAGNE !!!!!!!!!");
                        }
                    } else {
                        System.out.println("Donnez un numéro compris entre 0 et 6");
                        nb = sc.nextInt();
                        System.out.println(ct.toString());
                        System.out.println("0123456");
                    }
                } catch (NoSuchElementException e) {
                    e.printStackTrace();
                }
                System.out.println(ct.toString());
                System.out.println("0123456");
            }
        }
    }
}
