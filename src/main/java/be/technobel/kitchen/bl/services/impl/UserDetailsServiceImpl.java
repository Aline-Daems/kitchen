package be.technobel.kitchen.bl.services.impl;

import be.technobel.kitchen.dal.repositories.AuthorRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final AuthorRepository authorRepository;

    public UserDetailsServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return authorRepository.findByLogin(username)
                .orElseThrow(()-> new UsernameNotFoundException("Pas trouvé"));
    }
}
