package com.example.koxapka

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.view.MotionEvent
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import java.util.*


@Suppress("DEPRECATION")
class Mag_Game : AppCompatActivity() {
    var nick="asd"
    var User = Mag(nick)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

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
        var nick = getIntent().getStringExtra("nick").toString()
       // var bitmap = BitmapFactory.decodeResource(resources,R.drawable.background)
        //map.setImageBitmap(bitmap)

        User = Mag(nick)
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
        fun AktualizujGracza(User: Mag){
            printlvl.text = User.lvl.toString()
            HPBar.max = User.hp.toInt()
            money.text=User.money.toString()
            HPBar.progress = User.hp.toInt()
            printAtt.text = "Att: ${User.atak}"
            printzr.text = "Zr: ${User.zrecznosc}"
            printinte.text = "Int: ${User.intelekt}"
            printdef.text = "Obr: ${User.armor}"
            printStr.text = "Str: ${User.sila}"
        }
        AktualizujGracza(User)


        walka.setOnClickListener {
            val intent = Intent(this, ekranwalkiMag::class.java)
            //enemyimg do wyboru TODO
            intent.putExtra("Mag_Player",User)
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
        val maskotka = findViewById<ImageView>(R.id.maskotka)
        var maskotka3 = findViewById<ImageView>(R.id.maskotka3)
        maskotka.setImageResource(R.drawable.mage)
        val maplayout= findViewById(R.id.maplayout)as ConstraintLayout


        fun AktualizujGracza(User: Mag){
            printlvl.text = User.lvl.toString()
            HPBar.max = User.hp.toInt()
            HPBar.progress = User.hp.toInt()
            EXPBar.max=User.nextlvlEXP
            EXPBar.progress=User.exp
            money.text=User.money.toString()
            printAtt.text = "Att: ${User.atak}"
            printzr.text = "Zr: ${User.zrecznosc}"
            printinte.text = "Int: ${User.intelekt}"
            printdef.text = "Obr: ${User.armor}"
            printStr.text = "Str: ${User.sila}"
        }
        try {
            var Player=intent.getSerializableExtra("New_Mag") as Mag
            User=Player
            AktualizujGracza(User)
        }
        catch (ex:Exception){
            println(ex)
        }

        AktualizujGracza(User)
        goUp.setOnClickListener() {
            moveUP(maskotka3)
        }


        goUp.setOnLongClickListener() {m : View ->

                maskotka3.top-=10
                maskotka3.bottom-=10
                true

        }

        goRight.setOnClickListener {
        moveRIGHT(maskotka3)
            User.money+=1000
        }
        goDown.setOnClickListener {

            moveDOWN(maskotka3)

        }
        goLeft.setOnClickListener {

            moveLEFT(maskotka3)

        }
        playerAction.setOnClickListener {

                val intent = Intent(this, ekranwalkiMag::class.java)

                intent.putExtra("Mag_Player", User)
                startActivity(intent)


        }

    }
    fun moveUP(player:ImageView){
        player.top-=10
        player.bottom-=10

    }

    fun moveDOWN(player:ImageView){
        player.top+=10
        player.bottom+=10
    }
    fun moveRIGHT(player:ImageView){
        player.right+=10
        player.left+=10
    }
    fun moveLEFT(player:ImageView){
        player.right-=10
        player.left-=10
    }


}













