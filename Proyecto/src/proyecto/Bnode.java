/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto;

/**
 *
 * @author edas
 */
public class Bnode {
    
    protected KRRN keys[];//keys 
    protected int t;//minimun range for keys
    protected Bnode childs[];//childs of x
    protected int n;//current number or keys
    protected boolean leaf;//leaf or not

    public Bnode(int t, boolean leaf) {
        this.t = t;
        this.leaf = leaf;
        keys=new KRRN[2*t-1];
        childs=new Bnode[2*t];
        n=0;
        
    }

    
    
    
    
    
}
