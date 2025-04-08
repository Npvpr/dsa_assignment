import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class TreeVisualizer extends JFrame {
    private TreeNode root;
    private String title;
    private int nodeSize = 40;
    private int verticalGap = 70;
    private int horizontalSpaceFactor = 1;
    private Map<String, NodePosition> nodePositions;

    public TreeVisualizer(TreeNode root, String title) {
        this.root = root;
        this.title = title;
        this.nodePositions = new HashMap<>();

        setTitle(title);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);

        // Create a custom JPanel for drawing the tree
        JPanel treePanel = new TreePanel();
        add(treePanel, BorderLayout.CENTER);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private class NodePosition {
        int x;
        int y;
        String data;

        public NodePosition(int x, int y, String data) {
            this.x = x;
            this.y = y;
            this.data = data;
        }
    }

    private class TreePanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            // Calculate optimal width for the visualization
            int panelWidth = getWidth();
            int panelHeight = getHeight();

            nodePositions.clear();
            calculateNodePositions(root, "0", 0, panelWidth / 2, 40);

            // Draw connections (edges) first so they appear beneath nodes
            drawConnections(g2d);

            // Draw nodes
            for (Map.Entry<String, NodePosition> entry : nodePositions.entrySet()) {
                NodePosition pos = entry.getValue();
                drawNode(g2d, pos.x, pos.y, pos.data);
            }
        }

        private void calculateNodePositions(TreeNode node, String nodeId, int level, int x, int y) {
            if (node == null || node == root.EMPTY_TREE) {
                return;
            }

            // Store this node's position
            nodePositions.put(nodeId, new NodePosition(x, y, node.getData()));

            // Calculate horizontal offset for this level
            int panelWidth = getWidth();
            int horizontalOffset = (int) (panelWidth / Math.pow(2, level + 2) * horizontalSpaceFactor);

            // Process left child
            if (node.getLeftChild() != null && node.getLeftChild() != root.EMPTY_TREE) {
                String leftId = nodeId + "L";
                calculateNodePositions(node.getLeftChild(), leftId, level + 1,
                        x - horizontalOffset, y + verticalGap);
            }

            // Process right child
            if (node.getRightChild() != null && node.getRightChild() != root.EMPTY_TREE) {
                String rightId = nodeId + "R";
                calculateNodePositions(node.getRightChild(), rightId, level + 1,
                        x + horizontalOffset, y + verticalGap);
            }
        }

        private void drawConnections(Graphics2D g2d) {
            g2d.setStroke(new BasicStroke(2));
            g2d.setColor(Color.BLACK);

            for (String nodeId : nodePositions.keySet()) {
                // If this isn't the root, it has a parent
                if (!nodeId.equals("0")) {
                    String parentId = nodeId.substring(0, nodeId.length() - 1);
                    NodePosition child = nodePositions.get(nodeId);
                    NodePosition parent = nodePositions.get(parentId);

                    if (parent != null && child != null) {
                        g2d.drawLine(parent.x, parent.y, child.x, child.y);
                    }
                }
            }
        }

        private void drawNode(Graphics2D g2d, int x, int y, String value) {
            // Draw circle
            g2d.setColor(new Color(135, 206, 235)); // Sky blue
            g2d.fillOval(x - nodeSize / 2, y - nodeSize / 2, nodeSize, nodeSize);

            // Draw border
            g2d.setColor(Color.BLACK);
            g2d.setStroke(new BasicStroke(2));
            g2d.drawOval(x - nodeSize / 2, y - nodeSize / 2, nodeSize, nodeSize);

            // Draw value text
            g2d.setColor(Color.BLACK);
            FontMetrics fm = g2d.getFontMetrics();
            int textWidth = fm.stringWidth(value);
            int textHeight = fm.getHeight();
            g2d.drawString(value, x - textWidth / 2, y + textHeight / 4);
        }
    }

    // Example how to use this program
    // Main method to test the visualizer
    // public static void main(String[] args) {
    //     // Example usage
    //     TreeNode root = new TreeNode("A");
    //     TreeNode b = new TreeNode("B");
    //     TreeNode c = new TreeNode("C");
    //     TreeNode d = new TreeNode("D");
    //     TreeNode e = new TreeNode("E");
    //     TreeNode f = new TreeNode("F");
    //     TreeNode g = new TreeNode("G");

    //     root.setLeftChild(b);
    //     root.setRightChild(c);
    //     b.setLeftChild(d);
    //     b.setRightChild(e);
    //     c.setLeftChild(f);
    //     c.setRightChild(g);

    //     SwingUtilities.invokeLater(() -> {
    //         new TreeVisualizer(root, "Binary Tree Visualization");
    //     });
    // }
}
