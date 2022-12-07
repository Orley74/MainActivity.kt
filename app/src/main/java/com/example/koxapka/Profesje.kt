package com.example.koxapka

import java.io.FileWriter
import java.io.Serializable
import kotlin.random.Random


abstract class Default{
    abstract fun lvl_up()

}

public class Enemy(name:String):Serializable{
    var name = name
    var hp=100
    var armor=2
    var atak = 7
    var lvl=1


    fun ustawlvlW(lvl:Int){
        this.lvl=lvl
        this.hp=(100+(lvl*50))
        this.atak=(7+(2*lvl))
        this.armor=(10+(2*lvl))
    }
    fun ustawlvlM(lvl:Int){
        this.lvl=lvl
        this.hp=(100+(lvl*10))
        this.atak=(7+(7*lvl))
        this.armor=(10+(1*lvl))
    }
    fun ustawlvlL(lvl:Int){
        this.lvl=lvl
        this.hp=(100+(lvl*20))
        this.atak=(7+(5*lvl))
        this.armor=(10+(1.5*lvl)).toInt()
    }
    fun zwyklyAtakW(wojownik: Wojownik){
        var a=this.atak-wojownik.armor
        if(wojownik.hp<0)
            wojownik.hp=wojownik.hp
        else{
            if(a>0)
                wojownik.hp-=a.toInt()
        }


    }
    fun zwyklyAtakL(lowca: Lowca){
        var a=this.atak-lowca.armor
        if(lowca.hp<0)
            lowca.hp=lowca.hp
        else{
            if(a>0)
                lowca.hp-=a.toInt()
        }

    }
    fun zwyklyAtakM(mag: Mag){
        var a=this.atak-mag.armor
        if(mag.hp<0)
            mag.hp=mag.hp
        else{
            if(a>0)
                mag.hp-=a.toInt()
        }

    }

    fun specjalnyAtakW(wojownik: Wojownik){
        var randomnumber = Random.nextInt(100)

        when(randomnumber){
                in 1..10-> wojownik.hp-=this.atak*10

                in 11..50-> wojownik.hp-=(this.atak-wojownik.armor)*2

                in 51..85-> wojownik.hp-=(this.atak-wojownik.armor)*5

                in 86..100 -> wojownik.hp-=(this.atak-wojownik.armor)*0.2

            }
        }
    fun specjalnyAtakM(mag: Mag){
        var randomnumber = Random.nextInt(100)

        when(randomnumber){
            in 1..10-> mag.hp-=this.atak*10

            in 11..50-> mag.hp-=(this.atak-mag.armor)*2

            in 51..85-> mag.hp-=(this.atak-mag.armor)*5

            in 86..100 -> mag.hp-=((this.atak-mag.armor)*0.2).toInt()

        }
    }
    fun specjalnyAtakL(lowca: Lowca){
        var randomnumber = Random.nextInt(100)

        when(randomnumber){

            in 1..10-> lowca.hp-=this.atak*10

            in 11..50-> lowca.hp-=(this.atak-lowca.armor)*2

            in 51..85-> lowca.hp-=(this.atak-lowca.armor)*5

            in 86..100 -> lowca.hp-=(this.atak-lowca.armor)*0.2

        }
    }




}

public class Wojownik(name:String):Serializable,Default(){
    var name = name
    val prof="Wojownik"
    var sila=20
    var intelekt=1
    var zrecznosc=2
    var hp=100+0.2*sila
    var armor=7
    var atak = 5 + 0.1*sila
    var lvl=1
    var exp=0
    var nextlvlEXP=5*lvl
    var money=0

    override fun lvl_up(){
        this.sila+=7
        this.zrecznosc+=4
        this.intelekt+=1
        this.hp+=100
        this.atak+=7
        this.armor+=5
        this.lvl+=1
        this.exp=0
        this.sila+=10
        this.nextlvlEXP+=5*lvl
    }

    fun zwyklyAtak(Enemy: Enemy){
        var a=this.atak-Enemy.armor
        if(Enemy.hp<0)
            Enemy.hp=Enemy.hp
        else{
        if(a>0)
            Enemy.hp-=a.toInt()
        }

    }
    fun silnyAtak(Enemy:Enemy){
        if(Enemy.hp<0)
            Enemy.hp=Enemy.hp
        else{
        var a=((this.sila*0.5)+this.atak)-Enemy.armor
        if(a>0)
            Enemy.hp-=a.toInt()

    }
    }
    fun getEXP(enemy: Enemy,enemyCount: Int){
        this.exp+=((enemy.lvl*2)+((enemyCount*0.7).toInt())-(this.lvl*0.8).toInt())/this.nextlvlEXP
        if (this.exp>=this.nextlvlEXP){
            this.exp-=this.nextlvlEXP
            lvl_up()
        }
    }
    fun ZdobyteChalwy(){
        this.money = Random.nextInt(100)*this.lvl
    }

}
public class Mag(name:String):Serializable, Default(){
    var name = name

    val prof="Mag"
    var intelekt=15
    var sila=2
    var zrecznosc=3
    var hp=70
    var armor=2
    var atak = 10 + 0.4*intelekt
    var lvl=1
    var exp=0
    var nextlvlEXP=2
    var money=0

    override fun lvl_up(){
        this.hp+= 10
        this.sila+=1
        this.zrecznosc+=1
        this.intelekt+=5
        this.atak+=10
        this.armor+=2
        this.lvl+=1
        this.exp=0
        this.nextlvlEXP+=2
    }
    fun ZdobyteChalwy(){
        this.money = Random.nextInt(100)*this.lvl
    }
    fun zwyklyAtak(Enemy: Enemy){
        var a=this.atak-Enemy.armor
        if(Enemy.hp<0)
            Enemy.hp=Enemy.hp
        else{
            if(a>0)
                Enemy.hp-=a.toInt()
        }

    }
    fun silnyAtak(Enemy: Enemy){
        if(Enemy.hp<0)
            Enemy.hp=Enemy.hp
        else{
            var a=((this.intelekt*2)+this.atak)-Enemy.armor
            if(a>0)
                Enemy.hp-=a.toInt()

        }
    }
    fun getEXP(enemy: Enemy,enemyCount: Int){
        this.exp+=1
        if (this.exp>=this.nextlvlEXP){
            this.exp-=this.nextlvlEXP
            lvl_up()
        }
    }
}
public class Lowca(name:String):Serializable, Default(){
    var name = name
    val prof="Lowca"
    var intelekt=4
    var sila=2
    var zrecznosc=12
    var hp=75+ 0.1*sila
    var armor=3+ 0.3*sila+ 0.1*zrecznosc
    var atak = 4 + 0.1*sila + 0.3*zrecznosc
    var exp=0
    var lvl=1
    var nextlvlEXP=5*lvl
    var money=0

    fun ZdobyteChalwy(){
        this.money += Random.nextInt(100)*this.lvl
    }
    override fun lvl_up(){
        this.sila+=3
        this.zrecznosc+=7
        this.intelekt+=1
        this.hp+=25
        this.atak+=3
        this.armor+=5
        this.lvl+=1
        this.exp=0
        this.nextlvlEXP+=5*lvl
    }
    fun zwyklyAtak(Enemy: Enemy){
        var a=this.atak-Enemy.armor
        if(Enemy.hp<0)
            Enemy.hp=Enemy.hp
        else{
            if(a>0)
                Enemy.hp-=a.toInt()
        }

    }
    fun silnyAtak(Enemy: Enemy){
        if(Enemy.hp<0)
            Enemy.hp=Enemy.hp
        else{
            var a=((this.zrecznosc*1)+this.atak)-Enemy.armor
            if(a>0)
                Enemy.hp-=a.toInt()

        }
    }
    fun getEXP(enemy: Enemy,enemyCount: Int){
        this.exp+=((enemy.lvl*2)+((enemyCount*0.7).toInt())-(this.lvl*0.8).toInt())/this.nextlvlEXP
        if (this.exp>=this.nextlvlEXP){
            this.exp-=this.nextlvlEXP
            lvl_up()
        }
    }
}