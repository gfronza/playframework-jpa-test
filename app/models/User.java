package models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import play.api.libs.Crypto;
import play.data.validation.Constraints.Email;
import play.data.validation.Constraints.MaxLength;
import play.data.validation.Constraints.MinLength;
import play.data.validation.Constraints.Pattern;
import play.data.validation.Constraints.Required;
import play.db.ebean.Model;

@Entity
public class User extends Model {

	//////////////
	// Enuns

	public enum Profile {
		ADMIN, 	// Control over the system
		USER 	// Normal registered user
	}

	//////////////
	// Structure

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long id;

	@Column(length = 255, nullable = false)
	@Required
	@MinLength(value = 3)
	@MaxLength(value = 255)
	@Pattern(value = "^(?i)([À-ÿa-z\\-]{2,})\\x20((.*?)\\x20)?([À-ÿa-z\\-']{2,})(?-i)$", message="error.pattern.name")
	public String name;

	@Column(length = 255, nullable = false, unique = true)
	@Required
	@Email
	public String email;

	@Column(length = 255, nullable = false)
	@Required
	@MinLength(value = 6)
	@MaxLength(value = 255)
	private String password;
	public String getPassword() { return password; }
	public void setPassword(String newPassword) { password = newPassword; }

	@Column(length = 50, nullable = false)
	@Required
	@MinLength(value = 1)
	@MaxLength(value = 50)
	public String phone;

	@Enumerated(EnumType.STRING)
	public Profile profile;

	@Column(nullable = false, columnDefinition = "boolean default false")
	public Boolean confirmed;

	@Column(length = 250, nullable = true)
	public String recoveryKey;

	// NxN

	@OneToMany(mappedBy = "user")
	public List<Account> accounts;

	//////////////
	// Auxiliar

	@Override
	public void save() {
		if (phone != null) phone = phone.trim();
		if (profile == null) profile = Profile.USER;
		if (confirmed == null) confirmed = false;
		super.save();
	}

	@Override
	public void update() {
		if (phone != null) phone = phone.trim();
		super.update();
	}

	//////////////
	// Finder

	public static Finder<Long, User> finder = new Finder<>(Long.class, User.class);
}