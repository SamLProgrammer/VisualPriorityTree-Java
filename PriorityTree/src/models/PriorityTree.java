package models;

import java.util.ArrayList;

public class PriorityTree {

	private Node rootNode;

	public PriorityTree() {
		initTree();
	}

	private void initTree() {
		for (int i = 0; i < 1000; i++) {
			addNode(createNode(i));
		}
		addNode(createNode(9));
//		deleteNode(1);
//		deleteNode(4);
//		System.out.println(findNodeByData(9)== null);
		deleteNode(0);
	}

	// ================================== UPPER METHODS
	// =================================

	public void showTree() {
		belowShowTree(rootNode);
	}

	public Node createNode(int data) {
		return new Node(data);
	}

	public void addNode(Node node) {
		belowAddNode(rootNode, node);
//		balanceTreeAfterAdding();
	}

	public Node findNode(Node node) {
		return belowFindNode(rootNode, node);
	}

	public Node findNodeByData(int data) {
		return belowFindNodeByData(rootNode, data);
	}

	public Node findFatherNode(Node node) {
		Node auxNode = new Node();
		if (node == rootNode) {
			auxNode = rootNode;
		} else {
			auxNode = belowFindFatherNode(rootNode, node);
		}

		return auxNode;
	}

	public void lightTree(int i) {
		inOrderLightTree(rootNode, i);
	}

	public Node poll() {
		Node auxNode = rootNode;
		if (rootNode.isLeave()) {
			rootNode = null;
		} else {
			belowPoll(rootNode);
		}
		return auxNode;
	}
	
	public Node poll2(Node node) {
		Node auxNode = node;
		if (node.isLeave()) {
			node = null;
		} else {
			belowPoll(node);
		}
		return auxNode;
	}

//	public void balanceFullTree() {
//		balanceFullTree(rootNode);
//	}

	public int countLevels() {
		int levels = 0;
		if (rootNode != null) {
			levels = countTreeLevels(rootNode, 1);
		}
		return levels;
	}

	// =============================== BELLOW ESSENTIAL METHODS
	// =============================

	public Node belowFindNode(Node fatherNode, Node node) { // probado, full
		if (fatherNode.equals(node)) {
			return fatherNode;
		} else {
			if (fatherNode.getLeftNode() != null && belowFindNode(fatherNode.getLeftNode(), node) != null) {
				return belowFindNode(fatherNode.getLeftNode(), node);
			}
			if (fatherNode.getRightNode() != null && belowFindNode(fatherNode.getRightNode(), node) != null) {
				return belowFindNode(fatherNode.getRightNode(), node);
			} else {
				return null;
			}
		}
	}

	public Node belowFindNodeByData(Node node, int data) { // probado, full
		Node auxNode = null;
		if (node.getData() == data) {
			auxNode = node;
		} else {
			if (node.getRightNode() != null) {
				auxNode = belowFindNodeByData(node.getRightNode(), data);
			}
			if(auxNode == null && node.getLeftNode() != null) {
				auxNode = belowFindNodeByData(node.getLeftNode(), data);
			}
		}
		return auxNode;
	}

	public Node belowFindFatherNode(Node fatherNode, Node node) { // probado, full
		if (!fatherNode.isLeave() && sonEqualsToNode(fatherNode, node) != null) {
			return fatherNode;
		} else {
			if (fatherNode.getLeftNode() != null && belowFindFatherNode(fatherNode.getLeftNode(), node) != null) {
				return belowFindFatherNode(fatherNode.getLeftNode(), node);
			}
			if (fatherNode.getRightNode() != null && belowFindFatherNode(fatherNode.getRightNode(), node) != null) {
				return belowFindFatherNode(fatherNode.getRightNode(), node);
			} else {
				return null;
			}
		}
	}
	
	public void deleteNode(int data) {
		if(rootNode.getData() == data) { 
			poll();
		}
		else {
			Node node = findNodeByData(data);
			if(node.isLeave()) {
				Node fatherNode = findFatherNode(findNodeByData(data));
				if(fatherNode.getRightNode() == node) { 
					fatherNode.setRightNode(null);
				}
				else {
					fatherNode.setLeftNode(null);
				}
			}
			else {
				if(node.getRightNode() != null && node.getLeftNode() != null) {
					if(node.getRightNode().getData() <= node.getLeftNode().getData()) {
						node.setData(node.getRightNode().getData());
						node.getRightNode().setData(99999);
						deleteNode(node.getRightNode().getData());
					} else {
						node.setData(node.getLeftNode().getData());
						node.getLeftNode().setData(99999);
						deleteNode(node.getLeftNode().getData());
					}
				}
				else {
					if(node.getRightNode() == null) {
						node.setData(node.getLeftNode().getData());
						node.getLeftNode().setData(99999);
						deleteNode(node.getLeftNode().getData());
					} else {
						node.setData(node.getRightNode().getData());
						node.getRightNode().setData(99999);
						deleteNode(node.getRightNode().getData());
					}
				}
			}
		}
	}

	public void belowAddNode(Node fatherNode, Node node) { // probado, full
		if (rootNode == null) {
			rootNode = node;
		} else {
			if (node.getData() >= fatherNode.getData()) {
				if (fatherNode.getRightNode() == null || fatherNode.getLeftNode() == null) {
					if (fatherNode.getRightNode() == null) {
						fatherNode.setRightNode(node);
					} else {
						fatherNode.setLeftNode(node);
					}
				} else {
					if (countNodes(fatherNode.getRightNode()) <= countNodes(fatherNode.getLeftNode())) {
						if (node.getData() < fatherNode.getRightNode().getData()) {
							node.setRightNode(fatherNode.getRightNode().getRightNode());
							node.setLeftNode(fatherNode.getRightNode().getLeftNode());
							fatherNode.getRightNode().setLeftNode(null);
							fatherNode.getRightNode().setRightNode(null);
							Node auxNode = fatherNode.getRightNode();
							fatherNode.setRightNode(node);
							belowAddNode(node, auxNode);
						} else {
							belowAddNode(fatherNode.getRightNode(), node);
						}
					} else {
						if (node.getData() <= fatherNode.getLeftNode().getData()) {
							node.setRightNode(fatherNode.getLeftNode().getRightNode());
							node.setLeftNode(fatherNode.getLeftNode().getLeftNode());
							fatherNode.getLeftNode().setLeftNode(null);
							fatherNode.getLeftNode().setRightNode(null);
							Node auxNode = fatherNode.getLeftNode();
							fatherNode.setLeftNode(node);
							belowAddNode(node, auxNode);
						} else {
							belowAddNode(fatherNode.getLeftNode(), node);
						}
					}
				}
			} else {
				Node auxNode = fatherNode;
				node.setRightNode(fatherNode.getRightNode());
				node.setLeftNode(fatherNode.getLeftNode());
				fatherNode.setLeftNode(null);
				fatherNode.setRightNode(null);
				fatherNode = node;
				belowAddNode(node, auxNode);
			}
		}
	}

	private void belowPoll(Node node) {
		if (node.getRightNode() == null || node.getLeftNode() == null) {
			if (node.getRightNode() != null) {
				node.setData(node.getRightNode().getData());
				if (node.getRightNode().isLeave()) {
					node.setRightNode(null);
				} else {
					belowPoll(node.getRightNode());
				}
			} else {
				node.setData(node.getLeftNode().getData());
				if (node.getLeftNode().isLeave()) {
					node.setLeftNode(null);
				} else {
					belowPoll(node.getLeftNode());
				}
			}
		} else {
			if (node.getRightNode().getData() <= node.getLeftNode().getData()) {
				node.setData(node.getRightNode().getData());
				if (node.getRightNode().isLeave()) {
					node.setRightNode(null);
				} else {
					belowPoll(node.getRightNode());
				}
			} else {
				node.setData(node.getLeftNode().getData());
				if (node.getLeftNode().isLeave()) {
					node.setLeftNode(null);
				} else {
					belowPoll(node.getLeftNode());
				}
			}
		}
	}

	// ================================== UTILITY METHODS
	// ===================================

	public int countTreeLevels(Node node, int counter) {
		int levels = counter;
		int auxLevel;
		if (!node.isLeave()) {
			if (node.getLeftNode() != null) {
				levels = countTreeLevels(node.getLeftNode(), counter + 1);
			}
			if (node.getRightNode() != null
					&& (auxLevel = countTreeLevels(node.getRightNode(), counter + 1)) > levels) {
				levels = auxLevel;
			}
		}
		return levels;
	}

	public int balanceFactor(Node node) {
		int left = 0, right = 0;
		if (!node.isLeave()) {
			if (node.getLeftNode() != null) {
				left = countTreeLevels(node.getLeftNode(), 1);
			}
			if (node.getRightNode() != null) {
				right = countTreeLevels(node.getRightNode(), 1);
			}
		}
		return left - right;
	}

	public Node sonEqualsToNode(Node fatherNode, Node node) {
		Node auxNode = new Node();
		if (fatherNode.getLeftNode() != null && fatherNode.getLeftNode().equals(node)) {
			auxNode = fatherNode.getLeftNode();
		} else if (fatherNode.getRightNode() != null && fatherNode.getRightNode().equals(node)) {
			auxNode = fatherNode.getRightNode();
		} else {
			auxNode = null;
		}
		return auxNode;
	}

	public Node leftestNode(Node node) {
		if (node.getLeftNode() == null) {
			return node;
		} else {
			return leftestNode(node.getLeftNode());
		}
	}

	public Node rightestNode(Node node) {
		if (node.getRightNode() == null) {
			return node;
		} else {
			return leftestNode(node.getRightNode());
		}
	}

	public void belowShowTree(Node node) {
		if (node != null) {
			belowShowTree(node.getLeftNode());
			System.out.println(node.getData());
			belowShowTree(node.getRightNode());
		}
	}

	public void inOrderLightTree(Node node, int i) {
		if (node != null && i < 0) {
			inOrderLightTree(node.getLeftNode(), i - 1);
			node.setPainted(true);
			inOrderLightTree(node.getRightNode(), i - 1);
		}
	}

	public void generateNodes(int amount) {
		ArrayList<Node> nodesList = new ArrayList<Node>();
		for (int i = 0; i < amount; i++) {
			int data = 1;
			do {
				data = 1 + ((int) (Math.random() * amount));
			} while (nodeExists(nodesList, data));
			nodesList.add(createNode(data));
		}
		for (Node node : nodesList) {
			addNode(node);
		}
	}

	public boolean balancingTest() {
		boolean flag = false;
		if (isTreeBalanced(rootNode) == 0 && countNodes(rootNode) == 20) {
			flag = true;
		}
		return flag;
	}

	public int isTreeBalanced(Node node) {
		if (node == null) {
			return 0;
		}
		if (balanceFactor(node) > 1 || balanceFactor(node) < -1) {
			return 1;
		} else {
			return isTreeBalanced(node.getLeftNode()) + isTreeBalanced(node.getRightNode());
		}
	}

	public int countNodes(Node node) {
		if (node == null) {
			return 0;
		}
		if (node.isLeave()) {
			return 1;
		} else {
			return countNodes(node.getRightNode()) + countNodes(node.getLeftNode()) + 1;
		}
	}

	public boolean nodeExists(ArrayList<Node> list, int data) {
		boolean flag = false;
		for (Node node : list) {
			if (node.getData() == data) {
				flag = true;
			}
		}
		return flag;
	}

	// ================================== GETTERS && SETTERS
	// ================================

	public Node getRootNode() {
		return rootNode;
	}

	public void setRootNode(Node rootNode) {
		this.rootNode = rootNode;
	}

}
