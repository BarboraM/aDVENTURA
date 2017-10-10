package logika;


/**
 *  Class HerniPlan - třída představující mapu a stav adventury.
 * 
 *  Tato třída inicializuje prvky ze kterých se hra skládá:
 *  vytváří všechny prostory,
 *  propojuje je vzájemně pomocí východů 
 *  a pamatuje si aktuální prostor, ve kterém se hráč právě nachází.
 *
 *@author     Michael Kolling, Lubos Pavlicek, Jarmila Pavlickova
 *@version    pro školní rok 2015/2016
 */
public class HerniPlan {
    
    private Prostor aktualniProstor;

     /**
     *  Konstruktor který vytváří jednotlivé prostory a propojuje je pomocí východů.
     *  Jako výchozí aktuální prostor nastaví halu.
     */
    public HerniPlan() {
        zalozProstoryHry();
    }
    /**
     *  Vytváří jednotlivé prostory a propojuje je pomocí východů.
     *  Jako výchozí aktuální prostor nastaví domeček.
     */
    private void zalozProstoryHry() {
        // vytvářejí se jednotlivé prostory
        Prostor pokoj = new Prostor("pokoj", "pokoj");
        Prostor koupelna = new Prostor("koupelna", "koupelna.\n Myslis, ze je spravna doba se ted sprchovat?");
        Prostor sklad = new Prostor("sklad", "sklad");
        Prostor jidelna = new Prostor("jidelna", "jidelna");
        Prostor spolecenskaMistnost = new Prostor("spol.mistnost", "spol.mistnost");
        Prostor schodiste = new Prostor("schodiste", "schodiste");
        Prostor prechodovaKomora = new Prostor("prechod.komora", "prechodova komora. \nBunkr je hned u hlavni silnice, ta by nas mela dovest k letisti...\n");
        Prostor silnice = new Prostor("silnice", "silnice. \nFajn, doufam, ze to odtud neni daleko, moc casu nemame...\n"
                                        + "Pockat, neni tamhle auto?!");                                
        Prostor auto = new Prostor("auto", "auto. \n Konecne! Odsud uz by to mel byt jen kousek.\n" + "Uz vidim letiste!!!\n" + 
                                    "Dokazali jsme to!!!  Jsem v bezpeci!!");
       
       
        
        // přiřazují se průchody mezi prostory (sousedící prostory)
        pokoj.setVychod(spolecenskaMistnost);
        spolecenskaMistnost.setVychod(pokoj);
        spolecenskaMistnost.setVychod(jidelna);
        jidelna.setVychod(schodiste);
        jidelna.setVychod(spolecenskaMistnost);
        jidelna.setVychod(sklad);
        sklad.setVychod(koupelna);
        sklad.setVychod(jidelna);
        koupelna.setVychod(sklad);
        schodiste.setVychod(prechodovaKomora);
        schodiste.setVychod(jidelna);
        prechodovaKomora.setVychod(silnice);
        prechodovaKomora.setVychod(schodiste);
        silnice.setVychod(auto);
        auto.setVychod(prechodovaKomora);
        
        // vytvářejí se jednotlivé věci
        Vec zaves = new Vec("zaves",true, false);
        Vec lepenka = new Vec ("lepenka", true, false);
        Vec kredenc = new Vec ("kredenc", false, true);
        Vec konzerva = new Vec ("konzerva", true, false);
        Vec mouka = new Vec ("mouka", false, false);
        Vec mydlo = new Vec ("mydlo", false, false);
        Vec rucnik = new Vec ("rucnik", false, false);
        Vec matrace = new Vec ("matrace", false, true);
        Vec lampicka = new Vec ("lampicka", false, false);
        Vec knihovna = new Vec ("knihovna", false, true);
  
        // přiřazují se věci do místností
        koupelna.vlozVec(zaves);
        koupelna.vlozVec(mydlo);
        koupelna.vlozVec(rucnik);
        pokoj.vlozVec(matrace);
        pokoj.vlozVec(lampicka);
        jidelna.vlozVec(kredenc);
        sklad.vlozVec(mouka);
        sklad.vlozVec(konzerva);
        spolecenskaMistnost.vlozVec(lepenka);
        spolecenskaMistnost.vlozVec(knihovna);
                 
        aktualniProstor = pokoj;  // hra začíná v pokoji     
    }
    
    /**
     *  Metoda vrací odkaz na aktuální prostor, ve ktetém se hráč právě nachází.
     *
     *@return     aktuální prostor
     */
    
    public Prostor getAktualniProstor() {
        return aktualniProstor;
    }
    
    
    /**
     *  Metoda nastaví aktuální prostor, používá se nejčastěji při přechodu mezi prostory
     *
     *@param  prostor nový aktuální prostor
     */
    public void setAktualniProstor(Prostor prostor) {
       aktualniProstor = prostor;
    }
    
    

}