(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-32966ab8"],{"1ccb":function(e,t,l){},"34cd":function(e,t,l){"use strict";l.d(t,"d",(function(){return a})),l.d(t,"a",(function(){return n})),l.d(t,"b",(function(){return o})),l.d(t,"c",(function(){return c})),l.d(t,"f",(function(){return i})),l.d(t,"e",(function(){return m}));var r=l("b775");function a(){return Object(r["a"])({url:"/admin/mall/label/homeLabel",method:"get"})}function n(e){return Object(r["a"])({url:"/admin/mall/label/createLabel",method:"post",params:e})}function o(e){return Object(r["a"])({url:"/admin/mall/label/deleteLabel",method:"post",params:e})}function c(e){return Object(r["a"])({url:"/admin/mall/label/firstVideo",method:"post",params:e})}function i(e){return Object(r["a"])({url:"/admin/mall/label/officialLink",method:"post",params:e})}function m(e){return Object(r["a"])({url:"/common/mall/show/getMallConfigById",method:"get",params:e})}},ccc7:function(e,t,l){"use strict";l.r(t);var r=function(){var e=this,t=e.$createElement,l=e._self._c||t;return l("div",{staticClass:"app-container"},[l("el-form",{attrs:{"label-width":"80px"}},[l("el-form-item",{attrs:{label:"视频地址"}},[l("el-input",{attrs:{placeholder:"腾讯视频码"},model:{value:e.url,callback:function(t){e.url=t},expression:"url"}})],1),e._v(" "),l("el-form-item",[l("el-button",{attrs:{type:"success"},on:{click:e.onSubmit}},[e._v("替换首页视频")]),e._v(" "),l("el-link",{attrs:{href:e.url,target:"_blank"}},[l("el-button",{attrs:{type:"primary"}},[e._v("查看视频")])],1)],1)],1),e._v(" "),l("el-form",{ref:"form",attrs:{model:e.form}},[l("el-form-item",{attrs:{label:"官方联系名"}},[l("el-input",{attrs:{placeholder:"不能大于50个汉字"},model:{value:e.form.name,callback:function(t){e.$set(e.form,"name",t)},expression:"form.name"}})],1),e._v(" "),l("el-form-item",{attrs:{label:"官方电话"}},[l("el-input",{attrs:{placeholder:"请填写正确的电话号码"},model:{value:e.form.phone,callback:function(t){e.$set(e.form,"phone",t)},expression:"form.phone"}})],1),e._v(" "),l("el-form-item",{attrs:{label:"官方地址"}},[l("el-input",{attrs:{placeholder:"请填写正确的地址"},model:{value:e.form.address,callback:function(t){e.$set(e.form,"address",t)},expression:"form.address"}})],1),e._v(" "),l("el-form-item",{attrs:{label:"官方邮件"}},[l("el-input",{attrs:{placeholder:"请填写正确的邮件"},model:{value:e.form.mail,callback:function(t){e.$set(e.form,"mail",t)},expression:"form.mail"}})],1),e._v(" "),l("el-form-item",{attrs:{label:"官方描述"}},[l("el-input",{attrs:{type:"textarea",placeholder:"不能大于200个汉字"},model:{value:e.form.desc,callback:function(t){e.$set(e.form,"desc",t)},expression:"form.desc"}})],1),e._v(" "),l("el-form-item",[l("el-button",{attrs:{type:"warning"},on:{click:e.official}},[e._v("替换联系信息")])],1)],1)],1)},a=[],n=l("34cd"),o={data:function(){return{url:"",form:{name:"",phone:"",address:"",mail:"",desc:""}}},mounted:function(){var e=this;Object(n["e"])({id:1}).then((function(t){e.url=t.data.videoUrl})),Object(n["e"])({id:2}).then((function(t){e.form=t.data}))},methods:{onSubmit:function(){Object(n["c"])({url:this.url})},official:function(){Object(n["f"])(this.form)}}},c=o,i=(l("d23f"),l("2877")),m=Object(i["a"])(c,r,a,!1,null,"81d25a1a",null);t["default"]=m.exports},d23f:function(e,t,l){"use strict";var r=l("1ccb"),a=l.n(r);a.a}}]);