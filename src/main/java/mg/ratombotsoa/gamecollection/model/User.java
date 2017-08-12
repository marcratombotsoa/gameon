package mg.ratombotsoa.gamecollection.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;

import com.google.common.base.Function;
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;

@Entity
@Table(name = "user")
public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6977200661390233667L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true, nullable = false)
	private Long id;
	
	@Column(name = "first_name")
	private String firstName;
	
	@Column(name = "name", nullable = false)
	private String name;
	
	@Temporal(TemporalType.DATE)
    @Column(name = "birth_date")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date birthDate;
	
	@Column(name = "user_name", nullable = false)
	private String username;
	
	@Column(name = "password")
	private String password;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "user_game", joinColumns = {
			@JoinColumn(name = "user_id", nullable = false, updatable = false) 
		}, inverseJoinColumns = {
			@JoinColumn(name = "game_id", nullable = false, updatable = false)
		})
	private List<VideoGame> games;
	
	@Transient
	private List<Long> selectedGameIds = Lists.newArrayList();
	
	@Transient
	private String transientPassword;

	public User(Long id, String username) {
		super();
		this.id = id;
		this.username = username;
	}

	public User() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public List<VideoGame> getGames() {
		if (games == null) {
			games = new ArrayList<VideoGame>();
		}
		return games;
	}
	
	public void addGame(VideoGame game) {
		getGames().add(game);
	}

	public void setGames(List<VideoGame> games) {
		this.games = games;
	}
	
	public List<Long> getSelectedGameIds() {
		return selectedGameIds;
	}

	public void setSelectedGameIds(List<Long> selectedGameIds) {
		this.selectedGameIds = selectedGameIds;
	}

	public String getTransientPassword() {
		return transientPassword;
	}

	public void setTransientPassword(String transientPassword) {
		this.transientPassword = transientPassword;
	}

	public void initSelectedGames() {
		Collection<Long> ids = Collections2.transform(getGames(), new Function<VideoGame, Long>() {
			@Override
			public Long apply(VideoGame game) {
				return game.getId();
			}
		});
		this.selectedGameIds = Lists.newArrayList(ids);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + "]";
	}
	
	
}
