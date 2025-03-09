package br.com.usuario.controller;

import br.com.usuario.business.UsuarioService;
import br.com.usuario.business.dto.EnderecoDTO;
import br.com.usuario.business.dto.TelefoneDTO;
import br.com.usuario.business.dto.UsuarioDTO;
import br.com.usuario.infrastructure.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    @PostMapping
    public ResponseEntity<UsuarioDTO> salvar(@RequestBody UsuarioDTO usuarioDTO) {
        return ResponseEntity.ok().body(usuarioService.salvar(usuarioDTO));
    }

    @PostMapping("/login")
    public String login(@RequestBody UsuarioDTO usuarioDTO) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        usuarioDTO.getEmail(),
                        usuarioDTO.getSenha())
        );
        return "Bearer " + jwtUtil.generateToken(authentication.getName());
    }

    @GetMapping
    public ResponseEntity<UsuarioDTO> buscarUsuarioPorEmail(@RequestParam("email") String email) {
        return ResponseEntity.ok().body(usuarioService.buscarUsuarioPorEmail(email));
    }

    @DeleteMapping("/{email}")
    public ResponseEntity<Void> deletarUsuario(@PathVariable String email) {
        usuarioService.deletarPorEmail(email);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<UsuarioDTO> atualizarDados(@RequestBody UsuarioDTO usuarioDTO, @RequestHeader("Authorization") String token) {
        return ResponseEntity.ok().body(usuarioService.atualizarDadosUsuario(token, usuarioDTO));
    }

    @PutMapping("/endereco")
    public ResponseEntity<EnderecoDTO> atualizarEndereco(@RequestBody EnderecoDTO enderecoDTO, @RequestParam("id") Long id) {
        return ResponseEntity.ok().body(usuarioService.atualizarEndereco(id, enderecoDTO));
    }

    @PutMapping("/telefone")
    public ResponseEntity<TelefoneDTO> atualizarTelefone(@RequestBody TelefoneDTO telefoneDTO, @RequestParam("id") Long id) {
        return ResponseEntity.ok().body(usuarioService.atualizarTelefone(id, telefoneDTO));
    }

    @PostMapping("/endereco")
    public ResponseEntity<EnderecoDTO> cadastrarEndereco(@RequestBody EnderecoDTO enderecoDTO, @RequestHeader("Authorization") String token) {
        return ResponseEntity.ok().body(usuarioService.cadastrarEndereco(token, enderecoDTO));
    }

    @PostMapping("/telefone")
    public ResponseEntity<TelefoneDTO> cadastrarTelefone(@RequestBody TelefoneDTO telefoneDTO, @RequestHeader("Authorization") String token) {
        return ResponseEntity.ok().body(usuarioService.cadastrarTelefone(token, telefoneDTO));
    }
}
