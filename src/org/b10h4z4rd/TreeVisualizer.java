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
        frame.setContentPane(new JScrollPane(treeVisualizerPanel));
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

    private class TreeVisualizerPanel extends JPanel {

        public static final int BOX_HEIGHT = 20, BOX_WIDHT = 40, BOX_HEIGHT_DIFF = 10, BOX_WIDHT_DIFF = 5;
        private int windowHeight = 500, windowWidth = 500;
        private int treeHeight;
        private double[] diffs;

        public TreeVisualizerPanel() {
            setPreferredSize(new Dimension(windowWidth, windowHeight));
        }

        private int calcTreeHeight(IElement element) {
            int left = 0, right = 0;

            if (element.getData() == null) {
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

        public void treeChanged() {
            treeHeight = calcTreeHeight(TreeVisualizer.this.tree.getRoot());

            if (treeHeight > 1) {
                diffs = new double[treeHeight - 1];

                diffs[treeHeight - 2] = BOX_WIDHT_DIFF;

                for (int i = treeHeight - 3; i >= 0; i--) {
                    diffs[i] += 2 * BOX_WIDHT + 2 * diffs[i + 1];
                }


                windowHeight = 80 + treeHeight * BOX_HEIGHT + treeHeight * 10;
                windowWidth = Math.abs(getWidth() / 2 - calcRightBound(tree.getRoot().getRightNode(), getWidth() / 2, 0)) +
                        Math.abs(getWidth() / 2 - calcLeftBound(tree.getRoot().getLeftNode(), getWidth() / 2, 0)) + 80;
                setPreferredSize(new Dimension(windowWidth, windowHeight));
                //Window window = SwingUtilities.getWindowAncestor(this);
                //window.pack();
                repaint();
            }
        }

        @Override
        public void paint(Graphics g) {
            g.clearRect(0, 0, getWidth(), getHeight());

            g.setColor(Color.black);
            if (treeHeight > 1) {
                if (TreeVisualizer.this.tree != null && TreeVisualizer.this.tree.getRoot().getData() != null) {
                    paintFromNode(getWidth() / 2, 20 + BOX_HEIGHT / 2, 0, TreeVisualizer.this.tree.getRoot(), g);
                }
            }
        }

        private void paintFromNode(int x, int y, int level, IElement node, Graphics g) {
            int boxX = x - BOX_WIDHT / 2, boxY = y - BOX_HEIGHT / 2;
            g.drawRect(boxX, boxY, BOX_WIDHT, BOX_HEIGHT);
            String toDraw = node.getData().getData().toString();
            g.drawString(toDraw, x - g.getFontMetrics().stringWidth(toDraw) / 2, y + g.getFontMetrics().getHeight() / 2);
            int newY = y + BOX_HEIGHT + BOX_HEIGHT_DIFF;
            if (node.getLeftNode() != null) {
                if (node.getLeftNode().getData() != null) {
                    double newX = x - diffs[level] / 2 - BOX_WIDHT / 2;
                    g.drawLine(x, y + BOX_HEIGHT / 2, (int) newX, y + BOX_HEIGHT / 2 + BOX_HEIGHT_DIFF);
                    paintFromNode((int) newX, newY, level + 1, node.getLeftNode(), g);
                }
            }
            if (node.getRightNode() != null) {
                if (node.getRightNode().getData() != null) {
                    double newX = x + diffs[level] / 2 + BOX_WIDHT / 2;
                    g.drawLine(x, y + BOX_HEIGHT / 2, (int) newX, y + BOX_HEIGHT / 2 + BOX_HEIGHT_DIFF);
                    paintFromNode((int) newX, newY, level + 1, node.getRightNode(), g);
                }
            }
        }

        private int calcLeftBound(IElement element, int width, int level) {
            if (element.getData() == null)
                return width;

            width -= BOX_WIDHT_DIFF / 2 - BOX_WIDHT / 2 - diffs[level] / 2;
            if (element.getLeftNode() != null)
                return calcLeftBound(element.getLeftNode(), width, level + 1);
            else
                return width;
        }

        private int calcRightBound(IElement element, int width, int level) {
            if (element.getData() == null)
                return width;

            width += BOX_WIDHT_DIFF / 2 + BOX_WIDHT / 2 + diffs[level] / 2;
            if (element.getRightNode() != null)
                return calcLeftBound(element.getRightNode(), width, level + 1);
            else
                return width;
        }
    }
}
