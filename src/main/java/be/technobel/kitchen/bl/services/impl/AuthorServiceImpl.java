package be.technobel.kitchen.bl.services.impl;

import be.technobel.kitchen.bl.services.AuthorService;
import be.technobel.kitchen.dal.models.entities.Author;
import be.technobel.kitchen.dal.repositories.AuthorRepository;
import be.technobel.kitchen.pl.forms.AuthorForm;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
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
        author.setPassword(form.password());

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
}
