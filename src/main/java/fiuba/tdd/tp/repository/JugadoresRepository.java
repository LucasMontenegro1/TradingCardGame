package fiuba.tdd.tp.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import fiuba.tdd.tp.controller.CuentaJugador;
import fiuba.tdd.tp.model.jugador.Jugador;

@Repository
public class JugadoresRepository {
    
    private final HashMap<String, Jugador> jugadores = new HashMap<>();

    public JugadoresRepository() {
        
    }

    public List<String> jugadoresRegistrados() {
        return new ArrayList<>(jugadores.keySet());
    }

    public void registrar(String nombreJugador, String contra) {
        Jugador nuevoRegistro = new Jugador(nombreJugador, contra);
        jugadores.put(nombreJugador, nuevoRegistro);
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
        if (jugadores.containsKey(unaCuenta.usuario())) {
            if (jugadores.get(unaCuenta.usuario()).password().equals(unaCuenta.password())) {
                return true;
            }
        }
        return false;
    }


}
