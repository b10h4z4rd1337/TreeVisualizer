package org.b10h4z4rd.test;

class Zahldaten extends Datenelement
{
    /** Der Wert des Elements */
    private int wert;
    
    /**
     * Legt das Datenelement mit dem gegebenen Wert an.
     * @param w Wert des Datenelements
     */
    Zahldaten(int w)
    {
        wert = w;
    }
    
    /**
     * Vergleicht das vorhandene Datenelement mit dem �bergebenen.
     * @param wert Referenzauf den Vergleichswert
     * @return -1: das aktuelle Element ist kleiner als das Vergleichselement
     *          0: das aktuelle Elelemt hat den gleichen Wert wie das Vergleichselement
     *          1: das aktuelle Element ist gr��er als das Vergleichselement
     */
    public int vergleichen (Datenelement vWert)
    {
        int vergleich;
        vergleich = ((Zahldaten) vWert).wert;
        if (wert == vergleich)
        {
            return 0;
        }
        else if (wert < vergleich)
        {
            return -1;
        }
        else
        {
            return 1;
        }
    }
    
    /**
     * Gibt die relevante Information des Datenelements auf die Konsole aus.
     */
    public void ausgeben ()
    {
        System.out.println ("Das Datenelement hat den Wert: " + wert);
    }

    @Override
    public Object getData() {
        return wert;
    }
}
