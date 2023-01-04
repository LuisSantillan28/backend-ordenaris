package com.ordenaris.escuela


import grails.rest.*
import grails.converters.*

class AreaController {
    def AreaService

	static responseFormats = ['json']
	
    def registrar() {
        def data = request.JSON

        if( !data.nombre){
            render([success: false, mensaje: "El nombre esta vacío "] as JSON)
            return
        }
        if(data.nombre.size() < 2) {
            render ([ success: false, mensaje: "El nombre debe tener mínimo 2 letras"] as JSON)
            return
        }
        render(AreaService.registrar(data, params.uuid) as JSON)
        
    }
    def informacion() {
        render(AreaService.informacion(params.uuid) as JSON)

    }
    def lista() {
        
        render(AreaService.lista() as JSON)
    }
    def paginar() {
        if (!params.sort){
            render( [success:false, mensaje: "Sort vacio"] as JSON)
            return
        }
        if (params.sort != "nombre" && params.sort != "estatus"){
            render( [ success: false, mensaje: "No se puede ordenar por essa propiedad" ] as JSON)
            return
        }
        if(!params.order){
            render( [success:false, mensaje: "Order vacío"] as JSON)
            return
        }
        if(params.order != "asc" && params.order != "desc"){
             render( [success:false, mensaje: "No se puede ordenar"] as JSON)
            return
        }
        if(!params.page){
            render([success:false, mensaje: "Page vacío"] as JSON)
            return
        }

        try{
            if(params.page.toInteger() < 1){
                render([success:false, mensaje: "Page debe ser número positivo"] as JSON)
                return
            }
        }catch(e){
            render([success: false, mensaje: "no"] as JSON)
            return

        }
        try{
            if(params.max.toInteger() < 1){
                render([success:false, mensaje: "Max debe ser número positivo"] as JSON)
                return
            }
        }catch(e){
            render([success: false, mensaje: "Max debe ser número positivo"] as JSON)
            return

        }
        render( AreaService.paginador(params) as JSON)
    }
    

    def actualizarEstatus() {
        if( !params.estatus){
            render([success:false, mensaje: "No viene el parámetro estatus"] as JSON)
            return
        }

        try{
            if(params.estatus.toInteger()< 1 && params.estatus.toInteger() > 3){
                render( [success:false, mensaje: "El estatus no es valido"] as JSON)
                return
            }
        }catch(e){
            render( [success:true, mensaje: "El estatus debe ser un número"] as JSON )
            return
        }
        render( AreaService.actualizarEstatus( params.uuid, params.estatus.toInteger()) as JSON)
    }
}
