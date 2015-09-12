package com.tvshows.service.impl;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import com.tvshows.model.BaseEntity;
import com.tvshows.service.BaseEntityCrudService;

public abstract class SimpleBaseEntityCrudServiceImpl<T extends BaseEntity, R extends JpaRepository<T, Long>>
		implements BaseEntityCrudService<T> {

	protected abstract R getRepository();

	public T save(T entity) {
		return getRepository().save(entity);
	}

	public List<T> save(Iterable<T> entities) {
		return getRepository().save(entities);
	}

	public T saveAndFlush(T entity) {
		return getRepository().saveAndFlush(entity);
	}

	public List<T> findAll() {
		return getRepository().findAll();
	}

	public Page<T> findAll(Pageable pageable) {
		return getRepository().findAll(pageable);
	}

	public List<T> findAll(Sort sort) {
		return getRepository().findAll(sort);
	}

	public List<T> findAll(Iterable<Long> ids) {
		return getRepository().findAll(ids);
	}

	public List<T> findAll(Specification<T> spec) {
		return findAll();
	}

	public Page<T> findAll(Specification<T> spec, Pageable pageable) {
		return null;
	}

	public List<T> findAll(Specification<T> spec, Sort sort) {
		return findAll();
	}

	public long count() {
		return getRepository().count();
	}

	public long count(Specification<T> spec) {
		return 0;
	}

	public T findOne(Long id) {
		return getRepository().findOne(id);
	}

	public T findOne(Specification<T> spec) {
		return null;
	}

	public boolean exists(Long id) {
		return getRepository().exists(id);
	}

	public void delete(Long id) {
		getRepository().delete(id);
	}

	public void delete(T entity) {
		getRepository().delete(entity);
	}

	public void delete(Iterable<T> entities) {
		getRepository().delete(entities);
	}

	public void deleteAll() {
		getRepository().deleteAll();
	}

}
