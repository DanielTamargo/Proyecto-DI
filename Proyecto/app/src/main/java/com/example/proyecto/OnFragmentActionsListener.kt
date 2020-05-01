package com.example.proyecto

/**
 * Interfaz que se encarga de conectar los listeners de los fragmentos de los mapas con la
 * ventana donde tienen que ejecutarse
 *
 * La ventana que tiene que ejecutarlos implementará la interfaz y sobreescribirá el método
 * Los fragmentos crearán un objeto de esta interfaz y ejecutará el método
 * */

interface OnFragmentActionsListener {
    /**
     * Envía:
     * nombre_mapa: String para saber qué fragmento lo está enviando y así reutilizarlo
     * accion: Int para saber qué tipo de acción
     *
     * accion == 0 -> checkbox listener
     * accion == 1 -> volver listener
     * accion == 2 -> ????e???a??s??t??e???r????e??g??g???3????
     */
    fun onCLickFragmentButton(nombre_mapa: String, accion: Int)
    fun onClickWeaponFragmentButton(nombre_pestanya: String)
}

