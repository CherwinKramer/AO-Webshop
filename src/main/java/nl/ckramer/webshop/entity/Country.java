package nl.ckramer.webshop.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
//@Table(name = "country")
public class Country {

	@Id
	@Column(name = "COUNTRY_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "NAME")
	private String name;
	
	@Column(name = "CODE")
	private String code;
	
	@Column(name = "CODE_SHORT")
	private String codeShort;

	@Override
    public int hashCode() {
          return new HashCodeBuilder()
                 .append(getId())
                 .toHashCode();
    }

    @Override
    public boolean equals(Object obj) {
          if (obj instanceof Country) {
        	  Country other = (Country) obj;
                 return new EqualsBuilder()
                        .append(getId(), other.getId())
                        .isEquals();
          }
          return false;
    }
	
}
