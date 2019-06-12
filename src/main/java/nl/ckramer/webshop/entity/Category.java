package nl.ckramer.webshop.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
//@Table(name = "category")
public class Category implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "CATEGORY_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "CATEGORY_NAME")
	private String name;

	@Override
	public String toString() {
		return name;
	}
}
