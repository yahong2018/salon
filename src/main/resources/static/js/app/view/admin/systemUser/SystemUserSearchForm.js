Ext.define('app.view.admin.systemUser.SystemUserSearchForm',{
    extend:"Ext.form.Panel",
    xtype:'app_view_admin_systemUser_SystemUserSearchForm',

    width:400,
    height:200,
    getSearchExpr:function(){
        //return "USER_ID='C00001' or USER_NAME like 't%'";
    }
});