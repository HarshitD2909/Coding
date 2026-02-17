package trie;

import java.util.ArrayList;
import java.util.List;

/**
 * LeetCode 212 - Word Search II.
 */
public class WordSearchII {
    private static class TrieNode {
        private final TrieNode[] children = new TrieNode[26];
        private String word;
    }

    private final int[] rowDelta = {-1, 0, 1, 0};
    private final int[] colDelta = {0, 1, 0, -1};

    public List<String> findWords(char[][] board, String[] words) {
        TrieNode root = buildTrie(words);
        List<String> result = new ArrayList<>();

        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[0].length; col++) {
                backtrack(board, row, col, root, result);
            }
        }

        return result;
    }

    private TrieNode buildTrie(String[] words) {
        TrieNode root = new TrieNode();
        for (String word : words) {
            TrieNode current = root;
            for (char c : word.toCharArray()) {
                int idx = c - 'a';
                if (current.children[idx] == null) {
                    current.children[idx] = new TrieNode();
                }
                current = current.children[idx];
            }
            current.word = word;
        }
        return root;
    }

    private void backtrack(char[][] board, int row, int col, TrieNode node, List<String> result) {
        if (row < 0 || row >= board.length || col < 0 || col >= board[0].length) {
            return;
        }

        char c = board[row][col];
        if (c == '#') {
            return;
        }

        TrieNode next = node.children[c - 'a'];
        if (next == null) {
            return;
        }

        if (next.word != null) {
            result.add(next.word);
            next.word = null;
        }

        board[row][col] = '#';
        for (int i = 0; i < 4; i++) {
            backtrack(board, row + rowDelta[i], col + colDelta[i], next, result);
        }
        board[row][col] = c;
    }
}
