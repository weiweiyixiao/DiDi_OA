public class Trie {
    private static final int size = 26;
    private static final char OFFSET = 'a';
    private TrieNode rootNode = new TrieNode();

    /** Initialize your data structure here. */
    public Trie() {

    }

    private class TrieNode{
        TrieNode[] children = new TrieNode[size];
        boolean isEnd = false;
    }

    /** Inserts a word into the trie. */
    public void insert(String word) {
        TrieNode curr = rootNode;
        //tranverse word to generate a trie path.
        for(int i = 0; i< word.length(); i++){
            if(curr.children[word.charAt(i) - OFFSET] == null){
                curr.children[word.charAt(i) - OFFSET] = new TrieNode();
            }
            curr = curr.children[word.charAt(i) - OFFSET];
        }
        curr.isEnd = true;
    }

    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        TrieNode curr = rootNode;
        for(int i = 0; i < word.length(); i++){
            if(curr.children[word.charAt(i) - OFFSET] == null){
                return false;
            }
            curr = curr.children[word.charAt(i) - OFFSET];
        }
        return curr.isEnd;
    }

    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        TrieNode curr = rootNode;
        for(int i = 0; i < prefix.length(); i++){
            if(curr.children[prefix.charAt(i) - OFFSET] == null){
                return false;
            }
            curr = curr.children[prefix.charAt(i) - OFFSET];
        }
        return true;
    }
}
