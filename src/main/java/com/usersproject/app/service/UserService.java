package com.usersproject.app.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.usersproject.app.entity.User;

public interface UserService {
	
	/**
	 * Este método se encarga de consultar todos los objetos de tipo usuario
	 * @return Iterable
	 */
	public Iterable<User> findAll();
	
	/**
	 * Este método se encarga de consultar todos los objetos de tipo usuario con paginación
	 * @param pageable - Número de página
	 * @return Page
	 */
	public Page<User> findAll(Pageable pageable);
	
	/**
	 * Este método se encarga de consultar un objeto de tipo usuario por id
	 * @param id - Identificador único del objeto de tipo usuario
	 * @return Optional
	 */
	public Optional<User> findById(Long id);
	
	/**
	 * Este método se encarga de crear un objeto de tipo usuario
	 * @param user - Objeto de tipo usuario
	 * @return User
	 */
	public User save(User user);

	/**
	 * Este método se encarga de eliminar un objeto de tipo usuario
	 * @param id - Identificador único del objeto de tipo usuario
	 */
	public void deleteById(Long id);
}