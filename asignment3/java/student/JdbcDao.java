package practise;

import java.util.Collection;

public interface JdbcDao<T,K> {
	Collection<T> getAll();
	T getOne(K key);
	void add(T t);
}
