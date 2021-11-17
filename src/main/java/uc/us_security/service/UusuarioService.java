package uc.us_security.service;

import java.util.List;

import uc.us_security.entity.Usuario;

public interface UusuarioService {
	List<Usuario> readall();
	Usuario create(Usuario al);
	Usuario read(int id);
	void delete(int id);
	Usuario update(Usuario al);
}
