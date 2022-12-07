package com.example.koxapka

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.*
import kotlin.random.Random


class ekranwalkiWojownik : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ekranwalki)
        val printprof = findViewById(R.id.mag_prof) as TextView
        val printname = findViewById(R.id.mag_name) as TextView
        val printlvl = findViewById(R.id.mag_lvl) as TextView
        val printStr = findViewById(R.id.magStr) as TextView
        val printAtt = findViewById(R.id.magAttack) as TextView
        val printinte = findViewById(R.id.magInt) as TextView
        val printzr = findViewById(R.id.magZr) as TextView
        val printdef = findViewById(R.id.magDef) as TextView
        val enemyhp = findViewById(R.id.EnemyHP) as ProgressBar
        val HPBar = findViewById(R.id.HPbar) as ProgressBar
        val EXPBar = findViewById(R.id.EXPbar) as ProgressBar
        val winner = findViewById<TextView>(R.id.winner)
        val AutoF = findViewById<Button>(R.id.AutoF)
        val exit = findViewById<Button>(R.id.exit)
        val enemy1 = findViewById<ImageButton>(R.id.enemy1)
        val gracz = findViewById<ImageButton>(R.id.player)
        val dmg = findViewById<TextView>(R.id.dmg)
        var wojownik = intent.getSerializableExtra("Wojownik_Player") as Wojownik
        var Enemy1 = Enemy("Jachnik")
        val maskotka2 = findViewById<ImageView>(R.id.maskotka2)
        maskotka2.setImageResource(R.drawable.warriorimg)
        gracz.setImageResource(R.drawable.warriorimg)
        enemyhp.max=Enemy1.hp
        EXPBar.max=wojownik.nextlvlEXP
        EXPBar.progress=wojownik.exp
        enemyhp.progress=Enemy1.hp
        val Tura= findViewById<TextView>(R.id.Tura)
        var tura=0
        val wojhp=wojownik.hp
        var logarray: Array<Wojownik>


        fun AktualizujGracza(User: Wojownik){
            printprof.text = User.prof
            printname.text = User.name
            printlvl.text = User.lvl.toString()
            HPBar.progress = User.hp.toInt()
            HPBar.max =wojownik.hp.toInt()
            printAtt.text = "Att: ${User.atak}"
            printzr.text = "Zr: ${User.zrecznosc}"
            printinte.text = "Int: ${User.intelekt}"
            printdef.text = "Obr: ${User.armor}"
            printStr.text = "Str: ${User.sila}"
        }
        AktualizujGracza(wojownik)
        fun showPopup(view: View) {
            val popup = PopupMenu(this, view)
            popup.inflate(R.menu.popup_wojownik)
            popup.setOnMenuItemClickListener(PopupMenu.OnMenuItemClickListener { item: MenuItem? ->

                when (item!!.itemId) {
                    R.id.header1 -> {
                        var hp=Enemy1.hp
                        wojownik.zwyklyAtak(Enemy1)
                        var dmgde=Enemy1.hp-hp
                        enemyhp.progress=Enemy1.hp
                        dmg.setText("$dmgde")
                        if(Enemy1.hp<0){
                            Tura.text="jachnik zostal rozjebany"

                            enemyhp.progress=Enemy1.hp
                            enemy1.setImageResource(R.drawable.dead)
                        }

                        var range= Random.nextInt(100)
                        when(range){
                            in 1..50->{Enemy1.zwyklyAtakW(wojownik) }
                            in 51..100->{Enemy1.specjalnyAtakW(wojownik)}
                        }
                        AktualizujGracza(wojownik)
                        HPBar.progress=wojownik.hp.toInt()
                        if(wojownik.hp<0) {
                            Tura.text = "jachnik jest jebany"
                            gracz.setImageResource(R.drawable.dead)
                        }

                            AktualizujGracza(wojownik)
                            tura += 1
                            Tura.text = tura.toString()
                    }
                    R.id.header2 -> {
                        wojownik.silnyAtak(Enemy1)
                        enemyhp.progress=Enemy1.hp
                        if(Enemy1.hp<0){
                            Tura.text="jachnik zostal rozjebany"
                            enemyhp.progress=Enemy1.hp
                            enemy1.setImageResource(R.drawable.dead)
                        }

                        var range= Random.nextInt(100)
                        when(range){
                            in 1..50->{Enemy1.zwyklyAtakW(wojownik) }
                            in 51..100->{Enemy1.specjalnyAtakW(wojownik)}
                        }
                        AktualizujGracza(wojownik)
                        if(wojownik.hp<0) {
                            Tura.text = "jachnik jest jebany"
                            gracz.setImageResource(R.drawable.dead)
                        }

                        AktualizujGracza(wojownik)
                        tura += 1
                        Tura.text = tura.toString()
                    }

                }

                true
            })

            popup.show()
        }
        AutoF.setOnClickListener {
            var i=0
            while(wojownik.hp>0&&Enemy1.hp>0){
                wojownik.zwyklyAtak(Enemy1)
                var range= Random.nextInt(100)
                when(range){
                    in 1..50->{Enemy1.zwyklyAtakW(wojownik) }
                    in 51..100->{Enemy1.specjalnyAtakW(wojownik)}
                }
                enemyhp.progress=Enemy1.hp
                HPBar.progress=wojownik.hp.toInt()
                i+=1
                if (i==200)
                    break
            }
            if (wojownik.hp>0){
                winner.setText("Wygrales")
                wojownik.getEXP(Enemy1,1)
                enemy1.setImageResource(R.drawable.dead)
            } else{
                winner.setText("Przejebales")

            }
            if(wojownik.hp>0&&Enemy1.hp>0){
            winner.setText("jebac jachnika")
        }
        }
        exit.setOnClickListener {
            val resultintent = Intent(this, Wojownik_Game::class.java)

            if (wojownik.hp>0&&Enemy1.hp<=0) {
                wojownik.getEXP(Enemy1,1)
                wojownik.hp=wojhp
                wojownik.ZdobyteChalwy()
                resultintent.putExtra("New_Wojownik",wojownik)
                startActivity(resultintent)

            }else{
                wojownik.hp=wojhp
                resultintent.putExtra("New_Wojownik",wojownik)
                startActivity(resultintent)

            }
            }

        enemy1.setOnClickListener {
            showPopup(enemy1)
        }


    }

    override fun onStop() {
        super.onStop()
        finish()
    }



}