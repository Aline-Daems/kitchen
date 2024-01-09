package be.technobel.kitchen.pl.controller;

import be.technobel.kitchen.bl.services.AuthorService;
import be.technobel.kitchen.dal.models.entities.Author;
import be.technobel.kitchen.pl.dtos.AuthorDTO;
import be.technobel.kitchen.pl.forms.AuthorForm;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/author")
public class AuthorController {

    private AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @PostMapping("/create")
    public void createAuthor(@RequestBody AuthorForm form){
        authorService.create(form);
    }

    @PutMapping("/update/{id}")
    public void updateAuthor(@PathVariable Long id, @RequestBody AuthorForm form){
        authorService.update(id,form);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuthorDTO> getOne(@PathVariable Long id){
        Author author = authorService.getOne(id);

        return ResponseEntity.ok(AuthorDTO.fromEntity(author));
    }

    @GetMapping("/all")
    public ResponseEntity<List<AuthorDTO>> getAll(){
        List<Author> authors = authorService.getAll();

        List<AuthorDTO> dtos = authors.stream().map(AuthorDTO::fromEntity).toList();

        return  ResponseEntity.ok(dtos);

    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id) { authorService.delete(id);}



}
