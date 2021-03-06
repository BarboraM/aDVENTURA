/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;


/*******************************************************************************
 * Instance třídy PrikazSeber představují ...
 *
 *@author     Barbora Mlejnková
 *@version    ZS 2016/2017
 */
public class PrikazSeber implements IPrikaz
{
    //== Datové atributy (statické i instancí)======================================
    private static final String NAZEV = "seber";
    private HerniPlan plan;
    private Inventar inventar; 
    private static final String NUZKY = "nuzky" ;
    private static final String ZAVES = "zaves";
    private static final String LEPENKA = "lepenka";
    private static final String PRUVODCE = "pruvodce";
    //== Konstruktory a tovární metody =============================================

    /***************************************************************************
     *  Konstruktor ....
     */
    public PrikazSeber(HerniPlan plan, Inventar inventar)
    {
        this.plan = plan;
        this.inventar = inventar;
    }

    //== Nesoukromé metody (instancí i třídy) ======================================
    /**
     *  Metoda pro provedení příkazu ve hře.
     *  Počet parametrů je závislý na konkrétním příkazu,
     *  např. příkazy konec a napoveda nemají parametry
     *  příkazy jdi, seber, polož mají jeden parametr
     *  příkaz pouzij může mít dva parametry.
     *  
     *  @param parametry počet parametrů závisí na konkrétním příkazu.
     *  
     */
    public String proved(String... parametry){
        String odpoved;
        if(parametry.length == 0){
            odpoved = "Co mam sebrat?";
            return odpoved;
        }       
        String nazevSbiraneVeci = parametry[0];        
        Prostor aktualni = plan.getAktualniProstor();        
        Vec sbirana = aktualni.odeberVec(nazevSbiraneVeci); 
        if(sbirana == null){
            odpoved = "Tuhle vec tu nevidim";
            return odpoved;
        }else{
           if(sbirana.jePrenositelna()){
                if(inventar.vlozVec(sbirana)){
                    if(inventar.jeVInventari(LEPENKA)&&inventar.jeVInventari(ZAVES)&&inventar.jeVInventari(PRUVODCE)&&inventar.jeVInventari(NUZKY)){
                            odpoved = this.vytvorOblek();
                            odpoved = "Sebrano\n" + odpoved;
                            return odpoved;
                    } else{
                        odpoved = "Sebrano";
                        return odpoved;
                    }
                }
            }else{
                 aktualni.vlozVec(sbirana);
                 odpoved = "Tohle nam k nicemu nebude \n";
                 return odpoved;
           }
        }
        return "";
    }
    
    /**
     *  Metoda vytváří z daných věcí oblek, ten pak vlkádá do inventáře a použité věci z inventáře odstraňuje
     *  
     *  @return Vrací oblek do inventáře
     */
    public String vytvorOblek(){
        inventar.odeberVec(NUZKY);
        inventar.odeberVec(ZAVES);
        inventar.odeberVec(LEPENKA);
        inventar.odeberVec(PRUVODCE);
        inventar.vlozVec(new Vec("oblek", true, false));
        return "\nTak to by bylo...Lepenka, nuzky a zaves - z toho bude podle pruvodce paradni ochrannej skafandr!\n" +
                            "Tady to staci trochu strihnout......slepit......a je to! Mame oblek!";             
    }
   

    /**
     *  Metoda vrací název příkazu (slovo které používá hráč pro jeho vyvolání)
     *  
     *  @return nazev prikazu
     */
    public String getNazev(){
        return NAZEV;
    }
}

    //== Soukromé metody (instancí i třídy) ========================================

  
