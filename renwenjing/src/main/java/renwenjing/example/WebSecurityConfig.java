package renwenjing.example;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;

import renwenjing.example.model.UserService;



@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.authorizeRequests()
		.antMatchers("/blog","/bloguserdetail/**","/blogcategorylist/**","/register","/css/**", "/js/**","/blog-image/**","/images/**").permitAll()
		.anyRequest().authenticated()
		.and()
		.formLogin()
		.loginPage("/login")
		.defaultSuccessUrl("/homepage")
		.usernameParameter("username") //リクエストパラメータのname属性を明示
		.passwordParameter("password")
		.permitAll()
		.and()
		.logout()
		.logoutSuccessUrl("/login")
		.permitAll();
	}

	public static UserDetailsManager manager = null;
	@Autowired
	private UserService userService;

	@Override
	@Bean
	public UserDetailsService userDetailsService() {
		List<UserDetails> users = userService.getAllAccounts().stream().map(
				account -> User.withDefaultPasswordEncoder()
				.username(account.getUsername())
				.password(account.getPassword())
				.roles("USER")
				.build()
				).toList();

		manager = new InMemoryUserDetailsManager(users);

		return manager;
	}

	public static void addUser(String username, String password) {
		manager.createUser(User.withDefaultPasswordEncoder()
				.username(username)
				.password(password)
				.roles("USER")
				.build());
	}
}