package nl.ckramer.webshop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nl.ckramer.webshop.dao.AuthRoleDao;
import nl.ckramer.webshop.entity.AuthRole;

@Service
public class AuthRoleService {

	@Autowired
	private AuthRoleDao authRoleDao;
	
	public List<AuthRole> findAll() {
		return authRoleDao.findAll();
	}

	public void save(AuthRole authRole) {
		authRoleDao.save(authRole);
	}

	public void remove(AuthRole authRole) {
		authRoleDao.delete(authRole);
	}

}
