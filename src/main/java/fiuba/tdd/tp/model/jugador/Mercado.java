package fiuba.tdd.tp.model.jugador;

import java.util.ArrayList;
import java.util.HashMap;

import fiuba.tdd.tp.model.Excepciones.CartaNoEncontrada;

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
                        intercambiador.eliminarCarta(cartaDesada);

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

    public HashMap<String, ArrayList<String>> intercambiosJugador(String jugador) {
        for (Jugador intercambiador : intercambiadores) {
            if (intercambiador.getUsername().equals(jugador)) {
                return intercambiador.intercambiosAbiertos();
            }
        }

        return null;
    }

    public HashMap<String, HashMap<String, ArrayList<String>>> intercambiosDisponibles() {
        HashMap<String, HashMap<String, ArrayList<String>>> intercambios = new HashMap<>();
        for (Jugador intercambiador : intercambiadores) {
            intercambios.put(intercambiador.getUsername(), intercambiador.intercambiosAbiertos());
        }
        
        return intercambios;
    }
}