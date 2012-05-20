    <meta http-equiv="Content-Type" content="text/html;charset=utf-8">
    <title>${actionBean.pageTitle}</title>
      <base href="${ctx}/" />
      <link href="${ctx }/css/reset.css" rel="stylesheet" type="text/css" />
      <link href="${ctx }/css/common.css" rel="stylesheet" type="text/css" />
      <link href="${ctx }/css/layout.css" rel="stylesheet" type="text/css" />
      <link href="${ctx }/css/skin.css" rel="stylesheet" type="text/css" />
      <script type="text/javascript" src="${ctx }/js/vx.js"></script>
      <script type="text/javascript" src="${ctx }/js/4096.js"></script>
      <!--[if lt IE 9]><script type="text/javascript" src="${ctx }/js/html5.js"></script><![endif]-->
      <script type="text/javascript" >
_.R(function(){	<c:if test="${!clntInfo.sendedFlag}">$4096.sendSizes({'sc.w':window.screen.width,'sc.h':window.screen.height,"v.w":$4096.cl.getW(),"v.h":$4096.cl.getH(),"vA.w":$4096.cl.getWA(),"vA.h":$4096.cl.getHA()});</c:if>});
      </script>
      <s:layout-component name="html_head"/>
    <style type="text/css">
<c:if test="${clntInfo.sendedFlag}">
    .contentsArea { 
  margin-left: -${clntInfo.clientSize.w/2}px;
  margin-top : -${clntInfo.clientSize.h/2}px;
  width :${clntInfo.clientSize.w}px;
  height:${clntInfo.clientSize.h}px;
</c:if>
    </style>
