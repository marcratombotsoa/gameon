package mg.ratombotsoa.gamecollection.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import mg.ratombotsoa.gamecollection.util.DateUtil;

@Entity
@Table(name = "video_game")
public class VideoGame extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4086340553276309314L;
	
	@Column(name = "publisher", nullable = false)
	private String publisher;
	
	@ManyToOne
	@JoinColumn(name = "console_id")
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
