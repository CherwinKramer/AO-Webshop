package nl.ckramer.webshop.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
//@Table(name = "auth_users")
public class AuthUser implements UserDetails{

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "USER_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "USERNAME")
	private String username;

	@Column(name = "PASSWORD")
	private String password;

	@Column(name = "EMAIL")
	private String email;

	@Column(name = "PHONE")
	private String phone;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ROLE_ID")
	private AuthRole role;

	@Column(name = "ENABLED")
	private Boolean enabled = true;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> gal = new ArrayList<>();
		gal.add(new SimpleGrantedAuthority(role.getRole()));
		return gal;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return enabled;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return enabled;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return enabled;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return enabled;
	}
}
