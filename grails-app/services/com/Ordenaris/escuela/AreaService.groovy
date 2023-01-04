package com.ordenaris.escuela

import grails.gorm.transactions.Transactional

@Transactional
class AreaService {

    def registrar(data, uuid = null) {
        Area.withTransaction { tStatus ->
        def nArea
        try{
            // def nArea = new Area([nombre: data.nombre])
            nArea = Area.findByUuid( uuid )

            if( !nArea ) {
                nArea = new Area()
            }

            nArea.nombre = data.nombre
            nArea.save(flush:true, failOnError: true)
            return [success: true]

            // def nArea = new Area()
            // nArea.nombre = data.nombre
        }catch(e){
            println "${new Date()} AreaService - registrar - error - {e.getMessage()}"
            tStatus.setRollbackOnly()
            return [ success: false, mensaje: e.getMessage()]
        }
        }

    }

    def informacion (uuid) {
        try{
            def area = Area.findByUuid(uuid)

            if(!area ){
                return [success:false, mensaje: "Area no encontrada"]
            }
            def informacion= [
                uuid: area.uuid,
                nombre: area.nombre,
                estatus: area.estatus,
                empleados: 0
            ]

            return[ success: true, informacion: informacion]
        }catch(e){
            println "${new Date()} AreaService - info - error - {e.getMessage()}"
            return [success: false, mensaje: e.getMessage()]

        }
    }

    def lista() {
        try{
            println "funcion :c"
            def lista = []
            // select * from area where estatus = 1
            Area.findAllByEstatus(1).each{ a->
            lista.add([uuid: a.uuid, nombre: a.nombre])
            }
            return[success: true, lista: lista]
        }catch(e){
            println "${new Date()} AreaService - lista - error - {e.getMessage()}"
            return [success: false, mensaje: e.getMessage()]
        }
    }

    def paginador(data){
        try{
            def _max = data.max ? data.max.toInteger() : 5
            def _offset = ( --data.page.toInteger() * _max.toInteger())
            
            def lista = []
            def total = Area.countByEstatusNotEqual(3)

            if(data.search){
                total = Area.withCriteria {
                    ne("estatus", 3)
                    or{
                        ilike("nombre", "%${data.search}%")
                    }
                    projections{
                        rowCount()
                        // este cero refleja la posición del resultado de búsqueda
                    }
                }[0]

               
            }

                Area.withCriteria{
                    ne("estatus", 3)
                    if(data.search){
                        or{
                            ilike("nombre", "%${data.search}%")
                        }
                    }
                    firstResult(_offset)
                    maxResults _max
                    order(data.sort, data.order)
                }each{a->
                lista.add([
                    uuid: a.uuid,
                    nombre: a.nombre,
                    estatus: a.estatus,
                    empleado: 0
                ])}
                 return [success: true, lista: lista, total: total]
        }catch(e){
            println "${new Date()} AreaService - paginar - error - {e.getMessage()}"
            return [success: false, mensaje: e.getMessage()]
        }
    }

    def actualizarEstatus(uuid, estatus){
        Area.withTransaction{ tStatus->
            try{
                def area = Area.findByUuid( uuid )
                if(!area){
                    return [ success: false, mensaje: "No se encontró el área"]
                }
                area.estatus = estatus

                area.save( flush: true, failOnError: true )
                return[success:true]
            }catch(e){
                println"${new Date()}  - Area Service - actualizarEstatus - error - ${e.getMessage()}"
                tStatus.setRollbackOnly()
                return[success:false, mensaje: e.getMessage()]
            }
        }
    }



}
