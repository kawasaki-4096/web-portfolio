<s:form action="/admin/PageEdit.action" id="sys-page-edit-form">
<s:hidden name="bookId" />
<s:hidden name="pageId" />
公開  <s:checkbox name="publicFlag" /><br />
  </s:form>
<div id="sys-art-board">
<c:if test="${imageData != null}">
<img src="${ctx }/images/full/${imageData.imagePath }${imageData.fileName}" id="sys-main-vidual"/>
</c:if>
</div>
<script type="text/javascript">

</script>  
