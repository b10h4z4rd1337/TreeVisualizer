package org.b10h4z4rd.test;

import org.b10h4z4rd.TreeVisualizer;

public class Test
{
    Baum baum;

    public Test()
    {
        baum  = new Baum();
        testSuchen();
    }

    void testSuchen()
    {
        TreeVisualizer visualizer = TreeVisualizer.getVisualizer();
        visualizer.setTree(baum);

        for (int i = 0; i < 10; i++) {
            baum.einfuegen(new Zahldaten((int)(Math.random() * Short.MAX_VALUE)));
        }
    }
}
