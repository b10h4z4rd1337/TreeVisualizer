package org.b10h4z4rd.test;

import org.b10h4z4rd.interfaces.IData;
import org.b10h4z4rd.interfaces.IElement;

public class Abschluss extends Baumelement
{
    public Baumelement einfuegen(Datenelement elem, int level) {
        Knoten neu = new Knoten(elem, level);
        neu.links = Baum.ABSCHLUSS;
        neu.rechts = this;
        return neu;
    }
    
    public boolean istVorhanden(Datenelement elem) {
        return false;
    }
    
    public Baumelement entfernen(Datenelement elem) {
        return this;
    }
    
    public int istAusgeglichen() {
        return 0;
    }
    
    public void inOrder() {}
    public void preOrder() {}
    public void postOrder() {}
    
    public int tiefeBestimmen(Datenelement d, int c) { return 0; }
    
    public int hoeheBestimmen() { return 0; }
    
    public Knoten thirdSuperSecretMethod(Knoten p, Knoten p2) { return null; }
    public Baumelement loeschen(Datenelement d) { return this; }

    @Override
    public IElement getLeftNode() {
        return null;
    }

    @Override
    public IElement getRightNode() {
        return null;
    }

    @Override
    public IData getData() {
        return null;
    }
}
