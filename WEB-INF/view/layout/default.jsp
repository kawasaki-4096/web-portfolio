<s:layout-definition>
<!DOCTYPE html> 
<html>
  <head>
    <s:layout-component name="htmlHeader"><%@ include file="/WEB-INF/view/layout/htmlHead.jsp" %></s:layout-component>
  </head>
  <body>
  <div id="wrapper">
    <s:layout-component name="header"><jsp:include page="/WEB-INF/view/layout/header.jsp"/></s:layout-component>
    <div id="contents" class="contentsArea bgColor00" >
      <s:layout-component name="contents"/>
    </div>
    <s:layout-component name="controlPanel"/>
    <s:layout-component name="footer"> <jsp:include page="/WEB-INF/view/layout/footer.jsp"/> </s:layout-component>
  </div>
    <s:layout-component name="scripts" />
    <script type="text/javascript">
    </script>
  </body>
</html>
</s:layout-definition>
