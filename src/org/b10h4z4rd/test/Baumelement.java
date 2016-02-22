package org.b10h4z4rd.test;

import org.b10h4z4rd.interfaces.IElement;

public abstract class Baumelement implements IElement
{
    int level = 0;
    public abstract Baumelement einfuegen(Datenelement elem, int level);
    public abstract boolean istVorhanden(Datenelement elem);
    public abstract Baumelement entfernen(Datenelement elem);
    public abstract int istAusgeglichen();
    public abstract void inOrder();
    public abstract void preOrder();
    public abstract void postOrder();
    public abstract int tiefeBestimmen(Datenelement d, int c);
    public abstract int hoeheBestimmen();
    public abstract Baumelement loeschen(Datenelement d);
    public abstract Baumelement thirdSuperSecretMethod(Knoten parent, Knoten k);
}
