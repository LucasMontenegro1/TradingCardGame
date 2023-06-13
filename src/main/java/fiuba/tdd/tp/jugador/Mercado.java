package fiuba.tdd.tp.jugador;

import java.net.SocketImpl;
import java.util.ArrayList;
import java.util.HashMap;

import fiuba.tdd.tp.Excepciones.CartaNoEncontrada;

public class Mercado {

    ArrayList<Jugador> intercambiadores = new ArrayList<>();

    public void agregarIntercambiador(Jugador jugador) {
        intercambiadores.add(jugador);
    }

    private boolean buscarMatchIntercambio(Jugador jugador, String cartaDispuesta, String cartaDesada) throws CartaNoEncontrada {
        for (Jugador intercambiador : intercambiadores) {

            if (intercambiador != jugador) {
                HashMap<String, ArrayList<String>> intercambios = intercambiador.intercambiosAbiertos();

                if (intercambios.containsKey(cartaDesada)) {
                    ArrayList<String> cartasDeseadas = intercambios.get(cartaDesada);
                    if (cartasDeseadas.contains(cartaDispuesta)) {
    
                        cartasDeseadas.remove(cartaDispuesta);
                        if (cartasDeseadas.isEmpty()) {
                            intercambios.remove(cartaDesada);
                        }
                        
                        jugador.eliminarCarta(cartaDispuesta);
                        jugador.agregarCarta(cartaDesada);
                        intercambiador.agregarCarta(cartaDispuesta);
    
                        return true;
                    }
                }
            }
        }

        return false;
    }

    public void realizarIntercambio(Jugador jugador, String cartaDispuesta, String cartaDeseada) throws CartaNoEncontrada {
        if (!jugador.getCartas().containsKey(cartaDispuesta)) {
            throw new CartaNoEncontrada("No puede realizar el intercambio si no posee la carta");
        }
        
        if (!buscarMatchIntercambio(jugador, cartaDispuesta, cartaDeseada)) {

            jugador.eliminarCarta(cartaDispuesta);

            ArrayList<String> cartasDeseadas = jugador.intercambiosAbiertos().get(cartaDispuesta);
            if (cartasDeseadas == null) {
                cartasDeseadas = new ArrayList<>();
                jugador.intercambiosAbiertos().put(cartaDispuesta, cartasDeseadas);
            }
            cartasDeseadas.add(cartaDeseada);
        }
    }

    public void eliminarIntercambio(Jugador jugador, String cartaDispuesta, String cartaDeseada) {
        ArrayList<String> cartasDeseadas = jugador.intercambiosAbiertos().get(cartaDispuesta);
        if (cartasDeseadas == null) {
            return; 
        }

        cartasDeseadas.remove(cartaDeseada);
        if (cartasDeseadas.isEmpty()) {
            jugador.intercambiosAbiertos().remove(cartaDispuesta);
        }

        jugador.agregarCarta(cartaDispuesta);
    }
}
