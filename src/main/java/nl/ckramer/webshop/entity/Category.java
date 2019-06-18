package nl.ckramer.webshop.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
public class Category implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "CATEGORY_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "CATEGORY_NAME")
	private String name;

	@Override
    public int hashCode() {
          return new HashCodeBuilder()
                 .append(getId())
                 .toHashCode();
    }

    @Override
    public boolean equals(Object obj) {
          if (obj instanceof Category) {
        	  Category other = (Category) obj;
                 return new EqualsBuilder()
                        .append(getId(), other.getId())
                        .isEquals();
          }
          return false;
    }

	
}
