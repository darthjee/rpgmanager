package personagem.ded35

class FichaController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [fichaInstanceList: Ficha.list(params), fichaInstanceTotal: Ficha.count()]
    }

    def create = {
        def fichaInstance = new Ficha()
        fichaInstance.properties = params
        return [fichaInstance: fichaInstance]
    }

    def save = {
        def fichaInstance = new Ficha(params)
        if (fichaInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'ficha.label', default: 'Ficha'), fichaInstance.id])}"
            redirect(action: "show", id: fichaInstance.id)
        }
        else {
            render(view: "create", model: [fichaInstance: fichaInstance])
        }
    }

    def show = {
        def fichaInstance = Ficha.get(params.id)
        if (!fichaInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'ficha.label', default: 'Ficha'), params.id])}"
            redirect(action: "list")
        }
        else {
            [fichaInstance: fichaInstance]
        }
    }

    def edit = {
        def fichaInstance = Ficha.get(params.id)
        if (!fichaInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'ficha.label', default: 'Ficha'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [fichaInstance: fichaInstance]
        }
    }

    def update = {
        def fichaInstance = Ficha.get(params.id)
        if (fichaInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (fichaInstance.version > version) {
                    
                    fichaInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'ficha.label', default: 'Ficha')] as Object[], "Another user has updated this Ficha while you were editing")
                    render(view: "edit", model: [fichaInstance: fichaInstance])
                    return
                }
            }
            fichaInstance.properties = params
            if (!fichaInstance.hasErrors() && fichaInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'ficha.label', default: 'Ficha'), fichaInstance.id])}"
                redirect(action: "show", id: fichaInstance.id)
            }
            else {
                render(view: "edit", model: [fichaInstance: fichaInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'ficha.label', default: 'Ficha'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def fichaInstance = Ficha.get(params.id)
        if (fichaInstance) {
            try {
                fichaInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'ficha.label', default: 'Ficha'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'ficha.label', default: 'Ficha'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'ficha.label', default: 'Ficha'), params.id])}"
            redirect(action: "list")
        }
    }
}
