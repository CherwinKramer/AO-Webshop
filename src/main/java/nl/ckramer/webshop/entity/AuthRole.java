package nl.ckramer.webshop.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
//@Table(name = "auth_roles")
public class AuthRole implements Serializable{

	private static final long serialVersionUID = 1L;

	public final static String ADMIN = "ADMIN";
	public final static String USER = "USER";

	@Id
	@Column(name = "ROLE_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "ROLE_NAME")
	private String name;
	
	@ManyToMany(mappedBy = "roles")
	private List<AuthUser> users = new ArrayList<>();
	
	@Override
	public String toString() {
		return name;
	}

}
