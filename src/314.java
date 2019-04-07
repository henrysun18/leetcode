import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.TreeMap;

class BinaryTreeVerticalOrderTraversal {
	/**
	 * Definition for a binary tree node.
	 * public class TreeNode {
	 *     int val;
	 *     TreeNode left;
	 *     TreeNode right;
	 *     TreeNode(int x) { val = x; }
	 * }
	 */

	public List<List<Integer>> verticalOrder(TreeNode root) {
		//preorder traversal, with col starting at 0 and go to negative if we traverse left
		//have a map to keep track of currlevel -> list
		//in the end, we will put these lists together by the order of their level (negative to positive)
		//we can have a res linkedlist if we don't want to use a treemap to sort the keys, which would require lgn time,
		//but we'll have o(n) time anyways, so just use a treemap for simplicity
		if (root == null) return new ArrayList<>();

		Map<Integer, List<Integer>> map = new TreeMap<>();

		//bfs preorder
		Queue<TreeNode> nodes = new LinkedList<>();
		Queue<Integer> levels = new LinkedList<>();
		nodes.add(root);
		levels.add(0);
		while (!nodes.isEmpty()) {
			TreeNode currNode = nodes.poll();
			int currLevel = levels.poll();
			//handle curr node
			if (map.get(currLevel) == null) {
				map.put(currLevel, new ArrayList<>());
			}
			map.get(currLevel).add(currNode.val);

			if (currNode.left != null) {
				nodes.add(currNode.left);
				levels.add(currLevel-1);
			}
			if (currNode.right != null) {
				nodes.add(currNode.right);
				levels.add(currLevel+1);
			}
		}

		List<List<Integer>> res = new LinkedList<>();
		for (int sortedKey : map.keySet()) {
			res.add(map.get(sortedKey));
		}
		return res;
	}


    /*second attempt, also bad since i used dfs instead of bfs, which fails ex 3
    public List<List<Integer>> verticalOrder(TreeNode root) {
        //preorder traversal, with col starting at 0 and go to negative if we traverse left
        //have a map to keep track of currlevel -> list
        //in the end, we will put these lists together by the order of their level (negative to positive)
        //we can have a res linkedlist if we don't want to use a treemap to sort the keys, which would require lgn time,
        //but we'll have o(n) time anyways, so just use a treemap for simplicity

        Map<Integer, List<Integer>> map = new TreeMap<>();

        traverse(root, 0, map);

        List<List<Integer>> res = new LinkedList<>();
        for (int sortedKey : map.keySet()) {
            res.add(map.get(sortedKey));
        }
        return res;
    }

    private void traverse(TreeNode root, int currLevel, Map<Integer, List<Integer>> map) {
        if (root == null) return;

        if (map.get(currLevel) == null) {
            map.put(currLevel, new ArrayList<>());
        }
        map.get(currLevel).add(root.val);

        //PREORDER! VERY IMPORTANT
        traverse(root.left, currLevel-1, map);

        traverse(root.right, currLevel+1, map);
    }*/




		//pretty bad solution now that i think about it, since we want top to bottom it really should be preorder traversal
    /*public List<List<Integer>> verticalOrder(TreeNode root) {
        //maybe do an inorder traversal, first node in traversal gets index 0 in res
        //dfs function should return the index/level of root after traversal
        //dfs function should take as param the current level as backup
        List<List<Integer>> res = new ArrayList<>();

        if (root != null) {
            traverseAndReturnNodeLevel(root, 0, res);
        }

        return res;
    }

    private int traverseAndReturnNodeLevel(TreeNode root, int currLevel, List<List<Integer>> res) {
        if (root.left != null) {
            //let leftmost child decide what currLevel for this node should be
            currLevel = traverseAndReturnNodeLevel(root.left, currLevel-1, res) + 1;
        }

        //initially, the first node we see will have currLevel < 0 for a big tree, so this handles that case
        if (currLevel < 0) currLevel = 0;

        if (res.size() == currLevel) {
            res.add(new ArrayList<>());
        }
        res.get(currLevel).add(root.val);

        if (root.right != null) {
            traverseAndReturnNodeLevel(root.right, currLevel+1, res);
        }

        return currLevel;
    }*/
}
