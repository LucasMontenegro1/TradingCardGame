package fiuba.tdd.tp.carta.Metodos;

import java.util.ArrayList;
import java.util.Deque;

import org.apache.tomcat.jni.Sockaddr;

import fiuba.tdd.tp.carta.Carta;
import fiuba.tdd.tp.carta.CartasDisponibles;
import fiuba.tdd.tp.carta.Energia;
import fiuba.tdd.tp.carta.Tipo;
import fiuba.tdd.tp.jugador.Tablero;
import fiuba.tdd.tp.partida.Ejecucion;
import fiuba.tdd.tp.turno.Etapa;
import fiuba.tdd.tp.zona.Zona;
import fiuba.tdd.tp.zona.ZonaDescarte;

public class Resonancia extends MetodoCarta {

    public Resonancia(Tipo tipoCarta, ArrayList<Integer> costoDeUso) {
        tipo = tipoCarta;
        costo = costoDeUso;
    }

    @Override
    public boolean esAplicableA(Etapa etapa, Zona zona, Deque<Ejecucion> pilaMetodos, ArrayList<Carta> cartasUsadas) {
        if (pilaMetodos == null || zona instanceof ZonaDescarte) {
            return false;
        }

        return cartasUsadas.get(cartasUsadas.size()-1).nombre.equals(CartasDisponibles.RESONANCIA.nombre);
    }

    @Override
    public void ejecutar(Tablero enJuego, Tablero contrincante, Deque<Ejecucion> pilaMetodos, 
                            String jugadorObjetivo, ArrayList<Carta> cartasObjetivo, Carta cartaActivada, Energia energia) {

        pilaMetodos.pop();

        for (Carta criatura: cartasObjetivo) {
            criatura.descartar();
        }
    }
}
