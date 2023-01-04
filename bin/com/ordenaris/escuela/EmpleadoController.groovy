package com.ordenaris.escuela


import grails.rest.*
import grails.converters.*

class EmpleadoController {
    def EmpleadoService

	static responseFormats = ['json']
	
    
    def registrar() {
        def data = request.JSON
        if(!data.area){
            render( [success: false, mensaje: "Debe colocarse el area"] as JSON)
            return
        }

        if(!data.nombre){
            render( [success: false, mensaje: "El nombre debe quedar vacío"] as JSON)
            return
        }

        if(data.nombre.size < 3){
            render( [success: false, mensaje: "El nombre debe contener más de 3 letras"] )
        }

        if(!data.apaterno){
            render( [success: false, mensaje: "El apellido debe quedar vacío"] as JSON)
            return
        }

        if(data.apaterno.size < 3){
            render( [success: false, mensaje: "El apellido debe contener más de 3 letras"] )
        }

        if(data.amaterno && amaterno.size < 3){
            render( [success: false, mensaje: "Este campo puede ser nulo o contener más de 3 lestras"] )
        }
        
    }

    def informacion() {

    }
    def lista() {

    }
    def paginar() {

    }
    def actualizarEstatus() {

    }
}
