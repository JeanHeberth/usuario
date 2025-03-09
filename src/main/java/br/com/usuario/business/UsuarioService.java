package br.com.usuario.business;

import br.com.usuario.business.converter.UsuarioConverter;
import br.com.usuario.business.dto.EnderecoDTO;
import br.com.usuario.business.dto.TelefoneDTO;
import br.com.usuario.business.dto.UsuarioDTO;
import br.com.usuario.infrastructure.entity.Endereco;
import br.com.usuario.infrastructure.entity.Telefone;
import br.com.usuario.infrastructure.entity.Usuario;
import br.com.usuario.infrastructure.exceptions.ConflictException;
import br.com.usuario.infrastructure.exceptions.ResourceNotFoundException;
import br.com.usuario.infrastructure.repository.EnderecoRepository;
import br.com.usuario.infrastructure.repository.TelefoneRepository;
import br.com.usuario.infrastructure.repository.UsuarioRepository;
import br.com.usuario.infrastructure.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioConverter usuarioConverter;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final EnderecoRepository enderecoRepository;
    private final TelefoneRepository telefoneRepository;

    public UsuarioDTO salvar(UsuarioDTO usuarioDTO) {
        emailExiste(usuarioDTO.getEmail());
        usuarioDTO.setSenha(passwordEncoder.encode(usuarioDTO.getSenha()));
        Usuario usuario = usuarioConverter.paraUsuario(usuarioDTO);
        return usuarioConverter.paraUsuarioDTO(usuarioRepository.save(usuario));

    }


    public void emailExiste(String email) {
        try {
            boolean emailExistente = verificaEmailExistente(email);
            if (emailExistente) {
                throw new ConflictException("Email já cadastrado " + email);
            }
        } catch (ConflictException e) {
            throw new ConflictException("Email já cadastrado ", e.getCause());
        }

    }

    public boolean verificaEmailExistente(String email) {
        return usuarioRepository.existsByEmail(email);
    }

    public UsuarioDTO buscarUsuarioPorEmail(String email) {
        try {
            return usuarioConverter.paraUsuarioDTO(
                    usuarioRepository.findByEmail(email)
                            .orElseThrow(() -> new ResourceNotFoundException("Email nao encontrado: " + email)));
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("Email nao encontrado: " + email);
        }
    }

    public void deletarPorEmail(String email) {
        try {
            usuarioRepository.deleteByEmail(email);
        } catch (Exception e) {
            throw new RuntimeException("Usuário nao encontrado: " + email);
        }
    }

    public UsuarioDTO atualizarDadosUsuario(String token, UsuarioDTO usuarioDTO) {
        String email = jwtUtil.extrairEmailDoToken(token.substring(7));

        //criando a encriptografia da senha
        usuarioDTO.setSenha(usuarioDTO.getSenha() != null ? passwordEncoder.encode(usuarioDTO.getSenha()) : null);

        Usuario usuarioEntity = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário nao encontrado: " + email));

        Usuario usuario = usuarioConverter.updateUsuario(usuarioEntity, usuarioDTO);
        return usuarioConverter.paraUsuarioDTO(usuarioRepository.save(usuario));
    }

    public EnderecoDTO atualizarEndereco(Long idEndereco, EnderecoDTO enderecoDTO) {

        Endereco enderecoEntity = enderecoRepository.findById(idEndereco)
                .orElseThrow(() -> new ResourceNotFoundException("Endereco nao encontrado: " + idEndereco));


        Endereco endereco = usuarioConverter.updateEndereco(enderecoDTO, enderecoEntity);

        enderecoRepository.save(endereco);

        return usuarioConverter.paraEnderecoDTO(enderecoRepository.save(endereco));

    }

    public TelefoneDTO atualizarTelefone(Long idTelefone, TelefoneDTO telefoneDTO) {

        Telefone telefoneEntity = telefoneRepository.findById(idTelefone)
                .orElseThrow(() -> new ResourceNotFoundException("Telefone nao encontrado: " + idTelefone));

        Telefone telefone = usuarioConverter.updateTelefone(telefoneDTO, telefoneEntity);
        telefoneRepository.save(telefone);

        return usuarioConverter.paraTelefoneDTO(telefoneRepository.save(telefone));
    }

    public EnderecoDTO cadastrarEndereco(String token, EnderecoDTO enderecoDTO) {

        String email = jwtUtil.extrairEmailDoToken(token.substring(7));

        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Email nao encontrado: " + email));

        Endereco endereco = usuarioConverter.paraEnderecoEntity(enderecoDTO, usuario.getId());
        return usuarioConverter.paraEnderecoDTO(enderecoRepository.save(endereco));

    }

    public TelefoneDTO cadastrarTelefone(String token, TelefoneDTO telefoneDTO) {

        String email = jwtUtil.extrairEmailDoToken(token.substring(7));

        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Email nao encontrado: " + email));

        Telefone telefone = usuarioConverter.paraTelefoneEntity(telefoneDTO, usuario.getId());
        return usuarioConverter.paraTelefoneDTO(telefoneRepository.save(telefone));
    }

}


