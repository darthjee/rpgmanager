package personagem.ded35

class RacaController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [racaInstanceList: Raca.list(params), racaInstanceTotal: Raca.count()]
    }

    def create = {
        def racaInstance = new Raca()
        racaInstance.properties = params
        def racaTranslateInstance = new RacaTranslate()
        racaTranslateInstance.properties = params
        return [racaInstance: racaInstance,racaTranslateInstance:racaTranslateInstance]
    }

    def save = {
        Raca.withTransaction {status ->
          def racaTranslateInstance = new RacaTranslate()
          def racaInstance = new Raca()
          racaTranslateInstance.properties = params
          racaTranslateInstance.raca = racaInstance
          if (!racaTranslateInstance.hasErrors() && !racaInstance.hasErrors() && racaInstance.save(flush: true) && (racaTranslateInstance.raca = racaInstance) && racaTranslateInstance.save(flush: true)) {
              flash.message = "${message(code: 'default.created.message', args: [message(code: 'raca.label', default: 'Raca'), racaInstance.id])}"
              redirect(action: "show", id: racaInstance.id)
          }
          else {
            status.setRollbackOnly()
            render(view: "create", model: [racaInstance: racaInstance,racaTranslateInstance:racaTranslateInstance])
          }
        }

    }

    def show = {
        def racaInstance = Raca.get(params.id)
        if (!racaInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'raca.label', default: 'Raca'), params.id])}"
            redirect(action: "list")
        }
        else {
            [racaInstance: racaInstance]
        }
    }

    def edit = {
        def racaInstance = Raca.get(params.id)
        if (!racaInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'raca.label', default: 'Raca'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [racaInstance: racaInstance]
        }
    }

    def update = {
        def racaInstance = Raca.get(params.id)
        if (racaInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (racaInstance.version > version) {
                    
                    racaInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'raca.label', default: 'Raca')] as Object[], "Another user has updated this Raca while you were editing")
                    render(view: "edit", model: [racaInstance: racaInstance])
                    return
                }
            }
            racaInstance.properties = params
            if (!racaInstance.hasErrors() && racaInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'raca.label', default: 'Raca'), racaInstance.id])}"
                redirect(action: "show", id: racaInstance.id)
            }
            else {
                render(view: "edit", model: [racaInstance: racaInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'raca.label', default: 'Raca'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def racaInstance = Raca.get(params.id)
        if (racaInstance) {
            try {
                def trans
                while (trans = racaInstance.getTrans()){ trans.delete(flush: true); racaInstance.trans=null }
                racaInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'raca.label', default: 'Raca'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'raca.label', default: 'Raca'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'raca.label', default: 'Raca'), params.id])}"
            redirect(action: "list")
        }
    }
}
