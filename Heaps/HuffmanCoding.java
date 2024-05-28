//loss less data compression 

/* i. pass the string data

ii. Make frequency map from it 

iii. for every key in frequency map, creat a node and insert that node in a min heap/ priority queue
Node data : char data, int cost/frequency, Node left, Node right
if data = a, cost will be the frequency of a

iv. remove 2 element from the heap and combine them 

v. add these 2 removed value to a new node left and right.

vi. 
*/
import java.util.*;
class HuffmanCode {
	HashMap<Character, String>  encoder;
	HashMap<String, Character>  decoder;

	private class Node implements Comparable<Node> {
		Character data; 
		int cost; //frequency
		Node left; 
		Node right; 

		public Node(Character data, int cost) {
			this.data = data;
			this.cost = cost;
			this.left = null;
			this.right = null;
		}

		@Override
		public int compareTo(Node other) {
			return this.cost - other.cost;
		} 

	}

	public HuffmanCode(String feeder) throws Exception {
		HashMap<Character, Integer> frequencyMap = new HashMap<>();
		for(int i = 0; i < feeder.length(); i++){
			char cc = feeder.charAt(i);
			frequencyMap.put(cc, frequencyMap.getOrDefault(cc, 0) + 1);
		}

		PriorityQueue<Node> minHeap = new PriorityQueue<Node>();
		HashSet<Node> nodes = new HashSet<>();
		for(Map.Entry<Character,Integer> entry : frequencyMap.entrySet()) {
			Node n = new Node (entry.getKey(), entry.getValue());
			minHeap.add(n);	
		}


		while(minHeap.size() != 1) {
			Node first = minHeap.remove();
			Node second = minHeap.remove();

			Node newNode = new Node('\0', first.cost + second.cost);
			newNode.left = first;
			newNode.right = second;

			minHeap.add(newNode);
		}

		Node finalTree = minHeap.remove();

		this.encoder = new HashMap<>();
		this.decoder = new HashMap<>();

		this.initEncoderDecoder(finalTree, "");
	}

	private void initEncoderDecoder(Node n, String output) {
		if(n == null) {
			return;
		}
		if(n.left == null && n.right == null) {
			this.encoder.put(n.data, output);
			this.decoder.put(output, n.data);
		}
		initEncoderDecoder(n.left, output + '0');
		initEncoderDecoder(n.right, output + '1');
	}


	public String encode(String source) {
		String ans = "";
		for (int i = 0; i < source.length(); i++) {
			ans = ans + encoder.get(source.charAt(i));
		}
		return ans;
	}

	public String decode(String encodedMessage) {
		String toReturn = "";

		String key = "";
		for(int i = 0; i < encodedMessage.length(); i++) {
			key = key + encodedMessage.charAt(i);
			if(decoder.containsKey(key)) {
				toReturn += decoder.get(key);
				key = "";
			}

		}

		return toReturn;

	}
}


class HuffmanCoding {
	public static void main(String[] args) throws Exception {
		HuffmanCode h = new HuffmanCode("abbbcgfd");
		String cs = h.encode("abbbcgfd");
		System.out.println(cs);
		String ds = h.decode(cs);
		System.out.println(ds);
	}
}