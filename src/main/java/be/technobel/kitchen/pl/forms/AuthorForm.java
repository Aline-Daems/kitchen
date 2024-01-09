package be.technobel.kitchen.pl.forms;

import lombok.Data;


public record AuthorForm(
        String name,
        String lastname,
        String login,
        String password) {
}
