    <meta http-equiv="Content-Type" content="text/html;charset=utf-8">
    <title>${actionBean.pageTitle}</title>
    <base href="<%=request.getContextPath() %>/" />
    <link href="css/reset.css" rel="stylesheet" type="text/css" />
    <link href="css/common.css" rel="stylesheet" type="text/css" />
    <link href="css/layout.css" rel="stylesheet" type="text/css" />
    <link href="css/skin.css" rel="stylesheet" type="text/css" />
    <link type="text/css" href="css/smoothness/jquery-ui-1.8.14.custom.css" rel="stylesheet" />
    <script type="text/javascript" src="js/jquery-1.6.2.min.js"></script>
    <script type="text/javascript" src="js/jquery-ui-1.8.14.custom.min.js"></script>
    <script type="text/javascript" src="js/jquery.dialogextend.min.js"></script>
    <!--[if lt IE 9]><script type="text/javascript" src="js/html5.js"></script><![endif]-->
    <s:layout-component name="html-head"/>
    <style type="text/css">
.border01{border:1px solid black;}
#controlPanel div:first-child span{cursor:pointer;
background: transparent url(images/detail.png) no-repeat  scroll left top;display:block;width:19px;height:19px;font-size:0;
}
#detailInfo   { margin:1em;position:absolute;top:0;width:30%;display:none; z-index:10;background-color:black;filter:alpha(opacity=80);-moz-opacity:0.8;opacity:0.8;}
#detailInfo * {color:white;}
#detailInfo table {  margin-bottom: 2em;}
#detailInfo table tr,th,td { text-align:left;font-size:0.9em;color:white;}
#detailInfo table th,td    { padding :0 1em;}
#detailInfo table th       { width :20%;}
#detailInfo table tr:last-child td { padding-top:1em}
#detailInfo div:first-child {padding :0 1em;}
#detailInfo #detailCloseButton {cursor:pointer;background: transparent url(images/close.png) no-repeat  scroll left top;display:block;width:19px;height:19px;font-size:0;}
.sys-pagingLink  { 
  position:absolute;
  bottom: 0;
  width :50%;
  height:94%;
  background-color: transparent;
  background-repeat: no-repeat;
  background-attachment: scroll;  
  background-position: left center;  
}
.sys-pagingLink a{ display:block; width :100%; height:100%; font-size:0;}
#sys-moreLink  {text-indent:1em; color:white;cursor:pointer;}
#sys-prevLink  { left : 0; background-image : url(images/mark-prev.png) ; background-position: left center; }
#sys-nextLink  { right: 0; background-image : url(images/mark-next.png) ; background-position: right center; }
        #sys-page-list{ overflow:scroll;float:left;width:160px;height:566px;border : 1px solid black;}
        #sys-page-edit-view{float:right;width:79%;border : 1px solid red;}
        
        #sys-image-list{ overflow:scroll;}
        #sys-art-board{ float:right;width:800px;height:566px; border : 1px solid black;}
        #sys-main-vidual{ width:100%;height:100%; }
    </style>
