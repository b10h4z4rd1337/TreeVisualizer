package org.b10h4z4rd.test;

import org.b10h4z4rd.TreeVisualizer;
import org.b10h4z4rd.interfaces.IElement;
import org.b10h4z4rd.interfaces.ITree;

public class Baum implements ITree
{
    public static Abschluss ABSCHLUSS = new Abschluss();
    private Baumelement wurzel = new Abschluss();
    
    public void einfuegen(Datenelement elem) {
        wurzel = wurzel.einfuegen(elem, 1);
        TreeVisualizer.getVisualizer().treeChanged();
    }
    
    public boolean istVorhanden(Datenelement elem) {
        return wurzel.istVorhanden(elem);
    }
    
    public void entfernen(Datenelement elem) {
        wurzel = wurzel.entfernen(elem);
    }
    
    public boolean istAusgeglichen() {
        return Math.abs(wurzel.istAusgeglichen()) < 1 ? true : false;
    }
    
    public void inOrder() {
        wurzel.inOrder();
    }
    
    public void preOrder() {
        wurzel.preOrder();
    }
    
    public void postOrder() {
        wurzel.postOrder();
    }
    
    public int tiefeBestimmen(Datenelement d) {
        return wurzel.tiefeBestimmen(d, 0);
    }
    
    public int hoeheBestimmen() {
        return wurzel.hoeheBestimmen();
    }
    
    public void loeschen(Datenelement d) {
        wurzel = wurzel.loeschen(d);
    }

    @Override
    public IElement getRoot() {
        return wurzel;
    }
}
