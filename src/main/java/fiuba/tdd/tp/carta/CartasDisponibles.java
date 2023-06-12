package fiuba.tdd.tp.carta;

import java.util.ArrayList;

import fiuba.tdd.tp.carta.Metodos.Atacar;
import fiuba.tdd.tp.carta.Metodos.Curar;
import fiuba.tdd.tp.carta.Metodos.DamagePorAccion;
import fiuba.tdd.tp.carta.Metodos.DamagePorAtributo;
import fiuba.tdd.tp.carta.Metodos.Descartar;
import fiuba.tdd.tp.carta.Metodos.DescartarDeMano;
import fiuba.tdd.tp.carta.Metodos.Destruir;
import fiuba.tdd.tp.carta.Metodos.Drenar;
import fiuba.tdd.tp.carta.Metodos.Impedir;
import fiuba.tdd.tp.carta.Metodos.MetodoCarta;
import fiuba.tdd.tp.carta.Metodos.MetodoCartaCompuesto;
import fiuba.tdd.tp.carta.Metodos.AumentarEnergia;
import fiuba.tdd.tp.carta.Metodos.Reducir;
import fiuba.tdd.tp.carta.Metodos.Replica;
import fiuba.tdd.tp.carta.Metodos.Resonancia;
import fiuba.tdd.tp.carta.Metodos.Sabotear;
import fiuba.tdd.tp.carta.Metodos.Sacrificio;
import fiuba.tdd.tp.carta.Metodos.TomarCarta;
import fiuba.tdd.tp.carta.Metodos.TransferirCarta;
import fiuba.tdd.tp.carta.Metodos.TransferirEnergia;

public enum CartasDisponibles {

    AGUA("AGUA", null,10, new ArrayList<Atributo>(),
                             new ArrayList<Tipo>(){{add(Tipo.Artefacto);}},
                             new ArrayList<MetodoCarta>(){{add(new AumentarEnergia(Energia.Agua, 1, Tipo.Artefacto));}}
                            ),
                            
    FUEGO("FUEGO", null, 10, new ArrayList<Atributo>(),
                            new ArrayList<Tipo>(){{add(Tipo.Artefacto);}},
                            new ArrayList<MetodoCarta>(){{add(new AumentarEnergia(Energia.Fuego, 1, Tipo.Artefacto));}}
                           ),

    PLANTA("PLANTA", null, 10, new ArrayList<Atributo>(),
                           new ArrayList<Tipo>(){{add(Tipo.Artefacto);}},
                           new ArrayList<MetodoCarta>(){{add(new AumentarEnergia(Energia.Planta, 1, Tipo.Artefacto));}}
                        ),
    
    ALQUIMISTA("ALQUIMISTA", 3, 20, new ArrayList<Atributo>(){{add(Atributo.Ciencia);}}, 
                                new ArrayList<Tipo>(){{add(Tipo.Criatura); add(Tipo.Artefacto);}},
                                new ArrayList<MetodoCarta>(){{
                                    add(new MetodoCartaCompuesto(new Atacar(1), new TomarCarta(1,Tipo.Criatura)));
                                    add(new MetodoCartaCompuesto(new Atacar(1), new AumentarEnergia(Energia.Fuego, 1, Tipo.Criatura)));
                                    add(new TomarCarta(1, Tipo.Artefacto));
                                }}
                            ),

    ANTIMAGIA("ANTIMAGIA", null, 15, new ArrayList<Atributo>(), 
                                             new ArrayList<Tipo>(){{add(Tipo.Artefacto);add(Tipo.Accion);}},
                                             new ArrayList<MetodoCarta>(){{
                                                add(new DamagePorAtributo(1, false, Atributo.Magia, Tipo.Artefacto));
                                                add(new MetodoCartaCompuesto(
                                                        new DamagePorAtributo(3, true, Atributo.Magia, Tipo.Accion),
                                                        new Descartar(Tipo.Accion))
                                                    );
                                             }}
                                        ),

    BARRERAMAGICA("BARRERAMAGICA", 10, 12,  new ArrayList<>(){{add(Atributo.Magia);}},
                                                      new ArrayList<Tipo>(){{add(Tipo.Criatura); add(Tipo.Reaccion);}},
                                                      new ArrayList<MetodoCarta>(){{
                                                        add(new Reducir());
                                                      }}
                                                ),
    
    CORROSION("CORROSION", null, 12, new ArrayList<Atributo>(),
                                             new ArrayList<Tipo>(){{add(Tipo.Accion);}},
                                             new ArrayList<MetodoCarta>(){{
                                                add(new MetodoCartaCompuesto(
                                                        new DamagePorAtributo(2, true, Atributo.Metal, Tipo.Accion),
                                                        new Descartar(Tipo.Accion)
                                                    ));
                                             }}
                                        ),
        
    DRENAR("DRENAR", null, 15, new ArrayList<Atributo>(), 
                                                new ArrayList<Tipo>(){{add(Tipo.Accion);}},
                                                new ArrayList<MetodoCarta>(){{
                                                    add(new MetodoCartaCompuesto(
                                                            new Drenar(),
                                                            new Descartar(Tipo.Accion)
                                                        ));
                                                }}
                                        ),

    ESPADAMAGICA("ESPADAMAGICA", 3, 12, new ArrayList<Atributo>(){{add(Atributo.Metal); add(Atributo.Magia);}},
                                                    new ArrayList<Tipo>(){{add(Tipo.Criatura); add(Tipo.Accion);}},
                                                    new ArrayList<MetodoCarta>(){{
                                                        add(new Atacar(3));
                                                        add(new MetodoCartaCompuesto(
                                                                new DamagePorAccion(3),
                                                                new Descartar(Tipo.Accion)
                                                            ));
                                                    }}
                                                ),
                                                
    GOBLIN("GOBLIN", 5, 12,  new ArrayList<Atributo>(),
                                        new ArrayList<Tipo>(){{add(Tipo.Criatura);}},
                                        new ArrayList<MetodoCarta>(){{
                                            add(new MetodoCartaCompuesto(new Atacar(1), new TransferirEnergia())); 
                                        }}
                                    ),

    HOSPITAL("HOSPITAL", null, 12,  new ArrayList<Atributo>(),
                                            new ArrayList<Tipo>(){{add(Tipo.Artefacto);}},
                                            new ArrayList<MetodoCarta>(){{
                                                add(new Curar(2)); 
                                            }}
                                        ),

    IMPEDIR("IMPEDIR", null, 12,  new ArrayList<Atributo>(),
                                            new ArrayList<Tipo>(){{add(Tipo.Reaccion);}},
                                            new ArrayList<MetodoCarta>(){{
                                                add(new MetodoCartaCompuesto(
                                                        new MetodoCartaCompuesto(new Impedir(), new Destruir()), 
                                                        new Descartar(Tipo.Reaccion)
                                                    ));
                                            }}
                                        ),
    
    INVENTOR("INVENTOR", 2, 12,  new ArrayList<Atributo>(),
                                        new ArrayList<Tipo>(){{add(Tipo.Criatura); add(Tipo.Reaccion);}},
                                        new ArrayList<MetodoCarta>(){{
                                            add(new MetodoCartaCompuesto(new Replica(), new DescartarDeMano()));
                                        }}
                                    ),
    
    ORCO("ORCO", 5, 12,  new ArrayList<Atributo>(),
                                    new ArrayList<Tipo>(){{add(Tipo.Criatura);}},
                                    new ArrayList<MetodoCarta>(){{
                                        add(new Atacar(3));
                                    }}
                                ),
    
    RECICLAR("RECICLAR", null, 12,  new ArrayList<Atributo>(),
                                            new ArrayList<Tipo>(){{add(Tipo.Accion);}},
                                            new ArrayList<MetodoCarta>(){{
                                                add(new MetodoCartaCompuesto(new TomarCarta(3, Tipo.Accion), new Descartar(Tipo.Accion)));
                                            }}
                                        ),

    RESONANCIA("RESONANCIA", null, 12,  new ArrayList<Atributo>(),
                                new ArrayList<Tipo>(){{add(Tipo.Accion); add(Tipo.Reaccion);}},
                                new ArrayList<MetodoCarta>(){{
                                    add(new MetodoCartaCompuesto(new Destruir(), new Descartar(Tipo.Accion)));
                                    add(new MetodoCartaCompuesto(new Resonancia(Tipo.Reaccion), new Descartar(Tipo.Reaccion)));
                                }}
                            ),

    SABOTEAR("SABOTEAR", 3, 12, new ArrayList<Atributo>(),
                                            new ArrayList<Tipo>(){{add(Tipo.Criatura); add(Tipo.Reaccion);}},
                                            new ArrayList<MetodoCarta>(){{
                                                add(new MetodoCartaCompuesto(
                                                        new MetodoCartaCompuesto(new Sabotear(), new Destruir()),
                                                        new DescartarDeMano()
                                                    ));
                                            }}
                                        ),

    SACRIFICIO("SACRIFICIO", null, 12,  new ArrayList<Atributo>(),
                                                new ArrayList<Tipo>(){{add(Tipo.Accion);}},
                                                new ArrayList<MetodoCarta>(){{
                                                    add(new MetodoCartaCompuesto(new Sacrificio(), new Descartar(Tipo.Accion)));
                                                }}
                                        ),

    TALADROMAGICO("TALADROMAGICO", 3, 12,   new ArrayList<Atributo>(){{add(Atributo.Metal); add(Atributo.Magia);}},
                                                    new ArrayList<Tipo>(){{add(Tipo.Criatura); add(Tipo.Reaccion);}},
                                                    new ArrayList<MetodoCarta>(){{
                                                        add(new Atacar(5));
                                                        add(new Impedir());
                                                    }}
                                                ),

    TRAICION("TRAICION", null, 12, new ArrayList<Atributo>(),
                                                new ArrayList<Tipo>(){{add(Tipo.Accion);}},
                                                new ArrayList<MetodoCarta>(){{
                                                    add(new MetodoCartaCompuesto(new TransferirCarta(), new Descartar(Tipo.Accion)));
                                                }}
                                            );   

    public String nombre;
    public Integer hp;
    public Integer precio;
    public ArrayList<Integer> costo;
    public ArrayList<Atributo> atributos;
    public ArrayList<Tipo> tipos;
    public ArrayList<MetodoCarta> metodos;

    CartasDisponibles(String nombre, Integer hp, Integer precio, ArrayList<Atributo> atributos, ArrayList<Tipo> tipos, ArrayList<MetodoCarta> metodos) {
        this.nombre = nombre;
        this.hp = hp;
        this.precio = precio;
        this.atributos = atributos;
        this.tipos = tipos;
        this.metodos = metodos;
    }
}
