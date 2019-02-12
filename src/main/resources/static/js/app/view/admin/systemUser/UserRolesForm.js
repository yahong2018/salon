Ext.define('app.view.admin.systemUser.UserRolesForm',{
    extend:'Ext.form.Panel',
    xtype:'systemUser_UserRolesForm',
    allRoles:[],    
    items:[
        {
            xtype:'checkboxgroup',
            columns: 3,
            items:[]
        }
    ],   

    initComponent:function(){
        var roleGroup = this.items[0];
        roleGroup.items=[];
        for (var i = 0; i < this.allRoles.length; i++) {
            var role = this.allRoles[i];
            roleGroup.items.push(
                {
                    boxLabel: role.get('roleName'),
                    inputValue: role.get('recordId'),
                    name: 'userRoles',                            
                });
        }        
        this.callParent(arguments);        
    }
});