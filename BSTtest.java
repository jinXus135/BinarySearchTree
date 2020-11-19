
public class BSTtest {
public static void main(String args[]) {
	BST N = new BST();
	N.inOrderTraversal();
	N.insert(6);
	N.insert(9);
	N.insert(2);
	N.insert(5);
	N.insert(11);
	N.inOrderTraversal();
	int g = N.rangeSum(2, 10);
	System.out.println("range Sum: "+ g);
	N.delete(6);
	N.inOrderTraversal();
	g = N.rangeSum(2, 10);
	System.out.println("range Sum after deletion: "+ g);
}
}