(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-40f9d33f"],{"34cd":function(e,t,l){"use strict";l.d(t,"d",(function(){return r})),l.d(t,"a",(function(){return o})),l.d(t,"b",(function(){return i})),l.d(t,"c",(function(){return n})),l.d(t,"g",(function(){return s})),l.d(t,"f",(function(){return c})),l.d(t,"e",(function(){return u}));var a=l("b775");function r(){return Object(a["a"])({url:"/admin/mall/label/homeLabel",method:"get"})}function o(e){return Object(a["a"])({url:"/admin/mall/label/createLabel",method:"post",params:e})}function i(e){return Object(a["a"])({url:"/admin/mall/label/deleteLabel",method:"post",params:e})}function n(e){return Object(a["a"])({url:"/admin/mall/label/firstVideo",method:"post",params:e})}function s(e){return Object(a["a"])({url:"/admin/mall/label/salePicuture",method:"post",params:e})}function c(e){return Object(a["a"])({url:"/admin/mall/label/officialLink",method:"post",params:e})}function u(e){return Object(a["a"])({url:"/common/mall/show/getMallConfigById",method:"get",params:e})}},afd3:function(e,t,l){"use strict";var a=l("e000"),r=l.n(a);r.a},ccc7:function(e,t,l){"use strict";l.r(t);var a=function(){var e=this,t=e.$createElement,l=e._self._c||t;return l("div",{staticClass:"app-container"},[l("el-form",{attrs:{"label-width":"80px"}},[l("el-form-item",{attrs:{label:"首页视频"}},[l("el-input",{attrs:{placeholder:"腾讯视频码"},model:{value:e.url,callback:function(t){e.url=t},expression:"url"}})],1),e._v(" "),l("el-form-item",[l("el-button",{attrs:{type:"success"},on:{click:e.onSubmit}},[e._v("替换首页视频")]),e._v(" "),l("el-link",{attrs:{href:e.url,target:"_blank"}},[l("el-button",{attrs:{type:"primary"}},[e._v("查看视频")])],1)],1)],1),e._v(" "),l("el-form",{attrs:{"label-width":"80px"}},[l("el-row",{staticClass:"row-bg",attrs:{type:"flex"}},[l("el-col",{attrs:{span:8}},[l("el-form-item",{attrs:{label:"标签图片"}},[l("el-upload",{ref:"upload",attrs:{action:"/mallserver/fileUpload/upload","list-type":"picture-card",name:"myFile","on-success":e.uploadSuccess,"on-error":e.uploadError,"on-remove":e.handleRemove,"on-preview":e.handlePictureCardPreview,"auto-upload":!1,"file-list":e.fileList,limit:1}},[l("i",{staticClass:"el-icon-plus",attrs:{slot:"default"},slot:"default"})]),e._v(" "),l("el-dialog",{attrs:{visible:e.dialogVisible},on:{"update:visible":function(t){e.dialogVisible=t}}},[l("img",{attrs:{width:"100%",src:e.dialogImageUrl,alt:""}})])],1)],1),e._v(" "),l("el-col",{attrs:{span:4}},[l("div",{staticClass:"demo-image__preview"},[l("el-image",{staticStyle:{width:"150px",height:"150px"},attrs:{src:e.value,"preview-src-list":e.pictureList}})],1)])],1),e._v(" "),l("el-form-item",[l("el-button",{attrs:{type:"danger",plain:""},on:{click:e.salePicuture}},[e._v("替换售后页面图片")])],1)],1),e._v(" "),l("el-form",{ref:"form",attrs:{model:e.form,"label-width":"100px"}},[l("el-form-item",{attrs:{label:"官方名称"}},[l("el-input",{attrs:{placeholder:"不能大于50个汉字"},model:{value:e.form.name,callback:function(t){e.$set(e.form,"name",t)},expression:"form.name"}})],1),e._v(" "),l("el-form-item",{attrs:{label:"官方电话"}},[l("el-input",{attrs:{placeholder:"请填写正确的电话号码"},model:{value:e.form.phone,callback:function(t){e.$set(e.form,"phone",t)},expression:"form.phone"}})],1),e._v(" "),l("el-form-item",{attrs:{label:"官方地址"}},[l("el-input",{attrs:{placeholder:"请填写正确的地址"},model:{value:e.form.address,callback:function(t){e.$set(e.form,"address",t)},expression:"form.address"}})],1),e._v(" "),l("el-form-item",{attrs:{label:"官方邮件"}},[l("el-input",{attrs:{placeholder:"请填写正确的邮件"},model:{value:e.form.mail,callback:function(t){e.$set(e.form,"mail",t)},expression:"form.mail"}})],1),e._v(" "),l("el-form-item",{attrs:{label:"官方描述"}},[l("el-input",{attrs:{type:"textarea",placeholder:"不能大于200个汉字"},model:{value:e.form.desc,callback:function(t){e.$set(e.form,"desc",t)},expression:"form.desc"}})],1),e._v(" "),l("el-form-item",[l("el-button",{attrs:{type:"warning"},on:{click:e.official}},[e._v("替换联系信息")])],1)],1)],1)},r=[],o=l("34cd"),i={data:function(){return{url:"",form:{name:"",phone:"",address:"",mail:"",desc:""},fileList:[],dialogImageUrl:"",dialogVisible:!1,pictureList:[],value:""}},mounted:function(){var e=this;Object(o["e"])({id:1}).then((function(t){e.url=t.data.videoUrl})),Object(o["e"])({id:2}).then((function(t){e.form=t.data})),Object(o["e"])({id:3}).then((function(t){e.pictureList=t.data.url,e.value=e.pictureList[0]}))},methods:{onSubmit:function(){Object(o["c"])({url:this.url})},official:function(){Object(o["f"])(this.form)},uploadSuccess:function(e,t,l){this.dialogImageUrl=e,console.log(this.dialogImageUrl),Object(o["g"])({url:this.dialogImageUrl})},uploadError:function(e,t,l){console.log("error"),this.$message("上传失败")},salePicuture:function(){this.$refs.upload.submit()},handlePictureCardPreview:function(e){this.dialogImageUrl=e.url,this.dialogVisible=!0,console.log(e)},handleRemove:function(e){console.log(e)},deletePicture:function(){null!=this.value&&deleteLabel({id:this.value.id}).then((function(e){console.log(e),window.location.reload()}))}}},n=i,s=(l("afd3"),l("2877")),c=Object(s["a"])(n,a,r,!1,null,"759d56e6",null);t["default"]=c.exports},e000:function(e,t,l){}}]);