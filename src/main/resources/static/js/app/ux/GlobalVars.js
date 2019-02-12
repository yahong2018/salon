Ext.define('app.ux.GlobalVars', {
    singleton: true,

    currentLogin: {},
    jsRoot: '',
    webRoot: '',

    hasPrivilege: function (programId, privilegeId) {
        if (this.currentLogin.privileges == null) {
            return false;
        }

        for (var i = 0; i < this.currentLogin.privileges.length; i++) {
            var privilege = this.currentLogin.privileges[i];
            if (privilege.programId == programId && privilege.privilegeId == privilegeId) {
                return true;
            }
        }
        return false;
    }
});