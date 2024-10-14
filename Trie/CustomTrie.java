class Node {
    Node[] links = new Node[26];
    boolean flag = false;

    boolean containsKey(char c) {
        return links[c - 'a'] != null;
    }

    void put(char ch, Node node) {
        links[ch - 'a'] = node;
    }

    Node get(char ch) {
        return links[ch - 'a']; 
    }

    void setEnd() {
        flag = true;
    }

    boolean isEnd() {
        return flag;
    }
}


class CustomTrie {
    private Node root;

    CustomTrie() {
        root = new Node();

    }

    void insert(String word) {
        Node node = root;
        for(int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if(!node.containsKey(c)) {
                node.put(c, new Node());
            }
            node = node.get(c);
        }
        node.setEnd();

    }

    boolean search(String s) {
        Node node = root;
        for(char c : s.toCharArray()) {
            if(!node.containsKey(c)) {
                return false;
            }
            node = node.get(c);
        }

        return node.isEnd();
    }

    boolean startsWith(String s) {
        Node node = root;
        for(char c : s.toCharArray()) {
            if(!node.containsKey(c)){
                return false;
            }
            node = node.get(c);
        }

        return true;
    }
    
	
}