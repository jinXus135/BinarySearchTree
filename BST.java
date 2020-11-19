import java.util.LinkedList;
import java.util.Queue;

//Binary search tree implementation
public class BST {
	BSTnode root;
	BST(){
		root = null;
	}
	//NODE CLASS
	public class BSTnode{
		int data;
		BSTnode left;
		BSTnode right;
		
		BSTnode(int d){
			data = d;
		}
		BSTnode(){
			data = 0;
			left = null;
			right = null;
		}
		BSTnode(int d, BSTnode l, BSTnode r) {
			data = d;
			left = l;
			right = r;
		}
	}

	public boolean find(int d) { // public version of our method 
		return find(root, d);
	}
	protected boolean find(BSTnode r, int d) { //private version of our method
		boolean isThere = 	false;
		if(r == null) {
			return isThere = false; // returns false if we have no nodes to look at
		}
		if(d == r.data) { // returns true if we've found our Node
			return isThere = true;
		}
		else if (d < r.data) { // recursively iterates through tree on the left if data is less than
			return isThere = find(r.left, d);
			
		}
		else if(d> r.data) { // recursively iterates through tree on the right if data is greater than
			return isThere = find(r.right, d);
		}
		return isThere;
	}
	
	public void insert(int d) { // public function for insert
		root = insert(root, d);
	}
	protected BSTnode insert(BSTnode r, int d) { // protected function for insert to be called within public method
		if (r == null) {	// if root is null...
			r = new BSTnode(d); // create a root
		}
		if (d < r.data) { // otherwise if our value is less than... we go left
			r.left = insert(r.left, d);
		}
		else if(d >r.data) { // we go right if its bigger
			r.right = insert(r.right, d);
		}
		return r;
	
	}
	public void inOrderTraversal (){ //public in order traversal function
		inOrderTraversal(root);
	}
	protected void inOrderTraversal(BSTnode r) { // called inside the public version above. implements functionality
		if(r != null) {
			inOrderTraversal(r.left); // print all left most values first by calling function recursively
			System.out.println(r.data);
			inOrderTraversal(r.right); // then we print the right side by calling again recursively
		}
	}
	
	public void delete(int key) {
		delete(root, key);
		}

	protected BSTnode delete(BSTnode r, int d) {
		if (r == null) { //if we delete nothing say we deleted nothing
			return null;
		}
		if (r.data < d) { 
			r.left = delete(r.left, d); // looking for our value and deleting it on the left
			return r;
		}
		else if (r.data > d) {
			r.right = delete(r.right, d); // looking for our value and deleting it on the right
			return r;
		}
		else {
			if (r.left == null) { // if nothing on the left..
				return r.right;
			}
			else if (r.right == null) { // if nothing on our right..
				return r.left;
			}
			else {
				if (r.right.left == null) {
					r.data = r.right.data;
					r.right = r.right.right;
				}
				else {
					r.data = removeSmallest(r.right);
				}
				return r;
			}
		}
	}
	
	public int removeSmallest(BSTnode r){
		if(r.left.left == null) {
			int smallest = r.left.data;
			r.left = r.left.right;
			return smallest;
			}
		else {
			return removeSmallest(r.left);
		}
		
	}
	public int rangeSum(int L, int R) { // determines sum of all values lying within range L to R
		int tbr = 0;
		BSTnode r = root;
		
		if (r == null ) { // if tree is empty of if our range DNE
		return -1;
	}
		Queue<BSTnode> queue = new LinkedList<>(); // stores all values within range. when empty, we have run out of
		queue.add(r);							   // things to possibly add
		while(!queue.isEmpty()) {
			BSTnode phold = queue.remove();
			if(phold.data >= L && phold.data <= R) { // if item in queue is in range...
				tbr += phold.data; // add to our sum
			}
			if (phold.left != null && phold.left.data >= L) {// if we have values to our left in range...
				queue.add(phold.left); 						// add the left side to our queue for adding 
			}
			if(phold.right != null && phold.right.data <= R) { // if we have values to our right in range..
				queue.add(phold.right);						   // add the right side to our queue for adding
			}
		}
		return tbr;	// return our sum
}
}