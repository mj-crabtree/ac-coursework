package com.crabtree.customDSA.dataStructures.hashMap;

import java.util.Arrays;

public class HashMap<K, V> implements CustomMap<K, V> {

	private HashMap<K, V> collection = null;

	public HashMap(Type type, int size) {
		if (type == Type.CHAINING) {
			collection = null;
		}
		else if (type == Type.OPEN_ADDRESSING) {
			collection = new OpenAddressingHashMap<K, V>(size);
		}
	}

	public HashMap(Type type) {
		if (type == Type.CHAINING) {
			collection = null;
		}
		else if (type == Type.OPEN_ADDRESSING) {
			collection = new OpenAddressingHashMap<K, V>();
		}
	}

	private HashMap() {
	}

	@Override
	public V put(K key, V value) {
		return collection.put(key, value);
	}

	@Override
	public V get(K key) {
		return collection.get(key);
	}

	@Override
	public V remove(K key) {
		return collection.remove(key);
	}

	@Override
	public void clear() {
		collection.clear();
	}

	@Override
	public boolean contains(K key) {
		return (get(key) != null);
	}

	@Override
	public int size() {
		return collection.size();
	}

	@Override
	public String toString() {
		return collection.toString();
	}

	public enum Type {CHAINING, OPEN_ADDRESSING}

	private static class OpenAddressingHashMap<K, V> extends HashMap<K, V> {

		private final float loadFactor = 0.6f;
		private final int minimumSize = 512;
		private int key = -1;
		private HashMap.HashMapKeyValuePair<K, V>[] collection = null;
		private int size = 0;

		public OpenAddressingHashMap(int size) {
			initialiseCollection(size);
		}

		public OpenAddressingHashMap() {
			initialiseCollection(minimumSize);
		}

		private static int getLargerSize(int input) {
			return input << 1;
		}

		private static int getSmallerSize(int input) {
			return input >> 1 >> 1;
		}

		@Override
		public V put(K key, V value) {
			return put(new HashMapKeyValuePair<K, V>(key, value));
		}

		@Override
		public V get(K key) {
			int index = indexOf(key);
			HashMapKeyValuePair<K, V> pair = collection[index];

			if (pair == null) {
				return null;
			}

			if (pair.key.equals(key)) {
				return pair.value;
			}


			int start = getNextIndex(index);

			while (start != index) {
				pair = collection[start];

				if (pair == null) {
					return null;
				}

				if (pair.key.equals(key)) {
					return pair.value;
				}

				start = getNextIndex(start);
			}
			return null;
		}

		@Override
		public V remove(K key) {
			int index = indexOf(key);
			HashMapKeyValuePair<K, V> previousEntry = null;
			HashMapKeyValuePair<K, V> pair = collection[index];

			if (pair != null && pair.key.equals(key)) {
				previousEntry = collection[index];
				collection[index] = null;
				size--;

				int newLoadFactor = (int) (size / loadFactor);
				int smallerSize = getSmallerSize(collection.length);

				if (newLoadFactor < smallerSize && smallerSize > minimumSize) {
					reduceCollectionSize();
				}

				return previousEntry.value;
			}

			int start = getNextIndex(index);
			while (start != index) {
				pair = collection[start];
				if (pair != null && pair.key.equals(key)) {
					previousEntry = collection[start];
					collection[start] = null;
					size--;

					int loadFactored = (int) (size / loadFactor);
					int smallerSize = getSmallerSize(collection.length);
					if (loadFactored < smallerSize && smallerSize > minimumSize) {
						reduceCollectionSize();
					}

					return previousEntry.value;
				}
				start = getNextIndex(start);
			}
			return null;
		}

		@Override
		public void clear() {
			Arrays.fill(collection, null);
			size = 0;
		}

		@Override
		public boolean contains(K key) {
			return (get(key) != null);
		}

		@Override
		public int size() {
			return size;
		}

		private V put(HashMap.HashMapKeyValuePair<K, V> newPair) {
			V previousEntry = null;
			int index = indexOf(newPair.key);
			HashMap.HashMapKeyValuePair<K, V> pair = collection[index];

			if (pair == null) {
				collection[index] = newPair;
				size++;

				int maxSize = (int) (loadFactor * collection.length);
				if (size >= maxSize) {
					increaseCollectionSize();
				}

				return previousEntry;
			}

			if (pair.key.equals(newPair.key)) {
				previousEntry = pair.value;
				pair.value = newPair.value;
				return previousEntry;
			}

			int start = getNextIndex(index);
			while (start != index) {
				pair = collection[start];

				if (pair == null) {
					collection[start] = newPair;
					size++;

					int maxSize = (int) (loadFactor * collection.length);
					if (size >= maxSize) increaseCollectionSize();

					return previousEntry;
				}

				if (pair.key.equals(newPair.key)) {
					previousEntry = pair.value;
					pair.value = newPair.value;
					return previousEntry;
				}

				start = getNextIndex(start);
			}
			return null;
		}

		private void initialiseCollection(int currentLength) {
			int newLength = getLargerSize(currentLength);
			collection = new HashMap.HashMapKeyValuePair[newLength];
			size = 0;
			key = newLength;
		}

		private void increaseCollectionSize() {
			HashMap.HashMapKeyValuePair<K, V>[] previousCollection = this.collection;

			int largerSize = getLargerSize(collection.length);
			initialiseCollection(largerSize);

			populateNewCollection(previousCollection);
		}

		private void reduceCollectionSize() {
			HashMapKeyValuePair<K, V>[] previousCollection = this.collection;

			int smallerSize = getSmallerSize(collection.length);
			initialiseCollection(smallerSize);

			// Re-hash old data
			populateNewCollection(previousCollection);
		}

		private void populateNewCollection(HashMap.HashMapKeyValuePair<K, V>[] previousCollection) {
			for (HashMapKeyValuePair<K, V> pair : previousCollection) {
				if (pair != null) this.put(pair);
			}
		}

		private int getNextIndex(int input) {
			int index = input + 1;
			if (index >= collection.length) {
				index = 0;
			}
			return index;
		}

		private int indexOf(K key) {
			int index = Math.abs(key.hashCode()) % this.key;
			if (index >= collection.length) index = index - ((index / collection.length) * collection.length);
			return index;
		}
	}

	private static final class HashMapKeyValuePair<K, V> {
		private K key = null;
		private V value = null;

		public HashMapKeyValuePair(K key, V value) {
			this.key = key;
			this.value = value;
		}

		@Override
		public int hashCode() {
			return 31 * (this.key.hashCode());
		}

		@Override
		public boolean equals(Object obj) {
			if (obj == null) {
				return false;
			}
			if (!(obj instanceof HashMapKeyValuePair)) {
				return false;
			}

			var pair = (HashMapKeyValuePair<K, V>) obj;
			return key.equals(pair.key);
		}
	}
}