package kh.com.kshrd.ipcam.service;

import java.util.List;

import org.springframework.stereotype.Service;

public interface CrudService<T> {

	T findOne(int id);

	List<T> findAll();

	boolean remove(int id);

	boolean update(T object);

	boolean save(T object);
}
