package fiuba.tdd.tp.model.carta.Metodos;

import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;

import fiuba.tdd.tp.model.carta.Carta;
import fiuba.tdd.tp.model.carta.Energia;
import fiuba.tdd.tp.model.carta.Tipo;
import fiuba.tdd.tp.model.jugador.Tablero;
import fiuba.tdd.tp.model.partida.Ejecucion;
import fiuba.tdd.tp.model.turno.Etapa;
import fiuba.tdd.tp.model.zona.Zona;

public class Sacrificio extends MetodoCarta {

    final Integer CANTIDAD  = 3;

    public Sacrificio() {
        tipo = Tipo.Accion;
    }
  
    @Override
    public boolean esAplicableA(Etapa etapa, Zona zona, Deque<Ejecucion> pilaMetodos, ArrayList<Carta> cartasUsadas, HashMap<Energia, Integer> energiaJugador) {
        if (etapa == null || zona == null){
            return false;
        }
        return etapa.sacrificio() && zona.sacrificio();
    }
    
    @Override
    public void ejecutar(Tablero enJuego, Tablero contrincante, Deque<Ejecucion> pilaMetodos, 
                            String jugadorObjetivo, ArrayList<Carta> cartasObjetivo, Carta cartaActivada, Energia energia) {
        
        for (Carta carta : cartasObjetivo) {
            modificarEnergia(enJuego.energia, Energia.Fuego, carta.costoDeInvocacion.get(Energia.Fuego));
            modificarEnergia(enJuego.energia, Energia.Planta, carta.costoDeInvocacion.get(Energia.Planta));
            modificarEnergia(enJuego.energia, Energia.Agua, carta.costoDeInvocacion.get(Energia.Agua));
        }
    }

    private void modificarEnergia(HashMap<Energia, Integer> energias,Energia energia, Integer costo){
        Integer cantidadEnergia = energias.get(energia) + CANTIDAD * costo;

        energias.put(energia, cantidadEnergia);
    }
}
