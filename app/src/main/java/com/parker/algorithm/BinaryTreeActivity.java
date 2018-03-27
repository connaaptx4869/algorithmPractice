package com.parker.algorithm;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.parker.algorithm.databinding.ActivityBinaryTreeBinding;


/**
 * Author : zhenh.
 * Created by Orz on 2018/3/14 15:40.
 */

public class BinaryTreeActivity extends AppCompatActivity {
    private ActivityBinaryTreeBinding mBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_binary_tree);
        TreeNode<String> stringTreeNode = generateTestTree();

    }

    private TreeNode<String> generateTestTree() {
        TreeNode<String> treeRoot = new TreeNode<>("A");
        treeRoot.leftNode = new TreeNode<>("B");
        treeRoot.rightNode = new TreeNode<>("C");
        treeRoot.leftNode.leftNode = new TreeNode<>("D");
        treeRoot.leftNode.rightNode = new TreeNode<>("E");
        treeRoot.rightNode.leftNode = new TreeNode<>("F");
        treeRoot.rightNode.rightNode = new TreeNode<>("G");
        return treeRoot;
    }

    private class TreeNode<T> {
        private TreeNode<T> leftNode;
        private TreeNode<T> rightNode;
        private T element;

        public TreeNode(T t) {
            this.element = t;
        }
    }
}
