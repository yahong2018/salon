Ext.define('app.ux.dbgrid.DbGridButton',{
    extend:'Ext.button.Button'
   ,xtype:'dbgridbutton'
   ,getGrid:function(){
       return this.ownerCt.dbGrid;     
   },
   listeners: {
       destroy: function (me, eOpts) {
          // debugger;
       }
   }
});