package be.technobel.kitchen.pl.dtos;

import be.technobel.kitchen.dal.models.entities.Author;

public record AuthorDTO(

         Long authorId,
         String name,
         String lastname,
         String login,
         String password
) {

    public static AuthorDTO fromEntity (Author author){
        return new AuthorDTO(author.getAuthorId(), author.getName(), author.getLastname(), author.getLogin(), author.getPassword());
    }

}
