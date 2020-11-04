/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Hp
 */
import java.util.Random;

public class Sensor {
    public String type;
    private int data;
    private int min;
    private int max;

    public Sensor(String type) {
        this.type = type;
        this.min = 0;
        if(type=="no2") this.max = 600;
        else if(type=="pm10") this.max = 200;
        else if(type=="o3") this.max = 300;
        else if(type=="pm2.5") this.max = 150;
        else if(type=="hum") {
            this.min = 20;
            this.max = 60;
        }
        else if(type=="temp") {
            this.min = 20;
            this.max = 40;
        }
        this.data = generateRandom();
    }

    public int getData() {
        this.data = generateRandom();
        return this.data;
    }

    public int generateRandom() {
        Random rand = new Random();
        return rand.nextInt(this.max - this.min) + this.min;
    }
}
