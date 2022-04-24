package com.crabtree.customDSA.dataStructures.hashMap;

public interface CustomMap<K, V> {
	V put(K key, V value);

	V get(K key);

	V remove(K key);

	void clear();

	boolean contains(K key);

	int size();
}