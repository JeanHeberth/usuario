package br.com.usuario.business;

import br.com.usuario.business.converter.UsuarioConverter;
import br.com.usuario.business.dto.UsuarioDTO;
import br.com.usuario.infrastructure.entity.Usuario;
import br.com.usuario.infrastructure.exceptions.ConflictException;
import br.com.usuario.infrastructure.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioConverter usuarioConverter;

    public UsuarioDTO salvar(UsuarioDTO usuarioDTO) {
        Usuario usuario = usuarioConverter.paraUsuario(usuarioDTO);
        return usuarioConverter.paraUsuarioDTO(usuarioRepository.save(usuario));

    }


}
