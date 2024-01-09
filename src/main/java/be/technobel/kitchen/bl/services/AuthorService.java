package be.technobel.kitchen.bl.services;

import be.technobel.kitchen.dal.models.entities.Author;
import be.technobel.kitchen.dal.models.entities.Recipe;
import be.technobel.kitchen.pl.dtos.AuthDTO;
import be.technobel.kitchen.pl.forms.AuthorForm;
import be.technobel.kitchen.pl.forms.LoginForm;
import be.technobel.kitchen.pl.forms.RecipeForm;

import java.util.List;

public interface AuthorService {

    void create(AuthorForm form);
    void update(Long id, AuthorForm form);
    List<Author> getAll();
    Author getOne(Long id);
    void delete(Long id);

    AuthDTO login(LoginForm form);

}
