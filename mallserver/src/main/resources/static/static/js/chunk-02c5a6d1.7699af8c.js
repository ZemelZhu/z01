(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-02c5a6d1"],{"0fc0":function(t,e,l){},"7fee":function(t,e,l){"use strict";var a=l("0fc0"),n=l.n(a);n.a},f5ba:function(t,e,l){"use strict";l.r(e);var a=function(){var t=this,e=t.$createElement,l=t._self._c||e;return l("div",[l("el-table",{staticStyle:{width:"100%"},attrs:{data:t.friendList,border:""}},[l("el-table-column",{attrs:{label:"商品id",width:"100"},scopedSlots:t._u([{key:"default",fn:function(e){return[l("span",{staticStyle:{"margin-left":"10px"}},[t._v(t._s(e.row.id))])]}}])}),t._v(" "),l("el-table-column",{attrs:{label:"商品名",width:"100"},scopedSlots:t._u([{key:"default",fn:function(e){return[l("span",{staticStyle:{"margin-left":"10px"}},[t._v(t._s(e.row.name))])]}}])}),t._v(" "),l("el-table-column",{staticStyle:{width:"100%"},attrs:{label:"商品价格"},scopedSlots:t._u([{key:"default",fn:function(e){return[l("span",{staticStyle:{"margin-left":"10px"}},[t._v(t._s(e.row.price))])]}}])}),t._v(" "),l("el-table-column",{staticStyle:{width:"100%"},attrs:{label:"商品标签"},scopedSlots:t._u([{key:"default",fn:function(e){return[l("span",{staticStyle:{"margin-left":"10px"}},[t._v(t._s(e.row.label))])]}}])}),t._v(" "),l("el-table-column",{staticStyle:{width:"100%"},attrs:{label:"商品库存"},scopedSlots:t._u([{key:"default",fn:function(e){return[l("span",{staticStyle:{"margin-left":"10px"}},[t._v(t._s(e.row.count))])]}}])}),t._v(" "),l("el-table-column",{staticStyle:{width:"100%"},attrs:{label:"操作"},scopedSlots:t._u([{key:"default",fn:function(e){return[l("el-button",{attrs:{size:"mini",type:"danger"},on:{click:function(l){return t.deleteShop(e.$index,e.row)}}},[t._v("删除")]),t._v(" "),l("el-button",{attrs:{size:"mini",type:"success"},on:{click:function(l){return t.detail(e.$index,e.row)}}},[t._v("详细信息")])]}}])})],1),t._v(" "),l("br"),t._v(" "),l("el-pagination",{staticClass:"page",attrs:{background:"",layout:"prev, pager, next",total:t.friendPage}}),t._v(" "),l("el-dialog",{attrs:{title:"用户信息",visible:t.dialogFormVisible,width:"75%"},on:{"update:visible":function(e){t.dialogFormVisible=e}}},[l("el-card",{staticClass:"mes"},[l("el-form",{ref:"numberValidateForm",staticClass:"el-form",attrs:{"label-position":"left","label-width":"70px"}},[l("el-form-item",{attrs:{label:"描述"}},[l("el-alert",{attrs:{type:"success",closable:!1}},[t._v(t._s(t.detailInfo.description))])],1),t._v(" "),l("el-form-item",{attrs:{label:"创建时间"}},[l("el-alert",{attrs:{type:"warning",closable:!1}},[t._v(t._s(t.detailInfo.createTime))])],1),t._v(" "),l("el-form-item",{attrs:{label:"商品视频"}},[l("el-alert",{attrs:{type:"warning",closable:!1}},[t._v(t._s(t.detailInfo.video))])],1),t._v(" "),l("el-form-item",{attrs:{label:"备注"}},[l("el-alert",{attrs:{type:"error",closable:!1}},[t._v(t._s(t.detailInfo.status))])],1)],1)],1),t._v(" "),l("div",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"})],1)],1)},n=[],i=l("f8b7"),r={data:function(){return{friendList:[],friendPage:10,dialogFormVisible:!1,detailInfo:[]}},mounted:function(){var t=this;Object(i["a"])({page:0}).then((function(e){console.log(e.data),t.friendList=e.data}))},methods:{detail:function(t,e){this.detailInfo=e,this.dialogFormVisible=!0}},deleteShop:function(t,e){}},o=r,s=(l("7fee"),l("2877")),c=Object(s["a"])(o,a,n,!1,null,"3d93127f",null);e["default"]=c.exports},f8b7:function(t,e,l){"use strict";l.d(e,"b",(function(){return n})),l.d(e,"a",(function(){return i}));var a=l("b775");function n(t){return Object(a["a"])({url:"/admin/mallOrder/getOrdByPage",method:"get",params:t})}function i(t){return Object(a["a"])({url:"/mall/commodity/getMallShop",method:"get",params:t})}}}]);