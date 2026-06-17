package cl.marcelo_castillo.restaurant

import android.os.Bundle
import android.widget.EditText
import android.widget.Switch
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.widget.addTextChangedListener
import cl.marcelo_castillo.restaurant.modelo.CuentaMesa
import cl.marcelo_castillo.restaurant.modelo.Plato
import java.text.NumberFormat
import java.util.Locale

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        val formatoPeso = NumberFormat.getCurrencyInstance(
            Locale("es", "CL"))
        val editPastelChoclo = findViewById<EditText>(R.id.editPastelChoclo)
        val editCazuela = findViewById<EditText>(R.id.editCazuela)
        val txtSubtotalPastel = findViewById<TextView>(R.id.txtSubtotalPastel)
        val txtSubtotalCazuela = findViewById<TextView>(R.id.txtSubtotalCazuela)
        val txtPrecioTotal = findViewById<TextView>(R.id.txtPrecioTotal)
        val txtValorPropina = findViewById<TextView>(R.id.txtValorPropina)
        val txtTotalCuenta = findViewById<TextView>(R.id.txtTotalCuenta)
        val switchPropina = findViewById<Switch>(R.id.switchPropina)
        val pastelChoclo = Plato("Pastel de Choclo", 12000)
        val cazuela = Plato("Cazuela", 10000)
        val cuentaMesa = CuentaMesa()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        fun actualizarTotales() {

            val cantidadPastel = editPastelChoclo.text.toString().toIntOrNull() ?: 0
            val cantidadCazuela = editCazuela.text.toString().toIntOrNull() ?: 0
            val subtotalPastel = cuentaMesa.calcularSubtotal( cantidadPastel, pastelChoclo.precio )
            val subtotalCazuela = cuentaMesa.calcularSubtotal(cantidadCazuela, cazuela.precio)
            val totalComida = subtotalPastel + subtotalCazuela
            val propina =
                if (switchPropina.isChecked)
                    (totalComida * 0.10).toInt()
                else
                    0
            val totalFinal = totalComida + propina
            txtSubtotalPastel.text = formatoPeso.format(subtotalPastel)
            txtSubtotalCazuela.text = formatoPeso.format(subtotalCazuela)
            txtPrecioTotal.text = formatoPeso.format(totalComida)
            txtValorPropina.text = formatoPeso.format(propina)
            txtTotalCuenta.text = formatoPeso.format(totalFinal)
        }
        editPastelChoclo.addTextChangedListener { actualizarTotales() }
        editCazuela.addTextChangedListener { actualizarTotales() }
        switchPropina.setOnCheckedChangeListener { _, _ -> actualizarTotales() }
        actualizarTotales()
    }
}
