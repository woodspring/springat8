package woodspring.springat8.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import woodspring.springat8.service.SpringAtService;

@RestController
public class SpringAtController {
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired 
	SpringAtService serviceImpl;
	
	@GetMapping("/AT/{number}")
	public String startService(@PathVariable(value = "number") String number) {
		logger.info("AtController, startService-----"+ number );
		int num = Integer.valueOf( number).intValue();
		String retStr = serviceImpl.runBinaryTreeService(num);
		return retStr;
	}
	
	@GetMapping("/AT/FIND/{number}") 
	public String findKeyService(@PathVariable(value="number") String number) {
		logger.info("AtController, findKeyService-----"+ number );
		int num = Integer.valueOf( number).intValue();
		String retStr = serviceImpl.findBTreeService(num);
		logger.info("AtController, END of findKeyService-----"+ retStr );
		return retStr;
	}
	
	@GetMapping("/AT/REVERSE") 
	public String reverseService() {
		logger.info("AtController, reverseService-----");
		String retStr = serviceImpl.printReverse();
		logger.info("AtController, END of reverseService-----"+ retStr );
		return retStr;
	}
	@GetMapping("/AT/ORDER") 
	public String inOrderService() {
		logger.info("AtController, inOrderService-----");
		String retStr = serviceImpl.printInOrder();
		logger.info("AtController, END of inOrderService-----"+ retStr );
		return retStr;
	}
	@GetMapping("/AT/TEST") 
	public String testService() {
		logger.info("AtController, testService-----");
		String retStr = serviceImpl.testBinaryTree();
		logger.info("AtController, END of testService-----"+ retStr );
		return retStr;
	}
	@GetMapping("/AT/TEST2/{base}") 
	public String binaryTreeMergeReverseService(@PathVariable(value = "base") String base) {
		logger.info("AtController, binaryTreeMergeReverseService-----");
		int baseInt = Integer.valueOf( base).intValue();
		String retStr = serviceImpl.testBinaryTree2Merge(baseInt);
		logger.info("AtController, END of binaryTreeMergeReverseService-----"); //+ retStr );
		return retStr;
	}
	@GetMapping("/AT/ITR/{number}") 
	public String iteratorService(@PathVariable(value="number") String number) {
		logger.info("AtController, iteratorService-----"+ number );
		int num = Integer.valueOf( number).intValue();
		String retStr = serviceImpl.printNode( num);
		logger.info("AtController, END of iteratorService-----"+ retStr );
		return retStr;
	}
	
	
	@GetMapping("/AT/DDL") 
	public String convertToDDL() {
		logger.info("AtController, convertToDDL-----" );
		String retStr = serviceImpl.convertBinaryTree2DDList();
		logger.info("AtController, END of convertToDDL-----"+ retStr );
		return retStr;
	}

	@GetMapping("/AT/TWO/{treeA}/{treeB}") 
	public String mergerBTreeService(@PathVariable(value="treeA") String treeA, @PathVariable(value="treeB") String treeB) {
		logger.info("AtController, mergerBTreeService-----treeA:"+ treeA+"  treeB:"+ treeB );
		int aTree = Integer.valueOf( treeA).intValue();
		int bTree = Integer.valueOf( treeB).intValue();
		String retStr = serviceImpl.mergeTwoBinaryTree(aTree,  bTree);
		logger.info("AtController, END of mergerBTreeService-----"+ retStr );
		return retStr;
	}
	
	@GetMapping("/AT/SK/{number}") 
	public String addNodeToSkipList(@PathVariable(value = "number") int nodeNum) {
		logger.info("AtController, addNodeToSkipList-----" + nodeNum);
		String retStr = serviceImpl.addNodeIntoSkipList( nodeNum, null);
		logger.info("AtController, END of addNodeToSkipList-----"+ retStr );
		return retStr;
	}
	
	@GetMapping("/AT/SK/KEY/{number}") 
	public String findNodeFromSkipList(@PathVariable(value = "key") int key) {
		logger.info("AtController, findNodeFromSkipList-----" + key);
		String retStr = serviceImpl.findNodeFromSkipList(key);
		logger.info("AtController, END of findNodeFromSkipList-----"+ retStr );
		return retStr;
	}
	
	@GetMapping("/AT/SK/BYKEY/{number}") 
	public String findNodeByKey(@PathVariable(value = "key") int key) {
		logger.info("AtController, findNodeByKey-----" + key);
		String retStr = serviceImpl.findNodeByKey(key);
		logger.info("AtController, END of findNodeByKey-----"+ retStr );
		return retStr;
	}
}
