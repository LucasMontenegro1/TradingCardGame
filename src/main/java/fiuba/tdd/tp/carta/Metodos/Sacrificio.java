package fiuba.tdd.tp.carta.Metodos;

import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;

import fiuba.tdd.tp.carta.Carta;
import fiuba.tdd.tp.carta.Energia;
import fiuba.tdd.tp.carta.Tipo;
import fiuba.tdd.tp.etapa.Etapa;
import fiuba.tdd.tp.etapa.EtapaPrincipal;
import fiuba.tdd.tp.partida.Ejecucion;
import fiuba.tdd.tp.tablero.Tablero;
import fiuba.tdd.tp.zona.Zona;
import fiuba.tdd.tp.zona.ZonaMano;

public class Sacrificio extends MetodoCarta {

    final Integer CANTIDAD  = 3;

    public Sacrificio(ArrayList<Integer> costoDeUso) {
        tipo = Tipo.Accion;
        costo = costoDeUso;
    }
  
    @Override
    public boolean esAplicableA(Etapa etapa, Zona zona, Deque<MetodoCarta> pilaMetodos) {
        return etapa instanceof EtapaPrincipal && zona instanceof ZonaMano;
    }
    
    @Override
    public void ejecutar(Tablero enJuego, Tablero contrincante, Deque<Ejecucion> pilaMetodos, 
                            String jugadorObjetivo, Carta cartaObjetivo, Carta cartaActivada, Energia energia) {
        
        modificarEnergia(enJuego.energia, Energia.Fuego, cartaObjetivo.costoDeInvocacion.get(Energia.Fuego));
        modificarEnergia(enJuego.energia, Energia.Planta, cartaObjetivo.costoDeInvocacion.get(Energia.Planta));
        modificarEnergia(enJuego.energia, Energia.Agua, cartaObjetivo.costoDeInvocacion.get(Energia.Agua));
    }

    private void modificarEnergia(HashMap<Energia, Integer> energias,Energia energia, Integer costo){
        Integer cantidadEnergia = energias.get(energia) + CANTIDAD * costo;

        energias.put(energia, cantidadEnergia);
    }
}
