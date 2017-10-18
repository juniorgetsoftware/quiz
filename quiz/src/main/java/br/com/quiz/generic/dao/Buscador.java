package br.com.quiz.generic.dao;

import java.io.Serializable;

public interface Buscador<T> {

	T buscarPorId(Serializable id);

}
