var makeOrgTree =function (jqueryObj,orgId){
	var orgTree= jqueryObj.combotree({
		url:"org/comTree?pid=0",
	    valueField: 'id',
	    textField: 'text',
	    required: false,
	    editable: false,
	    value:orgId,
	    onBeforeExpand:function(node){
	        jqueryObj.combotree("tree").tree("options").url="org/comTree?pid="+node.id;
	    },
	    onLoadSuccess: function (node, data) {
	        jqueryObj.combotree('tree').tree("collapseAll");
	    }
	});
};


var makeOrgTree2 =function (jqueryObj,orgId){
	var orgTree2= jqueryObj.combotree({
		url:"tree/comTree?pid=0",
	    valueField: 'id',
	    textField: 'text',
	    required: false,
	    editable: false,
	    value:orgId,
	    onBeforeExpand:function(node){
	        jqueryObj.combotree("tree").tree("options").url="tree/comTree?pid="+node.id;
	    },
	    onLoadSuccess: function (node, data) {
	        jqueryObj.combotree('tree').tree("collapseAll");
	    }
	});
};