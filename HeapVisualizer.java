import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HeapVisualizer extends JFrame {
    private List<String> heap;
    private String title;
    private int nodeSize = 40;
    private int verticalGap = 70;
    private Map<Integer, NodePosition> nodePositions;

    public HeapVisualizer(List<String> heap, String title) {
        this.heap = heap;
        this.title = title;
        this.nodePositions = new HashMap<>();

        setTitle(title);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);

        JPanel heapPanel = new HeapPanel();
        add(heapPanel, BorderLayout.CENTER);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private class NodePosition {
        int x, y;
        String data;

        public NodePosition(int x, int y, String data) {
            this.x = x;
            this.y = y;
            this.data = data;
        }
    }

    private class HeapPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            nodePositions.clear();
            calculateNodePositions();
            drawConnections(g2d);
            drawNodes(g2d);
        }

        private void calculateNodePositions() {
            if (heap.isEmpty())
                return;

            int panelWidth = getWidth();

            // Calculate positions based on heap indices
            for (int i = 0; i < heap.size(); i++) {
                int depth = (int) (Math.log(i + 1) / Math.log(2));
                int posInLevel = i - (1 << depth) + 1;
                int x = panelWidth * (2 * posInLevel + 1) / (2 << depth);
                int y = 40 + depth * verticalGap;

                nodePositions.put(i, new NodePosition(x, y, heap.get(i)));
            }
        }

        private void drawConnections(Graphics2D g2d) {
            g2d.setColor(Color.BLACK);
            g2d.setStroke(new BasicStroke(2));

            for (int i = 0; i < heap.size(); i++) {
                if (nodePositions.containsKey(i)) {
                    NodePosition parent = nodePositions.get(i);

                    // Draw connection to left child
                    int leftChild = 2 * i + 1;
                    if (leftChild < heap.size() && nodePositions.containsKey(leftChild)) {
                        NodePosition child = nodePositions.get(leftChild);
                        g2d.drawLine(parent.x, parent.y, child.x, child.y);
                    }

                    // Draw connection to right child
                    int rightChild = 2 * i + 2;
                    if (rightChild < heap.size() && nodePositions.containsKey(rightChild)) {
                        NodePosition child = nodePositions.get(rightChild);
                        g2d.drawLine(parent.x, parent.y, child.x, child.y);
                    }
                }
            }
        }

        private void drawNodes(Graphics2D g2d) {
            for (NodePosition pos : nodePositions.values()) {
                // Draw node circle
                g2d.setColor(new Color(135, 206, 235)); // Sky blue
                g2d.fillOval(pos.x - nodeSize / 2, pos.y - nodeSize / 2, nodeSize, nodeSize);

                // Draw border
                g2d.setColor(Color.BLACK);
                g2d.drawOval(pos.x - nodeSize / 2, pos.y - nodeSize / 2, nodeSize, nodeSize);

                // Draw text
                FontMetrics fm = g2d.getFontMetrics();
                int textWidth = fm.stringWidth(pos.data);
                g2d.drawString(pos.data, pos.x - textWidth / 2, pos.y + fm.getAscent() / 2);
            }
        }
    }
}
