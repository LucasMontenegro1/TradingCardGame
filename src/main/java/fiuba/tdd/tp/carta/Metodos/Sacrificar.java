package fiuba.tdd.tp.carta.Metodos;

import java.util.ArrayList;
import java.util.Deque;

import fiuba.tdd.tp.carta.Carta;
import fiuba.tdd.tp.carta.Energia;
import fiuba.tdd.tp.etapa.Etapa;
import fiuba.tdd.tp.etapa.EtapaPrincipal;
import fiuba.tdd.tp.tablero.Tablero;
import fiuba.tdd.tp.zona.Zona;
import fiuba.tdd.tp.zona.ZonaMano;

public class Sacrificar extends MetodoCarta {

    @Override
    public boolean esAplicableA(Etapa etapa, Zona zona, Deque<MetodoCarta> pilaMetodos) {
        return etapa instanceof EtapaPrincipal && zona instanceof ZonaMano;
    }

    @Override
    public void ejecutar(Tablero enJuego, Tablero contrincante, Deque<MetodoCarta> pilaMetodos, String jugadorObjetivo,
            Carta carta, Energia energia) {
        ArrayList<Carta> cartas = enJuego.cartas;
        cartas.forEach((cartaUso -> {
            if(carta.nombreCarta() == cartaUso.nombreCarta()){
                //costo = cartaUso.getCostoInvocacion()
            }
        }));
        /// sumar 3 veces el costo de invocacion
    }

}
