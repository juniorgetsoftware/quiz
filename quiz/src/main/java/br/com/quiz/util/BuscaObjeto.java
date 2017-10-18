package br.com.quiz.util;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import br.com.quiz.generic.dao.Buscador;

public class BuscaObjeto {

	public static <T> T comParametroGET(Class<T> clazz, String paramId, Buscador<T> dao) {
		T t = null;
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
				.getRequest();

		if (request.getParameter(paramId) != null) {
			StringBuilder stringId = new StringBuilder(request.getParameter(paramId));
			AES.decrypt(stringId.toString().replace("@", "/").concat("=="));
			stringId = new StringBuilder(AES.getDecryptedString());

			if (stringId != null && stringId.toString().matches("\\d+")) {
				Long id = Long.parseLong(stringId.toString());
				t = dao.buscarPorId(id);
			}

			dao = null;

		}

		return t;
	}
}
