(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-4205ecbe"],{"371d":function(e,t,l){"use strict";l.r(t);var o=function(){var e=this,t=e.$createElement,l=e._self._c||t;return l("div",{staticClass:"app-container"},[l("el-form",{ref:"form",attrs:{model:e.form,"label-width":"120px"}},[l("el-row",{staticClass:"row-bg",attrs:{type:"flex"}},[l("el-col",{attrs:{span:9}},[l("el-form-item",{attrs:{label:"商品名"}},[l("el-input",{model:{value:e.form.name,callback:function(t){e.$set(e.form,"name",t)},expression:"form.name"}})],1)],1),e._v(" "),l("el-col",{attrs:{span:3}},[l("el-form-item",{attrs:{label:"商品价格"}},[l("el-input",{model:{value:e.form.price,callback:function(t){e.$set(e.form,"price",t)},expression:"form.price"}})],1)],1),e._v(" "),l("el-col",{attrs:{span:9}},[l("el-form-item",{attrs:{label:"商品标签"}},[l("el-input",{model:{value:e.form.label,callback:function(t){e.$set(e.form,"label",t)},expression:"form.label"}})],1)],1),e._v(" "),l("el-col",{attrs:{span:3}},[l("el-form-item",{attrs:{label:"商品库存"}},[l("el-input",{model:{value:e.form.count,callback:function(t){e.$set(e.form,"count",t)},expression:"form.count"}})],1)],1)],1),e._v(" "),l("el-form-item",{attrs:{label:"商品图片"}},[l("el-upload",{ref:"upload",attrs:{action:"/fileUpload/upload","list-type":"picture-card",name:"myFile","on-success":e.uploadSuccess,"on-error":e.uploadError,"auto-upload":!1},scopedSlots:e._u([{key:"file",fn:function(t){var o=t.file;return l("div",{},[l("img",{staticClass:"el-upload-list__item-thumbnail",attrs:{src:o.url,alt:""}}),e._v(" "),l("span",{staticClass:"el-upload-list__item-actions"},[l("span",{staticClass:"el-upload-list__item-preview",on:{click:function(t){return e.handlePictureCardPreview(o)}}},[l("i",{staticClass:"el-icon-zoom-in"})]),e._v(" "),e.disabled?e._e():l("span",{staticClass:"el-upload-list__item-delete",on:{click:function(t){return e.handleDownload(o)}}},[l("i",{staticClass:"el-icon-download"})]),e._v(" "),e.disabled?e._e():l("span",{staticClass:"el-upload-list__item-delete",on:{click:function(t){return e.handleRemove(o)}}},[l("i",{staticClass:"el-icon-delete"})])])])}}])},[l("i",{staticClass:"el-icon-plus",attrs:{slot:"default"},slot:"default"})]),e._v(" "),l("el-dialog",{attrs:{visible:e.dialogVisible},on:{"update:visible":function(t){e.dialogVisible=t}}},[l("img",{attrs:{width:"100%",src:e.dialogImageUrl,alt:""}})])],1),e._v(" "),l("el-form-item",{attrs:{label:"商品描述"}},[l("el-input",{attrs:{type:"textarea"},model:{value:e.form.desc,callback:function(t){e.$set(e.form,"desc",t)},expression:"form.desc"}})],1),e._v(" "),l("el-form-item",[l("el-button",{attrs:{type:"primary"},on:{click:e.onSubmit}},[e._v("上传商品")]),e._v(" "),l("el-button",{on:{click:e.onCancel}},[e._v("取消")]),e._v(" "),l("el-button",{attrs:{type:"primary"},on:{click:e.myUpload}},[e._v("上传图片")])],1)],1)],1)},a=[],s=l("b775");function i(e){return Object(s["a"])({url:"/admin/commodity/createOrder",method:"post",params:e})}var n={data:function(){return{form:{name:"",price:"",label:"",count:"",desc:"",picture:[]},dialogImageUrl:"",dialogVisible:!1,disabled:!1}},methods:{uploadSuccess:function(e,t,l){console.log("success"+e),this.form.picture.push(e),console.log(e)},uploadError:function(e,t,l){console.log("error"),this.$message("error!")},myUpload:function(){this.$refs.upload.submit()},handleRemove:function(e){console.log(e)},handlePictureCardPreview:function(e){this.dialogImageUrl=e.url,this.dialogVisible=!0,console.log(e)},handleDownload:function(e){console.log(e)},onSubmit:function(){i(this.form).then((function(e){console.log(e)})),this.$message("submit!")},onCancel:function(){this.$message({message:"cancel!",type:"warning"})}}},r=n,c=(l("5061"),l("2877")),u=Object(c["a"])(r,o,a,!1,null,"187af861",null);t["default"]=u.exports},3863:function(e,t,l){},5061:function(e,t,l){"use strict";var o=l("3863"),a=l.n(o);a.a}}]);