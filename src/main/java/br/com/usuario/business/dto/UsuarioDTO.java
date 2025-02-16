package br.com.usuario.business.dto;


import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsuarioDTO {

    private String nome;
    private String email;
    private String senha;
    private List<EnderecoDTO> enderecos; // EnderecoDTO endereco;
    private List<TelefoneDTO> telefones; // TelefoneDTO telefone;
}
