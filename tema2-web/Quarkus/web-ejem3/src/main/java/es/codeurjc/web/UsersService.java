package es.codeurjc.web;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Singleton;

@ApplicationScoped
public class UsersService {

	public int getNumUsers() {
		return 5;
	}
}
