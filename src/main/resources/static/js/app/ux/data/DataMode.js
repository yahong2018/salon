Ext.define("app.ux.data.DataMode", {
    singleton: true,

    INSERT: { value: 'INSERT' },
    EDIT: { value: 'EDIT' },
    DELETE: { value: 'DELETE' },
    BROWSE: { value: "BROWSE" },
    POST: { value: 'POST' },
});

Ext.define("app.ux.data.DataOperationSeq", {
    singleton: true,

    BEFORE: { value: 'BEFORE' },
    AFTER: { value: 'AFTER' },
})