package com.github.takahirom.tree

interface TreeNode {
    // if null, not implemented or root
    var parent: TreeNode?
    val children: List<TreeNode>
}

open class BinaryTreeNode(
    val value: Int,
    var left: BinaryTreeNode? = null,
    var right: BinaryTreeNode? = null,
) : TreeNode {
    override var parent: TreeNode? = null
    override val children: List<BinaryTreeNode>
        get() = listOfNotNull(left, right)

    fun printTree(depth: Int = 0) {
        val indent = " ".repeat(depth)
        println(indent + value)
        left?.printTree(depth + 1)
        right?.printTree(depth + 1)
    }

    override fun toString(): String {
        return "${this::class.java.simpleName}($value)"
    }
}


interface Tree {
    val root: BinaryTreeNode
}

open class BinaryTree(override val root: BinaryTreeNode) : Tree {
    /**
     *   1
     *  2 3
     * 4 5 6 7
     *
     * 1
     * 2, 3
     * 4, 5, 6, 7
     *
     * Time complexity: O(N)
     * Space complexity: O(log N)
     */
    fun depthNodes(): List<List<BinaryTreeNode>> {
        val nodes = mutableListOf<List<BinaryTreeNode>>()
        nodes.add(listOf(root))
        while (true) {
            val addingList = nodes.last().flatMap {
                it.children
            }
            if (addingList.isEmpty()) {
                break
            } else {
                nodes.add(addingList)
            }
        }
        return nodes
    }

    private sealed class CheckBalancingResult {
        class NodeDepths(val depth: Int) : CheckBalancingResult()
        object NotBalancing : CheckBalancingResult()
    }

    /**
     * balancing
     * 1
     *   2
     *     3
     *     4
     *   5
     *
     * not balancing
     * 1
     *   2
     *     3
     *       4
     *       5
     *     6
     *       7
     *   9
     *
     * Time complexity: O(N)
     * Space complexity: O(H) (H = Tree height)
     */
    fun isSelfBalancingTree(): Boolean {
        fun checkBalancing(depth: Int, node: BinaryTreeNode): CheckBalancingResult {
            if (node.children.isEmpty()) return CheckBalancingResult.NodeDepths(depth)
            val results = node.children.map {
                val balancingResult = checkBalancing(depth + 1, it)
                if (balancingResult == CheckBalancingResult.NotBalancing) return CheckBalancingResult.NotBalancing
                balancingResult
            }
            val depths = results
                .filterIsInstance<CheckBalancingResult.NodeDepths>()
                .map { it.depth }
            if (depths.size == 1) return CheckBalancingResult.NodeDepths(depths[0])
            if ((depths.maxOrNull() ?: 0) - (depths.minOrNull() ?: 0) > 1) {
                return CheckBalancingResult.NotBalancing
            } else {
                return CheckBalancingResult.NodeDepths(depths.maxOrNull()!!)
            }
        }
        return checkBalancing(1, root) is CheckBalancingResult.NodeDepths
    }

    /**
     * Time complexity: O(N)
     * Space complexity: O(log N) (If self balancing tree)
     */
    fun isSearchTree(): Boolean {
        fun checkSearchTree(node: BinaryTreeNode?, min: Int, max: Int): Boolean {
            if (node == null) return true
            if (node.value < min || node.value > max) {
                return false
            }
            node.left?.let {
                if (!checkSearchTree(it, min, it.value)) return false
            }
            node.right?.let {
                if (!checkSearchTree(it, it.value, max)) return false
            }
            return true
        }
        return checkSearchTree(root.left, Int.MIN_VALUE, root.value) &&
                checkSearchTree(root.right, root.value, Int.MAX_VALUE)
    }
}


class BinarySearchTree(override val root: BinaryTreeNode) : BinaryTree(root) {
    fun findNodeByValue(value: Int): BinaryTreeNode? {
        var node: BinaryTreeNode? = root
        while (true) {
            when {
                node == null -> return null
                node.value == value -> return node
                node.value > value -> node = node.left
                node.value < value -> node = node.right
            }
        }
    }

    /**
     * Time complexity: O(h)
     * Space complexity: O(1)
     */
    fun getNextNodeInOrderByValue(value: Int): BinaryTreeNode? {
        var node = findNodeByValue(value) ?: return null
        var nextAccessNode = getNextAccessNode(node)
        while (nextAccessNode?.left != null) {
            nextAccessNode = nextAccessNode.left
        }
        return nextAccessNode
    }

    private fun getNextAccessNode(node: BinaryTreeNode): BinaryTreeNode? {
        var currentNode: BinaryTreeNode? = node
        if (currentNode?.right != null) {
            return currentNode?.right
        }
        var previousNode = currentNode
        currentNode = currentNode?.parent as BinaryTreeNode?
        while (true) {
            if (currentNode?.right == previousNode) {
                previousNode = currentNode
                currentNode = currentNode?.parent as BinaryTreeNode?
                continue
            }
            if (currentNode?.right != null) return currentNode?.right
            if (currentNode?.parent == null) {
                return null
            }
            previousNode = currentNode
            currentNode = currentNode.parent as BinaryTreeNode?
        }
    }

    companion object {
        /**
         * Time complexity: O(N)
         * Space complexity: O(N)
         */
        fun of(sortedValues: List<Int>): BinarySearchTree {
            fun buildBinaryTreeNode(first: Int, end: Int): BinaryTreeNode {
                val rangeSize = end - first
                if (rangeSize == 0) {
                    return BinaryTreeNode(sortedValues[first])
                } else if (rangeSize == 1) {
                    val left = BinaryTreeNode(sortedValues[first])
                    val binaryTreeNode = BinaryTreeNode(
                        sortedValues[first + 1],
                        left,
                        null
                    )
                    left.parent = binaryTreeNode
                    return binaryTreeNode
                } else if (rangeSize == 2) {
                    val left = BinaryTreeNode(sortedValues[first])
                    val right = BinaryTreeNode(sortedValues[end])
                    val binaryTreeNode = BinaryTreeNode(
                        sortedValues[first + 1],
                        left,
                        right
                    )
                    left.parent = binaryTreeNode
                    right.parent = binaryTreeNode
                    return binaryTreeNode
                }
                var n = 2
                while (n <= rangeSize) n *= 2
                val centerIndex = n / 2 - 1 + first
                val rootValue = sortedValues[centerIndex]
                val root = BinaryTreeNode(rootValue)
                root.left = buildBinaryTreeNode(first, centerIndex - 1)
                root.right = buildBinaryTreeNode(centerIndex + 1, end)
                root.left?.parent = root
                root.right?.parent = root
                return root
            }
            return BinarySearchTree(buildBinaryTreeNode(0, sortedValues.size - 1))
        }
    }
}