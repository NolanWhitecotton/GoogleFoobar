package tree;

import java.util.Arrays;

public class Solution {
    public static int[] solution(int h, int[] q) {
        int[] results = new int[q.length];
        
        //generate tree of height h
        Node tree = new Node(h);
        
        //set all the labels in tree
        labelCur = 1;//reset labelCur because static
        label(tree);
        
        //traverse the tree until each q is a child is found
        for(int i=0;i<q.length;i++) {
        	results[i] = findParent(tree,q[i]);
        }
        
    	return results;
    }
    
    public static void main(String[] args) {
    	int[] temp = {7,3,5,1};
    	System.out.println(Arrays.toString(solution(3, temp)));
    }
    
    /*
     * finds the parent node of q number
     */
    public static int findParent(Node n, int q) {
    	if(n.left!=null) {
	    	if(n.left.label == q || n.right.label == q) {
	    		return n.label;
	    	}
    	
	    	int leftval = findParent(n.left, q);
	    	if(leftval != -1) {
	    		return leftval;
	    	}
	    	int rightval = findParent(n.right, q);
	    	if(rightval != -1) {
	    		return rightval;
	    	}
    	}
    	return -1;
    }
    
    /*
     * labels the given tree via post order traversal
     */
    static int labelCur = 1;
    public static void label(Node n) {
    	if(n.left == null) {
    		n.setLabel(labelCur++);
    	}
    	
    	if(n.left != null) {
    		label(n.left);
    	}
    	if(n.right!=null) {
    		label(n.right);
    	}
    	
    	if(n.left!=null) {
    		n.setLabel(labelCur++);
    	}
    }
}

/*
 * a node of a perfect binary tree with an int label
 */
class Node {
	public static int count = 0;
	
	Node left;
	Node right;
	int label;
	
	public Node(int times) {
		count++;
		//populate left and right
		if(times-1 > 0) {
			left = new Node(times-1);
			right = new Node(times-1);
		}
	}
	
	public void setLabel(int label) {
		this.label = label;
	}
}