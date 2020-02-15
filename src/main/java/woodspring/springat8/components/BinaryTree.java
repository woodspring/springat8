package woodspring.springat8.components;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import woodspring.springat8.model.TreeNode;


@Component
public class BinaryTree<T extends Comparable<T> > {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	private TreeNode<T> head;
	
	private TreeNode<T> ddlHead;
	
	public BinaryTree() {
		head = null;
	}
	
	public boolean add( T value) {
		//logger.info( "add value:"+ value.toString());
		boolean bRet = false;
		if ( head == null) {
			head = new TreeNode<T>( value);
			bRet = true;
		} else {
			head.addNode( head, value);
		}		
		return bRet;		
	}
	
	public String find(T value) {
		if ( head == null) return "NULL";
		
		TreeNode<T> result = head.find(head, value);
		String retStr = (result == null) ? "NOT FOUND" : new String("FOUND:"+ result.getValue().toString());
		return retStr;
	}
	
	TreeNode<T> processBTree2DLList(TreeNode<T> currNode) {
		if (currNode == null) {
			return currNode;
		}
		logger.info("--- currNode, value:"+ currNode.getValue());
		if ( currNode.getLeft() != null) {
			TreeNode<T> leftNode = processBTree2DLList( currNode.getLeft());
			for (; leftNode.getRight() != null; leftNode = leftNode.getRight()) {
				logger.info("--- leftNode, value:"+ leftNode.getValue());
			}
			
			leftNode.setRight(currNode);
			currNode.setLeft(leftNode);
		} else logger.info("currNode.getLeft() is NULL" );
		
		if ( currNode.getRight() != null) {
			TreeNode<T> rightNode = processBTree2DLList( currNode.getRight());
			for (; rightNode.getLeft() != null; rightNode = rightNode.getLeft()) {
				logger.info("--- rightNode, value:"+ rightNode.getValue());
			}
			rightNode.setLeft(currNode);
			currNode.setRight( rightNode);
		} else logger.info("currNode.geRight() is NULL" );
		
		if (currNode == null) logger.info("currNode is NULL" );
		return currNode;
	}
	
	public TreeNode<T> convertBTree2DLList(TreeNode<T> currNode) {
		if ( currNode == null) {
			return currNode;
		}
		
		currNode = processBTree2DLList(currNode);
		int ind = 0;
		for (;currNode.getLeft() != null; currNode = currNode.getLeft(), ind++) {
			logger.info(" in Binarytree:convertBTree2DLList, ind:"+ ind+" value:"+ currNode.getValue());
		}

		return currNode;
	}
	
	public TreeNode<T> getHead() {
		return head;
	}
	
	
	public TreeNode<T> getDDLHead() {
		return ddlHead;
	}
	public void setDDLHead( TreeNode<T> ddlHead) {
		this.ddlHead = ddlHead;
	}
	
	public String printDLList( TreeNode<T> currNode) {
		StringBuffer strBuf = new StringBuffer();
		int ind =0;
		for ( TreeNode<T> theNode = currNode; theNode != null; theNode = theNode.getRight(), ind++) {
			strBuf.append(" ind:"+ind+ " value:"+ theNode.getValue());
			
		}
		logger.info("Doub Linked List:"+ strBuf.toString());
		return strBuf.toString();
	}

	public boolean isEmpty() {
		return (head==null) ? true : false;
	}
	
	public String toString() {
		String retStr = (head==null) ? "NULL" : head.printInOrder();
		return retStr;
	}

	public String printPrefix() {
		String retStr = (head==null) ? "NULL" : head.printPreOrder();
		return retStr;
	}
	public String printPostfix() {
		String retStr = (head==null) ? "NULL" : head.printPostOrder();
		return retStr;
	}
	public String printReverse() {
		String retStr = (head==null) ? "NULL" : head.printReverseOrder();
		return retStr;
	}
//	public TreeNode<T> sortAddNode(TreeNode<T> currentNode, T value) {
//		if ( head ==null) {
//			head = new TreeNode(value);
//			return head;
//		};
//		
//		//if (currentNode.isEmpty()) {
//		//	head = new TreeNode(value);
//		//	return true;
//		//};
//		int compareReult = currentNode.compareTo( value); 
//		if ( compareReult >0) {
//			currentNode.setRight( sortAddNode( currentNode.getRight(), value));
//		} else if (compareReult < 0) {
//			currentNode.setLeft( sortAddNode( currentNode.getLeft(), value));
//		} else {
//			return currentNode;
//		}
//		
//		return currentNode;
//	}
	
	
}
