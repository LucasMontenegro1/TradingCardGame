package fiuba.tdd.tp.carta;

import java.util.ArrayList;
import java.util.List;

import fiuba.tdd.tp.carta.Metodos.Atacar;
import fiuba.tdd.tp.carta.Metodos.Curar;
import fiuba.tdd.tp.carta.Metodos.DamagePorAccion;
import fiuba.tdd.tp.carta.Metodos.DamagePorAtributo;
import fiuba.tdd.tp.carta.Metodos.Descartar;
import fiuba.tdd.tp.carta.Metodos.DestruirCriatura;
import fiuba.tdd.tp.carta.Metodos.Drenar;
import fiuba.tdd.tp.carta.Metodos.MetodoCarta;
import fiuba.tdd.tp.carta.Metodos.MetodoCartaCompuesto;
import fiuba.tdd.tp.carta.Metodos.ModificarEnergia;
import fiuba.tdd.tp.carta.Metodos.Reducir;
import fiuba.tdd.tp.carta.Metodos.Replica;
import fiuba.tdd.tp.carta.Metodos.Resonancia;
import fiuba.tdd.tp.carta.Metodos.TomarCarta;
import fiuba.tdd.tp.carta.Metodos.TransferirEnergia;

public enum CartasDisponibles {

    AGUA("AGUA", 10, new ArrayList<Atributo>(),
                             new ArrayList<Tipo>(){{add(Tipo.Artefacto);}},
                             new ArrayList<MetodoCarta>(){{add(new ModificarEnergia(Energia.Agua, 1, Tipo.Artefacto));}}
                            ),
                            
    FUEGO("FUEGO", 10, new ArrayList<Atributo>(),
                            new ArrayList<Tipo>(){{add(Tipo.Artefacto);}},
                            new ArrayList<MetodoCarta>(){{add(new ModificarEnergia(Energia.Fuego, 1, Tipo.Artefacto));}}
                           ),

    PLANTA("PLANTA", 10, new ArrayList<Atributo>(),
                           new ArrayList<Tipo>(){{add(Tipo.Artefacto);}},
                           new ArrayList<MetodoCarta>(){{add(new ModificarEnergia(Energia.Planta, 1, Tipo.Artefacto));}}
                        ),
    
    ALQUMISTA("Alquimista", 20, new ArrayList<Atributo>(){{add(Atributo.Ciencia);}}, 
                                new ArrayList<Tipo>(){{add(Tipo.Criatura); add(Tipo.Artefacto);}},
                                new ArrayList<MetodoCarta>(){{
                                    add(new MetodoCartaCompuesto(new Atacar(1), new TomarCarta(1,Tipo.Criatura)));
                                    add(new MetodoCartaCompuesto(new Atacar(1), new ModificarEnergia(Energia.Fuego, 1, Tipo.Criatura)));
                                    add(new TomarCarta(1, Tipo.Artefacto));
                                }}
                            ),

    ANTIMAGIA("Antimagia", 15, new ArrayList<Atributo>(), 
                                             new ArrayList<Tipo>(){{add(Tipo.Artefacto);add(Tipo.Accion);}},
                                             new ArrayList<MetodoCarta>(){{
                                                add(new DamagePorAtributo(1, false, Atributo.Magia, Tipo.Artefacto));
                                                add(new DamagePorAtributo(3, true, Atributo.Magia, Tipo.Accion));
                                             }}
                                        ),

    BARRERAMAGICA("Barrera Mágica", 12, new ArrayList<>(){{add(Atributo.Magia);}},
                                                      new ArrayList<Tipo>(){{add(Tipo.Criatura); add(Tipo.Reaccion);}},
                                                      new ArrayList<MetodoCarta>(){{
                                                        add(new Reducir());
                                                      }}
                                                ),
    
    CORROSION("Corrosion", 12, new ArrayList<Atributo>(),
                                             new ArrayList<Tipo>(){{add(Tipo.Accion);}},
                                             new ArrayList<MetodoCarta>(){{
                                                add(new DamagePorAtributo(2, true, Atributo.Metal, Tipo.Accion));
                                             }}
                                        ),
        
    DRENAR("Drenar", 15, new ArrayList<Atributo>(), 
                                                new ArrayList<Tipo>(){{add(Tipo.Accion);}},
                                                new ArrayList<MetodoCarta>(){{
                                                    add(new Drenar());
                                                }}
                                        ),

    ESPADAMAGICA("Espada mágica", 12, new ArrayList<Atributo>(){{add(Atributo.Metal); add(Atributo.Magia);}},
                                                    new ArrayList<Tipo>(){{add(Tipo.Criatura); add(Tipo.Accion);}},
                                                    new ArrayList<MetodoCarta>(){{
                                                        add(new Atacar(3));
                                                        add(new DamagePorAccion(3)); //TODO: aca debería recibirse un objetivo
                                                    }}
                                                ),
                                                
    GOBLIN("Goblin", 12,  new ArrayList<Atributo>(),
                                        new ArrayList<Tipo>(){{add(Tipo.Criatura);}},
                                        new ArrayList<MetodoCarta>(){{
                                            add(new MetodoCartaCompuesto(new Atacar(1), new TransferirEnergia())); 
                                        }}
                                    ),

    HOSPITAL("Hospital", 12,  new ArrayList<Atributo>(),
                                            new ArrayList<Tipo>(){{add(Tipo.Artefacto);}},
                                            new ArrayList<MetodoCarta>(){{
                                                add(new Curar(2)); 
                                            }}
                                        ),

    IMPEDIR("Impedir reacción", 12,  new ArrayList<Atributo>(),
                                            new ArrayList<Tipo>(){{add(Tipo.Reaccion);}},
                                            new ArrayList<MetodoCarta>(){{}}
                                        ),
    
    INVENTOR("Inventor", 12,  new ArrayList<Atributo>(),
                                        new ArrayList<Tipo>(){{add(Tipo.Criatura); add(Tipo.Reaccion);}},
                                        new ArrayList<MetodoCarta>(){{
                                            add(new Replica());
                                        }}
                                    ),
    
    ORCO("Orco", 12,  new ArrayList<Atributo>(),
                                    new ArrayList<Tipo>(){{add(Tipo.Criatura);}},
                                    new ArrayList<MetodoCarta>(){{
                                        add(new Atacar(3));
                                    }}
                                ),
    
    RECICLAR("Reciclar", 12,  new ArrayList<Atributo>(),
                                            new ArrayList<Tipo>(){{add(Tipo.Accion);}},
                                            new ArrayList<MetodoCarta>(){{
                                                add(new Descartar());
                                                add(new TomarCarta(3, Tipo.Accion));
                                            }}
                                        ),

    RESONANCIA("Resonancia", 12,  new ArrayList<Atributo>(),
                                new ArrayList<Tipo>(){{add(Tipo.Accion); add(Tipo.Reaccion);}},
                                new ArrayList<MetodoCarta>(){{
                                    add(new DestruirCriatura());
                                    add(new Resonancia());
                                }}
                            ),

    SABOTEAR("Sabotear", 12,new ArrayList<Atributo>(),
                                            new ArrayList<Tipo>(){{add(Tipo.Criatura); add(Tipo.Reaccion);}},
                                            new ArrayList<MetodoCarta>(){{
                                                
                                            }}
                                        ),

    SACRIFICIO("Sacrificio", 12,  new ArrayList<Atributo>(),
                                                new ArrayList<Tipo>(){{add(Tipo.Criatura); add(Tipo.Reaccion);}},
                                                new ArrayList<MetodoCarta>(){{
                                                    
                                                }}
                                        ),

    TALADROMAGICO("Taladro mágico", 12,   new ArrayList<Atributo>(){{add(Atributo.Metal); add(Atributo.Magia);}},
                                                    new ArrayList<Tipo>(){{add(Tipo.Criatura); add(Tipo.Reaccion);}},
                                                    new ArrayList<MetodoCarta>(){{
                                                        
                                                    }}
                                                ),

    TRAICION("Traicion", 12,new ArrayList<Atributo>(),
                                                new ArrayList<Tipo>(){{add(Tipo.Accion);}},
                                                new ArrayList<MetodoCarta>(){{
                                                    
                                                }}
                                            );   

    public String nombre;
    public int precio;
    public ArrayList<Integer> costo;
    public ArrayList<Atributo> atributos;
    public ArrayList<Tipo> tipos;
    public ArrayList<MetodoCarta> metodos;

    CartasDisponibles(String nombre, int precio, ArrayList<Atributo> atributos, ArrayList<Tipo> tipos, ArrayList<MetodoCarta> metodos) {
        this.nombre = nombre;
        this.precio = precio;
        this.atributos = atributos;
        this.tipos = tipos;
        this.metodos = metodos;
    }
}
