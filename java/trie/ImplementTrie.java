package trie;

/**
 * LeetCode 208 - Implement Trie (Prefix Tree).
 */
public class ImplementTrie {
    private static class TrieNode {
        private final TrieNode[] children = new TrieNode[26];
        private boolean isWord;
    }

    private final TrieNode root = new TrieNode();

    public void insert(String word) {
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
        TrieNode node = findNode(word);
        return node != null && node.isWord;
    }

    public boolean startsWith(String prefix) {
        return findNode(prefix) != null;
    }

    private TrieNode findNode(String text) {
        TrieNode current = root;
        for (char c : text.toCharArray()) {
            int idx = c - 'a';
            if (current.children[idx] == null) {
                return null;
            }
            current = current.children[idx];
        }
        return current;
    }
}
