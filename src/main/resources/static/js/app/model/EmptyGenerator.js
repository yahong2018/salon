Ext.define('app.model.EmptyGenerator', {
    extend: 'Ext.data.identifier.Generator',
    alias: 'data.identifier.empty',    

    generate: function () {
        return "";
    }
});