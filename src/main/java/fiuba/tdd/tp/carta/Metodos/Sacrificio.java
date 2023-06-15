package fiuba.tdd.tp.carta.Metodos;

import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;

import fiuba.tdd.tp.carta.Carta;
import fiuba.tdd.tp.carta.Energia;
import fiuba.tdd.tp.carta.Tipo;
import fiuba.tdd.tp.jugador.Tablero;
import fiuba.tdd.tp.partida.Ejecucion;
import fiuba.tdd.tp.turno.Etapa;
import fiuba.tdd.tp.turno.EtapaPrincipal;
import fiuba.tdd.tp.zona.Zona;
import fiuba.tdd.tp.zona.ZonaMano;

public class Sacrificio extends MetodoCarta {

    final Integer CANTIDAD  = 3;

    public Sacrificio(ArrayList<Integer> costoDeUso) {
        tipo = Tipo.Accion;
        costo = costoDeUso;
    }
  
    @Override
    public boolean esAplicableA(Etapa etapa, Zona zona, Deque<Ejecucion> pilaMetodos) {
        return etapa instanceof EtapaPrincipal && zona instanceof ZonaMano;
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
