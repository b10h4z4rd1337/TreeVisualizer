package org.b10h4z4rd.test;

import org.b10h4z4rd.interfaces.IData;
import org.b10h4z4rd.interfaces.IElement;

public class Knoten extends Baumelement
{
    protected Baumelement links, rechts;
    protected Datenelement data;
    
    public Knoten(Datenelement elem, int level) {
        this.data = elem;
        this.level = level;
    }
    
    public Baumelement einfuegen(Datenelement elem, int level) {
        int compare = data.vergleichen(elem);
        if (compare < 0) {
            rechts = rechts.einfuegen(elem, level + 1);
            return this;
        } else if (compare == 0) {
            return this;
        } else {
            links = links.einfuegen(elem, level + 1);
            return this;
        }
    }
    
    public boolean istVorhanden(Datenelement elem) {
        int compare = data.vergleichen(elem);
        if (compare == 0) {
            return true;
        } else if (compare < 0) {
            return links.istVorhanden(elem);
        } else {
            return rechts.istVorhanden(elem);
        }
    }
    
    public Baumelement entfernen(Datenelement elem) {
        int compare = data.vergleichen(elem);
        if (compare == 0) {
            if (rechts instanceof Abschluss) {
                return links;
            } else if (links instanceof Abschluss) {
                return rechts;
            } else {
                rechts = ((Knoten)rechts).sucheMin(this);
                return this;
            }
        } else if (compare < 0) {
            links = links.entfernen(elem);
            return this;
        } else {
            rechts = rechts.entfernen(elem);
            return this;
        }
    }
    
    private Baumelement sucheMin(Knoten knoten) {
        if (links instanceof Abschluss) {
            knoten.data = this.data;
            return rechts;
        } else {
            return ((Knoten)links).sucheMin(knoten);
        }
    }
    
    public int istAusgeglichen() {
        if (rechts instanceof Abschluss) {
                return level;
            } else if (links instanceof Abschluss) {
                return level;
            } else {
                return links.istAusgeglichen() - rechts.istAusgeglichen();
            }
    }
    
    public void inOrder() {
        links.inOrder();
        data.ausgeben();
        rechts.inOrder();
    }
    
    public void preOrder() {
        data.ausgeben();
        links.preOrder();
        rechts.preOrder();
    }
    
    public void postOrder() {
        links.postOrder();
        rechts.postOrder();
        data.ausgeben();
    }
    
    public int tiefeBestimmen(Datenelement d, int c) {
        if (data.vergleichen(d) == 0) {
            return c + 1;
        } else {
            int l = 0, r = 0;
            if (links instanceof Knoten) {
                l = links.tiefeBestimmen(d, c+ 1);
            }
            if (rechts instanceof Knoten) {
                r = rechts.tiefeBestimmen(d, c+ 1);
            }
            return Math.max(r, l);
        }
    }
    
    public int hoeheBestimmen() {
        int l = links.hoeheBestimmen();
        int r = rechts.hoeheBestimmen();
        return Math.max(l, r) + 1;
    }
    
    public Baumelement loeschen(Datenelement d) {
        switch (data.vergleichen(d)) {
            case 0:
                switch (superSecretMethod()) {
                    case 0:
                        return Baum.ABSCHLUSS;
                    case 1:
                        return secondSuperSecretMethod();
                    case 2:
                        Baumelement result = rechts.thirdSuperSecretMethod(this, this);
                            Knoten k = (Knoten) result;
                            k.rechts = rechts;
                            k.links = links;
                        return result;
                }
                break;
            case 1:
                links = links.loeschen(d);
                return this;
            case -1:
                rechts = rechts.loeschen(d);
                return this;
        }
        return null;
    }
    
    private int superSecretMethod() {
        int result = 0;
        if (links instanceof Knoten) {
            result++;
        }
        
        if (rechts instanceof Knoten) {
            result++;
        }
        
        return result;
    }
    
    private Baumelement secondSuperSecretMethod() {
        if (links instanceof Knoten) {
            return links;
        }
        return rechts;
    }
    
    public Baumelement thirdSuperSecretMethod(Knoten toRemove, Knoten parent) {
        if (links instanceof Abschluss) {
            return this;
        }
        return links.thirdSuperSecretMethod(toRemove, this);
    }
    
    private Baumelement removeLeftChild(Knoten toRemove) {
        if (this == toRemove) {
            return Baum.ABSCHLUSS;
        }

        if (links == toRemove) {
            links = Baum.ABSCHLUSS;
            return this;
        }

        links = ((Knoten)links).removeLeftChild(toRemove);
        return this;
    }

    @Override
    public IElement getLeftNode() {
        return links;
    }

    @Override
    public IElement getRightNode() {
        return rechts;
    }

    @Override
    public IData getData() {
        return data;
    }
}
