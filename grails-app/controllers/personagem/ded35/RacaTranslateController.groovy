package personagem.ded35

class RacaTranslateController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [racaTranslateInstanceList: RacaTranslate.list(params), racaTranslateInstanceTotal: RacaTranslate.count()]
    }

    def create = {
        def racaTranslateInstance = new RacaTranslate()
        racaTranslateInstance.properties = params
        return [racaTranslateInstance: racaTranslateInstance]
    }

    def save = {
        def racaTranslateInstance = new RacaTranslate(params)
        if (racaTranslateInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'racaTranslate.label', default: 'RacaTranslate'), racaTranslateInstance.id])}"
            redirect(action: "show", id: racaTranslateInstance.id)
        }
        else {
            render(view: "create", model: [racaTranslateInstance: racaTranslateInstance])
        }
    }

    def show = {
        def racaTranslateInstance = RacaTranslate.get(params.id)
        if (!racaTranslateInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'racaTranslate.label', default: 'RacaTranslate'), params.id])}"
            redirect(action: "list")
        }
        else {
            [racaTranslateInstance: racaTranslateInstance]
        }
    }

    def edit = {
        def racaTranslateInstance = RacaTranslate.get(params.id)
        if (!racaTranslateInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'racaTranslate.label', default: 'RacaTranslate'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [racaTranslateInstance: racaTranslateInstance]
        }
    }

    def update = {
        def racaTranslateInstance = RacaTranslate.get(params.id)
        if (racaTranslateInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (racaTranslateInstance.version > version) {
                    
                    racaTranslateInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'racaTranslate.label', default: 'RacaTranslate')] as Object[], "Another user has updated this RacaTranslate while you were editing")
                    render(view: "edit", model: [racaTranslateInstance: racaTranslateInstance])
                    return
                }
            }
            racaTranslateInstance.properties = params
            if (!racaTranslateInstance.hasErrors() && racaTranslateInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'racaTranslate.label', default: 'RacaTranslate'), racaTranslateInstance.id])}"
                redirect(action: "show", id: racaTranslateInstance.id)
            }
            else {
                render(view: "edit", model: [racaTranslateInstance: racaTranslateInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'racaTranslate.label', default: 'RacaTranslate'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def racaTranslateInstance = RacaTranslate.get(params.id)
        if (racaTranslateInstance) {
            try {
                racaTranslateInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'racaTranslate.label', default: 'RacaTranslate'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'racaTranslate.label', default: 'RacaTranslate'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'racaTranslate.label', default: 'RacaTranslate'), params.id])}"
            redirect(action: "list")
        }
    }
}
