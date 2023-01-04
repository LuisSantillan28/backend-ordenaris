package escuela

class UrlMappings {

    static mappings = {
        
        group "/ordenaris/api", {
            group "/area", {
                "/registrar"(controller:"area", action: "registrar", method: "POST")
                "/$uuid/modificar"(controller:"area", action: "registrar", method: "PUT")
                "/$uuid/actualizar-estatus"(controller:"area", action: "actualizarEstatus", method: "PATCH")
                "/$uuid/informacion"(controller:"area", action: "informacion", method: "GET")
                "/lista"(controller:"area", action: "lista", method: "GET")
                "/paginador"(controller:"area", action: "paginar", method: "GET")
            }

            group "/empleado", {
                "/registrar"(controller:"empleado", action: "registrar", method: "POST")
                "/$uuid/modificar"(controller:"empleado", action: "registrar", method: "PUT")
                "/$uuid/actualizar-estatus"(controller:"empleado", action: "actualizarEstatus", method: "PATCH")
                "/$uuid/informacion"(controller:"empleado", action: "informacion", method: "GET")
                "/lista"(controller:"empleado", action: "lista", method: "GET")
                "/paginador"(controller:"empleado", action: "paginar", method: "GET")
            }
        }

        "/"(controller: 'application', action:'index')
        "500"(view: '/error')
        "404"(view: '/notFound')
    }
}
