package br.com.quiz.util;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Projection;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import br.com.quiz.generic.dao.Alias;
import br.com.quiz.generic.dao.DAO;
import br.com.quiz.model.EntidadeGenerica;

public class MyLazyDataModel<T extends EntidadeGenerica> extends LazyDataModel<T> implements Serializable {

	private static final long serialVersionUID = 1705877968617746122L;

	private Class<T> clazz;
	private List<Criterion> restrictions = null;
	private List<Projection> projections = null;
	private List<Alias> aliases = null;
	private Boolean includeDistinctRootEntity = null;

	private DAO<T> dao;

	public MyLazyDataModel(DAO<T> dao, Class<T> clazz, List<Criterion> restrictions, List<Projection> projections,
			List<Alias> aliases, Boolean includeDistinctRootEntity) {
		this.dao = dao;
		this.clazz = clazz;
		this.restrictions = restrictions;
		this.projections = projections;
		this.aliases = aliases;
		this.includeDistinctRootEntity = includeDistinctRootEntity;
	}

	@Override
	public List<T> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {

		setRowCount(dao.tamanhoDaLista(clazz, restrictions, aliases, includeDistinctRootEntity).intValue());

		List<T> list = (List<T>) dao.listarPorDemanda(clazz, first, pageSize, restrictions, projections, sortField,
				sortOrder, aliases, includeDistinctRootEntity);

		return list;
	}

}