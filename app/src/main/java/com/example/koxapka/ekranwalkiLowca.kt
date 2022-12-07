package com.example.koxapka

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.*
import kotlin.random.Random


class ekranwalkiLowca : AppCompatActivity() {
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
        gracz.setImageResource(R.drawable.archerimg)
        val maskotka2 = findViewById<ImageView>(R.id.maskotka2)
        maskotka2.setImageResource(R.drawable.archerimg)
        var lowca = intent.getSerializableExtra("Lowca_Player") as Lowca
        EXPBar.progress=lowca.nextlvlEXP
        EXPBar.progress=lowca.exp
        var Enemy1 = Enemy("Jachnik")
        enemyhp.max=Enemy1.hp
        HPBar.progress=Enemy1.hp
        val Tura= findViewById<TextView>(R.id.Tura)
        var tura=0
        var range= Random.nextInt(100)
        val lowhp=lowca.hp
        fun AktualizujGracza(User: Lowca){
            printprof.text = User.prof
            printname.text = User.name
            printlvl.text = User.lvl.toString()
            HPBar.progress = User.hp.toInt()
            HPBar.max =lowca.hp.toInt()
            printAtt.text = "Att: ${User.atak}"
            printzr.text = "Zr: ${User.zrecznosc}"
            printinte.text = "Int: ${User.intelekt}"
            printdef.text = "Obr: ${User.armor}"
            printStr.text = "Str: ${User.sila}"
        }
        AktualizujGracza(lowca)
        fun showPopup(view: View) {
            val popup = PopupMenu(this, view)
            popup.inflate(R.menu.popup_lucznik)
            popup.setOnMenuItemClickListener(PopupMenu.OnMenuItemClickListener { item: MenuItem? ->

                when (item!!.itemId) {
                    R.id.header1 -> {
                        lowca.zwyklyAtak(Enemy1)
                        enemyhp.progress=Enemy1.hp
                        if(Enemy1.hp<0){
                            Tura.text="jachnik zostal rozjebany"
                            enemyhp.progress=Enemy1.hp
                            enemy1.setImageResource(R.drawable.dead)
                        }
                        range= Random.nextInt(100)
                        when(range){
                            in 1..79->{Enemy1.zwyklyAtakL(lowca) }
                            in 80..100->{Enemy1.specjalnyAtakL(lowca)}
                        }
                        Enemy1.zwyklyAtakL(lowca)

                        HPBar.progress=lowca.hp.toInt()
                        if(lowca.hp<0) {
                            Tura.text = "jachnik jest jebany"
                            gracz.setImageResource(R.drawable.dead)

                        }

                        HPBar.progress=lowca.hp.toInt()
                        tura += 1
                        Tura.text = tura.toString()
                    }
                    R.id.header2 -> {
                        lowca.silnyAtak(Enemy1)
                        enemyhp.progress=Enemy1.hp
                        if(Enemy1.hp<0){
                            Tura.text="jachnik zostal rozjebany"
                            enemyhp.progress=Enemy1.hp
                            enemy1.setImageResource(R.drawable.dead)
                        }

                        range= Random.nextInt(100)
                        when(range){
                            in 1..50->{Enemy1.zwyklyAtakL(lowca) }
                            in 51..100->{Enemy1.specjalnyAtakL(lowca)}
                        }
                        HPBar.progress=lowca.hp.toInt()
                        if(lowca.hp<0) {
                            Tura.text = "jachnik jest jebany"
                            gracz.setImageResource(R.drawable.dead)
                            popup.dismiss()
                        }

                        HPBar.progress=lowca.hp.toInt()
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
            while(lowca.hp>0&&Enemy1.hp>0){
                lowca.zwyklyAtak(Enemy1)
                Enemy1.zwyklyAtakL(lowca)
                enemyhp.progress=Enemy1.hp
                HPBar.progress=lowca.hp.toInt()
                i+=1
                if (i==200)
                    break
            }
            if (lowca.hp>0){
                winner.setText("Wygrales")

                enemy1.setImageResource(R.drawable.dead)
            } else{
                winner.setText("Przejebales")
                gracz.setImageResource(R.drawable.dead)
            }
            if(lowca.hp>0&&Enemy1.hp>0){
                winner.setText("jebac jachnika")
            }
        }
        exit.setOnClickListener {
            val resultintent = Intent(this, Lowca_Game::class.java)

            if (lowca.hp>0&&Enemy1.hp<=0) {
                lowca.getEXP(Enemy1,1)
                lowca.ZdobyteChalwy()
                lowca.hp=lowhp
                resultintent.putExtra("New_Lowca",lowca)
                startActivity(resultintent)

            }else{
                lowca.hp=lowhp
                resultintent.putExtra("New_Lowca",lowca)
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