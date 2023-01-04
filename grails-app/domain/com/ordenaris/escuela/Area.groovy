package com.ordenaris.escuela
import java.util.UUID

class Area {
    static hasMany = [empleados : Empleado]

    String uuid = UUID.randomUUID().toString().replaceAll('\\-', "")
    String nombre
    int estatus = 1

    static constraints = {
        uuid unique:true
        empleados nullable: true, blank: true
    }

    static mapping = {
        version false
    }
}
