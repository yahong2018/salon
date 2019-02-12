
Ext.define('app.ux.MapDataMeta', {
  //  extend:'Ext.Component',
    alias:'component_map_data_meta',     
    constructor:function (){
        var me = this;
        this.data = new Array();
        // 添加  
        this.put = function (_key, _value) {
            if (this.containsKey(_key)) {
                this.remove(_key);
            }
            me.data.push({ key: _key, value: _value });
        };
        // 取值  
        this.get = function (_key) {
            var rtn = null;
            try {
                for (var i = 0; i < me.data.length; i++) {
                    if (me.data[i].key == _key) {
                        rtn = this.data[i].value;
                        break;
                    }
                }
            }
            catch (e) {
                rtn = null;
            }
            return rtn;
        };
        // 删除  
        this.remove = function (_key) {
            var btn = false;
            try {
                for (var i = 0; i < me.data.length; i++) {
                    if (me.data[i].key == _key) {
                        me.data.splice(i, 1);
                        return true;
                    }
                }
            }
            catch (e) {
                btn = false;
            }
            return btn;

        };
        // 判断是否存在key  
        this.containsKey = function (_key) {
            var bln = false;
            try {
                for (i = 0; i < me.data.length; i++) {
                    if (me.data[i].key == _key) {
                        bln = true;
                        break;
                    }
                }
            } catch (e) {
                bln = false;
            }
            return bln;
        };
        // 清空  
        this.clear = function () {
            me.data = new Array();
        };

      //  me.callParent(arguments);
    }
});


