Ext.define('app.ux.ModalWindow', {
    extend: 'Ext.window.Window',
    alias:'widget.ModalWindow',
    closeAction:'destroy',
    buttons: [
        '->'
        , {
            text: '保存',
            handler:function(){
                var me = this.up('ModalWindow');
                if(me.doSave){
                    me.doSave(me.parentView,me);
                }
            }
        }, {
            text: '取消',
            handler:function(){
                var me = this.up('ModalWindow');
                me.close();
                me.destroy();
            }
        }
    ]
});