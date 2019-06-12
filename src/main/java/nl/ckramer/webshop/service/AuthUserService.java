package nl.ckramer.webshop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nl.ckramer.webshop.dao.AuthUserDao;
import nl.ckramer.webshop.entity.AuthUser;

@Service
public class AuthUserService {

	@Autowired
	private AuthUserDao authUserDao;
	
	public List<AuthUser> findAll() {
		return authUserDao.findAll();
	}

	public void save(AuthUser authUser) {
		authUserDao.save(authUser);
	}

	public void remove(AuthUser authUser) {
		authUserDao.delete(authUser);
	}

}
