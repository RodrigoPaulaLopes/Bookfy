package api.crud.bookfy.auth;

import api.crud.bookfy.auth.dto.DadosAutenticacaoDto;
import api.crud.bookfy.infra.security.TokenService;
import api.crud.bookfy.infra.security.dto.MostrarTokenDto;
import api.crud.bookfy.usuarios.entity.Usuarios;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("authentication")
public class AutenticacaoController {

    @Autowired
    private AuthenticationManager auth;
    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity autenticacao(@RequestBody @Valid DadosAutenticacaoDto dados) {
        var authToken = new UsernamePasswordAuthenticationToken(dados.email(), dados.senha());
        var authentication = this.auth.authenticate(authToken);


        var tokenJWT = this.tokenService.gerarToken((Usuarios) authentication.getPrincipal());
        return ResponseEntity.ok(new MostrarTokenDto(tokenJWT));
    }

}
