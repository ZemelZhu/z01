(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-c7d63a02"],{"09c5":function(e,t,l){"use strict";var o=l("105e"),a=l.n(o);a.a},"105e":function(e,t,l){},"34cd":function(e,t,l){"use strict";l.d(t,"d",(function(){return a})),l.d(t,"a",(function(){return r})),l.d(t,"b",(function(){return i})),l.d(t,"c",(function(){return s})),l.d(t,"g",(function(){return n})),l.d(t,"f",(function(){return c})),l.d(t,"e",(function(){return u}));var o=l("b775");function a(){return Object(o["a"])({url:"/admin/mall/label/homeLabel",method:"get"})}function r(e){return Object(o["a"])({url:"/admin/mall/label/createLabel",method:"post",params:e})}function i(e){return Object(o["a"])({url:"/admin/mall/label/deleteLabel",method:"post",params:e})}function s(e){return Object(o["a"])({url:"/admin/mall/label/firstVideo",method:"post",params:e})}function n(e){return Object(o["a"])({url:"/admin/mall/label/salePicuture",method:"post",params:e})}function c(e){return Object(o["a"])({url:"/admin/mall/label/officialLink",method:"post",params:e})}function u(e){return Object(o["a"])({url:"/common/mall/show/getMallConfigById",method:"get",params:e})}},"371d":function(e,t,l){"use strict";l.r(t);var o=function(){var e=this,t=e.$createElement,l=e._self._c||t;return l("div",{staticClass:"app-container"},[l("el-form",{ref:"form",attrs:{model:e.form,"label-width":"120px"}},[l("el-form-item",{attrs:{label:"商品名"}},[l("el-input",{attrs:{placeholder:"最多20个字"},model:{value:e.form.name,callback:function(t){e.$set(e.form,"name",t)},expression:"form.name"}})],1),e._v(" "),l("el-row",{staticClass:"row-bg",attrs:{type:"flex"}},[l("el-col",{attrs:{span:4}},[l("el-form-item",{attrs:{label:"商品原价格"}},[l("el-input",{attrs:{placeholder:"小数点2位"},model:{value:e.form.price,callback:function(t){e.$set(e.form,"price",t)},expression:"form.price"}})],1)],1),e._v(" "),l("el-col",{attrs:{span:4}},[l("el-form-item",{attrs:{label:"商品折扣价格"}},[l("el-input",{attrs:{placeholder:"小数点2位"},model:{value:e.form.discountPrice,callback:function(t){e.$set(e.form,"discountPrice",t)},expression:"form.discountPrice"}})],1)],1),e._v(" "),l("el-col",{attrs:{span:4}},[l("el-form-item",{attrs:{label:"商品标签"}},[l("el-select",{attrs:{placeholder:"请选择"},model:{value:e.form.label,callback:function(t){e.$set(e.form,"label",t)},expression:"form.label"}},e._l(e.options,(function(e){return l("el-option",{key:e.value,attrs:{label:e.labelName,value:e.id}})})),1)],1)],1),e._v(" "),l("el-col",{attrs:{span:8}},[l("el-form-item",{attrs:{label:"商品库存"}},[l("el-input",{attrs:{placeholder:"填0为无限制"},model:{value:e.form.count,callback:function(t){e.$set(e.form,"count",t)},expression:"form.count"}})],1)],1)],1),e._v(" "),l("el-form-item",{attrs:{label:"商品视频"}},[l("el-input",{attrs:{placeholder:"请输入腾信视频链接"},model:{value:e.form.video,callback:function(t){e.$set(e.form,"video",t)},expression:"form.video"}})],1),e._v(" "),l("el-form-item",{attrs:{label:"商品轮播图片"}},[l("el-upload",{ref:"upload",attrs:{action:"/mallserver/fileUpload/upload","list-type":"picture-card",name:"myFile","on-success":e.uploadSuccess,"on-error":e.uploadError,"on-remove":e.handleRemove,"on-preview":e.handlePictureCardPreview,"auto-upload":!1,"file-list":e.fileList,limit:5}},[l("i",{staticClass:"el-icon-plus",attrs:{slot:"default"},slot:"default"})]),e._v(" "),l("el-dialog",{attrs:{visible:e.dialogVisible},on:{"update:visible":function(t){e.dialogVisible=t}}},[l("img",{attrs:{width:"100%",src:e.dialogImageUrl,alt:""}})])],1),e._v(" "),l("el-form-item",{attrs:{label:"商品描述"}},[l("el-input",{attrs:{type:"textarea",placeholder:"少于200字"},model:{value:e.form.desc,callback:function(t){e.$set(e.form,"desc",t)},expression:"form.desc"}})],1),e._v(" "),l("el-form-item",{attrs:{label:"商品描述图片"}},[l("el-upload",{ref:"upload1",attrs:{action:"/mallserver/fileUpload/upload","list-type":"picture-card",name:"myFile","on-success":e.uploadSuccess1,"on-error":e.uploadError,"on-remove":e.handleRemove,"on-preview":e.handlePictureCardPreview,"auto-upload":!1,"file-list":e.fileList1,limit:5}},[l("i",{staticClass:"el-icon-plus",attrs:{slot:"default"},slot:"default"})]),e._v(" "),l("el-dialog",{attrs:{visible:e.dialogVisible},on:{"update:visible":function(t){e.dialogVisible=t}}},[l("img",{attrs:{width:"100%",src:e.dialogImageUrl,alt:""}})])],1),e._v(" "),l("el-form-item",[l("el-button",{attrs:{type:"primary"},on:{click:e.onSubmit}},[e._v("上传商品")]),e._v(" "),l("el-button",{on:{click:e.onCancel}},[e._v("取消")]),e._v(" "),l("el-button",{attrs:{type:"primary"},on:{click:e.myUpload}},[e._v("上传图片")])],1)],1)],1)},a=[],r=(l("7f7f"),l("b775"));function i(e){return Object(r["a"])({url:"/admin/commodity/createOrder",method:"post",params:e})}var s=l("34cd"),n={data:function(){return{options:[],form:{name:"",price:"",discountPrice:"",label:"",count:"",desc:"",picture:"",video:"",descPicture:""},fileList:[],fileList1:[],dialogImageUrl:"",dialogVisible:!1,disabled:!1}},mounted:function(){var e=this;Object(s["d"])().then((function(t){e.options=t.data}))},methods:{uploadSuccess:function(e,t,l){""!=this.form.picture?this.form.picture=this.form.picture+","+e:this.form.picture=e,console.log(this.form.picture),this.$message("上传成功"+e)},uploadSuccess1:function(e,t,l){""!=this.form.descPicture?this.form.descPicture=this.form.descPicture+","+e:this.form.descPicture=e,console.log(this.form.descPicture),this.$message("上传成功"+e)},uploadError:function(e,t,l){console.log("error"),this.$message("上传失败")},myUpload:function(){this.$refs.upload.submit(),this.$refs.upload1.submit()},handleRemove:function(e){console.log(e)},handlePictureCardPreview:function(e){this.dialogImageUrl=e.url,this.dialogVisible=!0,console.log(e)},handleDownload:function(e){console.log(e)},onSubmit:function(){var e=this,t=this.form,l=!0;""!=t.name&&""!=t.price&&""!=t.discountPrice&&""!=t.label&&""!=t.video&&""!=t.desc||(l=!1),0!=l?i(this.form).then((function(t){console.log(t),e.$refs.form.resetFields()})):this.$message({message:"部分字段不能为空",type:"warning"})},onCancel:function(){this.$message({message:"cancel!",type:"warning"})}}},c=n,u=(l("09c5"),l("2877")),m=Object(u["a"])(c,o,a,!1,null,"0f313c8c",null);t["default"]=m.exports}}]);