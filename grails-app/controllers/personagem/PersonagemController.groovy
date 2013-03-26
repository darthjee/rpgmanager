package personagem

class PersonagemController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [personagemInstanceList: Personagem.list(params), personagemInstanceTotal: Personagem.count()]
    }

    def create = {
        def personagemInstance = new Personagem()
        personagemInstance.properties = params
        return [personagemInstance: personagemInstance]
    }

    def save = {
        def personagemInstance = new Personagem(params)
        if (personagemInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'personagem.label', default: 'Personagem'), personagemInstance.id])}"
            redirect(action: "show", id: personagemInstance.id)
        }
        else {
            render(view: "create", model: [personagemInstance: personagemInstance])
        }
    }

    def show = {
        def personagemInstance = Personagem.get(params.id)
        if (!personagemInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'personagem.label', default: 'Personagem'), params.id])}"
            redirect(action: "list")
        }
        else {
            [personagemInstance: personagemInstance]
        }
    }

    def edit = {
        def personagemInstance = Personagem.get(params.id)
        if (!personagemInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'personagem.label', default: 'Personagem'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [personagemInstance: personagemInstance]
        }
    }

    def update = {
        def personagemInstance = Personagem.get(params.id)
        if (personagemInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (personagemInstance.version > version) {
                    
                    personagemInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'personagem.label', default: 'Personagem')] as Object[], "Another user has updated this Personagem while you were editing")
                    render(view: "edit", model: [personagemInstance: personagemInstance])
                    return
                }
            }
            personagemInstance.properties = params
            if (!personagemInstance.hasErrors() && personagemInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'personagem.label', default: 'Personagem'), personagemInstance.id])}"
                redirect(action: "show", id: personagemInstance.id)
            }
            else {
                render(view: "edit", model: [personagemInstance: personagemInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'personagem.label', default: 'Personagem'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def personagemInstance = Personagem.get(params.id)
        if (personagemInstance) {
            try {
                personagemInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'personagem.label', default: 'Personagem'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'personagem.label', default: 'Personagem'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'personagem.label', default: 'Personagem'), params.id])}"
            redirect(action: "list")
        }
    }
}
