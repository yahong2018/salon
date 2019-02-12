Ext.define('app.view.admin.systemUser.SystemUserController', {
    extend: 'Ext.app.ViewController',
    alias: 'controller.SystemUserController',
    uses: ['app.view.admin.systemUser.UserRolesForm', 'app.ux.ModalWindow', 'app.store.admin.SystemRoleStore'],

    resetPassword: function () {
        var grid = this.getView();
        app.ux.Utils.verifySelection(grid, function (record) {
            Ext.Msg.confirm('系统提示', '是否要重设该账号的密码？', function (buttonId) {
                if (buttonId == 'yes') {
                    grid.getStore().restePassword(record);
                }
            });
        });
    },
    loadAllRoles: function () {
        var me = this;
        var roleStore = Ext.create({ xtype: 'app_store_admin_SystemRoleStore', autoLoad: false });
        roleStore.load(function (records, operation, success) {
            me.getView().getViewModel().set('allRoles', records);
        });
    },
    gridSelectionChanged: function (model, selected, index) {
        if (!selected) {
            return;
        }
        if (selected.userRoles) {
            return;
        }
        this.getView().getStore().getUserRoles(selected);
    },
    startUser: function () {
        var grid = this.getView();
        app.ux.Utils.verifySelection(grid, function (record) {
            grid.getStore().startUser(record);
        });
    },
    stopUser: function () {
        var grid = this.getView();
        app.ux.Utils.verifySelection(grid, function (record) {
            grid.getStore().stopUser(record);
        });
    },
    updateRoles: function () {
        var me = this;
        var grid = me.getView();
        var doSaveMethod = me.doUpdateRoles;
        var users = grid.getSelectionModel().getSelection();
        if (users == null || users.length == 0) {
            return;
        }

        var deailWindow = Ext.create('app.ux.ModalWindow', {
            modal: true,
            width: 400,
            title: '系统授权',
            parentView: grid,
            doSave: doSaveMethod,
            items: [{
                xtype: 'systemUser_UserRolesForm',
                allRoles: grid.getViewModel().get('allRoles')
            }]
        });
        var form = deailWindow.down('systemUser_UserRolesForm');
        var checkboxGroup = form.down('checkboxgroup');
        var userRoleIdList = [];
        for (var i = 0; i < users[0].userRoles.length; i++) {
            userRoleIdList.push(users[0].userRoles[i].get('recordId') + "");
        }
        checkboxGroup.setValue({ userRoles: userRoleIdList });

        deailWindow.show();
    },

    doUpdateRoles: function (grid, win) {
        var checkboxGroup = win.down('checkboxgroup');
        var roleIdList = checkboxGroup.getValue().userRoles;
        var roles = [];
        var allRoles = grid.getViewModel().get('allRoles');
        if ((typeof roleIdList) === "string") {
            var tmp = [];
            tmp.push(roleIdList);
            roleIdList = tmp;
        } else if ((typeof roleIdList) === "undefined") {
            roleIdList = [];
        }

        for (var i = 0; i < roleIdList.length; i++) {
            for (var j = 0; j < allRoles.length; j++) {
                if (allRoles[j].get('recordId') == roleIdList[i]) {
                    roles.push(allRoles[j]);
                    break;
                }
            }
        }
        var user = grid.getSelectionModel().getSelection()[0];
        var userId = user.get('recordId');
        grid.getStore().updateUserRoles(win, user, roles);
    }
});