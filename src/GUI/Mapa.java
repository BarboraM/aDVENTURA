/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import logika.IHra;
import main.Main;
import utils.Observer;

/**
 *
 * @author Barbora
 */
public class Mapa extends AnchorPane implements Observer{
    public IHra hra;
    private Circle tecka;
    
    /**
     *  Registruje observer k hernímu plánu
     */  
    public Mapa(IHra hra){
        this.hra = hra; 
        hra.getHerniPlan().registerObserver(this);
        init();
    }
    
    /**
     *  Vytváří mapu a ukazatel místnosti
     */  
    public void init(){
        ImageView obrazekImageView = new ImageView(new Image(Main.class.getResourceAsStream("/zdroje/mapa.jpg"),620,400,false,true));
        tecka = new Circle(10, Paint.valueOf("red"));      
        this.getChildren().addAll(obrazekImageView, tecka);
        update();
    }
    
    
    public void newGame(IHra novaHra){
        hra.getHerniPlan().removeObserver(this);       
        hra = novaHra;
        hra.getHerniPlan().registerObserver(this);
        update();
    }
    
    /**
     *  Aktualizuje umístění ukazatele aktuální místnosti
     */ 
    @Override
    public void update(){ 
        this.setTopAnchor(tecka, hra.getHerniPlan().getAktualniProstor().getPosTop());
        this.setLeftAnchor(tecka, hra.getHerniPlan().getAktualniProstor().getPosLeft());
    }  
}
