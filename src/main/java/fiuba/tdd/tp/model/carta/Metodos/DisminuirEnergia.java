package fiuba.tdd.tp.model.carta.Metodos;

import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;

import fiuba.tdd.tp.model.Excepciones.MovimientoInvalido;
import fiuba.tdd.tp.model.carta.Carta;
import fiuba.tdd.tp.model.carta.Energia;
import fiuba.tdd.tp.model.jugador.Tablero;
import fiuba.tdd.tp.model.partida.Ejecucion;
import fiuba.tdd.tp.model.turno.Etapa;
import fiuba.tdd.tp.model.zona.Zona;

public class DisminuirEnergia extends MetodoCarta {

    public DisminuirEnergia(ArrayList<Integer> costoDeUso) {
        costo = costoDeUso;
    }

    @Override
    public boolean esAplicableA(Etapa etapa, Zona zona, Deque<Ejecucion> pilaMetodos, 
                                ArrayList<Carta> cartasUsadas, HashMap<Energia, Integer> energiaJugador) {

        return costo.get(0) <= energiaJugador.get(Energia.Fuego) && costo.get(1) <= energiaJugador.get(Energia.Planta) && costo.get(2) <= energiaJugador.get(Energia.Agua);
    }

    @Override
    public void ejecutar(Tablero enJuego, Tablero contrincante, Deque<Ejecucion> pilaMetodos, String jugadorObjetivo,
            ArrayList<Carta> cartasObjetivo, Carta cartaActivada, Energia energia) throws MovimientoInvalido {
        
        if (energia != null) {
            enJuego.disminuirEnergia(energia, 1);
        } else {
            enJuego.disminuirEnergia(Energia.Fuego, costo.get(0));
            enJuego.disminuirEnergia(Energia.Planta, costo.get(1));
            enJuego.disminuirEnergia(Energia.Agua, costo.get(2));
        }
    }
    
}
