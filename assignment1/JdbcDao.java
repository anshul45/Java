package jdbcpractice;

import java.util.Collection;

public interface JdbcDao<T, K> {

	Collection<T> getAll();

	T getone(K key);

	void add(T t);
}
