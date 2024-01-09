package be.technobel.kitchen.pl.controller;

import be.technobel.kitchen.bl.services.AuthorService;
import be.technobel.kitchen.dal.models.entities.Author;
import be.technobel.kitchen.pl.dtos.AuthorDTO;
import be.technobel.kitchen.pl.forms.AuthorForm;
import be.technobel.kitchen.pl.forms.LoginForm;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/author")
public class AuthorController {

    private AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }
    @PreAuthorize("isAnonymous()")
    @PostMapping("/create")
    public void createAuthor(@RequestBody AuthorForm form){
        authorService.create(form);
    }
    @PreAuthorize("isAnonymous()")
    @PutMapping("/update/{id}")
    public void updateAuthor(@PathVariable Long id, @RequestBody AuthorForm form){
        authorService.update(id,form);
    }
    @PreAuthorize("isAnonymous()")
    @GetMapping("/{id}")
    public ResponseEntity<AuthorDTO> getOne(@PathVariable Long id){
        Author author = authorService.getOne(id);

        return ResponseEntity.ok(AuthorDTO.fromEntity(author));
    }
    @PreAuthorize("isAnonymous()")
    @GetMapping("/all")
    public ResponseEntity<List<AuthorDTO>> getAll(){
        List<Author> authors = authorService.getAll();

        List<AuthorDTO> dtos = authors.stream().map(AuthorDTO::fromEntity).toList();

        return  ResponseEntity.ok(dtos);

    }
    @PreAuthorize("isAnonymous()")
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id) { authorService.delete(id);}

    @PreAuthorize("isAnonymous()")
    @PostMapping("/login")
    public void login (@RequestBody LoginForm form){

        authorService.login(form);
    }


}
