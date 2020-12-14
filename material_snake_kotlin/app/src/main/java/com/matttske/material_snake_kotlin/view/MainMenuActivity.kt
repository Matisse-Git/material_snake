package com.matttske.material_snake_kotlin.view

import android.app.ActivityOptions
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.matttske.material_snake_kotlin.R
import com.matttske.material_snake_kotlin.viewmodel.PlaySnakeViewModel
import android.view.Window
import android.view.WindowManager
import android.widget.ImageButton
import androidx.lifecycle.ViewModelProviders
import com.matttske.material_snake_kotlin.view.custom.SnakeFieldView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main_menu.*


class MainMenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_menu)

        startGameButton.setOnClickListener{
            val intent = Intent(this, PlaySnakeActivity::class.java)
            startActivity(intent)

            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        }
    }
}