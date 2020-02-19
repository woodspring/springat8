package woodspring.springat8.service;

import woodspring.springat8.model.NodeValue;

public interface SpringAtService {
	
	String runBinaryTreeService(int number);
	String findBTreeService( int theNum);
	String printNode(int index);
	String printInOrder();
	String printReverse();
	String testBinaryTree();
	String testBinaryTree2Merge(int base);
	String convertBinaryTree2DDList();
	String mergeTwoBinaryTree(int treeA, int treeB);
	
	String addNodeIntoSkipList(Integer theKey, NodeValue theValue);
	String findNodeFromSkipList(Integer theKey);
	String findNodeByKey(Integer theKey);
}
