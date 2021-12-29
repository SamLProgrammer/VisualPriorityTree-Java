package models;

public class Node {
	
	private int data;
	private boolean painted;
	private Node leftNode;
	private Node rightNode;

	public Node(int data) {
		setData(data);
	}
	
	public Node() {
		
	}
	
	public boolean isLeave() {
		boolean flag = true;
		if(leftNode != null || rightNode != null) {
			flag = false;
		}
		return flag;
	}
	
	public int getData() {
		return data;
	}

	public Node getLeftNode() {
		return leftNode;
	}

	public Node getRightNode() {
		return rightNode;
	}

	public void setData(int data) {
		this.data = data;
	}

	public void setLeftNode(Node leftNode) {
		this.leftNode = leftNode;
	}

	public void setRightNode(Node rightNode) {
		this.rightNode = rightNode;
	}

	public boolean isPainted() {
		return painted;
	}

	public void setPainted(boolean painted) {
		this.painted = painted;
	}

}
