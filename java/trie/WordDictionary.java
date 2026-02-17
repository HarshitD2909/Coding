package trie;

/**
 * LeetCode 211 - Design Add and Search Words Data Structure.
 */
public class WordDictionary {
    private static class TrieNode {
        private final TrieNode[] children = new TrieNode[26];
        private boolean isWord;
    }

    private final TrieNode root = new TrieNode();

    public void addWord(String word) {
        TrieNode current = root;
        for (char c : word.toCharArray()) {
            int idx = c - 'a';
            if (current.children[idx] == null) {
                current.children[idx] = new TrieNode();
            }
            current = current.children[idx];
        }
        current.isWord = true;
    }

    public boolean search(String word) {
        return dfs(word, 0, root);
    }

    private boolean dfs(String word, int index, TrieNode node) {
        if (node == null) {
            return false;
        }
        if (index == word.length()) {
            return node.isWord;
        }

        char c = word.charAt(index);
        if (c == '.') {
            for (TrieNode child : node.children) {
                if (child != null && dfs(word, index + 1, child)) {
                    return true;
                }
            }
            return false;
        }

        return dfs(word, index + 1, node.children[c - 'a']);
    }
}
