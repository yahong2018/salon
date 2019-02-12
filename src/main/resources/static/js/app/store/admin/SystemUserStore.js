Ext.define('app.store.admin.SystemUserStore', {
    extend: 'app.store.BaseStore',
    model: 'app.model.admin.SystemUserModel',
    alias: 'widget.app_store_admin_SystemUserStore',
    uses: ['app.model.admin.UserRoleModel', 'app.ux.Utils','Ext.window.Toast'],
    dao: {
        deleteUrl: '/admin/systemUsers/delete.handler',
        insertUrl: '/admin/systemUsers/openLoginAccount.handler',
        updateUrl: '/admin/systemUsers/update.handler',
        selectUrl: '/admin/systemUsers/getAllUsers.handler',
    },

    restePassword: function (user) {
        var me = this;
        app.ux.Utils.ajaxRequest({
            url: webRoot + '/admin/systemUsers/resetPassword.handler?userId=' + user.get('recordId'),
            successCallback: function (record, response, opts) {
                Ext.Msg.alert('系统提示', '密码已重设为系统缺省密码!');
            }
        });
    },
    startUser: function (user) {
        var me = this;
        app.ux.Utils.ajaxRequest({
            url: webRoot + '/admin/systemUsers/enableUser.handler?userId=' + user.get('recordId'),
            successCallback: function (record, response, opts) {
                user.beginEdit();
                user.set('userStatus', 0);
                user.endEdit();
                me.commitChanges();
            }
        });
    },
    stopUser: function (user) {
        var me = this;
        app.ux.Utils.ajaxRequest({
            url: webRoot + '/admin/systemUsers/disableUser.handler?userId=' + user.get('recordId'),
            successCallback: function (record, response, opts) {
                user.beginEdit();
                user.set('userStatus', 1);
                user.endEdit();
                me.commitChanges();
            }
        });
    },
    beforeUpdate: function (current, old) {
        current.password = old.get('password');
    },
    getUserRoles: function (user) {
        if (user.userRoles != null) {
            return;
        }
        
        app.ux.Utils.ajaxRequest({
            url: webRoot + '/admin/systemUsers/getUserRoles.handler?userId=' + user.get('recordId'),
            successCallback: function (result, response, opts) {
                user.userRoles = [];
                for (var i = 0; i < result.length; i++) {
                    user.userRoles.push(Ext.create('app.model.admin.UserRoleModel', result[i]));
                }
            }
        });
    },
    updateUserRoles: function (win, user, roles) {
        var userRoles = [];
        var userId = user.get('recordId');
        for (var i = 0; i < roles.length; i++) {
            userRoles.push({
                userId: user.get('recordId'),
                roleId: roles[i].get('recordId')
            });
        }

        app.ux.Utils.ajaxRequest({
            url: webRoot + '/admin/systemUsers/updateUserRoles.handler?userId=' + userId,
            method: 'POST',
            jsonData: userRoles,
            successCallback: function (result, response, opts) {
                user.userRoles = roles;
                win.close();
                win.destroy();

                Ext.toast({
                    html: '数据已保存',
                    title: '系统提示',
                    width: 200,
                    align: 't'
                });
            }
        });
    }
});