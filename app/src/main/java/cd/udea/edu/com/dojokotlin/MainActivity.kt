package cd.udea.edu.com.dojokotlin

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import cd.udea.edu.com.dojokotlin.database.DBHelper
import cd.udea.edu.com.dojokotlin.model.Note

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var notes = mutableListOf<Note>()
    lateinit var database: DBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        database = DBHelper(this@MainActivity)
        refrescar()

        lista.onItemLongClickListener = AdapterView.OnItemLongClickListener { parent, view, position, id ->
            database.deleteNote(notes[position])
            refrescar()
            true
        }

        fab.setOnClickListener { view ->
            val intent = Intent(this@MainActivity, NewNoteActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun refrescar () {
        notes = database.getNotes()
        var adapter = ArrayAdapter<Note> (this@MainActivity, android.R.layout.simple_list_item_1 ,notes)
        lista.adapter = adapter
    }
}
