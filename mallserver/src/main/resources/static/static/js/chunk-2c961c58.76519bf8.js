(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-2c961c58"],{"1ab6":function(t,e,a){},dac3:function(t,e,a){"use strict";var l=a("1ab6"),n=a.n(l);n.a},f5ba:function(t,e,a){"use strict";a.r(e);var l=function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",[a("el-table",{staticStyle:{width:"100%"},attrs:{data:t.friendList,border:""}},[a("el-table-column",{attrs:{label:"商品id",width:"100"},scopedSlots:t._u([{key:"default",fn:function(e){return[a("span",{staticStyle:{"margin-left":"10px"}},[t._v(t._s(e.row.id))])]}}])}),t._v(" "),a("el-table-column",{attrs:{label:"商品名",width:"100"},scopedSlots:t._u([{key:"default",fn:function(e){return[a("span",{staticStyle:{"margin-left":"10px"}},[t._v(t._s(e.row.name))])]}}])}),t._v(" "),a("el-table-column",{staticStyle:{width:"100%"},attrs:{label:"商品价格"},scopedSlots:t._u([{key:"default",fn:function(e){return[a("span",{staticStyle:{"margin-left":"10px"}},[t._v(t._s(e.row.price))])]}}])}),t._v(" "),a("el-table-column",{staticStyle:{width:"100%"},attrs:{label:"商品标签"},scopedSlots:t._u([{key:"default",fn:function(e){return[a("span",{staticStyle:{"margin-left":"10px"}},[t._v(t._s(e.row.label))])]}}])}),t._v(" "),a("el-table-column",{staticStyle:{width:"100%"},attrs:{label:"商品库存"},scopedSlots:t._u([{key:"default",fn:function(e){return[a("span",{staticStyle:{"margin-left":"10px"}},[t._v(t._s(e.row.count))])]}}])}),t._v(" "),a("el-table-column",{staticStyle:{width:"100%"},attrs:{label:"操作"},scopedSlots:t._u([{key:"default",fn:function(e){return[a("el-button",{attrs:{size:"mini",type:"danger"},on:{click:function(a){return t.deleteShop(e.$index,e.row)}}},[t._v("删除")]),t._v(" "),a("el-button",{attrs:{size:"mini",type:"success"},on:{click:function(a){return t.detail(e.$index,e.row)}}},[t._v("详细信息")])]}}])})],1),t._v(" "),a("br"),t._v(" "),a("el-pagination",{staticClass:"page",attrs:{background:"",layout:"prev, pager, next",total:t.friendPage}}),t._v(" "),a("el-dialog",{attrs:{title:"用户信息",visible:t.dialogFormVisible,width:"75%"},on:{"update:visible":function(e){t.dialogFormVisible=e}}},[a("el-card",{staticClass:"mes"},[a("el-form",{ref:"numberValidateForm",staticClass:"el-form",attrs:{"label-position":"left","label-width":"70px"}},[a("el-form-item",{attrs:{label:"描述"}},[a("el-alert",{attrs:{type:"success",closable:!1}},[t._v(t._s(t.detailInfo.description))])],1),t._v(" "),a("el-form-item",{attrs:{label:"创建时间"}},[a("el-alert",{attrs:{type:"warning",closable:!1}},[t._v(t._s(t.detailInfo.createTime))])],1),t._v(" "),a("el-form-item",{attrs:{label:"商品视频"}},[a("el-alert",{attrs:{type:"warning",closable:!1}},[t._v(t._s(t.detailInfo.video))])],1),t._v(" "),a("el-form-item",{attrs:{label:"备注"}},[a("el-alert",{attrs:{type:"error",closable:!1}},[t._v(t._s(t.detailInfo.status))])],1)],1)],1),t._v(" "),a("div",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"})],1)],1)},n=[],i=a("f8b7"),r={data:function(){return{friendList:[],friendPage:10,dialogFormVisible:!1,detailInfo:[]}},mounted:function(){var t=this;Object(i["b"])({page:0}).then((function(e){console.log(e.data),t.friendList=e.data}))},methods:{detail:function(t,e){this.detailInfo=e,this.dialogFormVisible=!0},deleteShop:function(t,e){var a=this;Object(i["a"])({id:e.id}).then((function(e){a.$message(e.data),a.friendList.splice(t,1)}))}}},o=r,s=(a("dac3"),a("2877")),c=Object(s["a"])(o,l,n,!1,null,"6b96b1c6",null);e["default"]=c.exports},f8b7:function(t,e,a){"use strict";a.d(e,"c",(function(){return n})),a.d(e,"b",(function(){return i})),a.d(e,"a",(function(){return r}));var l=a("b775");function n(t){return Object(l["a"])({url:"/admin/mallOrder/getOrdByPage",method:"get",params:t})}function i(t){return Object(l["a"])({url:"/mall/commodity/getMallShop",method:"get",params:t})}function r(t){return Object(l["a"])({url:"/mall/commodity/delete",method:"delete",params:t})}}}]);