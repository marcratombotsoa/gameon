package mg.ratombotsoa.gamecollection.model;

import mg.ratombotsoa.gamecollection.util.DateUtil;

public class VideoGame extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4086340553276309314L;
	
	private String publisher;
	private Console console;
	
	public VideoGame() {
		super();
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public Console getConsole() {
		return console;
	}

	public void setConsole(Console console) {
		this.console = console;
	}

	@Override
	public String toString() {
		return "\n{publisher:" + publisher + ", console:" + console.getName() + ", name:" + getName() + ", release date: "
				+ DateUtil.formatDate(getReleaseDate()) + "}";
	}
}
