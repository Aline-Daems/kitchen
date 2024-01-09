package be.technobel.kitchen.bl.services.impl;

import be.technobel.kitchen.bl.services.AuthorService;
import be.technobel.kitchen.dal.models.entities.Author;
import be.technobel.kitchen.dal.repositories.AuthorRepository;
import be.technobel.kitchen.pl.config.security.JWTProvider;
import be.technobel.kitchen.pl.dtos.AuthDTO;
import be.technobel.kitchen.pl.forms.AuthorForm;
import be.technobel.kitchen.pl.forms.LoginForm;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    private final AuthenticationManager authenticationManager;
    private final JWTProvider jwtProvider;
    private final PasswordEncoder passwordEncoder;

    public AuthorServiceImpl(AuthorRepository authorRepository, AuthenticationManager authenticationManager, JWTProvider jwtProvider, PasswordEncoder passwordEncoder) {
        this.authorRepository = authorRepository;
        this.authenticationManager = authenticationManager;
        this.jwtProvider = jwtProvider;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void create(AuthorForm form) {

        if(form == null){
            throw  new IllegalArgumentException("Le formulaire ne peut pas être null");
        }

        Author author = new Author();
        author.setName(form.name());
        author.setLastname(form.lastname());
        author.setLogin(form.login());
        author.setPassword(passwordEncoder.encode(form.password()));

        authorRepository.save(author);

    }

    @Override
    public void update(Long id, AuthorForm form) {
        if(form == null){
            throw  new IllegalArgumentException("Le formulaire ne peut pas être null");
        }

        Author author = authorRepository.getOne(id);
        author.setName(form.name());
        author.setLastname(form.lastname());
        author.setLogin(form.login());
        author.setPassword(form.password());
        authorRepository.save(author);

    }

    @Override
    public List<Author> getAll() {
        return authorRepository.findAll();
    }

    @Override
    public Author getOne(Long id) {
        return authorRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public void delete(Long id) {

        authorRepository.deleteById(id);

    }

    @Override
    public AuthDTO login(LoginForm form) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(form.getLogin(), form.getPassword()));

        Author author = authorRepository.findByLogin(form.getLogin()).get();

        String token = jwtProvider.generateToken(author.getUsername(), author);

        return AuthDTO.builder()
                .token(token)
                .login(author.getLogin())
                .build();

    }
}
