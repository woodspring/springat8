package woodspring.springat8.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TreeNode<T extends Comparable<T>> {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	private T value;
	private TreeNode<T> left, right;
	
	public TreeNode() {
		this.value = null;
		left = right = null;
	}
	
	public TreeNode(T value) {
		this.value = value;
		left = right = null;
	}

	
	public TreeNode<T> addNode(TreeNode<T> currNode, T theValue) {
		if (currNode == null) {
			return new TreeNode<T>(theValue);
		}
		int compResult = currNode.getValue().compareTo( theValue); 
		logger.info("curr value:"+ ((currNode == null) ? "null" : currNode.getValue().toString())+" theValue:"+ theValue+" compare:"+compResult);
		
		if (compResult < 0) {
			currNode.setRight( addNode( currNode.getRight(), theValue));
		} else if ( compResult > 0) {
			currNode.setLeft( addNode( currNode.getLeft(), theValue));
		} else {
			logger.info("DUPLICATED value :"+ theValue.toString());
		}
		return currNode;
	}
	
	public TreeNode<T> find(TreeNode<T> currNode, T theKey) {
		if ( currNode == null) return null;
		int compResult = currNode.getValue().compareTo( theKey);
		logger.info(" find: currNode value:["+ currNode.getValue() +"] theKey:["+ theKey+" compResultL" + compResult);
		if ( compResult >0 ) {
			return currNode.find(currNode.getLeft(), theKey);
		} else if ( compResult <0) {
			return currNode.find( currNode.getRight(), theKey);
		} 
		return currNode;
	}
	public int compareTo(T value) {
		return this.value.compareTo( value);
	}
	public T getValue() {
		return value;
	}

	public void setValue(T value) {
		this.value = value;
	}

	public TreeNode<T> getLeft() {
		return left;
	}

	public void setLeft(TreeNode<T> left) {
		this.left = left;
	}

	public TreeNode<T> getRight() {
		return right;
	}

	public void setRight(TreeNode<T> right) {
		this.right = right;
	}
	public boolean isEmpty() {
		return (value == null) ? true : false;
	}
	
	public String printInOrder() {
		StringBuffer strBuf = new StringBuffer();
		if ( left != null) {
			strBuf.append(" "+ left.printInOrder());
		}
		strBuf.append(" "+ value.toString());
		if ( right != null) {
			strBuf.append(" "+ right.printInOrder());
		}
		
		return ( strBuf.toString());
	}
	
	public String printPreOrder() {
		StringBuffer strBuf = new StringBuffer();
		strBuf.append(" C-"+ value.toString());
		if ( left != null) {
			strBuf.append(" L-"+ left.printPreOrder());
		}
		
		if ( right != null) {
			strBuf.append(" R-"+ right.printPreOrder());
		}
		
		return ( strBuf.toString());
	}
	
	public String printPostOrder() {
		StringBuffer strBuf = new StringBuffer();
		if ( left != null) {
			strBuf.append(" L-"+ left.printPostOrder());
		}
		
		if ( right != null) {
			strBuf.append(" R-"+ right.printPostOrder());
		}
		strBuf.append(" C-"+ value.toString());
		
		return ( strBuf.toString());
	}
	
	public String printReverseOrder() {
		StringBuffer strBuf = new StringBuffer();
		if ( right != null) {
			strBuf.append(" "+ right.printReverseOrder());
		}
		strBuf.append(" "+ value.toString());
		if ( left != null) {
			strBuf.append(" "+ left.printReverseOrder());
		}
		
		return ( strBuf.toString());
	}
	

}
