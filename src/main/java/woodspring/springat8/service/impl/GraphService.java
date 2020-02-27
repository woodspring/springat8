package woodspring.springloop.service;



public interface GraphService {
	void init(int item);
	String buildGraph();
	String travelNode_DFS(Integer startPoint);
	String travelNode_BFS(Integer startPoint );
	String checkCycle(Integer startPoint );
	
}
