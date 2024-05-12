import java.util.*;
class MapUsingLinkedLint {
	private class Entity {
		String key;
		String value;
		Entity next;
		public Entity(String key, String value) {
			this.key = key;
			this.value = value;
			next = null;
		}
	}



	private Entity[] entities;
	MapUsingLinkedLint() {
		entities = new Entity[100];
	}

	public void put(String key, String value) {
		int hash = Math.abs(key.hashCode() % entities.length); 
		Entity temp = entities[hash];
		if(temp == null) {
			entities[hash] = new Entity(key, value);
			return;
		}
		while(temp.next != null) {
			if(temp.key.equals(key)) {
				temp.value = value;
				return;
			}
			temp = temp.next;
		}
		if(temp.key.equals(key)) {
				temp.value = value;
				return;
		}
		temp.next = new Entity(key, value);
	}

	public String get(String key) {
		int hash = Math.abs(key.hashCode() % entities.length); 
		Entity temp = entities[hash];
		while(temp != null && !temp.key.equals(key)) {
			temp = temp.next;
		} 
		return temp != null ? temp.value : null;
	}


	public void remove(String key) {
		int hash = Math.abs(key.hashCode() % entities.length); 
		Entity head = entities[hash];
		if(head == null) {
			return;
		}
		if(head.key.equals(key)) {
			entities[hash] = head.next;
			return;
		}
		while(head.next != null) {
			if(head.next.key.equals(key)) {
				head.next = head.next.next;
			}
			head = head.next;
		}
	}

}


class MapsUsingLinkedList <K, V> {
	private class Entity {
		K key;
		V value;

		public Entity(K key, V value) {
			this.key = key;
			this.value = value;
		}
	}

	ArrayList<LinkedList<Entity>> list; 
	int size = 0;
	public float f = 0.5f;
	MapsUsingLinkedList() {
		list = new ArrayList<LinkedList<Entity>>();
		for (int i = 0; i < 10; i++ ) {
			list.add(new LinkedList<Entity>());
			
		}
	}


	void put(K key, V value)  {
		int hash = Math.abs(key.hashCode() % list.size());
		LinkedList<Entity> entities = list.get(hash);
		for(Entity entity : entities) {
			if(entity.key.equals(key)) {
				entity.value = value;
				return;
			}
		}

		if((float) size / list.size() > f) {
			reHash();
		}
		entities.add(new Entity(key, value));
		size++;
	}

	private void reHash() {
		System.out.println("REHASHING");
		ArrayList<LinkedList<Entity>> old = list;
		list = new ArrayList<>();
		size = 0;
		for(int i = 0; i < old.size() * 2; i++) {
			list.add(new LinkedList<>());
		}

		for(LinkedList<Entity> e : old) {
			for(Entity entry : e) {
				put(entry.key, entry.value);
			}
		}
	}

	public V get(K key) {
		int hash = Math.abs(key.hashCode() % list.size());
		LinkedList<Entity> entities = list.get(hash);
		for(Entity e : entities) {
			if(e.key.equals(key)) {
				return e.value;
			}
		}
		return null;
	}

	public void remove(K key) {
		int hash = Math.abs(key.hashCode() % list.size());
		LinkedList<Entity> entities = list.get(hash);
		Entity target = null;
		for(Entity e : entities) {
			if(e.key.equals(key)) {
				target = e;
				break;
			}
		}
		entities.remove(target);
		size--;
 	}

 	public boolean containsKey(K key) {
 		return get(key) != null;
 	}

 	@Override
 	public String toString() {
 		StringBuilder sb = new StringBuilder();
 		sb.append("{");
 		for(LinkedList<Entity> entities : list) {
 			for(Entity entity : entities) {
 				sb.append(entity.key);
 				sb.append(" = ");
 				sb.append(entity.value);
 				sb.append(" , ");
 			}
 		}
 		sb.append("}");

 		return sb.toString();

 	}


}

public class HashMaps2 {
	public static void main(String[] args) {
		MapUsingLinkedLint map = new MapUsingLinkedLint();
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
		System.out.println(map);
	}
}