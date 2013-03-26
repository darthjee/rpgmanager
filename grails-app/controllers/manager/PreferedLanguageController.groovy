package manager

class PreferedLanguageController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [preferedLanguageInstanceList: PreferedLanguage.list(params), preferedLanguageInstanceTotal: PreferedLanguage.count()]
    }

    def create = {
        def preferedLanguageInstance = new PreferedLanguage()
        preferedLanguageInstance.properties = params
        return [preferedLanguageInstance: preferedLanguageInstance]
    }

    def save = {
        def preferedLanguageInstance = new PreferedLanguage(params)
        if (preferedLanguageInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'preferedLanguage.label', default: 'PreferedLanguage'), preferedLanguageInstance.id])}"
            redirect(action: "show", id: preferedLanguageInstance.id)
        }
        else {
            render(view: "create", model: [preferedLanguageInstance: preferedLanguageInstance])
        }
    }

    def show = {
        def preferedLanguageInstance = PreferedLanguage.get(params.id)
        if (!preferedLanguageInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'preferedLanguage.label', default: 'PreferedLanguage'), params.id])}"
            redirect(action: "list")
        }
        else {
            [preferedLanguageInstance: preferedLanguageInstance]
        }
    }

    def edit = {
        def preferedLanguageInstance = PreferedLanguage.get(params.id)
        if (!preferedLanguageInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'preferedLanguage.label', default: 'PreferedLanguage'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [preferedLanguageInstance: preferedLanguageInstance]
        }
    }

    def update = {
        def preferedLanguageInstance = PreferedLanguage.get(params.id)
        if (preferedLanguageInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (preferedLanguageInstance.version > version) {
                    
                    preferedLanguageInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'preferedLanguage.label', default: 'PreferedLanguage')] as Object[], "Another user has updated this PreferedLanguage while you were editing")
                    render(view: "edit", model: [preferedLanguageInstance: preferedLanguageInstance])
                    return
                }
            }
            preferedLanguageInstance.properties = params
            if (!preferedLanguageInstance.hasErrors() && preferedLanguageInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'preferedLanguage.label', default: 'PreferedLanguage'), preferedLanguageInstance.id])}"
                redirect(action: "show", id: preferedLanguageInstance.id)
            }
            else {
                render(view: "edit", model: [preferedLanguageInstance: preferedLanguageInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'preferedLanguage.label', default: 'PreferedLanguage'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def preferedLanguageInstance = PreferedLanguage.get(params.id)
        if (preferedLanguageInstance) {
            try {
                preferedLanguageInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'preferedLanguage.label', default: 'PreferedLanguage'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'preferedLanguage.label', default: 'PreferedLanguage'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'preferedLanguage.label', default: 'PreferedLanguage'), params.id])}"
            redirect(action: "list")
        }
    }
}
