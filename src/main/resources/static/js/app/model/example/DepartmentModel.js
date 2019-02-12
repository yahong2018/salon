Ext.define("app.model.example.DepartmentModel",{
    extend:"Ext.data.TreeModel",    
    requires:["app.model.EmptyGenerator","app.model.TrackableModel"],    
    mixins:["app.model.TrackableModel"],    
    identifier:"empty",
    fields:[
        {name:"deptId",type:"string",unique:true},
        {name:"deptName",type:"string"},
        {name:"parentDeptId",type:"string"},
        {name:"remark",type:"string"},

    ],
    idProperty:"deptId",
});