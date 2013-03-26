package personagem.ded35

class TranslateController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [translateInstanceList: Translate.list(params), translateInstanceTotal: Translate.count()]
    }

    def create = {
        def translateInstance = new Translate()
        translateInstance.properties = params
        return [translateInstance: translateInstance]
    }

    def save = {
        def translateInstance = new Translate(params)
        if (translateInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'translate.label', default: 'Translate'), translateInstance.id])}"
            redirect(action: "show", id: translateInstance.id)
        }
        else {
            render(view: "create", model: [translateInstance: translateInstance])
        }
    }

    def show = {
        def translateInstance = Translate.get(params.id)
        if (!translateInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'translate.label', default: 'Translate'), params.id])}"
            redirect(action: "list")
        }
        else {
            [translateInstance: translateInstance]
        }
    }

    def edit = {
        def translateInstance = Translate.get(params.id)
        if (!translateInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'translate.label', default: 'Translate'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [translateInstance: translateInstance]
        }
    }

    def update = {
        def translateInstance = Translate.get(params.id)
        if (translateInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (translateInstance.version > version) {
                    
                    translateInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'translate.label', default: 'Translate')] as Object[], "Another user has updated this Translate while you were editing")
                    render(view: "edit", model: [translateInstance: translateInstance])
                    return
                }
            }
            translateInstance.properties = params
            if (!translateInstance.hasErrors() && translateInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'translate.label', default: 'Translate'), translateInstance.id])}"
                redirect(action: "show", id: translateInstance.id)
            }
            else {
                render(view: "edit", model: [translateInstance: translateInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'translate.label', default: 'Translate'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def translateInstance = Translate.get(params.id)
        if (translateInstance) {
            try {
                translateInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'translate.label', default: 'Translate'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'translate.label', default: 'Translate'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'translate.label', default: 'Translate'), params.id])}"
            redirect(action: "list")
        }
    }
}
