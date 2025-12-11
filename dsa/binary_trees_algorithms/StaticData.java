package binary_trees_algorithms;

class Node {
    int value;
    Node left;
    Node right;

    Node(int value){
        this.value = value;
    }
}

public class StaticData {

    // Simple Balanced Binary Tree
    //         1
    //      /     \
    //     2       3
    //    / \     / \
    //   4   5   6   7
    public static Node balancedTree() {
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right.left = new Node(6);
        root.right.right = new Node(7);
        return root;
    }

    // Left-Skewed Tree
    // 1 -> 2 -> 3 -> 4 -> 5
    public static Node leftSkewed() {
        Node root = new Node(1);
        root.left = new Node(2);
        root.left.left = new Node(3);
        root.left.left.left = new Node(4);
        root.left.left.left.left = new Node(5);
        return root;
    }

    // Right-Skewed Tree
    // 1 -> 2 -> 3 -> 4 -> 5
    public static Node rightSkewed() {
        Node root = new Node(1);
        root.right = new Node(2);
        root.right.right = new Node(3);
        root.right.right.right = new Node(4);
        root.right.right.right.right = new Node(5);
        return root;
    }

    // Tree with Single Child Nodes (ragged tree)
    //       1
    //     /
    //    2
    //     \
    //      3
    //     /
    //    4
    public static Node raggedTree() {
        Node root = new Node(1);
        root.left = new Node(2);
        root.left.right = new Node(3);
        root.left.right.left = new Node(4);
        return root;
    }

    // Perfect but small tree (edge-case for 2-level)
    //      10
    //     /  \
    //   20    30
    public static Node smallPerfect() {
        Node root = new Node(10);
        root.left = new Node(20);
        root.right = new Node(30);
        return root;
    }

    // BST (Binary Search Tree)
    //          8
    //       /     \
    //      4       12
    //     / \     /  \
    //    2   6   10  14
    public static Node sampleBST() {
        Node root = new Node(8);
        root.left = new Node(4);
        root.right = new Node(12);
        root.left.left = new Node(2);
        root.left.right = new Node(6);
        root.right.left = new Node(10);
        root.right.right = new Node(14);
        return root;
    }

    // Non-BST (random tree to test validation algorithms)
    //         5
    //       /   \
    //      1     4
    //         /    \
    //        3      6
    public static Node notBST() {
        Node root = new Node(5);
        root.left = new Node(1);
        root.right = new Node(4);
        root.right.left = new Node(3);
        root.right.right = new Node(6);
        return root;
    }

    // Single Node Tree
    public static Node singleNode() {
        return new Node(999);
    }

    // Empty Tree (null)
    public static Node emptyTree() {
        return null;
    }

    // Tree with some null children mixed
    //       10
    //     /    \
    //    5      20
    //   / \    /
    // null 7  15
    public static Node missingChildren() {
        Node root = new Node(10);
        root.left = new Node(5);
        root.left.right = new Node(7);
        root.right = new Node(20);
        root.right.left = new Node(15);
        return root;
    }
}
