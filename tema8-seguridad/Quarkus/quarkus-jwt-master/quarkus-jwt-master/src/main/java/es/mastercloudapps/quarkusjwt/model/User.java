package es.mastercloudapps.quarkusjwt.model;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor @AllArgsConstructor @ToString @EqualsAndHashCode(callSuper = false)
public class User {

	public String username;
	public String password;
	public Set<Role> roles;

	// this is just an example, you can load the user from the database (via PanacheEntityBase)
	public static User findByUsername(String username) {

		//if using Panache pattern (extends or PanacheEntity PanacheEntityBase)
		//return find("username", username).firstResult();

		Map<String, User> data = new HashMap<>();

		//username:passwowrd -> user:user
		data.put("user", new User("user", "cBrlgyL2GI2GINuLUUwgojITuIufFycpLG4490dhGtY=", Collections.singleton(Role.USER)));

		//username:passwowrd -> admin:admin
		data.put("admin", new User("admin", "dQNjUIMorJb8Ubj2+wVGYp6eAeYkdekqAcnYp+aRq5w=", Collections.singleton(Role.ADMIN)));

		if (data.containsKey(username)) {
			return data.get(username);
		} else {
			return null;
		}
	}

}