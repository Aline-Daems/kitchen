package be.technobel.kitchen.pl.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthDTO {

    private String login;
    private String token;
}
