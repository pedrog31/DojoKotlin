package cd.udea.edu.com.dojokotlin

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import cd.udea.edu.com.dojokotlin.database.DBHelper
import cd.udea.edu.com.dojokotlin.model.Note
import kotlinx.android.synthetic.main.activity_new_note.*

class NewNoteActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_note)
        guardar.setOnClickListener {
            val title = titulo.text.toString()
            val body = texto.text.toString()
            val note = Note(title,body)

            val database = DBHelper(this@NewNoteActivity)
            database.insertNote(note)
            database.close()
            startActivity(Intent(this@NewNoteActivity, MainActivity::class.java))
        }

        salir.setOnClickListener {
            startActivity(Intent(this@NewNoteActivity, MainActivity::class.java))
            finish()
        }
    }
}
