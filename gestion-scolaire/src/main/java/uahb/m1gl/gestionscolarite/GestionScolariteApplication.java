package uahb.m1gl.gestionscolarite;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.security.crypto.password.PasswordEncoder;
import uahb.m1gl.gestionscolarite.model.Role;
import uahb.m1gl.gestionscolarite.model.Utilisateur;
import uahb.m1gl.gestionscolarite.repository.RoleRepository;
import uahb.m1gl.gestionscolarite.repository.UtilisateurRepository;

@SpringBootApplication//(exclude = {DataSourceAutoConfiguration.class})
public class GestionScolariteApplication implements CommandLineRunner {
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private UtilisateurRepository utilisateurRepository;

	public static void main(String[] args) {

		SpringApplication.run(GestionScolariteApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
		Role role1 = roleRepository.save(new Role(0, "admin"));
		Role role2 = roleRepository.save(new Role(0, "user"));

		Utilisateur utilisateur= new Utilisateur();
		utilisateur.setUsername("aby");
		utilisateur.setPassword(passwordEncoder.encode("passer"));
		utilisateur.getRoles().add(role1);
		utilisateurRepository.save(utilisateur);

		Utilisateur utilisateur1= new Utilisateur();
		utilisateur1.setUsername("biba");
		utilisateur1.setPassword(passwordEncoder.encode("passer"));
		utilisateur1.getRoles().add(role2);
		utilisateurRepository.save(utilisateur1);

	}
}
