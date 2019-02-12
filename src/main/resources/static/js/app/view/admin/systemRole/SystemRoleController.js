Ext.define('app.view.admin.systemRole.SystemRoleController', {
    extend: 'Ext.app.ViewController',
    alias: 'controller.SystemRoleController',
    mangeRoleUser: function () {
        alert('管理用户');
    },
    updatePrivilege: function () {
        var privilegeList = [];

        var grid = this.getView().down('SystemRoleGrid');
        var role = grid.getSelectedRecord();
        if (role == null) {
            return;
        }
        var roleId = role.get('recordId');

        var throughTree = function (node) {
            for (var i = 0; i < node.childNodes.length; i++) {
                var child = node.getChildAt(i);
                if (!child.get('checked')) {
                    continue;
                }
                var dataType = child.get('dataType');
                var privilegeCode = 'RUN';
                if (dataType == 'app.model.admin.ProgramPrivilegeModel') {
                    privilegeCode = child.get('privilegeCode');
                }
                privilegeList.push({
                    privilegeCode: privilegeCode,
                    programId: child.get('programId'),
                    recordId: child.get("recordId")
                });
                throughTree(child);
            }
        };
        var node = this.getView().down('RolePrivilegePanel').getRootNode();
        throughTree(node);

        this.getView().down('SystemRoleGrid').getStore().updateRolePrivilege.apply(this, [role, privilegeList, function () {
            Ext.Msg.alert('系统提示', '权限更新成功');
        }]);
    },
    gridSelectionChanged: function (model, selected, index) {
        this.getView().down('SystemRoleGrid').getStore().loadRolePrivileges.apply(this, [selected, this.showPrivilege]);
    },

    showPrivilege: function (role, privileges) {
        var roleId = role.get('recordId');
        var tree = this.getView().down('RolePrivilegePanel');

        var setPrivilege = function (node) {
            var programId = node.get('programId');
            var privilegeCode = 'RUN';
            var dataType = node.get('dataType');
            if (dataType == 'app.model.admin.ProgramPrivilegeModel') {
                privilegeCode = node.get('privilegeCode');
            }
            var hasPrivilege = false;
            for (var j = 0; j < privileges.length; j++) {
                var privilege = privileges[j];
                if (privilege.privilegeCode == privilegeCode && privilege.programId == programId) {
                    hasPrivilege = true;
                    break;
                }
            }
            node.set('checked', hasPrivilege);
            for (var i = 0; i < node.childNodes.length; i++) {
                var child = node.getChildAt(i);
                setPrivilege(child);
            }
        };

        var node = tree.getRootNode();
        setPrivilege(node);
    },

    checkNodeChildren: function (node, revert) {
        for (var i = 0; i < node.childNodes.length; i++) {
            var child = node.getChildAt(i);
            var checked = true;
            if (revert == true) {
                checked = !child.get('checked');
            }
            child.set('checked', checked);
            this.checkNodeChildren(child, revert);
        }
        this.checkchange(node, node.get('checked'));
    },
    selectAllWhole: function () {
        var tree = this.getView().down('RolePrivilegePanel');
        var node = tree.getRootNode();

        node.set('checked', true);
        this.checkNodeChildren(node);
    },
    selectAllChilren: function () {
        var tree = this.getView().down('RolePrivilegePanel');
        var selected = tree.getSelectionModel().getSelected();
        if (selected.getCount() > 0) {
            selected = selected.getAt(0);
        } else {
            return;
        }

        selected.set('checked', true);
        this.checkNodeChildren(selected);
    },

    revertSelectAllWhole: function () {
        var tree = this.getView().down('RolePrivilegePanel');
        var node = tree.getRootNode();

        node.set('checked', !node.get('chekced'));
        this.checkNodeChildren(node, true);
    },
    revertSelectAllChilren: function () {
        var tree = this.getView().down('RolePrivilegePanel');
        var selected = tree.getSelectionModel().getSelected();
        if (selected.getCount() > 0) {
            selected = selected.getAt(0);
        } else {
            return;
        }

        selected.set('checked', !selected.get('chekced'));
        this.checkNodeChildren(selected, true);
    },
    checkchange: function (node, checked, e, eOpts) {
        // 1.如果下级选择了，上级必须具选中         
        // 2.如果本级取消选择，则取消选择所有的下级
        var checkParentNode = function (theNode) {
            if (theNode.parentNode) {
                theNode.parentNode.set('checked', true);
                checkParentNode(theNode.parentNode);
            }
        };

        var unCheckChildren = function (theNode) {
            for (var i = 0; i < theNode.childNodes.length; i++) {
                var child = theNode.getChildAt(i);
                child.set('checked', false);
                unCheckChildren(child);
            }
        };
        if (checked) {
            checkParentNode(node);
        } else {
            unCheckChildren(node);
        }
    }
});