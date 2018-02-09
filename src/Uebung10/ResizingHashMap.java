public class ResizingHashMap<K, V> implements HashMapInterface<K, V> {

	private int size;
	public Pair<K, V>[] buckets;

	@SuppressWarnings("unchecked")
	public ResizingHashMap(int capacity) {
		buckets = (Pair<K, V>[]) new Pair<?, ?>[capacity];
	}

	@Override
	public int size() {
		// TODO b
		return size;
	}

	@Override
	public void insertEntry(int idx, Pair<K, V> entry) {
		if (buckets[idx] == null) {
			buckets[idx] = entry;
		} else {
			Pair next = buckets[idx].nextPairInBucket;
			while (next.nextPairInBucket != null) {
				next = next.nextPairInBucket;

			}
			next.nextPairInBucket = entry;
		}
		size++;
	}

	@Override
	public int getBucketIndex(K key) {
		// TODO d
		return ((key.hashCode() % buckets.length) + buckets.length) % buckets.length;
	}

	@Override
	public Pair<K, V> getEntry(K key) {
		// TODO e
		if (buckets[getBucketIndex(key)] == null) {
			return null;
		} else if (buckets[getBucketIndex(key)].key.equals(key)) {
			return buckets[getBucketIndex(key)];
		} else {
			Pair next = buckets[getBucketIndex(key)].nextPairInBucket;
			while (next.nextPairInBucket != null) {
				if (next.key.equals(key)) {
					return next;
				} else {
					next = next.nextPairInBucket;
				}
			}
			return null;
		}
	}

	@Override
	public V get(K key) {
		// TODO f
		if (buckets[getBucketIndex(key)] == null) {
			return null;
		} else if (buckets[getBucketIndex(key)].key.equals(key)) {
			return buckets[getBucketIndex(key)].value;
		} else {
			Pair<K, V> next = buckets[getBucketIndex(key)].nextPairInBucket;
			while (next.nextPairInBucket != null) {
				if (next.key.equals(key)) {
					// V test = (V) next.getValue();
					return next.value;// test;
				} else {
					next = next.nextPairInBucket;
				}
			}
			return null;
		}
	}

	@Override
	public boolean contains(K key) {
		// TODO g
		if (buckets[getBucketIndex(key)] == null) {
			return false;
		} else if (buckets[getBucketIndex(key)].key.equals(key)) {
			return true;
		} else {
			Pair next = buckets[getBucketIndex(key)].nextPairInBucket;
			while (next.nextPairInBucket != null) {
				if (next.key.equals(key)) {
					return true;
				} else {
					next = next.nextPairInBucket;
				}
			}
			return false;
		}
	}

	@Override
	public boolean put(K key, V value) {
		// TODO h
		if (buckets[getBucketIndex(key)] == null) {
			insertEntry(getBucketIndex(key), new Pair<>(key, value, null));
			return true;
		} else if (buckets[getBucketIndex(key)].key.equals(key)) {
			buckets[getBucketIndex(key)].value = value;
			return false;
		} else {
			Pair next = buckets[getBucketIndex(key)].nextPairInBucket;
			while (next.nextPairInBucket != null) {
				if (next.key.equals(key)) {
					next.value = value;
					return false;
				} else {
					next = next.nextPairInBucket;
				}
			}
			insertEntry(getBucketIndex(key), new Pair<>(key, value, null));
			return false;
		}
	}

	@Override
	public boolean remove(K key) {
		if (buckets[getBucketIndex(key)] == null) {
			return false;
		} else if (key.equals(buckets[getBucketIndex(key)].key)) {
			if (buckets[getBucketIndex(key)].nextPairInBucket != null) {
				buckets[getBucketIndex(key)] = buckets[getBucketIndex(key)].nextPairInBucket;
				size--;
				return true;
			} else {
				buckets[getBucketIndex(key)] = null;
				size--;
				return true;
			}
		} else {
			if (buckets[getBucketIndex(key)].key.equals(key)) {
				buckets[getBucketIndex(
						key)].nextPairInBucket = buckets[getBucketIndex(key)].nextPairInBucket.nextPairInBucket;
				size--;
				return true;
			} else {
				Pair next = buckets[getBucketIndex(key)].nextPairInBucket;
				while (next.nextPairInBucket.nextPairInBucket != null) {
					if (next.nextPairInBucket.key.equals(key)) {
						next.nextPairInBucket = next.nextPairInBucket.nextPairInBucket;
						size--;
						return true;
					}
					return false;
				}
			}
		}
		return false;
	}

	@Override
	@SuppressWarnings("unchecked")
	public void resize(int updatedCapacity) {
		// TODO j
		Pair<K, V>[] bucketsOld = buckets;
		buckets = (Pair<K, V>[]) new Pair<?, ?>[updatedCapacity];
		size = 0;
		for (int i = 0; i < bucketsOld.length; i++) {
			if (bucketsOld[i] != null) {
				insertEntry(getBucketIndex(bucketsOld[i].key), bucketsOld[i]);
				if (bucketsOld[i].nextPairInBucket != null) {
					Pair<K, V> next = bucketsOld[i].nextPairInBucket;
					while(next.nextPairInBucket != null){
						insertEntry(getBucketIndex(next.key), next);
						next = next.nextPairInBucket;
					}
				}
			}
		}
	}
}
