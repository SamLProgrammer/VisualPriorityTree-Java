package views;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;
import models.Node;
import models.PriorityTree;

public class TreePanel extends JPanel{

	private PriorityTree tree;
	private int initX, initY, nodeSize, horizontalDistance;
	private boolean lighting;
	
	public TreePanel(PriorityTree tree) {
		this.tree = tree;
		lighting = false;
		Dimension panelDimension = new Dimension((int)(nodeSize*Math.pow(2, tree.countLevels())), getHeight()/100+nodeSize*tree.countLevels());
		setPreferredSize(panelDimension);
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2 = (Graphics2D)g;
		initX = getWidth()/2;
		initY = getHeight()/100;
		nodeSize = 40;
		horizontalDistance = nodeSize*(tree.countLevels());
		paintNodes(g2,tree.getRootNode(), initX, initY, 0,1);
		if(lighting) {			
			lightNodesPreOrder(g2,tree.getRootNode(), initX, initY, 0,1);
		}
		
	}
	//debería recorrer pre_orden el arbol, seteando bool a cada uno y repaint() esto con un thread.Sleep
	//algo como 
//	public void paintNodes(Graphics2D g2, Node node,int x, int y, int spaces, int counter) {
//		int spacesY = spaces;
//		if(spacesY < 0 ) {
//			spacesY *= -1;
//		}
//		x = x + spaces;
//		y = y + nodeSize;
//		if(node != null) {
//			spaces = horizontalDistance;
//			g2.drawOval(x, y, nodeSize, nodeSize);
//			g2.setFont(new Font("Oswald",Font.PLAIN,nodeSize/2));
//			g2.drawString(String.valueOf(node.getData()),
//					x+nodeSize/2 - g2.getFontMetrics().stringWidth(String.valueOf(node.getData()))/2,
//					y +nodeSize/2 + nodeSize/4);
//			if(node.getLeftNode() != null) {
//				g2.drawLine(x+nodeSize/2, y+nodeSize, x-(spaces/counter)+nodeSize, y+3*nodeSize/2);
//			}
//			if(node.getRightNode() != null) {
//				g2.drawLine(x+nodeSize/2, y+nodeSize, x+(spaces/counter), y+3*nodeSize/2);
//			}
//			paintNodes(g2, node.getLeftNode(), x, y, -spaces/counter, counter+1);
//			paintNodes(g2, node.getRightNode(), x, y, spaces/counter, counter+1);
//		}
		public void paintNodes(Graphics2D g2, Node node,int x, int y, int spaces, int counter) {
			int spacesY = spaces;
			if(spacesY < 0 ) {
				spacesY *= -1;
			}
			x = x + spaces;
			y = y + nodeSize;
			g2.setStroke(new BasicStroke(2));
			if(node != null) {
				spaces = horizontalDistance;
				if(node.isLeave()) {
					g2.setColor(Color.red);
					g2.fillOval(x, y, nodeSize, nodeSize);
					g2.setColor(new Color(0, 255/tree.countTreeLevels(node, 1), 0));
					g2.drawOval(x, y, nodeSize, nodeSize);
					g2.setColor(Color.black);
				}else {
				g2.setColor(new Color(0, 256/tree.countTreeLevels(node, 1), 0));
				g2.drawOval(x, y, nodeSize, nodeSize);
				}
				g2.setFont(new Font("Oswald",Font.PLAIN,nodeSize/2));
				g2.drawString(String.valueOf(node.getData()),
						x+nodeSize/2 - g2.getFontMetrics().stringWidth(String.valueOf(node.getData()))/2,
						y +nodeSize/2 + nodeSize/4);
				if(node.getLeftNode() != null) {
					g2.drawLine(x+nodeSize/2, y+nodeSize, x-(spaces/counter)+nodeSize, y+3*nodeSize/2);
				}
				if(node.getRightNode() != null) {
					g2.drawLine(x+nodeSize/2, y+nodeSize, x+(spaces/counter), y+3*nodeSize/2);
				}
				paintNodes(g2, node.getLeftNode(), x, y, -spaces/counter, counter+1);
				paintNodes(g2, node.getRightNode(), x, y, spaces/counter, counter+1);
			}
		
		
	}
	
	public void lightNodesPreOrder(Graphics2D g2, Node node,int x, int y, int spaces, int counter) {
		int spacesY = spaces;
		if(spacesY < 0 ) {
			spacesY *= -1;
		}
		x = x + spaces;
		y = y + nodeSize;
		if(node != null) {
			spaces = horizontalDistance;
			if(node.isPainted()) {
			g2.setColor(Color.green);
			g2.fillOval(x, y, nodeSize, nodeSize);
			g2.setFont(new Font("Oswald",Font.PLAIN,nodeSize/2));
			g2.drawString(String.valueOf(node.getData()),
					x+nodeSize/2 - g2.getFontMetrics().stringWidth(String.valueOf(node.getData()))/2,
					y +nodeSize/2 + nodeSize/4);
			}
			lightNodesPreOrder(g2, node.getLeftNode(), x, y, -spaces/counter, counter+1);
			lightNodesPreOrder(g2, node.getRightNode(), x, y, spaces/counter, counter+1);
		}
	}

	public void updateTreePanel(PriorityTree tree) {
		this.tree = tree;
		this.repaint();
	}
	
	public void lightTreePanel() {
		lighting = true;
		repaint();
	}
	
}
