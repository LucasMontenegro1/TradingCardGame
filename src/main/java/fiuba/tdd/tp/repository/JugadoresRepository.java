package fiuba.tdd.tp.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import fiuba.tdd.tp.model.jugador.Jugador;
import fiuba.tdd.tp.service.CuentaJugador;
import fiuba.tdd.tp.service.JwtService;

@Repository
public class JugadoresRepository {
    
    private final HashMap<String, Jugador> jugadores = new HashMap<>();
    private final JwtService jwtService;

    public JugadoresRepository(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    public Optional<Jugador> solicitador(String authorizationHeader) {
        String jwt = authorizationHeader.substring(7);
        String username = jwtService.extractUsername(jwt);

        Optional<Jugador> unJugador = buscarPorUsername(username);
        return unJugador;
    }

    public List<String> jugadoresRegistrados() {
        return new ArrayList<>(jugadores.keySet());
    }

    public Optional<Jugador> buscarPorUsername(String username) {
        
        if (jugadores.containsKey(username)) {
            return Optional.of(jugadores.get(username));
        }
        
        return Optional.empty();
    }


    public void registrar(String nombreJugador, Jugador jugador) {
        jugadores.put(nombreJugador, jugador);
    }

    public void actualizarUsuario(String usuario, String nuevoUsuario) {
        Jugador jugador = jugadores.get(usuario);
        jugadores.remove(usuario);
        jugador.cambiarUsuario(nuevoUsuario);
        jugadores.put(nuevoUsuario, jugador);
    }

    public boolean nombreDisponible(String usuario) {
        return !jugadores.containsKey(usuario);
    }

    public boolean cuentaExistente(CuentaJugador unaCuenta) {
        if (jugadores.containsKey(unaCuenta.username())) {
            if (jugadores.get(unaCuenta.username()).password().equals(unaCuenta.password())) {
                return true;
            }
        }
        return false;
    }


}
