webpackJsonp([1],{"+cgv":function(e,t){},"3f40":function(e,t){},"4qOc":function(e,t){},NHnr:function(e,t,o){"use strict";Object.defineProperty(t,"__esModule",{value:!0});var n={};o.d(n,"sendSock",function(){return w}),o.d(n,"setController",function(){return W}),o.d(n,"setdisConnection",function(){return T}),o.d(n,"initWebSocket",function(){return b}),o.d(n,"websock",function(){return C}),o.d(n,"websocketclose",function(){return k});var r=o("7+uW"),s={render:function(){var e=this.$createElement,t=this._self._c||e;return t("div",{attrs:{id:"app"}},[t("router-view")],1)},staticRenderFns:[]};var i=o("VU/8")({name:"App"},s,!1,function(e){o("WtXG")},null,null).exports,a=o("/ocq"),l=o("ifoU"),c=o.n(l),u=o("WDro"),p=o.n(u),d={fireUrl:"http://127.0.0.1:8084/Blog/FileUpload/upload",host:"127.0.0.1",pong:8084},g={name:"chatPage",data:function(){return{content:"",url:d.fireUrl}},components:{},props:["contentList","my","it"],watch:{content:"scrollToBottom"},methods:{scrollToBottom:function(){this.$nextTick(function(){var e=document.getElementById("chat");e.scrollTop=e.scrollHeight})},sendMeeage:function(){this.send(0,this.content)},send:function(e,t){var o=this,n=(this.it.id,new p.a.CommonMsgPB),r=new p.a.User;n.setContenttype(e),n.setType(p.a.MessageType.NEWFRIEND),n.setToid(1),n.setFromid(2),n.setContent(t),r.setUid(3),r.setPassword("44"),n.setUser(r),console.log("发送消息："+n),this.socketApi.sendSock(n),this.$nextTick(function(){o.content="";var e=document.getElementById("chat");e.scrollTop=e.scrollHeight+1e3})},beforeAvatarUpload:function(e){var t="image/jpeg"===e.type||"image/png"===e.type,o=e.size/1024/1024<5;return t||this.$message.error("上传头像图片只能是 JPG 格式!"),o||this.$message.error("上传头像图片大小不能超过 5MB!"),t&&o},handleAvatarSuccess:function(e,t){console.log("=>"+e.url),this.send(1,e.url)}}},m={render:function(){var e=this,t=e.$createElement,o=e._self._c||t;return o("div",[o("el-card",{staticClass:"card1",attrs:{id:"chat"}},[o("message",{attrs:{"content-list":e.contentList,my:e.my,it:e.it}})],1),e._v(" "),o("el-card",[o("el-input",{attrs:{type:"textarea",rows:2,placeholder:"请输入回复内容"},model:{value:e.content,callback:function(t){e.content=t},expression:"content"}}),e._v(" "),o("el-upload",{staticClass:" fire",attrs:{name:"myFile",action:e.url,"show-file-list":!1,"before-upload":e.beforeAvatarUpload,"on-success":e.handleAvatarSuccess}}),e._v(" "),o("el-button",{attrs:{type:"warning send"},on:{click:e.sendMeeage}},[e._v("发送")])],1)],1)},staticRenderFns:[]};var f={data:function(){return{my:"",it:"",msg:"",items:["3",666],mp:new c.a,meindex:new c.a,userList:[],chatBox:[],messageList:[],sid:""}},components:{chatPage:o("VU/8")(g,m,!1,function(e){o("bZGN")},"data-v-4f7765ea",null).exports},mounted:function(){},methods:{WebSocketByte:function(){this.initSocket("ws://127.0.0.1:8084/test/gameServer")},SpringNettyByte:function(){this.initSocket("ws://127.0.0.1:5000/websocket")},initSocket:function(e){this.socketApi.initWebSocket(e),this.my=1,this.socketApi.setController(this.controller),this.socketApi.setdisConnection(this.disconnect),this.getFriendsList()},controller:function(e){var t=new p.a.CommonMsgPB.deserializeBinary(e);console.log("接受消息:"+t)},getFriendsList:function(){},disconnect:function(){},analyzeOneMessage:function(e){var t=new Object;t.fromId=e.getFromid(),t.toId=e.getToid(),t.content=e.getContent(),t.time=e.getTime(),t.type=e.getContenttype();var o=t.fromId;o==this.my.uid&&(o=t.toId);var n=this.mp.get(o);console.log(n),n.push(t);var r=this.meindex.get(o);this.messageList[r]=t},toggle:function(e){this.sid=e.id;var t=e.id;this.it=e,this.chatBox=this.mp.get(t)}}},h={render:function(){var e=this,t=e.$createElement,o=e._self._c||t;return o("div",[o("el-row",[o("el-col",{attrs:{xs:4,sm:12,md:4,lg:6,xl:6}},[o("div",{staticClass:"card2"},[o("el-button",{attrs:{type:"success send"},on:{click:e.WebSocketByte}},[e._v("WebSocketByte")]),e._v(" "),o("br"),o("br"),e._v(" "),o("el-button",{attrs:{type:"danger send"},on:{click:e.SpringNettyByte}},[e._v("SpringNettyByte")])],1)]),e._v(" "),o("el-col",{attrs:{xs:12,sm:12,md:12,lg:18,xl:18}},[o("chat-page",{attrs:{"content-list":e.chatBox,my:e.my,it:e.it}})],1)],1)],1)},staticRenderFns:[]};var y=o("VU/8")(f,h,!1,function(e){o("sfYy")},"data-v-426eb0c2",null).exports;r.default.use(a.a);var M=new a.a({routes:[{path:"/",component:y}]}),B=o("zL8q"),v=o.n(B),C=(o("tvR6"),null),P=null,F=null,U=null;function b(e){e="ws://"+d.host+":8084/test/gameServer";null==C||C.readyState!=C.OPEN?(C=new WebSocket(e),null!=U&&clearInterval(U),console.log(e+"连接成功")):alert("请先关闭连接"),C.onmessage=function(e){!function(e){var t=new FileReader;t.readAsArrayBuffer(e.data),t.onload=function(e){var o=new Uint8Array(t.result);P(o)}}(e)},C.onclose=function(e){k(e)},C.onopen=function(){console.log("连接成功")},C.onerror=function(e){k(e)},C.onerror=function(){console.log("WebSocket连接发生错误")}}function w(e){var t;null!=C?C.readyState===C.OPEN?(t=e,0,C.send(t.serializeBinary()),console.log("字节为:"+t.serializeBinary())):C.readyState===C.CONNECTING?setTimeout(function(){},1e3):(alert("发送失败"),F()):alert("websocket没有建立")}function k(e){null!=C&&(console.log("socket断开连接"),clearInterval(U),C.close(),F())}function W(e){P=e}function T(e){F=e}var S=o("mtWM"),E=o.n(S),I=o("G0J2"),D=o.n(I);o("3f40"),o("4qOc"),o("+cgv");r.default.use(D.a),r.default.prototype.axios=E.a,r.default.use(v.a),r.default.config.productionTip=!1,r.default.prototype.socketApi=n,new r.default({el:"#app",router:M,components:{App:i},template:"<App/>",mounted:function(){}})},WDro:function(e,t,o){var n=o("LkYP"),r=n,s=Function("return this")();r.exportSymbol("proto.CommonMsgPB",null,s),r.exportSymbol("proto.MessageType",null,s),r.exportSymbol("proto.User",null,s),proto.CommonMsgPB=function(e){n.Message.initialize(this,e,0,-1,null,null)},r.inherits(proto.CommonMsgPB,n.Message),r.DEBUG&&!COMPILED&&(proto.CommonMsgPB.displayName="proto.CommonMsgPB"),n.Message.GENERATE_TO_OBJECT&&(proto.CommonMsgPB.prototype.toObject=function(e){return proto.CommonMsgPB.toObject(e,this)},proto.CommonMsgPB.toObject=function(e,t){var o,r={type:n.Message.getFieldWithDefault(t,1,0),fromid:n.Message.getFieldWithDefault(t,2,0),toid:n.Message.getFieldWithDefault(t,3,0),content:n.Message.getFieldWithDefault(t,4,""),user:(o=t.getUser())&&proto.User.toObject(e,o),time:n.Message.getFieldWithDefault(t,6,""),contenttype:n.Message.getFieldWithDefault(t,7,0)};return e&&(r.$jspbMessageInstance=t),r}),proto.CommonMsgPB.deserializeBinary=function(e){var t=new n.BinaryReader(e),o=new proto.CommonMsgPB;return proto.CommonMsgPB.deserializeBinaryFromReader(o,t)},proto.CommonMsgPB.deserializeBinaryFromReader=function(e,t){for(;t.nextField()&&!t.isEndGroup();){switch(t.getFieldNumber()){case 1:var o=t.readEnum();e.setType(o);break;case 2:o=t.readInt32();e.setFromid(o);break;case 3:o=t.readInt32();e.setToid(o);break;case 4:o=t.readString();e.setContent(o);break;case 5:o=new proto.User;t.readMessage(o,proto.User.deserializeBinaryFromReader),e.setUser(o);break;case 6:o=t.readString();e.setTime(o);break;case 7:o=t.readInt32();e.setContenttype(o);break;default:t.skipField()}}return e},proto.CommonMsgPB.prototype.serializeBinary=function(){var e=new n.BinaryWriter;return proto.CommonMsgPB.serializeBinaryToWriter(this,e),e.getResultBuffer()},proto.CommonMsgPB.serializeBinaryToWriter=function(e,t){var o=void 0;0!==(o=e.getType())&&t.writeEnum(1,o),0!==(o=e.getFromid())&&t.writeInt32(2,o),0!==(o=e.getToid())&&t.writeInt32(3,o),(o=e.getContent()).length>0&&t.writeString(4,o),null!=(o=e.getUser())&&t.writeMessage(5,o,proto.User.serializeBinaryToWriter),(o=e.getTime()).length>0&&t.writeString(6,o),0!==(o=e.getContenttype())&&t.writeInt32(7,o)},proto.CommonMsgPB.prototype.getType=function(){return n.Message.getFieldWithDefault(this,1,0)},proto.CommonMsgPB.prototype.setType=function(e){n.Message.setProto3EnumField(this,1,e)},proto.CommonMsgPB.prototype.getFromid=function(){return n.Message.getFieldWithDefault(this,2,0)},proto.CommonMsgPB.prototype.setFromid=function(e){n.Message.setProto3IntField(this,2,e)},proto.CommonMsgPB.prototype.getToid=function(){return n.Message.getFieldWithDefault(this,3,0)},proto.CommonMsgPB.prototype.setToid=function(e){n.Message.setProto3IntField(this,3,e)},proto.CommonMsgPB.prototype.getContent=function(){return n.Message.getFieldWithDefault(this,4,"")},proto.CommonMsgPB.prototype.setContent=function(e){n.Message.setProto3StringField(this,4,e)},proto.CommonMsgPB.prototype.getUser=function(){return n.Message.getWrapperField(this,proto.User,5)},proto.CommonMsgPB.prototype.setUser=function(e){n.Message.setWrapperField(this,5,e)},proto.CommonMsgPB.prototype.clearUser=function(){this.setUser(void 0)},proto.CommonMsgPB.prototype.hasUser=function(){return null!=n.Message.getField(this,5)},proto.CommonMsgPB.prototype.getTime=function(){return n.Message.getFieldWithDefault(this,6,"")},proto.CommonMsgPB.prototype.setTime=function(e){n.Message.setProto3StringField(this,6,e)},proto.CommonMsgPB.prototype.getContenttype=function(){return n.Message.getFieldWithDefault(this,7,0)},proto.CommonMsgPB.prototype.setContenttype=function(e){n.Message.setProto3IntField(this,7,e)},proto.User=function(e){n.Message.initialize(this,e,0,-1,null,null)},r.inherits(proto.User,n.Message),r.DEBUG&&!COMPILED&&(proto.User.displayName="proto.User"),n.Message.GENERATE_TO_OBJECT&&(proto.User.prototype.toObject=function(e){return proto.User.toObject(e,this)},proto.User.toObject=function(e,t){var o={uid:n.Message.getFieldWithDefault(t,1,0),password:n.Message.getFieldWithDefault(t,2,"")};return e&&(o.$jspbMessageInstance=t),o}),proto.User.deserializeBinary=function(e){var t=new n.BinaryReader(e),o=new proto.User;return proto.User.deserializeBinaryFromReader(o,t)},proto.User.deserializeBinaryFromReader=function(e,t){for(;t.nextField()&&!t.isEndGroup();){switch(t.getFieldNumber()){case 1:var o=t.readInt32();e.setUid(o);break;case 2:o=t.readString();e.setPassword(o);break;default:t.skipField()}}return e},proto.User.prototype.serializeBinary=function(){var e=new n.BinaryWriter;return proto.User.serializeBinaryToWriter(this,e),e.getResultBuffer()},proto.User.serializeBinaryToWriter=function(e,t){var o=void 0;0!==(o=e.getUid())&&t.writeInt32(1,o),(o=e.getPassword()).length>0&&t.writeString(2,o)},proto.User.prototype.getUid=function(){return n.Message.getFieldWithDefault(this,1,0)},proto.User.prototype.setUid=function(e){n.Message.setProto3IntField(this,1,e)},proto.User.prototype.getPassword=function(){return n.Message.getFieldWithDefault(this,2,"")},proto.User.prototype.setPassword=function(e){n.Message.setProto3StringField(this,2,e)},proto.MessageType={MSG:0,LOGIN:1,NEWFRIEND:3,ERROR:4,PING:5},r.object.extend(t,proto)},WtXG:function(e,t){},bZGN:function(e,t){},sfYy:function(e,t){},tvR6:function(e,t){}},["NHnr"]);
//# sourceMappingURL=app.63d631e2df0af32746a6.js.map