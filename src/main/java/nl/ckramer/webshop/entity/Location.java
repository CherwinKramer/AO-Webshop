package nl.ckramer.webshop.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Location {

	@Id
	@Column(name = "LOCATION_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "USER_ID")
	private AuthUser authUser;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "COUNTRY_ID")
	private Country country;
	
	@Column(name = "NAME")
	private String name;
	
	@Column(name = "ADDRESS_LINE_1")
	private String addressLine1;
	
	@Column(name = "ADDRESS_LINE_2")
	private String addressLine2;
	
	@Column(name = "POSTAL_CODE")
	private String postalCode;
	
	@Column(name = "PLACE")
	private String place;
	
	@Override
    public int hashCode() {
          return new HashCodeBuilder()
                 .append(getId())
                 .toHashCode();
    }

    @Override
    public boolean equals(Object obj) {
          if (obj instanceof Location) {
        	  Location other = (Location) obj;
                 return new EqualsBuilder()
                        .append(getId(), other.getId())
                        .isEquals();
          }
          return false;
    }

}
