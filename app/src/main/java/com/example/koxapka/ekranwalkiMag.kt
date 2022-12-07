package com.example.koxapka

import android.content.Intent
import android.graphics.Rect
import android.os.Bundle
import android.view.Gravity
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random


class ekranwalkiMag : AppCompatActivity() {

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
        var mag = intent.getSerializableExtra("Mag_Player") as Mag
        var Enemy1 = Enemy("Jachnik")
        EXPBar.max=mag.nextlvlEXP
        EXPBar.progress=mag.exp
        var moneycount=0

        gracz.setImageResource(R.drawable.mage)
        val maskotka2 = findViewById<ImageView>(R.id.maskotka2)
        maskotka2.setImageResource(R.drawable.mage)
       // var toast= Toast.makeText(enemy1.context,"aaa",Toast.LENGTH_SHORT)
        var v = enemy1.rootView
        enemyhp.max=Enemy1.hp
        enemyhp.progress=Enemy1.hp
        HPBar.max =mag.hp.toInt()
        val Tura= findViewById<TextView>(R.id.Tura)
        var tura=0
        val maghp=mag.hp

        fun AktualizujGracza(User: Mag){
            printprof.text = User.prof
            printname.text = User.name
            printlvl.text = User.lvl.toString()
            HPBar.progress = User.hp.toInt()

            printAtt.text = "Att: ${User.atak}"
            printzr.text = "Zr: ${User.zrecznosc}"
            printinte.text = "Int: ${User.intelekt}"
            printdef.text = "Obr: ${User.armor}"
            printStr.text = "Str: ${User.sila}"
        }
        AktualizujGracza(mag)
        fun showPopup(view: View) {
            val popup = PopupMenu(this, view)
            popup.inflate(R.menu.popup_mag)
            popup.setOnMenuItemClickListener(PopupMenu.OnMenuItemClickListener { item: MenuItem? ->
            var enytemphp=Enemy1.hp
                var attackDMG=0
                when (item!!.itemId) {
                    R.id.header1 -> {
                        enytemphp=Enemy1.hp
                        mag.zwyklyAtak(Enemy1)
                        attackDMG=enytemphp-Enemy1.hp
                        var toast=Toast.makeText(this,"-$attackDMG",Toast.LENGTH_SHORT)
                        toast.setGravity(Gravity.CENTER_VERTICAL,0,0)
                        toast.show()
                        enemyhp.progress=Enemy1.hp
                        if(Enemy1.hp<0){

                            Tura.text="jachnik zostal rozjebany"
                            enemyhp.progress=Enemy1.hp
                            enemy1.setImageResource(R.drawable.dead)
                        }

                        var range= Random.nextInt(100)
                        when(range){
                            in 1..50->{Enemy1.zwyklyAtakM(mag) }
                            in 51..100->{Enemy1.specjalnyAtakM(mag)}
                        }
                        AktualizujGracza(mag)
                        HPBar.progress=mag.hp.toInt()
                        if(mag.hp<0) {
                            Tura.text = "jachnik jest jebany"

                            gracz.setImageResource(R.drawable.dead)
                        }

                        AktualizujGracza(mag)
                        tura += 1

                        Tura.text = tura.toString()
                    }
                    R.id.header2 -> {
                        mag.silnyAtak(Enemy1)
                        enemyhp.progress=Enemy1.hp
                        if(Enemy1.hp<0){
                            Tura.text="jachnik zostal rozjebany"
                            enemyhp.progress=Enemy1.hp
                            enemy1.setImageResource(R.drawable.dead)

                        }

                        var range= Random.nextInt(100)
                        when(range){
                            in 1..50->{Enemy1.zwyklyAtakM(mag) }
                            in 51..100->{Enemy1.specjalnyAtakM(mag)}
                        }
                        AktualizujGracza(mag)
                        if(mag.hp<0) {
                            Tura.text = "jachnik jest jebany"
                            popup.dismiss()
                            gracz.setImageResource(R.drawable.dead)
                        }

                        AktualizujGracza(mag)
                        tura += 1

                        Tura.text = tura.toString()
                    }

                }

                true
            })

            popup.show()
        }

        AutoF.setOnClickListener {
            var k=0;
            while(mag.hp>0&&Enemy1.hp>0){
                mag.zwyklyAtak(Enemy1)

                var range= Random.nextInt(100)
                when(range){
                    in 1..50->{Enemy1.zwyklyAtakM(mag) }
                    in 51..100->{Enemy1.specjalnyAtakM(mag)}
                }
                enemyhp.progress=Enemy1.hp
                HPBar.progress=mag.hp.toInt()
                k+=1

                if (k==200)
                    break
            }
            if (mag.hp>0){
                winner.setText("Wygrales")
                enemyhp.progress=Enemy1.hp
                HPBar.progress=mag.hp.toInt()
                enemy1.setImageResource(R.drawable.dead)
            } else{
                winner.setText("Przejebales")
                enemyhp.progress=Enemy1.hp
                HPBar.progress=mag.hp.toInt()

            }
            if(mag.hp>0&&Enemy1.hp>0){
                enemyhp.progress=Enemy1.hp
                HPBar.progress=mag.hp.toInt()
                winner.setText("jebac jachnika")
            }
        }
        exit.setOnClickListener {
            val resultintent = Intent(this, Mag_Game::class.java)

            if (mag.hp>0&&Enemy1.hp<=0) {
                mag.getEXP(Enemy1,1)
                mag.ZdobyteChalwy()
                mag.hp=maghp
                resultintent.putExtra("New_Mag",mag)
                startActivity(resultintent)

            }else{
                mag.hp=maghp
                resultintent.putExtra("New_Mag",mag)
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
