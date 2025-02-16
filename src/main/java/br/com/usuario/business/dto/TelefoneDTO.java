package br.com.usuario.business.dto;

import jakarta.persistence.Column;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class TelefoneDTO {

    private String numero;
    private String ddd;
}
