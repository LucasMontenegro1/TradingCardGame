package fiuba.tdd.tp.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import fiuba.tdd.tp.model.jugador.Jugador;
import fiuba.tdd.tp.repository.JugadoresRepository;

@Service
public class AuthenticationService {

    private final JugadoresRepository repositorio;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationService(JugadoresRepository repositorio, PasswordEncoder passwordEncoder, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.repositorio = repositorio;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    public AuthenticationResponse registrar(CuentaJugador request) {

        Jugador jugador = new Jugador(request.username(), passwordEncoder.encode(request.password()));
        repositorio.registrar(request.username(), jugador);
        var jwtToken = jwtService.generateToken(jugador);

        return new AuthenticationResponse(jwtToken);
    }

    public AuthenticationResponse loguear(CuentaJugador request) {
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                request.username(), 
                request.password()
            )
        );

        Jugador jugador = repositorio.buscarPorUsername(request.username()).orElseThrow(null);
        var jwtToken = jwtService.generateToken(jugador);
        return new AuthenticationResponse(jwtToken);
    }

}
