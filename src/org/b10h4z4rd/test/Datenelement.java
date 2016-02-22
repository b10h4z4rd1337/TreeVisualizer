package org.b10h4z4rd.test;

import org.b10h4z4rd.interfaces.IData;

public abstract class Datenelement implements IData
{
    /**
     * Vergleicht das vorhandene Datenelement mit dem �bergebenen.
     * @param wert der Vergleichswert
     * @return -1: das aktuelle Element ist kleiner als das Vergleichselement
     *          0: das aktuelle Elelemt hat den gleichen Wert wie das Vergleichselement
     *          1: das aktuelle Element ist gr��er als das Vergleichselement
     */
    abstract int vergleichen (Datenelement wert);
    
    /**
     * Gibt die relevante Information des Datenelements auf die Konsole aus.
     */
    abstract void ausgeben ();
}
