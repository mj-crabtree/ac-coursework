package com.crabtree.customDSA.dataStructures.hashMap;

public interface CustomMap<K, V> {
	public V put(K key, V value);

	public V get(K key);

	public V remove(K key);

	public void clear();

	public boolean contains(K key);

	public int size();
}