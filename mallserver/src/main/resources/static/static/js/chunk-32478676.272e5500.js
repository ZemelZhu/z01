(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-32478676"],{1288:function(t,e,a){"use strict";var l=a("b914"),n=a.n(l);n.a},b914:function(t,e,a){},f5ba:function(t,e,a){"use strict";a.r(e);var l=function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",[a("el-table",{staticStyle:{width:"100%"},attrs:{data:t.friendList,border:""}},[a("el-table-column",{attrs:{label:"商品id",width:"100"},scopedSlots:t._u([{key:"default",fn:function(e){return[a("span",{staticStyle:{"margin-left":"10px"}},[t._v(t._s(e.row.id))])]}}])}),t._v(" "),a("el-table-column",{attrs:{label:"商品名",width:"100"},scopedSlots:t._u([{key:"default",fn:function(e){return[a("span",{staticStyle:{"margin-left":"10px"}},[t._v(t._s(e.row.name))])]}}])}),t._v(" "),a("el-table-column",{staticStyle:{width:"100%"},attrs:{label:"商品价格"},scopedSlots:t._u([{key:"default",fn:function(e){return[a("span",{staticStyle:{"margin-left":"10px"}},[t._v(t._s(e.row.price))])]}}])}),t._v(" "),a("el-table-column",{staticStyle:{width:"100%"},attrs:{label:"商品标签"},scopedSlots:t._u([{key:"default",fn:function(e){return[a("span",{staticStyle:{"margin-left":"10px"}},[t._v(t._s(e.row.label))])]}}])}),t._v(" "),a("el-table-column",{staticStyle:{width:"100%"},attrs:{label:"商品库存"},scopedSlots:t._u([{key:"default",fn:function(e){return[a("span",{staticStyle:{"margin-left":"10px"}},[t._v(t._s(e.row.count))])]}}])}),t._v(" "),a("el-table-column",{staticStyle:{width:"100%"},attrs:{label:"操作"},scopedSlots:t._u([{key:"default",fn:function(e){return[a("el-button",{attrs:{size:"mini",type:"danger"},on:{click:function(a){return t.deleteShop(e.$index,e.row)}}},[t._v("删除")]),t._v(" "),a("el-button",{attrs:{size:"mini",type:"success"},on:{click:function(a){return t.detail(e.$index,e.row)}}},[t._v("详细信息")])]}}])})],1),t._v(" "),a("br"),t._v(" "),a("el-pagination",{staticClass:"page",attrs:{background:"",layout:"prev, pager, next",total:t.friendPage},on:{"current-change":t.handleCurrentChange}}),t._v(" "),a("el-dialog",{attrs:{title:"商品图片",visible:t.dialogFormVisible,width:"75%"},on:{"update:visible":function(e){t.dialogFormVisible=e}}},[a("el-carousel",{attrs:{interval:4e3,type:"card",height:"200px"}},t._l(t.images,(function(e){return a("el-carousel-item",{key:e},[a("el-image",{attrs:{src:e}}),t._v("\n        "+t._s(e)+"\n      ")],1)})),1),t._v(" "),a("el-card",{staticClass:"mes"},[a("el-form",{ref:"numberValidateForm",staticClass:"el-form",attrs:{"label-position":"left","label-width":"70px"}},[a("el-form-item",{attrs:{label:"商品信息"}},[a("el-badge",{staticClass:"item",attrs:{value:"商品名称"}},[a("el-button",{attrs:{size:"small"}},[t._v(t._s(t.detailInfo.name))])],1),t._v(" "),a("el-badge",{staticClass:"item",attrs:{value:t.detailInfo.count,type:"success"}},[a("el-button",{attrs:{size:"small"}},[t._v("商品库存")])],1),t._v(" "),a("el-badge",{staticClass:"item",attrs:{value:t.detailInfo.price,type:"warning"}},[a("el-button",{attrs:{size:"small"}},[t._v("商品价格")])],1),t._v(" "),a("el-badge",{staticClass:"item",attrs:{value:"商品标签",type:"primary"}},[a("el-button",{attrs:{size:"small"}},[t._v(t._s(t.detailInfo.label))])],1)],1),t._v(" "),a("el-form-item",{attrs:{label:"商品视频"}},[a("a",{attrs:{href:t.detailInfo.video,target:"_blank"}},[t._v(t._s(t.detailInfo.video))])]),t._v(" "),a("el-form-item",{attrs:{label:"描述"}},[a("el-alert",{attrs:{type:"success"}},[t._v(t._s(t.detailInfo.desc))])],1)],1)],1),t._v(" "),a("div",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"})],1)],1)},n=[],i=a("f8b7"),s={data:function(){return{friendList:[],friendPage:0,dialogFormVisible:!1,detailInfo:[],images:[]}},mounted:function(){var t=this;Object(i["d"])().then((function(e){t.friendPage=e.data})),Object(i["b"])({page:0}).then((function(e){console.log(e.data),t.friendList=e.data}))},methods:{detail:function(t,e){this.detailInfo=e,this.dialogFormVisible=!0,this.images=e.picture},deleteShop:function(t,e){var a=this;Object(i["a"])({id:e.id}).then((function(e){a.$message(e.data),a.friendList.splice(t,1)}))},handleCurrentChange:function(t){var e=this;Object(i["b"])({page:t-1}).then((function(t){console.log(t.data),e.friendList=t.data}))}}},r=s,o=(a("1288"),a("2877")),c=Object(o["a"])(r,l,n,!1,null,"6420fd57",null);e["default"]=c.exports},f8b7:function(t,e,a){"use strict";a.d(e,"c",(function(){return n})),a.d(e,"b",(function(){return i})),a.d(e,"a",(function(){return s})),a.d(e,"d",(function(){return r}));var l=a("b775");function n(t){return Object(l["a"])({url:"/admin/mallOrder/getOrdByPage",method:"get",params:t})}function i(t){return Object(l["a"])({url:"/mall/commodity/getMallShop",method:"get",params:t})}function s(t){return Object(l["a"])({url:"/admin/commodity/delete",method:"delete",params:t})}function r(){return Object(l["a"])({url:"/mall/commodity/getMallAllShopCount",method:"get"})}}}]);