package com.example.koxapka

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

@Suppress("DEPRECATION")
class Wojownik_Game : AppCompatActivity() {
    var nick="asd"
    var User = Wojownik(nick)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        val goUp = findViewById(R.id.magGoUp) as ImageButton
        val goDown = findViewById(R.id.magGoDown) as ImageButton
        val goRight = findViewById(R.id.magGoRight) as ImageButton
        val goLeft = findViewById(R.id.magGoLeft) as ImageButton
        val playerAction = findViewById<ImageButton>(R.id.magAction)
        val printprof = findViewById(R.id.mag_prof) as TextView
        val printname = findViewById(R.id.mag_name) as TextView
        val printlvl = findViewById(R.id.mag_lvl) as TextView
        val printStr = findViewById(R.id.magStr) as TextView
        val printAtt = findViewById(R.id.magAttack) as TextView
        val printinte = findViewById(R.id.magInt) as TextView
        val printzr = findViewById(R.id.magZr) as TextView
        val printdef = findViewById(R.id.magDef) as TextView
        val HPBar = findViewById(R.id.HPbar) as ProgressBar
        val EXPBar = findViewById(R.id.EXPbar) as ProgressBar
        val maskotka = findViewById<ImageView>(R.id.maskotka)
        maskotka.setImageResource(R.drawable.warriorimg)
        val money=findViewById<TextView>(R.id.chalwaCount)
        val walka = findViewById<Button>(R.id.walka)
        var nick = getIntent().getStringExtra("nick").toString()
        User = Wojownik(nick)
        printprof.text = User.prof

        printname.text = nick
        printlvl.text = User.lvl.toString()
        HPBar.max = User.hp.toInt()
        HPBar.progress = User.hp.toInt()
        printAtt.text = "Att: ${User.atak}"
        printzr.text = "Zr: ${User.zrecznosc}"
        printinte.text = "Int: ${User.intelekt}"
        printdef.text = "Obr: ${User.armor}"
        printStr.text = "Str: ${User.sila}"
        fun AktualizujGracza(User: Wojownik){
            printlvl.text = User.lvl.toString()
            HPBar.max = User.hp.toInt()
            EXPBar.max=User.nextlvlEXP
            EXPBar.progress=User.exp
            HPBar.progress = User.hp.toInt()
            printAtt.text = "Att: ${User.atak}"
            printzr.text = "Zr: ${User.zrecznosc}"
            printinte.text = "Int: ${User.intelekt}"
            printdef.text = "Obr: ${User.armor}"
            printStr.text = "Str: ${User.sila}"
            money.text = User.money.toString()
        }
        AktualizujGracza(User)

        goUp.setOnClickListener {

            AktualizujGracza(User)
        }
        walka.setOnClickListener {
            val intent = Intent(this, ekranwalkiWojownik::class.java)

            intent.putExtra("Wojownik_Player",User)
            startActivity(intent)

            }

        AktualizujGracza(User)


    }

    override fun onResume() {
        super.onResume()
        val goUp = findViewById(R.id.magGoUp) as ImageButton
        val goDown = findViewById(R.id.magGoDown) as ImageButton
        val goRight = findViewById(R.id.magGoRight) as ImageButton
        val goLeft = findViewById(R.id.magGoLeft) as ImageButton
        val playerAction = findViewById<ImageButton>(R.id.magAction)
        val printprof = findViewById(R.id.mag_prof) as TextView
        val printname = findViewById(R.id.mag_name) as TextView
        val printlvl = findViewById(R.id.mag_lvl) as TextView
        val printStr = findViewById(R.id.magStr) as TextView
        val printAtt = findViewById(R.id.magAttack) as TextView
        val printinte = findViewById(R.id.magInt) as TextView
        val printzr = findViewById(R.id.magZr) as TextView
        val printdef = findViewById(R.id.magDef) as TextView
        val HPBar = findViewById(R.id.HPbar) as ProgressBar
        val EXPBar = findViewById(R.id.EXPbar) as ProgressBar
        val money=findViewById<TextView>(R.id.chalwaCount)
        val walka = findViewById<Button>(R.id.walka)
        fun AktualizujGracza(User: Wojownik){
            printlvl.text = User.lvl.toString()
            HPBar.max = User.hp.toInt()
            HPBar.progress = User.hp.toInt()
            printAtt.text = "Att: ${User.atak}"
            money.text=User.money.toString()
            printzr.text = "Zr: ${User.zrecznosc}"
            printinte.text = "Int: ${User.intelekt}"
            printdef.text = "Obr: ${User.armor}"
            printStr.text = "Str: ${User.sila}"
        }
    try {
        var Player=intent.getSerializableExtra("New_Wojownik") as Wojownik
        User=Player
        AktualizujGracza(User)
    }
    catch (ex:Exception){
        println(ex)
    }

        AktualizujGracza(User)

    }



}












