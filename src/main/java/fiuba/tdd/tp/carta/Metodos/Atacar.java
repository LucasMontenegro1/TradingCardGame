package fiuba.tdd.tp.carta.Metodos;

import java.util.Deque;

import fiuba.tdd.tp.carta.Carta;
import fiuba.tdd.tp.carta.Energia;
import fiuba.tdd.tp.carta.Tipo;
import fiuba.tdd.tp.etapa.Etapa;
import fiuba.tdd.tp.etapa.EtapaDeAtaque;
import fiuba.tdd.tp.tablero.Tablero;
import fiuba.tdd.tp.zona.Zona;
import fiuba.tdd.tp.zona.ZonaCombate;

public class Atacar implements MetodoCarta {

    private int hp;
    public Tipo tipo = Tipo.Criatura;

    public Atacar(int hp){
        this.hp = hp;
    }
    
    @Override
    public boolean esAplicableA(Etapa etapa, Zona zona) {
        return etapa instanceof EtapaDeAtaque && zona instanceof ZonaCombate;
    }

    @Override
    public void ejecutar(Tablero enJuego, Tablero contrincante, Deque<MetodoCarta> pilaMetodos, String jugadorObjetivo,
            Carta carta, Energia energia) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'ejecutar'");
    }

}
