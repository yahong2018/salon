Ext.define("app.ux.ZhxhDate", {
    extend: "Ext.data.field.Date",
    alias: 'data.field.zhxhDate',

    convert: function (v) {
        if (v == null) {
            return null;
        }
        var date = new Date(v);
        var dateFormat = this.dateReadFormat || this.dateFormat;

        return Ext.Date.format(date, dateFormat);
    }
});