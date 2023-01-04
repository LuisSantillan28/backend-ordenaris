package com.ordenaris.escuela

import java.util.UUID

class Empleado {

    static hasMany = [areas: Area]
    static belongsTo = Area

    String uuid = UUID.randomUUID().toString().replaceAll('\\-', '')
    String noEmpleado
    String nombre
    String apaterno
    String amaterno

    Boolean sexo // true - Masculino

    Date fechaNacimiento

    String calle
    String exterior
    String interior
    String colonia
    String cp

    String telefono
    String correo

    int estatus = 1 //1 activo,  2 inctivo, 3 eliminado
    Date fechaRegistro = new Date()


    static constraints = {
        uuid unique:true
        noEmpleado unique: true, nullable: true, blank: true
        amaterno nullable: true, blank: true
        interior nullable: true, blank: true
        correo email: true

        areas nullable: true, blank: true
    }

    static mapping = {
        version false
    }

// crea funciones
    def afterInsert(){
        Empleado.withNewSession{
            this.noEmpleado = generarNumeroEmpleado(this.fechaNacimiento, this.sexo,
            this.nombre, this.apaterno)
        }
    }

    def generarNumeroEmpleado( fechaNacimiento, sexo, nombre, apaterno){
        def totalEmpleados = Empleado.count()+1 //select count(*) from empleado

        def letraSexo = (sexo) ? "M" : "F"
        def codigo = "${nombre[0].toUpperCase()}${apaterno[0].toUpperCase()}${fechaNacimiento.format("ddMMyyyy")}${letraSexo}"
        //00001-NA130897M

        def serie = totalEmpleados.toString().padLeft(5, "0")
        return "${codigo}-${serie}"
    }

}
