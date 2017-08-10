package mg.ratombotsoa.gamecollection.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import mg.ratombotsoa.gamecollection.util.DateUtil;

@Entity
@Table(name = "console")
public class Console extends AbstractEntity implements Comparable<Console> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6484393573509793574L;
	
	@Column(name = "manufacturer", nullable = false, length = 500)
	private String manufacturer;
	
	@Column(name = "number_bits")
	private Integer numberOfBits;
	
	public Console() {
		super();
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public Integer getNumberOfBits() {
		return numberOfBits;
	}

	public void setNumberOfBits(Integer numberOfBits) {
		this.numberOfBits = numberOfBits;
	}

	@Override
	public String toString() {
		return "\n{manufacturer:" + manufacturer + ", name:" + getName() + ", release date:"
				+ DateUtil.formatDate(getReleaseDate()) + "}";
	}

	@Override
	public int compareTo(Console o) {
		return this.getName().compareTo(o.getName());
	}
}
