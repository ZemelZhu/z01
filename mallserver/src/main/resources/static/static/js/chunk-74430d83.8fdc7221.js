(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-74430d83"],{8771:function(t,e,a){"use strict";var l=a("ee9e"),r=a.n(l);r.a},9406:function(t,e,a){"use strict";a.r(e);var l=function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",[a("el-table",{staticStyle:{width:"100%"},attrs:{data:t.friendList,border:""}},[a("el-table-column",{attrs:{label:"用户id",width:"100"},scopedSlots:t._u([{key:"default",fn:function(e){return[a("span",{staticStyle:{"margin-left":"10px"}},[t._v(t._s(e.row.createId))])]}}])}),t._v(" "),a("el-table-column",{attrs:{label:"产品id",width:"100"},scopedSlots:t._u([{key:"default",fn:function(e){return[a("span",{staticStyle:{"margin-left":"10px"}},[t._v(t._s(e.row.productId))])]}}])}),t._v(" "),a("el-table-column",{staticStyle:{width:"100%"},attrs:{label:"创建时间"},scopedSlots:t._u([{key:"default",fn:function(e){return[a("span",{staticStyle:{"margin-left":"10px"}},[t._v(t._s(e.row.createTime))])]}}])}),t._v(" "),a("el-table-column",{staticStyle:{width:"100%"},attrs:{label:"订单状态"},scopedSlots:t._u([{key:"default",fn:function(e){return[a("span",{staticStyle:{"margin-left":"10px"}},[t._v(t._s(e.row.status))])]}}])}),t._v(" "),a("el-table-column",{staticStyle:{width:"100%"},attrs:{label:"操作"},scopedSlots:t._u([{key:"default",fn:function(e){return[a("el-button",{attrs:{disabled:"true",size:"mini",type:"primary"},on:{click:function(a){return t.handleEdit(e.$index,e.row)}}},[t._v("订单操作")]),t._v(" "),a("el-button",{attrs:{size:"mini",type:"danger"},on:{click:function(a){return t.getUserInfo(e.$index,e.row)}}},[t._v("用户联系方式")])]}}])})],1),t._v(" "),a("br"),t._v(" "),a("el-pagination",{staticClass:"page",attrs:{background:"",layout:"prev, pager, next",total:t.friendPage}}),t._v(" "),a("el-dialog",{attrs:{title:"用户信息",visible:t.dialogFormVisible,width:"75%"},on:{"update:visible":function(e){t.dialogFormVisible=e}}},[a("el-card",{staticClass:"mes"},[a("el-form",{ref:"numberValidateForm",staticClass:"el-form",attrs:{"label-position":"left","label-width":"70px"}},[a("el-form-item",{attrs:{label:"账号"}},[a("el-alert",{attrs:{type:"success",closable:!1}},[t._v(t._s(t.user.id))])],1),t._v(" "),a("el-form-item",{attrs:{label:"姓名"}},[a("el-alert",{attrs:{type:"warning",closable:!1}},[t._v(t._s(t.user.name))])],1),t._v(" "),a("el-form-item",{attrs:{label:"电话"}},[a("el-alert",{attrs:{type:"warning",closable:!1}},[t._v(t._s(t.user.phone))])],1),t._v(" "),a("el-form-item",{attrs:{label:"地址"}},[a("el-alert",{attrs:{type:"warning",closable:!1}},[t._v(t._s(t.user.address))])],1),t._v(" "),a("el-form-item",{attrs:{label:"备注"}},[a("el-alert",{attrs:{type:"error",closable:!1}},[t._v(t._s(t.user.status))])],1)],1)],1),t._v(" "),a("div",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"})],1)],1)},r=[],n=a("f8b7"),s=a("c24f"),i={data:function(){return{friendList:[],friendPage:10,dialogFormVisible:!1,user:[]}},mounted:function(){var t=this;Object(n["c"])({page:0}).then((function(e){console.log(e.data),t.friendList=e.data}))},methods:{getUserInfo:function(t,e){var a=this;Object(s["b"])(e.createId).then((function(t){a.dialogFormVisible=!0,console.log(t.data),a.user=t.data}))}}},o=i,c=(a("8771"),a("2877")),u=Object(c["a"])(o,l,r,!1,null,"39b80c9a",null);e["default"]=u.exports},ee9e:function(t,e,a){},f8b7:function(t,e,a){"use strict";a.d(e,"c",(function(){return r})),a.d(e,"b",(function(){return n})),a.d(e,"a",(function(){return s}));var l=a("b775");function r(t){return Object(l["a"])({url:"/admin/mallOrder/getOrdByPage",method:"get",params:t})}function n(t){return Object(l["a"])({url:"/mall/commodity/getMallShop",method:"get",params:t})}function s(t){return Object(l["a"])({url:"/mall/commodity/delete",method:"delete",params:t})}}}]);