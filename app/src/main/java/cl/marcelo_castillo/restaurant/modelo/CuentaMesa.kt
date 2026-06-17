package cl.marcelo_castillo.restaurant.modelo

class CuentaMesa {

    fun calcularSubtotal(cantidad: Int, precio: Int): Int {
        return cantidad * precio
    }

    fun calcularPropina(total: Int): Int {
        return (total * 0.10).toInt()
    }

    fun calcularTotal(total: Int, incluirPropina: Boolean): Int {
        return if (incluirPropina) {
            total + calcularPropina(total)
        } else {
            total
        }
    }
}
