package binary_trees_algorithms;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        // Example: load a sample tree
        Node root = binary_trees_algorithms.StaticData.balancedTree();

        List<Integer> result_dfs_recursive = dfs_recursive(root);
        List<Integer> result_dfs_iterative = dfs_iterative(root);
        List<Integer> result_bfs_iterative = bfs_iterative(root);
        List<Integer> result_bfs_recursive = bfs_recursive(root);

        System.out.print("Recursive DFS: ");
        result_dfs_recursive.forEach(num -> System.out.print(num + " -> "));
        System.out.println();

        System.out.print("Iterative DFS: ");
        result_dfs_iterative.forEach(num -> System.out.print(num + " -> "));

        System.out.println();

        System.out.print("Iterative BFS: ");
        result_bfs_iterative.forEach(num -> System.out.print(num + " -> "));

        System.out.println();

        System.out.print("Recursive BFS: ");
        result_bfs_recursive.forEach(num -> System.out.print(num + " -> "));
    }


    public static List<Integer> bfs_iterative(Node root){
        List<Integer> result = new ArrayList<>();
        if(root == null) return result;

        Deque<Node> queue = new ArrayDeque<>();
        queue.add(root);

        while (!queue.isEmpty()){
            Node current = queue.poll();
            result.add(current.value);

            // add the children
            if(current.left != null) queue.add(current.left);
            if(current.right != null) queue.add(current.right);
        }

        return result;
    }

    public static List<Integer> bfs_recursive(Node root){
        List<Integer> result = new ArrayList<>();
        if(root == null) return result;

        Deque<Node> queue = new ArrayDeque<>();
        queue.add(root);
        bfs_recursive_helper(result, queue);

        return result;
    }

    private static void bfs_recursive_helper(List<Integer> result, Deque<Node> queue) {
        if(queue.isEmpty()) return;

        Node current = queue.poll(); // remove node from queue
        result.add(current.value); // mark explored

        if(current.left != null) queue.add(current.left);
        if(current.right != null) queue.add(current.right);

        bfs_recursive_helper(result, queue); // perform breath-first search on rest
    }

    public static List<Integer> dfs_recursive(Node root){
        List<Integer> result = new ArrayList<>();
        if(root == null) return result;
        dfs_recursive_helper(root, result);
        return result;
    }

    public static void dfs_recursive_helper(Node root, List<Integer> result){
        if(root == null) return;
        result.add(root.value);
        dfs_recursive_helper(root.left, result);
        dfs_recursive_helper(root.right, result);
    }

    public static List<Integer> dfs_iterative(Node root) {
        List<Integer> result = new ArrayList<>();

        // Implementation goes here
        if(root == null) return result;

        Deque<Node> stack =  new ArrayDeque<>();
        stack.push(root);

        while(!stack.isEmpty()){
            Node current = stack.pop();
            result.add(current.value);

            if(current.right != null) stack.push(current.right);
            if(current.left != null) stack.push(current.left);
        }

        return result;
    }
}