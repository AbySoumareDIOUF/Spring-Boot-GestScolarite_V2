package uahb.m1gl.gestionscolarite.service;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import uahb.m1gl.gestionscolarite.model.Utilisateur;
import uahb.m1gl.gestionscolarite.repository.UtilisateurRepository;

@Service
public class CustomUserDetailService implements UserDetailsService {
    private final UtilisateurRepository utilisateurRepository;

    public CustomUserDetailService(UtilisateurRepository utilisateurRepository) {
        this.utilisateurRepository = utilisateurRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        Utilisateur utilisateur = utilisateurRepository.findByUsername(username)
                .orElseThrow(()->new UsernameNotFoundException("Utilisateur introuvable"));
        return new User(
                utilisateur.getUsername(),
                utilisateur.getPassword(),
                utilisateur.getRoles().stream()
                        .map(role -> new SimpleGrantedAuthority(role.getNom()))
                        .toList()
        );
    }

}
