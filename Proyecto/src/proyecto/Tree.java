/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto;

import java.util.Scanner;

/**
 *
 * @author edas
 */
public class Tree {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Btree btree=new Btree(3);//new btree
        Scanner in=new Scanner(System.in);
        char resp='s';
        while (resp=='s') {
            System.out.print("escriba un numero entero para insertarlo en el arbol b: ");
            int num=in.nextInt();
            btree.insert(num);
            System.out.print("continue(s/n): ");
            resp=in.next().charAt(0);
        }
        
        
        
        
    }
    
}
