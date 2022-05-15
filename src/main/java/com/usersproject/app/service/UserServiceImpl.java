package com.usersproject.app.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.usersproject.app.entity.User;
import com.usersproject.app.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;

	@Override
	@Transactional(readOnly = true)
	public Iterable<User> findAll() {
		Iterable<User> users = userRepository.findAll();
		return(users);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<User> findAll(Pageable pageable) {
		Page<User> users = userRepository.findAll(pageable);
		return(users);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<User> findById(Long id) {
		Optional<User> user = userRepository.findById(id);
		return(user);
	}

	@Override
	@Transactional
	public User save(User user) {
		User userSaved = userRepository.save(user);
		return(userSaved);
	}

	@Override
	@Transactional
	public void deleteById(Long id) {
		userRepository.deleteById(id);
	}
}