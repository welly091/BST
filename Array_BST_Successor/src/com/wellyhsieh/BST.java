package com.wellyhsieh;

import jdk.nashorn.api.tree.Tree;

import java.util.*;

public class BST {
    int[] preorderArray;
    int[] inorderArray;
    int[] postorderArray;
    TreeNode nodesOfInputArray;


    public BST(int[] inputArray) {
        inorderArray = new int[inputArray.length];
        preorderArray = new int[inputArray.length];
        postorderArray = new int[inputArray.length];

        nodesOfInputArray = arrayToBST(inputArray);

        inorder(nodesOfInputArray);
        System.out.println();

        preorder(nodesOfInputArray);
        System.out.println();

        postorder(nodesOfInputArray);
        System.out.println();


    }

    public TreeNode arrayToBST(int[] inputArray) {
        if (inputArray.length == 0) return null;
        TreeNode numbers = new TreeNode(inputArray[0]);
        for (int i = 1; i < inputArray.length; i++) {
            numbers = addNode(numbers, inputArray[i]);
        }
        return numbers;
    }

    private TreeNode addNode(TreeNode root, int num) {
        if (root.val >= num && root.left == null) root.left = new TreeNode(num);
        else if (root.val < num && root.right == null) root.right = new TreeNode(num);
        else if (root.val >= num && root.left != null) root.left = addNode(root.left, num);
        else if (root.val < num && root.right != null) root.right = addNode(root.right, num);
        return root;
    }

    public boolean isValidBST(TreeNode root) {
        Stack<TreeNode> stack = new Stack();
        double inorder = -Double.MAX_VALUE;

        while (!stack.isEmpty() || root != null) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }

            root = stack.pop();
            //If next element in inorder traversal
            //is small than the previous one
            // that's not BST.
            if (root.val <= inorder) return false;
            inorder = root.val;
            root = root.right;
        }
        return true;
    }

    public int[] inorder(TreeNode root) {
        Stack<TreeNode> stack = new Stack();
        int index = 0;

        while (!stack.isEmpty() || root != null) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            inorderArray[index] = root.val;
            root = root.right;
            index++;
        }
        return inorderArray;
    }

    public String inorderToString(){
//        for (int i = 0; i < inorderArray.length; i++) {
//            System.out.print(inorderArray[i] + " ");
//        }
        return "Inorder: " +Arrays.toString(inorderArray);

    }

    public TreeNode inorderSuccessor(TreeNode root, int p) {
        TreeNode candidate = null;
        TreeNode cur = root;

        while (cur != null) {
            if (cur.val > p) {
                candidate = cur;
                cur = cur.left;
            } else {
                cur = cur.right;
            }
        }
        return candidate;
    }

    public int[] preorder(TreeNode root) {
        int index = 0;
        Stack<TreeNode> stack = new Stack();
        stack.push(root);

        while (!stack.isEmpty()) {
            TreeNode current = stack.pop();
            this.preorderArray[index] = current.val;
            index++;
            if(current.right != null){
                stack.push(current.right);
            }
            if(current.left != null){
                stack.push(current.left);
            }
        }
        return preorderArray;
    }

    public String preorderToString(){
//        for(int i = 0 ; i < preorderArray.length;i++){
//            System.out.print(preorderArray[i] + " ");
//        }
        return "Preorder: " + Arrays.toString(preorderArray);
    }

    public int[] postorder(TreeNode root){
        int index = 0;
        Stack<TreeNode> stack = new Stack();
        stack.push(root);

        int[] temp = new int[postorderArray.length];
        while(!stack.isEmpty()){
            TreeNode top = stack.pop();
            if (top.left != null)
                stack.push(top.left);
            if (top.right != null)
                stack.push(top.right);

            temp[index++] = top.val;
        }
        for(int i = 0 ; i < temp.length; i++){
            postorderArray[i] = temp[temp.length-1-i];
        }
        return postorderArray;
    }

    public String postorderToString(){
//        for(int i = 0 ; i < postorderArray.length; i++) {
//            System.out.print(postorderArray[i] + " ");
//        }
        return "Postorder: " + Arrays.toString(postorderArray);

    }
}





