class MapUsingHash {
	// used in variable assignment in memory, netwroking, cryptography etc
	// imagine we are woking with arrrays in hashmaps, we first find the hashcode of the key on the hashmap, then after getting the hashcode we put the key into that index of the array, if the hashcpde is to large we perform hashing, hashing give the smaller value for the hashcode, nd then we put the value to that particluar index of array, we time we have to deal with collison after getting hashed value, 
	// we deal with collion in 2 different ways, i. chaining, ii. open addressing
	/* it is implemented using array of linked list - chaining concept is valid here
	 each index of array which again has a linked list is called bucket 
	 
	*/

	private class Entity {
		String key;
		String value;

		public Entity(String key, String value) {
			this.key = key;
			this.value = value;
		}
	}



	private Entity[] entities;
	MapUsingHash() {
		entities = new Entity[100];
	}

	public void put(String key, String value) {
		int hash = Math.abs(key.hashCode() % entities.length); 
		entities[hash] = new Entity(key, value); // if this will find same hash value it will over ride it 
	}

	public String get(String key) {
		int hash = Math.abs(key.hashCode() % entities.length); 
		if(entities[hash] != null && entities[hash].key.equals(key)) {
			return entities[hash].value;
		} 
		return null;
	}


	public void remove(String key) {
		int hash = Math.abs(key.hashCode() % entities.length); 
		if(entities[hash] != null && entities[hash].key.equals(key)) {
			entities[hash] = null;
		} 
	}

}

class HashMaps {
	public static void main(String[] args) {
		MapUsingHash map = new MapUsingHash();
		map.put("hehe", "22");
		map.put("h42", "23");
		map.put("hdfsfe", "24");
		map.put("htree", "25");
		map.put("hgr", "26");
		System.out.println(map.get("hehe"));
		System.out.println(map.get("h42"));
		System.out.println(map.get("hgr"));
		map.remove("hgr");
		System.out.println(map.get("hgr"));
		

		
		
	}
}