package org.b10h4z4rd;

import org.b10h4z4rd.interfaces.IElement;
import org.b10h4z4rd.interfaces.ITree;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Mathias on 22.02.16.
 */
public class TreeVisualizer {

    private static TreeVisualizer singleton;
    public static TreeVisualizer getVisualizer() {
        if (singleton == null)
            singleton = new TreeVisualizer();
        return singleton;
    }

    private ITree tree;
    private TreeVisualizerPanel treeVisualizerPanel;

    private TreeVisualizer() {
        JFrame frame = new JFrame("TreeVisualizer");
        treeVisualizerPanel = new TreeVisualizerPanel();
        frame.setContentPane(treeVisualizerPanel);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

    }

    public void setTree(ITree tree) {
        this.tree = tree;
        treeChanged();
    }

    public void treeChanged() {
        treeVisualizerPanel.treeChanged();
    }

    private static int calcTreeHeight(IElement element) {
        int left = 0, right = 0;

        if (element.getLeftNode() == null && element.getRightNode() == null) {
            return 0;
        }

        if (element.getLeftNode() != null) {
            left = calcTreeHeight(element.getLeftNode()) + 1;
        }

        if (element.getRightNode() != null) {
            right = calcTreeHeight(element.getRightNode()) + 1;
        }

        return Math.max(left, right);
    }

    private class TreeVisualizerPanel extends JPanel {

        public static final int BOX_HEIGHT = 20, BOX_WIDHT = 40, BOX_HEIGHT_DIFF = 10, BOX_WIDHT_DIFF = 20;
        private int windowHeight = 100, windowWidth = 100;

        public TreeVisualizerPanel() {
            setPreferredSize(new Dimension(windowWidth, windowHeight));
        }

        public void treeChanged() {
            int treeHeight = TreeVisualizer.calcTreeHeight(TreeVisualizer.this.tree.getRoot());
            windowHeight = 40 + treeHeight * BOX_HEIGHT + (treeHeight - 2) * 10;
            windowWidth = (int) (40 + Math.pow(2, treeHeight - 1) * BOX_WIDHT + Math.pow(2, treeHeight - 2) * BOX_WIDHT_DIFF);
            setPreferredSize(new Dimension(windowWidth, windowHeight));
            Window window = SwingUtilities.getWindowAncestor(this);
            window.pack();
            repaint();
        }

        @Override
        public void paint(Graphics g) {
            g.clearRect(0, 0, getWidth(), getHeight());

            g.setColor(Color.black);
            if (TreeVisualizer.this.tree != null && TreeVisualizer.this.tree.getRoot().getData() != null) {
                paintFromNode(getWidth() / 2, 20 + BOX_HEIGHT / 2, getWidth() / 2, TreeVisualizer.this.tree.getRoot(), g);
            }
        }

        private void paintFromNode(int x, int y, int diff, IElement node, Graphics g) {
            int boxX = x - BOX_WIDHT / 2, boxY = y - BOX_HEIGHT / 2;
            g.drawRect(boxX, boxY, BOX_WIDHT, BOX_HEIGHT);
            String toDraw = node.getData().getData().toString();
            g.drawString(toDraw, x - g.getFontMetrics().stringWidth(toDraw) / 2, y + g.getFontMetrics().getHeight() / 2);
            int newY = y + BOX_HEIGHT + BOX_HEIGHT_DIFF;
            if (node.getLeftNode() != null) {
                if (node.getLeftNode().getData() != null) {
                    int newX = x - BOX_WIDHT_DIFF / 2 - BOX_WIDHT / 2 - diff / 2;
                    g.drawLine(x, y + BOX_HEIGHT / 2, newX, y + BOX_HEIGHT / 2 + BOX_HEIGHT_DIFF);
                    paintFromNode(newX, newY, diff / 2, node.getLeftNode(), g);
                }
            }
            if (node.getRightNode() != null) {
                if (node.getRightNode().getData() != null) {
                    int newX = x + BOX_WIDHT_DIFF / 2 + BOX_WIDHT / 2 + diff / 2;
                    g.drawLine(x, y + BOX_HEIGHT / 2, newX, y + BOX_HEIGHT / 2 + BOX_HEIGHT_DIFF);
                    paintFromNode(newX, newY, diff / 2, node.getRightNode(), g);
                }
            }
        }
    }
}
