package fiuba.tdd.tp.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import fiuba.tdd.tp.model.jugador.Jugador;
import fiuba.tdd.tp.repository.JugadoresRepository;

@Service
public class AuthenticationService {

    private final JugadoresRepository repositorio;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthenticationService(JugadoresRepository repositorio, PasswordEncoder passwordEncoder, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.repositorio = repositorio;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public AuthenticationResponse registrar(CuentaJugador request) {

        if (repositorio.nombreDisponible(request.username())) {
            Jugador jugador = new Jugador(request.username(), passwordEncoder.encode(request.password()));
            repositorio.registrar(request.username(), jugador);
            var jwtToken = jwtService.generateToken(jugador);

            return new AuthenticationResponse(jwtToken);
        }

        return null;
    }

    public AuthenticationResponse loguear(CuentaJugador request) {

        Jugador jugador = repositorio.buscarPorUsername(request.username()).orElseThrow(null);

        if (jugador != null) {
            var jwtToken = jwtService.generateToken(jugador);
            return new AuthenticationResponse(jwtToken);
        }
        
        return null;
    }

}