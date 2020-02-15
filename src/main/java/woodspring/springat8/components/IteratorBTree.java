package woodspring.springat8.components;

import java.util.Stack;
import woodspring.springat8.model.TreeNode;


public class IteratorBTree<T extends Comparable<T>> {
	private Stack<TreeNode<T>> stack = new Stack<>();
	
	public IteratorBTree(TreeNode head) {
		push(head);
	}
	
	private void push(TreeNode<T> currNode) {
		while( currNode != null) {
			stack.push( currNode);
			currNode = currNode.getLeft();
		}
	}
	
	public boolean hasNext() {
		return !stack.isEmpty();
	}
	
	public TreeNode<T> next() {
		TreeNode<T> tNode = stack.pop();
		push(tNode.getRight());
		return tNode;
	}

}
