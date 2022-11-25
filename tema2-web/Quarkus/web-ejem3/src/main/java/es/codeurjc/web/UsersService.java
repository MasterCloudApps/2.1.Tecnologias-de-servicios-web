package es.codeurjc.web;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UsersService {

	public int getNumUsers() {
		return 5;
	}
}
