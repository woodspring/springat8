package woodspring.springat8.components;

import java.util.Stack;
import woodspring.springat8.model.TreeNode;

public class RevIterator<T extends Comparable<T>> {
	private Stack<TreeNode<T>> stack = new Stack<>();
	TreeNode<T> head;
	
	public RevIterator(TreeNode<T> head) {
		this.head = head;
		TreeNode<T> currNode = head;
		while ( currNode != null) {
			stack.push( currNode);
			currNode = currNode.getRight();
		}
	}
	
	
	private boolean push(TreeNode<T> currNode) {
		while ( currNode != null) {
			stack.push( currNode);
			currNode = currNode.getRight();
		}
		return true;
	}
	
	public boolean hasNext() {
		return !stack.isEmpty();
	}
	
	public TreeNode<T> next() {
		return pop();
	}
	public TreeNode<T> pop() {
		TreeNode<T> theNode = stack.pop();
		push(theNode.getLeft());
		return theNode;
	}
	
	public TreeNode<T> peek() {
		if (stack == null) return null;
		return stack.peek();
	}
	public boolean reset() {
		stack.clear();
		push(this.head);
		return true;
	}

}
