package mg.ratombotsoa.gamecollection.model;

public class Console extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6484393573509793574L;
	
	private String manufacturer;
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
}
