package br.com.quiz.generic.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.primefaces.model.SortOrder;

import br.com.quiz.model.EntidadeGenerica;
import br.com.quiz.model.Status;
import br.com.quiz.model.filter.Filter;
import br.com.quiz.util.MyLazyDataModel;
import br.com.quiz.util.Transacional;

public abstract class DAO<T extends EntidadeGenerica> implements Serializable {

	private static final long serialVersionUID = -3812355503300426470L;

	@Inject
	private EntityManager entityManager;
	private Class<T> clazz;

	public DAO(Class<T> clazz) {
		this.clazz = clazz;
	}

	@Transacional
	public void salvar(T entity) {
		entityManager.persist(entity);
	}

	@Transacional
	public void atualizar(T entity) {
		entityManager.merge(entity);
	}

	@Transacional
	public T buscarPorId(Serializable id) {
		return entityManager.find(clazz, id);
	}

	@Transacional
	public void excluir(Serializable id) {
		T entity = this.buscarPorId(id);
		entityManager.remove(entity);
	}

	@Transacional
	public void atualizarStatus(T t) {
		t.setStatus(Status.negarStatus(t.getStatus()));
		atualizar(t);
	}

	public T criarQuery(String query) {
		return entityManager.createQuery(query, clazz).getSingleResult();
	}

	public List<T> listarComQuery(String query) {
		return entityManager.createQuery(query, clazz).getResultList();
	}

	public List<T> listar(boolean ordemAscendente, String... propertiersOrders) {

		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<T> cq = builder.createQuery(clazz);
		Root<T> root = cq.from(clazz);
		List<javax.persistence.criteria.Order> orders = new ArrayList<>();

		for (String p : propertiersOrders) {
			if (ordemAscendente) {
				orders.add(builder.asc(root.get(p)));
			} else {
				orders.add(builder.desc(root.get(p)));
			}
		}

		cq.orderBy(orders);

		return entityManager.createQuery(cq).getResultList();
	}

	public List<T> listarAutocompletar(String orderBy, boolean orderAsc, String propertyName, String value) {
		ArrayList<Criterion> restricoes = new ArrayList<>();

		if (value != null && !value.isEmpty()) {
			restricoes.add(Restrictions.like(propertyName, value, MatchMode.ANYWHERE));
		}

		return listarPorAtributosERestricoes(clazz, orderBy, orderAsc, restricoes, null);
	}

	public T buscarPorAtributo(Class<T> clazz, String chave, Object valor) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<T> cq = builder.createQuery(clazz);
		Root<T> root = cq.from(clazz);
		Predicate predicate = builder.equal(root.get(chave), valor);
		cq.select(root);
		cq.where(predicate);
		TypedQuery<T> query = entityManager.createQuery(cq);
		return query.getSingleResult();
	}

	public T buscarPorAtributo(String... args) {
		return null;
	}

	public Long tamanhoDaLista(Class<?> clazz, List<?> restricoes, List<Alias> aliases,
			Boolean includeDistinctRootEntity) {

		Long size = null;
		try {
			Session session = entityManager.unwrap(Session.class);
			Criteria criteria = session.createCriteria(clazz);
			if (includeDistinctRootEntity != null && includeDistinctRootEntity.booleanValue())
				criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			if (aliases != null && !aliases.isEmpty()) {
				for (Iterator<Alias> iterator = aliases.iterator(); iterator.hasNext();) {

					Alias alias = (Alias) iterator.next();
					if (alias.getTipoDeJoin() != null)
						criteria.createAlias(alias.getAtributo(), alias.getAliasAtributo(), alias.getTipoDeJoin());
					else
						criteria.createAlias(alias.getAtributo(), alias.getAliasAtributo());
				}

			}
			if (restricoes != null && restricoes != null) {
				Criterion criterion;
				for (Iterator<?> iterator1 = restricoes.iterator(); iterator1.hasNext(); criteria.add(criterion))
					criterion = (Criterion) iterator1.next();

			}
			criteria.setProjection(Projections.rowCount());
			size = (Long) criteria.uniqueResult();
			Transaction transaction = session.beginTransaction();
			transaction.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return size;
	}

	@SuppressWarnings("unchecked")
	public List<T> listarPorDemanda(Class<T> clazz, Integer startPage, Integer maxPage, List<Criterion> restricoes,
			List<Projection> projecoes, String campoOrdenacao, SortOrder tipoOrdem, List<Alias> aliases,
			Boolean includeDistinctRootEntity) {

		List<T> list = null;

		try {
			Session session = entityManager.unwrap(Session.class);

			Criteria criteria = session.createCriteria(clazz);
			if (includeDistinctRootEntity != null && includeDistinctRootEntity.booleanValue())
				criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			if (aliases != null && !aliases.isEmpty()) {
				for (Iterator<?> iterator = aliases.iterator(); iterator.hasNext();) {
					Alias alias = (Alias) iterator.next();
					if (alias.getTipoDeJoin() != null)
						criteria.createAlias(alias.getAtributo(), alias.getAliasAtributo(), alias.getTipoDeJoin());
					else
						criteria.createAlias(alias.getAtributo(), alias.getAliasAtributo());
				}

			}
			if (restricoes != null && !restricoes.isEmpty()) {
				Criterion criterion;
				for (Iterator<Criterion> iterator = restricoes.iterator(); iterator.hasNext(); criteria.add(criterion))
					criterion = (Criterion) iterator.next();

			}
			if (projecoes != null && !projecoes.isEmpty()) {
				Projection projection;
				for (Iterator<Projection> iterator2 = projecoes.iterator(); iterator2.hasNext(); criteria
						.setProjection(projection))
					projection = (Projection) iterator2.next();

			}
			if (campoOrdenacao != null && tipoOrdem != null)
				criteria.addOrder(
						tipoOrdem.equals(SortOrder.ASCENDING) ? Order.asc(campoOrdenacao) : Order.desc(campoOrdenacao));
			criteria.setFirstResult(startPage.intValue());
			criteria.setMaxResults(maxPage.intValue());
			list = criteria.list();
			Transaction transaction = session.beginTransaction();
			transaction.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
		}

		return list;
	}

	@SuppressWarnings("unchecked")
	public List<T> listarPorAtributosERestricoes(Class<T> clazz, String orderBy, boolean orderAsc,
			List<Criterion> restrictions, Collection<Alias> aliases) {
		List<T> list = null;
		try {
			Session session = entityManager.unwrap(Session.class);
			Transaction transaction = session.beginTransaction();
			Criteria criteria = session.createCriteria(clazz);

			if (aliases != null && !aliases.isEmpty()) {
				Iterator<Alias> i = aliases.iterator();
				Alias aux = null;
				while (i.hasNext()) {
					aux = (Alias) i.next();
					if (aux.getTipoDeJoin() != null)
						criteria.createAlias(aux.getAtributo(), aux.getAliasAtributo(), aux.getTipoDeJoin());
					else
						criteria.createAlias(aux.getAtributo(), aux.getAliasAtributo());
				}
			}

			if (orderBy != null)
				criteria.addOrder(orderAsc ? Order.asc(orderBy) : Order.desc(orderBy));

			if (restrictions != null && !restrictions.isEmpty()) {
				for (int i = 0; i < restrictions.size(); i++) {
					criteria.add(restrictions.get(i));
				}
			}

			list = criteria.list();
			transaction.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
		}

		return list;
	}

	@SuppressWarnings("unchecked")
	public T buscarPorAtributosERestricoes(Class<T> clazz, List<Criterion> restrictions, Collection<Alias> aliases) {
		T t = null;
		try {
			Session session = entityManager.unwrap(Session.class);
			Criteria criteria = session.createCriteria(clazz);

			if (aliases != null && !aliases.isEmpty()) {
				Iterator<Alias> i = aliases.iterator();
				Alias aux = null;
				while (i.hasNext()) {
					aux = (Alias) i.next();
					if (aux.getTipoDeJoin() != null)
						criteria.createAlias(aux.getAtributo(), aux.getAliasAtributo(), aux.getTipoDeJoin());
					else
						criteria.createAlias(aux.getAtributo(), aux.getAliasAtributo());
				}
			}

			if (restrictions != null && !restrictions.isEmpty()) {
				for (int i = 0; i < restrictions.size(); i++) {
					criteria.add(restrictions.get(i));
				}
			}

			t = (T) criteria.uniqueResult();
		} catch (HibernateException e) {
			e.printStackTrace();
		}

		return t;
	}

	public MyLazyDataModel<T> filtrar(DAO<T> dao, Filter filter) {
		return new MyLazyDataModel<>(dao, clazz, filter.restricoes(), filter.projecoes(), filter.aliases(),
				filter.usarDistinct());
	}

}
