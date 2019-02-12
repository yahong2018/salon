Ext.define('app.Application', {
	extend: 'Ext.app.Application',
	name: 'app',
	appFolder: jsRoot + '/app',
	
	views: [],
	controllers: [],
	stores: [],
	launch: function() {
        Ext.override(Ext.form.field.Base,{
            initComponent:function(){
                if(this.allowBlank!==undefined && !this.allowBlank){
                    if(this.fieldLabel){
                        this.fieldLabel += '<font color=red>*</font>';
                    }
                }
                this.callParent(arguments);
            }
        });
	}
});